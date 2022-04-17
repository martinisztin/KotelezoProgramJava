package com.bkyzsa.heroeskotprog.units;

/**
 * A Földműves egység osztálya.
 * @author Isztin Martin
 * @version 1.0
 */

public class Foldmuves extends Egyseg {
    /**
     * Az osztály alapértelmezett konstruktora.
     */
    public Foldmuves(Hos gazda, int db) {
        super("foldmuves", db, 2, 1, 1, 3, 4, 8, "nincs", gazda);
    }

    /**
     * Az absztrakt képesség metódus felüldefiniálása.
     */
    @Override
    public void kepesseg() {

    }
}
