import controller.Controller;
import model.Model;
import view.View;

public class Chess {

    public static void main(String[] args) {
        Model model = new Model();
        View view = new View(model);
        Controller controller = new Controller(model, view);

        controller.updateView();
        while(controller.isRunning()) {
            controller.performNextTurn();
            controller.updateView();
        }
    }
}