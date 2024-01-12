package de.hhn.mi.swlab.ctViewer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
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

  public StartScreen(ctManager manager){
    this.manager = manager;
    setLayout(new BorderLayout());
    bar = new JMenuBar();
    language = new JMenu("language");
    bar.add(language);
    setJMenuBar(bar);

    buttonPanel = new JPanel();
    buttonPanel.setPreferredSize(new Dimension(100,100));
    loadButton = new JButton("load ct-data");
    frontalButton = new JButton("frontal View");
    sagittalButton = new JButton("sagittal view");
    dataChangeButton = new JButton("change Patient Info");
    sliderPanel = new JPanel();
    sliderPanel.setPreferredSize(new Dimension(100,100));
    slider = new JSlider(0,174);
    slider.setValue(0);
    sliderLabel = new JLabel("0");
    patientPanel = new JPanel(new GridLayout(5,2));
    add(patientPanel,BorderLayout.EAST);

    buttonPanel.add(sagittalButton);
    buttonPanel.add(frontalButton);
    buttonPanel.add(loadButton);
    buttonPanel.add(dataChangeButton);
    add(buttonPanel,BorderLayout.NORTH);

    sliderPanel.add(slider);
    sliderPanel.add(sliderLabel);
    add(sliderPanel,BorderLayout.SOUTH);


    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setSize(500,500);


  }
  public void fillPatientPanel(){
    String[] patientData = manager.getPatientInfo();
    String[] name = patientData[0].split("\t");
    String[] birth = patientData[1].split("\t");
    String[] weight = patientData[2].split("\t");
    String[] height = patientData[3].split("\t");
    String[] iz = patientData[4].split("\t");


    JLabel nameLabel = new JLabel("name");
    JLabel birthLabel = new JLabel("birth");
    JLabel weightLabel = new JLabel("weight");
    JLabel heightLabel = new JLabel("height");
    JLabel izLabel = new JLabel("IZ");
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

}
