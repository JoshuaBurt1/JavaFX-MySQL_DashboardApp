package com.example.joshuaburt_comp1011sec005_labex02;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

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
            String type = "'add'";
            System.out.println(type);
            double num1 = Double.parseDouble(txtNum1.getText());
            double num2 = Double.parseDouble(txtNum2.getText());
            double output = num1+num2;
            calcOutput.setText(String.format("%.6f", output)); //rounds to 6 decimal places
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3308/transactions", "root", "");
                Statement statement = connection.createStatement();
                String sql = String.format("INSERT INTO calculations (type, variable1, variable2, output) VALUES (%s,%.6f,%.6f,%.6f)",type,num1,num2,output);
                statement.executeUpdate(sql);
                System.out.println(output);
            }
            catch (Exception e){
                e.printStackTrace();
            }
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
            String type = "'subtract'";
            double num1 = Double.parseDouble(txtNum1.getText());
            double num2 = Double.parseDouble(txtNum2.getText());
            double output = num1-num2;
            calcOutput.setText(String.format("%.6f", output)); //rounds to 6 decimal places
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3308/transactions", "root", "");
                Statement statement = connection.createStatement();
                String sql = String.format("INSERT INTO calculations (type, variable1, variable2, output) VALUES (%s,%.6f,%.6f,%.6f)",type,num1,num2,output);
                statement.executeUpdate(sql);
                System.out.println(output);
            }
            catch (Exception e){
                e.printStackTrace();
            }
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
            String type = "'product'";
            double num1 = Double.parseDouble(txtNum1.getText());
            double num2 = Double.parseDouble(txtNum2.getText());
            double output = num1*num2;
            calcOutput.setText(String.format("%.6f", output)); //rounds to 6 decimal places
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3308/transactions", "root", "");
                Statement statement = connection.createStatement();
                String sql = String.format("INSERT INTO calculations (type, variable1, variable2, output) VALUES (%s,%.6f,%.6f,%.6f)",type,num1,num2,output);
                statement.executeUpdate(sql);
                System.out.println(output);
            }
            catch (Exception e){
                e.printStackTrace();
            }
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
            String type = "'quotient'";
            double num1 = Double.parseDouble(txtNum1.getText());
            double num2 = Double.parseDouble(txtNum2.getText());
            double output = num1/num2;
            calcOutput.setText(String.format("%.6f", output)); //rounds to 6 decimal places
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3308/transactions", "root", "");
                Statement statement = connection.createStatement();
                String sql = String.format("INSERT INTO calculations (type, variable1, variable2, output) VALUES (%s,%.6f,%.6f,%.6f)",type,num1,num2,output);
                statement.executeUpdate(sql);
                System.out.println(output);
            }
            catch (Exception e){
                e.printStackTrace();
            }
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

    //Required to add data to TableView in Database Controller & database.fxml
    public static class CalculationsLog {
        private String id = null;
        private String type = null;
        private String variable1 = null;
        private String variable2 = null;
        private String output = null;

        public CalculationsLog() {
        }

        public CalculationsLog(String id, String type, String variable1, String variable2, String output) {
            this.id = id;
            this.type = type;
            this.variable1 = variable1;
            this.variable2 = variable2;
            this.output = output;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getVariable1() {
            return variable1;
        }

        public void setVariable1(String variable1) {
            this.variable1 = variable1;
        }

        public String getVariable2() {
            return variable2;
        }

        public void setVariable2(String variable2) {
            this.variable2 = variable2;
        }

        public String getOutput() {
            return output;
        }

        public void setOutput(String output) {
            this.output = output;
        }
    }
}