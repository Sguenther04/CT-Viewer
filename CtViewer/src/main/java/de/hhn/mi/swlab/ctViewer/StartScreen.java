package de.hhn.mi.swlab.ctViewer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;

public class StartScreen extends JFrame {
  private JMenuBar bar;

  private JButton loadButton;
  private JButton sagittalButton;
  private JButton frontalButton;
  private JButton dataChangeButton;
  private JMenu language;
  private JSlider slider;
  private JPanel buttonPanel;
  private JPanel sliderPanel;
  private JLabel sliderLabel;
  private ctManager manager;
  private JPanel patientPanel;
  private JTextField nameField;
  private JTextField birthField;
  private JTextField weightField;
  private JTextField heightField;
  private JTextField izField;
  private Locale locale;
  private JLabel nameLabel;
  private JLabel heightLabel;
  private JLabel weightLabel;
  private JLabel izLabel;
  private JLabel birthLabel;
  private JMenuItem german;
  private JMenuItem english;

  public JMenuItem getGerman() {
    return german;
  }

  public JMenuItem getEnglish() {
    return english;
  }

  public StartScreen(ctManager manager) {
    locale = new Locale("en");

    this.manager = manager;
    setLayout(new BorderLayout());
    bar = new JMenuBar();
    language = new JMenu("language");
    german = new JMenuItem("german");
    english = new JMenuItem("english");
    language.add(german);
    language.add(english);

    bar.add(language);
    setJMenuBar(bar);

    buttonPanel = new JPanel();
    buttonPanel.setPreferredSize(new Dimension(100, 100));
    loadButton = new JButton("load ct-data");
    frontalButton = new JButton("frontal View");
    sagittalButton = new JButton("sagittal view");
    dataChangeButton = new JButton("change Patient Info");
    sliderPanel = new JPanel();
    sliderPanel.setPreferredSize(new Dimension(100, 100));
    slider = new JSlider(0, 174);
    slider.setValue(0);
    sliderLabel = new JLabel("0");
    patientPanel = new JPanel(new GridLayout(5, 2));

    nameLabel = new JLabel("name");
    birthLabel = new JLabel("birth");
    weightLabel = new JLabel("weight");
    heightLabel = new JLabel("height");
    izLabel = new JLabel("IZ");

    add(patientPanel, BorderLayout.EAST);

    buttonPanel.add(sagittalButton);
    buttonPanel.add(frontalButton);
    buttonPanel.add(loadButton);
    buttonPanel.add(dataChangeButton);
    add(buttonPanel, BorderLayout.NORTH);

    sliderPanel.add(slider);
    sliderPanel.add(sliderLabel);
    add(sliderPanel, BorderLayout.SOUTH);


    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setSize(500, 500);


  }

  public JMenuBar getBar() {
    return bar;
  }

  public JMenu getLanguage() {
    return language;
  }

  public JPanel getButtonPanel() {
    return buttonPanel;
  }

  public Locale getCurrentLocale() {
    return locale;
  }

  public void fillPatientPanel() {
    String[] patientData = manager.getPatientInfo();
    String[] name = patientData[0].split("\t");
    String[] birth = patientData[1].split("\t");
    String[] weight = patientData[2].split("\t");
    String[] height = patientData[3].split("\t");
    String[] iz = patientData[4].split("\t");



    nameField = new JTextField(name[1]);
    birthField = new JTextField(birth[1]);
    weightField = new JTextField(weight[1]);
    heightField = new JTextField(height[1]);
    izField = new JTextField(iz[1]);

    patientPanel.add(nameLabel);
    patientPanel.add(nameField);
    patientPanel.add(birthLabel);
    patientPanel.add(birthField);
    patientPanel.add(weightLabel);
    patientPanel.add(weightField);
    patientPanel.add(heightLabel);
    patientPanel.add(heightField);
    patientPanel.add(izLabel);
    patientPanel.add(izField);


  }

  public JButton getDataChangeButton() {
    return dataChangeButton;
  }

  public JSlider getSlider() {
    return slider;
  }

  public JButton getLoadButton() {
    return loadButton;
  }

  public JButton getSagittalButton() {
    return sagittalButton;
  }

  public JButton getFrontalButton() {
    return frontalButton;
  }

  public JTextField getBirthField() {
    return birthField;
  }

  public JPanel getPatientPanel() {
    return patientPanel;
  }

  public JTextField getWeightField() {
    return weightField;
  }

  public JTextField getNameField() {
    return nameField;
  }

  public JTextField getHeightField() {
    return heightField;
  }

  public JTextField getIzField() {
    return izField;
  }

  public JLabel getSliderLabel() {
    return sliderLabel;
  }

  public JLabel getHeightLabel() {
    return heightLabel;
  }

  public JLabel getIzLabel() {
    return izLabel;
  }

  public JPanel getSliderPanel() {
    return sliderPanel;
  }

  public JLabel getNameLabel() {
    return nameLabel;
  }

  public JLabel getWeightLabel() {
    return weightLabel;
  }

  public JLabel getBirthLabel() {
    return birthLabel;
  }
}
