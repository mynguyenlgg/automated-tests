package com.ui.config;

import com.microsoft.playwright.Browser;
import com.ui.browser.BrowserConfig;
import com.ui.browser.ChromeBrowserConfig;
import com.ui.browser.FirefoxBrowserConfig;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Configuration {
    private static Configuration instance = null;

    @Getter
    @Setter
    private String browser;

    public static Configuration getInstance() {
        if (instance == null) {
            instance = new Configuration();
            instance.setBrowser(System.getProperty("BROWSER"));

            if (instance.getBrowser() == null) {
                throw new NullPointerException("Browser can not be null");
            }
        }
        return instance;
    }

    private Map<String, Class<? extends BrowserConfig>> factories() {
        Map<String, Class<? extends BrowserConfig>> result = new HashMap<>();
        result.put("chrome", ChromeBrowserConfig.class);
        result.put("firefox", FirefoxBrowserConfig.class);
        return result;
    }

    private BrowserConfig getBrowser(String browser) {
        Class<? extends BrowserConfig> factoryClass = this.factories().getOrDefault(browser, BrowserConfig.class);
        try {
            return factoryClass.getConstructor().newInstance();
        } catch (Exception ex) {
            throw new RuntimeException("Failed to initialize " + factoryClass.getName(), ex);
        }
    }

    public Browser createBrowser() {
        BrowserConfig factory = getBrowser(browser);
        return factory.getBrowserConfig();
    }
}
