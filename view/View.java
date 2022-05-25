package view;

import model.Model;


/**
 * View class receives orders from the Controller to update the UI displayed
 * to the user.
 * This is part of the MVC Architecture.
 */
public class View {

    /**
     * Model of the data to display.
     */
    private Model model;


    /**
     * Default Constructor for View.
     * 
     * @param   model   contains the data for the view to display.
     */
    public View(Model model) {
        this.model = model;
    }

    
    /**
     * Display the model to the user.
     */
    public void display() {
        model.getBoard().display();
    }

    
    /**
     * Display text the the user.
     * 
     * @param   message text to display to user.
     */
    public void displayMessage(String message) {
        System.out.println(message);
    }
}
