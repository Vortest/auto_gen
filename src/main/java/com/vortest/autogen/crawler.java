package com.vortest.autogen;

import com.vortest.autogen.DataAdapaters.Database;
import com.vortest.autogen.DataAdapaters.websitesContainer;
import com.vortest.autogen.Page.Page;
import com.vortest.autogen.Page.PageParser;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class crawler implements Runnable {
    private static Driver driver;
    private String baseUrl;
    private websitesContainer _website;

    public crawler(String startURL){
        baseUrl = startURL;
    }

    public void run() {
        autogen_logging.log(crawler.class.getSimpleName() + " Opening Browser...");
        openBrowser();
    }

    public void openBrowser() {
        driver = new Driver();
        driver.get(baseUrl);
        gatherSiteInfo();
        autogen_logging.log(crawler.class.getSimpleName() + " Loaded browser...");
        //need to check that the page is done loading
        PageParser parse = new PageParser(_website.id);
        Page page = parse.get_page();

    }

    private void gatherSiteInfo() {
        websitesContainer thissite = new websitesContainer();
        thissite.uri = baseUrl;
        _website = Database.set_website(thissite);
    }

    public static Driver getDriver(){
        return driver;
    }



}
