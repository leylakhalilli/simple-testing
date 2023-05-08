package com.example.model;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Hooks {
    protected static WebDriver driver;
    protected static Actions actions;
    public Logger logger = Logger.getLogger(getClass());

    String browserName = "chrome";
    String selectPlatform = "win";
    ChromeOptions chromeOptions;
    DesiredCapabilities capabilities;

    @Before
    public void beforeTest() {
        logger.info("Start");
        try {
            if (StringUtils.isEmpty(System.getProperty("key"))) {
                logger.info("Local device " + selectPlatform + " env " + browserName + " browser test executed");
                if ("win".equalsIgnoreCase(selectPlatform)) {
                    if ("chrome".equalsIgnoreCase(browserName)) {
                        driver = new ChromeDriver(chromeOptions());
                        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ChromeOptions chromeOptions() {

        chromeOptions = new ChromeOptions();
        capabilities = DesiredCapabilities.chrome();
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        chromeOptions.setExperimentalOption("prefs", prefs);
        chromeOptions.addArguments("disable-notifications");
        chromeOptions.addArguments("start-fullscreen");
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        chromeOptions.merge(capabilities);
        return chromeOptions;
    }

    @After
    public void afterTest() {
        driver.quit();
    }


    public static WebDriver getWebDriver() {
        return driver;
    }
}
