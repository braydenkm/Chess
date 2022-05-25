package model.token;

import globals.Team;
import model.Board;
import model.Point;


/**
 * Knight is a concrete class of Token.
 * A Knight object encapsulates the behaviours and restrictions of a knight
 * chess piece.
 */
public class Knight extends Token {

    /**
     * Default Constructor for Knight.
     * 
     * @param team              the team this knight is sided with.
     * @param startingLocation  the location this knight starts at.
     * @param board             the game board this knight is playing on.
     */
    protected Knight(Team team, Point startingLocation, Board board) {
        super(team, startingLocation, board);
    }


    @Override
    public boolean isValidMove(Point target) {
        
        if (!target.isInBounds()) {
            return false;
        }

        return false;
    }


    @Override
    protected boolean isBlockedTowards(Point target) {
        return false;
    }
    

    @Override
    protected String characterRepresentation() {
        return "N";
    }
}