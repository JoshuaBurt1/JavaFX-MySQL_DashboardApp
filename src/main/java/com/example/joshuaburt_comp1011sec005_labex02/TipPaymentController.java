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
        private Integer id = null;
        private Double amount = null;
        private Double tipPercent = null;
        private Double tipAmount = null;
        private Double total = null;

        public TipLog() {
        }

        public TipLog(Integer id, Double amount, Double tipPercent, Double tipAmount, Double total) {
            this.id = id;
            this.amount = amount;
            this.tipPercent = tipPercent;
            this.tipAmount = tipAmount;
            this.total = total;
        }

        public Integer getId() {
            return id;
        }
        public void setId(Integer id) {
            this.id = id;
        }
        public Double getAmount() {
            return amount;
        }
        public void setAmount(Double amount) {
            this.amount = amount;
        }
        public Double getTipPercent() {
            return tipPercent;
        }
        public void setTipPercent(Double tipPercent) {
            this.tipPercent = tipPercent;
        }
        public Double getTipAmount() {
            return tipAmount;
        }
        public void setTipAmount(Double tipAmount) {
            this.tipAmount = tipAmount;
        }
        public Double getTotal() {
            return total;
        }
        public void setTotal(Double total) {
            this.total = total;
        }
    }
}
