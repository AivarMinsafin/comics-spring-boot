package ru.itis.aivar.comics.test;

import org.junit.Test;

public class AddTitleTest extends AuthBase {

    @Test
    public void testAddTitle() throws Exception {
        applicationManager.getNavigationHelper().openProfilePage();
        applicationManager.getNavigationHelper().goToTitleManager();
        int count = applicationManager.getTitleHelper().getTitlesCount();
        applicationManager.getNavigationHelper().goToCreateNewTitle();
        applicationManager.getTitleHelper().addTitle(
                applicationManager.getTitleHelper().getTitleDataFromXml(
                        "/home/aivar/Documents/dataForTest.xml"
                )
        );
        assert (applicationManager.getTitleHelper().isTitleAdded(count));
    }
}
