package com.bkyzsa.heroeskotprog.units;

import com.bkyzsa.heroeskotprog.spells.*;

public class Hos {
    private int tamadas, vedekezes, varazsero, tudas, moral, szerencse, arany, mana;
    public int statuszAr;
    public Varazslat elerhetoVarazslatok[] = {new Villamcsapas(), new Tuzlabda(), new Feltamasztas(), new Kotelbilincs(), new Harcimamor()};
    public Egyseg egysegek[] = {new Foldmuves(this, 0), new Ijasz(this,0), new Griff(this, 0), new Magus(this, 0), new Szupercsillagharcos(this, 0)};
    private boolean harciMamorAktiv;
    private boolean eletbenVan;

    public int getTamadas() {
        return tamadas;
    }

    public void setTamadas(int tamadas) {
        this.tamadas = tamadas;
    }

    public int getVedekezes() {
        return vedekezes;
    }

    public void setVedekezes(int vedekezes) {
        this.vedekezes = vedekezes;
    }

    public int getVarazsero() {
        return varazsero;
    }

    public void setVarazsero(int varazsero) {
        this.varazsero = varazsero;
    }

    public int getTudas() {
        return tudas;
    }

    public void setTudas(int tudas) {
        this.tudas = tudas;
    }

    public int getMoral() {
        return moral;
    }

    public void setMoral(int moral) {
        this.moral = moral;
    }

    public int getSzerencse() {
        return szerencse;
    }

    public void setSzerencse(int szerencse) {
        this.szerencse = szerencse;
    }

    public int getArany() {
        return arany;
    }

    public void setArany(int arany) {
        this.arany = arany;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public boolean isHarciMamorAktiv() {
        return harciMamorAktiv;
    }

    public void setHarciMamorAktiv(boolean harciMamorAktiv) {
        this.harciMamorAktiv = harciMamorAktiv;
    }

    public void setElerhetoVarazslatok(boolean villamcsapas, boolean tuzlabda, boolean feltamasztas, boolean kotelbilincs, boolean harcimamor) {
        elerhetoVarazslatok = new Varazslat[] {new Villamcsapas(villamcsapas), new Tuzlabda(tuzlabda), new Feltamasztas(feltamasztas), new Kotelbilincs(kotelbilincs), new Harcimamor(harcimamor)};
    }

    public void setEgysegek(Hos gazda, int foldmuves, int ijasz, int griff, int magus, int szupercsillagparaszt) {
        egysegek = new Egyseg[] {new Foldmuves(gazda, foldmuves), new Ijasz(gazda, ijasz), new Griff(gazda, griff), new Magus(gazda, magus), new Szupercsillagharcos(gazda, szupercsillagparaszt)};
    }

    public Hos(int arany) {
        this.arany = arany;
        tamadas = vedekezes = varazsero = tudas = moral = szerencse = 1;
        statuszAr = 5;
        mana = 10;
        harciMamorAktiv = false;
        eletbenVan = true;
    }

    public boolean isEletbenVan() {
        return eletbenVan;
    }

    public void setEletbenVan(boolean eletbenVan) {
        this.eletbenVan = eletbenVan;
    }
}
