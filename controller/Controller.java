package controller;

import view.View;
import model.Model;
import model.MoveRequest;
import model.token.Token;
import util.ConsoleUI;
import util.MoveResponse;


/**
 * Controller class sends orders to the Model to make changes, and tells
 * the View when to update the UI.
 * This is part of the MVC Architecture.
 */
public class Controller {
    
    /**
     * Model containing all the data for chess program.
     */
    private Model model;

    /**
     * View containing all the UI logic for chess program.
     */
    private View view;

    /**
     * Flag for keeping program running.
     */
    private boolean running;
    
    
    /**
     * Default Constructor for Controller.
     * 
     * @param   model   Model for MVC.
     * @param   view    View for MVC.
     */
    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        this.running = true;
    }


    /**
     * Command the View to update the UI.
     */
    public void updateView() {
        view.display();
    }

    
    /**
     * Interact with the user to take their turn moving a token.
     */
    public void performNextTurn() {
        view.displayMessage(model.getActivePlayer().toString() + "'s turn");
        MoveRequest playerChoice = getChoice();
        Token chosenToken = model.getBoard().getTokenAt(playerChoice.getSource());
        if (chosenToken.getTeam() != model.getActivePlayer() ) {
            view.displayMessage("Cannot move the opponent's tokens.");
            return;
        }
        MoveResponse response = chosenToken.move(playerChoice.getTarget());
        if (!response.wasSuccessfull()) {
            view.displayMessage(response.getMessage());
            return;
        }
        model.toggleActivePlayer();
    }


    /**
     * Interact with the user to get their choice of token and target location.
     * 
     * @return  MoveRequest that contains token to move and target location.
     */
    public MoveRequest getChoice() {
        return ConsoleUI.getTurnRequest();
    }


    public void setUpDebugTokens() {
        model.getBoard().setUpDebugTokens();
    }


    public void setUpStandardGameTokens() {
        model.getBoard().setUpStandardGameTokens();
    }


    /**
     * Check if the flag for running program is set.
     * 
     * @return  running flag.
     */
    public boolean isRunning() {
        return running;
    }
}