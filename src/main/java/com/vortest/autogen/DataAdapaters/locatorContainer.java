package com.vortest.autogen.DataAdapaters;

import com.vortest.autogen.Element.ByOption;

/**
 * This class stores the locators retrieve and to be stored in the database.  Elements can have many locators.
 */
public class locatorContainer {
    public Integer id;
    public ByOption locator_by;
    public String locator_param;
}
