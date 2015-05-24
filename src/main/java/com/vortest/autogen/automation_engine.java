/**
 * Created by SethUrban on 5/24/15.
 */
package com.vortest.autogen;

public class automation_engine {
    private static String entryURL;

    public static void main(String [] args){
        //parse the args
        setupArgs(args);
        //call some class to setup the browser
    }

    private static void setupArgs(String[] args) {
        System.out.print("Maven Compiled\n");
        if (args.length == 0){
            System.out.print("There was no URL specified.");
            System.exit(-1);
        }
        else {
            entryURL = args[0];
        }
    }
}
