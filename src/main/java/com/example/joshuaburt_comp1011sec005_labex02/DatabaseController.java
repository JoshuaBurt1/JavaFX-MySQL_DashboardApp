package com.example.joshuaburt_comp1011sec005_labex02;


import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class DatabaseController implements Initializable {
    @FXML
    private Label showData;
    @FXML
    private TableView table;
    //make an array of tables for each tab in My SQL (DONE); each tab will have an insert data statement on button press (DONE)
    // In database.fxml --> scroll button to select SQL table (calc, car, dental, tip) & table view to see database
    String databaseName = "transactions";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    void connectButton(/*ActionEvent event*/){
        try {
            //Connect to SQL database
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3308/"+databaseName, "root", "");
            Statement statement = connection.createStatement();

            //Query 1 (single value: column count)
            ResultSet resultSet1 = statement.executeQuery("SELECT COUNT(*) as count FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'tips'");
            Integer columns = 0;
            while(resultSet1.next()){
                String columnCount = resultSet1.getString("count");
                columns = parseInt(columnCount);
            }
            System.out.println(columns);

            //Query 2 (array: column names)
            ResultSet resultSet2 = statement.executeQuery("SELECT COLUMN_NAME as columnNames FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = N'tips'");
            ArrayList<String> columnNames = new ArrayList<String>();
            while (resultSet2.next()) {
                //add to arraylist
                columnNames.add(resultSet2.getString("columnNames"));
            }
            System.out.println(columnNames);

            //Query 3 (array of arrays: column values)
            ArrayList<List> columnContents = new ArrayList<List>(); //creates an array of lists (arrays) --> to store column data (as an array)
            for (int i =0;i<columns;i++) {
                ArrayList<String> columnContentsPart = new ArrayList<>();//array for each column
                ResultSet resultSet3 = statement.executeQuery("SELECT " + columnNames.get(i) + " as content FROM tips");
                while (resultSet3.next()) { // resultSet3.next loops table number of row times
                    columnContentsPart.add(resultSet3.getString("content")); //all table information
                }
                columnContents.add(columnContentsPart);
            }
            //print table content
            System.out.println(columnContents);
            //print table column content
            for (int i = 0; i<columns; i++){
                System.out.println(columnContents.get(i));
            }
            //print table column cell content
            for (int i = 0; i<columns; i++){
                for (int j = 0; j<columnContents.get(0).size(); j++) {
                    System.out.println(columnContents.get(i).get(j));
                }
            }
            //insert data into .fxml Label
            String data = columnContents.get(0).get(1).toString();
            showData.setText(data);
            //insert data into .fxml TableView
            table.setPlaceholder(
                    new Label("No rows to display"));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
//        DatabaseConnection connectNow = new DatabaseConnection();
//        Connection connectDB = connectNow.getConnection();
//
//        String connectQuery = "SELECT * FROM categories";
//
//        try{
//            Statement statement = connectDB.createStatement();
//            ResultSet queryOutput = statement.executeQuery(connectQuery);
//            while(queryOutput.next()){
//                System.out.println(queryOutput.getString("c_name"));
//                showData.setText(queryOutput.getString("c_name"));
//            }
//        } catch (Exception e){
//            e.printStackTrace();
//        }

    //using class DatabaseConnection
    //    public Connection databaseLink;
//    public Connection getConnection(){
//        String databaseName = "biotech";
//        String databaseUser ="root";
//        String databasePassword = "";
//        String url = "jdbc:mysql://127.0.0.1:3308/" + databaseName;
//
//        try{
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        }
//        return databaseLink;
//    }
}
