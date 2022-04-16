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
    /*@FXML
    ImageView foldmuvesImg;
    @FXML
    ImageView ijaszImg;
    @FXML
    ImageView griffImg;
    @FXML
    ImageView magusImg;
    @FXML
    ImageView saiyanImg;*/

    @FXML
    Label lpley;
    @FXML
    Label rpley;
    @FXML
    Label details;
    @FXML
    Label details1;

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

    private int currentSorszam = 0;
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

        varazslatok = new Button[]{lvillamcsapas, ltuzlabda, lfeltamasztas, lkotelbilincs, lharcimamor};



        for(int i = 0; i<varazslatok.length; i++) {
            varazslatok[i].setDisable(true);
        }
        lvarazslattarto.setOpacity(0);


        notnull = new Image("file:img/notnull.png");

        actions  = new Button[]{ltamadas, lvarakozas, lvarazslas};

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

        player1.setImage(hosimg);
        player2.setImage(hosimg);

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

    @FXML
    public void skillCast() {
        //a tanult modon kijeloljuk az enemy szarjait
        //uthetove tesszuk, bar ezt pihentebben atgondolva lehet mashova rakom

        //barmelyik skillt racastoljuk a unitra, ott megvalositva vagjuk agyon csak meg nem tom mit kene passelni de valszeg
        //egy bool lesz mindenhol :D
        //szoval egy enemytarget function nagy lenne es kb csak at kene passzolni a boolt / global var
        //ugyis van mindegyik gombnak idje szoval majd egy giga switch case megoldja a bajt

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
        currentSorszam++;
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
        int highestKezd = 0;
        String highestKezdName = "";
        boolean megvan = false;
        currentsr = handleSorrend(currentsr);

        //System.out.println("\n\n\n");
        //for(Egyseg e : currentsr) {
            //if(e != null)
                //System.out.println(e.getNev());
        //}

        if(currentSorszam == maxSorszam) {
            currentSorszam = 0;
            kor.setText(++Application.gameData.kor + ". kör");
        }

        if(currentsr[currentSorszam].getDb() == 0) {
            currentSorszam++;
            kovetkezo();
        }
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

        printSorrend(handleSorrend(currentsr));
        String prefix = (Application.gameData.pakol == Application.gameData.lplayer ? "l" : "r");
        details1.setText("");


        //Hova léphet az úr illetve kiket támadhat? TODO FIX
        if(((Node)event.getSource()).getEffect() == valasztott) {
            for (Node node : field.getChildren()) {
                if(node instanceof ImageView) {
                    if(GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) != null && node.getId() == null) {
                        int y = GridPane.getRowIndex((Node)event.getSource());
                        int x = GridPane.getColumnIndex((Node)event.getSource());
                        for(int i = x - GridPane.getColumnIndex(node); i <= x + GridPane.getColumnIndex(node); i++) {
                            for(int j = y - GridPane.getRowIndex(node); j <= y + GridPane.getRowIndex(node); j++) {
                                //if(i >= 0 && j >= 0 && i < 12 && j < 10) {
                                if(Math.abs(i - GridPane.getColumnIndex(node)) + Math.abs(y - GridPane.getRowIndex(node)) <= currentsr[currentSorszam].getSebesseg()) {
                                    (node).setEffect(lepheto);
                                    //  }
                                }
                            }
                        }
                    }
                    if(GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) != null && node.getId() != null && !node.getId().substring(0,1).equals(prefix)) {
                        int y = GridPane.getRowIndex((Node)event.getSource());
                        int x = GridPane.getColumnIndex((Node)event.getSource());
                        for(int i = x - GridPane.getColumnIndex(node); i <= x + GridPane.getColumnIndex(node); i++) {
                            for(int j = y - GridPane.getRowIndex(node); j <= y + GridPane.getRowIndex(node); j++) {
                                //if(i >= 0 && j >= 0 && i < 12 && j < 10) {
                                if(Math.abs(i - GridPane.getColumnIndex(node)) + Math.abs(y - GridPane.getRowIndex(node)) <= currentsr[currentSorszam].getSebesseg()) {
                                    (node).setEffect(tamadhato);
                                    unitTamad = true;
                                    //  }
                                }
                            }
                        }
                    }

                }

            }
        }

        //ACTION: lépés

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

            //mivel action ezért ez a unit mar nem tud mast csinalni a korben
            currentSorszam++;
            kovetkezo();
            return;
        }

        //Kiket támadhatunk ????????
        //specialis skillek :D
        //megvalositas a kovetkezo modon:
        //majd holnap, kiveve ha most eszembejut valami

        //ACTION: TAMADAAAAAAAAAAAAAAAAS
        if(((Node)event.getSource()).getEffect() == tamadhato) {

            String szenvedoUnit = "";
            //unit amit megtamadtak
            switch(Application.gameData.map[GridPane.getRowIndex((Node) event.getSource())][GridPane.getColumnIndex((Node) event.getSource())].getNev()) {
                case "foldmuves" -> szenvedoUnit = "Földműves";
                case "ijasz" -> szenvedoUnit = "Íjász";
                case "griff" -> szenvedoUnit = "Griff";
                case "magus" -> szenvedoUnit = "Mágus";
                case "szupercsillagharcos" -> szenvedoUnit = "Szupercsillagharcos";
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


            //Hős támad
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
                currentSorszam++;
                hosTamad = false;
                kovetkezo();
            }

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


                    //először az odatámadást vesszük fel
                    Application.gameData.map[GridPane.getRowIndex((Node) event.getSource())][GridPane.getColumnIndex((Node) event.getSource())].setOsszHp(megtamadott.getOsszHp() - dmg);
                    //nézzük meg hogy Halt-e.
                    boolean visszaTamadasJelzoGepKetezer = unitHalt(event, szenvedoUnit, dmg, crit);

                    //visszatámadás momento
                    if (!visszaTamadasJelzoGepKetezer) {
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

                                details1.setText("HALÁLOS VISSZATÁMADÁS! " + dmg + " sebzés került kiosztásra " + szenvedoUnit + " egységnek " + (Application.gameData.pakol == Application.gameData.lplayer ? "1. játékos" : (Application.gameData.multiplayer ? "2. játékos" : "BOT játékos")) + " által!");

                            }
                            //nem halt szoval csak frissitsuk az infokat
                            else {
                                //darabszam
                                details1.setText("VISSZATÁMADÁS! " + dmg + " sebzés került kiosztásra " + szenvedoUnit + " egységnek " + (Application.gameData.pakol == Application.gameData.lplayer ? "1. játékos" : (Application.gameData.multiplayer ? "2. játékos" : "BOT játékos")) + " által!");

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
                currentSorszam++;
                unitTamad = false;
                kovetkezo();
            }


        }

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
            details.setText("HŐS SEBZÉS! "+ dmg + (crit ? "KRITKUS" : "") + " sebzés került kiosztásra " + szenvedoUnit + " egységnek " + (Application.gameData.pakol == Application.gameData.lplayer ? "1. játékos" : (Application.gameData.multiplayer ? "2. játékos" : "BOT játékos")) + " által!");

            boolean vanserult = Application.gameData.map[GridPane.getRowIndex((Node) event.getSource())][GridPane.getColumnIndex((Node) event.getSource())].getOsszHp() % Application.gameData.map[GridPane.getRowIndex((Node) event.getSource())][GridPane.getColumnIndex((Node) event.getSource())].getHp() != 0;

            Application.gameData.map[GridPane.getRowIndex((Node) event.getSource())][GridPane.getColumnIndex((Node) event.getSource())].setDb(Application.gameData.map[GridPane.getRowIndex((Node) event.getSource())][GridPane.getColumnIndex((Node) event.getSource())].getOsszHp() / Application.gameData.map[GridPane.getRowIndex((Node) event.getSource())][GridPane.getColumnIndex((Node) event.getSource())].getHp() + (vanserult ? 1 : 0));

            //oszt ennyi
            return false;
        }
    }
}