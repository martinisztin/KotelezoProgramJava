package com.bkyzsa.heroeskotprog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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

    Button[] actions;

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
    static Blend support;

    static Blend p1szin;
    static Blend p2szin;

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
    VBox lvarazslattarto;

    Button[] varazslatok;
    ImageView[] felelesztos;

    int[] lplayerUnits;
    int[] rplayerUnits;
    Egyseg[][] startMap;

    private int currentSorszam = -1;
    private int maxSorszam;
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

        varazslatok = new Button[] {lvillamcsapas, ltuzlabda, lfeltamasztas, lkotelbilincs, lharcimamor};


        for(int i = 0; i<varazslatok.length; i++) {
            varazslatok[i].setDisable(true);
        }
        lvarazslattarto.setOpacity(0);


        notnull = new Image("file:img/notnull.png");

        actions = new Button[]{ltamadas, lvarakozas, lvarazslas};

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


        lplayerUnits = new int[Application.gameData.lplayer.egysegek.length];
        rplayerUnits = new int[Application.gameData.rplayer.egysegek.length];
        startMap = new Egyseg[10][12];

        for (int i = 0; i < 5; i++) {
            int rakosGeci = Application.gameData.lplayer.egysegek[i].getDb(), anyad = Application.gameData.rplayer.egysegek[i].getDb();
            lplayerUnits[i] = rakosGeci;
            rplayerUnits[i] = anyad;
        }

        for(int i = 0; i < 10; i++) {
            for (int j = 0; j < 12; j++) {
                startMap[i][j] = Application.gameData.map[i][j];
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
        rpley.setText(Application.gameData.multiplayer ? "2. játékos" : "BOT játékos");
        rpley.setTextFill(Color.web("ORANGE"));

        for(Button a: actions) {
            a.setDisable(true);
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

        lmana.setText(Application.gameData.lplayer.getMana() + " mana");
        rmana.setText(Application.gameData.rplayer.getMana() + " mana");

        kor.setText("1. kör");

       /* foldmuvesImg.setImage(foldmuves);
        ijaszImg.setImage(ijasz);
        griffImg.setImage(griff);
        magusImg.setImage(magus);
        saiyanImg.setImage(szupercsillagharcos);*/

        Application.gameData.print();

        for(Node node: field.getChildren()) {
            if(node instanceof ImageView) {
                for(Egyseg e : Application.gameData.lplayer.egysegek) {
                    if(GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) != null && GridPane.getColumnIndex(node) < 2
                            && Application.gameData.map[GridPane.getRowIndex(node)][GridPane.getColumnIndex(node)] != null
                            && Application.gameData.map[GridPane.getRowIndex(node)][GridPane.getColumnIndex(node)].getNev().contains(e.getNev())) {

                        ((ImageView)node).setImage(new Image("file:img/" + e.getNev() + ".png"));
                        node.setId("l" + e.getNev());

                    }

                }
            }
        }

        for(Node node: field.getChildren()) {
            if(node instanceof ImageView) {
                for(Egyseg e : Application.gameData.rplayer.egysegek) {
                    if(GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) != null
                            && Application.gameData.map[GridPane.getRowIndex(node)][GridPane.getColumnIndex(node)] != null && GridPane.getColumnIndex(node) > 9
                            && Application.gameData.map[GridPane.getRowIndex(node)][GridPane.getColumnIndex(node)].getNev().contains(e.getNev())) {

                        ((ImageView)node).setImage(new Image("file:img/" + e.getNev() + ".png"));
                        node.setId("r" + e.getNev());

                    }

                }
            }
        }

        currentsr = handleSorrend(sorrendezo());

        kovetkezo();

    }

    private Egyseg[] sorrendezo() {
        Egyseg[] tsorrendall = new Egyseg[10];

        System.arraycopy(Application.gameData.lplayer.egysegek, 0, tsorrendall, 0, 5);
        System.arraycopy(Application.gameData.rplayer.egysegek, 0, tsorrendall, 5, 5);

        for(int i = 0; i < tsorrendall.length; i++) {
            if(tsorrendall[i] != null) {
                if(tsorrendall[i].getGazda() == Application.gameData.lplayer) {
                    tsorrendall[i].setKezdemenyezes(tsorrendall[i].getKezdemenyezes() + Application.gameData.lplayer.getMoral());
                    //System.out.println(tsorrendall[i].getNev() + tsorrendall[i].getKezdemenyezes());
                }
                if(tsorrendall[i].getGazda() == Application.gameData.rplayer) {
                    tsorrendall[i].setKezdemenyezes(tsorrendall[i].getKezdemenyezes() + Application.gameData.rplayer.getMoral());
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

                        if(vegso[i].getGazda() == Application.gameData.lplayer) {
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
                case "lfoldmuves" -> output = Application.gameData.lplayer.egysegek[0].getDb() + " db Földműves\nÉleterejük: "
                        + Application.gameData.lplayer.egysegek[0].getOsszHp() + ", ebből "
                        +  ((Application.gameData.lplayer.egysegek[0].getOsszHp() / Application.gameData.lplayer.egysegek[0].getHp()) == Application.gameData.lplayer.egysegek[0].getDb()
                        ? "mindenki, azaz " + Application.gameData.lplayer.egysegek[0].getDb() + " db sértetlen" : Application.gameData.lplayer.egysegek[0].getDb() - 1 + " sértetlen, és egynek "
                        + Application.gameData.lplayer.egysegek[0].getOsszHp() % Application.gameData.lplayer.egysegek[0].getHp() + " életereje van");

                case "lijasz" -> output = Application.gameData.lplayer.egysegek[1].getDb() + " db Íjász\nÉleterejük: "
                        + Application.gameData.lplayer.egysegek[1].getOsszHp() + ", ebből "
                        +  ((Application.gameData.lplayer.egysegek[1].getOsszHp() / Application.gameData.lplayer.egysegek[1].getHp()) == Application.gameData.lplayer.egysegek[1].getDb()
                        ? "mindenki, azaz " + Application.gameData.lplayer.egysegek[1].getDb() + " db sértetlen" : Application.gameData.lplayer.egysegek[1].getDb() - 1+ " sértetlen, és egynek "
                        + Application.gameData.lplayer.egysegek[1].getOsszHp() % Application.gameData.lplayer.egysegek[1].getHp() + " életereje van");

                case "lgriff" -> output = Application.gameData.lplayer.egysegek[2].getDb() + " db Griff\nÉleterejük: "
                        + Application.gameData.lplayer.egysegek[2].getOsszHp() + ", ebből "
                        +  ((Application.gameData.lplayer.egysegek[2].getOsszHp() / Application.gameData.lplayer.egysegek[2].getHp()) == Application.gameData.lplayer.egysegek[2].getDb()
                        ? "mindenki, azaz " + Application.gameData.lplayer.egysegek[2].getDb() + " db sértetlen" : Application.gameData.lplayer.egysegek[2].getDb() - 1 + " sértetlen, és egynek"
                        + Application.gameData.lplayer.egysegek[2].getOsszHp() % Application.gameData.lplayer.egysegek[2].getHp() + " életereje van");

                case "lmagus" -> output = Application.gameData.lplayer.egysegek[3].getDb() + " db Mágus\nÉleterejük: "
                        + Application.gameData.lplayer.egysegek[3].getOsszHp() + ", ebből "
                        +  ((Application.gameData.lplayer.egysegek[3].getOsszHp() / Application.gameData.lplayer.egysegek[3].getHp()) == Application.gameData.lplayer.egysegek[3].getDb()
                        ? "mindenki, azaz " + Application.gameData.lplayer.egysegek[3].getDb() + " db sértetlen" : Application.gameData.lplayer.egysegek[3].getDb() - 1 + " sértetlen, és egynek "
                        + Application.gameData.lplayer.egysegek[3].getOsszHp() % Application.gameData.lplayer.egysegek[3].getHp() + " életereje van");

                case "lszupercsillagharcos" -> output = Application.gameData.lplayer.egysegek[4].getDb() + " db Szupercsillagharcos\nÉleterejük: "
                        + Application.gameData.lplayer.egysegek[4].getOsszHp() + ", ebből "
                        +  ((Application.gameData.lplayer.egysegek[4].getOsszHp() / Application.gameData.lplayer.egysegek[4].getHp()) == Application.gameData.lplayer.egysegek[4].getDb()
                        ? "mindenki, azaz " + Application.gameData.lplayer.egysegek[4].getDb() + " db sértetlen" : Application.gameData.lplayer.egysegek[4].getDb() - 1 + " sértetlen, és egynek "
                        + Application.gameData.lplayer.egysegek[4].getOsszHp() % Application.gameData.lplayer.egysegek[4].getHp() + " életereje van");

                case "rfoldmuves" -> output = Application.gameData.rplayer.egysegek[0].getDb() + " db Földműves\nÉleterejük: "
                        + Application.gameData.rplayer.egysegek[0].getOsszHp() + ", ebből "
                        +  ((Application.gameData.rplayer.egysegek[0].getOsszHp() / Application.gameData.rplayer.egysegek[0].getHp()) == Application.gameData.rplayer.egysegek[0].getDb()
                        ? "mindenki, azaz " + Application.gameData.rplayer.egysegek[0].getDb() + " db sértetlen" : Application.gameData.rplayer.egysegek[0].getDb() - 1 + " sértetlen, és egynek "
                        + Application.gameData.rplayer.egysegek[0].getOsszHp() % Application.gameData.rplayer.egysegek[0].getHp() + " életereje van");

                case "rijasz" -> output = Application.gameData.rplayer.egysegek[1].getDb() + " db Íjász\nÉleterejük: "
                        + Application.gameData.rplayer.egysegek[1].getOsszHp() + ", ebből "
                        +  ((Application.gameData.rplayer.egysegek[1].getOsszHp() / Application.gameData.rplayer.egysegek[1].getHp()) == Application.gameData.rplayer.egysegek[1].getDb()
                        ? "mindenki, azaz " + Application.gameData.rplayer.egysegek[1].getDb() + " db sértetlen" : Application.gameData.rplayer.egysegek[1].getDb() - 1 + " sértetlen, és egynek "
                        + Application.gameData.rplayer.egysegek[1].getOsszHp() % Application.gameData.rplayer.egysegek[1].getHp() + " életereje van");

                case "rgriff" -> output = Application.gameData.rplayer.egysegek[2].getDb() + " db Griff\nÉleterejük: "
                        + Application.gameData.rplayer.egysegek[2].getOsszHp() + ", ebből "
                        +  ((Application.gameData.rplayer.egysegek[2].getOsszHp() / Application.gameData.rplayer.egysegek[2].getHp()) == Application.gameData.rplayer.egysegek[2].getDb()
                        ? "mindenki, azaz " + Application.gameData.rplayer.egysegek[2].getDb() + " db sértetlen" : Application.gameData.rplayer.egysegek[2].getDb() - 1 + " sértetlen, és egynek "
                        + Application.gameData.rplayer.egysegek[2].getOsszHp() % Application.gameData.rplayer.egysegek[2].getHp() + " életereje van");

                case "rmagus" -> output = Application.gameData.rplayer.egysegek[3].getDb() + " db Mágus\nÉleterejük: "
                        + Application.gameData.rplayer.egysegek[3].getOsszHp() + ", ebből "
                        +  ((Application.gameData.rplayer.egysegek[3].getOsszHp() / Application.gameData.rplayer.egysegek[3].getHp()) == Application.gameData.rplayer.egysegek[3].getDb()
                        ? "mindenki, azaz " + Application.gameData.rplayer.egysegek[3].getDb() + " db sértetlen" : Application.gameData.rplayer.egysegek[3].getDb() - 1 + " sértetlen, és egynek "
                        + Application.gameData.rplayer.egysegek[3].getOsszHp() % Application.gameData.rplayer.egysegek[3].getHp() + " életereje van");

                case "rszupercsillagharcos" -> output = Application.gameData.rplayer.egysegek[4].getDb() + " db Szupercsillagharcos\nÉleterejük: "
                        + Application.gameData.rplayer.egysegek[4].getOsszHp() + ", ebből "
                        +  ((Application.gameData.rplayer.egysegek[4].getOsszHp() / Application.gameData.rplayer.egysegek[4].getHp()) == Application.gameData.rplayer.egysegek[4].getDb()
                        ? "mindenki, azaz " + Application.gameData.rplayer.egysegek[4].getDb() + " db sértetlen" : Application.gameData.rplayer.egysegek[4].getDb() - 1 + " sértetlen, és egynek "
                        + Application.gameData.rplayer.egysegek[4].getOsszHp() % Application.gameData.rplayer.egysegek[4].getHp() + " életereje van");

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
                    if(Application.gameData.map[i][j] != null && Application.gameData.map[i][j].getGazda() != Application.gameData.pakol) {
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

    //TODO befejezés
    @FXML
    public void spellCast(MouseEvent event) {

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

        switch(((Button)event.getSource()).getId()) {
            case "lvillamcsapas" -> villamcsapasTamad = true;
            case "ltuzlabda" ->tuzlabdaTamad = true;
            case "lfeltamasztas" -> feltamasztasTamad = true;
            case "lkotelbilincs" -> kotelblilincsTamad = true;
            case "lharcimamor" -> harcimamorTamad = true;
        }

        if(villamcsapasTamad) {
            for(Node node : field.getChildren()) {
                for(int i = 0; i < 10; i++) {
                    for (int j = 0; j < 12; j++) {
                        if(Application.gameData.map[i][j] != null && Application.gameData.map[i][j].getGazda() != Application.gameData.pakol) {
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
            for(Node node : field.getChildren()) {
                if(GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) != null) {
                    node.setEffect(tamadhato);
                }
            }
        }
        if(feltamasztasTamad) {
            Hos caster = Application.gameData.pakol;

            System.out.println("bejottem feltamasztani");

            lfeltamaszto.setOpacity(1);
            lfeltamaszto.setDisable(false);

            //menjunk vegig az unitokon es nezzuk melyik halott, eltarolas jokerdes
            if(caster == Application.gameData.lplayer) {
                for(int i = 0; i < caster.egysegek.length; i++) {
                    if(caster.egysegek[i].getDb() == 0 && lplayerUnits[i] != 0) {
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
                        if(Application.gameData.map[i][j] != null && Application.gameData.map[i][j].getGazda() != Application.gameData.pakol) {
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
            //actually semmi
        }

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
    public void feleleszt(MouseEvent event) {
        //töröljük a csöves jelöléseket
        jelolesTorlese();


        //ha rakattintasz akkor legyen halott ez pl ellenorizheto opacitasbol like a boss
        if(((Node)event.getSource()).getOpacity() == 1) {
            if(Application.gameData.lplayer.getMana() < Application.gameData.lplayer.elerhetoVarazslatok[2].getManaCost()) {
                info.setText("Túl kevés mana a varázsláshoz!");
                return;
            }
            else {
                Application.gameData.lplayer.setMana(Application.gameData.lplayer.getMana() - Application.gameData.lplayer.elerhetoVarazslatok[2].getManaCost());
                lmana.setText(Application.gameData.lplayer.getMana() + " mana");
            }

            String id = "";
            //mire nyomtál
            switch(((Node)event.getSource()).getId()) {
                case "foldmuvesImg" -> {
                    Application.gameData.lplayer.egysegek[0].setDb(lplayerUnits[0]);
                    int maxHeal = Application.gameData.lplayer.getVarazsero() * 50;
                    int baseHp = Application.gameData.lplayer.egysegek[0].getHp() * Application.gameData.lplayer.egysegek[0].getDb();
                    if(maxHeal > baseHp)
                        Application.gameData.lplayer.egysegek[0].setOsszHp(baseHp);
                    else {
                        Application.gameData.lplayer.egysegek[0].setOsszHp(maxHeal);
                    }
                    for(Node node : field.getChildren()) {
                        if(node instanceof ImageView) {
                            if(GridPane.getColumnIndex(node) != null && GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) == 0
                            && node.getId() == null) {
                                //rakd le ezt a SZART
                                ((ImageView)node).setImage(foldmuves);
                                node.setId("l" + Application.gameData.lplayer.egysegek[0].getNev());

                                //most a mapra
                                Application.gameData.map[GridPane.getRowIndex(node)][GridPane.getColumnIndex(node)] = Application.gameData.lplayer.egysegek[0];
                                break;

                            }
                        }
                    }
                }
                case "ijaszImg" -> {
                    Application.gameData.lplayer.egysegek[1].setDb(lplayerUnits[1]);
                    int maxHeal = Application.gameData.lplayer.getVarazsero() * 50;
                    int baseHp = Application.gameData.lplayer.egysegek[1].getHp() * Application.gameData.lplayer.egysegek[1].getDb();
                    if(maxHeal > baseHp)
                        Application.gameData.lplayer.egysegek[1].setOsszHp(baseHp);
                    else {
                        Application.gameData.lplayer.egysegek[1].setOsszHp(maxHeal);
                    }
                    for(Node node : field.getChildren()) {
                        if(node instanceof ImageView) {
                            if(GridPane.getColumnIndex(node) != null && GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) == 0
                                    && node.getId() == null) {
                                //rakd le ezt a SZART
                                ((ImageView)node).setImage(foldmuves);
                                node.setId("l" + Application.gameData.lplayer.egysegek[1].getNev());

                                //most a mapra
                                Application.gameData.map[GridPane.getRowIndex(node)][GridPane.getColumnIndex(node)] = Application.gameData.lplayer.egysegek[1];
                                break;
                            }
                        }
                    }
                }
                case "griffImg" -> {
                    Application.gameData.lplayer.egysegek[2].setDb(lplayerUnits[2]);
                    int maxHeal = Application.gameData.lplayer.getVarazsero() * 50;
                    int baseHp = Application.gameData.lplayer.egysegek[2].getHp() * Application.gameData.lplayer.egysegek[2].getDb();
                    if(maxHeal > baseHp)
                        Application.gameData.lplayer.egysegek[2].setOsszHp(baseHp);
                    else {
                        Application.gameData.lplayer.egysegek[2].setOsszHp(maxHeal);
                    }
                    for(Node node : field.getChildren()) {
                        if(node instanceof ImageView) {
                            if(GridPane.getColumnIndex(node) != null && GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) == 0
                                    && node.getId() == null) {
                                //rakd le ezt a SZART
                                ((ImageView)node).setImage(foldmuves);
                                node.setId("l" + Application.gameData.lplayer.egysegek[2].getNev());

                                //most a mapra
                                Application.gameData.map[GridPane.getRowIndex(node)][GridPane.getColumnIndex(node)] = Application.gameData.lplayer.egysegek[2];
                                break;
                            }
                        }
                    }
                }
                case "magusImg" -> {
                    Application.gameData.lplayer.egysegek[3].setDb(lplayerUnits[3]);
                    int maxHeal = Application.gameData.lplayer.getVarazsero() * 50;
                    int baseHp = Application.gameData.lplayer.egysegek[3].getHp() * Application.gameData.lplayer.egysegek[3].getDb();
                    if(maxHeal > baseHp)
                        Application.gameData.lplayer.egysegek[3].setOsszHp(baseHp);
                    else {
                        Application.gameData.lplayer.egysegek[3].setOsszHp(maxHeal);
                    }
                    for(Node node : field.getChildren()) {
                        if(node instanceof ImageView) {
                            if(GridPane.getColumnIndex(node) != null && GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) == 0
                                    && node.getId() == null) {
                                //rakd le ezt a SZART
                                ((ImageView)node).setImage(foldmuves);
                                node.setId("l" + Application.gameData.lplayer.egysegek[3].getNev());

                                //most a mapra
                                Application.gameData.map[GridPane.getRowIndex(node)][GridPane.getColumnIndex(node)] = Application.gameData.lplayer.egysegek[3];
                                break;
                            }
                        }
                    }
                }
                case "saiyanImg" -> {
                    Application.gameData.lplayer.egysegek[4].setDb(lplayerUnits[4]);
                    int maxHeal = Application.gameData.lplayer.getVarazsero() * 50;
                    int baseHp = Application.gameData.lplayer.egysegek[4].getHp() * Application.gameData.lplayer.egysegek[4].getDb();
                    if(maxHeal > baseHp)
                        Application.gameData.lplayer.egysegek[4].setOsszHp(baseHp);
                    else {
                        Application.gameData.lplayer.egysegek[4].setOsszHp(maxHeal);
                    }
                    for(Node node : field.getChildren()) {
                        if(node instanceof ImageView) {
                            if(GridPane.getColumnIndex(node) != null && GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) == 0
                                    && node.getId() == null) {
                                //rakd le ezt a SZART
                                ((ImageView)node).setImage(foldmuves);
                                node.setId("l" + Application.gameData.lplayer.egysegek[4].getNev());

                                //most a mapra
                                Application.gameData.map[GridPane.getRowIndex(node)][GridPane.getColumnIndex(node)] = Application.gameData.lplayer.egysegek[4];
                                break;
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
    public void harcimamor(MouseEvent event) {
        //TODO implement

        for(int i = 0; i < 10; i++) {
            for (int j = 0; j < 12; j++) {
                if(Application.gameData.map[i][j].getGazda() == Application.gameData.pakol) {
                    Application.gameData.map[i][j].setSebzesinterval1(Application.gameData.map[i][j].getSebzesinterval1() + Application.gameData.pakol.getVarazsero() * 3);
                    Application.gameData.map[i][j].setSebzesinterval2(Application.gameData.map[i][j].getSebzesinterval2() + Application.gameData.pakol.getVarazsero() * 3);

                    harcimamorTamad = true;
                    Application.gameData.pakol.setHarciMamorAktiv(true);
                    details1.setText(Application.gameData.pakol == Application.gameData.lplayer ? "< " : "" + "A harci mámor ebben a körben aktiválva lett." + (Application.gameData.pakol == Application.gameData.rplayer ? " >" : ""));
                }
            }
        }

    }


    @FXML
    public void hoverOff(MouseEvent event) {
        details.setText("");
    }

    @FXML
    public void varakozas(ActionEvent event) {
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

    @FXML
    public void varazslas(ActionEvent event) {
        lvarazslattarto.setOpacity(1);
        for(int i = 0; i<varazslatok.length; i++) {
            if(Application.gameData.lplayer.elerhetoVarazslatok[i].isVan()) {
                varazslatok[i].setDisable(false);
            }
        }
    }

    @FXML
    public void hoverVarazslasbol(MouseEvent event) {
        lvarazslattarto.setOpacity(0);
        for(int i = 0; i<varazslatok.length; i++) {
            varazslatok[i].setDisable(true);
        }
    }

    private void kovetkezo() {
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
            kor.setText(++Application.gameData.kor + ". kör");

            for(Egyseg e : currentsr) {
                if(e != null)
                    e.setVisszatamadtAKorben(false);
            }

            //region harcimámor kikapcsolás
            if(Application.gameData.lplayer.isHarciMamorAktiv()) {
                for(int i = 0; i < 10; i++) {
                    for (int j = 0; j < 12; j++) {
                        if(Application.gameData.map[i][j].getGazda() == Application.gameData.lplayer) {
                            Application.gameData.map[i][j].setSebzesinterval1(Application.gameData.map[i][j].getSebzesinterval1() - Application.gameData.lplayer.getVarazsero() * 3);
                            Application.gameData.map[i][j].setSebzesinterval2(Application.gameData.map[i][j].getSebzesinterval2() - Application.gameData.lplayer.getVarazsero() * 3);

                            harcimamorTamad = false;
                            Application.gameData.lplayer.setHarciMamorAktiv(false);
                        }
                    }
                }
            }

            if(Application.gameData.rplayer.isHarciMamorAktiv()) {
                for(int i = 0; i < 10; i++) {
                    for (int j = 0; j < 12; j++) {
                        if(Application.gameData.map[i][j].getGazda() == Application.gameData.rplayer) {
                            Application.gameData.map[i][j].setSebzesinterval1(Application.gameData.map[i][j].getSebzesinterval1() - Application.gameData.rplayer.getVarazsero() * 3);
                            Application.gameData.map[i][j].setSebzesinterval2(Application.gameData.map[i][j].getSebzesinterval2() - Application.gameData.rplayer.getVarazsero() * 3);

                            harcimamorTamad = false;
                            Application.gameData.rplayer.setHarciMamorAktiv(false);
                        }
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

        for(int i = 0; i < 10; i++) {
            for (int j = 0; j < 12; j++) {
                if(Application.gameData.map[i][j] != null) {
                    if(Application.gameData.map[i][j].getGazda() == Application.gameData.lplayer)
                        lEloEgysegek++;
                    if(Application.gameData.map[i][j].getGazda() == Application.gameData.rplayer)
                        rEloEgysegek++;
                }
            }
        }

        if(lEloEgysegek != 0 && rEloEgysegek == 0) {
            details.setText("1. játékos nyert!");
        }
        else if (lEloEgysegek == 0 && rEloEgysegek != 0)
        {
            details.setText(Application.gameData.multiplayer ? "2. játékos nyert!" : "BOT játékos nyert!");
        }
        else if(lEloEgysegek == 0 && rEloEgysegek == 0) {
            details.setText("A meccs döntetlennel zárult.");
        }


        //endregion


        //region magus kepesseg (makes no sense kind of meghivas)
        if(Objects.equals(currentsr[currentSorszam].getNev(), "ijasz")) {
            currentsr[currentSorszam].kepesseg();
        }
        //endregion

        /*if(currentsr[currentSorszam].getDb() == 0) {
            if(currentSorszam == currentsr.length)
            currentSorszam++;
            kovetkezo();
        }*/


        //dont ask miert 2 function
        printSorrend(currentsr);

        //System.out.println(currentsr[currentSorszam].getNev() + currentsr[currentSorszam].getDb() + " " + currentsr[currentSorszam].getOsszHp());

        if(currentsr[currentSorszam].getGazda() == Application.gameData.lplayer) {
            Application.gameData.pakol = Application.gameData.lplayer;
        }
        else {
            Application.gameData.pakol = Application.gameData.rplayer;
        }
        for (Node node : field.getChildren()) {
            if (GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) != null
                    && Application.gameData.map[GridPane.getRowIndex(node)][GridPane.getColumnIndex(node)] != null
                    && Application.gameData.map[GridPane.getRowIndex(node)][GridPane.getColumnIndex(node)].getNev().contains(currentsr[currentSorszam].getNev())) {
                if(Application.gameData.pakol == Application.gameData.lplayer) {

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
                        node.setEffect(valasztott);
                    }
                }
            }
        }

        if(Application.gameData.pakol == Application.gameData.lplayer) {
            for(Button a : actions) {
                a.setDisable(false);
                info.setText("1. játékos következik!");
            }
        }
        else {
            for(Button a : actions) {
                a.setDisable(true);
                info.setText("BOT játékos következik!");
            }
        }

    }

    @FXML
    public void valasztottUnitAction(MouseEvent event) {

        //region misc
        printSorrend(handleSorrend(currentsr));
        String prefix = (Application.gameData.pakol == Application.gameData.lplayer ? "l" : "r");
        details1.setText("");
        //endregion



        //region Hova léphet az úr illetve kiket támadhat?
        if(((Node)event.getSource()).getEffect() == valasztott) {
            Egyseg egyseg = Application.gameData.map[GridPane.getRowIndex((Node)event.getSource())][GridPane.getColumnIndex((Node)event.getSource())];
            for (Node node : field.getChildren()) {
                if(node instanceof ImageView) {
                    if(GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) != null) {
                        Egyseg enemy = Application.gameData.map[GridPane.getRowIndex(node)][GridPane.getColumnIndex(node)];
                        int x = GridPane.getColumnIndex((Node)event.getSource());
                        int y = GridPane.getRowIndex((Node)event.getSource());

                        if(Math.abs(x - GridPane.getColumnIndex(node)) + Math.abs(y - GridPane.getRowIndex(node)) <= currentsr[currentSorszam].getSebesseg()
                            && Application.gameData.map[GridPane.getRowIndex(node)][GridPane.getColumnIndex(node)] == null)
                                    (node).setEffect(lepheto);

                        if(enemy != null && enemy.getGazda() != egyseg.getGazda() && (Math.abs(x - GridPane.getColumnIndex(node)) + Math.abs(y - GridPane.getRowIndex(node)) == 1 || egyseg.isTavolHarci())) {
                            (node).setEffect(tamadhato);
                            unitTamad = true;
                        }
                    }
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
                        Application.gameData.map[GridPane.getRowIndex(node)][GridPane.getColumnIndex(node)] = null;

                    }
                }

                //adjuk hozzá es una új
                ((ImageView)event.getSource()).setId(prefix + currentsr[currentSorszam].getNev());
                Application.gameData.map[GridPane.getRowIndex((Node)event.getSource())][GridPane.getColumnIndex((Node)event.getSource())] = currentsr[currentSorszam];

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
            switch(Application.gameData.map[GridPane.getRowIndex((Node) event.getSource())][GridPane.getColumnIndex((Node) event.getSource())].getNev()) {
                case "foldmuves" -> szenvedoUnit = "Földműves";
                case "ijasz" -> szenvedoUnit = "Íjász";
                case "griff" -> szenvedoUnit = "Griff";
                case "magus" -> szenvedoUnit = "Mágus";
                case "szupercsillagharcos" -> szenvedoUnit = "Szupercsillagharcos";
            }

            String kuldoUnit = "";
            //unit amit megtamadtak
            switch(Application.gameData.map[GridPane.getRowIndex((Node) event.getSource())][GridPane.getColumnIndex((Node) event.getSource())].getNev()) {
                case "foldmuves" -> kuldoUnit = "Földműves";
                case "ijasz" -> kuldoUnit = "Íjász";
                case "griff" -> kuldoUnit = "Griff";
                case "magus" -> kuldoUnit = "Mágus";
                case "szupercsillagharcos" -> kuldoUnit = "Szupercsillagharcos";
            }



            //actual megtámadott unit id alapján :D
            Egyseg megtamadott = null;

            switch(((ImageView) event.getSource()).getId()) {
                case "lfoldmuves" -> megtamadott = Application.gameData.lplayer.egysegek[0];
                case "lijasz" -> megtamadott = Application.gameData.lplayer.egysegek[1];
                case "lgriff" -> megtamadott = Application.gameData.lplayer.egysegek[2];
                case "lmagus" -> megtamadott = Application.gameData.lplayer.egysegek[3];
                case "lszupercsillagharcos" -> megtamadott = Application.gameData.lplayer.egysegek[4];

                case "rfoldmuves" -> megtamadott = Application.gameData.rplayer.egysegek[0];
                case "rijasz" -> megtamadott = Application.gameData.rplayer.egysegek[1];
                case "rgriff" -> megtamadott = Application.gameData.rplayer.egysegek[2];
                case "rmagus" -> megtamadott = Application.gameData.rplayer.egysegek[3];
                case "rszupercsillagharcos" -> megtamadott = Application.gameData.rplayer.egysegek[4];
            }
            //endregion

            //region Hős támad
            if (hosTamad) {
                //a hős nem tud kritelni
                boolean crit = false;

                int dmg = Application.gameData.pakol.getTamadas() * 10;
                int currentHp = Application.gameData.map[GridPane.getRowIndex((Node) event.getSource())][GridPane.getColumnIndex((Node) event.getSource())].getOsszHp();

                //az adott unitnak meg kéne változtatni az összhpját
                Application.gameData.map[GridPane.getRowIndex((Node) event.getSource())][GridPane.getColumnIndex((Node) event.getSource())].setOsszHp(currentHp - dmg);

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
                Hos enemy = (Application.gameData.pakol == Application.gameData.lplayer) ? Application.gameData.rplayer : Application.gameData.lplayer;

                //generálunk randomot az intervallumról
                Random rnd = new Random();

                //interval1 + interval2-interval1
                double unitGenDmg = currentsr[currentSorszam].getSebzesinterval1() + (currentsr[currentSorszam].getSebzesinterval2() - currentsr[currentSorszam].getSebzesinterval1() > 0 ? rnd.nextInt(currentsr[currentSorszam].getSebzesinterval2() - currentsr[currentSorszam].getSebzesinterval1()) : 0);
                System.out.println("generált base: " + unitGenDmg);
                //felszorozzuk a darabszámmal
                unitGenDmg *= currentsr[currentSorszam].getDb();
                System.out.println("darabokkal base: " + unitGenDmg);
                //támadási bonusz
                unitGenDmg *= 1 + (double)Application.gameData.pakol.getTamadas() / 10;
                System.out.println("támadás bonusz: " + unitGenDmg);
                //enemy armorját százalákosan vonjuk 5% / pont
                unitGenDmg *= 1 - (0.05 * enemy.getVedekezes());
                System.out.println("kivont védekezéssel: " + unitGenDmg);

                dmg = (int)Math.round(unitGenDmg);
                System.out.println("beküldött dmg: " + dmg);

                double szerencse = 100 * Application.gameData.pakol.getSzerencse() * 0.05; //pl 3 szerencse esetén 15% esélyünk van
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
                    unitDmgBack *= 1 - (0.05 * Application.gameData.pakol.getVedekezes());
                    System.out.println("v kivont védekezéssel: " + unitDmgBack);
                    //kesz
                    dmgback = (int) Math.round(unitDmgBack) / 2;
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
                        Application.gameData.map[GridPane.getRowIndex((Node) event.getSource())][GridPane.getColumnIndex((Node) event.getSource())].setOsszHp(megtamadott.getOsszHp() - dmg);
                        //nézzük meg hogy Halt-e.
                        visszaTamadasJelzoGepKetezer = unitHalt(event, szenvedoUnit, dmg, crit);
                    }
                    else {
                        details.setText(szenvedoUnit + " KIKERÜLTE A TÁMADÁST!!!!!");
                    }
                    //endregion

                    //region griff skill
                    if(Objects.equals(megtamadott.getNev(), "griff")) {
                        megtamadott.kepesseg();
                    }
                    //endregion

                    //region magus skill

                    if(Objects.equals(currentsr[currentSorszam].getNev(), "magus")) {
                        currentsr[currentSorszam].setOsszHp(currentsr[currentSorszam].getOsszHp() + Application.gameData.pakol.getVarazsero() * 5);
                    }

                    //endregion

                    //visszatámadás momento
                    if (!visszaTamadasJelzoGepKetezer && !megtamadott.isVisszatamadtAKorben()) {
                        int row = 0;
                        int col = 0;
                        for (int i = 0; i < 10; i++) {
                            for (int j = 0; j < 12; j++) {
                                if (Application.gameData.map[i][j] != null && Objects.equals(Application.gameData.map[i][j].getNev(), currentsr[currentSorszam].getNev()) && Application.gameData.map[i][j].getGazda() == Application.gameData.pakol) {
                                    row = i;
                                    System.out.println("i:" + row);
                                    col = j;
                                    System.out.println("j: " + col);
                                }
                            }
                        }
                        if (Application.gameData.map[row][col] != null) {
                            Application.gameData.map[row][col].setOsszHp(Application.gameData.map[row][col].getOsszHp() - dmgback);
                            megtamadott.setVisszatamadtAKorben(true);
                            //nézzük meg hogy Halt-e.
                            if (Application.gameData.map[row][col].getOsszHp() <= 0) {

                                //a progi 0-ra checkel szoval legyen 0

                                Application.gameData.map[row][col].setOsszHp(0);
                                Application.gameData.map[row][col].setDb(0);


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
                                Application.gameData.map[row][col] = null;

                                details1.setText("HALÁLOS VISSZATÁMADÁS! " + dmg + " sebzés került kiosztásra " + kuldoUnit + " egységnek " + (Application.gameData.pakol == Application.gameData.rplayer ? "1. játékos" : (Application.gameData.multiplayer ? "2. játékos" : "BOT játékos")) + " által!");

                            }
                            //nem halt szoval csak frissitsuk az infokat
                            else {
                                //darabszam
                                details1.setText("VISSZATÁMADÁS! " + dmg + " sebzés került kiosztásra " + kuldoUnit + " egységnek " + (Application.gameData.pakol == Application.gameData.lplayer ? "1. játékos" : (Application.gameData.multiplayer ? "2. játékos" : "BOT játékos")) + " által!");

                                boolean vanserult = Application.gameData.map[row][col].getOsszHp() % Application.gameData.map[row][col].getHp() != 0;

                                Application.gameData.map[row][col].setDb(Application.gameData.map[row][col].getOsszHp() / Application.gameData.map[row][col].getHp() + (vanserult ? 1 : 0));

                                //oszt ennyi
                            }
                        }

                    } else {
                        details1.setText("Nem került sor visszatámadásra!");
                    }
                }else {
                    details1.setText("Felfoghatatlan dolog történt! Semmi gond, megy tovább minden!");
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
                if(megtamadott != null && Application.gameData.pakol.getMana() > Application.gameData.pakol.elerhetoVarazslatok[0].getManaCost()) {
                    megtamadott.setOsszHp(megtamadott.getOsszHp() - Application.gameData.pakol.getVarazsero() * 30);
                    Application.gameData.pakol.setMana(Application.gameData.pakol.getMana() - 5);
                    details.setText(szenvedoUnit + " meg lett villámcsapva! " + Application.gameData.pakol.getVarazsero() * 30 + " sebzést szenvedett.");
                    lmana.setText(Application.gameData.lplayer.getMana() + " mana");
                    rmana.setText(Application.gameData.rplayer.getMana() + " mana");
                }
                else {
                    details.setText("Túl kevés mana a varázsláshoz!");
                }
                villamcsapasTamad = false;

                jelolesTorlese();

                kovetkezo();
            }
            //endregion

            //region tűzlabda támad
            if(tuzlabdaTamad) {
                //kéne a pont ami a közepe
                if(Application.gameData.pakol.getMana() > Application.gameData.pakol.elerhetoVarazslatok[1].getManaCost()) {
                    int x = GridPane.getRowIndex((Node)event.getSource());
                    int y = GridPane.getColumnIndex((Node)event.getSource());

                    for(int i = Math.max(0, x - 1); i < Math.min(x + 1, 11); i++) {
                        for(int j = Math.max(0, y - 1); j < Math.min(y + 1, 9); j++) {
                            if(Application.gameData.map[i][j] != null) {
                                Application.gameData.map[i][j].setOsszHp(Application.gameData.map[i][j].getOsszHp() - Application.gameData.pakol.getVarazsero() * 20);
                            }
                        }
                    }
                    Application.gameData.pakol.setMana(Application.gameData.pakol.getMana() - Application.gameData.pakol.elerhetoVarazslatok[1].getManaCost());
                    lmana.setText(Application.gameData.lplayer.getMana() + " mana");
                    rmana.setText(Application.gameData.rplayer.getMana() + " mana");
                    details.setText("TŰZLABDAAAAAAAAA!");
                } else {
                    details.setText("Túl kevés mana a varázsláshoz!");
                }
                tuzlabdaTamad = false;
                jelolesTorlese();
                kovetkezo();
            }
            //endregion

            //region kötélbilincs támad
            if(kotelblilincsTamad) {
                if(Application.gameData.pakol.getMana() > Application.gameData.pakol.elerhetoVarazslatok[2].getManaCost()) {
                    Application.gameData.map[GridPane.getRowIndex((Node)event.getSource())][GridPane.getColumnIndex((Node)event.getSource())].setKimarad(true);
                }
                else {
                    details.setText("Túl kevés mana a varázsláshoz!");
                }

                Application.gameData.pakol.setMana(Application.gameData.pakol.getMana() - Application.gameData.pakol.elerhetoVarazslatok[2].getManaCost());
                lmana.setText(Application.gameData.lplayer.getMana() + " mana");
                rmana.setText(Application.gameData.rplayer.getMana() + " mana");
                kotelblilincsTamad = false;

                jelolesTorlese();

                kovetkezo();

            }


            //endregion


        }
        //endregion

    }

    private boolean unitHalt(MouseEvent event, String szenvedoUnit, int dmg, boolean crit) {
        if (Application.gameData.map[GridPane.getRowIndex((Node) event.getSource())][GridPane.getColumnIndex((Node) event.getSource())].getOsszHp() <= 0) {

            //a progi 0-ra checkel szoval legyen 0

            Application.gameData.map[GridPane.getRowIndex((Node) event.getSource())][GridPane.getColumnIndex((Node) event.getSource())].setOsszHp(0);
            Application.gameData.map[GridPane.getRowIndex((Node) event.getSource())][GridPane.getColumnIndex((Node) event.getSource())].setDb(0);


            //tüntessük EL
            //mező
            ((ImageView) event.getSource()).setImage(notnull);
            ((ImageView) event.getSource()).setEffect(null);
            ((ImageView) event.getSource()).setId(null);

            //map
            Application.gameData.map[GridPane.getRowIndex((Node) event.getSource())][GridPane.getColumnIndex((Node) event.getSource())] = null;

            details.setText("EGYSÉG ELHULLOTT! " + dmg + (crit ? "KRITKUS" : "") + " sebzés került kiosztásra! " + (Application.gameData.pakol == Application.gameData.lplayer ? "1. játékos" : (Application.gameData.multiplayer ? "2. játékos" : "BOT játékos")) + " által! Ezzel megadta a kegyelemdöfést az ellenfél " + szenvedoUnit + " számára!");

            return true;

        }
        //nem halt szoval csak frissitsuk az infokat
        else {
            //darabszam
            details.setText("EGYSÉG SEBZÉS! "+ dmg + (crit ? "KRITKUS" : "") + " sebzés került kiosztásra " + szenvedoUnit + " egységnek " + (Application.gameData.pakol == Application.gameData.lplayer ? "1. játékos" : (Application.gameData.multiplayer ? "2. játékos" : "BOT játékos")) + " által!");

            boolean vanserult = Application.gameData.map[GridPane.getRowIndex((Node) event.getSource())][GridPane.getColumnIndex((Node) event.getSource())].getOsszHp() % Application.gameData.map[GridPane.getRowIndex((Node) event.getSource())][GridPane.getColumnIndex((Node) event.getSource())].getHp() != 0;

            Application.gameData.map[GridPane.getRowIndex((Node) event.getSource())][GridPane.getColumnIndex((Node) event.getSource())].setDb(Application.gameData.map[GridPane.getRowIndex((Node) event.getSource())][GridPane.getColumnIndex((Node) event.getSource())].getOsszHp() / Application.gameData.map[GridPane.getRowIndex((Node) event.getSource())][GridPane.getColumnIndex((Node) event.getSource())].getHp() + (vanserult ? 1 : 0));

            //oszt ennyi
            return false;
        }
    }
}