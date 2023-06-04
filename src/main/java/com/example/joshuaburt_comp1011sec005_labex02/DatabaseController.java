package com.example.joshuaburt_comp1011sec005_labex02;

import javafx.beans.Observable;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.ArrayList;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class DatabaseController implements Initializable {

    @FXML
    private TableView table;
    @FXML
    private ComboBox<String> tableNames;
    String databaseName = "transactions";

    //1.  In database.sql.fxml --> get ComboBox current value
    @Override
    public void initialize(URL location, ResourceBundle resources) { //runs on application initialization
        //TODO: select databases in MySQL: SHOW DATABASES;
        try {
            //Connect to SQL database.sql
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3308/" + databaseName, "root", "");
            Statement statement = connection.createStatement();

            //Query 0 (array: table names)
            //SHOW TABLES;
            ResultSet resultSet0 = statement.executeQuery("SHOW TABLES");
            ObservableList<String> tables = FXCollections.observableArrayList(); //ComboBox (fx:id tableNames) requires ObservableList to fill
            while (resultSet0.next()) {
                //add to arraylist
                tables.add(resultSet0.getString("Tables_in_transactions"));
            }
            System.out.println(tables);
            tableNames.setItems(tables);
            //insert placeholder into .fxml TableView
            table.setPlaceholder(
                    new Label("No rows to display"));
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void connectButton(/*ActionEvent event*/){ //runs on connectButton click in database.fxml
        //clear all previous data from TableView table before next population
        table.getItems().clear();
        table.getColumns().clear();
        //Connect to SQL database.sql
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3308/"+databaseName, "root", "");
            Statement statement = connection.createStatement();

            //Query 1 (single value: column count)
            ResultSet resultSet1 = statement.executeQuery("SELECT COUNT(*) as count FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = '"+tableNames.getSelectionModel().getSelectedItem()+"'"); //queries ComboBox selection
            int columns = 0;
            while(resultSet1.next()){
                String columnCount = resultSet1.getString("count");
                columns = parseInt(columnCount);
            }
            System.out.println(columns);

            //Query 2 (array: column names)
            ResultSet resultSet2 = statement.executeQuery("DESCRIBE "+tableNames.getSelectionModel().getSelectedItem());
            ArrayList<String> columnNames = new ArrayList<String>();
            while (resultSet2.next()) {
                //add to arraylist
                columnNames.add(resultSet2.getString("Field"));
            }

            //Query 3 (array of arrays: column values)
            int dataNum = 0;
            ArrayList<List> columnContents = new ArrayList<List>(); //creates an array of lists (arrays) --> to store column data (as an array)
            for (int i =0;i<columns;i++) {
                ArrayList<String> columnContentsPart = new ArrayList<>();//array for each column
                ResultSet resultSet3 = statement.executeQuery("SELECT " + columnNames.get(i) + " as content FROM "+tableNames.getSelectionModel().getSelectedItem());
                while (resultSet3.next()) { // resultSet3.next loops table number of row times
                    columnContentsPart.add(resultSet3.getString("content")); //all table information
                    dataNum++;
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

            //add queried data to TableView table
            //this adds a columns to the table; alternate format: https://jenkov.com/tutorials/javafx/tableview.html#add-data-to-tableview
            int rowNum = dataNum/columns;
            for(int i = 0; i<columnContents.size();i++){
                TableColumn<TipPaymentController.TipLog, String> column =
                        new TableColumn<>(columnNames.get(i));
                column.setCellValueFactory(
                        new PropertyValueFactory<>(columnNames.get(i)));
                table.getColumns().add(column);
            }

            //TODO: APPLY GENERAL METHOD (NO IF STATEMENT)
            //inserts data into TableView table
            if (tableNames.getSelectionModel().getSelectedItem().toString().equals("tips")) {
                for (int i = 0; i < rowNum; i++) {
                    table.getItems().add(new TipPaymentController.TipLog(columnContents.get(0).get(i).toString(), columnContents.get(1).get(i).toString(), columnContents.get(2).get(i).toString(), columnContents.get(3).get(i).toString(), columnContents.get(4).get(i).toString()));
                }
            }
            if (tableNames.getSelectionModel().getSelectedItem().toString().equals("car")) {
                for (int i = 0; i < rowNum; i++) {
                    table.getItems().add(new CarPaymentController.CarLog(columnContents.get(0).get(i).toString(), columnContents.get(1).get(i).toString(), columnContents.get(2).get(i).toString(), columnContents.get(3).get(i).toString(), columnContents.get(4).get(i).toString(), columnContents.get(5).get(i).toString()));
                }
            }
            if (tableNames.getSelectionModel().getSelectedItem().toString().equals("dental")) {
                for (int i = 0; i < rowNum; i++) {
                    table.getItems().add(new DentalPaymentController.DentalLog(columnContents.get(0).get(i).toString(), columnContents.get(1).get(i).toString(), columnContents.get(2).get(i).toString(), columnContents.get(3).get(i).toString(), columnContents.get(4).get(i).toString(), columnContents.get(5).get(i).toString(), columnContents.get(6).get(i).toString()));
                }
            }
            if (tableNames.getSelectionModel().getSelectedItem().toString().equals("calculations")) {
                for (int i = 0; i < rowNum; i++) {
                    table.getItems().add(new CalculatorController.CalculationsLog(columnContents.get(0).get(i).toString(), columnContents.get(1).get(i).toString(), columnContents.get(2).get(i).toString(), columnContents.get(3).get(i).toString(), columnContents.get(4).get(i).toString()));
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
