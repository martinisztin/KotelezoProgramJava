package com.bkyzsa.heroeskotprog;

import com.bkyzsa.heroeskotprog.units.Hos;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * A program fő osztálya, ahonnan elindul.
 * @author Isztin Martin
 * @version 1.0
 */

public class Main extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("difficulty.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Heroes of Might & Magic - Duel");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static Hos playerHos;

    public static DataContainer gameData;

    /**
     * A főosztály main metódusa
     * @param args  konzolos bemeneti argumentumok
     */
    public static void main(String[] args) {
        launch();
    }
}