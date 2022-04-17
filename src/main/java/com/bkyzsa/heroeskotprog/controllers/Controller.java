package com.bkyzsa.heroeskotprog.controllers;

import com.bkyzsa.heroeskotprog.Main;
import com.bkyzsa.heroeskotprog.DataContainer;
import com.bkyzsa.heroeskotprog.units.Hos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    @FXML
    public CheckBox multiplayer;

    @FXML
    protected void difficultyKonnyu(ActionEvent event) throws IOException {
        Main.playerHos = new Hos(1300);
        loadNext(event, multiplayer.isSelected());
    }

    @FXML
    protected void difficultyKozepes(ActionEvent event) throws IOException {
        Main.playerHos = new Hos(1000);
        loadNext(event, multiplayer.isSelected());
    }

    @FXML
    protected void difficultyNehez(ActionEvent event) throws IOException {
        Main.playerHos = new Hos(700);
        loadNext(event, multiplayer.isSelected());
    }

    private void loadNext(ActionEvent event, boolean bMultiplayer) throws IOException {
        Main.gameData = new DataContainer(Main.playerHos, new Hos(bMultiplayer ? Main.playerHos.getArany() : 1000), bMultiplayer);
        Main.gameData.pakol = Main.gameData.lplayer;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("shop.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);

    }
}