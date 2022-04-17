package com.bkyzsa.heroeskotprog;

import java.util.Objects;

public class Coords {
    public int row, col;

    public Coords(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coords coords = (Coords) o;
        return row == coords.row && col == coords.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}
