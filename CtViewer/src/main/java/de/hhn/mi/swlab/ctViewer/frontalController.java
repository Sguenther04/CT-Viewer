package de.hhn.mi.swlab.ctViewer;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class frontalController implements ChangeListener {
  private JSlider screenSlider;
  private ctFrontalPanel ctPanel;
  private ctManager manager;
  private frontalView screen;
  private short[][][] ctData;
  private int zParameter;
  private int xParameter;

  public frontalController(ctManager manager, frontalView screen) {
    this.manager = manager;
    this.screen = screen;
    screenSlider = screen.getSlider();
    screenSlider.addChangeListener(this);
    ctData = manager.getCtData();
    int[] parameter = manager.getCtParameters();
    xParameter = parameter[0];
    zParameter = parameter[2];
    screen.setSize(xParameter ,zParameter + 100);
    ctPanel = new ctFrontalPanel(this);
    screen.add(ctPanel, BorderLayout.CENTER);
    screen.repaint();
    ctPanel.repaint();
    screenSlider.setValue(1);
    screenSlider.setValue(0);
  }

  public int getZParameter() {
    return zParameter;
  }

  public int getXParameter() {
    return xParameter;
  }

  public BufferedImage createCtImage() {
    BufferedImage image = new BufferedImage(xParameter, zParameter, BufferedImage.TYPE_USHORT_555_RGB);
    for (int z = 0; z < zParameter; z++) {
      for (int x = 0; x < xParameter; x++) {
        short intensity = ctData[z][screenSlider.getValue()][x];
        image.setRGB(x, z, intensity);
      }

    }
    return image;
  }

  @Override
  public void stateChanged(ChangeEvent e) {
    ctPanel.repaint();
    screen.getSliderLabel().setText("" + screenSlider.getValue());
  }

  public void start() {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        screen.setVisible(true);
      }
    });
  }
}
