package it.unibo.project.utility;

/**
 * Class {@code Vector2D}, contains the x and y of a coordinate.
 */
public class Vector2D {
    private final int x;
    private final int y;

    /**
     * Constructor to inizialize the attributes
     * @param x value of coordinate 
     * @param y value of coordinate 
     */
    public Vector2D(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * Called for get the x value of the coordinate
     * @return the int value of x
     */
    public int getX(){
        return this.x;
    }

    /**
     * Called for get the y value of the coordinate
     * @return the int value of y
     */
    public int getY(){
        return this.y;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (obj == null){
            return false;
        }
        if (getClass() != obj.getClass()){
            return false;
        }
        Vector2D other = (Vector2D) obj;
        if (x != other.x){
            return false;
        }
        if (y != other.y){
            return false;
        }
        return true;
    }
 
    @Override
    public String toString() {
        return "Pair [x=" + this.x + ", y=" + this.y + "]";
    }
}
