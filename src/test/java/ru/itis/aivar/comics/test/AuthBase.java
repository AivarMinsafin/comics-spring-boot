package ru.itis.aivar.comics.test;

import org.junit.Before;

public class AuthBase extends TestBase{
    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        applicationManager.getNavigationHelper().openLogInPage();
        applicationManager.getLoginHelper().login(userAccountData);
    }
}
