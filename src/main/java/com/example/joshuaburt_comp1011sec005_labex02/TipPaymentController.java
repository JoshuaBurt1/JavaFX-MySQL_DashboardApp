package com.example.joshuaburt_comp1011sec005_labex02;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

import java.net.URL;
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

    public TipPaymentController() {
    }

    @FXML
    void calculate() {
        if (this.amount.getText().matches("([0-9]+\\.[0-9]*)|([0-9]*\\.[0-9]+)|([0-9]+)")) {
            double amountValue = Double.parseDouble(this.amount.getText());
            double sliderValue = this.slider.getValue();
            double tipPaid = amountValue * (sliderValue / 100.0);
            this.tip.setText(String.format("%.2f", tipPaid));
            double totalPaid = amountValue * (1.0 + sliderValue / 100.0);
            this.total.setText(String.format("%.2f", totalPaid));
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
}
