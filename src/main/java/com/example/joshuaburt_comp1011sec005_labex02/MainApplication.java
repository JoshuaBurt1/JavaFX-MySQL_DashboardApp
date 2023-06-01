package com.example.joshuaburt_comp1011sec005_labex02;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("mainView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 500); //Set application viewport size
        stage.setTitle("Various Calculators"); //This is the task bar title
        //Change top left icon
        //1. sets icon via URL
        stage.getIcons().add(new Image("https://cdn2.iconfinder.com/data/icons/ios7-inspired-mac-icon-set/512/Calculator_512.png"));
        //2. sets icon to relative path (stored in program file)
        //Image image = new Image("file:images/car.png");
        //stage.getIcons().add(image);
        //3. sets icon to absolute path
        //stage.getIcons().add(new Image("C:\\Users\\Josh\\IdeaProjects\\JoshuaBurt_COMP1011Sec005_LabEx02-main\\src\\main\\resources\\com\\example\\joshuaburt_comp1011sec005_labex02\\Images\\car.png"));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}