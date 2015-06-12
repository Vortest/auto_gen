package com.vortest.autogen.DataAdapaters;

import com.vortest.autogen.Element.ByOption;
import com.vortest.autogen.autogen_logging;
import com.vortest.autogen.config;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * The purpose of this class is to handle all the connections to the Database that we may need.
 */
public class Database {

    private static Connection dbconnect;
    private static Statement statement;
    //retrieval queries
    private static final String website_select_query = "Select websiteid, uri, create_date, update_date FROM autogendb.websites WHERE active = 1";
    private static final String page_select_query = "Select pageid, uri FROM autogendb.pages where websiteid = %s";
    private static final String element_select_query = "Select elementid, default_text, tagName, attributes FROM autogendb.elements where pageid = %s";
    private static final String locator_select_query = "Select locatorid, locator_by, locator_param FROM autogendb.locators WHERE elementid = %s";
    //setter queries
    private static final String website_insert_query = "Insert into autogen.websites ";
    private static void setup_connection(){
        try {
            dbconnect = DriverManager.getConnection(config.Db_location, config.Db_user, config.Db_pass);
            statement = dbconnect.createStatement();
            Class.forName(config.Db_class);
        } catch (Exception e) {
            autogen_logging.log(Database.class.getSimpleName() + "setup connection failed. Exception: " + e.toString());
        }
    }
    //Get Methods
    public static List<websitesContainer> get_websites(){
        setup_connection();
        //This function will be used to get a list of websites already present in the Database
        List<websitesContainer> stored_sites = new ArrayList<websitesContainer>();
        try{
            ResultSet results = statement.executeQuery(website_select_query);
            while(results.next()){
                websitesContainer site = new websitesContainer();
                site.id = results.getInt(1);
                site.uri = results.getString(2);
                site.CreatedDate = results.getTimestamp(3).toString();
                site.UpdateDate = results.getTimestamp(4).toString();
                stored_sites.add(site);
            }
            dbconnect.close();

        }catch (Exception e){
            autogen_logging.log(Database.class.getSimpleName() + " threw exception: " + e.toString());
        }
        return stored_sites;
    }

    public static List<pagesContainer> get_pages(int websiteid){
        //This method is used to retrieve pages with a given website id;
        setup_connection();
        List<pagesContainer> stored_pages = new ArrayList<pagesContainer>();
        try{
            ResultSet results = statement.executeQuery(String.format(page_select_query, websiteid));
            while(results.next()){
                pagesContainer page = new pagesContainer();
                page.id = results.getInt(1);
                page.uri = results.getString(2);
                stored_pages.add(page);
            }
        }catch(Exception e){
            autogen_logging.log(Database.class.getSimpleName() + " get_pages function threw exception: " + e.toString());
        }
        return stored_pages;
    }

    public static List<elementContainer> get_elements(int pageid){
        //This method is used to retrieve elements with a given page id
        setup_connection();
        List<elementContainer> stored_elements = new ArrayList<elementContainer>();
        try{
            ResultSet results = statement.executeQuery(String.format(element_select_query, pageid));
            while(results.next()){
                elementContainer element = new elementContainer();
                element.id = results.getInt(1);
                element.default_text = results.getString(2);
                element.tagName = results.getString(3);
                element.attributes = results.getString(4); //TODO convert this from-to base64 in element class
                stored_elements.add(element);
            }
        }catch (Exception e){
            autogen_logging.log(Database.class.getSimpleName() + " get_elements function threw exception: " + e.toString());
        }
        return stored_elements;
    }

    public static List<locatorContainer> get_locators(int elementid){
        //this function will return locators for the provided elementid
        setup_connection();
        List<locatorContainer> stored_locators = new ArrayList<locatorContainer>();
        try{
            ResultSet results = statement.executeQuery(String.format(locator_select_query, elementid));
            while(results.next()){
                locatorContainer locator = new locatorContainer();
                locator.id = results.getInt(1);
                locator.locator_by = results.getInt(2);
                locator.locator_param = results.getString(3);
                stored_locators.add(locator);
            }
        }catch (Exception e){
            autogen_logging.log(Database.class.getSimpleName() + " get_locators threw exception: " + e.toString());
        }
        return stored_locators;
    }

    //set methods
    //TODO setter methods
    //TODO update methods

}
