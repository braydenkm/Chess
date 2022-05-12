package model;
import java.lang.Math;

import globals.Constants;

public class Point {

    private int x;
    private int y;


    public Point(int x, int y) {
        set(x, y);
    }


    public void set(int x, int y) {
        this.x = x;
        this.y = y;
    }


    public boolean isSameColumn(Point target) {
        return this.getX() == target.getX();
    }
    
    
    public boolean isSameRow(Point target) {
        return this.getY() == target.getY();
    }
    
    
    public boolean isSameDiagonal(Point target) {
        int differenceX = this.distanceBetweenX(target);
        int differenceY = this.distanceBetweenY(target);
        return differenceX == differenceY;
    }
    
    
    public boolean inBounds() {
        boolean inBoundsX = 0 <= this.getX() && this.getX() <= Constants.WIDTH;
        boolean inBoundsY = 0 <= this.getY() && this.getY() <= Constants.HEIGHT;
        return inBoundsX && inBoundsY;
    }
    
    
    public boolean isSameAs(Point target) {
        return this.getX() == target.getX() && this.getY() == target.getY();
    }


    public int distanceBetweenY(Point target) {
        return Math.abs(this.getY() - target.getY());
    }
    
    
    public int distanceBetweenX(Point target) {
        return Math.abs(this.getX() - target.getX());
    }


    public int getX() {
        return x;
    }


    public int getY() {
        return y;
    }
    

    public String toString() {
        return "(" + this.getX() + ", " + this.getY() + ")";
    }
}