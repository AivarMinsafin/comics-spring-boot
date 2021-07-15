package ru.itis.aivar.comics.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.aivar.comics.app.models.Title;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TitleDto {

    private Long id;
    private String name;
    private String description;
    private String imagePath;


    public static TitleDto from(Title title){
        return TitleDto.builder()
                .id(title.getId())
                .name(title.getName())
                .description(title.getDescription())
                .imagePath(title.getImagePath())
                .build();
    }

    public static List<TitleDto> from(Collection<Title> titles){
        return titles.stream().map(TitleDto::from).collect(Collectors.toList());
    }

}
