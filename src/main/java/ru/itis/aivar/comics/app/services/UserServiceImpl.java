package ru.itis.aivar.comics.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.aivar.comics.app.dto.TitleDto;
import ru.itis.aivar.comics.app.dto.UserDto;
import ru.itis.aivar.comics.app.dto.forms.UserEditForm;
import ru.itis.aivar.comics.app.exceptions.NotFoundException;
import ru.itis.aivar.comics.app.models.User;
import ru.itis.aivar.comics.app.repositories.AuthorRepository;
import ru.itis.aivar.comics.app.repositories.TitleRepository;
import ru.itis.aivar.comics.app.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

import static ru.itis.aivar.comics.app.dto.UserDto.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private TitleRepository titleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<UserDto> getAllUsers() {
        return userDtoWithIsAuthorField(userRepository.findAll());
    }

    @Override
    public UserDto findById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null){
            throw new NotFoundException("User with id: "+id+" not found");
        }
        return userDtoWithIsAuthorField(user);
    }

    @Override
    public UserDto update(UserEditForm userEditForm) {
        User user = userRepository.findById(userEditForm.getId()).orElseThrow(NotFoundException::new);

        if (passwordEncoder.matches(userEditForm.getPasswordForConfirmAction(), user.getHashPassword())){
            user.setEmail(userEditForm.getNewEmail());
            user.setUsername(userEditForm.getNewUsername());
            userRepository.save(user);
        } else {
            return null; //TODO check in controller and add error message
        }

        return userDtoWithIsAuthorField(user);
    }

    @Override
    public UserDto findByEmail(String email) {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null){
            throw new NotFoundException("User with email: "+email+" not found");
        }
        return userDtoWithIsAuthorField(user);
    }

    @Override
    public List<TitleDto> findUserTitles(String name) {
        return TitleDto.from(titleRepository.findAllByUsersContaining(userRepository.findByEmail(name).orElseThrow(NotFoundException::new)));
    }

    @Override
    public void addTitleToList(Long id, String name) {
        User user = userRepository.findByEmail(name).orElseThrow(NotFoundException::new);
        user.getTitles().add(titleRepository.findById(id).orElseThrow(NotFoundException::new));
        userRepository.save(user);
    }

    private UserDto userDtoWithIsAuthorField(User user){
        UserDto dto = from(user);
        boolean isAuthor = authorRepository.findByUser(user).isPresent();
        dto.setAuthor(isAuthor);
        return dto;
    }

    private List<UserDto> userDtoWithIsAuthorField(List<User> users){
        List<UserDto> userDtos = new ArrayList<>();

        users.forEach(user -> {
            UserDto dto = userDtoWithIsAuthorField(user);
            userDtos.add(dto);
        });

        return userDtos;
    }
}
