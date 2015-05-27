package com.vortest.autogen;

import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.util.ArrayList;
import java.util.List;


public class LocatorBuilder {
    private WebElement element;
    private int maxLocators = 5;
    private List<String> goodLocators;
    private List<String> badLocators;
    private List<String> duplicateLocators;

    //because Java doesn't support default argument values - WTF?
    public LocatorBuilder(WebElement we){
        goodLocators = new ArrayList<String>();
        badLocators = new ArrayList<String>();
        duplicateLocators = new ArrayList<String>();
        element = we;
        buildLocators();
    }

    private boolean noChildren(){
        boolean hasChild = true;
        if(!element.findElements(By.cssSelector("*")).isEmpty()){
            hasChild = false;
        }
        return hasChild;
    }

    private void buildLocators() {
        //This could scan for all kinds of elements <hr>, <p>, <h2> etc
        String elementId = element.getAttribute("id");
        String elementClass = element.getAttribute("class");
        String elementText = element.getText();
        String elementTag = element.getTagName();

        //now we want to find all the children of the children and stop when there are no more children
        if(noChildren()){
            //If there is no child element - assume this is an interactive element
            if(!elementId.equals("")){
                //Test the Locator by building with ID
                TestLocator(new Locator(ByOption.Id, element.getAttribute("id")));
            }
            if(!elementClass.equals("")){
                //Test Locator building with class name.
                TestLocator(new Locator(ByOption.ClassName, element.getAttribute("class")));
            }
            if(!elementText.equals("")){
                //This is the section that we could add all kinds of modules to
                if(elementTag.equals("a")){
                    //Do some link text here                    
                    TestLocator(new Locator(ByOption.LinkText, element.getText()));
                }
                if(elementTag.equals("select")){
                    //This is a standard drop down menu
                    TestLocator(new Locator(ByOption.LinkText, element.getText()));
                }
                else{
                    //Do some contains stuff here
                    TestLocator(new Locator(ByOption.XPath, String.format("//%s[contains(test(), '%s')]", element.getTagName(), element.getText())));
                }
            }
        }
    }

    private void TestLocator(Locator locator){
        //First we try and find the element - then make sure there's only one element found with that locator.

        try {
            List<WebElement> foundElements = Crawler.getDriver().findElements(locator.ToBy());
            if(foundElements.size() == 1){
                //Found a unique locator!
                goodLocators.add(locator.ToBy().toString());
            }
            if(foundElements.size() == 0){
                //Found NO goodLocators you suck!
                badLocators.add(locator.ToBy().toString());
            }
            if(foundElements.size() > 1){
                //Found to many locators
                duplicateLocators.add(locator.ToBy().toString());
            }
        }
        catch (Exception e){
            //The locator was not valid - TODO log this
        }
    }

    public List<String> getGoodLocators(){
        return goodLocators;
    }

}
