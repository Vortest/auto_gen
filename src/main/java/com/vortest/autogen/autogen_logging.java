package com.vortest.autogen;


import com.vortest.autogen.UI.autogen_main_jform;
import com.vortest.autogen.UI.automation_engine;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class autogen_logging {

    private static Logger log = Logger.getLogger("auto_gen");
    private static autogen_main_jform autogen;

    FileHandler fh;

    public autogen_logging(){

        try{
            fh = new FileHandler(config.logFile, true);
            log.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
        }catch (SecurityException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void log(String message){
        log.info(message);

        if(!autogen.equals(null)){
            autogen.log_text_area.append(message + "\n");
            try {
                autogen.log_text_area.setCaretPosition(autogen.log_text_area.getLineStartOffset(autogen.log_text_area.getLineCount() - 1));
            }catch (Exception e){

            }
        }
    }

    public static void warn(String message){
        log.warning(message);
    }

    public static void error(String message){
        log.severe(message);
    }

    public static void setform(final autogen_main_jform ae){
        autogen = ae;
    }

}
