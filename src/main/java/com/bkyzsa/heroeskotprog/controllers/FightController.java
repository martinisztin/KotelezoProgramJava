package com.bkyzsa.heroeskotprog.controllers;

import com.bkyzsa.heroeskotprog.Coords;
import com.bkyzsa.heroeskotprog.Main;
import com.bkyzsa.heroeskotprog.units.Egyseg;
import com.bkyzsa.heroeskotprog.units.Hos;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.ColorInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class FightController implements Initializable {

    @FXML
    GridPane field;

    @FXML
    GridPane nexts;

    @FXML
    Label info;

    @FXML
    Label kor;

    @FXML
    Button ltamadas;
    @FXML
    Button lvarakozas;
    @FXML
    Button lvarazslas;

    @FXML
    Button rtamadas;
    @FXML
    Button rvarakozas;
    @FXML
    Button rvarazslas;

    Button[] lactions;
    Button[] ractions;

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

    @FXML
    VBox lfeltamaszto;

    @FXML
    Label lpley;
    @FXML
    Label rpley;
    @FXML
    Label details;
    @FXML
    Label details1;
    @FXML
    Label lmana;
    @FXML
    Label rmana;


    Image hosimg;
    Image foldmuves;
    Image ijasz;
    Image griff;
    Image magus;
    Image szupercsillagharcos;
    Image notnull;

    static Blend lepheto;
    static Blend tamadhato;
    static Blend valasztott;

    static Blend p1szin;
    static Blend p2szin;
    boolean visszatamadasKozel = false;

    @FXML
    static ImageView lfoldmuves;
    @FXML
    static ImageView lijasz;
    @FXML
    static ImageView lgriff;
    @FXML
    static ImageView lmagus;
    @FXML
    static ImageView lszupercsillagharcos;

    @FXML
    static ImageView rfoldmuves;
    @FXML
    static ImageView rijasz;
    @FXML
    static ImageView rgriff;
    @FXML
    static ImageView rmagus;
    @FXML
    static ImageView rszupercsillagharcos;

    @FXML
    Button lvillamcsapas;
    @FXML
    Button ltuzlabda;
    @FXML
    Button lfeltamasztas;
    @FXML
    Button lkotelbilincs;
    @FXML
    Button lharcimamor;

    @FXML
    Button rvillamcsapas;
    @FXML
    Button rtuzlabda;
    @FXML
    Button rfeltamasztas;
    @FXML
    Button rkotelbilincs;
    @FXML
    Button rharcimamor;

    @FXML
    VBox lvarazslattarto;
    @FXML
    VBox rvarazslattarto;

    @FXML
    Label spellStatus;

    Button[] lvarazslatok;
    Button[] rvarazslatok;
    ImageView[] felelesztos;

    @FXML
    Label backattack;

    int tuzX;
    int tuzY;

    int[] lplayerUnits;
    int[] rplayerUnits;
    Egyseg[][] startMap;

    private int currentSorszam = -1;
    private int maxSorszam; //idk sors
    private Egyseg[] currentsr;

    boolean hosTamad = false;
    boolean unitTamad = false;
    boolean villamcsapasTamad = false;
    boolean tuzlabdaTamad = false;
    boolean feltamasztasTamad = false;      //bár ő nem támad
    boolean kotelblilincsTamad = false;
    boolean harcimamorTamad = false;        //ő se támad :D


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        lvillamcsapas.setText("Villámcsapás");
        ltuzlabda.setText("Tűzlabda");
        lfeltamasztas.setText("Feltámasztás");
        lkotelbilincs.setText("Kötélbilincs");
        lharcimamor.setText("Harci mámor");

        rvillamcsapas.setText("Villámcsapás");
        rtuzlabda.setText("Tűzlabda");
        rfeltamasztas.setText("Feltámasztás");
        rkotelbilincs.setText("Kötélbilincs");
        rharcimamor.setText("Harci mámor");



        lvarazslatok = new Button[] {lvillamcsapas, ltuzlabda, lfeltamasztas, lkotelbilincs, lharcimamor};
        rvarazslatok = new Button[] {rvillamcsapas, rtuzlabda, rfeltamasztas, rkotelbilincs, rharcimamor};

        for (Button button : rvarazslatok) {
            button.setDisable(true);
        }
            rvarazslattarto.setOpacity(0);


        for (Button button : lvarazslatok) {
            button.setDisable(true);
        }
        lvarazslattarto.setOpacity(0);


        notnull = new Image("file:img/notnull.png");

        lactions = new Button[]{ltamadas, lvarakozas, lvarazslas};
        ractions = new Button[]{rtamadas, rvarakozas, rvarazslas};


        ColorAdjust monochrome = new ColorAdjust();
        monochrome.setSaturation(-1.0);

        //ő lesz a léphető
        lepheto = new Blend(BlendMode.MULTIPLY, monochrome, new ColorInput(0,0,52,45,Color.LIGHTGREEN));

        //ő lesz a támadható
        tamadhato = new Blend(BlendMode.MULTIPLY, monochrome, new ColorInput(0,0,52,45,Color.RED));

        //ő lesz a selected
        valasztott = new Blend(BlendMode.MULTIPLY, monochrome, new ColorInput(0,0,52,45,Color.LIGHTYELLOW));


        p1szin = new Blend(BlendMode.OVERLAY, monochrome, new ColorInput(0,0,52,45,Color.BLUE));
        p2szin = new Blend(BlendMode.OVERLAY, monochrome, new ColorInput(0,0,52,45,Color.ORANGE));

        //eltároljuk az eredetit hogy tudjunk felfele oszlopot játszani


        lplayerUnits = new int[Main.gameData.lplayer.egysegek.length];
        rplayerUnits = new int[Main.gameData.rplayer.egysegek.length];
        startMap = new Egyseg[10][12];

        for (int i = 0; i < 5; i++) {
            int rakosGeci = Main.gameData.lplayer.egysegek[i].getDb(), anyad = Main.gameData.rplayer.egysegek[i].getDb();
            lplayerUnits[i] = rakosGeci;
            rplayerUnits[i] = anyad;
        }

        for(int i = 0; i < 10; i++) {
            for (int j = 0; j < 12; j++) {
                startMap[i][j] = Main.gameData.map[i][j];
            }
        }

        for (Node node: field.getChildren()) {
            if(node instanceof ImageView) {
                ((ImageView)node).setImage(notnull);
                //node.effectProperty().set(lepheto);
                //node.setOpacity(0.5);
            }

        }

        lpley.setText("1. játékos");
        lpley.setTextFill(Color.web("BLUE"));
        rpley.setText(Main.gameData.multiplayer ? "2. játékos" : "BOT játékos");
        rpley.setTextFill(Color.web("ORANGE"));

        for(Button a: lactions) {
            a.setDisable(true);
        }

        for(Button a: ractions) {
            a.setDisable(true);
            if(!Main.gameData.multiplayer) {
                a.setOpacity(0);
            }
        }

        hosimg = new Image("file:img/hos.jpg");

        foldmuves = new Image("file:img/foldmuves.png");
        ijasz = new Image("file:img/ijasz.png");
        griff = new Image("file:img/griff.png");
        magus = new Image("file:img/magus.png");
        szupercsillagharcos = new Image("file:img/szupercsillagharcos.png");

        foldmuvesImg.setImage(foldmuves);
        ijaszImg.setImage(ijasz);
        griffImg.setImage(griff);
        magusImg.setImage(magus);
        saiyanImg.setImage(szupercsillagharcos);

        felelesztos = new ImageView[]{foldmuvesImg, ijaszImg, griffImg, magusImg, saiyanImg};

        for(ImageView iw : felelesztos) {
            iw.setDisable(true);
            iw.setOpacity(0.5);
        }

        lfeltamaszto.setOpacity(0);
        lfeltamaszto.setDisable(true);


        player1.setImage(hosimg);
        player2.setImage(hosimg);

        lmana.setText(Main.gameData.lplayer.getMana() + " mana");
        rmana.setText(Main.gameData.rplayer.getMana() + " mana");

        kor.setText("1. kör");

       /* foldmuvesImg.setImage(foldmuves);
        ijaszImg.setImage(ijasz);
        griffImg.setImage(griff);
        magusImg.setImage(magus);
        saiyanImg.setImage(szupercsillagharcos);*/

        Main.gameData.print();

        for(Node node: field.getChildren()) {
            if(node instanceof ImageView) {
                for(Egyseg e : Main.gameData.lplayer.egysegek) {
                    if(GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) != null && GridPane.getColumnIndex(node) < 2
                            && Main.gameData.map[GridPane.getRowIndex(node)][GridPane.getColumnIndex(node)] != null
                            && Main.gameData.map[GridPane.getRowIndex(node)][GridPane.getColumnIndex(node)].getNev().contains(e.getNev())) {

                        ((ImageView)node).setImage(new Image("file:img/" + e.getNev() + ".png"));
                        node.setId("l" + e.getNev());

                    }

                }
            }
        }

        for(Node node: field.getChildren()) {
            if(node instanceof ImageView) {
                for(Egyseg e : Main.gameData.rplayer.egysegek) {
                    if(GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) != null
                            && Main.gameData.map[GridPane.getRowIndex(node)][GridPane.getColumnIndex(node)] != null && GridPane.getColumnIndex(node) > 9
                            && Main.gameData.map[GridPane.getRowIndex(node)][GridPane.getColumnIndex(node)].getNev().contains(e.getNev())) {

                        ((ImageView)node).setImage(new Image("file:img/" + e.getNev() + ".png"));
                        node.setId("r" + e.getNev());

                    }

                }
            }
        }

        currentsr = handleSorrend(sorrendezo());

        try {
            kovetkezo();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void hoverOnSpells(MouseEvent event) {
        switch(((Node)event.getSource()).getId()) {
            case "lvillamcsapas", "rvillamcsapas" -> spellStatus.setText(Main.playerHos.elerhetoVarazslatok[0].toString() + "");
            case "ltuzlabda", "rtuzlabda" -> spellStatus.setText(Main.playerHos.elerhetoVarazslatok[1].toString() + "");
            case "lfeltamasztas", "rfeltamasztas" -> spellStatus.setText(Main.playerHos.elerhetoVarazslatok[2].toString() + "");
            case "lkotelbilincs", "rkotelbilincs" -> spellStatus.setText(Main.playerHos.elerhetoVarazslatok[3].toString() + "");
            case "lharcimamor", "rharcimamor" -> spellStatus.setText(Main.playerHos.elerhetoVarazslatok[4].toString() + "");
        }
    }

    private Egyseg[] sorrendezo() {
        Egyseg[] tsorrendall = new Egyseg[10];

        System.arraycopy(Main.gameData.lplayer.egysegek, 0, tsorrendall, 0, 5);
        System.arraycopy(Main.gameData.rplayer.egysegek, 0, tsorrendall, 5, 5);

        for (Egyseg egyseg : tsorrendall) {
            if (egyseg != null) {
                if (egyseg.getGazda() == Main.gameData.lplayer) {
                    egyseg.setKezdemenyezes(egyseg.getKezdemenyezes() + Main.gameData.lplayer.getMoral());
                    //System.out.println(tsorrendall[i].getNev() + tsorrendall[i].getKezdemenyezes());
                }
                if (egyseg.getGazda() == Main.gameData.rplayer) {
                    egyseg.setKezdemenyezes(egyseg.getKezdemenyezes() + Main.gameData.rplayer.getMoral());
                }
            }
        }

        Arrays.sort(tsorrendall, Comparator.reverseOrder());

        Egyseg[] tsorrend = new Egyseg[10];

        int k = 0;
        for(Egyseg e : tsorrendall) {
            if(e.getDb() != 0) {
                tsorrend[k] = e;
                k++;
            }
        }

        int count = 0;

        for(Egyseg e : tsorrend) {
            if(e != null) {
                //System.out.println(e.getNev() + " (" + (e.getGazda() == Application.gameData.lplayer ? "p1" : "p2") + ") : " + e.getKezdemenyezes());
                count++;
            }

        }

        Egyseg[] vegso = new Egyseg[count];

        System.arraycopy(tsorrend, 0, vegso, 0, count);

        return vegso;
    }

    private Egyseg[] handleSorrend(Egyseg[] sorrend) {
        int db = 0;

        for (Egyseg e : sorrend) {
            if(e != null && e.getOsszHp() != 0) {
                db++;
            }
        }
        Egyseg[] vegso = new Egyseg[db];
        int k = 0;

        for(Egyseg e : sorrend) {
            if(e != null && e.getOsszHp() != 0) {
                vegso[k] = e;
                k++;
            }
        }

        for(Egyseg e : vegso) {
            if(e != null)
            System.out.println(e.getNev());
        }

        maxSorszam = k;
        //System.out.println(maxSorszam);

        return vegso;
    }

    private void printSorrend(Egyseg[] vegso) {

        //NULL EM ALL
        for(Node node : nexts.getChildren()) {
            if(node instanceof ImageView && GridPane.getRowIndex(node) != null) {
                ((ImageView)node).setImage(notnull);
                node.setEffect(null);
            }
        }

        for(Node node : nexts.getChildren()) {
            for(int i = currentSorszam; i<vegso.length; i++) {
                if(node instanceof ImageView) {
                    if(GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) == i - currentSorszam && vegso[i].getDb() != 0) {
                        ((ImageView)node).setImage(new Image("file:img/" + vegso[i].getNev() + ".png"));

                        if(vegso[i].getGazda() == Main.gameData.lplayer) {
                            node.setEffect(p1szin);
                        }
                        else {
                            node.setEffect(p2szin);
                        }
                    }
                }
            }
        }
    }

    @FXML
    public void hoverUnit(MouseEvent event) {
        String output = "";

        if(((ImageView)event.getSource()).getId() != null) {
            switch(((ImageView)event.getSource()).getId()) {
                case "lfoldmuves" -> output = Main.gameData.lplayer.egysegek[0].getDb() + " db Földműves\nÉleterejük: "
                        + Main.gameData.lplayer.egysegek[0].getOsszHp() + ", ebből "
                        +  ((Main.gameData.lplayer.egysegek[0].getOsszHp() / Main.gameData.lplayer.egysegek[0].getHp()) == Main.gameData.lplayer.egysegek[0].getDb()
                        ? "mindenki, azaz " + Main.gameData.lplayer.egysegek[0].getDb() + " db sértetlen" : Main.gameData.lplayer.egysegek[0].getDb() - 1 + " sértetlen, és egynek "
                        + Main.gameData.lplayer.egysegek[0].getOsszHp() % Main.gameData.lplayer.egysegek[0].getHp() + " életereje van")
                        + "\nSebzés: " + Main.gameData.lplayer.egysegek[0].getSebzesinterval1() + "-" + Main.gameData.lplayer.egysegek[0].getSebzesinterval2();

                case "lijasz" -> output = Main.gameData.lplayer.egysegek[1].getDb() + " db Íjász\nÉleterejük: "
                        + Main.gameData.lplayer.egysegek[1].getOsszHp() + ", ebből "
                        +  ((Main.gameData.lplayer.egysegek[1].getOsszHp() / Main.gameData.lplayer.egysegek[1].getHp()) == Main.gameData.lplayer.egysegek[1].getDb()
                        ? "mindenki, azaz " + Main.gameData.lplayer.egysegek[1].getDb() + " db sértetlen" : Main.gameData.lplayer.egysegek[1].getDb() - 1+ " sértetlen, és egynek "
                        + Main.gameData.lplayer.egysegek[1].getOsszHp() % Main.gameData.lplayer.egysegek[1].getHp() + " életereje van")
                        + "\nSebzés: " + Main.gameData.lplayer.egysegek[1].getSebzesinterval1() + "-" + Main.gameData.lplayer.egysegek[1].getSebzesinterval2();


                case "lgriff" -> output = Main.gameData.lplayer.egysegek[2].getDb() + " db Griff\nÉleterejük: "
                        + Main.gameData.lplayer.egysegek[2].getOsszHp() + ", ebből "
                        +  ((Main.gameData.lplayer.egysegek[2].getOsszHp() / Main.gameData.lplayer.egysegek[2].getHp()) == Main.gameData.lplayer.egysegek[2].getDb()
                        ? "mindenki, azaz " + Main.gameData.lplayer.egysegek[2].getDb() + " db sértetlen" : Main.gameData.lplayer.egysegek[2].getDb() - 1 + " sértetlen, és egynek"
                        + Main.gameData.lplayer.egysegek[2].getOsszHp() % Main.gameData.lplayer.egysegek[2].getHp() + " életereje van")
                        + "\nSebzés: " + Main.gameData.lplayer.egysegek[2].getSebzesinterval1() + "-" + Main.gameData.lplayer.egysegek[2].getSebzesinterval2();

                case "lmagus" -> output = Main.gameData.lplayer.egysegek[3].getDb() + " db Mágus\nÉleterejük: "
                        + Main.gameData.lplayer.egysegek[3].getOsszHp() + ", ebből "
                        +  ((Main.gameData.lplayer.egysegek[3].getOsszHp() / Main.gameData.lplayer.egysegek[3].getHp()) == Main.gameData.lplayer.egysegek[3].getDb()
                        ? "mindenki, azaz " + Main.gameData.lplayer.egysegek[3].getDb() + " db sértetlen" : Main.gameData.lplayer.egysegek[3].getDb() - 1 + " sértetlen, és egynek "
                        + Main.gameData.lplayer.egysegek[3].getOsszHp() % Main.gameData.lplayer.egysegek[3].getHp() + " életereje van")
                        + "\nSebzés: " + Main.gameData.lplayer.egysegek[3].getSebzesinterval1() + "-" + Main.gameData.lplayer.egysegek[3].getSebzesinterval2();

                case "lszupercsillagharcos" -> output = Main.gameData.lplayer.egysegek[4].getDb() + " db Szupercsillagharcos\nÉleterejük: "
                        + Main.gameData.lplayer.egysegek[4].getOsszHp() + ", ebből "
                        +  ((Main.gameData.lplayer.egysegek[4].getOsszHp() / Main.gameData.lplayer.egysegek[4].getHp()) == Main.gameData.lplayer.egysegek[4].getDb()
                        ? "mindenki, azaz " + Main.gameData.lplayer.egysegek[4].getDb() + " db sértetlen" : Main.gameData.lplayer.egysegek[4].getDb() - 1 + " sértetlen, és egynek "
                        + Main.gameData.lplayer.egysegek[4].getOsszHp() % Main.gameData.lplayer.egysegek[4].getHp() + " életereje van")
                        + "\nSebzés: " + Main.gameData.lplayer.egysegek[4].getSebzesinterval1() + "-" + Main.gameData.lplayer.egysegek[4].getSebzesinterval2();


                case "rfoldmuves" -> output = Main.gameData.rplayer.egysegek[0].getDb() + " db Földműves\nÉleterejük: "
                        + Main.gameData.rplayer.egysegek[0].getOsszHp() + ", ebből "
                        +  ((Main.gameData.rplayer.egysegek[0].getOsszHp() / Main.gameData.rplayer.egysegek[0].getHp()) == Main.gameData.rplayer.egysegek[0].getDb()
                        ? "mindenki, azaz " + Main.gameData.rplayer.egysegek[0].getDb() + " db sértetlen" : Main.gameData.rplayer.egysegek[0].getDb() - 1 + " sértetlen, és egynek "
                        + Main.gameData.rplayer.egysegek[0].getOsszHp() % Main.gameData.rplayer.egysegek[0].getHp() + " életereje van")
                        + "\nSebzés: " + Main.gameData.rplayer.egysegek[0].getSebzesinterval1() + "-" + Main.gameData.rplayer.egysegek[0].getSebzesinterval2();

                case "rijasz" -> output = Main.gameData.rplayer.egysegek[1].getDb() + " db Íjász\nÉleterejük: "
                        + Main.gameData.rplayer.egysegek[1].getOsszHp() + ", ebből "
                        +  ((Main.gameData.rplayer.egysegek[1].getOsszHp() / Main.gameData.rplayer.egysegek[1].getHp()) == Main.gameData.rplayer.egysegek[1].getDb()
                        ? "mindenki, azaz " + Main.gameData.rplayer.egysegek[1].getDb() + " db sértetlen" : Main.gameData.rplayer.egysegek[1].getDb() - 1 + " sértetlen, és egynek "
                        + Main.gameData.rplayer.egysegek[1].getOsszHp() % Main.gameData.rplayer.egysegek[1].getHp() + " életereje van")
                        + "\nSebzés: " + Main.gameData.rplayer.egysegek[1].getSebzesinterval1() + "-" + Main.gameData.rplayer.egysegek[1].getSebzesinterval2();

                case "rgriff" -> output = Main.gameData.rplayer.egysegek[2].getDb() + " db Griff\nÉleterejük: "
                        + Main.gameData.rplayer.egysegek[2].getOsszHp() + ", ebből "
                        +  ((Main.gameData.rplayer.egysegek[2].getOsszHp() / Main.gameData.rplayer.egysegek[2].getHp()) == Main.gameData.rplayer.egysegek[2].getDb()
                        ? "mindenki, azaz " + Main.gameData.rplayer.egysegek[2].getDb() + " db sértetlen" : Main.gameData.rplayer.egysegek[2].getDb() - 1 + " sértetlen, és egynek "
                        + Main.gameData.rplayer.egysegek[2].getOsszHp() % Main.gameData.rplayer.egysegek[2].getHp() + " életereje van")
                        + "\nSebzés: " + Main.gameData.rplayer.egysegek[2].getSebzesinterval1() + "-" + Main.gameData.rplayer.egysegek[2].getSebzesinterval2();

                case "rmagus" -> output = Main.gameData.rplayer.egysegek[3].getDb() + " db Mágus\nÉleterejük: "
                        + Main.gameData.rplayer.egysegek[3].getOsszHp() + ", ebből "
                        +  ((Main.gameData.rplayer.egysegek[3].getOsszHp() / Main.gameData.rplayer.egysegek[3].getHp()) == Main.gameData.rplayer.egysegek[3].getDb()
                        ? "mindenki, azaz " + Main.gameData.rplayer.egysegek[3].getDb() + " db sértetlen" : Main.gameData.rplayer.egysegek[3].getDb() - 1 + " sértetlen, és egynek "
                        + Main.gameData.rplayer.egysegek[3].getOsszHp() % Main.gameData.rplayer.egysegek[3].getHp() + " életereje van")
                        + "\nSebzés: " + Main.gameData.rplayer.egysegek[3].getSebzesinterval1() + "-" + Main.gameData.rplayer.egysegek[3].getSebzesinterval2();

                case "rszupercsillagharcos" -> output = Main.gameData.rplayer.egysegek[4].getDb() + " db Szupercsillagharcos\nÉleterejük: "
                        + Main.gameData.rplayer.egysegek[4].getOsszHp() + ", ebből "
                        +  ((Main.gameData.rplayer.egysegek[4].getOsszHp() / Main.gameData.rplayer.egysegek[4].getHp()) == Main.gameData.rplayer.egysegek[4].getDb()
                        ? "mindenki, azaz " + Main.gameData.rplayer.egysegek[4].getDb() + " db sértetlen" : Main.gameData.rplayer.egysegek[4].getDb() - 1 + " sértetlen, és egynek "
                        + Main.gameData.rplayer.egysegek[4].getOsszHp() % Main.gameData.rplayer.egysegek[4].getHp() + " életereje van")
                        + "\nSebzés: " + Main.gameData.rplayer.egysegek[4].getSebzesinterval1() + "-" + Main.gameData.rplayer.egysegek[4].getSebzesinterval2();

                default -> output = "";
            }
        }
        details.setText(output);

    }

    @FXML
    public void hosTamadas(ActionEvent event) {
        //rá nincs hatással az armor mer ő hős

        //idk ha lplayer vagy akkor a masik tamadhato

        for(Node node : field.getChildren()) {
            for(int i = 0; i < 10; i++) {
                for (int j = 0; j < 12; j++) {
                    if(Main.gameData.map[i][j] != null && Main.gameData.map[i][j].getGazda() != Main.gameData.pakol) {
                        if(GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) != null &&
                                GridPane.getRowIndex(node) == i && GridPane.getColumnIndex(node) == j) {
                            node.setEffect(tamadhato);
                        }
                    }
                }
            }
        }
        //for map bejaras


        //ahol olyan egyseg van aminek nem pakolo a gazdaja akkor annak getnode row col es allitsuk tamadhatora
        //kesz a kijeloles

        //a kijelolteket az unitchooserben kell lekezelni:
        //kene passelni egy hostamadas booleant
        hosTamad = true;
        //es ha bent van akkor a dmgt ugy osztani hogy a sebzesszer 10 vagy idfk hogy van
        //ott lesetelni, es akk elvileg az ujra is kalkulalja a hpt
        //ott ugye a kovetkezo sorszamu unit tamad, mert ahelyett tamadt a hos i guess, szoval azt is le kell kezelni
    }

    @FXML
    public void spellCast(ActionEvent event) {

        //töröljük a jelöléseket justincase
        for (Node node : field.getChildren()) {
            //lepheto mezok
            if(GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) != null && (node.getEffect() == lepheto)) {
                node.setEffect(null);
            }
            //a soron levo unit mar nincs soron!!
            if(GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) != null && node.getEffect() == tamadhato) {
                node.setEffect(null);
            }
        }


        //a tanult modon kijeloljuk az enemy szarjait
        //uthetove tesszuk, bar ezt pihentebben atgondolva lehet mashova rakom

        //barmelyik skillt racastoljuk a unitra, ott megvalositva vagjuk agyon csak meg nem tom mit kene passelni de valszeg
        //egy bool lesz mindenhol :D
        //szoval egy enemytarget function nagy lenne es kb csak at kene passzolni a boolt / global var
        //ugyis van mindegyik gombnak idje szoval majd egy giga switch case megoldja a bajt

        unitTamad = false; //ha veletlen kattintott az ur
        hosTamad = false;

        switch(((Button)event.getSource()).getId()) {
            case "lvillamcsapas" -> villamcsapasTamad = true;
            case "ltuzlabda" ->tuzlabdaTamad = true;
            case "lfeltamasztas" -> feltamasztasTamad = true;
            case "lkotelbilincs" -> kotelblilincsTamad = true;
            case "lharcimamor" -> harcimamorTamad = true;

            case "rvillamcsapas" -> villamcsapasTamad = true;
            case "rtuzlabda" ->tuzlabdaTamad = true;
            case "rfeltamasztas" -> feltamasztasTamad = true;
            case "rkotelbilincs" -> kotelblilincsTamad = true;
            case "rharcimamor" -> harcimamorTamad = true;
        }

        if(villamcsapasTamad) {
            for(Node node : field.getChildren()) {
                for(int i = 0; i < 10; i++) {
                    for (int j = 0; j < 12; j++) {
                        if(node instanceof ImageView && Main.gameData.map[i][j] != null && Main.gameData.map[i][j].getGazda() != Main.gameData.pakol) {
                            if(GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) != null &&
                                    GridPane.getRowIndex(node) == i && GridPane.getColumnIndex(node) == j) {
                                node.setEffect(tamadhato);
                            }
                        }
                    }
                }
            }
        }
        if(tuzlabdaTamad) {
            //kell az előző kijelölt ha visszavonná
            for(Node node : field.getChildren()) {
                if(node instanceof ImageView) {
                    if(GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) != null) {
                        if(node.getEffect() == valasztott) {
                            tuzX = GridPane.getRowIndex(node);
                            tuzY = GridPane.getColumnIndex(node);
                            break;
                        }
                    }
                }
            }

            for(Node node : field.getChildren()) {
                if(node instanceof ImageView) {
                    if(GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) != null) {
                        node.setEffect(tamadhato);
                    }
                }

            }
        }
        if(feltamasztasTamad) {
            Hos caster = Main.gameData.pakol;

            System.out.println("bejottem feltamasztani");

            lfeltamaszto.setOpacity(1);
            lfeltamaszto.setDisable(false);

            //menjunk vegig az unitokon es nezzuk melyik halott, eltarolas jokerdes
            if(caster == Main.gameData.lplayer) {
                for(int i = 0; i < caster.egysegek.length; i++) {
                    if(caster.egysegek[i].getOsszHp() < lplayerUnits[i] * caster.egysegek[i].getHp() /*&& lplayerUnits[i] != 0*/) {
                        for(Node node : lfeltamaszto.getChildren()) {
                            if(node instanceof ImageView) {
                                System.out.println(caster.egysegek[i].getNev() + "Img");
                                if(Objects.equals(node.getId(), caster.egysegek[i].getNev() + "Img")) {
                                    node.setDisable(false);
                                    node.setOpacity(1);
                                }
                            }
                        }
                    }
                }
            }
            else if(caster == Main.gameData.rplayer) {
                for(int i = 0; i < caster.egysegek.length; i++) {
                    if(caster.egysegek[i].getOsszHp() < rplayerUnits[i] * caster.egysegek[i].getHp()) {
                        for(Node node : lfeltamaszto.getChildren()) {
                            if(node instanceof ImageView) {
                                System.out.println(caster.egysegek[i].getNev() + "Img");
                                if(Objects.equals(node.getId(), caster.egysegek[i].getNev() + "Img")) {
                                    node.setDisable(false);
                                    node.setOpacity(1);
                                }
                            }
                        }
                    }
                }
            }


        }
        if(kotelblilincsTamad) {
            for(Node node : field.getChildren()) {
                for(int i = 0; i < 10; i++) {
                    for (int j = 0; j < 12; j++) {
                        if(Main.gameData.map[i][j] != null && Main.gameData.map[i][j].getGazda() != Main.gameData.pakol) {
                            if(GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) != null &&
                                    GridPane.getRowIndex(node) == i && GridPane.getColumnIndex(node) == j) {
                                node.setEffect(tamadhato);
                            }
                        }
                    }
                }
            }

        }

        if(harcimamorTamad) {
            for(int i = 0; i < 10; i++) {
                for (int j = 0; j < 12; j++) {
                    if(Main.gameData.map[i][j] != null && Main.gameData.map[i][j].getGazda() == Main.gameData.pakol) {
                        Main.gameData.map[i][j].setSebzesinterval1(Main.gameData.map[i][j].getSebzesinterval1() + (Main.gameData.pakol.getVarazsero() * 2));
                        Main.gameData.map[i][j].setSebzesinterval2(Main.gameData.map[i][j].getSebzesinterval2() + (Main.gameData.pakol.getVarazsero() * 2));

                        System.out.println(Main.gameData.map[i][j].getSebzesinterval1() + "-" + Main.gameData.map[i][j].getSebzesinterval2());


                        harcimamorTamad = true;
                        Main.gameData.pakol.setHarciMamorAktiv(true);
                        details1.setText((Main.gameData.pakol == Main.gameData.lplayer ? "< " : "") + "A harci mámor ebben a körben aktiválva lett." + (Main.gameData.pakol == Main.gameData.rplayer ? " >" : ""));
                    }
                }
            }
        }

    }
    @FXML
    public void undoJeloles(ActionEvent event) {
        for(Node node : field.getChildren()) {
            if(GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) != null && node.getEffect() != valasztott) {
                node.setEffect(null);

                if(tuzlabdaTamad) {
                    if(GridPane.getRowIndex(node) == tuzX && GridPane.getColumnIndex(node) == tuzY) {
                        node.setEffect(valasztott);
                    }
                }

            }
        }
        for(Node node : lfeltamaszto.getChildren()) {
            if(node instanceof ImageView) {
                node.setDisable(true);
                node.setOpacity(0);
            }
        }
        lfeltamaszto.setOpacity(0);
        lfeltamaszto.setDisable(true);

        villamcsapasTamad = false;
        tuzlabdaTamad = false;
        kotelblilincsTamad = false;
        harcimamorTamad = false;
        feltamasztasTamad = false;
    }

    private void jelolesTorlese() {
        for (Node node : field.getChildren()) {
            //lepheto mezok
            if(GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) != null && (node.getEffect() == lepheto)) {
                node.setEffect(null);
            }
            //a soron levo unit mar nincs soron!!
            if(GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) != null && (node.getEffect() == valasztott || node.getEffect() == tamadhato)) {
                node.setEffect(null);
            }
        }
    }

    @FXML
    public void feleleszt(MouseEvent event) throws IOException {
        //töröljük a csöves jelöléseket
        jelolesTorlese();


        //ha rakattintasz akkor legyen halott ez pl ellenorizheto opacitasbol like a boss
        if(((Node)event.getSource()).getOpacity() == 1) {
            if(Main.gameData.pakol.getMana() < Main.gameData.pakol.elerhetoVarazslatok[2].getManaCost()) {
                info.setText("Túl kevés mana a varázsláshoz!");
                feltamasztasTamad = false;
                currentSorszam--;

                kovetkezo();

                return;
            }
            else {
                Main.gameData.pakol.setMana(Main.gameData.pakol.getMana() - Main.gameData.pakol.elerhetoVarazslatok[2].getManaCost());
                lmana.setText(Main.gameData.pakol.getMana() + " mana");
            }

            int col = 0;
            if(Main.gameData.pakol == Main.gameData.rplayer) {
                col = 11;
            }
            //mire nyomtál
            switch(((Node)event.getSource()).getId()) {
                case "foldmuvesImg" -> {
                    int maxHeal = Main.gameData.pakol.getVarazsero() * 50;
                    int baseHp = Main.gameData.pakol.egysegek[0].getHp() * Main.gameData.pakol.egysegek[0].getDb();
                    boolean halott = false;
                    if(Main.gameData.pakol.egysegek[0].getOsszHp() == 0) {
                        halott = true;
                    }

                    if(halott) {
                        Main.gameData.pakol.egysegek[0].setOsszHp(Math.min(maxHeal, baseHp));
                        boolean vanserult = Main.gameData.pakol.egysegek[0].getOsszHp() % Main.gameData.pakol.egysegek[0].getHp() != 0;
                        Main.gameData.pakol.egysegek[0].setDb(Main.gameData.pakol.egysegek[0].getOsszHp() / Main.gameData.pakol.egysegek[0].getHp() + (vanserult ? 1 : 0));
                        details.setText("A kiválaszott egység fel lett támasztva! Új életereje: " + Main.gameData.pakol.egysegek[0].getOsszHp());
                    } else {
                        int hianyzo = 0;
                        if(Main.gameData.pakol == Main.gameData.lplayer) {
                            hianyzo = Main.gameData.pakol.egysegek[0].getHp() * lplayerUnits[0] - Main.gameData.pakol.egysegek[0].getOsszHp();
                            if(hianyzo > maxHeal)
                                hianyzo = maxHeal;
                        }
                        else {
                            hianyzo = Main.gameData.pakol.egysegek[0].getHp() * rplayerUnits[0] - Main.gameData.pakol.egysegek[0].getOsszHp();
                            if(hianyzo > maxHeal)
                                hianyzo = maxHeal;
                        }

                        Main.gameData.pakol.egysegek[0].setOsszHp(Main.gameData.pakol.egysegek[0].getOsszHp() + hianyzo);
                        details.setText("A kiválaszott egység néhány tagja fel lett támasztva! Életerő: " + Main.gameData.pakol.egysegek[0].getOsszHp());
                    }
                    if(halott) {
                        for(Node node : field.getChildren()) {
                            if(node instanceof ImageView) {
                                if(GridPane.getColumnIndex(node) != null && GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) == col
                                        && node.getId() == null) {
                                    //rakd le ezt a SZART
                                    ((ImageView)node).setImage(foldmuves);
                                    if(Main.gameData.pakol == Main.gameData.lplayer) {
                                        node.setId("l" + Main.gameData.pakol.egysegek[0].getNev());
                                    }
                                    else {
                                        node.setId("r" + Main.gameData.pakol.egysegek[0].getNev());
                                    }


                                    //most a mapra
                                    Main.gameData.map[GridPane.getRowIndex(node)][GridPane.getColumnIndex(node)] = Main.gameData.pakol.egysegek[0];
                                    break;

                                }
                            }
                        }
                    }
                }
                case "ijaszImg" -> {
                    if(Main.gameData.pakol == Main.gameData.lplayer) {
                        Main.gameData.pakol.egysegek[1].setDb(lplayerUnits[1]);
                    }
                    else {
                        Main.gameData.pakol.egysegek[1].setDb(rplayerUnits[1]);
                    }
                    int maxHeal = Main.gameData.pakol.getVarazsero() * 50;
                    int baseHp = Main.gameData.pakol.egysegek[1].getHp() * Main.gameData.pakol.egysegek[1].getDb();
                    boolean halott = false;
                    if(Main.gameData.pakol.egysegek[1].getOsszHp() == 0) {
                        halott = true;
                    }

                    if(halott) {
                        Main.gameData.pakol.egysegek[1].setOsszHp(Math.min(maxHeal, baseHp));
                        details.setText("A kiválaszott egység fel lett támasztva! Új életereje: " + Main.gameData.pakol.egysegek[1].getOsszHp());
                    } else {
                        int hianyzo = Main.gameData.pakol.egysegek[1].getHp() * Main.gameData.pakol.egysegek[1].getDb() - Main.gameData.pakol.egysegek[1].getOsszHp();
                        Main.gameData.pakol.egysegek[1].setOsszHp(Main.gameData.pakol.egysegek[1].getOsszHp() + hianyzo);
                        details.setText("A kiválaszott egység néhány tagja fel lett támasztva! Életerő: " + Main.gameData.pakol.egysegek[1].getOsszHp());
                    }
                    if(halott) {
                        for(Node node : field.getChildren()) {
                            if(node instanceof ImageView) {
                                if(GridPane.getColumnIndex(node) != null && GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) == col
                                        && node.getId() == null) {
                                    //rakd le ezt a SZART
                                    ((ImageView)node).setImage(foldmuves);
                                    if(Main.gameData.pakol == Main.gameData.lplayer) {
                                        node.setId("l" + Main.gameData.pakol.egysegek[1].getNev());
                                    }
                                    else {
                                        node.setId("r" + Main.gameData.pakol.egysegek[1].getNev());
                                    }


                                    //most a mapra
                                    Main.gameData.map[GridPane.getRowIndex(node)][GridPane.getColumnIndex(node)] = Main.gameData.pakol.egysegek[1];
                                    break;

                                }
                            }
                        }
                    }

                }
                case "griffImg" -> {
                    if(Main.gameData.pakol == Main.gameData.lplayer) {
                        Main.gameData.pakol.egysegek[2].setDb(lplayerUnits[2]);
                    }
                    else {
                        Main.gameData.pakol.egysegek[2].setDb(rplayerUnits[2]);
                    }
                    int maxHeal = Main.gameData.pakol.getVarazsero() * 50;
                    int baseHp = Main.gameData.pakol.egysegek[2].getHp() * Main.gameData.pakol.egysegek[2].getDb();
                    boolean halott = false;
                    if(Main.gameData.pakol.egysegek[2].getOsszHp() == 0) {
                        halott = true;
                    }

                    if(halott) {
                        Main.gameData.pakol.egysegek[2].setOsszHp(Math.min(maxHeal, baseHp));
                        details.setText("A kiválaszott egység fel lett támasztva! Új életereje: " + Main.gameData.pakol.egysegek[2].getOsszHp());
                    } else {
                        int hianyzo = Main.gameData.pakol.egysegek[2].getHp() * Main.gameData.pakol.egysegek[2].getDb() - Main.gameData.pakol.egysegek[2].getOsszHp();
                        Main.gameData.pakol.egysegek[2].setOsszHp(Main.gameData.pakol.egysegek[2].getOsszHp() + hianyzo);
                        details.setText("A kiválaszott egység néhány tagja fel lett támasztva! Életerő: " + Main.gameData.pakol.egysegek[2].getOsszHp());
                    }
                    if(halott) {
                        for(Node node : field.getChildren()) {
                            if(node instanceof ImageView) {
                                if(GridPane.getColumnIndex(node) != null && GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) == col
                                        && node.getId() == null) {
                                    //rakd le ezt a SZART
                                    ((ImageView)node).setImage(foldmuves);
                                    if(Main.gameData.pakol == Main.gameData.lplayer) {
                                        node.setId("l" + Main.gameData.pakol.egysegek[2].getNev());
                                    }
                                    else {
                                        node.setId("r" + Main.gameData.pakol.egysegek[2].getNev());
                                    }


                                    //most a mapra
                                    Main.gameData.map[GridPane.getRowIndex(node)][GridPane.getColumnIndex(node)] = Main.gameData.pakol.egysegek[2];
                                    break;

                                }
                            }
                        }
                    }
                }
                case "magusImg" -> {
                    if(Main.gameData.pakol == Main.gameData.lplayer) {
                        Main.gameData.pakol.egysegek[3].setDb(lplayerUnits[3]);
                    }
                    else {
                        Main.gameData.pakol.egysegek[3].setDb(rplayerUnits[3]);
                    }
                    int maxHeal = Main.gameData.pakol.getVarazsero() * 50;
                    int baseHp = Main.gameData.pakol.egysegek[3].getHp() * Main.gameData.pakol.egysegek[3].getDb();
                    boolean halott = false;
                    if(Main.gameData.pakol.egysegek[3].getOsszHp() == 0) {
                        halott = true;
                    }

                    if(halott) {
                        Main.gameData.pakol.egysegek[3].setOsszHp(Math.min(maxHeal, baseHp));
                        details.setText("A kiválaszott egység fel lett támasztva! Új életereje: " + Main.gameData.pakol.egysegek[3].getOsszHp());
                    } else {
                        int hianyzo = Main.gameData.pakol.egysegek[3].getHp() * Main.gameData.pakol.egysegek[3].getDb() - Main.gameData.pakol.egysegek[3].getOsszHp();
                        Main.gameData.pakol.egysegek[3].setOsszHp(Main.gameData.pakol.egysegek[3].getOsszHp() + hianyzo);
                        details.setText("A kiválaszott egység néhány tagja fel lett támasztva! Életerő: " + Main.gameData.pakol.egysegek[3].getOsszHp());
                    }
                    if(halott) {
                        for(Node node : field.getChildren()) {
                            if(node instanceof ImageView) {
                                if(GridPane.getColumnIndex(node) != null && GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) == col
                                        && node.getId() == null) {
                                    //rakd le ezt a SZART
                                    ((ImageView)node).setImage(foldmuves);
                                    if(Main.gameData.pakol == Main.gameData.lplayer) {
                                        node.setId("l" + Main.gameData.pakol.egysegek[3].getNev());
                                    }
                                    else {
                                        node.setId("r" + Main.gameData.pakol.egysegek[3].getNev());
                                    }


                                    //most a mapra
                                    Main.gameData.map[GridPane.getRowIndex(node)][GridPane.getColumnIndex(node)] = Main.gameData.pakol.egysegek[3];
                                    break;

                                }
                            }
                        }
                    }
                }
                case "saiyanImg" -> {
                    if (Main.gameData.pakol == Main.gameData.lplayer) {
                        Main.gameData.pakol.egysegek[4].setDb(lplayerUnits[4]);
                    } else {
                        Main.gameData.pakol.egysegek[4].setDb(rplayerUnits[4]);
                    }
                    int maxHeal = Main.gameData.pakol.getVarazsero() * 50;
                    int baseHp = Main.gameData.pakol.egysegek[4].getHp() * Main.gameData.pakol.egysegek[4].getDb();
                    boolean halott = false;
                    if (Main.gameData.pakol.egysegek[4].getOsszHp() == 0) {
                        halott = true;
                    }

                    if (halott) {
                        Main.gameData.pakol.egysegek[4].setOsszHp(Math.min(maxHeal, baseHp));
                        details.setText("A kiválaszott egység fel lett támasztva! Új életereje: " + Main.gameData.pakol.egysegek[4].getOsszHp());
                    } else {
                        int hianyzo = Main.gameData.pakol.egysegek[4].getHp() * Main.gameData.pakol.egysegek[4].getDb() - Main.gameData.pakol.egysegek[4].getOsszHp();
                        Main.gameData.pakol.egysegek[1].setOsszHp(Main.gameData.pakol.egysegek[4].getOsszHp() + hianyzo);
                        details.setText("A kiválaszott egység néhány tagja fel lett támasztva! Életerő: " + Main.gameData.pakol.egysegek[4].getOsszHp());
                    }
                    if (halott) {
                        for (Node node : field.getChildren()) {
                            if (node instanceof ImageView) {
                                if (GridPane.getColumnIndex(node) != null && GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) == col
                                        && node.getId() == null) {
                                    //rakd le ezt a SZART
                                    ((ImageView) node).setImage(foldmuves);
                                    if (Main.gameData.pakol == Main.gameData.lplayer) {
                                        node.setId("l" + Main.gameData.pakol.egysegek[4].getNev());
                                    } else {
                                        node.setId("r" + Main.gameData.pakol.egysegek[4].getNev());
                                    }

                                    //most a mapra
                                    Main.gameData.map[GridPane.getRowIndex(node)][GridPane.getColumnIndex(node)] = Main.gameData.pakol.egysegek[4];
                                    break;

                                }
                            }
                        }
                    }
                }
            }

            currentsr = handleSorrend(sorrendezo());

            feltamasztasTamad = false;
            lfeltamaszto.setOpacity(0);
            lfeltamaszto.setDisable(true);
            kovetkezo();

        }

    }

    @FXML
    public void felelesztEltuntetese(MouseEvent event) {
        lfeltamaszto.setOpacity(0);
        lfeltamaszto.setDisable(true);
    }

    @FXML
    public void hoverOff(MouseEvent event) {
        details.setText("");
        spellStatus.setText("");
    }

    @FXML
    public void lvarakozas(ActionEvent event) throws IOException {
        if(Main.gameData.pakol == Main.gameData.lplayer) {
            for(Node node : field.getChildren()) {

                if(GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) != null) {
                    if(node.getEffect() == valasztott) {
                        node.setEffect(null);
                    }
                }
                //lepheto es tamadhato mezok ha veletlen kattintott a player
                if(GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) != null && (node.getEffect() == lepheto || node.getEffect() == tamadhato)) {
                    node.setEffect(null);
                }
            }
            //currentSorszam++;
            kovetkezo();
        }
        else {
            details1.setText("Nem te vagy soron!");
        }

    }
    public void rvarakozas(ActionEvent event) throws IOException {
        if(Main.gameData.pakol == Main.gameData.rplayer) {
            for(Node node : field.getChildren()) {

                if(GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) != null) {
                    if(node.getEffect() == valasztott) {
                        node.setEffect(null);
                    }
                }
                //lepheto es tamadhato mezok ha veletlen kattintott a player
                if(GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) != null && (node.getEffect() == lepheto || node.getEffect() == tamadhato)) {
                    node.setEffect(null);
                }
            }
            //currentSorszam++;
            kovetkezo();
        }
        else {
            details1.setText("Nem te vagy soron!");
        }

    }

    @FXML
    public void lvarazslas(ActionEvent event) {
        lvarazslattarto.setOpacity(1);
        for(int i = 0; i<lvarazslatok.length; i++) {
            if(Main.gameData.lplayer.elerhetoVarazslatok[i].isVan()) {
                lvarazslatok[i].setDisable(false);
            }
        }
    }
    @FXML
    public void rvarazslas(ActionEvent event) {
        rvarazslattarto.setOpacity(1);
        for(int i = 0; i<rvarazslatok.length; i++) {
            if(Main.gameData.rplayer.elerhetoVarazslatok[i].isVan()) {
                rvarazslatok[i].setDisable(false);
            }
        }
    }

    @FXML
    public void hoverVarazslasbol(MouseEvent event) {
        lvarazslattarto.setOpacity(0);
        for(int i = 0; i<lvarazslatok.length; i++) {
            lvarazslatok[i].setDisable(true);
        }
        rvarazslattarto.setOpacity(0);
        for(int i = 0; i<rvarazslatok.length; i++) {
            rvarazslatok[i].setDisable(true);
        }
    }

    private void aiLepkedo() {

        //van egy mostani cucca, amivel kéne mozogni
        List<Egyseg> enemies = new ArrayList<>();

        for(int i = 0; i < 10; i++) {
            for (int j = 0; j < 12; j++) {
                if(Main.gameData.map[i][j] != null && Main.gameData.map[i][j].getGazda() != Main.gameData.rplayer) {
                    enemies.add(Main.gameData.map[i][j]);


                }
            }
        }




    }
    //goat algoritmus
    private Set<Coords> dijkstra(Coords coord, int sebesseg) {
        Set<Coords> queue = new HashSet<>();
        int pos = 0;

        queue.add(coord);
        while(sebesseg > pos) {
            queue = szomszedok(queue);
            pos++;
        }

        return queue;
    }

    public Set<Coords> szomszedok(Set<Coords> set) {
        Set<Coords> var = new HashSet<>();

        for (Coords c : set) {
            if (Main.gameData.map[c.row][Math.min(c.col + 1, 11)] == null) {
                var.add(new Coords(c.row, Math.min(c.col + 1, 11)));
            }
            if (Main.gameData.map[c.row][Math.max(0, c.col - 1)] == null) {
                var.add(new Coords(c.row, Math.max(0, c.col - 1)));
            }
            if (Main.gameData.map[Math.min(c.row + 1, 9)][c.col] == null) {
                var.add(new Coords(Math.min(c.row + 1, 9), c.col));
            }
            if (Main.gameData.map[Math.max(0, c.row - 1)][c.col] == null) {
                var.add(new Coords(Math.max(0, c.row - 1), c.col));
            }

        }

        var.addAll(set);
        return var;
    }




    private void kovetkezo() throws IOException {
        Egyseg elozo = null;
        if(currentSorszam > -1) {
            elozo = currentsr[currentSorszam];
        }

        currentsr = handleSorrend(currentsr);

        if(currentsr.length > currentSorszam)
            currentSorszam++;

        //agyfaszt kapok geci
        if(elozo != null && elozo != currentsr[currentSorszam - 1])
            currentSorszam--;


        if(currentSorszam == currentsr.length) {
            currentSorszam = 0;
            kor.setText(++Main.gameData.kor + ". kör");

            for(Egyseg e : currentsr) {
                if(e != null)
                    e.setVisszatamadtAKorben(false);
            }

            //region harcimámor kikapcsolás
            if(Main.gameData.lplayer.isHarciMamorAktiv()) {
                for(int i = 0; i < 10; i++) {
                    for (int j = 0; j < 12; j++) {
                        if(Main.gameData.map[i][j] != null && Main.gameData.map[i][j].getGazda() == Main.gameData.lplayer) {
                            Main.gameData.map[i][j].setSebzesinterval1(Main.gameData.map[i][j].getSebzesinterval1() - Main.gameData.lplayer.getVarazsero() * 2);
                            Main.gameData.map[i][j].setSebzesinterval2(Main.gameData.map[i][j].getSebzesinterval2() - Main.gameData.lplayer.getVarazsero() * 2);

                        }
                            harcimamorTamad = false;
                            Main.gameData.lplayer.setHarciMamorAktiv(false);
                    }
                }
            }

            if(Main.gameData.rplayer.isHarciMamorAktiv()) {
                for(int i = 0; i < 10; i++) {
                    for (int j = 0; j < 12; j++) {
                        if(Main.gameData.map[i][j] != null && Main.gameData.map[i][j].getGazda() == Main.gameData.rplayer) {
                            Main.gameData.map[i][j].setSebzesinterval1(Main.gameData.map[i][j].getSebzesinterval1() - Main.gameData.rplayer.getVarazsero() * 2);
                            Main.gameData.map[i][j].setSebzesinterval2(Main.gameData.map[i][j].getSebzesinterval2() - Main.gameData.rplayer.getVarazsero() * 2);

                        }

                        harcimamorTamad = false;
                        Main.gameData.rplayer.setHarciMamorAktiv(false);
                    }
                }
            }
            //endregion
        }


        //region kötélbilincs kikapcsolás
        if(currentsr[currentSorszam].isKimarad()) {
            currentsr[currentSorszam].setKimarad(false);
            kovetkezo();
        }
        //endregion

        //region nyertél
        int lEloEgysegek = 0, rEloEgysegek = 0;

        System.out.println(currentsr.length);

        for(Egyseg e : currentsr) {
            if(e == null) {
                info.setText("Döntetlen!");
                break;
            }
            if(e.getGazda() == Main.gameData.lplayer) {
                lEloEgysegek++;
            }
            else if(e.getGazda() == Main.gameData.rplayer) {
                rEloEgysegek++;
            }
        }

        System.out.println(lEloEgysegek + " " + rEloEgysegek);

        if(currentsr.length >= 1 && rEloEgysegek == 0) {
            info.setText("1. játékos nyert!");
            Main.gameData.gyoztes = Main.gameData.lplayer;
        }
        else if(currentsr.length >= 1 && lEloEgysegek == 0) {
            info.setText(Main.gameData.multiplayer ? "2." : "BOT" + " játékos nyert!");
            Main.gameData.gyoztes = Main.gameData.rplayer;
        }
        else if(lEloEgysegek == 0 && rEloEgysegek == 0) {
            info.setText("A meccs döntetlennel zárult.");
            Main.gameData.gyoztes = null;
        }


        if(lEloEgysegek == 0 ||rEloEgysegek == 0) {

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("epilogue.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) field.getScene().getWindow();
            stage.setResizable(false);
            stage.setScene(scene);

        }

        //endregion




        //region magus kepesseg (makes no sense kind of meghivas)
        if(Objects.equals(currentsr[currentSorszam].getNev(), "ijasz")) {
            currentsr[currentSorszam].kepesseg();
        }
        //endregion

        //dont ask miert 2 function
        printSorrend(currentsr);

        //System.out.println(currentsr[currentSorszam].getNev() + currentsr[currentSorszam].getDb() + " " + currentsr[currentSorszam].getOsszHp());

        if(currentsr[currentSorszam].getGazda() == Main.gameData.lplayer) {
            Main.gameData.pakol = Main.gameData.lplayer;
        }
        else if(currentsr[currentSorszam].getGazda() == Main.gameData.rplayer) {
            Main.gameData.pakol = Main.gameData.rplayer;
        }
        for (Node node : field.getChildren()) {
            if (GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) != null
                    && Main.gameData.map[GridPane.getRowIndex(node)][GridPane.getColumnIndex(node)] != null
                    && Main.gameData.map[GridPane.getRowIndex(node)][GridPane.getColumnIndex(node)].getNev().contains(currentsr[currentSorszam].getNev())) {
                if(Main.gameData.pakol == Main.gameData.lplayer) {

                    if(Objects.equals(node.getId(), "l" + currentsr[currentSorszam].getNev()))
                    {
                        System.out.println("Az első guy: " + currentsr[currentSorszam].getNev());
                        node.setEffect(valasztott);
                    }
                }
                else {
                    if(Objects.equals(node.getId(), "r" + currentsr[currentSorszam].getNev()))
                    {
                        System.out.println("Az első guy: " + currentsr[currentSorszam].getNev());
                        if(Main.gameData.multiplayer)
                            node.setEffect(valasztott);
                        else
                            aiLepkedo();
                    }
                }
            }
        }

        if(Main.gameData.pakol == Main.gameData.lplayer) {
            for(Button a : ractions) {
                a.setDisable(true);
            }
            for(Button a : lactions) {
                a.setDisable(false);
            }
            info.setText("1. játékos következik!");
        }
        else {
            for(Button a : lactions) {
                a.setDisable(true);
            }
            if(Main.gameData.multiplayer) {
                for(Button a : ractions) {
                    a.setDisable(false);
                }
            }
            info.setText(Main.gameData.multiplayer ? "2. játékos következik!" : "BOT játékos következik!");
        }

    }

    @FXML
    public void valasztottUnitAction(MouseEvent event) throws IOException {

        //region misc
        printSorrend(handleSorrend(currentsr));
        String prefix = (Main.gameData.pakol == Main.gameData.lplayer ? "l" : "r");
        details1.setText("");
        //endregion


        boolean tavolharciHasNearby = false;
        //region Hova léphet az úr illetve kiket támadhat?
        if(((Node)event.getSource()).getEffect() == valasztott) {
            Egyseg egyseg = Main.gameData.map[GridPane.getRowIndex((Node)event.getSource())][GridPane.getColumnIndex((Node)event.getSource())];

            int x = 0,y = 0;
            Set<Coords> okay = dijkstra(new Coords(GridPane.getRowIndex((Node)event.getSource()), GridPane.getColumnIndex((Node)event.getSource())), egyseg.getSebesseg());

            for (Node node : field.getChildren()) {
                if(node instanceof ImageView) {
                    if(GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) != null) {
                        Egyseg enemy = Main.gameData.map[GridPane.getRowIndex(node)][GridPane.getColumnIndex(node)];
                        x = GridPane.getColumnIndex((Node)event.getSource());
                        y = GridPane.getRowIndex((Node)event.getSource());

                        /*if(Math.abs(x - GridPane.getColumnIndex(node)) + Math.abs(y - GridPane.getRowIndex(node)) <= currentsr[currentSorszam].getSebesseg()
                            && Main.gameData.map[GridPane.getRowIndex(node)][GridPane.getColumnIndex(node)] == null)
                                    (node).setEffect(lepheto);*/

                        for(Coords o : okay) {
                            if(GridPane.getRowIndex(node) == o.row && GridPane.getColumnIndex(node) == o.col) {
                                System.out.println("bejöttem mert itt volt egy jó kocka");
                                    (node).setEffect(lepheto);

                                System.out.println(node.getEffect() == lepheto);
                            }
                        }


                        if(egyseg.isTavolHarci()) {
                            if(enemy != null && enemy.getGazda() != egyseg.getGazda() && (Math.abs(x - GridPane.getColumnIndex(node)) + Math.abs(y - GridPane.getRowIndex(node)) == 1)) {
                                tavolharciHasNearby = true;
                                visszatamadasKozel = true;
                            }
                        }
                        if((enemy != null && enemy.getGazda() != egyseg.getGazda() && (Math.abs(x - GridPane.getColumnIndex(node)) + Math.abs(y - GridPane.getRowIndex(node)) == 1) || (enemy != null && enemy.getGazda() != egyseg.getGazda() && egyseg.isTavolHarci()) && !tavolharciHasNearby)) {
                            (node).setEffect(tamadhato);
                            unitTamad = true;
                        }
                    }
                }
            }

            for (Node node: field.getChildren()) {
                if(GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) != null && GridPane.getRowIndex(node) == y && GridPane.getColumnIndex(node) == x) {
                    node.setEffect(valasztott);
                    break;
                }
            }

        }

        //endregion

        //region ACTION: lépés

        if(((Node)event.getSource()).getEffect() == lepheto) {
            for (Node node : field.getChildren()) {
                if(node instanceof ImageView) {

                    //töröljük az előzőt
                    if(GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) != null && node.getId() != null && node.getId().contains(prefix + currentsr[currentSorszam].getNev())) {
                        node.setId(null);
                        ((ImageView)node).setImage(notnull);
                        Main.gameData.map[GridPane.getRowIndex(node)][GridPane.getColumnIndex(node)] = null;

                    }
                }

                //adjuk hozzá es una új
                ((ImageView)event.getSource()).setId(prefix + currentsr[currentSorszam].getNev());
                Main.gameData.map[GridPane.getRowIndex((Node)event.getSource())][GridPane.getColumnIndex((Node)event.getSource())] = currentsr[currentSorszam];

                switch(currentsr[currentSorszam].getNev()) {
                    case "foldmuves" -> ((ImageView)event.getSource()).setImage(foldmuves);
                    case "ijasz" -> ((ImageView)event.getSource()).setImage(ijasz);
                    case "griff" -> ((ImageView)event.getSource()).setImage(griff);
                    case "magus" -> ((ImageView)event.getSource()).setImage(magus);
                    case "szupercsillagharcos" -> ((ImageView)event.getSource()).setImage(szupercsillagharcos);
                }
            }

            //reset mezőszínek
            jelolesTorlese();

            //mivel action ezért ez a unit mar nem tud mast csinalni a korben
            //currentSorszam++;
            kovetkezo();
            return;
        }
        //endregion

        //Kiket támadhatunk ????????
        //specialis skillek :D
        //megvalositas a kovetkezo modon:
        //majd holnap, kiveve ha most eszembejut valami

        //region ACTION: TAMADAAAAAAAAAAAAAAAAS
        if(((Node)event.getSource()).getEffect() == tamadhato) {

            //region tamadasmisc
            String szenvedoUnit = "";
            //unit amit megtamadtak
            if(GridPane.getRowIndex((Node) event.getSource()) != null && GridPane.getColumnIndex((Node) event.getSource()) != null && (((Node) event.getSource()).getId() != null)) {
                switch(Main.gameData.map[GridPane.getRowIndex((Node) event.getSource())][GridPane.getColumnIndex((Node) event.getSource())].getNev()) {
                    case "foldmuves" -> szenvedoUnit = "Földműves";
                    case "ijasz" -> szenvedoUnit = "Íjász";
                    case "griff" -> szenvedoUnit = "Griff";
                    case "magus" -> szenvedoUnit = "Mágus";
                    case "szupercsillagharcos" -> szenvedoUnit = "Szupercsillagharcos";
                }
            }


            String kuldoUnit = "";
            //unit amit megtamadtak
            switch(currentsr[currentSorszam].getNev()) {
                case "foldmuves" -> kuldoUnit = "Földműves";
                case "ijasz" -> kuldoUnit = "Íjász";
                case "griff" -> kuldoUnit = "Griff";
                case "magus" -> kuldoUnit = "Mágus";
                case "szupercsillagharcos" -> kuldoUnit = "Szupercsillagharcos";
            }



            //actual megtámadott unit id alapján :D
            Egyseg megtamadott = null;

            if(GridPane.getRowIndex((Node) event.getSource()) != null && GridPane.getColumnIndex((Node) event.getSource()) != null && (((Node) event.getSource()).getId() != null)) {
                switch (((ImageView) event.getSource()).getId()) {
                    case "lfoldmuves" -> megtamadott = Main.gameData.lplayer.egysegek[0];
                    case "lijasz" -> megtamadott = Main.gameData.lplayer.egysegek[1];
                    case "lgriff" -> megtamadott = Main.gameData.lplayer.egysegek[2];
                    case "lmagus" -> megtamadott = Main.gameData.lplayer.egysegek[3];
                    case "lszupercsillagharcos" -> megtamadott = Main.gameData.lplayer.egysegek[4];

                    case "rfoldmuves" -> megtamadott = Main.gameData.rplayer.egysegek[0];
                    case "rijasz" -> megtamadott = Main.gameData.rplayer.egysegek[1];
                    case "rgriff" -> megtamadott = Main.gameData.rplayer.egysegek[2];
                    case "rmagus" -> megtamadott = Main.gameData.rplayer.egysegek[3];
                    case "rszupercsillagharcos" -> megtamadott = Main.gameData.rplayer.egysegek[4];
                }
            }
            //endregion

            //region Hős támad
            if (hosTamad) {
                //a hős nem tud kritelni
                boolean crit = false;

                int dmg = Main.gameData.pakol.getTamadas() * 10;
                int currentHp = Main.gameData.map[GridPane.getRowIndex((Node) event.getSource())][GridPane.getColumnIndex((Node) event.getSource())].getOsszHp();

                //az adott unitnak meg kéne változtatni az összhpját
                Main.gameData.map[GridPane.getRowIndex((Node) event.getSource())][GridPane.getColumnIndex((Node) event.getSource())].setOsszHp(currentHp - dmg);

                //nézzük meg hogy Halt-e.
                unitHalt(event, szenvedoUnit, dmg, crit);

                //System.out.println(currentsr.length);

                //le kéne szedni a SZUTYKOS TAMADASJELZOT

                for(Node node : field.getChildren()) {
                    if(node instanceof ImageView && GridPane.getColumnIndex(node) != null && GridPane.getRowIndex(node) != null) {
                        if(node.getEffect() == tamadhato) {
                            node.setEffect(null);
                        }

                        //a soron levo unit mar nincs soron!!
                        if(node.getEffect() == valasztott) {
                            node.setEffect(null);

                        }
                    }
                }
                //currentSorszam++;
                hosTamad = false;
                kovetkezo();
            }
            //endregion

            //region unit tamad
            if(unitTamad) {
                //mennyi lesz a demidzs Odafelé
                int dmg;
                Hos enemy = (Main.gameData.pakol == Main.gameData.lplayer) ? Main.gameData.rplayer : Main.gameData.lplayer;

                //generálunk randomot az intervallumról
                Random rnd = new Random();

                //interval1 + interval2-interval1
                double unitGenDmg = currentsr[currentSorszam].getSebzesinterval1() + (currentsr[currentSorszam].getSebzesinterval2() - currentsr[currentSorszam].getSebzesinterval1() > 0 ? rnd.nextInt(currentsr[currentSorszam].getSebzesinterval2() - currentsr[currentSorszam].getSebzesinterval1()) : 0);
                System.out.println("generált base: " + unitGenDmg);
                //felszorozzuk a darabszámmal
                unitGenDmg *= currentsr[currentSorszam].getDb();
                System.out.println("darabokkal base: " + unitGenDmg);
                //támadási bonusz
                unitGenDmg *= 1 + (double) Main.gameData.pakol.getTamadas() / 10;
                System.out.println("támadás bonusz: " + unitGenDmg);
                //enemy armorját százalákosan vonjuk 5% / pont
                unitGenDmg *= 1 - (0.05 * enemy.getVedekezes());
                System.out.println("kivont védekezéssel: " + unitGenDmg);

                dmg = (int)Math.round(unitGenDmg);
                System.out.println("beküldött dmg: " + dmg);

                double szerencse = 100 * Main.gameData.pakol.getSzerencse() * 0.05; //pl 3 szerencse esetén 15% esélyünk van
                double tryluck = 100 * ((double)rnd.nextInt(100) / 100);
                boolean crit = szerencse > tryluck;

                if(crit)
                    dmg *= 2;

                //visszatámadás
                int dmgback;

                if(megtamadott != null) {
                    //interval dmg
                    double unitDmgBack = megtamadott.getSebzesinterval1() + ((megtamadott.getSebzesinterval2() - megtamadott.getSebzesinterval1() > 0) ? rnd.nextInt(megtamadott.getSebzesinterval2() - megtamadott.getSebzesinterval1()) : 0);
                    System.out.println("v generált base: " + unitDmgBack);
                    //felszorozzuk a darabszámmal
                    unitDmgBack *= megtamadott.getDb();
                    System.out.println("v darabokkal base: " + unitDmgBack);
                    //támadási bonusz
                    unitDmgBack *= 1 + (double) enemy.getTamadas() / 10;
                    System.out.println("v támadás bonusz: " + unitDmgBack);
                    //vedekezes -
                    unitDmgBack *= 1 - (0.05 * Main.gameData.pakol.getVedekezes());
                    System.out.println("v kivont védekezéssel: " + unitDmgBack);
                    //kesz
                    dmgback = (int) Math.ceil(unitDmgBack) / 2;
                    System.out.println("visszaküldött dmg: " + dmgback);

                    boolean kikeruli = false;
                    boolean visszaTamadasJelzoGepKetezer = false;
                    //először az odatámadást vesszük fel

                    //region saiyan skill
                    if(Objects.equals(megtamadott.getNev(), "szupercsillagharcos")) {
                        kikeruli = 100 * ((double)rnd.nextInt(100) / 100) < 50;
                    }
                    if(!kikeruli)
                    {
                        Main.gameData.map[GridPane.getRowIndex((Node) event.getSource())][GridPane.getColumnIndex((Node) event.getSource())].setOsszHp(megtamadott.getOsszHp() - dmg);
                        //nézzük meg hogy Halt-e.
                        visszaTamadasJelzoGepKetezer = unitHalt(event, szenvedoUnit, dmg, crit);
                    }
                    else {
                        details1.setText(szenvedoUnit + " KIKERÜLTE A TÁMADÁST!!!!!");
                    }
                    //endregion

                    //region griff skill
                    if(Objects.equals(megtamadott.getNev(), "griff")) {
                        megtamadott.kepesseg();
                    }
                    //endregion

                    //region magus skill

                    if(Objects.equals(currentsr[currentSorszam].getNev(), "magus")) {
                        if(currentsr[currentSorszam].getOsszHp() < currentsr[currentSorszam].getHp() * currentsr[currentSorszam].getDb()) {
                            currentsr[currentSorszam].setOsszHp(currentsr[currentSorszam].getOsszHp() + Main.gameData.pakol.getVarazsero() * 5);
                            details1.setText("A mágus egység támadása során gyógyult " + Main.gameData.pakol.getVarazsero() * 5 + " életerőt.");
                            System.out.println("mágus: gyógyultam");
                        }
                        else {
                            System.out.println("mágus: max hpn vagyok, nem kell gyógyulnom");
                        }

                    }

                    //endregion

                    //visszatámadás momento
                    if(!currentsr[currentSorszam].isTavolHarci()) {
                        visszatamadasKozel = true;
                    }
                    if (!visszaTamadasJelzoGepKetezer && !megtamadott.isVisszatamadtAKorben() && visszatamadasKozel) {
                        visszatamadasKozel = false;
                        int row = 0;
                        int col = 0;
                        for (int i = 0; i < 10; i++) {
                            for (int j = 0; j < 12; j++) {
                                if (Main.gameData.map[i][j] != null && Objects.equals(Main.gameData.map[i][j].getNev(), currentsr[currentSorszam].getNev()) && Main.gameData.map[i][j].getGazda() == Main.gameData.pakol) {
                                    row = i;
                                    System.out.println("i:" + row);
                                    col = j;
                                    System.out.println("j: " + col);
                                }
                            }
                        }
                        if (Main.gameData.map[row][col] != null) {
                            Main.gameData.map[row][col].setOsszHp(Main.gameData.map[row][col].getOsszHp() - dmgback);
                            megtamadott.setVisszatamadtAKorben(true);
                            //nézzük meg hogy Halt-e.
                            if (Main.gameData.map[row][col].getOsszHp() <= 0) {

                                //a progi 0-ra checkel szoval legyen 0

                                Main.gameData.map[row][col].setOsszHp(0);
                                Main.gameData.map[row][col].setDb(0);


                                //tüntessük EL
                                //mező
                                for (Node node : field.getChildren()) {
                                    if (node instanceof ImageView && GridPane.getColumnIndex(node) != null && GridPane.getRowIndex(node) != null) {
                                        if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == col) {
                                            ((ImageView) node).setImage(notnull);
                                            node.setEffect(null);
                                            node.setId(null);
                                            break;
                                        }
                                    }
                                }


                                //map
                                Main.gameData.map[row][col] = null;

                                backattack.setText("HALÁLOS VISSZATÁMADÁS! " + dmg + " sebzés került kiosztásra " + kuldoUnit + " egységnek " + (Main.gameData.pakol == Main.gameData.rplayer ? "1. játékos" : (Main.gameData.multiplayer ? "2. játékos" : "BOT játékos")) + " által!");

                            }
                            //nem halt szoval csak frissitsuk az infokat
                            else {
                                //darabszam
                                backattack.setText("VISSZATÁMADÁS! " + dmgback + " sebzés került kiosztásra " + kuldoUnit + " egységnek " + (Main.gameData.pakol == Main.gameData.rplayer ? "1. játékos" : (Main.gameData.multiplayer ? "2. játékos" : "BOT játékos")) + " által!");

                                boolean vanserult = Main.gameData.map[row][col].getOsszHp() % Main.gameData.map[row][col].getHp() != 0;

                                Main.gameData.map[row][col].setDb(Main.gameData.map[row][col].getOsszHp() / Main.gameData.map[row][col].getHp() + (vanserult ? 1 : 0));

                                //oszt ennyi
                            }
                        }

                    } else {
                        backattack.setText("Nem került sor visszatámadásra!");
                    }
                }else {
                    backattack.setText("Felfoghatatlan dolog történt! Semmi gond, megy tovább minden!");
                }

                //leszedünk minden jelzést és next kör

                for(Node node : field.getChildren()) {
                    if(node instanceof ImageView && GridPane.getColumnIndex(node) != null && GridPane.getRowIndex(node) != null) {
                        if(node.getEffect() == tamadhato) {
                            node.setEffect(null);
                        }

                        if(node.getEffect() == lepheto) {
                            node.setEffect(null);
                        }

                        //a soron levo unit mar nincs soron!!
                        if(node.getEffect() == valasztott) {
                            node.setEffect(null);

                        }
                    }
                }
                unitTamad = false;
                kovetkezo();
            }
            //endregion

            //region villámcsapás támad
            if(villamcsapasTamad) {
                if(megtamadott != null && Main.gameData.pakol.getMana() > Main.gameData.pakol.elerhetoVarazslatok[0].getManaCost()) {
                    megtamadott.setOsszHp(megtamadott.getOsszHp() - Main.gameData.pakol.getVarazsero() * 30);
                    Main.gameData.pakol.setMana(Main.gameData.pakol.getMana() - 5);
                    details1.setText(szenvedoUnit + " meg lett villámcsapva! " + Main.gameData.pakol.getVarazsero() * 30 + " sebzést szenvedett.");
                    lmana.setText(Main.gameData.lplayer.getMana() + " mana");
                    rmana.setText(Main.gameData.rplayer.getMana() + " mana");

                    villamcsapasTamad = false;
                    jelolesTorlese();
                    kovetkezo();
                }
                else {
                    details1.setText("Túl kevés mana a varázsláshoz!");
                }

            }
            //endregion

            //region tűzlabda támad
            if(tuzlabdaTamad) {
                //kéne a pont ami a közepe
                if(Main.gameData.pakol.getMana() > Main.gameData.pakol.elerhetoVarazslatok[1].getManaCost()) {
                    int x = GridPane.getRowIndex((Node)event.getSource());
                    int y = GridPane.getColumnIndex((Node)event.getSource());

                    Egyseg[] erintettek = new Egyseg[9];
                    int k = 0;

                    System.out.println(((Node) event.getSource()).getId());

                    for(int i = Math.max(0, x - 1); i <= Math.min(x + 1, 11); i++) {
                        for(int j = Math.max(0, y - 1); j <= Math.min(y + 1, 9); j++) {
                            for (Node node: field.getChildren()) {
                                if(GridPane.getRowIndex(node) != null & GridPane.getColumnIndex(node) != null && GridPane.getRowIndex(node) == i && GridPane.getColumnIndex(node) == j) {
                                    System.out.printf("van : (%d : %d)\n", i, j);
                                    ((ImageView)node).setEffect(tamadhato);
                                }

                            }
                            if(Main.gameData.map[i][j] != null) {
                                erintettek[k] = Main.gameData.map[i][j];
                                k++;
                                Main.gameData.map[i][j].setOsszHp(Main.gameData.map[i][j].getOsszHp() - Main.gameData.pakol.getVarazsero() * 20);

                                boolean vanserult = Main.gameData.map[i][j].getOsszHp() % Main.gameData.map[i][j].getHp() != 0;
                                Main.gameData.map[i][j].setDb(Main.gameData.map[i][j].getOsszHp() / Main.gameData.map[i][j].getHp() + (vanserult ? 1 : 0));

                                if(Main.gameData.map[i][j].getOsszHp() <= 0) {

                                    Main.gameData.map[i][j].setOsszHp(0);
                                    Main.gameData.map[i][j].setDb(0);

                                    //tüntessük EL
                                    //mező



                                    for (Node node: field.getChildren()) {
                                        if(node instanceof ImageView) {
                                            if(GridPane.getRowIndex(node) != null & GridPane.getColumnIndex(node) != null && GridPane.getRowIndex(node) == i && GridPane.getColumnIndex(node) == j && node.getId() != null) {
                                                ((ImageView) node).setImage(notnull);
                                                ((ImageView) node).setEffect(null);
                                                ((ImageView) node).setId(null);
                                                break;
                                            }
                                        }
                                    }


                                    //map
                                    Main.gameData.map[i][j] = null;
                                }
                            }
                        }
                    }
                    Main.gameData.pakol.setMana(Main.gameData.pakol.getMana() - Main.gameData.pakol.elerhetoVarazslatok[1].getManaCost());
                    lmana.setText(Main.gameData.lplayer.getMana() + " mana");
                    rmana.setText(Main.gameData.rplayer.getMana() + " mana");
                    StringBuilder erintettUnitok = new StringBuilder();

                    for(Egyseg e : erintettek) {
                        if(e != null) {
                            erintettUnitok.append(e.getNev()).append(" (").append(e.getGazda() == Main.gameData.lplayer ? "p1), " : "p2), ");
                        }
                    }
                    details1.setText(erintettUnitok + "sebzést szenvedtek a tűzlabdában! Ami " + Main.gameData.pakol.getVarazsero() * 20 + " sebzés.");

                    tuzlabdaTamad = false;
                    jelolesTorlese();
                    kovetkezo();
                } else {
                    details1.setText("Túl kevés mana a varázsláshoz!");
                }

            }
            //endregion

            //region kötélbilincs támad
            if(kotelblilincsTamad) {
                if(Main.gameData.pakol.getMana() > Main.gameData.pakol.elerhetoVarazslatok[2].getManaCost()) {
                    Main.gameData.map[GridPane.getRowIndex((Node)event.getSource())][GridPane.getColumnIndex((Node)event.getSource())].setKimarad(true);

                    Main.gameData.pakol.setMana(Main.gameData.pakol.getMana() - Main.gameData.pakol.elerhetoVarazslatok[2].getManaCost());
                    details1.setText(szenvedoUnit + " kötélbilincsbe került a következő körig.");
                    lmana.setText(Main.gameData.lplayer.getMana() + " mana");
                    rmana.setText(Main.gameData.rplayer.getMana() + " mana");

                    kotelblilincsTamad = false;
                    jelolesTorlese();
                    kovetkezo();
                }
                else {
                    details1.setText("Túl kevés mana a varázsláshoz!");
                }

            }


            //endregion


        }
        //endregion

    }

    private boolean unitHalt(MouseEvent event, String szenvedoUnit, int dmg, boolean crit) {
        if (Main.gameData.map[GridPane.getRowIndex((Node) event.getSource())][GridPane.getColumnIndex((Node) event.getSource())].getOsszHp() <= 0) {

            //a progi 0-ra checkel szoval legyen 0

            Main.gameData.map[GridPane.getRowIndex((Node) event.getSource())][GridPane.getColumnIndex((Node) event.getSource())].setOsszHp(0);
            Main.gameData.map[GridPane.getRowIndex((Node) event.getSource())][GridPane.getColumnIndex((Node) event.getSource())].setDb(0);


            //tüntessük EL
            //mező
            ((ImageView) event.getSource()).setImage(notnull);
            ((ImageView) event.getSource()).setEffect(null);
            ((ImageView) event.getSource()).setId(null);

            //map
            Main.gameData.map[GridPane.getRowIndex((Node) event.getSource())][GridPane.getColumnIndex((Node) event.getSource())] = null;

            details1.setText("EGYSÉG ELHULLOTT! " + dmg + (crit ? " KRITIKUS" : "") + " sebzés került kiosztásra! " + (Main.gameData.pakol == Main.gameData.lplayer ? "1. játékos" : (Main.gameData.multiplayer ? "2. játékos" : "BOT játékos")) + " által! Ezzel megadta\na kegyelemdöfést az ellenfél " + szenvedoUnit + " számára!");

            return true;

        }
        //nem halt szoval csak frissitsuk az infokat
        else {
            //darabszam
            details1.setText("EGYSÉG SEBZÉS! "+ dmg + (crit ? " KRITIKUS" : "") + " sebzés került kiosztásra " + szenvedoUnit + " egységnek " + (Main.gameData.pakol == Main.gameData.lplayer ? "1. játékos" : (Main.gameData.multiplayer ? "2. játékos" : "BOT játékos")) + " által!");

            boolean vanserult = Main.gameData.map[GridPane.getRowIndex((Node) event.getSource())][GridPane.getColumnIndex((Node) event.getSource())].getOsszHp() % Main.gameData.map[GridPane.getRowIndex((Node) event.getSource())][GridPane.getColumnIndex((Node) event.getSource())].getHp() != 0;

            Main.gameData.map[GridPane.getRowIndex((Node) event.getSource())][GridPane.getColumnIndex((Node) event.getSource())].setDb(Main.gameData.map[GridPane.getRowIndex((Node) event.getSource())][GridPane.getColumnIndex((Node) event.getSource())].getOsszHp() / Main.gameData.map[GridPane.getRowIndex((Node) event.getSource())][GridPane.getColumnIndex((Node) event.getSource())].getHp() + (vanserult ? 1 : 0));

            //oszt ennyi
            return false;
        }
    }
}