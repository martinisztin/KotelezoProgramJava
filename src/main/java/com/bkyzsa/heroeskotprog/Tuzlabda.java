package com.bkyzsa.heroeskotprog;

public class Tuzlabda extends Varazslat {

    public Tuzlabda() {
        super(120, 20, 9, "Egy kiválasztott 3x3-as területen levő ÖSSZES egységre (varázserő * 20) sebzést okoz", false);
    }

    public Tuzlabda(boolean van) {
        super(120, 20, 9, "Egy kiválasztott 3x3-as területen levő ÖSSZES egységre (varázserő * 20) sebzést okoz", van);
    }
}
