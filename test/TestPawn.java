package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        Token whitePawn = tokenFactory.build(TokenType.PAWN, Team.WHITE, new Point(0, 0));
        whitePawn.move(new Point(0, 1));
        assertEquals(new Point(0, 1), whitePawn.getLocation());
    }


    @Test
    public void shouldMoveBlackPawnForwardByOne() {
        TokenFactory tokenFactory = new TokenFactory(new Board());
        Token blackPawn = tokenFactory.build(TokenType.PAWN, Team.BLACK, new Point(7, 7));
        blackPawn.move(new Point(7, 6));
        assertEquals(new Point(7, 6), blackPawn.getLocation());
    }


    @Test
    public void shouldMoveWhitePawnForwardByTwoOnFirstTurn() {
        TokenFactory tokenFactory = new TokenFactory(new Board());
        Token whitePawn = tokenFactory.build(TokenType.PAWN, Team.WHITE, new Point(0, 0));
        Point whiteTarget = new Point(0, 2);
        whitePawn.move(whiteTarget);
        assertEquals(whiteTarget, whitePawn.getLocation());
    }


    @Test
    public void shouldMoveBlackPawnForwardByTwoOnFirstTurn() {
        TokenFactory tokenFactory = new TokenFactory(new Board());
        Token blackPawn = tokenFactory.build(TokenType.PAWN, Team.BLACK, new Point(7, 7));
        Point blackTarget = new Point(7, 5);
        blackPawn.move(blackTarget);
        assertEquals(blackTarget, blackPawn.getLocation());
    }


    @Test
    public void shouldNotMovePawnForwardByTwoAfterFirstTurn() {
        TokenFactory tokenFactory = new TokenFactory(new Board());
        Token whitePawn = tokenFactory.build(TokenType.PAWN, Team.WHITE, new Point(0, 0));
        Point firstMove = new Point(0, 1);
        Point secondMove = new Point(0, 3);
        whitePawn.move(firstMove);
        whitePawn.move(secondMove);
        assertEquals(firstMove, whitePawn.getLocation());
    }


    @Test
    public void shouldNotMoveWhitePawnBackwards() {
        TokenFactory tokenFactory = new TokenFactory(new Board());
        Point whiteSource = new Point(0, 1);
        Token whitePawn = tokenFactory.build(TokenType.PAWN, Team.WHITE, whiteSource);
        whitePawn.move(new Point(0, 0));
        assertEquals(whiteSource, whitePawn.getLocation());
    }


    @Test
    public void shouldNotMoveBlackPawnBackwards() {
        TokenFactory tokenFactory = new TokenFactory(new Board());
        Point blackSource = new Point(7, 6);
        Token blackPawn = tokenFactory.build(TokenType.PAWN, Team.BLACK, blackSource);
        blackPawn.move(new Point(7, 7));
        assertEquals(blackSource, blackPawn.getLocation());
    }
    

    @Test
    public void shouldBlockWhitePawnMovingOneOntoBlackPawn() {
        TokenFactory tokenFactory = new TokenFactory(new Board());
        Point whiteSource = new Point(0, 0);
        Token whitePawn = tokenFactory.build(TokenType.PAWN, Team.WHITE, whiteSource);
        tokenFactory.build(TokenType.PAWN, Team.BLACK, new Point(0, 1));
        // Need a king for black pawn to determine if placing in check.
        whitePawn.getBoard().setKing(tokenFactory.build(TokenType.KING, Team.WHITE, new Point(7, 7)));
        whitePawn.move(new Point(0, 1));
        assertEquals(whiteSource, whitePawn.getLocation());
    }


    @Test
    public void shouldBlockWhitePawnMovingTwoThroughBlackPawn() {
        TokenFactory tokenFactory = new TokenFactory(new Board());
        Point whiteSource = new Point(0, 0);
        Token whitePawn = tokenFactory.build(TokenType.PAWN, Team.WHITE, whiteSource);
        tokenFactory.build(TokenType.PAWN, Team.BLACK, new Point(0, 1));
        // Need a king for black pawn to determine if placing in check.
        whitePawn.getBoard().setKing(tokenFactory.build(TokenType.KING, Team.WHITE, new Point(7, 7)));
        whitePawn.move(new Point(0, 2));
        assertEquals(whiteSource, whitePawn.getLocation());
    }


}
