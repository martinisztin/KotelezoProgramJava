package com.bkyzsa.heroeskotprog.units;

public class Szupercsillagharcos extends Egyseg {

    public Szupercsillagharcos(Hos gazda, int db) {
        super("szupercsillagharcos", db, 50, 20, 40, 30, 6, 20, "50% esély a támadás elkerülésére", gazda);
    }


    @Override
    public void kepesseg() {
        //TODO 50% ESÉLY KITÉRÉSRE
    }
}
