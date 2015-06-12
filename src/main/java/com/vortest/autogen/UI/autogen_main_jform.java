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


/**
 * @author unknown
 */
public class autogen_main_jform {
    public autogen_main_jform() {
        initComponents();
        autogen_main.setVisible(true);
        autogen_main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Database.get_websites();


    }

    public static void main(String[] args){
        new autogen_main_jform();
    }
    private void button1MouseClicked(MouseEvent e) {
        websitesContainer newwebsite = new websitesContainer();
        newwebsite.uri = "http://www.test.com";
        newwebsite.page_num = 4;
        newwebsite.test_num = 8;
        newwebsite.id = Database.set_website(newwebsite).id;
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - seth urban
        autogen_main = new JFrame();
        label1 = new JLabel();
        textField1 = new JTextField();
        button1 = new JButton();
        CellConstraints cc = new CellConstraints();

        //======== autogen_main ========
        {
            Container autogen_mainContentPane = autogen_main.getContentPane();
            autogen_mainContentPane.setLayout(new FormLayout(
                new ColumnSpec[] {
                    new ColumnSpec(Sizes.dluX(69)),
                    FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
                    new ColumnSpec(Sizes.dluX(118)),
                    FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
                    FormFactory.DEFAULT_COLSPEC,
                    FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
                    FormFactory.DEFAULT_COLSPEC,
                    FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
                    FormFactory.DEFAULT_COLSPEC,
                    FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
                    FormFactory.DEFAULT_COLSPEC,
                    FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
                    FormFactory.DEFAULT_COLSPEC
                },
                new RowSpec[] {
                    FormFactory.DEFAULT_ROWSPEC,
                    FormFactory.LINE_GAP_ROWSPEC,
                    FormFactory.DEFAULT_ROWSPEC,
                    FormFactory.LINE_GAP_ROWSPEC,
                    FormFactory.DEFAULT_ROWSPEC
                }));

            //---- label1 ----
            label1.setText("Here's a label");
            autogen_mainContentPane.add(label1, cc.xy(1, 1));

            //---- textField1 ----
            textField1.setText("http://www.google.com");
            autogen_mainContentPane.add(textField1, cc.xy(3, 1));

            //---- button1 ----
            button1.setText("button");
            button1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button1MouseClicked(e);
                }
            });
            autogen_mainContentPane.add(button1, cc.xy(5, 1));
            autogen_main.pack();
            autogen_main.setLocationRelativeTo(autogen_main.getOwner());
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - seth urban
    private JFrame autogen_main;
    private JLabel label1;
    private JTextField textField1;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
