package com.vortest.autogen.Element;


import com.vortest.autogen.Crawler;
import com.vortest.autogen.Locator;
import org.openqa.selenium.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Element {
    public static final int MAXLOCATORS = 5;

    private WebElement _parent;
    private WebElement _element;
    private WebDriver _driver;
    public Map Attributes;
    private List<Locator> _locators;

    public String TagName; //this is the thing that we can use to build custom elements
    private boolean _isVisible;
    private boolean _isEnabled;
    private boolean _isOnscreen;
    private Point _screenLocation;
    private String _text;

    public Element(WebElement ele){
        _element = ele;
        _driver = Crawler.getDriver();
        elementPreProcessor();
        if(isInteractive()){
            buildAttributes();
            _parent = _element.findElement(By.xpath(".."));
        }
    }

    private void elementPreProcessor() {
        TagName = _element.getTagName();
        _isVisible = _element.isDisplayed();
        _isEnabled = _element.isEnabled();
        _text = _element.getText();
        _screenLocation = _element.getLocation();
        if(_screenLocation.getX() > 0 && _screenLocation.getY() > 0){
            _isOnscreen = true;
        }
    }

    public boolean hasText(){
        boolean text = false;
        if(!_text.equals("")){
            text = true;
        }
        return text;
    }

    public String get_text(){
        String text = null;
        if(hasText()){
            text = _text;
        }
        return text;
    }

    public boolean isInteractive(){
        boolean isactive = false;
        if(_isVisible && _isEnabled && _isOnscreen){
            isactive = true;
        }
        return isactive;
    }

    private void buildAttributes(){
        //This little chunklet will get all the attributes for a given element
        Attributes = (Map) ((JavascriptExecutor)_driver).executeScript("var items = {}; " +
                "for (index = 0; index < arguments[0].attributes.length;" +
                " ++index) { items[arguments[0].attributes[index].name] = arguments[0].attributes[index].value }; " +
                "return items;", _element);
    }
    public List<Map> getAttribute(int numAttributes){
        //This function will try and return notable attributes first before returning other attributes
        //Notable attributes Type, Value, Name, ClassName
        List<Map> matchingAttributes = new ArrayList<Map>();
        //TODO need to check that the list of attributes contains enough attributes to return
        //TODO needs to be more than 1 unless it's ID


        return matchingAttributes;
    }

    public Element getParent(){
        //This will return the parent element of the current element
        return new Element(_element.findElement(By.xpath("..")));
    }


    public String getAttribute(String key){
        String value = "";
        if(Attributes.containsKey(key)){
            value = (String) Attributes.get(key);
        }
        return value;
    }

    public void setLocators(List<Locator> locators){
        _locators = locators;
    }
    public List<Locator> get_locators(){
        return _locators;
    }

}
