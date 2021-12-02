package ru.itis.aivar.comics.test;

import org.junit.Before;
import ru.itis.aivar.comics.ApplicationManager;
import ru.itis.aivar.comics.model.AddTitleData;
import ru.itis.aivar.comics.model.UserAccountData;

import java.util.UUID;

public class TestBase {
    protected ApplicationManager applicationManager;

    protected UserAccountData userAccountData;

    protected AddTitleData addTitleData;
    @Before
    public void setUp() throws Exception {
        applicationManager = ApplicationManager.getInstance();
        setUpTestData();
    }

    protected void setUpTestData() {
        userAccountData = UserAccountData.builder()
                .email(applicationManager.getSettings().getEmail())
                .username(applicationManager.getSettings().getUsername())
                .password(applicationManager.getSettings().getPassword())
                .build();
        addTitleData = AddTitleData.builder()
                .name(UUID.randomUUID().toString())
                .description(UUID.randomUUID().toString())
                .image(applicationManager.getSettings().getTitleBlankImage())
                .build();
    }

}
