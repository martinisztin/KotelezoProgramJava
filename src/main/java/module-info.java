module com.bkyzsa.heroeskotprog {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.bkyzsa.heroeskotprog to javafx.fxml;
    exports com.bkyzsa.heroeskotprog;
}