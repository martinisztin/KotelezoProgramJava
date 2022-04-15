package com.bkyzsa.heroeskotprog;

public abstract class Varazslat {

    private int ar, sebzes, manacost;
    private String leiras;
    private boolean van;

    public int getAr() {
        return ar;
    }

    public int getSebzes() {
        return sebzes;
    }

    public int getManacost() {
        return manacost;
    }

    public String getLeiras() {
        return leiras;
    }

    public Varazslat(int ar, int sebzes, int manacost, String leiras, boolean van) {
        this.ar = ar;
        this.sebzes = sebzes;
        this.manacost = manacost;
        this.leiras = leiras;
        this.van = van;
    }

    public void setVan(boolean van) {
        this.van = van;
    }

    public boolean isVan() {
        return van;
    }

    @Override
    public String toString() {
        return leiras + "\nSebzés: (varázserő * " + sebzes + ")\nMana költség: " + manacost + " mana\nÁr: " + ar;
    }
}
