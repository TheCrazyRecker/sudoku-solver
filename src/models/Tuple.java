package models;

import java.util.Objects;

public class Tuple {

    private int i;
    private int j;

    public Tuple(int i, int j) {
        this.i = i;
        this.j = j;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Tuple tuple = (Tuple) o;
        return i == tuple.i && j == tuple.j;
    }

    @Override
    public int hashCode() {
        return Objects.hash(i, j);
    }
}
