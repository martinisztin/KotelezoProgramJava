package com.bkyzsa.heroeskotprog.controllers;

import com.bkyzsa.heroeskotprog.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * A shop.fxml controller osztálya. Tartalmazza a játékbeli objektumok bolt rendszerét.
 * Ezekben a controllerekben találhatók a grafikus kinézethez tartozó backend funkciók.
 * @author Isztin Martin
 * @version 1.0
 */

public class ShopController implements Initializable {

    @FXML
    public Label nehezseg;
    @FXML
    public Label egyenleg;
    @FXML
    public Label info;

    //region hero pontok

    @FXML
    public Label tamadas;

    @FXML
    public Label vedekezes;

    @FXML
    public Label varazsero;

    @FXML
    public Label tudas;

    @FXML
    public Label moral;

    @FXML
    public Label szerencse;
    //endregion

    //region egység txtf

    @FXML
    public TextField foldmuves;
    @FXML
    public TextField ijasz;
    @FXML
    public TextField griff;
    @FXML
    public TextField magus;
    @FXML
    public TextField szupercsillagharcos;
    //endregion

    //region varazslat checkboxok
    @FXML
    public CheckBox tuzlabda;
    @FXML
    public CheckBox villamcsapas;
    @FXML
    public CheckBox feltamasztas;
    @FXML
    public CheckBox kotelbilincs;
    @FXML
    public CheckBox harcimamor;
    //endregion

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String nehezsegS = switch (Main.gameData.pakol.getArany()) {
            case 1300 -> "Könnyű";
            case 1000 -> "Közepes";
            case 700 -> "Nehéz";
            default -> "";
        };

