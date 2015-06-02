package com.vortest.autogen.Element;

import com.vortest.autogen.Element.ByOption;
import com.vortest.autogen.Element.Element;
import com.vortest.autogen.Element.Locator;
import com.vortest.autogen.autogen_logging;
import com.vortest.autogen.crawler;
import org.openqa.selenium.WebElement;

import java.util.*;


public class LocatorBuilder {
    private Element _element;
    private List<Locator> _allLocators;

    private List<Locator> goodLocators;
    private List<String> badLocators;
    private List<String> duplicateLocators;

    //because Java doesn't support default argument values - WTF?
    public LocatorBuilder(Element we){
        _allLocators = new ArrayList<Locator>();
        goodLocators = new ArrayList<Locator>();
        badLocators = new ArrayList<String>();
        duplicateLocators = new ArrayList<String>();
        _element = we;
        buildLocators();
        TestLocators();
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
            if(!_element.getText().equals("")){
                _allLocators.add(new Locator(ByOption.XPath, String.format("//*[text()[contains(., '%s')]]", _element.getText())));
            }
            else{
                HashMap<String, String> useableAttributes = _element.getAttributes();
                String xpath = "//" + _element.TagName;
                Iterator iterator = useableAttributes.entrySet().iterator();
                while(iterator.hasNext()){
                    Map.Entry keyvalpare = (Map.Entry)iterator.next();
                    xpath += String.format("[@%s=\"%s\"]", keyvalpare.getKey(), keyvalpare.getValue());
                    iterator.remove();
                }
                _allLocators.add(new Locator(ByOption.XPath, xpath));
            }
            //_element.setLocators(_allLocators);
        }

    }

    //TODO This should be faster since we're not building multiple locators for the same element
    private void TestLocators(){
        //First we try and find the element - then make sure there's only one element found with that locator.
        for(int x = 0; x < _allLocators.size(); x++){
            try{
                List<WebElement> found = crawler.getDriver().findElements(_allLocators.get(x).ToBy());
                if(found.size() == 1){
                    autogen_logging.log("Found good locator" + _allLocators.get(x).ToBy().toString());
                    goodLocators.add(_allLocators.get(x));
                }
            }
            catch (Exception e){
                autogen_logging.error(e.toString());
            }

        }
        _element.setLocators(goodLocators);
    }


}
