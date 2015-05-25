package com.vortest.autogen;

import org.openqa.selenium.*;

import java.util.ArrayList;
import java.util.List;

public class PageParser {
    private WebDriver driver;
    private List<WebElement> elements;
    private List<WebElement> childElements;
    private LocatorBuilder locatorBuilder;

    public PageParser(WebDriver d){
        driver = d;
        getSource();
    }

    public void getSource(){
        //This is going to find all the elements on the page
        elements = driver.findElements(By.cssSelector("*"));
        childElements = new ArrayList<WebElement>();

        //TODO Change this to a log function
        System.out.print(elements.size() + "\n");

        //We assume that every element without a child is an interactive element
        for(int i = 0; i < elements.size(); i++){
            //check to see if the element has a child
            if(elements.get(i).findElements(By.cssSelector("*")).isEmpty()){
                childElements.add(elements.get(i));
            }
        }
        //TODO change this to a log function
        System.out.print(childElements.size() + "\n");

        for(int i = 0; i < childElements.size(); i++){
/*            System.out.print(String.format("Tag Name: %s",childElements.get(i).getTagName() + "\n"));
            System.out.print(String.format("Class Name: %s", childElements.get(i).getAttribute("class") + "\n"));
            System.out.print(childElements.get(i).getAttribute("outerHTML") + "\n");
            System.out.print(childElements.get(i).getAttribute("innerHTML") + "\n");*/
            List<String> elementLocators = new ArrayList<String>();
            elementLocators = new LocatorBuilder(childElements.get(i)).getGoodLocators();
            for(int x = 0; x < elementLocators.size(); x++){
                System.out.println(elementLocators.get(x));
            }
        }
    }



}
