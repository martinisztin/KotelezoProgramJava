package com.bkyzsa.heroeskotprog;

public class Ijasz extends Egyseg {
    public Ijasz(Hos gazda, int db) {
        super("ijasz", db, 6, 2, 4, 7, 4, 9, "lövés", gazda);
    }

    @Override
    public void kepesseg() {
        //TODO lövés
        setTavolHarci(true);
    }
}
