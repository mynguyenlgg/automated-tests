package com.ui.browser;

import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;

public abstract class AbstractBrowserManager {
    protected final Playwright playwright = Playwright.create();

    protected BrowserType.LaunchOptions getLaunchOptions() {
        return new BrowserType.LaunchOptions()
                .setHeadless(false);
    }
}
