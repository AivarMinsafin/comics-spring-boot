package ru.itis.aivar.comics.helper;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import ru.itis.aivar.comics.ApplicationManager;
import ru.itis.aivar.comics.model.UserAccountData;

import java.time.LocalDateTime;

public class LoginHelper extends HelperBase{

    public LoginHelper(ApplicationManager applicationManager) {
        super(applicationManager);
    }

    public void login(UserAccountData user) {
        if (isLoggedIn()){
            if (isLoggedIn(user.getUsername())){
                applicationManager.getNavigationHelper().openMainPage();
                return;
            }
            logout();
        }
        driver.findElement(By.id("exampleInputEmail1")).click();
        driver.findElement(By.id("exampleInputEmail1")).clear();
        driver.findElement(By.id("exampleInputEmail1")).sendKeys(user.getEmail());
        driver.findElement(By.id("exampleInputPassword1")).click();
        driver.findElement(By.id("exampleInputPassword1")).clear();
        driver.findElement(By.id("exampleInputPassword1")).sendKeys(user.getPassword());
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

    public boolean isLoggedIn(UserAccountData user){
        return isLoggedIn(user.getUsername());
    }

    public boolean isLoggedIn(){
        try {
            driver.findElement(By.linkText("profile"));
            return true;
        } catch (NoSuchElementException e){
            System.out.println("["+ LocalDateTime.now() +"] - NO PROFILE BUTTON");
            return false;
        }
    }

    public boolean isLoggedIn(String username){
        applicationManager.getNavigationHelper().openProfilePage();
        return driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='logout'])[1]/following::h4[1]")).getText().substring("username: ".length()).equals(username);
    }

    public void logout(){
        try {
            driver.findElement(By.linkText("logout")).click();
        } catch (NoSuchElementException e){
            System.out.println("["+ LocalDateTime.now() +"] - NO LOGOUT BUTTON");
        }
    }
}
