/**
 * Created by SethUrban on 5/24/15.
 */
package com.vortest.autogen;
import com.vortest.autogen.Crawler;
import org.openqa.selenium.*;

public class automation_engine {
    private static String entryURL;
    public static Crawler crawl;
    private static final boolean DEBUG = true;
    private static autogen_logging log = new autogen_logging();

    public static void main(String [] args){
        //parse the args
        setupArgs(args);
        //call some class to setup the browser
        log.log("Here's a log sucker!");
        crawl = new Crawler(entryURL);
    }

    private static void setupArgs(String[] args) {
        if(DEBUG){
            entryURL = "http://adam.goucher.ca/parkcalc/";
        }
        else{
            if (args.length == 0){
                System.out.print("There was no URL specified.\n");
                System.exit(-1);
            }
            else {
                System.out.print(String.format("Selected URL setto: %s %s", args[0], "\n"));
                entryURL = args[0];
            }
        }
    }
}
