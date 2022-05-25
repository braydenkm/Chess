package model.token;

import globals.Team;
import model.Board;
import model.Point;

 
/**
 * Bishop is a concrete class of Token.
 * A Bishop object encapsulates the behaviours and restrictions of a bishop
 * chess piece.
 */
public class Bishop extends Token {

    /**
     * Default Constructor for Bishop.
     * 
     * @param team              the team this bishop is sided with.
     * @param startingLocation  the location this bishop starts at.
     * @param board             the game board this bishop is playing on.
     */
    protected Bishop(Team team, Point startingLocation, Board board) {
        super(team, startingLocation, board);
    }


    @Override
    public boolean isValidMove(Point target) {
        if (!target.isInBounds()) {
            return false;
        }
        if (isBlockedTowards(target)) {
            return false;
        }
        if (willBeInCheck(target)) {
            return false;
        }
        
        if (getLocation().isSameDiagonal(target)) {
            return true;
        }
        
        return false;
    }
    

    @Override
    protected String characterRepresentation() {
        return "B";
    }
}