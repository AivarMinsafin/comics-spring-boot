package ru.itis.aivar.comics.helper;

import com.thoughtworks.xstream.XStream;
import org.openqa.selenium.By;
import ru.itis.aivar.comics.ApplicationManager;
import ru.itis.aivar.comics.model.AddTitleData;
import ru.itis.aivar.comics.model.TitleData;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class TitleHelper extends HelperBase{

    public TitleHelper(ApplicationManager applicationManager) {
        super(applicationManager);
    }

    public void addTitle(AddTitleData addTitleData) {
        driver.findElement(By.id("name")).click();
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys(addTitleData.getName());
        driver.findElement(By.id("description")).click();
        driver.findElement(By.id("description")).clear();
        driver.findElement(By.id("description")).sendKeys(addTitleData.getDescription());
        driver.findElement(By.id("image")).sendKeys(addTitleData.getImage());
        driver.findElement(By.xpath("//input[@value='Submit']")).click();
    }

    public void deleteTitle(Integer pos){
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Chapters'])["+pos+"]/following::a[1]")).click();
    }

    public boolean isTitleDeleted(Integer count){
        return count - 1 == getTitlesCount();
    }

    public boolean isTitleAdded(Integer count){
        return count + 1 == getTitlesCount();
    }

    public int getTitlesCount(){
        return driver.findElements(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Chapters'])")).size();
    }

    public AddTitleData getTitleDataFromXml(String path) throws FileNotFoundException {
        File xml = new File(path);
        FileReader fileReader = new FileReader(xml);

        XStream xStream = new XStream();
        TitleData titleData = ((List<TitleData>) xStream.fromXML(fileReader)).get(0);
        return AddTitleData.builder()
                .name(titleData.getName())
                .description(titleData.getDescription())
                .image("/home/aivar/Pictures/title.jpg")
                .build();
    }

}
