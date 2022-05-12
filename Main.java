import controller.Controller;
import model.Board;

public class Main {

    public static void main(String[] args) {
        Board board = new Board();
        Controller controller = new Controller(board);
        board.display();
        controller.moveToken();
        board.display();
    }
}