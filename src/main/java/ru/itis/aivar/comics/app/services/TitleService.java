package ru.itis.aivar.comics.app.services;

import ru.itis.aivar.comics.app.dto.AuthorDto;
import ru.itis.aivar.comics.app.dto.ChapterDto;
import ru.itis.aivar.comics.app.dto.TitleDto;
import ru.itis.aivar.comics.app.dto.forms.TitleCreationForm;
import ru.itis.aivar.comics.app.dto.forms.TitleEditForm;

import java.util.List;

public interface TitleService {

    List<TitleDto> getTitlesPageable(int count, int page);

    List<TitleDto> getAllTitles();

    TitleDto createTitle(TitleCreationForm form);

    void deleteTitle(Long id);

    TitleDto getTitleById(Long id);

    TitleDto updateTitle(TitleEditForm form);

    List<TitleDto> findAllTitlesByAuthor(AuthorDto authorDto);

    List<TitleDto> findAllTitlesByAuthorUserEmail(String email);
    List<TitleDto> findAllTitlesByUserEmail(String email);

    boolean isAuthor(String email, Long titleId);

    boolean isReader(String email, Long titleId);

    Integer readersCountByTitleId(Long id);

    List<ChapterDto> findAllChaptersByTitleId(Long id);

    AuthorDto findAuthorByTitleId(Long id);
}
