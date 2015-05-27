package com.vortest.autogen;

import org.openqa.selenium.*;

import java.util.ArrayList;
import java.util.List;

public class PageParser {
    private WebDriver driver;
    private List<WebElement> elements;
    //private List<WebElement> childElements;
    private List<Element> childElements;
    private LocatorBuilder locatorBuilder;

    public PageParser(WebDriver d){
        driver = d;
        getChildelements();
    }

    public void getChildelements(){
        elements = driver.findElements(By.cssSelector("*"));
        childElements = new ArrayList<Element>();

        autogen_logging.log(String.format("Found %s TOTAL elements on the page", elements.size() + "\n"));

        //We assume that every element without a child is an interactive element
        for(int i = 0; i < elements.size(); i++){
            //check to see if the element has a child
            if(elements.get(i).findElements(By.cssSelector("*")).isEmpty()){
                Element childele = new Element(elements.get(i));
                childElements.add(childele);
            }
        }

        autogen_logging.log(String.format("Found %s elements with No children", childElements.size() + "\n"));

        //TODO here's where i'm tinkering
        getSource2();
    }

    public void getSource(){
        //This is going to find all the elements on the page

        for(int i = 0; i < childElements.size(); i++){
/*          System.out.print(String.format("Tag Name: %s",childElements.get(i).getTagName() + "\n"));
            System.out.print(String.format("Class Name: %s", childElements.get(i).getAttribute("class") + "\n"));
            System.out.print(childElements.get(i).getAttribute("outerHTML") + "\n");
            System.out.print(childElements.get(i).getAttribute("innerHTML") + "\n");

            List<String> elementLocators = new LocatorBuilder(childElements.get(i)).getGoodLocators();
            for(int x = 0; x < elementLocators.size(); x++){
                System.out.println(elementLocators.get(x));
            }*/
        }
    }

    public void getSource2(){
        //Assuming that all elements with no children are interactive - meaning they change the state of the application
        //So if the child element has an ID tag we're pretty much done - if the child element does not have an ID tag we would need to trace up the linage a bit
        for(int i = 0; i < childElements.size(); i++){
            LocatorBuilder locbuild = new LocatorBuilder(childElements.get(i));
        }

    }


}
