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

    //this is so stupid that I have to actually code these to methods
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

    public static Integer toInt(ByOption stupidjava){
        switch (stupidjava){
            case ClassName:
                return 0;
            case XPath:
                return 1;
            case Id:
                return 2;
            case Name:
                return 3;
            case LinkText:
                return 4;
            case PartialLinkText:
                return 5;
            case CssSelector:
                return 6;
        }return null;
    }
}