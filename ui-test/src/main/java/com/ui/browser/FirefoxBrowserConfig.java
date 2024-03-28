package com.ui.browser;

import com.microsoft.playwright.Browser;

public class FirefoxBrowserConfig extends AbstractBrowserManager implements BrowserConfig {
    @Override
    public Browser getBrowserConfig() {
        return this.playwright.firefox().launch(getLaunchOptions());
    }
}
