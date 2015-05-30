package com.vortest.autogen;

import com.vortest.autogen.Element.Element;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;


public class LocatorBuilder {
    private Element _element;
    private List<Locator> _allLocators;

    private List<String> goodLocators;
    private List<String> badLocators;
    private List<String> duplicateLocators;

    //because Java doesn't support default argument values - WTF?
    public LocatorBuilder(Element we){
        _allLocators = new ArrayList<Locator>();
        goodLocators = new ArrayList<String>();
        badLocators = new ArrayList<String>();
        duplicateLocators = new ArrayList<String>();
        _element = we;
        buildLocators();
    }

    private void buildLocators() {
        //I'm thinking we should just build a locator for everything
        //With an xpath containing several attributes
        //This way we shouldn't have to build multiple locators just one unique one
        if(_element.isInteractive()) {
            if (_element.Attributes.containsKey("id")) {//This locator should always be unique
                _allLocators.add(new Locator(ByOption.Id, _element.getAttribute("id")));
            }
            else{
                //TODO buid a locator with returned attributes
            }
        }
    }

    //TODO This should be faster since we're not building multiple locators for the same element
    private void TestLocator(Locator locator){
        //First we try and find the element - then make sure there's only one element found with that locator.

        try {
            List<WebElement> foundElements = Crawler.getDriver().findElements(locator.ToBy());
            if(foundElements.size() == 1){
                //Found a unique locator!
                goodLocators.add(locator.ToBy().toString());
            }
            if(foundElements.size() == 0){
                //Found NO goodLocators you suck!
                badLocators.add(locator.ToBy().toString());
            }
            if(foundElements.size() > 1){
                //Found to many locators
                duplicateLocators.add(locator.ToBy().toString());
            }
        }
        catch (Exception e){
            //The locator was not valid - TODO log this
        }
    }

    public List<String> getGoodLocators(){
        return goodLocators;
    }

}
