package com.vortest.autogen;

import com.vortest.autogen.Element.Element;
import org.openqa.selenium.WebElement;

import java.util.*;


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
        boolean elementInteractive = _element.isInteractive();
        boolean parentInteractive = _element.get_parent().isInteractive();
        if(_element.isInteractive() || _element.get_parent().isInteractive()) {
            if (_element.Attributes.containsKey("id")) {//This locator should always be unique
                _allLocators.add(new Locator(ByOption.Id, _element.getAttribute("id")));
            }
            else{
                HashMap<String, String> useableAttributes = _element.getAttributes();
                String xpath = "//" + _element.TagName;
                Iterator iterator = useableAttributes.entrySet().iterator();
                while(iterator.hasNext()){
                    Map.Entry keyvalpare = (Map.Entry)iterator.next();
                    xpath += String.format("[@%s='%s']", keyvalpare.getKey(), keyvalpare.getValue());
                    iterator.remove();
                }
                _allLocators.add(new Locator(ByOption.XPath, xpath));
            }
            _element.setLocators(_allLocators);
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
