package com.vortest.autogen.Element;


import com.vortest.autogen.Crawler;
import com.vortest.autogen.Locator;
import org.openqa.selenium.*;


import java.util.*;

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
    public HashMap<String, String> getAttributes(){
        //This function will try and return notable attributes first before returning other attributes
        //Notable attributes Type, Value, Name, ClassName
        HashMap<String, String> matchingAttributes = new HashMap<String, String>();

        if(Attributes.size() > 0){
            if(Attributes.containsKey("name")){
                matchingAttributes.put("name", (String) Attributes.get("name"));
            }
            if(Attributes.containsKey("type")){
                matchingAttributes.put("type", (String) Attributes.get("type"));
            }
            if(Attributes.containsKey("value")){
                matchingAttributes.put("value", (String) Attributes.get("value"));
            }
            if(Attributes.containsKey("class")){
                matchingAttributes.put("class", (String) Attributes.get("class"));
            }
            else{
                //get at least 3 random attributes if we don't have at least 3 attributes
                if(matchingAttributes.size() <= 2 && Attributes.size() >= 3){
                    //We only want the matchingAttributes return 3 values OR match the Attributes length if 3 or less
                    for(int i = 0; i < 3; i++){
                        Random random = new Random();
                        List<String> keys = new ArrayList<String>(Attributes.keySet());
                        String randomKey = keys.get(random.nextInt(keys.size()));
                        matchingAttributes.put(randomKey, (String) Attributes.get(randomKey));
                    }
                }
            }
        }

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
