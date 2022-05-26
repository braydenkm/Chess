package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        Token whiteBishop = tokenFactory.build(TokenType.BISHOP, Team.WHITE, new Point(0, 0));
        Point blackSource = new Point(7, 7);
        tokenFactory.build(TokenType.BISHOP, Team.BLACK, blackSource);
        // Need a king for black rook to determine if placing in check.
        whiteBishop.getBoard().setKing(tokenFactory.build(TokenType.KING, Team.WHITE, new Point(7, 0)));
        whiteBishop.move(blackSource);
        assertEquals(blackSource, whiteBishop.getLocation());
    }


    @Test
    public void shouldBlockWhiteBishopMovingThroughBlackBishop() {
        TokenFactory tokenFactory = new TokenFactory(new Board());
        Point whiteSource = new Point(0, 0);
        Token whiteBishop = tokenFactory.build(TokenType.BISHOP, Team.WHITE, whiteSource);
        tokenFactory.build(TokenType.BISHOP, Team.BLACK, new Point(3, 3));
        // Need a king for black rook to determine if placing in check.
        whiteBishop.getBoard().setKing(tokenFactory.build(TokenType.KING, Team.WHITE, new Point(7, 0)));
        whiteBishop.move(new Point(7, 7));
        assertEquals(whiteSource, whiteBishop.getLocation());
    }
}
