package ru.itis.aivar.comics.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itis.aivar.comics.app.dto.ChapterDto;
import ru.itis.aivar.comics.app.services.ChapterService;

@Controller
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    @GetMapping("/chapter/{chapter}")
    @PreAuthorize("permitAll()")
    public String chapter(@PathVariable ChapterDto chapter, Model model){
        model.addAttribute("chapter", chapter);
        model.addAttribute("pages", chapterService.findPagesByChapterId(chapter.getId()));
        if (chapter.getPreviousChapterId() != null){
            model.addAttribute("prevChapter", chapterService.findById(chapter.getPreviousChapterId()));
        }
        if (chapter.getNextChapterId() != null){
            model.addAttribute("nextChapter", chapterService.findById(chapter.getNextChapterId()));
        }
        return "chapter";
    }

}
