package view;

import model.Model;

public class View {

  private Model model;

  public View(Model model) {
    this.model = model;
  }

  public void display() {
    model.getBoard().display();
  }

  public void displayMessage(String message) {
    System.out.println(message);
  }
}
