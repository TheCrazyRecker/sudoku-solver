package models;

import java.util.Objects;

public class Tuple implements Comparable<Tuple> {

    private int i;
    private int j;
    private boolean marked = false;

    public Tuple(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public void mark() {
        this.marked = true;
    }

    public boolean isMarked() {
        return marked;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
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

    @Override
    public String toString() {
        return "(" + (i + 1) + ", " + (j + 1) + ")";
    }

    @Override
    public int compareTo(Tuple o) {
        if(i > o.i) {
            return 1;
        }
        if(i == o.i) {
            return Integer.compare(j, o.j);
        }
        return -1;
    }
}
