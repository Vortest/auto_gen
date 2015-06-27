package com.vortest.autogen.Page;

import com.google.gson.Gson;
import com.vortest.autogen.DataAdapaters.*;
import com.vortest.autogen.Element.Element;
import com.vortest.autogen.Element.LocatorBuilder;
import com.vortest.autogen.autogen_logging;
import com.vortest.autogen.config;
import com.vortest.autogen.crawler;
import org.openqa.selenium.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PageParser {
    private WebDriver driver;
    private List<Element> _allWebElements;
    private List<Element> _childElements;
    private int _websiteid;
    private pagesContainer _page;
    private String _pageTitle;
    private String _pageURL;


    public PageParser(int website_id){
        _websiteid = website_id;
        gather_page_data();
        getAllElements();
        get_ImportantElements();
    }

    private void gather_page_data() {
        _pageTitle = crawler.getDriver().findElementByTagName("title").getText();
        _pageURL = crawler.getDriver().getCurrentUrl();
        pagesContainer thispage = new pagesContainer();
        thispage.websiteid = _websiteid;
        thispage.uri = _pageURL;
        _page = Database.set_pages(_websiteid, thispage);
        autogen_logging.log(String.format("Added PageID: %s to database", _page.id));
    }


    public void getAllElements(){
        _allWebElements = new ArrayList<Element>();
        autogen_logging.log(PageParser.class.getSimpleName() + " Scanning for elements...");
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
                element.PageID = _page.id;
                element.persistElement();
                _childElements.add(element);
            }
            if(element.findElements(By.cssSelector("*")).isEmpty()){
                element.PageID = _page.id;
                element.persistElement();
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
    }

    public Page get_page(){
        return new Page(_pageTitle, _pageURL, _childElements);
    }




}
