package com.vortest.autogen;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by SethUrban on 5/24/15.
 */
public class LocatorBuilder {
    private WebElement element;
    private int maxLocators = 5;
    private List<String> locators;

    //because Java doesn't support default argument values - WTF?
    public LocatorBuilder(WebElement we){
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
        locators = new ArrayList<String>();
        //now we want to find all the children of the children and stop when there are no more children
        if(noChildren()){
            //If there is no child element - assume this is an interactive element
            String id = element.getAttribute("id");
        }
    }


}
