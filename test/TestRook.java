package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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
        Token rook = tokenFactory.build(TokenType.ROOK, Team.WHITE, new Point(0, 0));
        Point targetLocation = new Point(0, 7);
        tokenFactory.build(TokenType.ROOK, Team.BLACK, targetLocation);
        rook.move(targetLocation);
        assertEquals(targetLocation, rook.getLocation());
    }


    @Test
    public void shouldBlockRookMovingThroughToken() {
        TokenFactory tokenFactory = new TokenFactory(new Board());
        Token rook = tokenFactory.build(TokenType.ROOK, Team.WHITE, new Point(0, 0));
        tokenFactory.build(TokenType.PAWN, Team.WHITE, new Point(0, 3));
        Point targetLocation = new Point(0, 7);
        rook.move(targetLocation);
        assertNotEquals(targetLocation, rook.getLocation());
    }


    @Test
    public void shouldAllowMovingToBlockCheckForKing() {
        TokenFactory tokenFactory = new TokenFactory(new Board());
        Token rook = tokenFactory.build(TokenType.ROOK, Team.WHITE, new Point(1, 0));
        tokenFactory.build(TokenType.ROOK, Team.BLACK, new Point(7, 1));
        rook.getBoard().setKing(tokenFactory.build(TokenType.KING, Team.WHITE, new Point(0, 1)));
        Point targetLocation = new Point(1, 1);
        rook.move(targetLocation);
        assertEquals(targetLocation, rook.getLocation());
    }


    @Test
    public void shouldPreventLeavingKingInCheck() {
        TokenFactory tokenFactory = new TokenFactory(new Board());
        Token rook = tokenFactory.build(TokenType.ROOK, Team.WHITE, new Point(1, 0));
        tokenFactory.build(TokenType.ROOK, Team.BLACK, new Point(7, 0));
        rook.getBoard().setKing(tokenFactory.build(TokenType.KING, Team.WHITE, new Point(0, 0)));
        Point targetLocation = new Point(1, 7);
        rook.move(targetLocation);
        assertNotEquals(targetLocation, rook.getLocation());
    }
}
