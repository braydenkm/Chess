package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import globals.Team;
import globals.TokenType;
import model.Board;
import model.Point;
import model.token.Pawn;
import model.token.Token;
import model.token.TokenFactory;

public class TestPawn {

    @Test
    public void shouldMakeWhitePawnAtOrigin() {
        TokenFactory tokenFactory = new TokenFactory(new Board());
        Token pawn = tokenFactory.build(TokenType.PAWN, Team.WHITE, new Point(0, 0));
        assertTrue(pawn instanceof Pawn);
        assertEquals(Team.WHITE, pawn.getTeam());
        assertEquals(new Point(0, 0), pawn.getLocation());
    }


    @Test
    public void shouldMoveWhitePawnForwardByOne() {
        TokenFactory tokenFactory = new TokenFactory(new Board());
        Token pawn = tokenFactory.build(TokenType.PAWN, Team.WHITE, new Point(0, 0));
        Point targetLocation = new Point(0, 1);
        pawn.move(targetLocation);
        assertEquals(targetLocation, pawn.getLocation());
    }


    @Test
    public void shouldMoveBlackPawnForwardByOne() {
        TokenFactory tokenFactory = new TokenFactory(new Board());
        Token pawn = tokenFactory.build(TokenType.PAWN, Team.BLACK, new Point(7, 7));
        Point targetLocation = new Point(7, 6);
        pawn.move(targetLocation);
        assertEquals(targetLocation, pawn.getLocation());
    }


    @Test
    public void shouldMoveWhitePawnForwardByTwoOnFirstTurn() {
        TokenFactory tokenFactory = new TokenFactory(new Board());
        Token pawn = tokenFactory.build(TokenType.PAWN, Team.WHITE, new Point(0, 0));
        Point targetLocation = new Point(0, 2);
        pawn.move(targetLocation);
        assertEquals(targetLocation, pawn.getLocation());
    }


    @Test
    public void shouldMoveBlackPawnForwardByTwoOnFirstTurn() {
        TokenFactory tokenFactory = new TokenFactory(new Board());
        Token pawn = tokenFactory.build(TokenType.PAWN, Team.BLACK, new Point(7, 7));
        Point targetLocation = new Point(7, 5);
        pawn.move(targetLocation);
        assertEquals(targetLocation, pawn.getLocation());
    }


    @Test
    public void shouldNotMovePawnForwardByTwoAfterFirstTurn() {
        TokenFactory tokenFactory = new TokenFactory(new Board());
        Token pawn = tokenFactory.build(TokenType.PAWN, Team.WHITE, new Point(0, 0));
        Point firstTargetLocation = new Point(0, 1);
        Point secondTargetLocation = new Point(0, 3);
        pawn.move(firstTargetLocation);
        pawn.move(secondTargetLocation);
        assertEquals(firstTargetLocation, pawn.getLocation());
    }


    @Test
    public void shouldNotMoveWhitePawnBackwards() {
        TokenFactory tokenFactory = new TokenFactory(new Board());
        Token pawn = tokenFactory.build(TokenType.PAWN, Team.WHITE, new Point(0, 1));
        Point targetLocation = new Point(0, 0);
        pawn.move(targetLocation);
        assertNotEquals(targetLocation, pawn.getLocation());
    }


    @Test
    public void shouldNotMoveBlackPawnBackwards() {
        TokenFactory tokenFactory = new TokenFactory(new Board());
        Token pawn = tokenFactory.build(TokenType.PAWN, Team.BLACK, new Point(7, 6));
        Point targetLocation = new Point(7, 7);
        pawn.move(targetLocation);
        assertNotEquals(targetLocation, pawn.getLocation());
    }


    @Test
    public void shouldKillBlackPawnWithWhitePawn() {
        TokenFactory tokenFactory = new TokenFactory(new Board());
        Token pawn = tokenFactory.build(TokenType.PAWN, Team.WHITE, new Point(0, 0));
        Point targetLocation = new Point(1, 1);
        tokenFactory.build(TokenType.PAWN, Team.BLACK, targetLocation);
        pawn.move(targetLocation);
        assertEquals(targetLocation, pawn.getLocation());
    }
    

    @Test
    public void shouldBlockPawnMovingForwardOntoWhiteToken() {
        TokenFactory tokenFactory = new TokenFactory(new Board());
        Token pawn = tokenFactory.build(TokenType.PAWN, Team.WHITE, new Point(0, 0));
        tokenFactory.build(TokenType.PAWN, Team.WHITE, new Point(0, 1));
        Point targetLocation = new Point(0, 1);
        pawn.move(targetLocation);
        assertNotEquals(targetLocation, pawn.getLocation());
    }


    @Test
    public void shouldBlockPawnMovingForwardOntoBlackToken() {
        TokenFactory tokenFactory = new TokenFactory(new Board());
        Token pawn = tokenFactory.build(TokenType.PAWN, Team.WHITE, new Point(0, 0));
        tokenFactory.build(TokenType.PAWN, Team.BLACK, new Point(0, 1));
        pawn.getBoard().setKing(tokenFactory.build(TokenType.KING, Team.WHITE, new Point(7, 7)));
        Point targetLocation = new Point(0, 1);
        pawn.move(targetLocation);
        assertNotEquals(targetLocation, pawn.getLocation());
    }


    @Test
    public void shouldBlockPawnMovingTwoThroughToken() {
        TokenFactory tokenFactory = new TokenFactory(new Board());
        Token pawn = tokenFactory.build(TokenType.PAWN, Team.WHITE, new Point(0, 0));
        tokenFactory.build(TokenType.PAWN, Team.WHITE, new Point(0, 1));
        Point targetLocation = new Point(0, 2);
        pawn.move(targetLocation);
        assertNotEquals(targetLocation, pawn.getLocation());
    }


    @Test
    public void shouldAllowMovingToBlockCheckForKing() {
        TokenFactory tokenFactory = new TokenFactory(new Board());
        Token pawn = tokenFactory.build(TokenType.PAWN, Team.WHITE, new Point(1, 0));
        tokenFactory.build(TokenType.ROOK, Team.BLACK, new Point(7, 1));
        pawn.getBoard().setKing(tokenFactory.build(TokenType.KING, Team.WHITE, new Point(0, 1)));
        Point targetLocation = new Point(1, 1);
        pawn.move(targetLocation);
        assertEquals(targetLocation, pawn.getLocation());
    }


    @Test
    public void shouldPreventLeavingKingInCheck() {
        TokenFactory tokenFactory = new TokenFactory(new Board());
        Token pawn = tokenFactory.build(TokenType.PAWN, Team.WHITE, new Point(1, 0));
        tokenFactory.build(TokenType.ROOK, Team.BLACK, new Point(7, 0));
        pawn.getBoard().setKing(tokenFactory.build(TokenType.KING, Team.WHITE, new Point(0, 0)));
        Point targetLocation = new Point(1, 1);
        pawn.move(targetLocation);
        assertNotEquals(targetLocation, pawn.getLocation());
    }
}
