package model.token;

import model.Board;
import model.Point;
import globals.Team;


/**
 * Pawn is a concrete class of Token.
 * A Pawn object encapsulates the behaviours and restrictions of a pawn
 * chess piece.
 */
public class Pawn extends Token {
    
    /**
     * Represents if this is the first move made by this pawn.
     */
    private boolean firstMove = true;
    

    /**
     * Default Constructor for Pawn.
     * 
     * @param team              the team this pawn is sided with.
     * @param startingLocation  the location this pawn starts at.
     * @param board             the game board this pawn is playing on.
     */
    protected Pawn(Team team, Point startingLocation, Board board) {
        super(team, startingLocation, board);
    }


    @Override
    protected boolean isValidMove(Point target) {
        boolean sameColumn = this.getLocation().isSameColumn(target);
        boolean singleTileMove = this.getLocation().yDistanceTo(target) == 1;
        boolean singleDiagonalMove = singleTileMove && this.getLocation().xDistanceTo(target) == 1;
        
        if (!target.isInBounds()) {
            return false;
        }
        if (!this.isMovingForward(target)) {
            return false;
        }
        if (this.isBlockedTowards(target)) {
            return false;
        }
        
        if (this.firstMove && this.isDoubleTileMove(target) && 
            sameColumn && !this.hasOpponentAt(target)) {
            return true;
        }
        if (sameColumn && singleTileMove && !this.hasOpponentAt(target)) {
            return true;
        }
        if (singleDiagonalMove && this.hasOpponentAt(target)) {
            return true;
        }
        return false;
    }
    
    
    @Override
    protected boolean isBlockedTowards(Point target) {
        if (!this.isDoubleTileMove(target)) {
            return false;
        }
        return board.hasTokensBetweenPoints(this.getLocation(), target);
    }
    
    
    @Override
    protected boolean willBeInCheckmate(Point target) {
        return false;
    }
    
    
    @Override
    protected void postMoveActions() {
        this.firstMove = false;
    }


    @Override
    protected String toChar() {
        return "P";
    }


    /**
     * Check this pawn is attempting to move 'forward' when moving
     * towards the target location. 'forward' can be described as moving
     * towards the opposing player, where 'backwards' would be towards
     * the user.
     * 
     * @param   target  the target location for this pawn to move to.
     * @return          true if the pawn is moving 'forward'.
     *                  false otherwise.
     */
    private boolean isMovingForward(Point target) {
        int differenceY = target.getY() - this.getLocation().getY();
        if (this.isWhite() && differenceY >= 0) {
            return true;
        }
        if (!this.isWhite() && differenceY <= 0) {
            return true;
        }
        return false;
    }
    
    
    /**
     * Check if this pawn's target location is two spaces away in the y
     * direction.
     * 
     * @param   target  the target location for this pawn to move to.
     * @return          true if this pawn's target location is two spaces
     *                  away in the y direction.
     *                  false otherwise.
     */
    private boolean isDoubleTileMove(Point target) {
        return this.getLocation().yDistanceTo(target) == 2;
    }
}