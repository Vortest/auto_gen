package com.vortest.autogen;

import com.vortest.autogen.Element.Element;
import org.openqa.selenium.*;

import java.util.ArrayList;
import java.util.List;

public class PageParser {
    private WebDriver driver;
    private List<WebElement> _allWebElements;
    private List<Element> _parentElements;
    //private List<WebElement> _childElements;
    private List<Element> _childElements;
    private LocatorBuilder locatorBuilder;

    private List<String> _containerTags;
    private List<List<WebElement>> _containersAvailable;

    public PageParser(){
        initContainers();
        getHead();
        getAllElements();
        getChildelements();
    }

    public void initContainers(){
        _containerTags = new ArrayList<String>();
        _containerTags.add("form");
        _containerTags.add("table");
        _containerTags.add("p");
        _containerTags.add("a");
    }

    public void buildElementTree(){

    }

    public void getAllElements(){
        _parentElements = new ArrayList<Element>();
        _allWebElements = new ArrayList<WebElement>();
        _containersAvailable = new ArrayList<List<WebElement>>();
        List<Element> form = Crawler.getDriver().FindElements(By.cssSelector("form > *"));
        List<WebElement> form2 = Crawler.getDriver().findElements(By.cssSelector("form > *"));
        List<WebElement> tables = Crawler.getDriver().findElements(By.xpath("//table"));
        List<WebElement> ptags = Crawler.getDriver().findElements(By.xpath("//p"));
        List<WebElement> links = Crawler.getDriver().findElements(By.cssSelector("body a"));


        _allWebElements = Crawler.getDriver().findElements(By.cssSelector("body *"));
        for(int x = 0; x < _allWebElements.size(); x++){
            Element anElement = new Element(_allWebElements.get(x));
            _parentElements.add(anElement);
        }
    }

    public void getHead(){
        List<WebElement> headerElements = Crawler.getDriver().findElements(By.cssSelector("head *"));
        System.out.print(headerElements);
    }

    public void getChildelements(){
        _childElements = new ArrayList<Element>();
        List<Element> containers = new ArrayList<Element>();

        autogen_logging.log(String.format("Found %s TOTAL elements on the page", _parentElements.size()));
        for(int i = 0; i < _parentElements.size(); i++){
            //check to see if the element has a child
            if(_allWebElements.get(i).findElements(By.cssSelector("*")).isEmpty()){
                Element childele = new Element(_allWebElements.get(i));
                _childElements.add(childele);
            }
        }
        autogen_logging.log(String.format("Found %s elements with No children", _childElements.size()));

        //TODO here's where i'm tinkering
        getSource2();
    }

    public void getSource2(){
        //TODO This is the beginings of the page object storage
        //Assuming that all elements with no children are interactive - meaning they change the state of the application
        //So if the child element has an ID tag we're pretty much done - if the child element does not have an ID tag we would need to trace up the linage a bit
        for(int i = 0; i < _childElements.size(); i++){
            LocatorBuilder locbuild = new LocatorBuilder(_childElements.get(i));
        }
        for(int i = 0; i < _childElements.size(); i++){
            System.out.println(_childElements.get(i).toString() + "Locators: ");
            for(int x = 0; x < _childElements.get(i).get_locators().size(); x++){
                System.out.println(_childElements.get(i).get_locators().get(x).toString());
            }
        }

    }


}
