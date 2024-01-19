package de.hhn.mi.swlab.ctViewer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class StartScreenController implements ChangeListener {
  private StartScreen screen;
  private String selectedFilePath;
  private JSlider screenSlider;
  private ctManager manager;
  private short[][][] ctData;
  private int xParameter;
  private int yParameter;
  private String patientData;
  private int[] ctParameters;
  private JLabel sliderLabel;
  private String[] patientInfo;
  private JButton loadButton;
  private JButton sagittalButton;
  private JButton frontalButton;
  private JButton dataChangeButton;
  private JMenu language;
  private ctTransversalPanel ctPanel;
  private Locale locale;
  private JLabel nameLabel;
  private JLabel heightLabel;
  private JLabel weightLabel;
  private JLabel izLabel;
  private JLabel birthLabel;
  private JMenuItem german;
  private JMenuItem english;

  public static boolean loaded = false;


  public StartScreenController(StartScreen screen, ctManager manager) {
    this.manager = manager;
    this.screen = screen;
    sagittalButton = screen.getSagittalButton();
    sagittalButton.addActionListener(e -> showSagittal());
    frontalButton = screen.getFrontalButton();
    frontalButton.addActionListener(e -> showFrontal());
    loadButton = screen.getLoadButton();
    loadButton.addActionListener(e -> loadCtImage());
    dataChangeButton = screen.getDataChangeButton();
    dataChangeButton.addActionListener(e -> changeData());
    screen.getSlider().addChangeListener(this);
    izLabel = screen.getIzLabel();
    birthLabel = screen.getBirthLabel();
    nameLabel = screen.getNameLabel();
    weightLabel = screen.getWeightLabel();
    heightLabel = screen.getHeightLabel();
    language = screen.getLanguage();
    english = screen.getEnglish();
    english.addActionListener( e -> languageChanged(1));
    german = screen.getGerman();
    german.addActionListener( e -> languageChanged(2));
    locale = screen.getCurrentLocale();

    screenSlider = screen.getSlider();
  }


  public void showFrontal() {
    frontalView view = new frontalView(manager);
    frontalController controller = new frontalController(manager, view);
    controller.start();
  }

  public void showSagittal() {
    sagittalView view = new sagittalView(manager);
    sagittalController controller = new sagittalController(manager, view);
    controller.start();
  }

  public void loadCtImage() {
    JFileChooser fileChooser = new JFileChooser();

    int response = fileChooser.showOpenDialog(screen);
    if (response == JFileChooser.APPROVE_OPTION) {
      selectedFilePath = fileChooser.getSelectedFile().getAbsolutePath();
      ctData = manager.getCtDataFromFile(selectedFilePath);
      patientData = manager.getPatientDataFromFile(selectedFilePath);
      ctParameters = manager.getCtParametersFromFile(selectedFilePath);
      patientInfo = manager.getPatientInfoFromFile(selectedFilePath);
      xParameter = ctParameters[0];
      yParameter = ctParameters[1];
      screen.fillPatientPanel();
      ctPanel = new ctTransversalPanel(this);
      ctPanel.setPreferredSize(new Dimension(255, 255));
      screen.add(ctPanel, BorderLayout.CENTER);
      ctPanel.repaint();
      screen.repaint();
      loaded = true;
      screenSlider.setValue(1);
      screenSlider.setValue(0);
    }
  }

  public void start() {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        screen.setVisible(true);
      }
    });
  }


  @Override
  public void stateChanged(ChangeEvent e) {
    ctPanel.repaint();
    screen.getSliderLabel().setText(screenSlider.getValue() + "");

  }

  public BufferedImage createCtImage() {
    BufferedImage image =
        new BufferedImage(xParameter, yParameter, BufferedImage.TYPE_USHORT_555_RGB);
    for (int y = 0; y < yParameter; y++) {
      for (int x = 0; x < xParameter; x++) {
        short intensity = ctData[screenSlider.getValue()][y][x];
        image.setRGB(x, y, intensity);
      }

    }
    return image;
  }

  public void changeData() {
    JFileChooser fileChooser = new JFileChooser();

    int response = fileChooser.showOpenDialog(screen);
    if (response == JFileChooser.APPROVE_OPTION) {
      String filePath = fileChooser.getSelectedFile().getAbsolutePath();

      String name = screen.getNameField().getText();
      String birth = screen.getBirthField().getText();
      String weight = screen.getWeightField().getText();
      String height = screen.getHeightField().getText();
      String iz = screen.getBirthField().getText();
      String infoToWrite = "Updated data from: " + selectedFilePath + "\n" +
          name + "\n" + birth + "\n" + weight + "\n" + height + "\n" + iz;

      try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
        writer.write(infoToWrite);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }

  public void languageChanged(int languageID) {
    if(languageID == 1){
      locale = new Locale("en");
      ResourceBundle bundle = ResourceBundle.getBundle("Ct-Ressourcebundle", locale);
      changeLanguage(bundle);
    } else if (languageID == 2) {
      locale = new Locale("de");
      ResourceBundle bundle = ResourceBundle.getBundle("Ct-Ressourcebundle", locale);
      changeLanguage(bundle);

    }

  }
  public void changeLanguage(ResourceBundle bundle){
    language.setText(bundle.getString("language"));
    english.setText(bundle.getString("english"));
    german.setText(bundle.getString("german"));
    sagittalButton.setText(bundle.getString("sagittal"));
    frontalButton.setText(bundle.getString("frontal"));
    loadButton.setText(bundle.getString("load"));
    dataChangeButton.setText(bundle.getString("change"));



    nameLabel.setText(bundle.getString("name"));
    heightLabel.setText(bundle.getString("height"));
    weightLabel.setText(bundle.getString("weight"));
    izLabel.setText(bundle.getString("iz"));
    birthLabel.setText(bundle.getString("birth"));
    screen.repaint();
    screen.getBar().repaint();
    screen.getLanguage().repaint();
    screen.getPatientPanel().repaint();
  }


}

