/*
 * Created by JFormDesigner on Thu Jun 11 07:38:49 MDT 2015
 */

package com.vortest.autogen.UI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
import com.vortest.autogen.DataAdapaters.*;
import java.util.List;

public class autogen_main_jform {
    private DefaultListModel existing_sites_dm;  //Because god forbid we're allowed to

    public autogen_main_jform() {
        initComponents();
        //Test Database connection
        if(!Database.setup_connection()){
            JOptionPane.showMessageDialog(autogen_main, "Unable to connect to database: autogen", "Database Connection Error", JOptionPane.ERROR_MESSAGE);
        }
        populateExistingWebsites();
        autogen_main.setVisible(true);
        autogen_main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Database.get_websites();
    }

    private void populateExistingWebsites() {
        //gotta figure out how to add shit to a list
        List<websitesContainer> websites = Database.get_websites();
        for(int i = 0; i < websites.size(); i++){
            existing_sites_dm.addElement(websites.get(i).uri);
        }
    }

    public static void main(String[] args){
        new autogen_main_jform();
    }


    private void button1MouseClicked(MouseEvent e) {
        // TODO add your code here
    }

    private void scan_website_btnMouseClicked(MouseEvent e) {
        websitesContainer newwebsite = new websitesContainer();
        newwebsite.uri = "http://www.test.com";
        newwebsite.page_num = 4;
        newwebsite.test_num = 8;
        newwebsite.id = Database.set_website(newwebsite).id;
    }

    private void initComponents() {
        existing_sites_dm = new DefaultListModel();
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - seth urban
        autogen_main = new JFrame();
        autogen_label = new JLabel();
        website_label = new JLabel();
        textField1 = new JTextField();
        scan_website_btn = new JButton();
        existing_sites_label = new JLabel();
        scrollPane1 = new JScrollPane();
        existing_websites_list = new JList(existing_sites_dm);
        existing_site_instructions = new JLabel();
        retest_btn = new JButton();
        tests_label = new JLabel();
        totaltests_tb = new JTextField();
        passed_label = new JLabel();
        testspass_tb = new JTextField();
        test_failed_lbl = new JLabel();
        testsfailed_tb = new JTextField();
        CellConstraints cc = new CellConstraints();

        //======== autogen_main ========
        {
            Container autogen_mainContentPane = autogen_main.getContentPane();
            autogen_mainContentPane.setLayout(new FormLayout(
                new ColumnSpec[] {
                    new ColumnSpec(Sizes.DLUX11),
                    FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
                    new ColumnSpec(Sizes.dluX(118)),
                    FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
                    FormFactory.DEFAULT_COLSPEC,
                    FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
                    new ColumnSpec(Sizes.dluX(66)),
                    FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
                    new ColumnSpec(Sizes.dluX(107)),
                    new ColumnSpec(Sizes.dluX(64)),
                    FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
                    FormFactory.DEFAULT_COLSPEC,
                    FormFactory.LABEL_COMPONENT_GAP_COLSPEC
                },
                new RowSpec[] {
                    FormFactory.DEFAULT_ROWSPEC,
                    FormFactory.LINE_GAP_ROWSPEC,
                    FormFactory.DEFAULT_ROWSPEC,
                    FormFactory.LINE_GAP_ROWSPEC,
                    FormFactory.DEFAULT_ROWSPEC,
                    FormFactory.LINE_GAP_ROWSPEC,
                    FormFactory.DEFAULT_ROWSPEC,
                    FormFactory.LINE_GAP_ROWSPEC,
                    FormFactory.DEFAULT_ROWSPEC,
                    FormFactory.LINE_GAP_ROWSPEC,
                    FormFactory.DEFAULT_ROWSPEC,
                    FormFactory.LINE_GAP_ROWSPEC,
                    FormFactory.DEFAULT_ROWSPEC,
                    FormFactory.LINE_GAP_ROWSPEC,
                    FormFactory.DEFAULT_ROWSPEC,
                    FormFactory.LINE_GAP_ROWSPEC,
                    FormFactory.DEFAULT_ROWSPEC,
                    FormFactory.LINE_GAP_ROWSPEC,
                    FormFactory.DEFAULT_ROWSPEC
                }));

            //---- autogen_label ----
            autogen_label.setText("Automation Generation Engine");
            autogen_mainContentPane.add(autogen_label, cc.xywh(3, 1, 3, 1));

            //---- website_label ----
            website_label.setText("Webpage to Generate");
            autogen_mainContentPane.add(website_label, cc.xy(3, 5));

            //---- textField1 ----
            textField1.setText("http://www.google.com");
            autogen_mainContentPane.add(textField1, cc.xywh(5, 5, 5, 1));

            //---- scan_website_btn ----
            scan_website_btn.setText("Scan Webpage");
            scan_website_btn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button1MouseClicked(e);
                    scan_website_btnMouseClicked(e);
                }
            });
            autogen_mainContentPane.add(scan_website_btn, cc.xy(10, 5));

            //---- existing_sites_label ----
            existing_sites_label.setText("Existing Websites");
            autogen_mainContentPane.add(existing_sites_label, cc.xy(3, 7));

            //======== scrollPane1 ========
            {
                scrollPane1.setViewportView(existing_websites_list);
            }
            autogen_mainContentPane.add(scrollPane1, cc.xywh(3, 9, 8, 1));

            //---- existing_site_instructions ----
            existing_site_instructions.setText("To run a test for an existing site, select the site from the list and click the re-test button.");
            autogen_mainContentPane.add(existing_site_instructions, cc.xywh(3, 11, 9, 1));

            //---- retest_btn ----
            retest_btn.setText("ReTest");
            autogen_mainContentPane.add(retest_btn, cc.xy(10, 13));

            //---- tests_label ----
            tests_label.setText("Total Tests:");
            autogen_mainContentPane.add(tests_label, cc.xy(3, 15));
            autogen_mainContentPane.add(totaltests_tb, cc.xy(7, 15));

            //---- passed_label ----
            passed_label.setText("Tests Passed:");
            autogen_mainContentPane.add(passed_label, cc.xy(3, 17));
            autogen_mainContentPane.add(testspass_tb, cc.xy(7, 17));

            //---- test_failed_lbl ----
            test_failed_lbl.setText("Tests Failed:");
            autogen_mainContentPane.add(test_failed_lbl, cc.xy(3, 19));
            autogen_mainContentPane.add(testsfailed_tb, cc.xy(7, 19));
            autogen_main.setSize(845, 585);
            autogen_main.setLocationRelativeTo(autogen_main.getOwner());
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - seth urban
    private JFrame autogen_main;
    private JLabel autogen_label;
    private JLabel website_label;
    private JTextField textField1;
    private JButton scan_website_btn;
    private JLabel existing_sites_label;
    private JScrollPane scrollPane1;
    private JList existing_websites_list;
    private JLabel existing_site_instructions;
    private JButton retest_btn;
    private JLabel tests_label;
    private JTextField totaltests_tb;
    private JLabel passed_label;
    private JTextField testspass_tb;
    private JLabel test_failed_lbl;
    private JTextField testsfailed_tb;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
