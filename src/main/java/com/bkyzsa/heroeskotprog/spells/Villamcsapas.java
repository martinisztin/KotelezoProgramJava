package com.bkyzsa.heroeskotprog.spells;

/**
 * A Villámcsapás varázslat osztálya.
 * @author Isztin Martin
 * @version 1.0
 */

public class Villamcsapas extends Varazslat {

    /**
     * Az osztály alapértelmezett konstruktora.
     */
    public Villamcsapas() {
        super(60, 30, 5, "Egy kiválasztott ellenséges egységre (varázserő * 30) sebzést okoz", false);
    }

    /**
     * Az osztály overloadolt konstruktora, amely segítségével könnyebben ellenőrizhetjük, hogy a hősünk rendelkezik-e az adott képességgel.
     * @param van   a varázslat birtoklását jelképezi
     */
    public Villamcsapas(boolean van) {
        super(60, 30, 5, "Egy kiválasztott ellenséges egységre (varázserő * 30) sebzést okoz", van);
    }
}
