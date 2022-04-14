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
        setLabels();
    }

    public static int kivalasztottArany = Application.playerHos.getArany();

    private void setLabels() {
        String nehezsegS = switch (Application.playerHos.getArany()) {
            case 1300 -> "Könnyű";
            case 1000 -> "Közepes";
            case 700 -> "Nehéz";
            default -> "";
        };

        nehezseg.setText("Válaszott nehézség - " + nehezsegS);
        egyenleg.setText("Egyenleg: " + Application.playerHos.getArany() + " arany");
        foldmuves.setText("0");
        ijasz.setText("0");
        griff.setText("0");
        magus.setText("0");
        szupercsillagharcos.setText("0");
        info.setText("");
    }

    private int arReturnerById(String content) {
        switch(content) {
            case "foldmuves" -> {
                return Application.playerHos.egysegek[0].getAr();
            }
            case "ijasz" -> {
                return Application.playerHos.egysegek[1].getAr();
            }
            case "griff" -> {
                return Application.playerHos.egysegek[2].getAr();
            }
            case "magus" -> {
                return Application.playerHos.egysegek[3].getAr();
            }
            case "szupercsillagharcos" -> {
                return Application.playerHos.egysegek[4].getAr();
            }

            case "villamcsapas" -> {
                return Application.playerHos.elerhetoVarazslatok[0].getAr();
            }
            case "tuzlabda" -> {
                return Application.playerHos.elerhetoVarazslatok[1].getAr();
            }
            case "feltamasztas" -> {
                return Application.playerHos.elerhetoVarazslatok[2].getAr();
            }
            case "kotelbilincs" -> {
                return Application.playerHos.elerhetoVarazslatok[3].getAr();
            }
            case "harcimamor" -> {
                return Application.playerHos.elerhetoVarazslatok[4].getAr();
            }

        }

        return 0;
    }

    @FXML
    public void hoverOn(MouseEvent event) {
        switch(((Label)event.getSource()).getText()) {
            case "Tűzlabda" -> info.setText(Application.playerHos.elerhetoVarazslatok[0].toString());
            case "Villámcsapás" -> info.setText(Application.playerHos.elerhetoVarazslatok[1].toString());
            case "Feltámasztás" -> info.setText(Application.playerHos.elerhetoVarazslatok[2].toString());
            case "Kötélbilincs" -> info.setText(Application.playerHos.elerhetoVarazslatok[3].toString());
            case "Harci Mámor" -> info.setText(Application.playerHos.elerhetoVarazslatok[4].toString());

            case "Földműves" -> info.setText(Application.playerHos.egysegek[0].toString());
            case "Íjász" -> info.setText(Application.playerHos.egysegek[1].toString());
            case "Griff" -> info.setText(Application.playerHos.egysegek[2].toString());
            case "Mágus" -> info.setText(Application.playerHos.egysegek[3].toString());
            case "Szupercsillagharcos" -> info.setText(Application.playerHos.egysegek[4].toString());

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
        int statusz = Application.playerHos.statuszAr;

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

        Application.playerHos.setArany(kivalasztottArany - vasarlas());
        egyenleg.setText("Egyenleg: " + Application.playerHos.getArany() + " arany");

        System.out.println("mindent megkeráztam + " + vasarlas());
    }

    @FXML
    public void spellVasarlo(ActionEvent event) {
        Application.playerHos.setArany(kivalasztottArany - vasarlas());

        if(Application.playerHos.getArany() < 0) {
            ((CheckBox)event.getSource()).setSelected(false);
                Application.playerHos.setArany(kivalasztottArany - vasarlas());
        }
        egyenleg.setText("Egyenleg: " + Application.playerHos.getArany() + " arany");
    }


    //region statuszadogato
    @FXML
    public void addTamadas() {
        if(Application.playerHos.getTamadas() < 10) {
            Application.playerHos.setTamadas(Application.playerHos.getTamadas() + 1);
            tamadas.setText("" + Application.playerHos.getTamadas());
            Application.playerHos.setArany(kivalasztottArany - vasarlas());
        }
        if(Application.playerHos.getArany() < 0) {
            Application.playerHos.setTamadas(Application.playerHos.getTamadas() - 1);
            tamadas.setText("" + Application.playerHos.getTamadas());
            Application.playerHos.setArany(kivalasztottArany - vasarlas());
        }
        egyenleg.setText("Egyenleg: " + Application.playerHos.getArany() + " arany");
    }

    @FXML
    public void removeTamadas() {
        if(Application.playerHos.getTamadas() > 1) {
            Application.playerHos.setTamadas(Application.playerHos.getTamadas() - 1);
            tamadas.setText("" + Application.playerHos.getTamadas());
            Application.playerHos.setArany(kivalasztottArany - vasarlas());
        }
        egyenleg.setText("Egyenleg: " + Application.playerHos.getArany() + " arany");
    }

    @FXML
    public void addVedekezes() {
        if(Application.playerHos.getVedekezes() < 10) {
            Application.playerHos.setVedekezes(Application.playerHos.getVedekezes() + 1);
            vedekezes.setText("" + Application.playerHos.getVedekezes());
            Application.playerHos.setArany(kivalasztottArany - vasarlas());
        }
        if(Application.playerHos.getArany() < 0) {
            Application.playerHos.setVedekezes(Application.playerHos.getVedekezes() - 1);
            vedekezes.setText("" + Application.playerHos.getVedekezes());
            Application.playerHos.setArany(kivalasztottArany - vasarlas());
        }
        egyenleg.setText("Egyenleg: " + Application.playerHos.getArany() + " arany");

    }

    @FXML
    public void removeVedekezes() {
        if(Application.playerHos.getVedekezes() > 1) {
            Application.playerHos.setVedekezes(Application.playerHos.getVedekezes() - 1);
            vedekezes.setText("" + Application.playerHos.getVedekezes());
            Application.playerHos.setArany(kivalasztottArany - vasarlas());
        }
        egyenleg.setText("Egyenleg: " + Application.playerHos.getArany() + " arany");
    }

    @FXML
    public void addVarazsero() {
        if(Application.playerHos.getVarazsero() < 10) {
            Application.playerHos.setVarazsero(Application.playerHos.getVarazsero() + 1);
            varazsero.setText("" + Application.playerHos.getVarazsero());
            Application.playerHos.setArany(kivalasztottArany - vasarlas());
        }
        if(Application.playerHos.getArany() < 0) {
            Application.playerHos.setVarazsero(Application.playerHos.getVarazsero() - 1);
            varazsero.setText("" + Application.playerHos.getVarazsero());
            Application.playerHos.setArany(kivalasztottArany - vasarlas());
        }
        egyenleg.setText("Egyenleg: " + Application.playerHos.getArany() + " arany");

    }

    @FXML
    public void removeVarazsero() {
        if(Application.playerHos.getVarazsero() > 1) {
            Application.playerHos.setVarazsero(Application.playerHos.getVarazsero() - 1);
            varazsero.setText("" + Application.playerHos.getVarazsero());
            Application.playerHos.setArany(kivalasztottArany - vasarlas());
        }
        egyenleg.setText("Egyenleg: " + Application.playerHos.getArany() + " arany");
    }

    @FXML
    public void addTudas() {
        if(Application.playerHos.getTudas() < 10) {
            Application.playerHos.setTudas(Application.playerHos.getTudas() + 1);
            tudas.setText("" + Application.playerHos.getTudas());
            Application.playerHos.setArany(kivalasztottArany - vasarlas());
        }

        if(Application.playerHos.getArany() < 0) {
            Application.playerHos.setTudas(Application.playerHos.getTudas() - 1);
            tudas.setText("" + Application.playerHos.getTudas());
            Application.playerHos.setArany(kivalasztottArany - vasarlas());
        }

        egyenleg.setText("Egyenleg: " + Application.playerHos.getArany() + " arany");

    }

    @FXML
    public void removeTudas() {
        if(Application.playerHos.getTudas() > 1) {
            Application.playerHos.setTudas(Application.playerHos.getTudas() - 1);
            tudas.setText("" + Application.playerHos.getTudas());
            Application.playerHos.setArany(kivalasztottArany - vasarlas());
        }
        egyenleg.setText("Egyenleg: " + Application.playerHos.getArany() + " arany");
    }

    @FXML
    public void addMoral() {
        if(Application.playerHos.getMoral() < 10) {
            Application.playerHos.setMoral(Application.playerHos.getMoral() + 1);
            moral.setText("" + Application.playerHos.getMoral());
            Application.playerHos.setArany(kivalasztottArany - vasarlas());
        }
        if(Application.playerHos.getArany() < 0) {
            Application.playerHos.setMoral(Application.playerHos.getMoral() - 1);
            moral.setText("" + Application.playerHos.getMoral());
            Application.playerHos.setArany(kivalasztottArany - vasarlas());
        }
        egyenleg.setText("Egyenleg: " + Application.playerHos.getArany() + " arany");

    }

    @FXML
    public void removeMoral() {
        if(Application.playerHos.getMoral() > 1) {
            Application.playerHos.setMoral(Application.playerHos.getMoral() - 1);
            moral.setText("" + Application.playerHos.getMoral());
            Application.playerHos.setArany(kivalasztottArany - vasarlas());
        }
        egyenleg.setText("Egyenleg: " + Application.playerHos.getArany() + " arany");
    }

    @FXML
    public void addSzerencse() {
        if(Application.playerHos.getSzerencse() < 10) {
            Application.playerHos.setSzerencse(Application.playerHos.getSzerencse() + 1);
            szerencse.setText("" + Application.playerHos.getSzerencse());
            Application.playerHos.setArany(kivalasztottArany - vasarlas());
        }
        if(Application.playerHos.getArany() < 0) {
            Application.playerHos.setSzerencse(Application.playerHos.getSzerencse() - 1);
            szerencse.setText("" + Application.playerHos.getSzerencse());
            Application.playerHos.setArany(kivalasztottArany - vasarlas());
        }
        egyenleg.setText("Egyenleg: " + Application.playerHos.getArany() + " arany");

    }

    @FXML
    public void removeSzerencse() {
        if(Application.playerHos.getSzerencse() > 1) {
            Application.playerHos.setSzerencse(Application.playerHos.getSzerencse() - 1);
            szerencse.setText("" + Application.playerHos.getSzerencse());
            Application.playerHos.setArany(kivalasztottArany - vasarlas());
        }
        egyenleg.setText("Egyenleg: " + Application.playerHos.getArany() + " arany");
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

            Application.playerHos.setTamadas(Integer.parseInt(tamadas.getText()));
            Application.playerHos.setVedekezes(Integer.parseInt(vedekezes.getText()));
            Application.playerHos.setVarazsero(Integer.parseInt(varazsero.getText()));
            Application.playerHos.setTudas(Integer.parseInt(tudas.getText()));
            Application.playerHos.setMoral(Integer.parseInt(moral.getText()));
            Application.playerHos.setSzerencse(Integer.parseInt(szerencse.getText()));

            Application.playerHos.setElerhetoVarazslatok(villamcsapas.isSelected(), tuzlabda.isSelected(), feltamasztas.isSelected(), kotelbilincs.isSelected(), harcimamor.isSelected());

            Application.playerHos.setEgysegek(Application.playerHos, Integer.parseInt(foldmuves.getText()), Integer.parseInt(ijasz.getText()), Integer.parseInt(griff.getText()), Integer.parseInt(magus.getText()), Integer.parseInt(szupercsillagharcos.getText()));

            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("field.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);

        }


    }

}
