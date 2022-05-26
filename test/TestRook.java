package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import globals.Team;
import globals.TokenType;
import model.Board;
import model.Point;
import model.token.Rook;
import model.token.Token;
import model.token.TokenFactory;

public class TestRook {

    @Test
    public void shouldMakeWhiteRookAtOrigin() {
        TokenFactory tokenFactory = new TokenFactory(new Board());
        Token rook = tokenFactory.build(TokenType.ROOK, Team.WHITE, new Point(0, 0));
        assertTrue(rook instanceof Rook);
        assertEquals(Team.WHITE, rook.getTeam());
        assertEquals(new Point(0, 0), rook.getLocation());
    }


    @Test
    public void shouldMoveRookForwardByOne() {
        TokenFactory tokenFactory = new TokenFactory(new Board());
        Token rook = tokenFactory.build(TokenType.ROOK, Team.WHITE, new Point(0, 0));
        Point targetLocation = new Point(0, 1);
        rook.move(targetLocation);
        assertEquals(targetLocation, rook.getLocation());
    }


    @Test
    public void shouldMoveRookForwardAcrossBoard() {
        TokenFactory tokenFactory = new TokenFactory(new Board());
        Token rook = tokenFactory.build(TokenType.ROOK, Team.WHITE, new Point(0, 0));
        Point targetLocation = new Point(0, 7);
        rook.move(targetLocation);
        assertEquals(targetLocation, rook.getLocation());
    }


    @Test
    public void shouldMoveRookSidewaysByOne() {
        TokenFactory tokenFactory = new TokenFactory(new Board());
        Token rook = tokenFactory.build(TokenType.ROOK, Team.WHITE, new Point(0, 0));
        Point targetLocation = new Point(1, 0);
        rook.move(targetLocation);
        assertEquals(targetLocation, rook.getLocation());
    }


    @Test
    public void shouldMoveRookSidewaysAcrossBoard() {
        TokenFactory tokenFactory = new TokenFactory(new Board());
        Token rook = tokenFactory.build(TokenType.ROOK, Team.WHITE, new Point(0, 0));
        Point targetLocation = new Point(7, 0);
        rook.move(targetLocation);
        assertEquals(targetLocation, rook.getLocation());
    }


    @Test
    public void shouldKillBlackRookWithWhiteRook() {
        TokenFactory tokenFactory = new TokenFactory(new Board());
        Token whiteRook = tokenFactory.build(TokenType.ROOK, Team.WHITE, new Point(0, 0));
        Point blackSource = new Point(0, 7);
        tokenFactory.build(TokenType.ROOK, Team.BLACK, blackSource);
        // Need a king for black rook to determine if placing in check.
        whiteRook.getBoard().setKing(tokenFactory.build(TokenType.KING, Team.WHITE, new Point(7, 7)));
        whiteRook.move(blackSource);
        assertEquals(blackSource, whiteRook.getLocation());
    }


    @Test
    public void shouldBlockWhiteRookMovingThroughBlackRook() {
        TokenFactory tokenFactory = new TokenFactory(new Board());
        Point whiteSource = new Point(0, 0);
        Token whiteRook = tokenFactory.build(TokenType.ROOK, Team.WHITE, whiteSource);
        tokenFactory.build(TokenType.ROOK, Team.BLACK, new Point(0, 3));
        // Need a king for black rook to determine if placing in check.
        whiteRook.getBoard().setKing(tokenFactory.build(TokenType.KING, Team.WHITE, new Point(7, 7)));
        whiteRook.move(new Point(0, 7));
        assertEquals(whiteSource, whiteRook.getLocation());
    }
}
