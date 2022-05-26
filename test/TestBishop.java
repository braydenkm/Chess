package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import globals.Team;
import globals.TokenType;
import model.Board;
import model.Point;
import model.token.Bishop;
import model.token.Token;
import model.token.TokenFactory;

public class TestBishop {
    
    @Test
    public void shouldMakeWhiteBishopAtOrigin() {
        TokenFactory tokenFactory = new TokenFactory(new Board());
        Token bishop = tokenFactory.build(TokenType.BISHOP, Team.WHITE, new Point(0, 0));
        assertTrue(bishop instanceof Bishop);
        assertEquals(Team.WHITE, bishop.getTeam());
        assertEquals(new Point(0, 0), bishop.getLocation());
    }


    @Test
    public void shouldMoveBishopNorthEast() {
        TokenFactory tokenFactory = new TokenFactory(new Board());
        Token bishop = tokenFactory.build(TokenType.BISHOP, Team.WHITE, new Point(0, 0));
        Point targetLocation = new Point(7, 7);
        bishop.move(targetLocation);
        assertEquals(targetLocation, bishop.getLocation());
    }


    @Test
    public void shouldMoveBishopSouthEast() {
        TokenFactory tokenFactory = new TokenFactory(new Board());
        Token bishop = tokenFactory.build(TokenType.BISHOP, Team.WHITE, new Point(0, 7));
        Point targetLocation = new Point(7, 0);
        bishop.move(targetLocation);
        assertEquals(targetLocation, bishop.getLocation());
    }


    @Test
    public void shouldMoveBishopSoutWest() {
        TokenFactory tokenFactory = new TokenFactory(new Board());
        Token bishop = tokenFactory.build(TokenType.BISHOP, Team.WHITE, new Point(7, 7));
        Point targetLocation = new Point(0, 0);
        bishop.move(targetLocation);
        assertEquals(targetLocation, bishop.getLocation());
    }


    @Test
    public void shouldMoveBishopNorthWest() {
        TokenFactory tokenFactory = new TokenFactory(new Board());
        Token bishop = tokenFactory.build(TokenType.BISHOP, Team.WHITE, new Point(7, 0));
        Point targetLocation = new Point(0, 7);
        bishop.move(targetLocation);
        assertEquals(targetLocation, bishop.getLocation());
    }


    @Test
    public void shouldKillBlackBishopWithWhiteBishop() {
        TokenFactory tokenFactory = new TokenFactory(new Board());
        Token bishop = tokenFactory.build(TokenType.BISHOP, Team.WHITE, new Point(0, 0));
        Point targetLocation = new Point(7, 7);
        tokenFactory.build(TokenType.BISHOP, Team.BLACK, targetLocation);
        bishop.move(targetLocation);
        assertEquals(targetLocation, bishop.getLocation());
    }


    @Test
    public void shouldBlockBishopMovingThroughToken() {
        TokenFactory tokenFactory = new TokenFactory(new Board());
        Token bishop = tokenFactory.build(TokenType.BISHOP, Team.WHITE, new Point(0, 0));
        tokenFactory.build(TokenType.PAWN, Team.WHITE, new Point(3, 3));
        Point targetLocation = new Point(7, 7);
        bishop.move(targetLocation);
        assertNotEquals(targetLocation, bishop.getLocation());
    }


    @Test
    public void shouldAllowMovingToBlockCheckForKing() {
        TokenFactory tokenFactory = new TokenFactory(new Board());
        Token bishop = tokenFactory.build(TokenType.BISHOP, Team.WHITE, new Point(1, 0));
        tokenFactory.build(TokenType.ROOK, Team.BLACK, new Point(7, 1));
        bishop.getBoard().setKing(tokenFactory.build(TokenType.KING, Team.WHITE, new Point(0, 1)));
        Point targetLocation = new Point(2, 1);
        bishop.move(targetLocation);
        assertEquals(targetLocation, bishop.getLocation());
    }


    @Test
    public void shouldPreventLeavingKingInCheck() {
        TokenFactory tokenFactory = new TokenFactory(new Board());
        Token bishop = tokenFactory.build(TokenType.BISHOP, Team.WHITE, new Point(1, 0));
        tokenFactory.build(TokenType.ROOK, Team.BLACK, new Point(7, 0));
        bishop.getBoard().setKing(tokenFactory.build(TokenType.KING, Team.WHITE, new Point(0, 0)));
        Point targetLocation = new Point(2, 1);
        bishop.move(targetLocation);
        assertNotEquals(targetLocation, bishop.getLocation());
    }
}
