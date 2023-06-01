package com.example.joshuaburt_comp1011sec005_labex02;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CarPaymentController implements Initializable { //implements initializable for ChoiceBox population at start

    @FXML
    private TextField txtNum1;
    @FXML
    private TextField txtNum2;
    @FXML
    private ChoiceBox<String> choiceNum3;
    private String[] year = {"1","2","3","4","5","6","7","8"};
    @FXML
    private TextField txtNum4;
    @FXML
    private TextField calcOutput;

    @Override //this requires "implements Initializable"
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceNum3.getItems().addAll(year);
    }
    @FXML
    void calculate(/*ActionEvent event*/) {
        double price = Double.parseDouble(txtNum1.getText());
        double down = Double.parseDouble(txtNum2.getText());
        int length = Integer.parseInt((String) choiceNum3.getValue());
        double interest = Double.parseDouble(txtNum4.getText());
        double totalPayment = (price-down);
        for(int i = 0; i < length; i++){
            totalPayment*=(1+interest/100); // totalPayment should be multiplied interest rate every year
        }
        double monthPayment = (totalPayment)/12;
        calcOutput.setText(String.format("Total: $%.2f; Monthly: $%.2f", totalPayment, monthPayment)); //rounds to 2 decimal places
    }
}