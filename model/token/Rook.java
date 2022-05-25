package model.token;

import model.Board;
import model.Point;
import globals.Team;


/**
 * Rook is a concrete class of Token.
 * A Rook object encapsulates the behaviours and restrictions of a rook
 * chess piece.
 */
public class Rook extends Token {

    /**
     * Default Constructor for Rook.
     * 
     * @param team              the team this rook is sided with.
     * @param startingLocation  the location this rook starts at.
     * @param board             the game board this rook is playing on.
     */
    protected Rook(Team team, Point startingLocation, Board board) {
        super(team, startingLocation, board);
    }


    @Override
    public boolean isValidMove(Point target) {
        if (!target.isInBounds()) {
            return false;
        }
        if (this.isBlockedTowards(target)) {
            return false;
        }
        if (this.willBeInCheck(target)) {
            return false;
        }

        boolean sameColumn = location.isSameColumn(target);
        boolean sameRow = location.isSameRow(target);
        
        if (sameColumn || sameRow) {
            return true;
        }

        return false;
    }
    

    @Override
    protected String characterRepresentation() {
        return "R";
    }
}