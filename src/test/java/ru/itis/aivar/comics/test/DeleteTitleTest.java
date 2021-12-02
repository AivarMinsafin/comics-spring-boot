package ru.itis.aivar.comics.test;

import org.junit.Test;

public class DeleteTitleTest extends AuthBase {

    @Test
    public void testDeleteTitle() {
        applicationManager.getNavigationHelper().openProfilePage();
        applicationManager.getNavigationHelper().goToTitleManager();
        int count = applicationManager.getTitleHelper().getTitlesCount();
        applicationManager.getTitleHelper().deleteTitle(1);
        assert (applicationManager.getTitleHelper().isTitleDeleted(count));
    }

}
