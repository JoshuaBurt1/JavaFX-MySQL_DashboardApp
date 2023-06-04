package com.example.joshuaburt_comp1011sec005_labex02;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
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
        double totalPayment = (price - down);
        for (int i = 0; i < length; i++) {
            totalPayment *= (1 + interest / 100); // totalPayment should be multiplied interest rate every year
        }
        double monthPayment = (totalPayment) / 12;
        calcOutput.setText(String.format("Total: $%.2f; Monthly: $%.2f", totalPayment, monthPayment)); //rounds to 2 decimal places
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3308/transactions", "root", "");
            Statement statement = connection.createStatement();
            String sql = String.format("INSERT INTO car (price, down, length, interest, totalPayment) VALUES (%.2f,%.2f,%s,%.2f,%.2f)",price, down, length, interest, totalPayment);
            statement.executeUpdate(sql);
            System.out.println(totalPayment);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Required to add data to TableView in Database Controller & database.fxml
    public static class CarLog {
        private String id = null;
        private String price = null;
        private String down = null;
        private String length = null;
        private String interest = null;
        private String totalPayment = null;

        public CarLog() {
        }

        public CarLog(String id, String price, String down, String length, String interest, String totalPayment) {
            this.id = id;
            this.price = price;
            this.down = down;
            this.length = length;
            this.interest = interest;
            this.totalPayment = totalPayment;
        }
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getDown() {
            return down;
        }

        public void setDown(String down) {
            this.down = down;
        }

        public String getLength() {
            return length;
        }

        public void setLength(String length) {
            this.length = length;
        }

        public String getInterest() {
            return interest;
        }

        public void setInterest(String interest) {
            this.interest = interest;
        }

        public String getTotalPayment() {
            return totalPayment;
        }

        public void setTotalPayment(String totalPayment) {
            this.totalPayment = totalPayment;
        }
    }
}