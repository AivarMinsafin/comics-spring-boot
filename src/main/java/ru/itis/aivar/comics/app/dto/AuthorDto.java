package ru.itis.aivar.comics.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.aivar.comics.app.models.Author;


import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorDto {

    private Long id;
    private String firstname;
    private String lastname;
    private String phoneNumber;

    public static AuthorDto from(Author author){
        return AuthorDto.builder()
                .id(author.getId())
                .firstname(author.getFirstName())
                .lastname(author.getLastName())
                .phoneNumber(author.getPhoneNumber())
                .build();
    }

    public static List<AuthorDto> from(Collection<Author> authors){
        return authors.stream().map(AuthorDto::from).collect(Collectors.toList());
    }

}
