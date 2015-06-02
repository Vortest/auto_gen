package com.vortest.autogen;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class crawler {
    private static Driver driver;
    private String baseUrl;

    public crawler(String startURL){
        baseUrl = startURL;
        autogen_logging.log(crawler.class.getSimpleName() + " Opening Browser...");
        openBrowser();
    }

    public void openBrowser() {
        driver = new Driver();
        driver.get(baseUrl);

        //need to check that the page is done loading
        PageParser parse = new PageParser();
    }

    public static Driver getDriver(){
        return driver;
    }


}
