package ru.itis.aivar.comics.app.services;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.aivar.comics.app.dto.AuthorDto;
import ru.itis.aivar.comics.app.dto.ChapterDto;
import ru.itis.aivar.comics.app.dto.TitleDto;
import ru.itis.aivar.comics.app.dto.forms.TitleCreationForm;
import ru.itis.aivar.comics.app.dto.forms.TitleEditForm;
import ru.itis.aivar.comics.app.exceptions.NotFoundException;
import ru.itis.aivar.comics.app.models.Author;
import ru.itis.aivar.comics.app.models.Title;
import ru.itis.aivar.comics.app.models.User;
import ru.itis.aivar.comics.app.repositories.AuthorRepository;
import ru.itis.aivar.comics.app.repositories.ChapterRepository;
import ru.itis.aivar.comics.app.repositories.TitleRepository;
import ru.itis.aivar.comics.app.repositories.UserRepository;
import ru.itis.aivar.comics.app.utils.images.ImageStorageUtil;
import ru.itis.aivar.comics.app.utils.images.PathCreator;

import java.util.List;

import static ru.itis.aivar.comics.app.dto.TitleDto.from;


@Service
public class TitleServiceImpl implements TitleService {

    @Autowired
    private TitleRepository titleRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ImageStorageUtil imageStorageUtil;
    @Autowired
    private PathCreator pathCreator;
    @Autowired
    private ChapterRepository chapterRepository;

    @Override
    public List<TitleDto> getTitlesPageable(int count, int page) {
        return from(titleRepository.findAll(PageRequest.of(page, count)).getContent());
    }

    @Override
    public List<TitleDto> getAllTitles() {
        return from(titleRepository.findAll());
    }

    @Override
    public void deleteTitle(Long id) {
        Title title = titleRepository.findById(id).orElseThrow(NotFoundException::new);
        titleRepository.delete(title);
        imageStorageUtil.deleteDirectoryByPath(pathCreator.createTitlePath(title));
    }

    @Override
    public TitleDto getTitleById(Long id) {
        Title title = titleRepository.findById(id).orElseThrow(NotFoundException::new);
        return from(title);
    }

    @Override
    public TitleDto createTitle(TitleCreationForm form) {
        Title title = Title.builder()
                .name(form.getName())
                .description(form.getDescription())
                .author(authorRepository.findByUser(userRepository.findByEmail(form.getEmail()).orElseThrow(NotFoundException::new)).orElseThrow(NotFoundException::new))
                .build();

        title = titleRepository.save(title);

        MultipartFile image = form.getImage();
        if (!image.isEmpty()) {
            updateTitleImage(title, image);
        }

        title = titleRepository.save(title);

        return from(title);
    }

    @Override
    public TitleDto updateTitle(TitleEditForm form) {
        Title title = titleRepository.findById(form.getId()).orElseThrow(NotFoundException::new);

        title.setName(form.getName());
        title.setDescription(form.getDescription());

        MultipartFile image = form.getImage();
        if (!image.isEmpty()) {
            updateTitleImage(title, image);
        }
        title = titleRepository.save(title);

        return from(title);
    }

    @Override
    public List<TitleDto> findAllTitlesByAuthor(AuthorDto authorDto) {
        Author author = authorRepository.findById(authorDto.getId()).orElseThrow(NotFoundException::new);
        return from(titleRepository.findAllByAuthor(author));
    }

    @Override
    public List<TitleDto> findAllTitlesByAuthorUserEmail(String email) {
        Author author = authorRepository.findByUser(userRepository.findByEmail(email).orElseThrow(NotFoundException::new)).orElseThrow(NotFoundException::new);
        return from(titleRepository.findAllByAuthor(author));
    }

    @Override
    public List<TitleDto> findAllTitlesByUserEmail(String email) {
        return from(titleRepository.findAllByUsersContaining(userRepository.findByEmail(email).orElseThrow(NotFoundException::new)));
    }

    @Override
    public boolean isAuthor(String email, Long titleId) {
        Author author = authorRepository.findByUser(userRepository.findByEmail(email).orElseThrow(NotFoundException::new)).orElseThrow(NotFoundException::new);
        List<Title> titles = titleRepository.findAllByAuthor(author);
        Title title = titleRepository.findById(titleId).orElseThrow(NotFoundException::new);
        return titles.contains(title);
    }

    @Override
    public boolean isReader(String email, Long titleId) {
        User user = userRepository.findByEmail(email).orElseThrow(NotFoundException::new);
        Title title = titleRepository.findById(titleId).orElseThrow(NotFoundException::new);
        List<Title> titles = titleRepository.findAllByUsersContaining(user);
        return titles.contains(title);
    }

    @Override
    public Integer readersCountByTitleId(Long id) {
        return titleRepository.titleReadersCount(id);
    }

    @Override
    public List<ChapterDto> findAllChaptersByTitleId(Long id) {
        return ChapterDto.from(chapterRepository.findAllByTitle_IdOrderByChapterNumberAsc(id));
    }

    @Override
    public AuthorDto findAuthorByTitleId(Long id) {
        return AuthorDto.from(authorRepository.findByTitlesContaining(titleRepository.findById(id).orElseThrow(NotFoundException::new)).orElseThrow(NotFoundException::new));
    }

    private void updateTitleImage(Title title, MultipartFile image) {
        if (title.getImagePath() != null && imageStorageUtil.exists(title.getImagePath())) {
            imageStorageUtil.deleteImage(title.getImagePath());
        }
        String imagePathForTitle = pathCreator.createTitleImagePath(title, image.getOriginalFilename());
        imageStorageUtil.saveImageMultipartFile(imagePathForTitle, image);
        title.setImagePath(imagePathForTitle);
    }
}
