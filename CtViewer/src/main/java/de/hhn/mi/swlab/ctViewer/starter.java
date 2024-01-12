package de.hhn.mi.swlab.ctViewer;

public class starter {
  public static void main(String[] args) {
    ctManager manager = new ctManager();
    StartScreen screen = new StartScreen(manager);
    StartScreenController controller = new StartScreenController(screen,manager);
    controller.start();
  }
}
