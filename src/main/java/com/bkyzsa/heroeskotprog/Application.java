package com.bkyzsa.heroeskotprog;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("difficulty.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Heroes of Might & Magic - Duel");
        stage.setScene(scene);
        stage.show();
    }

    public static Hos playerHos;
    public static Hos comHos = new Hos(1000);

    public static Villamcsapas villamcsapas = new Villamcsapas();
    public static Tuzlabda tuzlabda = new Tuzlabda();
    public static Feltamasztas feltamasztas = new Feltamasztas();
    public static Kotelbilincs kotelbilincs = new Kotelbilincs();
    public static Harcimamor harcimamor = new Harcimamor();

    public static void main(String[] args) {
        launch();
    }
}