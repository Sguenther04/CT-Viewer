package de.hhn.mi.swlab.model;

import de.hhn.mi.swlab.ConsoleLogger;
import de.hhn.mi.swlab.LoggerCalls;
import de.hhn.mi.swlab.LoggerFactory;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class SimpleDataReader implements DataReader {
  private ConsoleLogger logger = (ConsoleLogger) LoggerFactory.createLogger("console");
  private String txtFileContent;
  private String txtFileContentCt;
  private int[] imageParameters;
  private short[] imageData;
  private ArrayList<Short> ctData;
  private short[][][] ct3Dimensional;
  private String[] patientInfo;

  /**
   * Read the content from the text file
   *
   * @param filepath the path to the text file
   * @return the content of the text file as a string
   */
  @Override
  public String readTxtFile(String filepath) {
    File file = new File(filepath);
    StringBuilder content = new StringBuilder();
    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      String line;
      while ((line = reader.readLine()) != null) {
        content.append(line).append("\n");
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
    txtFileContent = content.toString();
    logger.loggerCall("Text file was read", LoggerCalls.INFO);
    return txtFileContent;
  }

  /**
   * Reads content from the binary file and returns it in a short array
   *
   * @param filepath the path to the binary file
   * @return an Array of shorts with the data from the binary file
   */
  @Override
  public short[] readBinFile(String filepath) {
    try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream(filepath))) {
      int numberOfShorts = dataInputStream.available() / 2;

      imageData = new short[numberOfShorts];

      for (int i = 0; i < numberOfShorts; i++) {
        imageData[i] = dataInputStream.readShort();
      }

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    logger.loggerCall("Binary file was read", LoggerCalls.INFO);
    return imageData;
  }

  /**
   * Reads the text file content until the word 'DATA' and returns it as a string
   *
   * @param filepath the path to the text file
   * @return the content of the text file as a string
   */
  @Override
  public String readTxtFileContentFromCtFile(String filepath) {
    StringBuilder content = new StringBuilder();
    ctData = new ArrayList<>();
    int[] parameterTxt;
    boolean loop = true;
    try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
      String line;
      while (loop) {
        line = reader.readLine();
        content.append(line).append("\n");
        if (line.trim().equalsIgnoreCase("DATA")) {
          parameterTxt = getImageParameters(filepath);
          for (int i = 0; i < 3; i++) {
            content.append(parameterTxt[i]).append(("\n"));
          }
          loop = false;
        }
      }
      txtFileContentCt = content.toString();
    } catch (IOException e) {
      e.printStackTrace();
    }
    logger.loggerCall("Text file contents were extracted from ct file", LoggerCalls.INFO);
    return txtFileContentCt;

  }

  /**
   * Reads binary file content from the text file, starting from the word 'DATA' and return it as an integer ArrayList
   *
   * @param filepath the path to the binary file
   * @return an integer ArrayList with the binary content from the ct File
   */
  @Override
  public ArrayList<Short> readBinFileContentFromCtFile(String filepath) {
    File file = new File(filepath);
    ctData = new ArrayList<>();
    boolean loop = true;
    try {
      Scanner scanner = new Scanner(new File(filepath));
      while (loop) {
        String line = scanner.nextLine();
        if (line.trim().equalsIgnoreCase("data")) {
          while (scanner.hasNextInt()) {
            short num = scanner.nextShort();
            ctData.add(num);
            loop = scanner.hasNextShort();
          }

        }
      }


    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
    for (int i = 0; i < 3; i++) {
      ctData.remove(0);
    }
    return ctData;
  }


  /**
   * Reads image parameters from the text file
   *
   * @param filepath the path to the text file
   * @return an Array with the image parameters
   */
  @Override
  public int[] getImageParameters(String filepath) {
    try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
      imageParameters = new int[3];
      String line;
      boolean loop = true;
      while (loop) {
        line = reader.readLine();
        if (line.trim().equalsIgnoreCase("DATA")) {
          String line2;
          for (int i = 0; i < 3; i++) {
            line2 = reader.readLine();
            int parameter = Integer.parseInt(line2);
            imageParameters[i] = parameter;
          }
          loop = false;
        }


      }
    } catch (IOException e) {
      e.printStackTrace();
    }


    logger.loggerCall("Image parameters were extracted from Text file", LoggerCalls.INFO);
    return imageParameters;
  }

  @Override
  public String[] getPatientInfoFromCt(String filepath) {
    patientInfo = new String[6];
    try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
      String line;
      boolean loop = true;
      int j = 0;
      while (loop) {
        line = reader.readLine();
        if (line.trim().equalsIgnoreCase("Data")) {
          loop = false;
        }
        if(j < 6){
          patientInfo[j] = line;
          j++;
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }


    return patientInfo;
  }


  /**
   * Reads image parameters from a text file and organizes binary file content
   * into a three-dimensional short array.
   *
   * @param filepath the path to the text file containing image parameters
   */
  public short[][][] generate3DimensionalCtData(String filepath) {
    ctData = readBinFileContentFromCtFile(filepath);
    int[] imageParameters = getImageParameters(filepath);

    int width = imageParameters[0];
    int height = imageParameters[1];
    int depth = imageParameters[2];

    ct3Dimensional = new short[depth][height][width];

    int index = 0;
    for (int d = 0; d < depth; d++) {
      for (int h = 0; h < height; h++) {
        for (int w = 0; w < width; w++) {
          ct3Dimensional[d][h][w] = ctData.get(index);
          index++;
        }
      }
    }

    return ct3Dimensional;
  }

  public short[]getImageData() {
    return imageData;
  }

}
