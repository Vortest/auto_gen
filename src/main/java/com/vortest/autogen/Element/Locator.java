package com.vortest.autogen.Element;

import com.vortest.autogen.Element.ByOption;
import org.openqa.selenium.By;

public class Locator {
    public Integer LocatorID;
    public ByOption By;
    public String param;

    public Locator(){}
    public Locator(ByOption By, String param){
        this.By = By;
        this.param = param;
    }

    public String toString(){
        return ToBy().toString();
    }


    public By ToBy(){
        switch(By){
            case ClassName:
                return org.openqa.selenium.By.className(param);
            case XPath:
                return org.openqa.selenium.By.xpath(param);
            case Id:
                return org.openqa.selenium.By.id(param);
            case Name:
                return org.openqa.selenium.By.name(param);
            case LinkText:
                return org.openqa.selenium.By.linkText(param);
            case PartialLinkText:
                return org.openqa.selenium.By.partialLinkText(param);
            case CssSelector:
                return org.openqa.selenium.By.cssSelector(param);
            default:
                return org.openqa.selenium.By.cssSelector(param);
        }
    }
}
