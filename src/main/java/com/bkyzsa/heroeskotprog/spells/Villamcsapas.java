package com.bkyzsa.heroeskotprog.spells;

public class Villamcsapas extends Varazslat {

    public Villamcsapas() {
        super(60, 30, 5, "Egy kiválasztott ellenséges egységre (varázserő * 30) sebzést okoz", false);
    }

    public Villamcsapas(boolean van) {
        super(60, 30, 5, "Egy kiválasztott ellenséges egységre (varázserő * 30) sebzést okoz", van);
    }
}
