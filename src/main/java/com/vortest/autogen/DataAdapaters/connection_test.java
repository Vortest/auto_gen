package com.vortest.autogen.DataAdapaters;

import com.vortest.autogen.autogen_logging;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class connection_test {

    public void testconnection(){
        String dburl = "jdbc:mysql://localhost/autogendb";
        String dbclass = "com.mysql.jdbc.Driver";
        String query = "Select test_col from autogendb.test_table";
        String username = "root";
        String password = "";

        try{
            Class.forName(dbclass);
            Connection connection = DriverManager.getConnection(dburl, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                String tablename = resultSet.getString(1);
                System.out.println("Table name : " + tablename);
                autogen_logging.log("Table name : " + tablename);
            }
            connection.close();
        }catch (Exception e){
            autogen_logging.log(e.toString());
        }
    }
}
