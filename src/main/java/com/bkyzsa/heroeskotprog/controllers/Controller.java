package com.bkyzsa.heroeskotprog.controllers;

import com.bkyzsa.heroeskotprog.Application;
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
        Application.playerHos = new Hos(1300);
        loadNext(event, multiplayer.isSelected());
    }

    @FXML
    protected void difficultyKozepes(ActionEvent event) throws IOException {
        Application.playerHos = new Hos(1000);
        loadNext(event, multiplayer.isSelected());
    }

    @FXML
    protected void difficultyNehez(ActionEvent event) throws IOException {
        Application.playerHos = new Hos(700);
        loadNext(event, multiplayer.isSelected());
    }

    private void loadNext(ActionEvent event, boolean bMultiplayer) throws IOException {
        Application.gameData = new DataContainer(Application.playerHos, new Hos(bMultiplayer ? Application.playerHos.getArany() : 1000), bMultiplayer);
        Application.gameData.pakol = Application.gameData.lplayer;
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("shop.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);

    }
}