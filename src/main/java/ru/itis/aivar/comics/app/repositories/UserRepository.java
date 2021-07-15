package ru.itis.aivar.comics.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.aivar.comics.app.models.Author;
import ru.itis.aivar.comics.app.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByConfirmCode(String confirmCode);
    Optional<User> findByAuthor(Author author);
}
