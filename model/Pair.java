package model;
public class Pair {

  private Point source;
  private Point target;

  public Pair(Point source, Point target) {
    this.source = source;
    this.target = target;
  }

  public Point getSource() {
    return this.source;
  }

  public Point getTarget() {
    return this.target;
  }

  public String toString() {
    return source.toString() + " " + target.toString();
  }

}
