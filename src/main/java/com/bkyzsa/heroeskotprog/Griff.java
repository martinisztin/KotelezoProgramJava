package com.bkyzsa.heroeskotprog;

public class Griff extends Egyseg {
    public Griff(Hos gazda, int db) {
        super("griff", db, 15, 5, 10, 30, 7, 15, "végtelen visszatámadás", gazda);
    }

    @Override
    public void kepesseg() {
        //TODO vegtelen visszatamadas
        setVisszatamadtAKorben(false);
    }
}
