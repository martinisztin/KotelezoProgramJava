package com.bkyzsa.heroeskotprog.spells;

/**
 * A Harci mámor varázslat osztálya.
 * @author Isztin Martin
 * @version 1.0
 */

public class Harcimamor extends Varazslat {

    /**
     * Az osztály alapértelmezett konstruktora.
     */
    public Harcimamor() {
        super(120, 1, 3, "Felerősíti az egységek támadását az adott körben (varázserő * 2) értékkel", false);
    }

    /**
     * Az osztály overloadolt konstruktora, amely segítségével könnyebben ellenőrizhetjük, hogy a hősünk rendelkezik-e az adott képességgel.
     * @param van   a varázslat birtoklását jelképezi
     */
    public Harcimamor(boolean van) {
        super(120, 1, 3, "Felerősíti az egységek támadását az adott körben (varázserő * 2) értékkel", van);
    }
}
