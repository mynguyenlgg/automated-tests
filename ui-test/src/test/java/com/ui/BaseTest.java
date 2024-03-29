package com.ui;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Playwright;
import com.ui.browser.BrowserManager;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    protected Browser browser;
    protected BrowserContext browserContext;

    @BeforeClass(alwaysRun = true)
    public void beforeTestBase() {
        browser = BrowserManager.createBrowser(Playwright.create());
        browserContext = browser.newContext(new Browser.NewContextOptions().setViewportSize(null));
    }
}
