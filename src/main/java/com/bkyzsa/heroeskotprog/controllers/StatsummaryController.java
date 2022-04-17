package com.bkyzsa.heroeskotprog.controllers;

import com.bkyzsa.heroeskotprog.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StatsummaryController implements Initializable {

    @FXML
    ImageView player1img;
    @FXML
    ImageView player2img;

    @FXML
    Label p1label;
    @FXML
    Label p2label;

    @FXML
    Label p1stats;
    @FXML
    Label p2stats;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image hos = new Image("file:img/hos.jpg");

        player1img.setImage(hos);
        player2img.setImage(hos);

        p1label.setText("1. játékos");
        p2label.setText((Main.gameData.multiplayer ? "2." : "BOT") + " játékos");

        p1stats.setText("Tulajdonságok eloszlása:\n" +
                "Támadás: " + Main.gameData.lplayer.getTamadas() + "\n" +
                "Védekezés: " + Main.gameData.lplayer.getVedekezes() + "\n" +
                "Varázserő: " + Main.gameData.lplayer.getVarazsero() + "\n" +
                "Tudás: " + Main.gameData.lplayer.getTudas() + "\n" +
                "Morál: " + Main.gameData.lplayer.getMoral() + "\n" +
                "Szerencse: " + Main.gameData.lplayer.getSzerencse() + "\n" +

                "\nVarázslatok:\n" +
                "Villámcsapás: " + (Main.gameData.lplayer.elerhetoVarazslatok[0].isVan() ? "van" : "nincs") + "\n" +
                "Tűzlabda: " + (Main.gameData.lplayer.elerhetoVarazslatok[1].isVan() ? "van" : "nincs") + "\n" +
                "Feltámasztás: " + (Main.gameData.lplayer.elerhetoVarazslatok[2].isVan() ? "van" : "nincs") + "\n" +
                "Kötélbilincs: " + (Main.gameData.lplayer.elerhetoVarazslatok[3].isVan() ? "van" : "nincs") + "\n" +
                "Harci mámor: " + (Main.gameData.lplayer.elerhetoVarazslatok[4].isVan() ? "van" : "nincs") + "\n"
        );

        p2stats.setText("Tulajdonságok eloszlása:\n" +
                "Támadás: " + Main.gameData.rplayer.getTamadas() + "\n" +
                "Védekezés: " + Main.gameData.rplayer.getVedekezes() + "\n" +
                "Varázserő: " + Main.gameData.rplayer.getVarazsero() + "\n" +
                "Tudás: " + Main.gameData.rplayer.getTudas() + "\n" +
                "Morál: " + Main.gameData.rplayer.getMoral() + "\n" +
                "Szerencse: " + Main.gameData.rplayer.getSzerencse() + "\n" +

                "\nVarázslatok:\n" +
                "Villámcsapás: " + (Main.gameData.rplayer.elerhetoVarazslatok[0].isVan() ? "van" : "nincs") + "\n" +
                "Tűzlabda: " + (Main.gameData.rplayer.elerhetoVarazslatok[1].isVan() ? "van" : "nincs") + "\n" +
                "Feltámasztás: " + (Main.gameData.rplayer.elerhetoVarazslatok[2].isVan() ? "van" : "nincs") + "\n" +
                "Kötélbilincs: " + (Main.gameData.rplayer.elerhetoVarazslatok[3].isVan() ? "van" : "nincs") + "\n" +
                "Harci mámor: " + (Main.gameData.rplayer.elerhetoVarazslatok[4].isVan() ? "van" : "nincs") + "\n"
        );


    }

    @FXML
    public void csata(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fight.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        stage.setScene(scene);
    }



}
