package com.bkyzsa.heroeskotprog;

public class Harcimamor extends Varazslat {

    public Harcimamor() {
        super(120, 1, 3, "Felerősíti az egységek támadását az adott körben (varázserő * 3) értékkel", false);
    }

    public Harcimamor(boolean van) {
        super(120, 1, 3, "Felerősíti az egységek támadását az adott körben (varázserő * 3) értékkel", van);
    }
}
