package util;

import java.util.Scanner;

import model.Point;
import model.Pair;

public class ConsoleUI {
  
  public static Pair getTurnRequest() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Token at: ");
    Point sourceTile = ConsoleUI.stringToPoint(scanner.nextLine());
    System.out.print("Move to: ");
    Point targetTile = ConsoleUI.stringToPoint(scanner.nextLine());
    scanner.close();
    return new Pair(sourceTile, targetTile);
  }


  private static Point stringToPoint(String string) {
    string = string.toUpperCase().replaceAll("\\s", "");
    char[] coords = string.toCharArray();
    Integer x = coords[0] - 65;
    Integer y = Character.getNumericValue(coords[1]);
    return new Point(x, y);
  }
}
