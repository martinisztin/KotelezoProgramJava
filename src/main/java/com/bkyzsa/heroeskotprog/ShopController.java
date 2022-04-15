package com.bkyzsa.heroeskotprog;

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
        String nehezsegS = switch (Application.playerHos.getArany()) {
            case 1300 -> "Könnyű";
            case 1000 -> "Közepes";
            case 700 -> "Nehéz";
            default -> "";
        };

        nehezseg.setText("Válaszott nehézség - " + nehezsegS);
        egyenleg.setText("Egyenleg: " + Application.gameData.lplayer.getArany() + " arany");
        foldmuves.setText("0");
        ijasz.setText("0");
        griff.setText("0");
        magus.setText("0");
        szupercsillagharcos.setText("0");
        info.setText("");
    }

    public static int kivalasztottArany = Application.playerHos.getArany();

    private int arReturnerById(String content) {
        switch(content) {
            case "foldmuves" -> {
                return Application.gameData.lplayer.egysegek[0].getAr();
            }
            case "ijasz" -> {
                return Application.gameData.lplayer.egysegek[1].getAr();
            }
            case "griff" -> {
                return Application.gameData.lplayer.egysegek[2].getAr();
            }
            case "magus" -> {
                return Application.gameData.lplayer.egysegek[3].getAr();
            }
            case "szupercsillagharcos" -> {
                return Application.gameData.lplayer.egysegek[4].getAr();
            }

            case "villamcsapas" -> {
                return Application.gameData.lplayer.elerhetoVarazslatok[0].getAr();
            }
            case "tuzlabda" -> {
                return Application.gameData.lplayer.elerhetoVarazslatok[1].getAr();
            }
            case "feltamasztas" -> {
                return Application.gameData.lplayer.elerhetoVarazslatok[2].getAr();
            }
            case "kotelbilincs" -> {
                return Application.gameData.lplayer.elerhetoVarazslatok[3].getAr();
            }
            case "harcimamor" -> {
                return Application.gameData.lplayer.elerhetoVarazslatok[4].getAr();
            }

        }

        return 0;
    }

    @FXML
    public void hoverOn(MouseEvent event) {
        switch(((Label)event.getSource()).getText()) {
            case "Tűzlabda" -> info.setText(Application.gameData.lplayer.elerhetoVarazslatok[0].toString());
            case "Villámcsapás" -> info.setText(Application.gameData.lplayer.elerhetoVarazslatok[1].toString());
            case "Feltámasztás" -> info.setText(Application.gameData.lplayer.elerhetoVarazslatok[2].toString());
            case "Kötélbilincs" -> info.setText(Application.gameData.lplayer.elerhetoVarazslatok[3].toString());
            case "Harci Mámor" -> info.setText(Application.gameData.lplayer.elerhetoVarazslatok[4].toString());

            case "Földműves" -> info.setText(Application.gameData.lplayer.egysegek[0].toString());
            case "Íjász" -> info.setText(Application.gameData.lplayer.egysegek[1].toString());
            case "Griff" -> info.setText(Application.gameData.lplayer.egysegek[2].toString());
            case "Mágus" -> info.setText(Application.gameData.lplayer.egysegek[3].toString());
            case "Szupercsillagharcos" -> info.setText(Application.gameData.lplayer.egysegek[4].toString());

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
        int statusz = Application.gameData.lplayer.statuszAr;

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

        Application.gameData.lplayer.setArany(kivalasztottArany - vasarlas());
        egyenleg.setText("Egyenleg: " + Application.gameData.lplayer.getArany() + " arany");

        System.out.println("mindent megkeráztam + " + vasarlas());
    }

    @FXML
    public void spellVasarlo(ActionEvent event) {
        Application.gameData.lplayer.setArany(kivalasztottArany - vasarlas());

        if(Application.gameData.lplayer.getArany() < 0) {
            ((CheckBox)event.getSource()).setSelected(false);
                Application.gameData.lplayer.setArany(kivalasztottArany - vasarlas());
        }
        egyenleg.setText("Egyenleg: " + Application.gameData.lplayer.getArany() + " arany");
    }


    //region statuszadogato
    @FXML
    public void addTamadas() {
        if(Application.gameData.lplayer.getTamadas() < 10) {
            Application.gameData.lplayer.setTamadas(Application.gameData.lplayer.getTamadas() + 1);
            tamadas.setText("" + Application.gameData.lplayer.getTamadas());
            Application.gameData.lplayer.setArany(kivalasztottArany - vasarlas());
        }
        if(Application.gameData.lplayer.getArany() < 0) {
            Application.gameData.lplayer.setTamadas(Application.gameData.lplayer.getTamadas() - 1);
            tamadas.setText("" + Application.gameData.lplayer.getTamadas());
            Application.gameData.lplayer.setArany(kivalasztottArany - vasarlas());
        }
        egyenleg.setText("Egyenleg: " + Application.gameData.lplayer.getArany() + " arany");
    }

    @FXML
    public void removeTamadas() {
        if(Application.gameData.lplayer.getTamadas() > 1) {
            Application.gameData.lplayer.setTamadas(Application.gameData.lplayer.getTamadas() - 1);
            tamadas.setText("" + Application.gameData.lplayer.getTamadas());
            Application.gameData.lplayer.setArany(kivalasztottArany - vasarlas());
        }
        egyenleg.setText("Egyenleg: " + Application.gameData.lplayer.getArany() + " arany");
    }

    @FXML
    public void addVedekezes() {
        if(Application.gameData.lplayer.getVedekezes() < 10) {
            Application.gameData.lplayer.setVedekezes(Application.gameData.lplayer.getVedekezes() + 1);
            vedekezes.setText("" + Application.gameData.lplayer.getVedekezes());
            Application.gameData.lplayer.setArany(kivalasztottArany - vasarlas());
        }
        if(Application.gameData.lplayer.getArany() < 0) {
            Application.gameData.lplayer.setVedekezes(Application.gameData.lplayer.getVedekezes() - 1);
            vedekezes.setText("" + Application.gameData.lplayer.getVedekezes());
            Application.gameData.lplayer.setArany(kivalasztottArany - vasarlas());
        }
        egyenleg.setText("Egyenleg: " + Application.gameData.lplayer.getArany() + " arany");

    }

    @FXML
    public void removeVedekezes() {
        if(Application.gameData.lplayer.getVedekezes() > 1) {
            Application.gameData.lplayer.setVedekezes(Application.gameData.lplayer.getVedekezes() - 1);
            vedekezes.setText("" + Application.gameData.lplayer.getVedekezes());
            Application.gameData.lplayer.setArany(kivalasztottArany - vasarlas());
        }
        egyenleg.setText("Egyenleg: " + Application.gameData.lplayer.getArany() + " arany");
    }

    @FXML
    public void addVarazsero() {
        if(Application.gameData.lplayer.getVarazsero() < 10) {
            Application.gameData.lplayer.setVarazsero(Application.gameData.lplayer.getVarazsero() + 1);
            varazsero.setText("" + Application.gameData.lplayer.getVarazsero());
            Application.gameData.lplayer.setArany(kivalasztottArany - vasarlas());
        }
        if(Application.gameData.lplayer.getArany() < 0) {
            Application.gameData.lplayer.setVarazsero(Application.gameData.lplayer.getVarazsero() - 1);
            varazsero.setText("" + Application.gameData.lplayer.getVarazsero());
            Application.gameData.lplayer.setArany(kivalasztottArany - vasarlas());
        }
        egyenleg.setText("Egyenleg: " + Application.gameData.lplayer.getArany() + " arany");

    }

    @FXML
    public void removeVarazsero() {
        if(Application.gameData.lplayer.getVarazsero() > 1) {
            Application.gameData.lplayer.setVarazsero(Application.gameData.lplayer.getVarazsero() - 1);
            varazsero.setText("" + Application.gameData.lplayer.getVarazsero());
            Application.gameData.lplayer.setArany(kivalasztottArany - vasarlas());
        }
        egyenleg.setText("Egyenleg: " + Application.gameData.lplayer.getArany() + " arany");
    }

    @FXML
    public void addTudas() {
        if(Application.gameData.lplayer.getTudas() < 10) {
            Application.gameData.lplayer.setTudas(Application.gameData.lplayer.getTudas() + 1);
            tudas.setText("" + Application.gameData.lplayer.getTudas());
            Application.gameData.lplayer.setArany(kivalasztottArany - vasarlas());
        }

        if(Application.gameData.lplayer.getArany() < 0) {
            Application.gameData.lplayer.setTudas(Application.gameData.lplayer.getTudas() - 1);
            tudas.setText("" + Application.gameData.lplayer.getTudas());
            Application.gameData.lplayer.setArany(kivalasztottArany - vasarlas());
        }

        egyenleg.setText("Egyenleg: " + Application.gameData.lplayer.getArany() + " arany");

    }

    @FXML
    public void removeTudas() {
        if(Application.gameData.lplayer.getTudas() > 1) {
            Application.gameData.lplayer.setTudas(Application.gameData.lplayer.getTudas() - 1);
            tudas.setText("" + Application.gameData.lplayer.getTudas());
            Application.gameData.lplayer.setArany(kivalasztottArany - vasarlas());
        }
        egyenleg.setText("Egyenleg: " + Application.gameData.lplayer.getArany() + " arany");
    }

    @FXML
    public void addMoral() {
        if(Application.gameData.lplayer.getMoral() < 10) {
            Application.gameData.lplayer.setMoral(Application.gameData.lplayer.getMoral() + 1);
            moral.setText("" + Application.gameData.lplayer.getMoral());
            Application.gameData.lplayer.setArany(kivalasztottArany - vasarlas());
        }
        if(Application.gameData.lplayer.getArany() < 0) {
            Application.gameData.lplayer.setMoral(Application.gameData.lplayer.getMoral() - 1);
            moral.setText("" + Application.gameData.lplayer.getMoral());
            Application.gameData.lplayer.setArany(kivalasztottArany - vasarlas());
        }
        egyenleg.setText("Egyenleg: " + Application.gameData.lplayer.getArany() + " arany");

    }

    @FXML
    public void removeMoral() {
        if(Application.gameData.lplayer.getMoral() > 1) {
            Application.gameData.lplayer.setMoral(Application.gameData.lplayer.getMoral() - 1);
            moral.setText("" + Application.gameData.lplayer.getMoral());
            Application.gameData.lplayer.setArany(kivalasztottArany - vasarlas());
        }
        egyenleg.setText("Egyenleg: " + Application.gameData.lplayer.getArany() + " arany");
    }

    @FXML
    public void addSzerencse() {
        if(Application.gameData.lplayer.getSzerencse() < 10) {
            Application.gameData.lplayer.setSzerencse(Application.gameData.lplayer.getSzerencse() + 1);
            szerencse.setText("" + Application.gameData.lplayer.getSzerencse());
            Application.gameData.lplayer.setArany(kivalasztottArany - vasarlas());
        }
        if(Application.gameData.lplayer.getArany() < 0) {
            Application.gameData.lplayer.setSzerencse(Application.gameData.lplayer.getSzerencse() - 1);
            szerencse.setText("" + Application.gameData.lplayer.getSzerencse());
            Application.gameData.lplayer.setArany(kivalasztottArany - vasarlas());
        }
        egyenleg.setText("Egyenleg: " + Application.gameData.lplayer.getArany() + " arany");

    }

    @FXML
    public void removeSzerencse() {
        if(Application.gameData.lplayer.getSzerencse() > 1) {
            Application.gameData.lplayer.setSzerencse(Application.gameData.lplayer.getSzerencse() - 1);
            szerencse.setText("" + Application.gameData.lplayer.getSzerencse());
            Application.gameData.lplayer.setArany(kivalasztottArany - vasarlas());
        }
        egyenleg.setText("Egyenleg: " + Application.gameData.lplayer.getArany() + " arany");
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

            Application.gameData.lplayer.setTamadas(Integer.parseInt(tamadas.getText()));
            Application.gameData.lplayer.setVedekezes(Integer.parseInt(vedekezes.getText()));
            Application.gameData.lplayer.setVarazsero(Integer.parseInt(varazsero.getText()));
            Application.gameData.lplayer.setTudas(Integer.parseInt(tudas.getText()));
            Application.gameData.lplayer.setMoral(Integer.parseInt(moral.getText()));
            Application.gameData.lplayer.setSzerencse(Integer.parseInt(szerencse.getText()));

            Application.gameData.lplayer.setElerhetoVarazslatok(villamcsapas.isSelected(), tuzlabda.isSelected(), feltamasztas.isSelected(), kotelbilincs.isSelected(), harcimamor.isSelected());

            Application.gameData.lplayer.setEgysegek(Application.gameData.lplayer, Integer.parseInt(foldmuves.getText()), Integer.parseInt(ijasz.getText()), Integer.parseInt(griff.getText()), Integer.parseInt(magus.getText()), Integer.parseInt(szupercsillagharcos.getText()));

            Application.gameData.lplayer.egysegek[0].setOsszHp(Application.gameData.lplayer.egysegek[0].getHp() * Application.gameData.lplayer.egysegek[0].getDb());
            Application.gameData.lplayer.egysegek[1].setOsszHp(Application.gameData.lplayer.egysegek[1].getHp() * Application.gameData.lplayer.egysegek[1].getDb());
            Application.gameData.lplayer.egysegek[2].setOsszHp(Application.gameData.lplayer.egysegek[2].getHp() * Application.gameData.lplayer.egysegek[2].getDb());
            Application.gameData.lplayer.egysegek[3].setOsszHp(Application.gameData.lplayer.egysegek[3].getHp() * Application.gameData.lplayer.egysegek[3].getDb());
            Application.gameData.lplayer.egysegek[4].setOsszHp(Application.gameData.lplayer.egysegek[4].getHp() * Application.gameData.lplayer.egysegek[4].getDb());

            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("field.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setResizable(false);
            stage.setScene(scene);

        }


    }

}
