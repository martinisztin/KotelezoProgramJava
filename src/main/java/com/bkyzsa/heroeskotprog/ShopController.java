package com.bkyzsa.heroeskotprog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ShopController implements Initializable {

    @FXML
    public Label nehezseg;

    @FXML
    public Label egyenleg;

    private void setNehezsegLabel() {
        String nehezsegS = "";
        switch(Application.playerHos.getArany()) {
            case 1300: nehezsegS = "Könnyű";
            break;
            case 1000: nehezsegS = "Közepes";
            break;
            case 700: nehezsegS = "Nehéz";
            break;
        }

        nehezseg.setText("Válaszott nehézség - " + nehezsegS);
        egyenleg.setText("Egyenleg: " + Application.playerHos.getArany() + " arany");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setNehezsegLabel();
    }
}
