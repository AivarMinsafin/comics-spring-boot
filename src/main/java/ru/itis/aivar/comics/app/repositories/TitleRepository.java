package ru.itis.aivar.comics.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itis.aivar.comics.app.models.Author;
import ru.itis.aivar.comics.app.models.Chapter;
import ru.itis.aivar.comics.app.models.Title;
import ru.itis.aivar.comics.app.models.User;

import java.util.List;
import java.util.Optional;

public interface TitleRepository extends JpaRepository<Title, Long> {

    List<Title> findAllByAuthor(Author author);
    List<Title> findAllByUsersContaining(User user);
    Optional<Title> findByChaptersContaining(Chapter chapter);

    @Query("select count(u) from User u inner join Title t on u member of t.users and t.id = :id")
    Integer titleReadersCount(@Param("id") Long id);
}
