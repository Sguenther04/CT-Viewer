package de.hhn.mi.swlab.ctViewer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Label;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class sagittalView extends JFrame {
  private ctManager manager;
  private JSlider slider;
  private Label sliderLabel;
  private JPanel sliderPanel;
  private JMenuBar bar;
  private JMenu viewType;

  public sagittalView(ctManager manager) throws HeadlessException {
    this.manager = manager;
    setLayout(new BorderLayout());
    bar = new JMenuBar();

    viewType = new JMenu("sagittal view");
    bar.add(viewType);
    setJMenuBar(bar);
    this.manager = manager;
    setLayout(new BorderLayout());
    sliderPanel = new JPanel();
    sliderLabel= new Label("0");
    slider = new JSlider(0,255);
    slider.setValue(0);
    sliderPanel.add(slider);
    sliderPanel.add(sliderLabel);
    add(sliderPanel, BorderLayout.SOUTH);
    setLocationRelativeTo(null);


  }

  public JSlider getSlider() {
    return slider;
  }

  public Label getSliderLabel() {
    return sliderLabel;
  }
}
