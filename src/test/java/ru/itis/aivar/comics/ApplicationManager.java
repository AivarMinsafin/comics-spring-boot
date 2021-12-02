package ru.itis.aivar.comics;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.itis.aivar.comics.helper.LoginHelper;
import ru.itis.aivar.comics.helper.NavigationHelper;
import ru.itis.aivar.comics.helper.TitleHelper;
import ru.itis.aivar.comics.settings.Settings;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    JavascriptExecutor js;
    protected WebDriver driver;
    protected String baseUrl;
    protected StringBuffer verificationErrors = new StringBuffer();

    protected NavigationHelper navigationHelper;
    protected LoginHelper loginHelper;
    protected TitleHelper titleHelper;
    protected Settings settings;

    private static ThreadLocal<ApplicationManager> app = new ThreadLocal<>();

    protected ApplicationManager() {
        System.setProperty("webdriver.gecko.driver", "/home/aivar/Downloads/geckodriver-v0.30.0-linux64/geckodriver");
        driver = new FirefoxDriver();
        baseUrl = "https://www.google.com/";
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        js = (JavascriptExecutor) driver;

        //Settings
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        File settingsFile = new File(classLoader.getResource("settings.yml").getFile());
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());

        try {
            settings = objectMapper.readValue(settingsFile, Settings.class);
        } catch (IOException e) {
            System.out.println("["+ LocalDateTime.now() +"] - PROBLEMS WITH SETTINGS");
            System.exit(1);
        }

        navigationHelper = new NavigationHelper(this, settings.getBaseUrl());
        loginHelper = new LoginHelper(this);
        titleHelper = new TitleHelper(this);
        Runtime.getRuntime().addShutdownHook(new Thread(()->{ //Для закрытия браузера после завершения программы
            driver.quit();
        }));

    }

    public static ApplicationManager getInstance(){
        if (app.get() == null){
            ApplicationManager newInstance = new ApplicationManager();
            newInstance.getNavigationHelper().openHomePage();
            app.set(newInstance);
        }
        return app.get();
    }

    public void stop(){
        driver.quit();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }

    public LoginHelper getLoginHelper() {
        return loginHelper;
    }

    public TitleHelper getTitleHelper() {
        return titleHelper;
    }

    public Settings getSettings() {
        return settings;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        driver.quit();
    }
}
