package ru.itis.aivar.comics.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.aivar.comics.app.dto.ChapterDto;
import ru.itis.aivar.comics.app.dto.PageDto;
import ru.itis.aivar.comics.app.dto.TitleDto;
import ru.itis.aivar.comics.app.dto.forms.ChapterCreationForm;
import ru.itis.aivar.comics.app.dto.forms.ChapterEditForm;
import ru.itis.aivar.comics.app.exceptions.NotFoundException;
import ru.itis.aivar.comics.app.models.Chapter;
import ru.itis.aivar.comics.app.models.Page;
import ru.itis.aivar.comics.app.models.Title;
import ru.itis.aivar.comics.app.repositories.ChapterRepository;
import ru.itis.aivar.comics.app.repositories.PageRepository;
import ru.itis.aivar.comics.app.repositories.TitleRepository;
import ru.itis.aivar.comics.app.utils.images.ImageStorageUtil;
import ru.itis.aivar.comics.app.utils.images.PathCreator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static ru.itis.aivar.comics.app.dto.ChapterDto.from;

@Service
public class ChapterServiceImpl implements ChapterService {
    @Autowired
    private ChapterRepository chapterRepository;
    @Autowired
    private PageRepository pageRepository;
    @Autowired
    private TitleRepository titleRepository;
    @Autowired
    private ImageStorageUtil imageStorageUtil;
    @Autowired
    private PathCreator pathCreator;

    @Override
    public ChapterDto findById(Long id) {
        ChapterDto chapter = from(chapterRepository.findById(id).orElseThrow(NotFoundException::new));
        Chapter previousChapter = chapterRepository.findByChapterNumberAndTitle_Id(chapter.getNumber()-1, chapter.getTitleId()).orElse(null);
        Chapter nextChapter = chapterRepository.findByChapterNumberAndTitle_Id(chapter.getNumber()+1, chapter.getTitleId()).orElse(null);
        if (previousChapter != null){
            chapter.setPreviousChapterId(previousChapter.getId());
        }
        if (nextChapter != null){
            chapter.setNextChapterId(nextChapter.getId());
        }
        return chapter;
    }

    @Override
    public List<ChapterDto> findByTitleId(Long id) {
        return from(chapterRepository.findAllByTitle_IdOrderByChapterNumberAsc(id));
    }

    @Override
    public List<ChapterDto> findByTitle(TitleDto titleDto) {
        return from(chapterRepository.findAllByTitle_IdOrderByChapterNumberAsc(titleDto.getId()));
    }

    @Override
    public ChapterDto save(ChapterCreationForm form) {
        Chapter prevChapter = chapterRepository.findFirstByOrderByChapterNumberDesc(form.getId()).orElse(null);
        Chapter chapter = Chapter.builder()
                .name(form.getName())
                .title(titleRepository.findById(form.getId()).orElseThrow(NotFoundException::new))
                .pages(new HashSet<>())
                .build();
        chapter.setChapterNumber(prevChapter != null ? prevChapter.getChapterNumber() + 1 : 1);

        chapter = chapterRepository.save(chapter);

        List<MultipartFile> files = form.getPages();
        if (!files.isEmpty()){
            for (int i = 0; i < files.size(); i++) {
                Page page = Page.builder()
                        .chapter(chapter)
                        .pageNumber(i + 1)
                        .build();
                pageSetUp(page, files.get(i));
                chapter.getPages().add(page);
                pageRepository.save(page);
            }
            chapter = chapterRepository.save(chapter);
        }

        return from(chapter);
    }

    @Override
    public ChapterDto update(ChapterEditForm form) {
        Chapter chapter = chapterRepository.findById(form.getId()).orElseThrow(NotFoundException::new);
        chapter.setName(form.getName());
        Set<Page> pages = new HashSet<>();

        List<MultipartFile> files = form.getPages();
        if (!files.isEmpty() && !files.get(0).isEmpty()){
            for (int i = 0; i < files.size(); i++) {
                Page page = Page.builder()
                        .chapter(chapter)
                        .pageNumber(i + 1)
                        .build();
                pageSetUp(page, files.get(i));
                pages.add(page);
            }
            chapter.setPages(pages);
        }

        chapter = chapterRepository.save(chapter);
        return from(chapter);
    }

    @Override
    public TitleDto findTitleByChapterId(Long id) {
        return TitleDto.from(titleRepository.findByChaptersContaining(chapterRepository.findById(id).orElseThrow(NotFoundException::new)).orElseThrow(NotFoundException::new));
    }

    @Override
    public void deleteById(Long id) {
        Chapter chapter = chapterRepository.findById(id).orElseThrow(NotFoundException::new);
        chapterRepository.deleteById(id);
        imageStorageUtil.deleteDirectoryByPath(pathCreator.createChapterPath(chapter));
        updateChaptersOrder(chapter.getTitle().getId());
    }

    @Override
    public List<PageDto> findPagesByChapterId(Long id) {
        return PageDto.from(pageRepository.findAllByChapter_Id(id));
    }

    private void pageSetUp(Page page, MultipartFile file) {
        String imagePathForPage = pathCreator.createChapterPageImagePath(page.getChapter(), file.getOriginalFilename());
        imageStorageUtil.saveImageMultipartFile(imagePathForPage, file);
        page.setPath(imagePathForPage);
    }

    private void updateChaptersOrder(Long titleId) {
        List<Chapter> chapters = chapterRepository.findAllByTitle_IdOrderByChapterNumberAsc(titleId);
        for (int i = 0; i < chapters.size(); i++) {
            chapters.get(i).setChapterNumber(i + 1);
        }
        chapterRepository.saveAll(chapters);
    }
}
