package com.vortest.autogen;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebDriver;

import org.junit.*;
import org.openqa.selenium.*;
import java.io.IOException;
import org.openqa.selenium.WebElement;
import static junit.framework.Assert.assertEquals;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static junit.framework.Assert.assertTrue;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Crawler {
    private static WebDriver driver;
    private String baseUrl;

    public Crawler(String startURL){
        baseUrl = startURL;
        autogen_logging.log("Opening browser");
        openBrowser();
    }

    public void openBrowser() {
        driver = new FirefoxDriver();
        driver.get(baseUrl);

        //need to check that the page is done loading
        PageParser parse = new PageParser(driver);
    }

    public static WebDriver getDriver(){
        return driver;
    }


}
