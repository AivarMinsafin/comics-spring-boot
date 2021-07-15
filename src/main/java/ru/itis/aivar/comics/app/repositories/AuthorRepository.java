package ru.itis.aivar.comics.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itis.aivar.comics.app.models.Author;
import ru.itis.aivar.comics.app.models.Title;
import ru.itis.aivar.comics.app.models.User;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    Optional<Author> findByUser(User user);
    Optional<Author> findByUser_Id(Long id);
    Optional<Author> findByTitlesContaining(Title title);

    @Query("select count(u) from User u join Title t on u member of t.users and t.author=:author")
    Integer countOfReaders(@Param("author") Author author);
}
