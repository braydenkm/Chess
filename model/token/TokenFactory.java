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
     * Builds, adds to board, and returns a subclass of Token depending on the
     * type requested.
     * 
     * @param   type                the type of token to create.
     * @param   team                team this token is sided with.
     * @param   startingLocation    point where this token is placed.
     * @return                      new token of type TokenType that has fields
     *                              set by parameters supplied.
     */
    public Token build(TokenType type, Team team, Point startingLocation) {
        Token token;
        switch (type) {
            case PAWN:
                token = new Pawn  (team, startingLocation, board);
                break;
            case ROOK:
                token = new Rook  (team, startingLocation, board);
                break;
            case BISHOP:
                token = new Bishop(team, startingLocation, board);
                break;
            case KNIGHT:
                token = new Knight(team, startingLocation, board);
                break;
            case QUEEN:
                token = new Queen (team, startingLocation, board);
                break;
            case KING:
                token = new King  (team, startingLocation, board);
                break;
            default:
                token = new Pawn  (Team.WHITE, new Point(0, 0), board);
        }
        board.addToken(token);
        return token;
    }
}