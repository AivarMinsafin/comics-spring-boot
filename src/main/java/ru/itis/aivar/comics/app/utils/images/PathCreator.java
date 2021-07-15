package ru.itis.aivar.comics.app.utils.images;

import ru.itis.aivar.comics.app.models.Chapter;
import ru.itis.aivar.comics.app.models.Title;

public interface PathCreator {

    String createTitleImagePath(Title title, String imageName);
    String createChapterPageImagePath(Chapter chapter, String imageName);
    String createTitlePath(Title title);
    String createChapterPath(Chapter chapter);

}
