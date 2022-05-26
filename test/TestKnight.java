package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import globals.Team;
import globals.TokenType;
import model.Board;
import model.Point;
import model.token.Knight;
import model.token.Token;
import model.token.TokenFactory;


public class TestKnight {
        
    @Test
    public void shouldMakeWhiteKnightAtOrigin() {
        TokenFactory tokenFactory = new TokenFactory(new Board());
        Token knight = tokenFactory.build(TokenType.KNIGHT, Team.WHITE, new Point(0, 0));
        assertTrue(knight instanceof Knight);
        assertEquals(Team.WHITE, knight.getTeam());
        assertEquals(new Point(0, 0), knight.getLocation());
    }


    @Test
    public void shouldMoveKightInL() {
        TokenFactory tokenFactory = new TokenFactory(new Board());
        Token knight = tokenFactory.build(TokenType.KNIGHT, Team.WHITE, new Point(0, 0));
        Point targetLocation = new Point(1, 2);
        knight.move(targetLocation);
        assertEquals(targetLocation, knight.getLocation());
    }


    @Test
    public void shouldNotMoveKightStraight() {
        TokenFactory tokenFactory = new TokenFactory(new Board());
        Token knight = tokenFactory.build(TokenType.KNIGHT, Team.WHITE, new Point(0, 0));
        Point targetLocation = new Point(2, 0);
        knight.move(targetLocation);
        assertNotEquals(targetLocation, knight.getLocation());
    }


    @Test
    public void shouldKillBlackKnightWithWhiteKnight() {
        TokenFactory tokenFactory = new TokenFactory(new Board());
        Token knight = tokenFactory.build(TokenType.KNIGHT, Team.WHITE, new Point(0, 0));
        Point targetLocation = new Point(2, 1);
        tokenFactory.build(TokenType.KNIGHT, Team.BLACK, targetLocation);
        knight.move(targetLocation);
        assertEquals(targetLocation, knight.getLocation());
    }


    @Test
    public void shouldNotBlockWhiteKnightMovingOverTokens() {
        TokenFactory tokenFactory = new TokenFactory(new Board());
        Token knight = tokenFactory.build(TokenType.KNIGHT, Team.WHITE, new Point(0, 0));
        // Suround knight with pawns.
        tokenFactory.build(TokenType.PAWN, Team.WHITE, new Point(0, 1));
        tokenFactory.build(TokenType.PAWN, Team.WHITE, new Point(1, 1));
        tokenFactory.build(TokenType.PAWN, Team.WHITE, new Point(2, 1));
        tokenFactory.build(TokenType.PAWN, Team.WHITE, new Point(1, 0));
        Point targetLocation = new Point(1, 2);
        knight.move(targetLocation);
        assertEquals(targetLocation, knight.getLocation());
    }


    @Test
    public void shouldAllowMovingToBlockCheckForKing() {
        TokenFactory tokenFactory = new TokenFactory(new Board());
        Token knight = tokenFactory.build(TokenType.KNIGHT, Team.WHITE, new Point(1, 0));
        tokenFactory.build(TokenType.ROOK, Team.BLACK, new Point(7, 1));
        knight.getBoard().setKing(tokenFactory.build(TokenType.KING, Team.WHITE, new Point(0, 1)));
        Point targetLocation = new Point(3, 1);
        knight.move(targetLocation);
        assertEquals(targetLocation, knight.getLocation());
    }


    @Test
    public void shouldPreventLeavingKingInCheck() {
        TokenFactory tokenFactory = new TokenFactory(new Board());
        Token knight = tokenFactory.build(TokenType.KNIGHT, Team.WHITE, new Point(1, 0));
        tokenFactory.build(TokenType.ROOK, Team.BLACK, new Point(7, 0));
        knight.getBoard().setKing(tokenFactory.build(TokenType.KING, Team.WHITE, new Point(0, 0)));
        Point targetLocation = new Point(3, 1);
        knight.move(targetLocation);
        assertNotEquals(targetLocation, knight.getLocation());
    }
}
