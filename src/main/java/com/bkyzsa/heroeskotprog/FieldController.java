package com.bkyzsa.heroeskotprog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class FieldController implements Initializable {

    @FXML
    GridPane field;

    @FXML
    ImageView player1;
    @FXML
    ImageView player2;
    @FXML
    ImageView foldmuvesImg;
    @FXML
    ImageView ijaszImg;
    @FXML
    ImageView griffImg;
    @FXML
    ImageView magusImg;
    @FXML
    ImageView saiyanImg;

    static Image hosimg;
    static Image foldmuves;
    static Image ijasz;
    static Image griff;
    static Image magus;
    static Image szupercsillagharcos;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hosimg = new Image("file:img/hos.png");
        foldmuves = new Image("file:img/foldmuves.png");
        ijasz = new Image("file:img/ijasz.png");
        griff = new Image("file:img/griff.png");
        magus = new Image("file:img/magus.png");
        szupercsillagharcos = new Image("file:img/szupercsillagharcos.png");

        player1.setImage(hosimg);
        player2.setImage(hosimg);

        foldmuvesImg.setImage(foldmuves);
        ijaszImg.setImage(ijasz);
        griffImg.setImage(griff);
        magusImg.setImage(magus);
        saiyanImg.setImage(szupercsillagharcos);

        Image notnull = new Image("file:img/notnull.png");
        for (Node node: field.getChildren()) {
            if(node instanceof ImageView) {
                ((ImageView)node).setImage(notnull);
            }

        }

        ImageView[] units = new ImageView[]{foldmuvesImg, ijaszImg, griffImg, magusImg, saiyanImg};

        for(int i = 0; i<5; i++) {
            if(Application.playerHos.egysegek[i].getDb() == 0) {
                units[i].setOpacity(0.5);
                units[i].setDisable(true);
            }
        }

    }

    @FXML
    public void chooseUnit(MouseEvent event) {

        int index;
        Image chooseable = new Image("file:img/chooseable.png");
        Image notnull = new Image("file:img/notnull.png");
        //melyik unitra kattintottunk?
        switch(((ImageView)event.getSource()).getId()) {
            case "foldmuvesImg" -> index = 0;
            case "ijaszImg" -> index = 1;
            case "griffImg" -> index = 2;
            case "magusImg" -> index = 3;
            case "saiyanImg" -> index = 4;
            default -> index = 5;
        }

        //hova kattintottal bro
        if(index == 5) {
            return;
        }

        //hova lehet rakni kijelolese
        for (Node node: field.getChildren()) {
            if(node instanceof ImageView) {
                if(((ImageView)node).getImage().getUrl().contains("notnull") && GridPane.getColumnIndex(node) != null && GridPane.getColumnIndex(node) < 2) {
                    ((ImageView)node).setImage(chooseable);
                }
                if(!((ImageView)node).getImage().getUrl().contains("notnull") && !((ImageView)node).getImage().getUrl().contains("chooseable") && GridPane.getColumnIndex(node) != null && GridPane.getColumnIndex(node) < 2) {
                    ((ImageView)node).setImage(notnull);
                }
            }
        }

    }


    private Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }

    @FXML
    public void test(ActionEvent event) {


    }




}
