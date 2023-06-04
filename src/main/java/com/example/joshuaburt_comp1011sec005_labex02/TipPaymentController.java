package com.example.joshuaburt_comp1011sec005_labex02;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class TipPaymentController implements Initializable {
    @FXML
    private TextField amount;
    @FXML
    private Slider slider;
    @FXML
    private Label tipNumber;
    @FXML
    private TextField tip;
    @FXML
    private TextField total;
    double tipX;

    @FXML
    public void calculate() {
        if (this.amount.getText().matches("([0-9]+\\.[0-9]*)|([0-9]*\\.[0-9]+)|([0-9]+)")) { //matches a real number
            double amountValue = Double.parseDouble(this.amount.getText());
            double sliderValue = this.slider.getValue();
            double tipPaid = amountValue * (sliderValue / 100.0);
            this.tip.setText(String.format("%.2f", tipPaid));
            double totalPaid = amountValue * (1.0 + sliderValue / 100.0);
            this.total.setText(String.format("%.2f", totalPaid));
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3308/transactions", "root", "");
                Statement statement = connection.createStatement();
                String sql = String.format("INSERT INTO tips (amount, tipPercent, tipAmount, total) VALUES (%.2f,%.2f,%.2f,%.2f)",amountValue,sliderValue,tipPaid,totalPaid);
                statement.executeUpdate(sql);
                System.out.println(totalPaid);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        } else {
            throw new IllegalArgumentException("Input must be a number.");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       slider.valueProperty().addListener(new ChangeListener<Number>() {
           @Override
           public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
               tipX = slider.getValue();
               tipNumber.setText(String.format("%.2f", tipX)+"%");
           }
       });
    }

    //Required to add data to TableView in Database Controller & database.fxml
    public static class TipLog {
        private String id = null;
        private String amount = null;
        private String tipPercent = null;
        private String tipAmount = null;
        private String total = null;

        public TipLog() {
        }

        public TipLog(String id, String amount, String tipPercent, String tipAmount, String total) {
            this.id = id;
            this.amount = amount;
            this.tipPercent = tipPercent;
            this.tipAmount = tipAmount;
            this.total = total;
        }

        public String getId() {
            return id;
        }
        public void setId(String id) {
            this.id = id;
        }
        public String getAmount() {
            return amount;
        }
        public void setAmount(String amount) {
            this.amount = amount;
        }
        public String getTipPercent() {
            return tipPercent;
        }
        public void setTipPercent(String tipPercent) {
            this.tipPercent = tipPercent;
        }
        public String getTipAmount() {
            return tipAmount;
        }
        public void setTipAmount(String tipAmount) {
            this.tipAmount = tipAmount;
        }
        public String getTotal() {
            return total;
        }
        public void setTotal(String total) {
            this.total = total;
        }
    }
}
