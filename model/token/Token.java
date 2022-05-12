package model.token;

import globals.Team;
import model.Board;
import model.Point;

public abstract class Token {

    protected Team team;
    protected Point location;
    protected boolean isFacingOrigin;
    protected Board board;

    protected abstract boolean isValidMove(Point target);
    protected abstract boolean willBeInCheckmate(Point target);
    protected abstract String toChar();


    Token(Team team, Point startingLocation, Board board) {
        this.team = team;
        this.location = startingLocation;
        this.board = board;
    }
    
    
    protected void postMoveActions() {
        return;
    }
    
    
    protected boolean isBlockedTo(Point target) {
        return false;
    }


    public void move(Point target) {
        if (this.isValidMove(target)) {
            if (this.targetIsOpponent(target)) {
                board.removeTokenAt(target);
            }
            this.moveToLocation(target);
        }
        else {
            System.out.println("  Move to " + target.toString() + " is not valid.");
        }
        System.out.println(this.toString());
    }
    
    
    private void moveToLocation(Point target) {
        this.location = target;
        postMoveActions();
    }
    
    
    protected boolean targetIsOpponent(Point target) {
        Token targetToken = board.getTokenAt(target);
        if (targetToken == null) {
            return false;
        }
        return this.getTeam() != targetToken.getTeam();
    }
    
    
    public Point getLocation() {
        return this.location;
    }
    
    
    public Team getTeam() {
        return this.team;   
    }
    
    
    protected boolean isMovingForward(Point target) {
        int difference = target.getY() - this.getLocation().getY();
        if (!isFacingOrigin && difference >= 0) {
            return true;
        }
        if (isFacingOrigin && difference <= 0) {
            return true;
        }
        return false;
    }


    public boolean isAtPoint(Point target) {
        return this.getLocation().getX() == target.getX() && this.getLocation().getY() == target.getY();
    }


    public String toString() {
        return this.getClass() + " " + this.getLocation().toString() + " " + this.team;
    }
    

    public String toStringShort() {
        String team = (this.team == Team.WHITE) ? "w" : "b" ;
        return team + this.toChar();
    }
}