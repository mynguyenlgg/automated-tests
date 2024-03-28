package com.ui.browser;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;

public class ChromeBrowserConfig extends AbstractBrowserManager implements BrowserConfig {
    public Browser getBrowserConfig() {
        return this.playwright.chromium().launch(getLaunchOptions());
    }

    public BrowserType.LaunchOptions getLaunchOptions() {
        return super.getLaunchOptions().setChannel("chrome");
    }
}
