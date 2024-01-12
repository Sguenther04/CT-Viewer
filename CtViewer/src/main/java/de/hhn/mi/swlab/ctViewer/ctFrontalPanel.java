package de.hhn.mi.swlab.ctViewer;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class ctFrontalPanel extends JPanel {
  private frontalController controller;

  public ctFrontalPanel(frontalController controller) {
    this.controller = controller;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;

    int width = controller.getXParameter();
    int height = controller.getZParameter();


    AffineTransform at = new AffineTransform();
    at.rotate(Math.PI, width / 2.0, height / 2.0);
    g2d.setTransform(at);


    BufferedImage image = controller.createCtImage();
    g.drawImage(image, 0, 0, this);
  }
}
