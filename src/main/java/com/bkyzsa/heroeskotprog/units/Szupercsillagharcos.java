package com.bkyzsa.heroeskotprog.units;

/**
 * A Szupercsillagharcos egység osztálya. Kicsit op.
 * @author Isztin Martin
 * @version 1.0
 */

public class Szupercsillagharcos extends Egyseg {

    /**
     * Az osztály alapértelmezett konstruktora.
     */
    public Szupercsillagharcos(Hos gazda, int db) {
        super("szupercsillagharcos", db, 50, 20, 40, 30, 6, 20, "50% esély a támadás elkerülésére", gazda);
    }

    /**
     * Az absztrakt képesség metódus felüldefiniálása.
     */
    @Override
    public void kepesseg() {
        //implemented mashol
    }
}
