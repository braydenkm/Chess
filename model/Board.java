package model;

import java.util.ArrayList;
import globals.Constants;
import globals.Team;
import globals.TokenType;
import model.token.Token;
import model.token.TokenFactory;


/**
 * Board is a container class for holding game tokens and performing
 * checks between them.
 */
public class Board {
    
    /**
     * List containing all the tokens used in the game.
     */
    private ArrayList<Token> gameTokens;

    /**
     * King Token for white team.
     */
    private Token whiteKing;

    /**
     * King Token for black team.
     */
    private Token blackKing;


    /**
     * Default Constructor for Board.
     */
    public Board() {
        this.gameTokens = new ArrayList<>();
    }


    public void setKing(Token kingToken) {
        if (kingToken.isWhite()) {
            whiteKing = kingToken;
        }
        else {
            blackKing = kingToken;
        }
    }
    
    
    // Generate tokens on board for debugging.
    // Remove later.
    public void setUpDebugTokens() {
        TokenFactory tokenMaker = new TokenFactory(this);
        whiteKing = tokenMaker.build(TokenType.KING, Team.WHITE, new Point(1, 0));
        blackKing = tokenMaker.build(TokenType.KING, Team.BLACK, new Point(0, 7));
        tokenMaker.build(TokenType.KNIGHT, Team.WHITE, new Point(0, 1));
        tokenMaker.build(TokenType.ROOK, Team.BLACK, new Point(0, 3));
    }


    /**
     * Check if there are any tokens between two tiles of the gameboard.
     * 
     * @param   source  the source point on the board.
     * @param   target  the target point on the board.
     * @return          true if there are any tokens between the source and
     *                  target points.
     *                  false otherwise.
     */
    public boolean hasTokensBetweenPoints(Point source, Point target) {
        ArrayList<Point> possibleTiles = new ArrayList<>();
        
        if (source.isSameRow(target)) {
            int y = source.getY();
            int x1 = Math.min(source.getX(), target.getX());
            int x2 = Math.max(source.getX(), target.getX());
            for (int i = x1 + 1; i < x2; i++) {
                possibleTiles.add(new Point(i, y));
            }
        }
        else if (source.isSameColumn(target)) {
            int x = source.getX();
            int y1 = Math.min(source.getY(), target.getY());
            int y2 = Math.max(source.getY(), target.getY());
            for (int i = y1 + 1; i < y2; i++) {
                possibleTiles.add(new Point(x, i));
            }
        }
        else if (source.isSameDiagonal(target)) {
            int xSource = source.getX();
            int ySource = source.getY();
            int steps = source.xDistanceTo(target);
            boolean isMovingRight = target.getX() - xSource > 0;
            boolean isMovingUp    = target.getY() - ySource> 0;
            
            for (int i = 1; i < steps; i++) {
                int x = (isMovingRight) ? xSource + i : xSource - i;
                int y = (isMovingUp)    ? ySource + i : ySource - i;
                possibleTiles.add(new Point(x, y));
            }
        }

        for (Point point : possibleTiles) {
            for (Token token : gameTokens) {
                if (token.getLocation().isAtSameLocationAs(point)) {
                    return true;
                }
            }
        }

        return false;
    }


    // Calls functions to display the game board.
    // Planned to be replaced when graphics are added.
    public void display() {
        System.out.println(toString());
    }


    @Override
    public String toString() {
        String string = "\n  +--+--+--+--+--+--+--+--+";
        for (int i = Constants.HEIGHT * 2; i >=  1; i--) {
            if (!isEven(i)) {
                string += "\n  +--+--+--+--+--+--+--+--+";
            }
            else {
                string += "\n" + i / 2 + " ";
                int y = (i - 1) / 2;
                for (int j = 0; j < Constants.WIDTH * 2 + 1; j++) {
                    if (isEven(j)) {
                        string += "|";
                    }
                    else {
                        int x = (j - 1) / 2;
                        Point target = new Point(x, y);
                        boolean spaceIsTaken = false;
                        for (Token token : gameTokens) {
                            if (token.isAtPoint(target)) {
                                string += token.toStringShort();
                                spaceIsTaken = true;
                                continue;
                            }
                        }
                        if (!spaceIsTaken) {
                            string += "  ";
                        }
                    }
                }
            }
        }
        string += "\n    A  B  C  D  E  F  G  H";
        return string;
    }
    
    
    /**
     * Gets the token at the target location.
     * 
     * @param   target  the target location to grab the token from.
     * @return          Token at the target location.
     *                  null otherwise.
     */
    public Token getTokenAt(Point target) {
        for (Token token : gameTokens) {
            if (token.isAtPoint(target)) {
                return token;
            }
        }
        return null;
    }


