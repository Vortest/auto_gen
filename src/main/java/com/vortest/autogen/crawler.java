package com.vortest.autogen;

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import static junit.framework.Assert.assertEquals;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static junit.framework.Assert.assertTrue;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Crawler {
    private WebDriver driver;
    private String baseUrl;

    public Crawler(String startURL){

        baseUrl = startURL;
        openBrowser();
    }

    public void openBrowser() {
        driver = new FirefoxDriver();
        driver.get(baseUrl);
        PageParser parse = new PageParser(driver);
    }


}
