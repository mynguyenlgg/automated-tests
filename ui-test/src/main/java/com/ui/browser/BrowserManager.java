package com.ui.browser;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Playwright;

public final class BrowserManager {

    private BrowserManager() {}

    public static Browser createBrowser(final Playwright playwright) {
        if(System.getProperty("BROWSER") == null){
            throw new NullPointerException("Browser param cannot be null");
        }
        return BrowserFactory.valueOf(System.getProperty("BROWSER").toUpperCase()).createInstance(playwright);
    }
}
