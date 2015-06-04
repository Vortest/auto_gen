package com.vortest.autogen.Page;

import com.google.gson.Gson;
import com.vortest.autogen.Element.Element;
import com.vortest.autogen.autogen_logging;
import com.vortest.autogen.config;

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

    private List<Element> elementList; //used to store elements on the page

    public Page(List<Element> elements){
        elementList = elements;
    }

    public void savePage(){
        Gson gson = new Gson();
        String json = gson.toJson(this);
        try{
            FileWriter writer = new FileWriter(config.JSONStore + "page.json");
            writer.write(json);
            writer.close();
        }catch (IOException e){
            autogen_logging.error(e.getStackTrace().toString());
        }
    }

    @Override
    public String toString(){
        String json_String = "PageObjectName [elements=" + elementList + "]";
        return json_String;
    }
}
