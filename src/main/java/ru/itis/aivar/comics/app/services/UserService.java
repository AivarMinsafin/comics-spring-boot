package ru.itis.aivar.comics.app.services;

import ru.itis.aivar.comics.app.dto.TitleDto;
import ru.itis.aivar.comics.app.dto.UserDto;
import ru.itis.aivar.comics.app.dto.forms.UserEditForm;

import java.util.List;

public interface UserService {

    List<UserDto> getAllUsers();

    UserDto findById(Long id);

    UserDto update(UserEditForm userEditForm);

    UserDto findByEmail(String email);

    List<TitleDto> findUserTitles(String name);

    void addTitleToList(Long id, String name);
}
