package ru.itis.aivar.comics.app.utils.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.itis.aivar.comics.app.dto.ChapterDto;
import ru.itis.aivar.comics.app.services.ChapterService;

@Component
public class StringToChapterConverter implements Converter<String, ChapterDto> {

    @Autowired
    private ChapterService chapterService;

    @Override
    public ChapterDto convert(String s) {
        Long id = Long.parseLong(s);
        return chapterService.findById(id);
    }
}
