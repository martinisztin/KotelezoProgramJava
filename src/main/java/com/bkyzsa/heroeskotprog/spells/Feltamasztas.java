package com.bkyzsa.heroeskotprog.spells;

/**
 * A feltámasztás varázslat osztálya.
 * @author Isztin Martin
 * @version 1.0
 */

public class Feltamasztas extends Varazslat {
    /**
     * Az osztály alapértelmezett konstruktora.
     */
    public Feltamasztas() {
        super(120, 50, 6, "Egy kiválasztott saját egység feltámasztása. Maximum (varázserő * 50) életerővel.", false);
    }

    /**
     * Az osztály overloadolt konstruktora, amely segítségével könnyebben ellenőrizhetjük, hogy a hősünk rendelkezik-e az adott képességgel.
     * @param van   a varázslat birtoklását jelképezi
     */
    public Feltamasztas(boolean van) {
        super(120, 50, 6, "Egy kiválasztott saját egység feltámasztása. Maximum (varázserő * 50) életerővel.", van);
    }

    /**
     *
     * @return az osztály tulajdonságait szépen stringbe rendezve visszaadja, felülírva az ősosztály toString metódusát.
     */

    @Override
    public String toString() {
        return getLeiras() + "\nÚjraélesztés hatása: maximum (varázserő * " + getSebzes() + ") életerő\nMana költség: " + getManaCost() + " mana\nÁr: " + getAr();
    }
}
