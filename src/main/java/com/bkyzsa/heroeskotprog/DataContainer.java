package com.bkyzsa.heroeskotprog;

import com.bkyzsa.heroeskotprog.units.Egyseg;
import com.bkyzsa.heroeskotprog.units.Hos;

/**
 * A játékvezérlés globális menedzselését elősegítő osztály
 * @author Isztin Martin
 * @version 1.0
 */

public class DataContainer {
    public Hos lplayer, rplayer;
    public boolean multiplayer;
    public Hos pakol;
    public Hos gyoztes;

    public int kor = 1;

    public Egyseg[][] map = new Egyseg[10][12];


    /**
     * az osztály konstruktora
     * @param lplayer   a bal oldali játékos
     * @param rplayer   a jobb oldali játékos
     * @param multiplayer   a játékmenet multiplayer-e
     */
    public DataContainer(Hos lplayer, Hos rplayer, boolean multiplayer) {
        this.lplayer = lplayer;
        this.rplayer = rplayer;
        this.multiplayer = multiplayer;
        this.gyoztes = null;

        for(int i = 0; i < 10; i++) {
            for (int j = 0; j < 12; j++) {
                map[i][j] = null;
            }
        }
    }

    /**
     * kiírja a térképnek használt 2 dimenziós tömb tartalmát a standard kimenetre
     */
    public void print() {
        for(int i = 0; i < 10; i++) {
            for (int j = 0; j < 12; j++) {
                System.out.print(map[i][j] == null ? "-" : map[i][j].getNev());
            }
            System.out.println();
        }
    }
}
