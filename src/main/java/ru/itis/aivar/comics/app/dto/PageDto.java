package ru.itis.aivar.comics.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.aivar.comics.app.models.Page;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageDto {

    private Long id;
    private String path;

    public static PageDto from(Page page){
        return PageDto.builder()
                .id(page.getId())
                .path(page.getPath())
                .build();
    }

    public static List<PageDto> from(Collection<Page> pages){
        return pages.stream().map(PageDto::from).collect(Collectors.toList());
    }


}
