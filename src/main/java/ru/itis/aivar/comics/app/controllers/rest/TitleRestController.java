package ru.itis.aivar.comics.app.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.aivar.comics.app.dto.TitleDto;
import ru.itis.aivar.comics.app.dto.forms.TitleCreationForm;
import ru.itis.aivar.comics.app.dto.forms.TitleEditForm;
import ru.itis.aivar.comics.app.services.TitleService;

import javax.annotation.security.PermitAll;
import java.util.List;

@RestController
public class TitleRestController {

    @Autowired
    private TitleService titleService;

    @GetMapping("/api/titles")
    @PermitAll
    public ResponseEntity<List<TitleDto>> getTitles(){
        return ResponseEntity.ok(titleService.getAllTitles());
    }

    @GetMapping("/api/title/{id}")
    @PermitAll
    public ResponseEntity<TitleDto> getTitle(@PathVariable Long id){
        return ResponseEntity.ok(titleService.getTitleById(id));
    }

    @PostMapping("/api/title")
    @PermitAll
    public ResponseEntity<TitleDto> postTitle(@RequestBody TitleCreationForm form){
        return ResponseEntity.ok(titleService.createTitle(form));
    }

    @DeleteMapping("/api/title/{id}")
    @PermitAll
    public ResponseEntity<?> deleteTitle(@PathVariable Long id){
        titleService.deleteTitle(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/api/title")
    @PermitAll
    public ResponseEntity<TitleDto> putTitle(@RequestBody TitleEditForm form){
        return ResponseEntity.ok(titleService.updateTitle(form));
    }

    @GetMapping("/api/titles/{count}/{page}")
    public ResponseEntity<List<TitleDto>> getTitlesPageable(@PathVariable Integer count, @PathVariable Integer page){
        return ResponseEntity.ok(titleService.getTitlesPageable(count, page));
    }
}
