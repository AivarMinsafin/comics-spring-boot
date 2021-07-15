package ru.itis.aivar.comics.app.dto;

import lombok.*;
import ru.itis.aivar.comics.app.models.Chapter;


import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(of = "id")
public class ChapterDto {

    private Long id;
    private String name;
    private Integer number;
    private Long titleId;
    private Long previousChapterId;
    private Long nextChapterId;

    public static ChapterDto from(Chapter chapter){
        return ChapterDto.builder()
                .id(chapter.getId())
                .name(chapter.getName())
                .number(chapter.getChapterNumber())
                .titleId(chapter.getTitle().getId())
                .build();
    }

    public static List<ChapterDto> from(Collection<Chapter> chapters){
        return chapters.stream().map(ChapterDto::from).collect(Collectors.toList());
    }

}
