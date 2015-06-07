package com.vortest.autogen.Page;

import com.google.gson.Gson;
import com.vortest.autogen.Element.Element;
import com.vortest.autogen.autogen_logging;
import com.vortest.autogen.config;
import org.openqa.selenium.WebElement;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This object will track the different pages we crawl
 */
public class Page {
    //This is the container object to track pages we discover
    //A single page is determined by the title and URL of the page as well as header information
    //A page can have multiple states depending on change made to the page

    private List<Element> _elements;
    private String _title;

    public Page(String title, List<Element> elements){
        _elements = elements;
    }

    public void add_element(Element element){
        _elements.add(element);
    }
    public void add_elements(List<Element> elements){
        _elements.addAll(elements);
    }





}
