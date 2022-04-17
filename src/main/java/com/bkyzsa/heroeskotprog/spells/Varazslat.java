package com.bkyzsa.heroeskotprog.spells;

/**
 * A Varázslatok fő absztrakt osztálya.
 * @author Isztin Martin
 * @version 1.0
 */

public abstract class Varazslat {

    private int ar, sebzes, manaCost;
    private String leiras;
    private boolean van;

    /**
     * a varázslat árának a gettere
     * @return visszaadja egy adott példány árát
     */
    public int getAr() {
        return ar;
    }

    /**
     * a varázslat sebzésének a gettere
     * @return visszaadja egy adott példány sebzését
     */
    public int getSebzes() {
        return sebzes;
    }

    /**
     * a varázslat mana fogyasztása
     * @return visszaadja egy adott példány mana árát
     */
    public int getManaCost() {
        return manaCost;
    }

    /**
     * a varázslat leírásának a gettere
     * @return visszaadja egy adott példány leírását
     */
    public String getLeiras() {
        return leiras;
    }

    /**
     * az osztály konstruktora
     */

    public Varazslat(int ar, int sebzes, int manacost, String leiras, boolean van) {
        this.ar = ar;
        this.sebzes = sebzes;
        this.manaCost = manacost;
        this.leiras = leiras;
        this.van = van;
    }

    /**
     * a varázslat birtoklásának a settere
     */

    public void setVan(boolean van) {
        this.van = van;
    }

    /**
     * a varázslat birtoklásának a gettere
     * @return visszaadja egy adott példányról hogy van-e
     */

    public boolean isVan() {
        return van;
    }

    /**
     * felülírt kiíró metódus, rendezve adja vissza néhány adattag értékét
     */
    @Override
    public String toString() {
        return leiras + "\nSebzés: (varázserő * " + sebzes + ")\nMana költség: " + manaCost + " mana\nÁr: " + ar;
    }
}
