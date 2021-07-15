package ru.itis.aivar.comics.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.aivar.comics.app.dto.PageDto;
import ru.itis.aivar.comics.app.models.Page;

import java.util.List;

public interface PageRepository extends JpaRepository<Page, Long> {

    List<Page> findAllByChapter_Id(Long id);

}
