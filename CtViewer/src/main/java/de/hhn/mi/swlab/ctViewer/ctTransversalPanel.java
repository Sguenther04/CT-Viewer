package de.hhn.mi.swlab.ctViewer;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class ctTransversalPanel extends JPanel {
  private StartScreenController controller;
      public ctTransversalPanel(StartScreenController controller){
        this.controller = controller;
      }
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    BufferedImage image = controller.createCtImage();
    g.drawImage(image, 0, 0, this);
  }
}
