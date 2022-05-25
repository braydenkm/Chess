package model.token;

import model.Board;
import model.Point;

import java.util.ArrayList;

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
    public boolean isValidMove(Point target) {
        if (!target.isInBounds()) {
            return false;
        }
        if (!isMovingForward(target)) {
            return false;
        }
        if (isBlockedTowards(target)) {
            return false;
        }
        
        boolean sameColumn = location.isSameColumn(target);
        boolean singleTileMove = location.yDistanceTo(target) == 1;
        boolean singleDiagonalMove = singleTileMove && location.xDistanceTo(target) == 1;
        boolean opponentAtTarget = hasOpponentAt(target);

        if (firstMove && isDoubleTileMove(target) && 
            sameColumn && !opponentAtTarget) {
            return true;
        }
        if (sameColumn && singleTileMove && !opponentAtTarget) {
            return true;
        }
        if (singleDiagonalMove && opponentAtTarget) {
            return true;
        }
        return false;
    }
    
    
    @Override
    protected boolean isBlockedTowards(Point target) {
        if (!isDoubleTileMove(target)) {
            return false;
        }
        return board.hasTokensBetweenPoints(location, target);
    }
        
    
    @Override
    protected void postMoveActions() {
        firstMove = false;
    }


    @Override
    protected String characterRepresentation() {
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
        int differenceY = target.getY() - location.getY();
        if (isWhite() && differenceY >= 0) {
            return true;
        }
        if (!isWhite() && differenceY <= 0) {
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
        return location.yDistanceTo(target) == 2;
    }
}