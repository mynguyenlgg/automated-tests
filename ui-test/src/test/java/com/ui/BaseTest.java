package com.ui;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.ui.config.Configuration;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    protected Browser browser;
    protected BrowserContext browserContext;

    @BeforeClass(alwaysRun = true)
    public void beforeTestBase() {
        browser = Configuration.getInstance().createBrowser();
        browserContext = browser.newContext(new Browser.NewContextOptions()
                .setViewportSize(null));
    }
}