    /**
     * Checks if there is a token at the target location.
     */
    public boolean hasTokenAt(Point target) {
        return getTokenAt(target) != null;
    }


    /**
     * Getter for game tokens.
     * 
     * @return game tokens.
     */
    public ArrayList<Token> getTokens() {
        return this.gameTokens;
    }


    /**
     * Returns king for the given team.
     * 
     * @param   team    team the king to return is sided with.
     * @return          king token for the chosen team.
     */
    public Token getKing(Team team) {
        return (team == Team.WHITE) ? this.whiteKing : this.blackKing;
    }
    
    
    /**
     * Adds a token to the list of game tokens.
     * 
     * @param   token   token to be added to the list of game tokens.
     */
    public void addToken(Token token) {
        gameTokens.add(token);
    }
    
    
    /**
     * Removes a token from the list of game tokens.
     * 
     * @param   target  target token to be removed from the list of game
     *                  tokens.
     */
    public void removeTokenAt(Point target) {
        for (Token token : gameTokens) {
            if (token.isAtPoint(target)) {
                gameTokens.remove(token);
                return;
            }
        }
    }


    /**
     * Create the game tokens on each team for a standard game of chess.
     */
    public void setUpStandardGameTokens() {
        initializeTeamTokens(Team.WHITE);
        initializeTeamTokens(Team.BLACK);
        TokenFactory tokenMaker = new TokenFactory(this);
        this.setKing(tokenMaker.build(TokenType.KING, Team.WHITE, new Point(4, 0)));
        this.setKing(tokenMaker.build(TokenType.KING, Team.BLACK, new Point(3, Constants.HEIGHT - Constants.OFFSET)));
    }
 

    /**
     * Create the game tokens on each team for a standard game of chess.
     * 
     * @param   team    the team to create tokens for.
     */
    private void initializeTeamTokens(Team team) {
        int pawnY     = (team == Team.WHITE) ? 1 : Constants.HEIGHT - Constants.OFFSET - 1;
        int backLineY = (team == Team.WHITE) ? 0 : Constants.HEIGHT - Constants.OFFSET;
        
        TokenFactory tokenMaker = new TokenFactory(this);
        for (int i = 0; i < Constants.WIDTH; i++) {
            tokenMaker.build(TokenType.PAWN, team, new Point(i, pawnY));
        }
        
        tokenMaker.build(TokenType.ROOK,   team, new Point(0, backLineY));
        tokenMaker.build(TokenType.ROOK,   team, new Point(Constants.WIDTH - Constants.OFFSET,     backLineY));
        tokenMaker.build(TokenType.BISHOP, team, new Point(1, backLineY));
        tokenMaker.build(TokenType.BISHOP, team, new Point(Constants.WIDTH - Constants.OFFSET - 1, backLineY));
        tokenMaker.build(TokenType.KNIGHT, team, new Point(2, backLineY));
        tokenMaker.build(TokenType.KNIGHT, team, new Point(Constants.WIDTH - Constants.OFFSET - 2, backLineY));
        
        int queenX = (team == Team.WHITE) ? 3 : 4; 
        tokenMaker.build(TokenType.QUEEN,  team, new Point(queenX,  backLineY));
    }


    /**
     * Check if a number is even.
     * 
     * @param   number  the number to perform check on.
     * @return          true if number is even.
     *                  false otherwise.
     */
    private boolean isEven(int number) {
        return number % 2 == 0;
    }
}