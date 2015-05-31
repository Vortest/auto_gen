package com.vortest.autogen;

import com.vortest.autogen.Element.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by SethUrban on 5/31/15.
 */
public class Driver extends FirefoxDriver {

    public List<Element> FindElements(By by){
        List<Element> foundElements = new ArrayList<Element>();
        List<WebElement> elements = super.findElements(by);
        for(Iterator<WebElement> i = elements.iterator(); i.hasNext();){
            foundElements.add(new Element(i.next()));
        }
        return foundElements;
    }

    public Element FindElement(By by){
        return new Element(super.findElement(by));
    }
}
