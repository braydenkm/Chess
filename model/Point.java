package model;

import java.lang.Math;
import globals.Constants;


/**
 * Point is a container class for holding coordinates and performing
 * checks with other points.
 */
public class Point {

    /**
     * Coordinate for the x-dimension.
     */
    private int x;
    
    /**
     * Coordinate for the y-dimension.
     */
    private int y;


    /**
     * Default Constructor for Point.
     * 
     * @param x coordinate for the x-dimension.
     * @param y coordinate for the y-dimension.
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }


    /**
     * Check if the target point is within the same column as this point.
     * 
     * @param   target  the target point to compare with.
     * @return          true if the target point is within the same column as
     *                  this point.
     *                  false otherwise.
     */
    public boolean isSameColumn(Point target) {
        return x == target.getX();
    }
    
    
    /**
     * Check if the target point is within the same row as this point.
     * 
     * @param   target  the target point to compare with.
     * @return          true if the target point is within the same column as
     *                  this point.
     *                  false otherwise.
     */
    public boolean isSameRow(Point target) {
        return y == target.getY();
    }
    
    
    /**
     * Check if the target point is within either of the two diagonals of
     * this point.
     * 
     * @param   target  the target point to compare with.
     * @return          true if the target point is within either of the two
     *                  diagonals of this point.
     *                  false otherwise.
     */
    public boolean isSameDiagonal(Point target) {
        int differenceX = xDistanceTo(target);
        int differenceY = yDistanceTo(target);
        return differenceX == differenceY;
    }
    
    
    /**
     * Check if this point is within the boundaries defined withing the
     * Constants class.
     * 
     * @return  true if this point is within the boundaries.
     */
    public boolean isInBounds() {
        boolean inBoundsX = 0 <= x && x < Constants.WIDTH;
        boolean inBoundsY = 0 <= y && y < Constants.HEIGHT;
        return inBoundsX && inBoundsY;
    }
    
    
    /**
     * Check if the target point is at the same location as this point.
     * 
     * @param   target  the target point to compare with.
     * @return          true if the target is at the same location as this
     *                  point.
     *                  false otherwise.
     */
    public boolean isAtSameLocationAs(Point target) {
        return this.equals(target);
    }


    /**
     * Get the y distance to the target point.
     * 
     * @param   target  the target point to compare with.
     * @return          the y distance to the target point.
     */
    public int yDistanceTo(Point target) {
        return Math.abs(y - target.getY());
    }
    
    
    /**
     * Get the x distance to the target point.
     * 
     * @param   target  the target point to compare with.
     * @return          the x distance to the target point.
     */
    public int xDistanceTo(Point target) {
        return Math.abs(x - target.getX());
    }


    /**
     * Getter for this point's x value.
     * 
     * @return x-coordinate for this token.
     */
    public int getX() {
        return this.x;
    }


    /**
     * Getter for this point's y value.
     * 
     * @return y-coordinate for this token.
     */
    public int getY() {
        return this.y;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Point)) {
            return false;
        }
        Point point = (Point) o;
        return this.x == point.x && this.y == point.y;
    }
    

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}