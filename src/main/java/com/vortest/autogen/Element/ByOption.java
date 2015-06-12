package com.vortest.autogen.Element;

//NOTE: This order must match the order from the database
public enum ByOption{
    ClassName,
    XPath,
    Id,
    Name,
    LinkText,
    PartialLinkText,
    CssSelector;

    public static ByOption fromInt(int javasucks){
        switch (javasucks){
            case 0:
                return ClassName;
            case 1:
                return XPath;
            case 2:
                return Id;
            case 3:
                return Name;
            case 4:
                return LinkText;
            case 5:
                return PartialLinkText;
            case 6:
                return CssSelector;
        }return null;
    }
}