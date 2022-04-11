package com.bkyzsa.heroeskotprog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    @FXML
    public Label nehezseg;

    private Stage stage;

    @FXML
    protected void difficultyKonnyu(ActionEvent event) throws IOException {
        Application.playerHos = new Hos(1300);
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("shop.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);

        //valami
    }

    @FXML
    protected void difficultyKozepes(ActionEvent event) throws IOException {
        Application.playerHos = new Hos(1000);
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("shop.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);

        //valami
    }

    @FXML
    protected void difficultyNehez(ActionEvent event) throws IOException {
        Application.playerHos = new Hos(700);
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("shop.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        //valami
    }
}