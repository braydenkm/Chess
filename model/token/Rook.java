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
        if (isBlockedTowards(target)) {
            return false;
        }

        return location.isSameColumn(target) || location.isSameRow(target);
    }


    @Override
    protected boolean isBlockedTowards(Point target) {
        return board.hasTokensBetweenPoints(location, target);
    }
    

    @Override
    protected String characterRepresentation() {
        return "R";
    }
}