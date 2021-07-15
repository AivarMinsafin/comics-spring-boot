package ru.itis.aivar.comics.app.utils.images;

import org.springframework.stereotype.Component;
import ru.itis.aivar.comics.app.models.Chapter;
import ru.itis.aivar.comics.app.models.Title;

import java.nio.file.Paths;

@Component
public class PathCreatorImpl implements PathCreator {
    
    @Override
    public String createTitleImagePath(Title title, String imageName) {
        return String.valueOf(Paths.get(String.valueOf(title.getId()), imageName));
    }

    @Override
    public String createChapterPageImagePath(Chapter chapter, String imageName) {
        return String.valueOf(Paths.get(String.valueOf(chapter.getTitle().getId()), String.valueOf(chapter.getId()), imageName));
    }

    @Override
    public String createTitlePath(Title title) {
        return String.valueOf(Paths.get(String.valueOf(title.getId())));
    }

    @Override
    public String createChapterPath(Chapter chapter) {
        return String.valueOf(Paths.get(String.valueOf(chapter.getTitle().getId()), String.valueOf(chapter.getId())));
    }
}
