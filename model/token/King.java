package model.token;

import globals.Team;
import model.Board;
import model.Point;

public class King extends Token {

    King(Team team, Point startingLocation, Board board) {
        super(team, startingLocation, board);
    }


    @Override
    protected boolean isValidMove(Point target) {
        
        if (!target.inBounds()) {
            return false;
        }
        if (this.willBeInCheckmate(target)) {
            return false;
        }
        
        return true;
    }
    
    
    @Override
    protected boolean willBeInCheckmate(Point target) {
        return false;
    }
    

    @Override
    protected String toChar() {
        return "K";
    }
}