package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import globals.Team;
import globals.TokenType;
import model.Board;
import model.Point;
import model.token.King;
import model.token.Token;
import model.token.TokenFactory;

public class TestKing {

    @Test
    public void shouldMakeWhiteKingAtOrigin() {
        TokenFactory tokenFactory = new TokenFactory(new Board());
        Token king = tokenFactory.build(TokenType.KING, Team.WHITE, new Point(0, 0));
        assertTrue(king instanceof King);
        assertEquals(Team.WHITE, king.getTeam());
        assertEquals(new Point(0, 0), king.getLocation());
    }


    @Test
    public void shouldMoveKingForwardOne() {
        TokenFactory tokenFactory = new TokenFactory(new Board());
        Token king = tokenFactory.build(TokenType.KING, Team.WHITE, new Point(0, 0));
        Point targetLocation = new Point(0, 1);
        king.move(targetLocation);
        assertEquals(targetLocation, king.getLocation());
    }


    @Test
    public void shouldMoveKingSidewaysOne() {
        TokenFactory tokenFactory = new TokenFactory(new Board());
        Token king = tokenFactory.build(TokenType.KING, Team.WHITE, new Point(0, 0));
        Point targetLocation = new Point(1, 0);
        king.move(targetLocation);
        assertEquals(targetLocation, king.getLocation());
    }


    @Test
    public void shouldMoveKingDiagonalOne() {
        TokenFactory tokenFactory = new TokenFactory(new Board());
        Token king = tokenFactory.build(TokenType.KING, Team.WHITE, new Point(0, 0));
        Point targetLocation = new Point(1, 1);
        king.move(targetLocation);
        assertEquals(targetLocation, king.getLocation());
    }


    @Test
    public void shouldNotMoveKingMoreThanOne() {
        TokenFactory tokenFactory = new TokenFactory(new Board());
        Token king = tokenFactory.build(TokenType.KING, Team.WHITE, new Point(0, 0));
        Point targetLocation = new Point(0, 2);
        king.move(targetLocation);
        assertNotEquals(targetLocation, king.getLocation());
    }


    @Test
    public void shouldKillBlackKingWithWhiteKing() {
        TokenFactory tokenFactory = new TokenFactory(new Board());
        Token king = tokenFactory.build(TokenType.KING, Team.WHITE, new Point(0, 0));
        king.getBoard().setKing(king);
        Point targetLocation = new Point(0, 1);
        king.getBoard().setKing(tokenFactory.build(TokenType.KING, Team.BLACK, targetLocation));
        king.move(targetLocation);
        assertEquals(targetLocation, king.getLocation());
    }


    @Test
    public void shouldPreventMovingKingIntoCheck() {
        TokenFactory tokenFactory = new TokenFactory(new Board());
        Token king = tokenFactory.build(TokenType.KING, Team.WHITE, new Point(0, 0));
        king.getBoard().setKing(king);
        tokenFactory.build(TokenType.ROOK, Team.BLACK, new Point(7, 1));
        Point targetLocation = new Point(0, 1);
        king.move(targetLocation);
        assertNotEquals(targetLocation, king.getLocation());
    }
}
