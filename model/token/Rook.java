package model.token;

import globals.Team;
import model.Board;
import model.Point;

public class Rook extends Token {

    public Rook(Team team, Point startingLocation, Board board) {
        super(team, startingLocation, board);
    }


    @Override
    protected boolean isValidMove(Point target) {
        boolean isSameColumn = this.getLocation().isSameColumn(target);
        boolean isSameRow = this.getLocation().isSameRow(target);
        
        if (!target.inBounds()) {
            return false;
        }
        if (this.isBlockedTo(target)) {
            return false;
        }
        
        if (isSameColumn && !isSameRow) {
            return true;
        }
        if (!isSameColumn && isSameRow) {
            return true;
        }
        return false;
    }
    
    
    @Override
    protected boolean isBlockedTo(Point target) {
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