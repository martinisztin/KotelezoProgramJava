package com.bkyzsa.heroeskotprog.units;

/**
 * A Griff egység osztálya.
 * @author Isztin Martin
 * @version 1.0
 */

public class Griff extends Egyseg {
    /**
     * Az osztály alapértelmezett konstruktora.
     */
    public Griff(Hos gazda, int db) {
        super("griff", db, 15, 5, 10, 30, 7, 15, "végtelen visszatámadás", gazda);
    }

    /**
     * Az absztrakt képesség metódus felüldefiniálása.
     */
    @Override
    public void kepesseg() {
        setVisszatamadtAKorben(false);
    }
}
