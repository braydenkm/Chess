package model;


/**
 * Record for a player's move.
 */
public class MoveRequest {

    /**
     * Location of token to move.
     */
    private Point source;

    /**
     * Location to move token to.
     */
    private Point target;


    /**
     * Default Constructor for MoveRequest.
     * 
     * @param source  location of token to move.
     * @param target  location to move token to.
     */
    public MoveRequest(Point source, Point target) {
        this.source = source;
        this.target = target;
    }


    /**
     * Getter for the source location.
     * 
     * @return  location of the source.
     */
    public Point getSource() {
        return this.source;
    }


    /**
     * Getter for the target location.
     * 
     * @return  location of the target.
     */
    public Point getTarget() {
        return this.target;
    }


    @Override
    public String toString() {
        return source.toString() + " " + target.toString();
    }
}
