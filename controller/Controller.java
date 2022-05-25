package controller;

import view.View;

import model.Model;
import model.Point;
import model.MoveRequest;
import model.token.Token;
import util.ConsoleUI;

public class Controller {
    
    private Model model;
    private View view;
    private boolean running;
    
    
    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        this.running = true;
    }


    public void updateView() {
        view.display();
    }

    
    public void performNextTurn() {
        view.displayMessage(model.getActivePlayer().toString() + "'s turn");
        MoveRequest playerChoice = getChoice();
        Token chosenToken = model.getBoard().getTokenAt(playerChoice.getSource());
        if (chosenToken.getTeam() != model.getActivePlayer() ) {
            view.displayMessage("Cannot move the opponent's tokens.");
            return;
        }
        if (!chosenToken.isValidMove(playerChoice.getTarget())) {
            view.displayMessage("That is not a valid move.");
            return;
        }
        if (chosenToken.putsTeamInCheck(playerChoice.getTarget())) {
            view.displayMessage("Cannot move into check.");
            return;
        }
        chosenToken.move(playerChoice.getTarget());
        model.toggleActivePlayer();
        view.displayMessage("Moved token.");
    }


    public void foo() {
        Token token = model.getBoard().getTokenAt(new Point(0, 1));
        System.out.println(token.toString());
        token.move(new Point(0, 2));
    }


    public MoveRequest getChoice() {
        return ConsoleUI.getTurnRequest();
    }


    public boolean isRunning() {
        return running;
    }
}