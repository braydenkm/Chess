package model.token;

import globals.Team;
import model.Board;
import model.Point;


/**
 * Queen is a concrete class of Token.
 * A Queen object encapsulates the behaviours and restrictions of a queen
 * chess piece.
 */
public class Queen extends Token {

    /**
     * Default Constructor for Queen.
     * 
     * @param team              the team this queen is sided with.
     * @param startingLocation  the location this queen starts at.
     * @param board             the game board this queen is playing on.
     */
    protected Queen(Team team, Point startingLocation, Board board) {
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

        return  location.isSameColumn(target)   ||
                location.isSameRow(target)      ||
                location.isSameDiagonal(target);
    }


    @Override
    protected boolean isBlockedTowards(Point target) {
        return board.hasTokensBetweenPoints(location, target);
    }
    

    @Override
    protected String characterRepresentation() {
        return "Q";
    }
}