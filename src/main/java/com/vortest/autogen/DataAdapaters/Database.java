package com.vortest.autogen.DataAdapaters;

import com.vortest.autogen.Element.ByOption;
import com.vortest.autogen.autogen_logging;
import com.vortest.autogen.config;

import javax.xml.crypto.Data;
import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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
    private static final String website_select_query = "SELECT websiteid, uri, create_date, update_date FROM autogendb.websites WHERE active = 1";
    private static final String page_select_query = "SELECT pageid, uri FROM " + config.Db_name + ".pages WHERE websiteid = %s";
    private static final String element_select_query = "SELECT elementid, default_text, tagName, attributes FROM " + config.Db_name + ".elements WHERE pageid = %s";
    private static final String locator_select_query = "SELECT locatorid, locator_by, locator_param FROM " + config.Db_name + ".locators WHERE elementid = %s";
    //setter queries
    private static final String website_insert_query = "INSERT INTO " + config.Db_name + ".websites (uri, page_num, test_num, create_date, update_date, active )VALUES (%s, %s, %s, NOW(), NOW(), 1) ";
    private static final String website_update_query = "UPDATE " + config.Db_name + ".websites SET uri = %s, page_num = %s, test_num = %s, update_date = NOW() WHERE websiteid = %s";
    private static final String page_insert_query = "INSERT INTO " + config.Db_name + ".pages (websiteid, uri, create_date, update_date, active) VALUES (%s, %s, NOW(), NOW(), 1)";
    private static final String page_update_query = "UPDATE " + config.Db_name + ".pages SET uri = %s, update_date = NOW() WHERE pageid = %s";
    private static final String element_insert_query = "INSERT INTO " + config.Db_name + ".elements (pageid, default_text, tagName, attributes, create_date, update_date, active) VALUES (%s, '%s','%s', '%s', NOW(), NOW(), 1)";
    private static final String element_update_query = "UPDATE " + config.Db_name + ".elements SET default_text = %s, tagName = %s, attributes = %s, update_date = NOW() WHERE elementid = %s";
    private static final String locator_insert_query = "INSERT INTO " + config.Db_name + ".locators (elementid, locator_by, locator_param, create_date, update_date, active) VALUES (%s, %s, '%s', NOW(), NOW(), 1)";
    private static final String locator_update_query = "UPDATE " + config.Db_name + ".locators SET locator_by = %s, locator_param = %s, update_date = NOW() WHERE locatorid = %s";

    public static boolean setup_connection(){
        boolean database_exists = true;
        try {
            dbconnect = DriverManager.getConnection(config.Db_location, config.Db_user, config.Db_pass);
            statement = dbconnect.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);

            Class.forName(config.Db_class);
        } catch (Exception e) {
            autogen_logging.log(Database.class.getSimpleName() + "setup connection failed. Exception: " + e.toString());
            database_exists = false;
        }
        return database_exists;
    }


    //region Get Methods
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
            dbconnect.close();
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
                element.attributes = results.getString(4);
                stored_elements.add(element);
            }
            dbconnect.close();
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
                locator.locator_by = ByOption.fromInt(results.getInt(2));
                locator.locator_param = results.getString(3);
                stored_locators.add(locator);
            }
            dbconnect.close();
        }catch (Exception e){
            autogen_logging.log(Database.class.getSimpleName() + " get_locators threw exception: " + e.toString());
        }
        return stored_locators;
    }
    //endregion

    //region Insert Methods
    public static websitesContainer set_website(websitesContainer website){
        //this function will store a website into the database;
        //If the website passed in has an ID - do an update, else do an insert
        setup_connection();
        websitesContainer stored_website = new websitesContainer();
        stored_website.uri = website.uri;
        try{
            if(website.id == null){
                //then we want to insert and get the id back
                String insert_website = String.format(website_insert_query, "'" + website.uri + "'", website.page_num, website.test_num);
                statement.executeUpdate(insert_website, Statement.RETURN_GENERATED_KEYS);
                ResultSet rs = statement.getGeneratedKeys();
                while(rs.next()){
                    stored_website.id = rs.getInt(1);
                }
            }else {
                //We want to update instead
                String update_website = String.format(website_update_query, website.uri, website.page_num, website.test_num, website.id);
                int results = statement.executeUpdate(update_website, Statement.RETURN_GENERATED_KEYS);
                stored_website = website;
            }
            dbconnect.close();
        }catch (Exception e){
            autogen_logging.log(Database.class.getSimpleName() + ":set_website Exception: " + e.toString());
        }
        return stored_website;
    }

    public static pagesContainer set_pages(int websiteid, pagesContainer page){
        //this function will store a page for a website;
        setup_connection();
        pagesContainer stored_page = new pagesContainer();
        stored_page.uri = page.uri;
        try{
            if(page.id == null){
                //we want to do an insert here
                String insert_page = String.format(page_insert_query, websiteid, "'" + page.uri + "'");
                statement.executeUpdate(insert_page, Statement.RETURN_GENERATED_KEYS);
                ResultSet rs = statement.getGeneratedKeys();
                while(rs.next()){
                    stored_page.id = rs.getInt(1);
                }
            }else {
                String update_page = String.format(page_update_query, page.uri, page.id);
                statement.executeUpdate(update_page);
                stored_page = page;
            }
            dbconnect.close();
        }catch (Exception e){
            autogen_logging.log(Database.class.getSimpleName() + ":set_pages Exception: " + e.toString());
        }
        return stored_page;
    }

    public static elementContainer set_element(int pageid, elementContainer element){
        //this function will store the elements
        setup_connection();
        elementContainer stored_element = new elementContainer();
        stored_element.pageid = element.pageid;
        stored_element.default_text = element.default_text.replaceAll("'", "\\$" );

        stored_element.attributes = element.attributes;
        try{
            if(element.id == null){
                //insert into the database
                String insert_element = String.format(element_insert_query, pageid, stored_element.default_text, element.tagName, element.attributes);
                statement.executeUpdate(insert_element, Statement.RETURN_GENERATED_KEYS);
                ResultSet rs = statement.getGeneratedKeys();
                while(rs.next()){
                    stored_element.id = rs.getInt(1);
                }
            }else {
                String update_element = String.format(element_update_query, element.default_text, element.tagName, element.attributes, element.id);
                statement.executeUpdate(update_element);
                stored_element = element;
            }
            dbconnect.close();
        }catch (Exception e){
            autogen_logging.log(Database.class.getSimpleName() + ":set_element Exception: " + e.toString());
        }
        return stored_element;
    }

    public static locatorContainer set_locator(int elementid, locatorContainer locator){
        //this function will store the locators;
        setup_connection();
        locatorContainer stored_locator = new locatorContainer();
        try{
            if(locator.id == null){
                String insert_locator = String.format(locator_insert_query, elementid, locator.locator_by.toInt(locator.locator_by), locator.locator_param.replaceAll("'", "\\$" ));
                stored_locator = locator;
                statement.executeUpdate(insert_locator, Statement.RETURN_GENERATED_KEYS);
                ResultSet rs = statement.getGeneratedKeys();
                while(rs.next()){
                    stored_locator.id = rs.getInt(1);
                }
            }else {
                String update_locator = String.format(locator_update_query, locator.locator_by.ordinal(), locator.locator_param, locator.id);
                stored_locator = locator;
            }
        }catch (Exception e){
            autogen_logging.log(Database.class.getSimpleName() + ":set_locator Exception: " + e.toString());
        }
        return stored_locator;
    }
    //endregion

}
