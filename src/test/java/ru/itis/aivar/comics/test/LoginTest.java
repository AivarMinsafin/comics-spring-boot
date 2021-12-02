package ru.itis.aivar.comics.test;

import org.junit.Test;
import org.springframework.util.SerializationUtils;
import ru.itis.aivar.comics.model.UserAccountData;

public class LoginTest extends TestBase{
    @Test
    public void testValidLogin() throws Exception {
        applicationManager.getLoginHelper().logout();
        applicationManager.getNavigationHelper().openMainPage();
        applicationManager.getNavigationHelper().openLogInPage();
        applicationManager.getLoginHelper().login(userAccountData);
        assert (applicationManager.getLoginHelper().isLoggedIn(userAccountData.getUsername()));
    }

    @Test
    public void testInvalidLogin() throws Exception {
        UserAccountData invalidData = userAccountData.clone();
        invalidData.setPassword("AAA");
        applicationManager.getLoginHelper().logout();
        applicationManager.getNavigationHelper().openMainPage();
        applicationManager.getNavigationHelper().openLogInPage();
        applicationManager.getLoginHelper().login(invalidData);
        assert (applicationManager.getNavigationHelper().onCurrentPage("http://localhost:8080/signIn?error"));
    }
}