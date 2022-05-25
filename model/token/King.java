package model.token;

import globals.Team;
import model.Board;
import model.Point;


/**
 * King is a concrete class of Token.
 * A King object encapsulates the behaviours and restrictions of a king
 * chess piece.
 */
public class King extends Token {

    /**
     * Default Constructor for King.
     * 
     * @param team              the team this king is sided with.
     * @param startingLocation  the location this king starts at.
     * @param board             the game board this king is playing on.
     */
    protected King(Team team, Point startingLocation, Board board) {
        super(team, startingLocation, board);
    }


    @Override
    public boolean isValidMove(Point target) {
        if (!target.isInBounds()) {
            return false;
        }

        int xDistance = location.xDistanceTo(target);
        int yDistance = location.yDistanceTo(target);
        return xDistance <= 1 && yDistance <= 1;
    }
    

    @Override
    protected String characterRepresentation() {
        return "K";
    }
}