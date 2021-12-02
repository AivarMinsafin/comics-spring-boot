package ru.itis.aivar.comics.helper;

import org.openqa.selenium.By;
import ru.itis.aivar.comics.ApplicationManager;

public class NavigationHelper extends HelperBase{
    protected String baseUrl;

    public NavigationHelper(ApplicationManager applicationManager, String baseUrl) {
        super(applicationManager);
        this.baseUrl = baseUrl;
    }

    public void openLogInPage() {
        driver.get("http://localhost:8080/signIn");
    }

    public void openHomePage(){
        driver.get(baseUrl);
    }

    public void goToCreateNewTitle() {
        driver.findElement(By.linkText("Create new Title")).click();
    }

    public void goToTitleManager() {
        driver.findElement(By.linkText("Title manager")).click();
    }

    public void openMainPage() {
        driver.get("http://localhost:8080/");
    }

    public void openProfilePage() {
        driver.get("http://localhost:8080/profile");
    }

    public boolean onCurrentPage(String pageToCompare){
        return driver.getCurrentUrl().equals(pageToCompare);
    }
}
