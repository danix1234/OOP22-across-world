package it.unibo.project.utility;

import java.util.Objects;

/**
 * Class {@code Pair}, with generics to make a pair of values 
 */

public class Pair<E1,E2> {

    private final E1 e1;
    private final E2 e2;

    /**
     * Constructor to inizialize the attributes
     * @param x first element of pair
     * @param y second element of pair
     */
    public Pair(E1 x, E2 y) {
        super();
        this.e1 = x;
        this.e2 = y;
    }
    
    /**
     * Called for get the first element of pair
     * @return the value of first element
     */
    public E1 get1() {
        return e1;
    }

    /**
     * Called for get the second element of pair
     * @return the value of second element
     */
    public E2 get2() {
        return e2;
    }

    @Override
    public int hashCode() {
        return Objects.hash(e1, e2);
    }

    @Override
    @SuppressWarnings("rawtypes")
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Pair other = (Pair) obj;
        return Objects.equals(e1, other.e1) && Objects.equals(e2, other.e2);
    }

    @Override
    public String toString() {
        return "Pair [e1=" + e1 + ", e2=" + e2 + "]";
    }
}
