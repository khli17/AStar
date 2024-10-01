package com.astar;


import java.awt.*;
//By: Brandon Beckwith

public class Space implements Comparable<Space> {

    private SpaceType type;
    private int g, h;
    private final Point POINT;
    private Space previous;

    public Space(SpaceType type, int x, int y) {
        //Set the type and location of this space
        this.type = type;
        this.POINT = new Point(x,y);
    }

    /**
     * Get the field.SpaceType
     * @return field.SpaceType
     */
    public SpaceType getType() {
        return type;
    }

    /**
     * Sets the field.SpaceType
     * @param type The new field.SpaceType
     */
    public void setType(SpaceType type) {
        this.type = type;
    }

    /**
     * Get the distance to this space
     * @return The distance to this space
     */
    public int getG() {
        return g;
    }

    /**
     * Set the distance to this space
     * @param g The distance to this space
     */
    public void setG(int g) {
        this.g = g;
    }

    /**
     * Get the distance from this space
     * @return The distance from this space
     */
    public int getH() {
        return h;
    }

    /**
     * Set the distance from this space
     * @param h The distance from this space
     */
    public void setH(int h) {
        this.h = h;
    }

    /**
     * Get the space's Point object (x,y)
     * @return Point
     */
    public Point getPoint() {
        return POINT;
    }

    /**
     * Get the space's x coordinate
     * @return y
     */
    public int getX(){
        return this.POINT.x;
    }

    /**
     * Get the space's y coordinate
     * @return y
     */
    public int getY(){
        return this.POINT.y;
    }

    /**
     * Get the estimated total distance from the starting
     * position to the ending position
     * @return The total estimated distance
     */
    public int getF(){
        return this.getH() + this.getG();
    }

    /**
     * Get the previous Space
     * @return Previous Space
     */
    public Space getPrevious() {
        return previous;
    }

    /**
     * Set the previous Space
     * @param previous Space
     */
    public void setPrevious(Space previous) {
        this.previous = previous;
    }

    /**
     * Swaps the type of this space between empty and block
     */
    public void swapType(){
        switch (this.type){
            case BLOCK -> this.type = SpaceType.EMPTY;
            case EMPTY -> this.type = SpaceType.BLOCK;
            case END -> throw new UnsupportedOperationException("Unimplemented case: " + this.type);
            case INVALID -> throw new UnsupportedOperationException("Unimplemented case: " + this.type);
            case PATH -> throw new UnsupportedOperationException("Unimplemented case: " + this.type);
            case START -> throw new UnsupportedOperationException("Unimplemented case: " + this.type);
            default -> throw new IllegalArgumentException("Unexpected value: " + this.type);
        }
    }

    /**
     * Reset the values without altering any blocks
     */
    public void reset(){
        this.setG(0);
        this.setH(0);
        this.setPrevious(null);
        if (!(this.getType() == SpaceType.BLOCK || this.getType() == SpaceType.INVALID))
            this.setType(SpaceType.EMPTY);
    }

    /**
     * Generates a String that indicates where this Space is located
     * @return A String
     */
    public String getPointString(){
        return "[" + Integer.toString((int) POINT.getX() + 1) + "," + Integer.toString((int) POINT.getY() + 1) + "]";
    }

    /**
     * Is used to compare two objects, this allows the
     * spaces to be sorted
     * @param space The Space to compare with
     * @return -1, 0, 1
     */
    @Override
    public int compareTo(Space space) {
        return Integer.compare(this.getF(), space.getF());
    }

    /**
     * Returns a representation of this Space as a string
     * @return [c]
     */
    @Override
    public String toString() {
        return this.getType().toString();
    }

}
