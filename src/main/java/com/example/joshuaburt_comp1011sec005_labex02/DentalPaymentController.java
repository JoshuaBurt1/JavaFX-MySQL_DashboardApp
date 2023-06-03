package com.example.joshuaburt_comp1011sec005_labex02;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DentalPaymentController implements Initializable { //implements initializable for ChoiceBox population at start

    @FXML
    private TextField name;
    @FXML
    private TextField address;
    @FXML
    private ChoiceBox<String> province;
    private String[] taxRate = {"Alberta-tax rate: 6%","Ontario-tax rate: 13%","Quebec-tax rate: 5%"};
    @FXML
    private CheckBox checkbox1;
    @FXML
    private CheckBox checkbox2;
    @FXML
    private CheckBox checkbox3;
    @FXML
    private RadioButton radiobutton1;
    @FXML
    private RadioButton radiobutton2;
    @FXML
    private RadioButton radiobutton3;
    @FXML
    private TextField calcOutput;

    @Override //this requires "implements Initializable"
    public void initialize(URL url, ResourceBundle resourceBundle) {
        province.getItems().addAll(taxRate);
    }
    @FXML
    void radioSelect1(/*ActionEvent event*/) { //allows only 1 radio button to be selected
        if (radiobutton1.isSelected()) {
            radiobutton2.setSelected(false);
            radiobutton3.setSelected(false);
        }
    }
    @FXML
    void radioSelect2(/*ActionEvent event*/) { //allows only 1 radio button to be selected
        if (radiobutton2.isSelected()) {
            radiobutton1.setSelected(false);
            radiobutton3.setSelected(false);
        }
    }
    @FXML
    void radioSelect3(/*ActionEvent event*/) { //allows only 1 radio button to be selected
        if(radiobutton3.isSelected()){
            radiobutton1.setSelected(false);
            radiobutton2.setSelected(false);
        }
    }
    @FXML
    void calculate(/*ActionEvent event*/) { //calculates total
        String name1 = "'"+name.getText()+"'";
        String address1 = "'"+address.getText()+"'";

        String intermediate = province.getValue();
        double tax = Integer.valueOf(intermediate.replaceAll("\\D+","")); //replaces non-integer characters with ""
        int service = 0;
        if(checkbox1.isSelected()){
            service+=20;
        }
        if(checkbox2.isSelected()){
            service+=75;
        }
        if(checkbox3.isSelected()){
            service+=150;
        }
        double discount = 0;
        if(radiobutton1.isSelected()){
            discount=0.90;
        }
        else if(radiobutton2.isSelected()){
            discount=0.90;
        }
        else if(radiobutton3.isSelected()){
            discount=1;
        }
        double total = (service*(1+tax/100))*discount;
        calcOutput.setText(String.format(name.getText()+", Total: $%.2f", total)); //rounds to 2 decimal places
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3308/transactions", "root", "");
            Statement statement = connection.createStatement();
            String sql = String.format("INSERT INTO dental (name, address, tax, discount, service, total) VALUES (%s,%s,%.2f,%.2f,%d,%.2f)",name1, address1, tax, discount, service, total);
            statement.executeUpdate(sql);
            System.out.println(total);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}