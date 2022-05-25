package controller;

import view.View;

import model.Model;
import model.Point;
import model.Pair;
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
        Pair playerChoice = getChoice();
        Token chosenToken = model.getBoard().getTokenAt(playerChoice.getSource());
        if (chosenToken.getTeam() != model.getActivePlayer() ) {
            view.displayMessage("Cannot move the opponent's tokens.");
            return;
        }
        if (chosenToken.isValidMove(playerChoice.getTarget())) {
            chosenToken.move(playerChoice.getTarget());
            view.displayMessage("Moved token.");

        }
    }


    public void foo() {
        Token token = model.getBoard().getTokenAt(new Point(0, 1));
        System.out.println(token.toString());
        token.move(new Point(0, 2));
    }


    public Pair getChoice() {
        return ConsoleUI.getTurnRequest();
    }


    public boolean isRunning() {
        return running;
    }
}