package com.vortest.autogen;

import org.openqa.selenium.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PageParser {
    private WebDriver driver;
    private List<WebElement> elements;
    private List<WebElement> childElements;

    public PageParser(WebDriver d){
        driver = d;
        getSource();
    }

    public void getSource(){

        elements = driver.findElements(By.cssSelector("*"));
        childElements = new ArrayList<WebElement>();

        System.out.print(elements.size() + "\n");
        for(int i = 0; i < elements.size(); i++){
            if(elements.get(i).findElements(By.cssSelector("*")).isEmpty()){
                childElements.add(elements.get(i));
            }
        }
        System.out.print(childElements.size() + "\n");
        for(int i = 0; i < childElements.size(); i++){
            System.out.print(String.format("Tag Name: %s",childElements.get(i).getTagName() + "\n"));
            System.out.print(String.format("Class Name: %s", childElements.get(i).getAttribute("class") + "\n"));
        }
    }



}
