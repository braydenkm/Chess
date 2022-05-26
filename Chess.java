import controller.Controller;
import model.Model;
import view.View;

/**
 * Chess program created to practice using MVC Architecture, interfaces, and
 * select design patterns.
 * 
 * @author Brayden Martin
 */
public class Chess {

    public static void main(String[] args) {
        Model model = new Model();
        View view = new View(model);
        Controller controller = new Controller(model, view);

        // controller.setUpDebugTokens();
        controller.setUpStandardGameTokens();

        controller.updateView();
        while(controller.isRunning()) {
            controller.performNextTurn();
            controller.updateView();
        }
    }
}