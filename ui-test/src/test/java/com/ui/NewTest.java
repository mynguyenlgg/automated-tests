package com.ui;

import com.microsoft.playwright.Page;
import org.testng.annotations.Test;

public class NewTest extends BaseTest {
    @Test
    public void testDoubleClick() {
        Page page = browserContext.newPage();
        page.navigate("http://playwright.dev");
    }
}
