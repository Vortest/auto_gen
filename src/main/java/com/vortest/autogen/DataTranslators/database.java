package com.vortest.autogen.DataTranslators;

import com.vortest.autogen.autogen_logging;
import com.vortest.autogen.config;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * The purpose of this class is to handle all the connections to the database that we may need.
 */
public class database {

    private static Connection dbconnect;
    private static Statement statement;
    private static final String website_select_query = "Select websiteid, uri, create_date, update_date FROM autogendb.websites WHERE active = 1";

    private static void setup_connection(){
        try {
            dbconnect = DriverManager.getConnection(config.Db_location, config.Db_user, config.Db_pass);
            statement = dbconnect.createStatement();
            Class.forName(config.Db_class);
        } catch (Exception e) {
            autogen_logging.log(database.class.getSimpleName() + "setup connection failed. Exception: " + e.toString());
        }
    }

    public static List<websitesContainer> get_websites(){
        setup_connection();
        //This function will be used to get a list of websites already present in the database
        List<websitesContainer> stored_sites = new ArrayList<websitesContainer>();
        try{

            ResultSet results = statement.executeQuery(website_select_query);
            while(results.next()){
                websitesContainer site = new websitesContainer();
                site.id = results.getInt(1);
                site.URL = results.getString(2);
                site.CreatedDate = results.getTimestamp(3).toString();
                site.UpdateDate = results.getTimestamp(4).toString();
                stored_sites.add(site);
            }
            dbconnect.close();

        }catch (Exception e){
            autogen_logging.log(database.class.getSimpleName() + " threw exception: " + e.toString());
        }

        return stored_sites;
    }

}
