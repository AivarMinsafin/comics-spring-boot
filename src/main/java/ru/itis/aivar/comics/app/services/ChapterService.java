package ru.itis.aivar.comics.app.services;

import ru.itis.aivar.comics.app.dto.ChapterDto;
import ru.itis.aivar.comics.app.dto.PageDto;
import ru.itis.aivar.comics.app.dto.TitleDto;
import ru.itis.aivar.comics.app.dto.forms.ChapterCreationForm;
import ru.itis.aivar.comics.app.dto.forms.ChapterEditForm;
import ru.itis.aivar.comics.app.models.Page;

import java.util.List;

public interface ChapterService {

    ChapterDto findById(Long id);

    List<ChapterDto> findByTitleId(Long id);

    List<ChapterDto> findByTitle(TitleDto titleDto);

    ChapterDto save(ChapterCreationForm form);

    ChapterDto update(ChapterEditForm form);

    TitleDto findTitleByChapterId(Long id);

    void deleteById(Long id);

    List<PageDto> findPagesByChapterId(Long id);
}
