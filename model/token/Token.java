package model.token;

import model.Board;
import model.Point;
import globals.Team;


/**
 * Token is the abstract base class for all chess game pieces. 
 * A Token object encapsulates operations for moving and taking
 * other tokens.
 */
public abstract class Token {

    /**
     * The team this token is sided with.
     */
    protected Team team;

    /**
     * The location of this token.
     */
    protected Point location;

    /**
     * The board this token is playing on.
     */
    protected Board board;


    /**
     * Default Constructor for Token.
     * 
     * @param team              the team this token is sided with.
     * @param startingLocation  the initial location for this token.
     * @param board             the board which this token is contained in.
     */
    protected Token(Team team, Point startingLocation, Board board) {
        this.team     = team;
        this.location = startingLocation;
        this.board    = board;
    }


    /**
     * Move this token to the target location. If there is a token at the
     * target location, remove it.
     * 
     * @param   target  the target location for this token to move to.
     */
    public void move(Point target) {
        if (hasOpponentAt(target)) {
            board.removeTokenAt(target);
        }
        setLocation(target);
        postMoveActions();
    }
    
    
    /**
     * Getter for this token's location.
     * 
     * @return  location of this token.
     */
    public Point getLocation() {
        return this.location;
    }    
    
    
    /**
    * Getter for this token's team.
    * 
    * @return  team this token is sided with.
    */
    public Team getTeam() {
        return this.team;   
    }


    /**
    * Getter for this token's board.
    * 
    * @return  board this token is playing on.
    */
    public Board getBoard() {
        return this.board;
    }


    /**
     * Check if this token's team is white.
     * 
     * @return  true if this token's team is white.
     *          false if it is black.
     */
    public boolean isWhite() {
        return team == Team.WHITE;
    }


    /**
     * Check this token is at the target location.
     * 
     * @param   target  the target location this token is checking.
     * @return          true if this token is at the target location.
     *                  false otherwise.
     */
    public boolean isAtPoint(Point target) {
        return location.isAtSameLocationAs(target);
    }


    /**
     * Return the information contained inside this token into a String format.
     * 
     * @return  the information contained within this token as a String.
     */
    @Override
    public String toString() {
        return this.getClass() + " " + location.toString() + " " + team;
    }
    

    // Return this token as a short string for displaying on the game board.
    // Planned to be removed when graphics are added.
    public String toStringShort() {
        String team = (this.team == Team.WHITE) ? "w" : "b" ;
        return team + this.characterRepresentation();
    }
    
    
    /**
     * Check if a target location is a legal chess move given the current
     * token's restrictions.
     * 
     * @param   target  the target location for this token to move to.
     * @return          true if the target location is a valid move for the
     *                  given token.
     *                  false otherwise.
     */
    public abstract boolean isValidMove(Point target);


    /**
     * Check if moving this token to the target location will put this team
     * into checkmate.
     * 
     * @param   target  the target location for this token to move to.
     * @return          true if the moving the token to the target location
     *                  will put this team into checkmate.
     *                  false otherwise.
     */
    public boolean putsTeamInCheck(Point target) {
        Point startingLocation = location;
        Token tokenAtTarget = board.getTokenAt(target);
        boolean targetTileEmpty = tokenAtTarget == null;
        Token thisTeamKing = board.getKing(team);

        // Move target off the board temporarily, move this piece to target.
        if (!targetTileEmpty) {
            tokenAtTarget.setLocation(new Point(-1, -1));
        }
        setLocation(target);

        // Check opponent tokens for check.
        boolean inCheck = false;
        for (Token token : board.getTokens()) {
            if (token.getTeam() == team) {
                continue;
            }
            if (token == tokenAtTarget) {
                continue;
            }
            if (token.isValidMove(thisTeamKing.getLocation())) {
                inCheck = true;
                break;
            }
        }

        // Reset both token's original locations.
        setLocation(startingLocation);
        if (!targetTileEmpty) {
            tokenAtTarget.setLocation(target);
        }

        return inCheck;
    }

    
    /**
     * Perform extra actions that are related to updating this token after a
     * move is performed.
     * Default behavior is to do nothing. If a child class has extra actions
     * to perform after a move, they are expected to override this method.
     */
    protected void postMoveActions() {
        return;
    }
    
    
    /**
     * Check if this token's path is blocked by another token when trying to
     * move to the target location.
     * 
     * @param   target  the target location for this token to move to.
     * @return          true if the path to the target location is obstructed.
     *                  false otherwise.
     */
    protected boolean isBlockedTowards(Point target) {
        return false;
    }
    
    
    /**
     * Check if the target location is an opponent token.
     * 
     * @param   target  the target location for this token to move to.
     * @return          true if the target location has an opponent token
     *                  occupying it.
     *                  false otherwise.
     */
    protected boolean hasOpponentAt(Point target) {
        Token targetToken = board.getTokenAt(target);
        if (targetToken == null) {
            return false;
        }
        return team != targetToken.getTeam();
    }


    // Return the token as represented by a single character.
    // Planned to be removed when graphics are added.
    protected abstract String characterRepresentation();
    
    
    /**
     * Move this token to the target location.
     * 
     * @param   target  the target location for this token to move to.
     */
    private void setLocation(Point target) {
        this.location = target;
    }
}