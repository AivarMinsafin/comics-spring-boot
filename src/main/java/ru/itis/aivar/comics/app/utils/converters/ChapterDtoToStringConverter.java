package ru.itis.aivar.comics.app.utils.converters;

import org.springframework.core.convert.converter.Converter;
import ru.itis.aivar.comics.app.dto.ChapterDto;

public class ChapterDtoToStringConverter implements Converter<ChapterDto, String> {
    @Override
    public String convert(ChapterDto chapterDto) {
        return String.valueOf(chapterDto.getId());
    }
}
