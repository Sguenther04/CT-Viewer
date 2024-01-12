package de.hhn.mi.swlab.ctViewer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class sagittalController implements ChangeListener {
  private JSlider screenSlider;
  private ctSagittalPanel ctPanel;
  private ctManager manager;
  private sagittalView screen;
  private short[][][] ctData;
  private int zParameter;
  private int yParameter;

  public sagittalController(ctManager manager, sagittalView screen) {
    this.manager = manager;
    this.screen = screen;
    screenSlider = screen.getSlider();
    screenSlider.addChangeListener(this);
    ctData = manager.getCtData();
    int[] parameter = manager.getCtParameters();
    yParameter = parameter[1];
    zParameter = parameter[2];
    screen.setSize(yParameter,zParameter + 100);
    ctPanel = new ctSagittalPanel(this);
    screen.add(ctPanel, BorderLayout.CENTER);
    screen.repaint();
    ctPanel.repaint();
    screenSlider.setValue(1);
    screenSlider.setValue(0);
  }

  public int getZParameter() {
    return zParameter;
  }

  public int getYParameter() {
    return yParameter;
  }

  public BufferedImage createCtImage() {
    BufferedImage image = new BufferedImage(yParameter, zParameter, BufferedImage.TYPE_USHORT_555_RGB);
    for (int z = 0; z < zParameter; z++) {
      for (int y = 0; y < yParameter; y++) {
        short intensity = ctData[z][y][screenSlider.getValue()];
        image.setRGB(y, z, intensity);
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
