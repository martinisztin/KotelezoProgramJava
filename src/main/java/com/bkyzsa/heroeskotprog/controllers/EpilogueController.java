package com.bkyzsa.heroeskotprog.controllers;

import com.bkyzsa.heroeskotprog.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Az epilogue.fxml controller osztálya. Ezekben a controllerekben találhatók a
 *  * grafikus kinézethez tartozó backend funkciók.
 * @author Isztin Martin
 * @version 1.0
 */

public class EpilogueController implements Initializable {

    @FXML
    Label nyertes;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(Main.gameData.gyoztes == null) {
             nyertes.setText("A csata döntetlennel zárult!");
        }
        else
            nyertes.setText((Main.gameData.gyoztes == Main.gameData.lplayer ? "1. játékos" : (Main.gameData.multiplayer ? "2. játékos" : "BOT játékos")) + " győzött!");
    }

    @FXML
    public void ujJatek(ActionEvent event) throws IOException {
        Main.gameData = null;

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("difficulty.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        stage.setScene(scene);
    }

    @FXML
    public void kilepes(ActionEvent event) {
        System.exit(0);
    }
}
