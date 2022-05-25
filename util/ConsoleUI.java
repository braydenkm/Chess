package util;

import model.Point;
import model.MoveRequest;

public class ConsoleUI {
  
  public static MoveRequest getTurnRequest() {
    System.out.print("Token at: ");
    Point sourceTile = ConsoleUI.stringToPoint(System.console().readLine());
    System.out.print("Move to: ");
    Point targetTile = ConsoleUI.stringToPoint(System.console().readLine());
    return new MoveRequest(sourceTile, targetTile);
  }


  private static Point stringToPoint(String string) {
    string = string.toUpperCase().replaceAll("\\s", "");
    char[] coords = string.toCharArray();
    Integer x = coords[0] - 65;
    Integer y = Character.getNumericValue(coords[1]) - 1;
    return new Point(x, y);
  }
}
