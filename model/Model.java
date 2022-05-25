package model;

import globals.Team;


/**
 * Model class receives orders from the Controller to update the data contained
 * inside this model.
 * This is part of the MVC Architecture.
 */
public class Model {

    /**
     * Board contains the tokens of this chess game.
     */
    private Board board;

    /**
     * Keeps track of whose turn it is.
     */
    private Team activePlayer;


    /**
     * Default Constructor for Model.
     */
    public Model() {
        this.board = new Board();
        this.activePlayer = Team.WHITE;
    }


    /**
     * Getter for board.
     * 
     * @return  game board.
     */
    public Board getBoard() {
        return this.board;
    }


    /**
     * Getter for the active player.
     * 
     * @return  the team whose turn it is.
     */
    public Team getActivePlayer() {
        return this.activePlayer;
    }


    /**
     * Set the active player to the other team.
     */
    public void toggleActivePlayer() {
        activePlayer = (activePlayer == Team.WHITE) ? Team.BLACK : Team.WHITE;
    }
}
