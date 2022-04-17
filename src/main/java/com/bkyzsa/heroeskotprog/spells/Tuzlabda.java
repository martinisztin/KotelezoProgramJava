package com.bkyzsa.heroeskotprog.spells;

public class Tuzlabda extends Varazslat {

    public Tuzlabda() {
        super(120, 20, 9, "Tetszőleges 3x3-as területen az ÖSSZES egységre (varázserő * 20) sebzést okoz", false);
    }

    public Tuzlabda(boolean van) {
        super(120, 20, 9, "Tetszőleges 3x3-as területen az ÖSSZES egységre (varázserő * 20) sebzést okoz", van);
    }
}
