package model;
public class MoveRequest {

  private Point source;
  private Point target;

  public MoveRequest(Point source, Point target) {
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
