package com.bkyzsa.heroeskotprog.units;

/**
 * A Íjász egység osztálya.
 * @author Isztin Martin
 * @version 1.0
 */

public class Ijasz extends Egyseg {
    /**
     * Az osztály alapértelmezett konstruktora.
     */
    public Ijasz(Hos gazda, int db) {
        super("ijasz", db, 6, 2, 4, 7, 4, 9, "lövés", gazda);
    }

    /**
     * Az absztrakt képesség metódus felüldefiniálása.
     */
    @Override
    public void kepesseg() {
        //TODO lövés
        setTavolHarci(true);
    }
}
