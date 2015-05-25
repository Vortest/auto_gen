package com.vortest.autogen;

import org.openqa.selenium.*;

import java.util.Iterator;
import java.util.List;

public class PageParser {
    private WebDriver driver;
    private List<WebElement> elements;

    public PageParser(WebDriver d){
        driver = d;
        getSource();
    }

    public void getSource(){
        elements = driver.findElements(By.cssSelector("*"));

        for(Iterator<WebElement> ele = elements.iterator(); ele.hasNext();){
            String item = ele.next().toString();
            System.out.print(item);
        }
    }



}
