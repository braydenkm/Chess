package controller;

import globals.Team;
import globals.TokenType;
import model.Board;
import model.Point;
import model.token.Token;
import model.token.TokenFactory;

public class Controller {
    
    private Board board;
    
    
    public Controller(Board board) {
        this.board = board;
    }
    
    
    public void foo() {
        TokenFactory tokenMaker = new TokenFactory(board);
        board.placeToken(tokenMaker.build(TokenType.ROOK, Team.BLACK, new Point(1, 2)));
        Token token = board.getTokenAt(new Point(1, 1));
        System.out.println(token.toString());
        token.move(new Point(1, 3));
        token.move(new Point(1, 4));
    }
}