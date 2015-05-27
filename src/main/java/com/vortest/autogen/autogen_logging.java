package com.vortest.autogen;


import java.io.IOException;
import java.time.LocalTime;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class autogen_logging {

    private static Logger log = Logger.getLogger("auto_gen");
    FileHandler fh;

    public autogen_logging(){
        try{
            fh = new FileHandler("/Users/SethUrban/GitHub/VorTest/auto_gen/autogen.log", true);
            log.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
        }catch (SecurityException e){

        }catch (IOException e){

        }
    }

    public static void log(String message){
        System.out.println(message);
        log.info(message);
    }

}
