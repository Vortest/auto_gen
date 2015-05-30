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

    private List<String> parentHTML;
    private List<String> childHTML;

    public PageParser(){

    }

    public void getAllElements(){
        _parentElements = new ArrayList<Element>();
        _allWebElements = new ArrayList<WebElement>();
        _allWebElements = Crawler.getDriver().findElements(By.cssSelector("body *"));
        for(int x = 0; x < _allWebElements.size(); x++){
            Element anElement = new Element(_allWebElements.get(x));
            _parentElements.add(anElement);
        }
    }

    public void getChildelements(){
        _childElements = new ArrayList<Element>();

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

    }



    public void getSource2(){
        //TODO This is the beginings of the page object storage
        //Assuming that all elements with no children are interactive - meaning they change the state of the application
        //So if the child element has an ID tag we're pretty much done - if the child element does not have an ID tag we would need to trace up the linage a bit
        for(int i = 0; i < _childElements.size(); i++){
            LocatorBuilder locbuild = new LocatorBuilder(_childElements.get(i));
        }
        for(int i = 0; i < _childElements.size(); i++){
            System.out.println("***ELEMENT: " + _childElements.get(i).toString());
            if(_childElements.get(i).hasText()){
                System.out.println("Text: " + _childElements.get(i).get_text());
            }
            if(_childElements.get(i).Attributes != null){
                System.out.println("Attributes: " + _childElements.get(i).Attributes.toString());
            }
            for(int x = 0; x < _childElements.get(i).get_locators().size(); x++){
                System.out.print("Locators: " + _childElements.get(i).get_locators().get(x).toString());
            }
        }
    }


}
