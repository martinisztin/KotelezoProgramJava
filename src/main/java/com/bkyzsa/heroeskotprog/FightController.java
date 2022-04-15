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
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.ResourceBundle;

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
                    System.out.println(tsorrendall[i].getNev() + tsorrendall[i].getKezdemenyezes());
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
                System.out.println(e.getNev() + " (" + (e.getGazda() == Application.gameData.lplayer ? "p1" : "p2") + ") : " + e.getKezdemenyezes());
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
            if(e != null && e.getHp() != 0) {
                db++;
            }
        }
        Egyseg[] vegso = new Egyseg[db];
        int k = 0;

        for(Egyseg e : sorrend) {
            if(e != null && e.getHp() != 0) {
                vegso[k] = e;
                k++;
            }
        }

        maxSorszam = k;

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
                    if(GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) == i - currentSorszam) {
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
                        +  (Application.gameData.lplayer.egysegek[0].getOsszHp() / Application.gameData.lplayer.egysegek[0].getHp() == Application.gameData.lplayer.egysegek[0].getDb()
                        ? "mindenki, azaz " + Application.gameData.lplayer.egysegek[0].getDb() + " db sértetlen" : " sértetlen, és egynek"
                        + Application.gameData.lplayer.egysegek[0].getOsszHp() % Application.gameData.lplayer.egysegek[0].getHp() + " életereje van");

                case "lijasz" -> output = Application.gameData.lplayer.egysegek[1].getDb() + " db Íjász\nÉleterejük: "
                        + Application.gameData.lplayer.egysegek[1].getOsszHp() + ", ebből "
                        +  (Application.gameData.lplayer.egysegek[1].getOsszHp() / Application.gameData.lplayer.egysegek[1].getHp() == Application.gameData.lplayer.egysegek[1].getDb()
                        ? "mindenki, azaz " + Application.gameData.lplayer.egysegek[1].getDb() + " db sértetlen" : " sértetlen, és egynek"
                        + Application.gameData.lplayer.egysegek[1].getOsszHp() % Application.gameData.lplayer.egysegek[1].getHp() + " életereje van");

                case "lgriff" -> output = Application.gameData.lplayer.egysegek[2].getDb() + " db Griff\nÉleterejük: "
                        + Application.gameData.lplayer.egysegek[2].getOsszHp() + ", ebből "
                        +  (Application.gameData.lplayer.egysegek[2].getOsszHp() / Application.gameData.lplayer.egysegek[2].getHp() == Application.gameData.lplayer.egysegek[2].getDb()
                        ? "mindenki, azaz " + Application.gameData.lplayer.egysegek[2].getDb() + " db sértetlen" : " sértetlen, és egynek"
                        + Application.gameData.lplayer.egysegek[2].getOsszHp() % Application.gameData.lplayer.egysegek[2].getHp() + " életereje van");

                case "lmagus" -> output = Application.gameData.lplayer.egysegek[3].getDb() + " db Mágus\nÉleterejük: "
                        + Application.gameData.lplayer.egysegek[3].getOsszHp() + ", ebből "
                        +  (Application.gameData.lplayer.egysegek[3].getOsszHp() / Application.gameData.lplayer.egysegek[3].getHp() == Application.gameData.lplayer.egysegek[3].getDb()
                        ? "mindenki, azaz " + Application.gameData.lplayer.egysegek[3].getDb() + " db sértetlen" : " sértetlen, és egynek"
                        + Application.gameData.lplayer.egysegek[3].getOsszHp() % Application.gameData.lplayer.egysegek[3].getHp() + " életereje van");

                case "lszupercsillagharcos" -> output = Application.gameData.lplayer.egysegek[4].getDb() + " db Szupercsillagharcos\nÉleterejük: "
                        + Application.gameData.lplayer.egysegek[4].getOsszHp() + ", ebből "
                        +  (Application.gameData.lplayer.egysegek[4].getOsszHp() / Application.gameData.lplayer.egysegek[4].getHp() == Application.gameData.lplayer.egysegek[4].getDb()
                        ? "mindenki, azaz " + Application.gameData.lplayer.egysegek[4].getDb() + " db sértetlen" : " sértetlen, és egynek"
                        + Application.gameData.lplayer.egysegek[4].getOsszHp() % Application.gameData.lplayer.egysegek[4].getHp() + " életereje van");

                case "rfoldmuves" -> output = Application.gameData.rplayer.egysegek[0].getDb() + " db Földműves\nÉleterejük: "
                        + Application.gameData.rplayer.egysegek[0].getOsszHp() + ", ebből "
                        +  (Application.gameData.rplayer.egysegek[0].getOsszHp() / Application.gameData.rplayer.egysegek[0].getHp() == Application.gameData.rplayer.egysegek[0].getDb()
                        ? "mindenki, azaz " + Application.gameData.rplayer.egysegek[0].getDb() + " db sértetlen" : " sértetlen, és egynek"
                        + Application.gameData.rplayer.egysegek[0].getOsszHp() % Application.gameData.rplayer.egysegek[0].getHp() + " életereje van");

                case "rijasz" -> output = Application.gameData.rplayer.egysegek[1].getDb() + " db Íjász\nÉleterejük: "
                        + Application.gameData.rplayer.egysegek[1].getOsszHp() + ", ebből "
                        +  (Application.gameData.rplayer.egysegek[1].getOsszHp() / Application.gameData.rplayer.egysegek[1].getHp() == Application.gameData.rplayer.egysegek[1].getDb()
                        ? "mindenki, azaz " + Application.gameData.rplayer.egysegek[1].getDb() + " db sértetlen" : " sértetlen, és egynek"
                        + Application.gameData.rplayer.egysegek[1].getOsszHp() % Application.gameData.rplayer.egysegek[1].getHp() + " életereje van");

                case "rgriff" -> output = Application.gameData.rplayer.egysegek[2].getDb() + " db Griff\nÉleterejük: "
                        + Application.gameData.lplayer.egysegek[2].getOsszHp() + ", ebből "
                        +  (Application.gameData.rplayer.egysegek[2].getOsszHp() / Application.gameData.rplayer.egysegek[2].getHp() == Application.gameData.rplayer.egysegek[2].getDb()
                        ? "mindenki, azaz " + Application.gameData.rplayer.egysegek[2].getDb() + " db sértetlen" : " sértetlen, és egynek"
                        + Application.gameData.rplayer.egysegek[2].getOsszHp() % Application.gameData.rplayer.egysegek[2].getHp() + " életereje van");

                case "rmagus" -> output = Application.gameData.rplayer.egysegek[3].getDb() + " db Mágus\nÉleterejük: "
                        + Application.gameData.lplayer.egysegek[3].getOsszHp() + ", ebből "
                        +  (Application.gameData.rplayer.egysegek[3].getOsszHp() / Application.gameData.rplayer.egysegek[3].getHp() == Application.gameData.rplayer.egysegek[3].getDb()
                        ? "mindenki, azaz " + Application.gameData.rplayer.egysegek[3].getDb() + " db sértetlen" : " sértetlen, és egynek"
                        + Application.gameData.rplayer.egysegek[3].getOsszHp() % Application.gameData.rplayer.egysegek[3].getHp() + " életereje van");

                case "rszupercsillagharcos" -> output = Application.gameData.rplayer.egysegek[4].getDb() + " db Szupercsillagharcos\nÉleterejük: "
                        + Application.gameData.lplayer.egysegek[4].getOsszHp() + ", ebből "
                        +  (Application.gameData.rplayer.egysegek[4].getOsszHp() / Application.gameData.rplayer.egysegek[4].getHp() == Application.gameData.rplayer.egysegek[4].getDb()
                        ? "mindenki, azaz " + Application.gameData.rplayer.egysegek[4].getDb() + " db sértetlen" : " sértetlen, és egynek"
                        + Application.gameData.rplayer.egysegek[4].getOsszHp() % Application.gameData.rplayer.egysegek[4].getHp() + " életereje van");

                default -> output = "";
            }
        }
        details.setText(output);

    }

    @FXML
    public void hosTamadas(ActionEvent event) {
        //rá nincs hatással az armor mer ő hős


        //legyen tamadhato minden enemy unit
        for(Node node : field.getChildren()) {
            if(node instanceof )
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
            //lepheto mezok ha veletlen kattintott a retardalt
            if(GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) != null && (node.getEffect() == lepheto)) {
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

        if(currentSorszam == maxSorszam) {
            currentSorszam = 0;
            kor.setText(++Application.gameData.kor + ". kör");
        }
        //dont ask miert 2 function
        printSorrend(currentsr);

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


        //Hova léphet az úr? TODO FIX
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
                if(GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) != null && (node.getEffect() == valasztott)) {
                    node.setEffect(null);

                }
            }

            //mivel action ezért ez a unit mar nem tud mast csinalni a korben
            currentSorszam++;
            kovetkezo();
            return;
        }

        //Kiket támadhatunk ????????


        //ACTION: TAMADAAAAAAAAAAAAAAAAS
        if(((Node)event.getSource()).getEffect() == tamadhato) {

        }



    }
}