        nehezseg.setText("Válaszott nehézség - " + nehezsegS);
        egyenleg.setText("Egyenleg: " + Main.gameData.pakol.getArany() + " arany");
        foldmuves.setText("0");
        ijasz.setText("0");
        griff.setText("0");
        magus.setText("0");
        szupercsillagharcos.setText("0");
        info.setText("");
    }

    public static int kivalasztottArany = Main.gameData.pakol.getArany();

    private int arReturnerById(String content) {
        switch(content) {
            case "foldmuves" -> {
                return Main.gameData.pakol.egysegek[0].getAr();
            }
            case "ijasz" -> {
                return Main.gameData.pakol.egysegek[1].getAr();
            }
            case "griff" -> {
                return Main.gameData.pakol.egysegek[2].getAr();
            }
            case "magus" -> {
                return Main.gameData.pakol.egysegek[3].getAr();
            }
            case "szupercsillagharcos" -> {
                return Main.gameData.pakol.egysegek[4].getAr();
            }

            case "villamcsapas" -> {
                return Main.gameData.pakol.elerhetoVarazslatok[0].getAr();
            }
            case "tuzlabda" -> {
                return Main.gameData.pakol.elerhetoVarazslatok[1].getAr();
            }
            case "feltamasztas" -> {
                return Main.gameData.pakol.elerhetoVarazslatok[2].getAr();
            }
            case "kotelbilincs" -> {
                return Main.gameData.pakol.elerhetoVarazslatok[3].getAr();
            }
            case "harcimamor" -> {
                return Main.gameData.pakol.elerhetoVarazslatok[4].getAr();
            }

        }

        return 0;
    }

    @FXML
    public void hoverOn(MouseEvent event) {
        switch(((Label)event.getSource()).getText()) {
            case "Tűzlabda" -> info.setText(Main.gameData.pakol.elerhetoVarazslatok[0].toString());
            case "Villámcsapás" -> info.setText(Main.gameData.pakol.elerhetoVarazslatok[1].toString());
            case "Feltámasztás" -> info.setText(Main.gameData.pakol.elerhetoVarazslatok[2].toString());
            case "Kötélbilincs" -> info.setText(Main.gameData.pakol.elerhetoVarazslatok[3].toString());
            case "Harci Mámor" -> info.setText(Main.gameData.pakol.elerhetoVarazslatok[4].toString());

            case "Földműves" -> info.setText(Main.gameData.pakol.egysegek[0].toString());
            case "Íjász" -> info.setText(Main.gameData.pakol.egysegek[1].toString());
            case "Griff" -> info.setText(Main.gameData.pakol.egysegek[2].toString());
            case "Mágus" -> info.setText(Main.gameData.pakol.egysegek[3].toString());
            case "Szupercsillagharcos" -> info.setText(Main.gameData.pakol.egysegek[4].toString());

            case "Támadás" -> info.setText("az egységek sebzését növeli meg, tulajdonságpontonként 10%-kal.");
            case "Védekezés" -> info.setText("az egységeket ért sebzést csökkenti, tulajdonságpontonként 5%-kal.");
            case "Varázserő" -> info.setText("a hős által idézett varázslatok erősségét növeli.");
            case "Tudás" -> info.setText("a hős maximális mannapontjait növeli, tulajdonságpontonként 10-zel");
            case "Morál" -> info.setText(" az egységek kezdeményezését növeli, tulajdonságpontonként 1-gyel.");
            case "Szerencse" -> info.setText("az egységek kritikus támadásának esélyét növeli, tulajdonságpontonként 5%-kal.");
        }
    }

    @FXML
    public void hoverOff(MouseEvent event) {
        info.setText("");
    }

    @FXML
    public int vasarlas() {
        Label[] skills = {tamadas, vedekezes, varazsero, tudas, moral, szerencse};
        int skillek = 0;

        for(Label skill : skills) {
            skillek += Integer.parseInt(skill.getText());
        }
        skillek -= 6;
        //System.out.println(skillek);

        int sum = 0;
        int statusz = Main.gameData.pakol.statuszAr;

        for(int i = 0; i < skillek; i++) {
            sum += statusz;
            statusz = (int) Math.ceil(statusz * 1.1);
        }

        TextField[] units = {foldmuves, ijasz, griff, magus, szupercsillagharcos};

        for(TextField unit : units) {
            sum += arReturnerById(unit.getId()) * Integer.parseInt(unit.getText());
            System.out.println(unit.getId() + " ára: " + arReturnerById(unit.getId()) * Integer.parseInt(unit.getText()));
            System.out.println("födmüves: " + Integer.parseInt(unit.getText()));
        }

        CheckBox[] spells = {villamcsapas, tuzlabda, feltamasztas, kotelbilincs, harcimamor};

        for(CheckBox spell : spells) {
            sum += arReturnerById(spell.getId()) * (spell.isSelected() ? 1 : 0);
            System.out.println(spell.getId() + " ára: " + arReturnerById(spell.getId()) * (spell.isSelected() ? 1 : 0));
        }


        return sum;
    }

    @FXML
    public void textBoxKijelolo(MouseEvent event) {
        ((TextField)event.getSource()).selectAll();
    }

    @FXML
    public void egysegVasarlo(KeyEvent event) {
        try {
            int input = Integer.parseInt(((TextField)event.getSource()).getText());
        } catch (Exception e) {
            System.err.println("Ne buziskodj");
            ((TextField)event.getSource()).setText("0");
            ((TextField)event.getSource()).selectAll();
        }

        if(Objects.equals(((TextField) event.getSource()).getText(), "")) {
            ((TextField)event.getSource()).setText("0");
            ((TextField)event.getSource()).selectAll();
        }
        int input = Integer.parseInt(((TextField)event.getSource()).getText());

        if(input < 0) {
            input = 0;
            ((TextField)event.getSource()).setText(input + "");
            ((TextField)event.getSource()).selectAll();
        }

        while(vasarlas() > kivalasztottArany) {
            input = Integer.parseInt(((TextField)event.getSource()).getText());
            input--;
            ((TextField)event.getSource()).setText(input + "");
            System.out.println("mi a gecit csinálok itt");
        }

        Main.gameData.pakol.setArany(kivalasztottArany - vasarlas());
        egyenleg.setText("Egyenleg: " + Main.gameData.pakol.getArany() + " arany");

        System.out.println("mindent megkeráztam + " + vasarlas());
    }

    @FXML
    public void spellVasarlo(ActionEvent event) {
        Main.gameData.pakol.setArany(kivalasztottArany - vasarlas());

        if(Main.gameData.pakol.getArany() < 0) {
            ((CheckBox)event.getSource()).setSelected(false);
                Main.gameData.pakol.setArany(kivalasztottArany - vasarlas());
        }
        egyenleg.setText("Egyenleg: " + Main.gameData.pakol.getArany() + " arany");
    }


    //region statuszadogato
    @FXML
    public void addTamadas() {
        if(Main.gameData.pakol.getTamadas() < 10) {
            Main.gameData.pakol.setTamadas(Main.gameData.pakol.getTamadas() + 1);
            tamadas.setText("" + Main.gameData.pakol.getTamadas());
            Main.gameData.pakol.setArany(kivalasztottArany - vasarlas());
        }
        if(Main.gameData.pakol.getArany() < 0) {
            Main.gameData.pakol.setTamadas(Main.gameData.pakol.getTamadas() - 1);
            tamadas.setText("" + Main.gameData.pakol.getTamadas());
            Main.gameData.pakol.setArany(kivalasztottArany - vasarlas());
        }
        egyenleg.setText("Egyenleg: " + Main.gameData.pakol.getArany() + " arany");
    }

    @FXML
    public void removeTamadas() {
        if(Main.gameData.pakol.getTamadas() > 1) {
            Main.gameData.pakol.setTamadas(Main.gameData.pakol.getTamadas() - 1);
            tamadas.setText("" + Main.gameData.pakol.getTamadas());
            Main.gameData.pakol.setArany(kivalasztottArany - vasarlas());
        }
        egyenleg.setText("Egyenleg: " + Main.gameData.pakol.getArany() + " arany");
    }

    @FXML
    public void addVedekezes() {
        if(Main.gameData.pakol.getVedekezes() < 10) {
            Main.gameData.pakol.setVedekezes(Main.gameData.pakol.getVedekezes() + 1);
            vedekezes.setText("" + Main.gameData.pakol.getVedekezes());
            Main.gameData.pakol.setArany(kivalasztottArany - vasarlas());
        }
        if(Main.gameData.pakol.getArany() < 0) {
            Main.gameData.pakol.setVedekezes(Main.gameData.pakol.getVedekezes() - 1);
            vedekezes.setText("" + Main.gameData.pakol.getVedekezes());
            Main.gameData.pakol.setArany(kivalasztottArany - vasarlas());
        }
        egyenleg.setText("Egyenleg: " + Main.gameData.pakol.getArany() + " arany");

    }

    @FXML
    public void removeVedekezes() {
        if(Main.gameData.pakol.getVedekezes() > 1) {
            Main.gameData.pakol.setVedekezes(Main.gameData.pakol.getVedekezes() - 1);
            vedekezes.setText("" + Main.gameData.pakol.getVedekezes());
            Main.gameData.pakol.setArany(kivalasztottArany - vasarlas());
        }
        egyenleg.setText("Egyenleg: " + Main.gameData.pakol.getArany() + " arany");
    }

    @FXML
    public void addVarazsero() {
        if(Main.gameData.pakol.getVarazsero() < 10) {
            Main.gameData.pakol.setVarazsero(Main.gameData.pakol.getVarazsero() + 1);
            varazsero.setText("" + Main.gameData.pakol.getVarazsero());
            Main.gameData.pakol.setArany(kivalasztottArany - vasarlas());
        }
        if(Main.gameData.pakol.getArany() < 0) {
            Main.gameData.pakol.setVarazsero(Main.gameData.pakol.getVarazsero() - 1);
            varazsero.setText("" + Main.gameData.pakol.getVarazsero());
            Main.gameData.pakol.setArany(kivalasztottArany - vasarlas());
        }
        egyenleg.setText("Egyenleg: " + Main.gameData.pakol.getArany() + " arany");

    }

    @FXML
    public void removeVarazsero() {
        if(Main.gameData.pakol.getVarazsero() > 1) {
            Main.gameData.pakol.setVarazsero(Main.gameData.pakol.getVarazsero() - 1);
            varazsero.setText("" + Main.gameData.pakol.getVarazsero());
            Main.gameData.pakol.setArany(kivalasztottArany - vasarlas());
        }
        egyenleg.setText("Egyenleg: " + Main.gameData.pakol.getArany() + " arany");
    }

    @FXML
    public void addTudas() {
        if(Main.gameData.pakol.getTudas() < 10) {
            Main.gameData.pakol.setTudas(Main.gameData.pakol.getTudas() + 1);
            tudas.setText("" + Main.gameData.pakol.getTudas());
            Main.gameData.pakol.setArany(kivalasztottArany - vasarlas());
        }

        if(Main.gameData.pakol.getArany() < 0) {
            Main.gameData.pakol.setTudas(Main.gameData.pakol.getTudas() - 1);
            tudas.setText("" + Main.gameData.pakol.getTudas());
            Main.gameData.pakol.setArany(kivalasztottArany - vasarlas());
        }

        egyenleg.setText("Egyenleg: " + Main.gameData.pakol.getArany() + " arany");

    }

    @FXML
    public void removeTudas() {
        if(Main.gameData.pakol.getTudas() > 1) {
            Main.gameData.pakol.setTudas(Main.gameData.pakol.getTudas() - 1);
            tudas.setText("" + Main.gameData.pakol.getTudas());
            Main.gameData.pakol.setArany(kivalasztottArany - vasarlas());
        }
        egyenleg.setText("Egyenleg: " + Main.gameData.pakol.getArany() + " arany");
    }

    @FXML
    public void addMoral() {
        if(Main.gameData.pakol.getMoral() < 10) {
            Main.gameData.pakol.setMoral(Main.gameData.pakol.getMoral() + 1);
            moral.setText("" + Main.gameData.pakol.getMoral());
            Main.gameData.pakol.setArany(kivalasztottArany - vasarlas());
        }
        if(Main.gameData.pakol.getArany() < 0) {
            Main.gameData.pakol.setMoral(Main.gameData.pakol.getMoral() - 1);
            moral.setText("" + Main.gameData.pakol.getMoral());
            Main.gameData.pakol.setArany(kivalasztottArany - vasarlas());
        }
        egyenleg.setText("Egyenleg: " + Main.gameData.pakol.getArany() + " arany");

    }

    @FXML
    public void removeMoral() {
        if(Main.gameData.pakol.getMoral() > 1) {
            Main.gameData.pakol.setMoral(Main.gameData.pakol.getMoral() - 1);
            moral.setText("" + Main.gameData.pakol.getMoral());
            Main.gameData.pakol.setArany(kivalasztottArany - vasarlas());
        }
        egyenleg.setText("Egyenleg: " + Main.gameData.pakol.getArany() + " arany");
    }

    @FXML
    public void addSzerencse() {
        if(Main.gameData.pakol.getSzerencse() < 10) {
            Main.gameData.pakol.setSzerencse(Main.gameData.pakol.getSzerencse() + 1);
            szerencse.setText("" + Main.gameData.pakol.getSzerencse());
            Main.gameData.pakol.setArany(kivalasztottArany - vasarlas());
        }
        if(Main.gameData.pakol.getArany() < 0) {
            Main.gameData.pakol.setSzerencse(Main.gameData.pakol.getSzerencse() - 1);
            szerencse.setText("" + Main.gameData.pakol.getSzerencse());
            Main.gameData.pakol.setArany(kivalasztottArany - vasarlas());
        }
        egyenleg.setText("Egyenleg: " + Main.gameData.pakol.getArany() + " arany");

    }

    @FXML
    public void removeSzerencse() {
        if(Main.gameData.pakol.getSzerencse() > 1) {
            Main.gameData.pakol.setSzerencse(Main.gameData.pakol.getSzerencse() - 1);
            szerencse.setText("" + Main.gameData.pakol.getSzerencse());
            Main.gameData.pakol.setArany(kivalasztottArany - vasarlas());
        }
        egyenleg.setText("Egyenleg: " + Main.gameData.pakol.getArany() + " arany");
    }
    //endregion

    @FXML
    public void harcPrepare(ActionEvent event) throws IOException {
        int egysegCount = 0;

        TextField[] units = {foldmuves, ijasz, griff, magus, szupercsillagharcos};

        for(TextField unit : units) {
            egysegCount += Integer.parseInt(unit.getText());
        }
        if(egysegCount == 0) {
            info.setText("Vásárolnod kell egységeket mielőtt harcba szállsz!");
        }
        else {

            Main.gameData.pakol.setTamadas(Integer.parseInt(tamadas.getText()));
            Main.gameData.pakol.setVedekezes(Integer.parseInt(vedekezes.getText()));
            Main.gameData.pakol.setVarazsero(Integer.parseInt(varazsero.getText()));
            Main.gameData.pakol.setTudas(Integer.parseInt(tudas.getText()));
            Main.gameData.pakol.setMoral(Integer.parseInt(moral.getText()));
            Main.gameData.pakol.setSzerencse(Integer.parseInt(szerencse.getText()));

            Main.gameData.pakol.setElerhetoVarazslatok(villamcsapas.isSelected(), tuzlabda.isSelected(), feltamasztas.isSelected(), kotelbilincs.isSelected(), harcimamor.isSelected());

            Main.gameData.pakol.setEgysegek(Main.gameData.pakol, Integer.parseInt(foldmuves.getText()), Integer.parseInt(ijasz.getText()), Integer.parseInt(griff.getText()), Integer.parseInt(magus.getText()), Integer.parseInt(szupercsillagharcos.getText()));

            Main.gameData.pakol.egysegek[0].setOsszHp(Main.gameData.pakol.egysegek[0].getHp() * Main.gameData.pakol.egysegek[0].getDb());
            Main.gameData.pakol.egysegek[1].setOsszHp(Main.gameData.pakol.egysegek[1].getHp() * Main.gameData.pakol.egysegek[1].getDb());
            Main.gameData.pakol.egysegek[2].setOsszHp(Main.gameData.pakol.egysegek[2].getHp() * Main.gameData.pakol.egysegek[2].getDb());
            Main.gameData.pakol.egysegek[3].setOsszHp(Main.gameData.pakol.egysegek[3].getHp() * Main.gameData.pakol.egysegek[3].getDb());
            Main.gameData.pakol.egysegek[4].setOsszHp(Main.gameData.pakol.egysegek[4].getHp() * Main.gameData.pakol.egysegek[4].getDb());

            Main.gameData.pakol.setMana(Main.gameData.pakol.getTudas() * 10);

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("field.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setResizable(false);
            stage.setScene(scene);

        }


    }

}
