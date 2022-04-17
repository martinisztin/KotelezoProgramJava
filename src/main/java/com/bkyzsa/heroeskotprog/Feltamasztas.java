package com.bkyzsa.heroeskotprog;

public class Feltamasztas extends Varazslat {
    public Feltamasztas() {
        super(120, 50, 6, "Egy kiválasztott saját egység feltámasztása. Maximum (varázserő * 50) életerővel.", false);
    }
    public Feltamasztas(boolean van) {
        super(120, 50, 6, "Egy kiválasztott saját egység feltámasztása. Maximum (varázserő * 50) életerővel.", van);
    }

    @Override
    public String toString() {
        return getLeiras() + "\nÚjraélesztés hatása: maximum (varázserő * " + getSebzes() + ") életerő\nMana költség: " + getManaCost() + " mana\nÁr: " + getAr();
    }
}
