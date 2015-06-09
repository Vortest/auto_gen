/**
 * Created by SethUrban on 5/24/15.
 */
package com.vortest.autogen.UI;

import com.vortest.autogen.autogen_logging;
import com.vortest.autogen.crawler;
import org.apache.commons.validator.routines.UrlValidator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class automation_engine extends JFrame {

    private JPanel main_panel;
    private JLabel app_label;
    private JPanel footer_panel;
    private JPanel content_panel;
    private JTextField url_textfield;
    private JButton scan_button;
    private JProgressBar scan_progress;
    private JLabel error_message_label;
    public JTextArea log_textarea;
    private crawler crawl;



    public automation_engine()  {

        scan_button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(validURL(url_textfield.getText())){
                    clear_error();
                    autogen_logging.setform(automation_engine.this);
                    Runnable run = new crawler(url_textfield.getText());
                    Thread thread = new Thread(run);
                    thread.start();
                }
                else{
                    setError("Invalid URL Provided!");
                }

            }
        });
        scan_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(String[] args) {

        initUI();
    }

    private static void initUI(){
        JFrame frame = new JFrame("automation_engine");
        frame.setContentPane(new automation_engine().main_panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setAlwaysOnTop(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private boolean validURL(String url){
        boolean URL_valid = false;
        String[] schemes = {"http", "https"};
        UrlValidator urlValidator = new UrlValidator(schemes);
        if(urlValidator.isValid(url)){
            URL_valid = true;
        }
        return URL_valid;
    }

    private void setError(String error){
        error_message_label.setText(error);
        error_message_label.setForeground(new Color(247, 5, 37));
        error_message_label.setVisible(true);
    }

    private void clear_error(){
        error_message_label.setVisible(false);
    }

    public void update_ui_Log(String uilog){
        log_textarea.append(uilog + "\n");
    }




    /*private static String entryURL;
    public static crawler crawl;
    private static final boolean DEBUG = true;
    private static autogen_logging log = new autogen_logging();

    public static void main(String [] args){
        //parse the args
        setupArgs(args);
        //call some class to setup the browser
        log.log("Here's a log sucker!");
        crawl = new crawler(entryURL);
    }

    private static void setupArgs(String[] args) {
        if(DEBUG){
            entryURL = config.TestSite1;
        }
        else{
            if (args.length == 0){
                System.out.print("There was no URL specified.\n");
                System.exit(-1);
            }
            else {
                System.out.print(String.format("Selected URL setto: %s %s", args[0], "\n"));
                entryURL = args[0];
            }
        }
    }*/
}
