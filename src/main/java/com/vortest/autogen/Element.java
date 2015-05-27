package com.vortest.autogen;


import com.google.common.collect.Maps;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.Map;

public class Element {
    private WebElement _element;
    private WebDriver _driver;
    private Map attributeMap;

    private String _tagName;

    public Element(WebElement ele){
        _element = ele;
        _tagName = _element.getTagName();
        _driver = Crawler.getDriver();
    }

    public void getAttributes(){
        //This little chunklet will get all the attributes for a given element
        attributeMap = (Map) ((JavascriptExecutor)_driver).executeScript("var items = {}; " +
                "for (index = 0; index < arguments[0].attributes.length;" +
                " ++index) { items[arguments[0].attributes[index].name] = arguments[0].attributes[index].value }; " +
                "return items;", _element);
    }

    public Element getParent(){
        //This will return the parent element of the current element
        return new Element(_element.findElement(By.xpath("..")));
    }
}
