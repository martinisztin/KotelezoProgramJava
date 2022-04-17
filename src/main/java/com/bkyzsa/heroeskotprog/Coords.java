package com.bkyzsa.heroeskotprog;

import java.util.Objects;

/**
 * A harcmező koordinátái tárolására alkalmas struktúra.
 * @author Isztin Martin
 * @version 1.0
 */

public class Coords {
    public int row, col;

    /**
     * Az osztály konstruktora.
     * @param row sor
     * @param col oszlop
     */
    public Coords(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * felüldefiniált equals metódus
     * @param o összehasonlítandó objektum
     * @return a két objektum egyenlőségének igazsága
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coords coords = (Coords) o;
        return row == coords.row && col == coords.col;
    }

    /**
     * felüldefiniált equals metódus
     * @return az osztály tulajdonságainak hashelése
     */
    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}
