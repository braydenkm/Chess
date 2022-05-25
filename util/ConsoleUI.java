package util;

import model.Point;
import model.MoveRequest;


/**
 * Class for handling interaction with the user through the console.
 */
public class ConsoleUI {

    /**
     * Interact with the user to get their move request.
     * 
     * @return  MoveRequest containing token to move and target location.
     */
    public static MoveRequest getTurnRequest() {
        System.out.print("Token at: ");
        Point sourceTile = ConsoleUI.stringToPoint(System.console().readLine());
        System.out.print("Move to: ");
        Point targetTile = ConsoleUI.stringToPoint(System.console().readLine());
        return new MoveRequest(sourceTile, targetTile);
    }

    
    /**
     * Convert a string for a chess location (ie. "G3") into a point.
     * 
     * @param   string  input string to be processed.
     * @return          point from the converted string.
     */
    private static Point stringToPoint(String string) {
        string = string.toUpperCase().replaceAll("\\s", "");
        char[] coords = string.toCharArray();
        Integer x = coords[0] - 65;
        Integer y = Character.getNumericValue(coords[1]) - 1;
        return new Point(x, y);
    }
}
