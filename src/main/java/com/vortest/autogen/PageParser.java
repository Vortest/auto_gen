package com.vortest.autogen;

import com.vortest.autogen.Element.Element;
import org.openqa.selenium.*;

import java.util.ArrayList;
import java.util.List;

public class PageParser {
    private WebDriver driver;
    private List<WebElement> allelements;
    //private List<WebElement> childElements;
    private List<Element> childElements;
    private LocatorBuilder locatorBuilder;

    public PageParser(){
        getAllElements();
        getChildelements();
    }

    public void getAllElements(){
        allelements = Crawler.getDriver().findElements(By.cssSelector("body *"));
    }

    public void getChildelements(){
        childElements = new ArrayList<Element>();

        autogen_logging.log(String.format("Found %s TOTAL elements on the page", allelements.size()));

        //We assume that every element without a child is an interactive element
        for(int i = 0; i < allelements.size(); i++){
            //check to see if the element has a child
            if(allelements.get(i).findElements(By.cssSelector("*")).isEmpty()){
                Element childele = new Element(allelements.get(i));
                childElements.add(childele);
            }
        }

        autogen_logging.log(String.format("Found %s elements with No children", childElements.size()));

        //TODO here's where i'm tinkering
        getSource2();
    }




    public void getSource2(){
        //TODO This is the beginings of the page object storage
        //Assuming that all elements with no children are interactive - meaning they change the state of the application
        //So if the child element has an ID tag we're pretty much done - if the child element does not have an ID tag we would need to trace up the linage a bit
        for(int i = 0; i < childElements.size(); i++){
            LocatorBuilder locbuild = new LocatorBuilder(childElements.get(i));
        }
        for(int i = 0; i < childElements.size(); i++){
            System.out.println("***ELEMENT: " + childElements.get(i).toString());
            if(childElements.get(i).hasText()){
                System.out.println("Text: " + childElements.get(i).get_text());
            }
            if(childElements.get(i).Attributes != null){
                System.out.println("Attributes: " + childElements.get(i).Attributes.toString());
            }
            for(int x = 0; x < childElements.get(i).get_locators().size(); x++){
                System.out.print("Locators: " + childElements.get(i).get_locators().get(x).toString());
            }
        }
    }


}
