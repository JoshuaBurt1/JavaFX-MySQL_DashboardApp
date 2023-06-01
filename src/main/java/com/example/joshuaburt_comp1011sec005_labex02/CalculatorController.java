package com.example.joshuaburt_comp1011sec005_labex02;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CalculatorController {

    @FXML
    private TextField txtNum1;
    @FXML
    private TextField txtNum2;
    @FXML
    private TextField calcOutput;

    @FXML
    void add(/*ActionEvent event*/) { //format: /*ActionEvent event*/ required for .fxml onMouseClicked to work
        if (txtNum1.getText().matches("([0-9]+\\.[0-9]*)|([0-9]*\\.[0-9]+)|([0-9]+)") && txtNum2.getText().matches("([0-9]+\\.[0-9]*)|([0-9]*\\.[0-9]+)|([0-9]+)")) { //matches real # only
            double num1 = Double.parseDouble(txtNum1.getText());
            double num2 = Double.parseDouble(txtNum2.getText());
            double sum = num1+num2;
            calcOutput.setText(String.format("%.6f", sum)); //rounds to 6 decimal places
        }
        else if (txtNum1.getText().matches("[^0-9]") || txtNum2.getText().matches("[^0-9]")){ //matches not a number
            calcOutput.setText(String.format("")); //removes previous text
            calcOutput.setPromptText(String.format("Input must be a number.")); //changes promptText
            throw new IllegalArgumentException("Input must be a number.");
        }
        else{
            throw new IllegalArgumentException("Input must be a number.");
        }
    }
    public double testAdd(double num1, double num2) { //format: public --> accessed by CalculatorControllerTest; double --> returns a double value
        return num1+num2; //required for tests;
    }
    @FXML
    void subtract(/*ActionEvent event*/) {
        if (txtNum1.getText().matches("([0-9]+\\.[0-9]*)|([0-9]*\\.[0-9]+)|([0-9]+)") && txtNum2.getText().matches("([0-9]+\\.[0-9]*)|([0-9]*\\.[0-9]+)|([0-9]+)")) {
            double num1 = Double.parseDouble(txtNum1.getText());
            double num2 = Double.parseDouble(txtNum2.getText());
            double sum = num1-num2;
            calcOutput.setText(String.format("%.6f", sum)); //rounds to 6 decimal places
        }
        else if (txtNum1.getText().matches("[^0-9]") || txtNum2.getText().matches("[^0-9]")) {
            calcOutput.setText(String.format(""));
            calcOutput.setPromptText(String.format("Input must be a number."));
            throw new IllegalArgumentException("Input must be a number.");
        }
        else{
            throw new IllegalArgumentException("Input must be a number.");
        }
    }
    public double testSubtract(double num1, double num2) { //format: public --> accessed by CalculatorControllerTest; double --> returns a double value
        return num1-num2; //required for tests;
    }
    @FXML
    void multiply(/*ActionEvent event*/) {
        if (txtNum1.getText().matches("([0-9]+\\.[0-9]*)|([0-9]*\\.[0-9]+)|([0-9]+)") && txtNum2.getText().matches("([0-9]+\\.[0-9]*)|([0-9]*\\.[0-9]+)|([0-9]+)")) {
            double num1 = Double.parseDouble(txtNum1.getText());
            double num2 = Double.parseDouble(txtNum2.getText());
            double sum = num1*num2;
            calcOutput.setText(String.format("%.6f", sum)); //rounds to 6 decimal places
        }
        else if (txtNum1.getText().matches("[^0-9]") || txtNum2.getText().matches("[^0-9]")) {
            calcOutput.setText(String.format(""));
            calcOutput.setPromptText(String.format("Input must be a number."));
            throw new IllegalArgumentException("Input must be a number.");
        }
        else{
            throw new IllegalArgumentException("Input must be a number.");
        }
    }
    public double testMultiply(double num1, double num2) { //format: public --> accessed by CalculatorControllerTest; double --> returns a double value
        return num1*num2; //required for tests;
    }
    @FXML
    void divide(/*ActionEvent event*/) {
        if (txtNum1.getText().matches("([0-9]+\\.[0-9]*)|([0-9]*\\.[0-9]+)|([0-9]+)") && txtNum2.getText().matches("([0-9]+\\.[0-9]*)|([0-9]*\\.[0-9]+)|([0-9]+)")) {
            double num1 = Double.parseDouble(txtNum1.getText());
            double num2 = Double.parseDouble(txtNum2.getText());
            double sum = num1/num2;
            calcOutput.setText(String.format("%.6f", sum)); //rounds to 6 decimal places
        }
        else if (txtNum1.getText().matches("[^0-9]") || txtNum2.getText().matches("[^0-9]")){
            calcOutput.setText(String.format(""));
            calcOutput.setPromptText(String.format("Input must be a number."));
            throw new IllegalArgumentException("Input must be a number.");
        }
        else{
            throw new IllegalArgumentException("Input must be a number.");
        }
    }
    public double testDivide(double num1, double num2) { //format: public --> accessed by CalculatorControllerTest; double --> returns a double value
        return num1/num2; //required for tests;
    }
}