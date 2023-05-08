package com.example.model;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Steps {

    public WebDriver webDriver;
    public WebDriverWait webDriverWait;
    private final int timeOut = 30;
    private final int sleepTime = 150;
    protected Logger logger = Logger.getLogger(getClass());

    public Steps() {
        this.webDriver = Hooks.getWebDriver();
        this.webDriverWait = new WebDriverWait(webDriver, timeOut, sleepTime);
    }

    @Given("Go to {string} address")
    public void goToUrl(String url) {
        webDriver.get(url);
        logger.info("Go to the " + url);
    }
    @Then("{int} seconds wait")
    public void secondsWait(int second) {
        try {
            logger.info(second + " seconds wait");
            TimeUnit.SECONDS.sleep(second);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @And("Click to search")
    public void clickToSearch() {
        WebElement element = webDriver.findElement(By.xpath("//button[@class='search-widget-button button is-widget-submit'][1]"));
        element.click();
    }
}
