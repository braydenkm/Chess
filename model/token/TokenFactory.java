package model.token;

import globals.Team;
import globals.TokenType;
import model.Board;
import model.Point;


/**
 * TokenFactory is used for abstracting the token creation process.
 */
public class TokenFactory {
    
    /**
     * Board the tokens will be created for.
     */
    private Board board;
    
    
    /**
     * Default Constructor for TokenFactory.
     * 
     * @param   board   where the tokens will be created for.
     */
    public TokenFactory(Board board) {
        this.board = board;    
    }
    

    /**
     * Builds and returns a subclass of Token depending on the type requested.
     * 
     * @param   type                the type of token to create.
     * @param   team                team this token is sided with.
     * @param   startingLocation    point where this token is placed.
     * @return                      new token of type TokenType that has fields
     *                              set by parameters supplied.
     */
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