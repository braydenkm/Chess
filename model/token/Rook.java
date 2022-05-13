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
    protected boolean isValidMove(Point target) {
        boolean sameColumn = this.getLocation().isSameColumn(target);
        boolean sameRow = this.getLocation().isSameRow(target);
        
        if (!target.isInBounds()) {
            return false;
        }
        if (this.isBlockedTowards(target)) {
            return false;
        }
        
        if (sameColumn && !sameRow) {
            return true;
        }
        if (!sameColumn && sameRow) {
            return true;
        }
        return false;
    }
    
    
    @Override
    protected boolean isBlockedTowards(Point target) {
        return board.hasTokensBetweenPoints(this.getLocation(), target);
    }
    
    
    @Override
    protected boolean willBeInCheckmate(Point target) {
        return false;
    }
    

    @Override
    protected String toChar() {
        return "R";
    }
}