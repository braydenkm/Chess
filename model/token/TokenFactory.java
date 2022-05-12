package model.token;

import globals.Team;
import globals.TokenType;
import model.Board;
import model.Point;

public class TokenFactory {
    
    private Board board;
    
    
    public TokenFactory(Board board) {
        this.board = board;    
    }
    

    public Token build(TokenType type, Team team, Point startingLocation) {
        switch (type) {
            case PAWN:
                return new Pawn  (team, startingLocation, board);
            case ROOK:
                return new Rook  (team, startingLocation, board);
            case BISHOP:
                return new Bishop(team, startingLocation, board);
            case KNIGHT:
                return new Knight(team, startingLocation, board);
            case QUEEN:
                return new Queen (team, startingLocation, board);
            case KING:
                return new King  (team, startingLocation, board);
            default:
                return new Pawn  (Team.WHITE, new Point(0, 0), board);
        }
    }
}