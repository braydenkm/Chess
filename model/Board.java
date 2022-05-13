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
     * Default Constructor for Board.
     */
    public Board() {
        this.gameTokens = initializeGameTokens();
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
        if (!source.isSameRow(target) && !source.isSameColumn(target) && !source.isSameDiagonal(target)) {
            // Throw invalid parameters exception.
            System.out.println("ERROR: This should be an exception inside Board.java");
        }
        
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
            boolean isMovingRight = xSource - target.getX() > 0;
            boolean isMovingUp    = ySource - target.getY() > 0;
            
            for (int i = 1; i < steps; i++) {
                int x = (isMovingRight) ? xSource + i : xSource - i;
                int y = (isMovingUp)    ? ySource + i : ySource - i;
                possibleTiles.add(new Point(x, y));
            }
        }

        for (Point point : possibleTiles) {
            for (Token token : this.gameTokens) {
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


    // Creates the board as a string.
    // Planned to be replaced when graphics are added.
    @Override
    public String toString() {
        String string = "\n+--+--+--+--+--+--+--+--+";
        for (int i = Constants.HEIGHT * 2; i >=  1; i--) {
            if (!isEven(i)) {
                string += "\n+--+--+--+--+--+--+--+--+";
            }
            else {
                string += "\n";
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
     * Adds a token to the list of game tokens.
     * 
     * @param   token   token to be added to the list of game tokens.
     */
    public void placeToken(Token token) {
        this.gameTokens.add(token);
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
     * 
     * @return  the game tokens for each team for a standard game of chess.
     */
    private ArrayList<Token> initializeGameTokens() {
        ArrayList<Token> allTokens = initializeTeamTokens(Team.WHITE);
        ArrayList<Token> blackTokens = initializeTeamTokens(Team.BLACK);
        for (Token token : blackTokens) {
            allTokens.add(token);
        }
        return allTokens;
    }


    /**
     * Create the game tokens on each team for a standard game of chess.
     * 
     * @param   team    the team to create tokens for.
     * @return          the game tokens for specified team for a standard
     *                  game of chess.
     */
    private ArrayList<Token> initializeTeamTokens(Team team) {
        boolean isWhiteTeam = team == Team.WHITE;
        int pawnY     = (isWhiteTeam) ? 1 : Constants.HEIGHT - Constants.OFFSET - 1;
        int backLineY = (isWhiteTeam) ? 0 : Constants.HEIGHT - Constants.OFFSET;
        
        TokenFactory tokenMaker = new TokenFactory(this);
        ArrayList<Token> tokens = new ArrayList<>();
        for (int i = 0; i < Constants.WIDTH; i++) {
            tokens.add(tokenMaker.build(TokenType.PAWN, team, new Point(i, pawnY)));
        }
        
        tokens.add(tokenMaker.build(TokenType.ROOK,   team, new Point(0, backLineY)));
        tokens.add(tokenMaker.build(TokenType.ROOK,   team, new Point(Constants.WIDTH - Constants.OFFSET,     backLineY)));

        tokens.add(tokenMaker.build(TokenType.BISHOP, team, new Point(1, backLineY)));
        tokens.add(tokenMaker.build(TokenType.BISHOP, team, new Point(Constants.WIDTH - Constants.OFFSET - 1, backLineY)));

        tokens.add(tokenMaker.build(TokenType.KNIGHT, team, new Point(2, backLineY)));
        tokens.add(tokenMaker.build(TokenType.KNIGHT, team, new Point(Constants.WIDTH - Constants.OFFSET - 2, backLineY)));
        
        int kingX  = (isWhiteTeam) ? 3 : 4;
        int queenX = (isWhiteTeam) ? 4 : 3; 
        tokens.add(tokenMaker.build(TokenType.QUEEN,  team, new Point(kingX,  backLineY)));
        tokens.add(tokenMaker.build(TokenType.KING,   team, new Point(queenX, backLineY)));

        return tokens;
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