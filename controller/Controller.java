package controller;
import globals.Team;
import model.Board;
import model.Point;
import model.token.Rook;
import model.token.Token;

public class Controller {
    
    private Board board;
    
    
    public Controller(Board board) {
        this.board = board;
    }
    
    
    public void moveToken() {
        board.placeToken(new Rook(Team.BLACK, new Point(1, 2), board));
        Token token = board.getTokenAt(new Point(1, 1));
        System.out.println(token.toString());
        token.move(new Point(1, 2));
    }
}