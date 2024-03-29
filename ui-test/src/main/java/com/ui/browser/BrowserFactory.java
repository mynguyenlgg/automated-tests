package com.ui.browser;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;

import java.util.List;

public enum BrowserFactory {
    CHROME {
        @Override
        public Browser createInstance(final Playwright playwright) {
            return playwright.chromium().launch(options());
        }
    },
    FIREFOX {
        @Override
        public Browser createInstance(final Playwright playwright) {
            return playwright.firefox().launch(options());
        }
    };

    protected BrowserType.LaunchOptions options() {
        return new BrowserType.LaunchOptions()
                .setHeadless(false)
                .setArgs(List.of("--start-maximized"));
    }

    public abstract Browser createInstance(final Playwright playwright);
}
