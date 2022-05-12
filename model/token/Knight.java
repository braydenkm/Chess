package model.token;

import globals.Team;
import model.Board;
import model.Point;

public class Knight extends Token {

    Knight(Team team, Point startingLocation, Board board) {
        super(team, startingLocation, board);
    }


    @Override
    protected boolean isValidMove(Point target) {
        
        if (!target.inBounds()) {
            return false;
        }
        
        if (!target.isSameColumn(this.location)) {
            return false;
        }
        if (target.distanceBetweenY(this.location) != 1) {
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
        return "N";
    }
}