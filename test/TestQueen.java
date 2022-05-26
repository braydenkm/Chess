package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import globals.Team;
import globals.TokenType;
import model.Board;
import model.Point;
import model.token.Queen;
import model.token.Token;
import model.token.TokenFactory;

public class TestQueen {

    @Test
    public void shouldMakeWhiteQueenAtOrigin() {
        TokenFactory tokenFactory = new TokenFactory(new Board());
        Token queen = tokenFactory.build(TokenType.QUEEN, Team.WHITE, new Point(0, 0));
        assertTrue(queen instanceof Queen);
        assertEquals(Team.WHITE, queen.getTeam());
        assertEquals(new Point(0, 0), queen.getLocation());
    }


    @Test
    public void shouldMoveQueenForwardAcrossBoard() {
        TokenFactory tokenFactory = new TokenFactory(new Board());
        Token queen = tokenFactory.build(TokenType.QUEEN, Team.WHITE, new Point(0, 0));
        Point targetLocation = new Point(0, 7);
        queen.move(targetLocation);
        assertEquals(targetLocation, queen.getLocation());
    }


    @Test
    public void shouldMoveQueenSidewaysAcrossBoard() {
        TokenFactory tokenFactory = new TokenFactory(new Board());
        Token queen = tokenFactory.build(TokenType.QUEEN, Team.WHITE, new Point(0, 0));
        Point targetLocation = new Point(7, 0);
        queen.move(targetLocation);
        assertEquals(targetLocation, queen.getLocation());
    }


    @Test
    public void shouldMoveQueenDiagonalAcrossBoard() {
        TokenFactory tokenFactory = new TokenFactory(new Board());
        Token queen = tokenFactory.build(TokenType.QUEEN, Team.WHITE, new Point(0, 0));
        Point targetLocation = new Point(7, 7);
        queen.move(targetLocation);
        assertEquals(targetLocation, queen.getLocation());
    }


    @Test
    public void shouldNotMoveQueenOffAxis() {
        TokenFactory tokenFactory = new TokenFactory(new Board());
        Token queen = tokenFactory.build(TokenType.QUEEN, Team.WHITE, new Point(0, 0));
        Point targetLocation = new Point(7, 6);
        queen.move(targetLocation);
        assertNotEquals(targetLocation, queen.getLocation());
    }


    @Test
    public void shouldKillBlackQueenWithWhiteQueen() {
        TokenFactory tokenFactory = new TokenFactory(new Board());
        Token queen = tokenFactory.build(TokenType.QUEEN, Team.WHITE, new Point(0, 0));
        Point targetLocation = new Point(0, 7);
        tokenFactory.build(TokenType.QUEEN, Team.BLACK, targetLocation);
        queen.move(targetLocation);
        assertEquals(targetLocation, queen.getLocation());
    }


    @Test
    public void shouldBlockQueenMovingThroughToken() {
        TokenFactory tokenFactory = new TokenFactory(new Board());
        Token queen = tokenFactory.build(TokenType.QUEEN, Team.WHITE, new Point(0, 0));
        tokenFactory.build(TokenType.ROOK, Team.WHITE, new Point(0, 3));
        Point targetLocation = new Point(0, 7);
        queen.move(targetLocation);
        assertNotEquals(targetLocation, queen.getLocation());
    }


    @Test
    public void shouldAllowMovingToBlockCheckForKing() {
        TokenFactory tokenFactory = new TokenFactory(new Board());
        Token queen = tokenFactory.build(TokenType.QUEEN, Team.WHITE, new Point(1, 0));
        tokenFactory.build(TokenType.ROOK, Team.BLACK, new Point(7, 1));
        queen.getBoard().setKing(tokenFactory.build(TokenType.KING, Team.WHITE, new Point(0, 1)));
        Point targetLocation = new Point(1, 1);
        queen.move(targetLocation);
        assertEquals(targetLocation, queen.getLocation());
    }


    @Test
    public void shouldPreventLeavingKingInCheck() {
        TokenFactory tokenFactory = new TokenFactory(new Board());
        Token queen = tokenFactory.build(TokenType.QUEEN, Team.WHITE, new Point(1, 0));
        tokenFactory.build(TokenType.ROOK, Team.BLACK, new Point(7, 0));
        queen.getBoard().setKing(tokenFactory.build(TokenType.KING, Team.WHITE, new Point(0, 0)));
        Point targetLocation = new Point(1, 7);
        queen.move(targetLocation);
        assertNotEquals(targetLocation, queen.getLocation());
    }
}
