package com.bkyzsa.heroeskotprog.units;

/**
 * A Mágus egység osztálya.
 * @author Isztin Martin
 * @version 1.0
 */

public class Magus extends Egyseg {

    /**
     * Az osztály alapértelmezett konstruktora.
     */
    public Magus(Hos gazda, int db) {
        super("magus", db, 10, 4, 7, 7, 5, 10, "Minden támadása után gyógyul (varázserő * 5) életerőt", gazda);
    }

    /**
     * Az absztrakt képesség metódus felüldefiniálása.
     */
    @Override
    public void kepesseg() {
        //nem itt van implementalva
    }

}
