package com.vortest.autogen;


import java.time.LocalTime;

public class autogen_logging {


    public static void log(String message){
        LocalTime thissecond = LocalTime.now();
        System.out.print(thissecond + ":  message");
    }

}
