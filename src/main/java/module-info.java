module com.example.joshuaburt_comp1011sec005_labex02 {

    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.joshuaburt_comp1011sec005_labex02 to javafx.fxml;
    exports com.example.joshuaburt_comp1011sec005_labex02;
}