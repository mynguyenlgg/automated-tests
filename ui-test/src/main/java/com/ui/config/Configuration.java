package com.ui.config;

import com.microsoft.playwright.Browser;
import com.ui.factory.pattern.BrowserConfig;
import com.ui.factory.pattern.ChromeBrowserConfig;
import com.ui.factory.pattern.FirefoxBrowserConfig;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Configuration {
    private String browser;
    private static Configuration instance = null;

    public static Configuration getInstance() {
        if (instance == null) {
            instance = new Configuration();
            instance.setBrowser(System.getProperty("BROWSER"));
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
