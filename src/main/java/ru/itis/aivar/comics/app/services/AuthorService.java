package ru.itis.aivar.comics.app.services;

import ru.itis.aivar.comics.app.dto.AuthorDto;
import ru.itis.aivar.comics.app.dto.TitleDto;
import ru.itis.aivar.comics.app.dto.UserDto;
import ru.itis.aivar.comics.app.dto.forms.AuthorCreationForm;

import java.util.List;

public interface AuthorService {

    AuthorDto create(AuthorCreationForm form);

    AuthorDto findAuthorByUserId(Long id);

    UserDto findUserByAuthorId(Long id);

    AuthorDto findAuthorByUser(UserDto dto);

    UserDto findUserByAuthor(AuthorDto dto);

    UserDto findUserByEmail(String email);

    AuthorDto findAuthorByUserEmail(String email);

    List<TitleDto> findTitlesByAuthor(AuthorDto dto);

    List<TitleDto> findTitlesByAuthorId(Long id);

    AuthorDto findById(Long id);

    Integer countOfReadersByAuthorId(Long id);
}
