package model;
import java.util.ArrayList;

import globals.Constants;
import globals.Team;
import globals.TokenType;
import model.token.Token;
import model.token.TokenFactory;

public class Board {

    private static final int WIDTH = Constants.WIDTH;
    private static final int HEIGHT = Constants.HEIGHT;
    private static final int OFFSET = 1;
    private ArrayList<Token> whiteTokens;
    private ArrayList<Token> blackTokens;


    public Board() {
        this.whiteTokens = initializeTeamTokens(Team.WHITE);
        this.blackTokens = initializeTeamTokens(Team.BLACK);
    }
    
    
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
            int steps = source.distanceBetweenX(target);
            boolean isMovingRight = xSource - target.getX() > 0;
            boolean isMovingUp    = ySource - target.getY() > 0;
            
            for (int i = 1; i < steps; i++) {
                int x = (isMovingRight) ? xSource + i : xSource - i;
                int y = (isMovingUp)    ? ySource + i : ySource - i;
                possibleTiles.add(new Point(x, y));
            }
        }

        for (Point point : possibleTiles) {
            for (Token token : this.whiteTokens) {
                if (token.getLocation().isSameAs(point)) {
                    return true;
                }
            }
            
            for (Token token : this.blackTokens) {
                if (token.getLocation().isSameAs(point)) {
                    return true;
                }
            }
        }
        
        
        
        return false;
    }


    private ArrayList<Token> initializeTeamTokens(Team team) {
        boolean isWhiteTeam = team == Team.WHITE;
        int pawnY     = (isWhiteTeam) ? 1 : HEIGHT - OFFSET - 1;
        int backLineY = (isWhiteTeam) ? 0 : HEIGHT - OFFSET;
        
        TokenFactory tokenMaker = new TokenFactory(this);
        ArrayList<Token> tokens = new ArrayList<>();
        for (int i = 0; i < WIDTH; i++) {
            tokens.add(tokenMaker.build(TokenType.PAWN, team, new Point(i, pawnY)));
        }
        
        tokens.add(tokenMaker.build(TokenType.ROOK,   team, new Point(0,                  backLineY)));
        tokens.add(tokenMaker.build(TokenType.ROOK,   team, new Point(WIDTH - OFFSET - 0, backLineY)));

        tokens.add(tokenMaker.build(TokenType.BISHOP, team, new Point(1,                  backLineY)));
        tokens.add(tokenMaker.build(TokenType.BISHOP, team, new Point(WIDTH - OFFSET - 1, backLineY)));

        tokens.add(tokenMaker.build(TokenType.KNIGHT, team, new Point(2,                  backLineY)));
        tokens.add(tokenMaker.build(TokenType.KNIGHT, team, new Point(WIDTH - OFFSET - 2, backLineY)));
        
        int kingX  = (isWhiteTeam) ? 3 : 4;
        int queenX = (isWhiteTeam) ? 4 : 3; 
        tokens.add(tokenMaker.build(TokenType.QUEEN,  team, new Point(kingX,  backLineY)));
        tokens.add(tokenMaker.build(TokenType.KING,   team, new Point(queenX, backLineY)));

        return tokens;
    }


    public void display() {
        System.out.println(boardAsString());
    }


    // This function probably extremely slow and is very ugly.
    private String boardAsString() {
        String string = "\n+--+--+--+--+--+--+--+--+";
        for (int i = HEIGHT * 2; i >=  1; i--) {
            if (!isEven(i)) {
                string += "\n+--+--+--+--+--+--+--+--+";
            }
            else {
                string += "\n";
                int y = (i - 1) / 2;
                for (int j = 0; j < WIDTH * 2 + 1; j++) {
                    if (isEven(j)) {
                        string += "|";
                    }
                    else {
                        int x = (j - 1) / 2;
                        Point target = new Point(x, y);
                        boolean spaceIsTaken = false;
                        for (Token token : whiteTokens) {
                            if (token.isAtPoint(target)) {
                                string += token.toStringShort();
                                spaceIsTaken = true;
                                continue;
                            }
                        }
                        for (Token token : blackTokens) {
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
    
    
    public Token getTokenAt(Point target) {
        for (Token token : whiteTokens) {
            if (token.isAtPoint(target)) {
                return token;
            }
        }
        for (Token token : blackTokens) {
            if (token.isAtPoint(target)) {
                return token;
            }
        }
        return null;
    }
    
    
    public void placeToken(Token token) {
        if (token.getTeam() == Team.WHITE) this.whiteTokens.add(token);
        else this.blackTokens.add(token);
    }
    
    
    public void removeTokenAt(Point target) {
        for (Token token : whiteTokens) {
            if (token.isAtPoint(target)) {
                whiteTokens.remove(token);
                return;
            }
        }
        for (Token token : blackTokens) {
            if (token.isAtPoint(target)) {
                blackTokens.remove(token);
                return;
            }
        }
    }
    
    
    public ArrayList<Token> getTeamTokens(Team team) {
        return (team == Team.WHITE) ? this.whiteTokens : this.blackTokens;
    }


    private boolean isEven(int number) {
        return number % 2 == 0;
    }


    public String toString() {
        String string = "White:";
        for (Token token: whiteTokens) {
            string += "\n  " + token.toString();
        }
        string += "\nBlack:";
        for (Token token: blackTokens) {
            string += "\n  " + token.toString();
        }
        return string;
    }
}