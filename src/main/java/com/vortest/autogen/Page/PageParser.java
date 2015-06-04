package com.vortest.autogen.Page;

import com.vortest.autogen.Element.Element;
import com.vortest.autogen.Element.LocatorBuilder;
import com.vortest.autogen.autogen_logging;
import com.vortest.autogen.crawler;
import org.openqa.selenium.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PageParser {
    private WebDriver driver;
    private List<Element> _allWebElements;
    private List<Element> _childElements;

    public PageParser(){
        getAllElements();
        get_ImportantElements();
    }


    public void getAllElements(){
        _allWebElements = new ArrayList<Element>();
        _allWebElements = crawler.getDriver().FindElements(By.cssSelector("body *"));
    }

    public void get_form_inputs(List<Element> form){
        if(form.size() > 0){
            for(Iterator<Element> i = form.iterator(); i.hasNext();){
                Element element = i.next();
                element.get_children(By.cssSelector(element.getTagName() + " input"));

            }
        }
    }

    public void get_childElements(List<Element> parents){
        if(parents.size() > 0){
            //we want to find all the input elements on the form - their maybe multiple forms
            for(Iterator<Element> i = parents.iterator(); i.hasNext();){
                Element element = i.next();
                element.get_children(By.cssSelector(element.getTagName() + " *"));
            }
        }
    }

    public void get_ImportantElements(){
        _childElements = new ArrayList<Element>();

        autogen_logging.log(String.format("Found %s TOTAL elements on the page", _allWebElements.size()));
        for(int i = 0; i < _allWebElements.size(); i++){
            Element element = _allWebElements.get(i);
            if(element.TagName.equals("p") || element.TagName.equals("a") || element.TagName.equals("select")){
                autogen_logging.log("Found Paragraph, link or Dropdown");
                _childElements.add(element);
            }
            if(element.findElements(By.cssSelector("*")).isEmpty()){
                _childElements.add(element);
                autogen_logging.log("Found Element with no descendants!");
            }
        }
        autogen_logging.log(String.format("Found %s elements with No children", _childElements.size()));

        GatherLocators();
    }

    public void GatherLocators(){
        //TODO This is the beginings of the page object storage
        //Assuming that all elements with no children are interactive - meaning they change the state of the application
        //So if the child element has an ID tag we're pretty much done - if the child element does not have an ID tag we would need to trace up the linage a bit
        for(int i = 0; i < _childElements.size(); i++){
            LocatorBuilder locbuild = new LocatorBuilder(_childElements.get(i));
        }
        Page thispage = new Page(_childElements);
        thispage.savePage();
    }




}
