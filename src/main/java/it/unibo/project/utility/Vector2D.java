package it.unibo.project.utility;

import java.util.Objects;

public class Vector2D {
    private int x;
    private int y;

    public Vector2D(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public boolean equals(Vector2D vector) {
        if (this == vector) {
            return true;
        }
        if (vector == null) {
            return false;
        }
        if (getClass() != vector.getClass()) {
            return false;
        }
        return Objects.equals(this.x, vector.getX()) && Objects.equals(this.y, vector.getY());
    }

    public String toString() {
        return "Pair [x=" + this.x + ", y=" + this.y + "]";
    }
}
