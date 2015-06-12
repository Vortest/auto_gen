package com.vortest.autogen;

import java.util.ArrayList;
import java.util.List;

/**
 * Just a place to store global config options
 */
public class config {

    //Log File Settings
    public static final String logFile = "/Users/sethurban/GitHub/Vortest/auto_gen/autogen.log";

    //Test Sites
    public static final String TestSite1 = "http://adam.goucher.ca/parkcalc/";
    public static final String TestSite2 = "http://www.reddit.com";

    //Database connection
    public static final String Db_name = "autogendb";
    public static final String Db_location = "jdbc:mysql://localhost/" + Db_name;
    public static final String Db_class = "com.mysql.jdbc.Driver";
    public static final String Db_user = "root";
    public static final String Db_pass = "";

}
