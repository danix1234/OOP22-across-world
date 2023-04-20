package it.unibo.project.utility;

import java.util.Objects;

/**
 * Class {@code Pair}, with generics to make a pair of values.
 * @param <X> first element of pair
 * @param <Y> second element of pair
 */
public class Pair<X, Y> {
    private final X x;
    private final Y y;

    /**
     * Constructor to inizialize the attributes.
     * @param x first element of pair
     * @param y second element of pair
     */
    public Pair(final X x, final Y y) {
        super();
        this.x = x;
        this.y = y;
    }

    /**
     * Called for get the first element of pair.
     * @return the value of first element
     */
    public X get1() {
        return x;
    }

    /**
     * Called for get the second element of pair.
     * @return the value of second element
     */
    public Y get2() {
        return y;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    @SuppressWarnings("rawtypes")
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pair other = (Pair) obj;
        return Objects.equals(x, other.x) && Objects.equals(y, other.y);
    }

    @Override
    public final String toString() {
        return "Pair [X=" + x + ", Y=" + y + "]";
    }
}
