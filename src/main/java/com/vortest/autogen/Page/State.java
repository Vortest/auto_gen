package com.vortest.autogen.Page;

import com.vortest.autogen.Element.Element;
import org.apache.commons.codec.binary.Base64;

import java.util.List;

/**
 * This class will be used to track the found states of the pages we discover
 */
public class State {
    //This is really only checking the state of the page at a specific uri
    //The big question right now is how is state determined?
    //What determines the state of the page -
    // 1) If there are new elements being displayed on the page - AJAX Stuff
    // 2) other?
    //The state of the page is determined by:
    //The page uri doesn't change - The visible elements on the page and their default values do not change
    //Our test site has three states: 1) Entry, Error state, and Successful Calculation
    //This class needs to return an unique identifier per state the page is in
    //Takesome values and encode them in base64

    public static String encodeState(String url, List<Element> elements){
        String pageState = url;
        for(int x = 0; x < elements.size(); x++){
            pageState += elements.get(x).TagName;
            pageState += elements.get(x).getText();
            pageState += elements.get(x).getFirstLocator().toString();
        }
        System.out.println("Page state: " + pageState);
        return Base64.encodeBase64String(pageState.getBytes());
    }

    public static String decodeState(String encoded){
        byte[] decoded = Base64.decodeBase64(encoded);
        return new String(decoded);

    }



    
}
