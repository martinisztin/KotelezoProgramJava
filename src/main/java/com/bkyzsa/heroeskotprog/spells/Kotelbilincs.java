package com.bkyzsa.heroeskotprog.spells;

/**
 * A Kötélbilincs varázslat osztálya.
 * @author Isztin Martin
 * @version 1.0
 */
public class Kotelbilincs extends Varazslat {

    /**
     * Az osztály alapértelmezett konstruktora.
     */
    public Kotelbilincs() {
        super(60, 0, 6, "A megcélzott egység kimarad 3 körből.", false);
    }

    /**
     * Az osztály overloadolt konstruktora, amely segítségével könnyebben ellenőrizhetjük, hogy a hősünk rendelkezik-e az adott képességgel.
     * @param van   a varázslat birtoklását jelképezi
     */
    public Kotelbilincs(boolean van) {
        super(60, 0, 6, "A megcélzott egység kimarad 3 körből.", van);
    }
}
