package com.ui.browser;

import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBrowserManager {
    protected final Playwright playwright = Playwright.create();

    protected BrowserType.LaunchOptions getLaunchOptions() {
        List<String> args = new ArrayList<>();
        args.add("--start-maximized");
        return new BrowserType.LaunchOptions()
                .setHeadless(false)
                .setArgs(args);
    }
}
