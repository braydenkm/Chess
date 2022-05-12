package model.token;

import globals.Team;
import model.Board;
import model.Point;

public class Pawn extends Token {
    
    private boolean isFirstMove = true;
    

    Pawn(Team team, Point startingLocation, Board board) {
        super(team, startingLocation, board);
    }


    @Override
    protected boolean isValidMove(Point target) {
        boolean isSameColumn = this.getLocation().isSameColumn(target);
        boolean isSingleTileMove = this.getLocation().distanceBetweenY(target) == 1;
        boolean isSingleDiagonalMove = isSingleTileMove && this.getLocation().distanceBetweenX(target) == 1;
        
        if (!target.inBounds()) {
            return false;
        }
        if (!this.isMovingForward(target)) {
            return false;
        }
        if (this.isBlockedTo(target)) {
            return false;
        }
        
        if (this.isFirstMove && this.isDoubleTileMove(target) && 
            isSameColumn && !this.targetIsOpponent(target)) {
            return true;
        }
        if (isSameColumn && isSingleTileMove && !this.targetIsOpponent(target)) {
            return true;
        }
        if (isSingleDiagonalMove && this.targetIsOpponent(target)) {
            return true;
        }
        return false;
    }
    
    
    @Override
    protected boolean isBlockedTo(Point target) {
        if (!this.isDoubleTileMove(target)) {
            return false;
        }
        return board.hasTokensBetweenPoints(this.getLocation(), target);
    }
    
    
    @Override
    protected boolean willBeInCheckmate(Point target) {
        return false;
    }
    
    
    @Override
    protected void postMoveActions() {
        this.isFirstMove = false;
    }


    @Override
    protected String toChar() {
        return "P";
    }
    
    
    private boolean isDoubleTileMove(Point target) {
        return this.getLocation().distanceBetweenY(target) == 2;
    }
}