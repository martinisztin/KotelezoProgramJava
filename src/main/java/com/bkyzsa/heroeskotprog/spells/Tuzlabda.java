package com.bkyzsa.heroeskotprog.spells;

/**
 * A Tűzlabda varázslat osztálya.
 * @author Isztin Martin
 * @version 1.0
 */

public class Tuzlabda extends Varazslat {

    /**
     * Az osztály alapértelmezett konstruktora.
     */
    public Tuzlabda() {
        super(120, 20, 9, "Tetszőleges 3x3-as területen az ÖSSZES egységre (varázserő * 20) sebzést okoz", false);
    }

    /**
     * Az osztály overloadolt konstruktora, amely segítségével könnyebben ellenőrizhetjük, hogy a hősünk rendelkezik-e az adott képességgel.
     * @param van   a varázslat birtoklását jelképezi
     */
    public Tuzlabda(boolean van) {
        super(120, 20, 9, "Tetszőleges 3x3-as területen az ÖSSZES egységre (varázserő * 20) sebzést okoz", van);
    }
}
