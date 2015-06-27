package com.vortest.autogen.Element;


import com.vortest.autogen.crawler;
import org.openqa.selenium.*;


import java.util.*;

public class Element implements WebElement {
    private Element _parent;
    private List<Element> _children;

    private WebElement _element;
    private WebDriver _driver;
    public Map Attributes;
    private List<Locator> _locators;

    public String TagName; //this is the thing that we can use to build custom elements
    private boolean _isVisible;
    private boolean _isEnabled;
    private boolean _isOnscreen;
    public Integer PageID;
    public Integer ElementID;


    public Element(WebElement ele){
        _element = ele;
        _driver = crawler.getDriver();
        elementPreProcessor();
        buildAttributes();
    }

    private void elementPreProcessor() {
        TagName = _element.getTagName();
        _isVisible = _element.isDisplayed();
        _isEnabled = _element.isEnabled();

        if(_element.getSize().getHeight() > 1 && _element.getSize().getWidth() > 1){
            _isOnscreen = true;
        }
    }

    public Element get_parent(){
        _parent = new Element(_element.findElement(By.xpath("..")));
        return _parent;
    }

    public List<Element> get_children(By by){
        _children = this.FindElements(by);
        return _children;
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
            if(Attributes.containsKey("href")){
                matchingAttributes.put("href", (String) Attributes.get("href"));
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

    public void setLocators(List<Locator> locators){
        _locators = locators;
    }
    public List<Locator> get_locators(){
        if(_locators == null){
            _locators = new ArrayList<Locator>();
        }
        return _locators;
    }

    public List<Element> FindElements(By by){
        List<Element> elements = new ArrayList<Element>();
        List<WebElement> webElements = _element.findElements(by);
        for(Iterator<WebElement> i = webElements.iterator(); i.hasNext();){
            elements.add(new Element(i.next()));
        }
        return elements;
    }

    public Element FindElement(By by){
        return new Element(_element.findElement(by));
    }

    public Locator getFirstLocator(){
        if(_locators.size() > 0){
            return _locators.get(0);
        }
        else{
            return null;
        }
    }


    //***************************
    //Impemented Methods
    //**************************
    public void click() {
        _element.click();
    }

    public void submit() {
        _element.submit();
    }

    public void sendKeys(CharSequence... charSequences) {
        _element.sendKeys(charSequences);
    }

    public void clear() {
        _element.clear();
    }

    public String getTagName() {
       return _element.getTagName();
    }

    public String getAttribute(String key){
        String value = "";
        if(Attributes.containsKey(key)){
            value = (String) Attributes.get(key);
        }
        return value;
    }

    public boolean isSelected() {
        return _element.isSelected();
    }

    public boolean isEnabled() {
        return _element.isEnabled();
    }

    public String getText() {
        return _element.getText();
    }

    public List<WebElement> findElements(By by) {
        return _element.findElements(by);
    }

    public WebElement findElement(By by) {
        return _element.findElement(by);
    }

    public boolean isDisplayed() {
        return _element.isDisplayed();
    }

    public Point getLocation() {
        return _element.getLocation();
    }

    public Dimension getSize() {
        return _element.getSize();
    }

    public String getCssValue(String s) {
        return _element.getCssValue(s);
    }



}
