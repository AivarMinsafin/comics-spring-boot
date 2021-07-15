package ru.itis.aivar.comics.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itis.aivar.comics.app.models.Chapter;
import ru.itis.aivar.comics.app.models.Title;

import java.util.List;
import java.util.Optional;

public interface ChapterRepository extends JpaRepository<Chapter, Long> {

    @Query(value = "SELECT * FROM Chapter c WHERE c.title_id=:id ORDER BY c.chapter_number DESC LIMIT 1", nativeQuery = true)
    Optional<Chapter> findFirstByOrderByChapterNumberDesc(@Param("id") Long titleId);

    Optional<Chapter> findByChapterNumberAndTitle_Id(Integer chapterNumber, Long titleId);

    List<Chapter> findAllByTitle_Id(Long id);
    List<Chapter> findAllByTitle(Title title);

    List<Chapter> findAllByTitle_IdOrderByChapterNumberAsc(Long id);

}
