package com.bkyzsa.heroeskotprog.units;

public class Magus extends Egyseg {

    public Magus(Hos gazda, int db) {
        super("magus", db, 10, 4, 7, 7, 5, 10, "Minden támadása után gyógyul (varázserő * 5) életerőt", gazda);
    }

    @Override
    public void kepesseg() {
        //nem itt van implementalva
    }

    //blabla
}
