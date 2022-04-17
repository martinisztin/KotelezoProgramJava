module com.bkyzsa.heroeskotprog {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.bkyzsa.heroeskotprog to javafx.fxml;
    exports com.bkyzsa.heroeskotprog;
    exports com.bkyzsa.heroeskotprog.controllers;
    opens com.bkyzsa.heroeskotprog.controllers to javafx.fxml;
    exports com.bkyzsa.heroeskotprog.units;
    opens com.bkyzsa.heroeskotprog.units to javafx.fxml;
    exports com.bkyzsa.heroeskotprog.spells;
    opens com.bkyzsa.heroeskotprog.spells to javafx.fxml;
}