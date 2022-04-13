package com.bkyzsa.heroeskotprog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class ShopController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setLabels();

    }

    @FXML
    public Label nehezseg;

    @FXML
    public Label egyenleg;


    //hero pontok

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

    //egység nevek

    @FXML
    public Label foldmuves;
    @FXML
    public Label ijasz;
    @FXML
    public Label griff;
    @FXML
    public Label magus;
    @FXML
    public Label szupercsillagharcos;

    @FXML
    public Label info;

    public static int kepessegreKoltott = 0;
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
        foldmuves.setText("Földműves");
        ijasz.setText("Íjász");
        griff.setText("Griff");
        magus.setText("Mágus");
        szupercsillagharcos.setText("Szupercsillagharcos");
        info.setText("");
    }

    public int vasarlas() {
        Label[] skills = {tamadas, vedekezes, varazsero, tudas, moral, szerencse};
        int skillek = 0;

        for(Label skill : skills) {
            skillek += Integer.parseInt(skill.getText());
        }
        skillek -= 6;
        System.out.println(skillek);

        int sum = 0;
        int statusz = Application.playerHos.statuszAr;

        for(int i = 0; i<skillek; i++) {
            sum += statusz;
            statusz = (int) Math.ceil(statusz * 1.1);
        }

        if(skillek == 0) {
            return 0;
        }
        return sum;
    }

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

    public void hoverOnFoldmuves(MouseEvent event) {
        info.setText(Application.allyFoldmuves.toString());
    }
    public void hoverOnIjasz(MouseEvent event) {
        info.setText(Application.allyIjasz.toString());
    }
    public void hoverOnGriff(MouseEvent event) {
        info.setText(Application.allyGriff.toString());
    }
    public void hoverOnMagus(MouseEvent event) {
        info.setText(Application.allyMagus.toString());
    }
    public void hoverOnSaiyan(MouseEvent event) {
        info.setText(Application.allySaiyan.toString());
    }
    public void hoverOff(MouseEvent event) {
        info.setText("");
    }

}
