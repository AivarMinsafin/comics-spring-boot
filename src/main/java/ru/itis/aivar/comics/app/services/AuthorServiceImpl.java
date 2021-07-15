package ru.itis.aivar.comics.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.aivar.comics.app.dto.AuthorDto;
import ru.itis.aivar.comics.app.dto.TitleDto;
import ru.itis.aivar.comics.app.dto.UserDto;
import ru.itis.aivar.comics.app.dto.forms.AuthorCreationForm;
import ru.itis.aivar.comics.app.exceptions.NotFoundException;
import ru.itis.aivar.comics.app.models.Author;
import ru.itis.aivar.comics.app.models.User;
import ru.itis.aivar.comics.app.repositories.AuthorRepository;
import ru.itis.aivar.comics.app.repositories.RoleRepository;
import ru.itis.aivar.comics.app.repositories.TitleRepository;
import ru.itis.aivar.comics.app.repositories.UserRepository;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TitleRepository titleRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public AuthorDto create(AuthorCreationForm form) {
        User user = userRepository.findById(form.getUserId()).orElseThrow(NotFoundException::new);
        user.getRoles().add(roleRepository.findRoleByName("ROLE_AUTHOR"));
        Author author = Author.builder()
                .firstName(form.getFirstname())
                .lastName(form.getLastname())
                .phoneNumber(form.getPhoneNumber())
                .user(user)
                .build();
        return AuthorDto.from(authorRepository.save(author));
    }

    @Override
    public AuthorDto findAuthorByUserId(Long id) {
        return AuthorDto.from(authorRepository.findByUser_Id(id).orElseThrow(NotFoundException::new));
    }

    @Override
    public UserDto findUserByAuthorId(Long id) {
        return UserDto.from(userRepository.findByAuthor(authorRepository.findById(id).orElseThrow(NotFoundException::new)).orElseThrow(NotFoundException::new));
    }

    @Override
    public AuthorDto findAuthorByUser(UserDto dto) {
        return findAuthorByUserId(dto.getId());
    }

    @Override
    public UserDto findUserByAuthor(AuthorDto dto) {
        return findUserByAuthorId(dto.getId());
    }

    @Override
    public UserDto findUserByEmail(String email) {
        return UserDto.from(userRepository.findByEmail(email).orElseThrow(NotFoundException::new));
    }

    @Override
    public AuthorDto findAuthorByUserEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(NotFoundException::new);
        return AuthorDto.from(authorRepository.findByUser(user).orElseThrow(NotFoundException::new));
    }

    @Override
    public List<TitleDto> findTitlesByAuthor(AuthorDto dto) {
        return findTitlesByAuthorId(dto.getId());
    }

    @Override
    public List<TitleDto> findTitlesByAuthorId(Long id) {
        Author author = authorRepository.findById(id).orElseThrow(NotFoundException::new);
        return TitleDto.from(titleRepository.findAllByAuthor(author));
    }

    @Override
    public AuthorDto findById(Long id) {
        return AuthorDto.from(authorRepository.findById(id).orElseThrow(NotFoundException::new));
    }

    @Override
    public Integer countOfReadersByAuthorId(Long id) {
        Author author = authorRepository.findById(id).orElseThrow(NotFoundException::new);
        return authorRepository.countOfReaders(author);
    }
}
