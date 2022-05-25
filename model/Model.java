package model;

import globals.Team;

public class Model {
  
  private Board board;
  private Team activePlayer;



  public Model() {
    this.board = new Board();
    this.activePlayer = Team.WHITE;
  }


  public Board getBoard() {
    return this.board;
  }


  public Team getActivePlayer() {
    return this.activePlayer;
  }


  public void toggleActivePlayer() {
    activePlayer = (activePlayer == Team.WHITE) ? Team.BLACK : Team.WHITE;
  }
}
