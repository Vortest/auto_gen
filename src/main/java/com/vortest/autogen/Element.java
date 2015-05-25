package com.vortest.autogen;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Element {
    private WebElement _element;
    private WebDriver _driver;

    public Element(WebElement ele, WebDriver driver){
        _element = ele;
        _driver = driver;
    }

    public void AllAttributes(){
        JavascriptExecutor jsexec = (JavascriptExecutor)_driver;
    }
}
