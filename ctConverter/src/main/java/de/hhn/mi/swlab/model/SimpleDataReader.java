package de.hhn.mi.swlab.model;

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

public class SimpleDataReader implements DataReader{

  private String txtFileContent;
  private String txtFileContentCt;
  private int[] imageParameters;
  private short[] imageData;
  private ArrayList<Integer> ctData;

  /**
   * Read the content from the text file
   *
   * @param filepath the path to the text file
   * @return the content of the text file as a string
   * @throws FileNotFoundException if the file is not found
   */
  @Override
  public String readTxtFile(String filepath) throws FileNotFoundException {
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
    return txtFileContent;
  }

  /**
   * Reads content from the binary file and returns it in an shorts Array
   *
   * @param filepath the path to the binary file
   * @return an Array of shorts with the data from the binary file
   * @throws FileNotFoundException if the file is not found
   */
  @Override
  public short[] readBinFile(String filepath) throws FileNotFoundException {
    try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream(filepath))) {
      int numberOfShorts = dataInputStream.available() / 2;

      imageData = new short[numberOfShorts];

      for (int i = 0; i < numberOfShorts; i++) {
        imageData[i] = dataInputStream.readShort();
      }

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return imageData;
  }

  /**
   * Reads the text file content until the word 'DATA' and returns it as a string
   *
   * @param filepath the path to the text file
   * @return the content of the text file until the word 'DATA' as a string
   * @throws FileNotFoundException if the file is not found
   */
  @Override
  public String readTxtFileContentFromCtFile(String filepath) throws FileNotFoundException {
    File file = new File(filepath);
    StringBuilder content = new StringBuilder();
    ctData = new ArrayList<>();
    boolean loop = true;
    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      String line;
      while (loop) {
        line = reader.readLine();
        content.append(line).append("\n");
        if(line.trim().equalsIgnoreCase("DATA")) {
          loop = false;
        }
      }
      txtFileContentCt = content.toString();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return txtFileContentCt;

  }

  /**
   * Reads binary file content from the text file, starting from the word 'DATA' and return it as an integer ArrayList
   *
   * @param filepath the path to the binary file
   * @return an integer ArrayList with the binary content from the ct File
   * @throws FileNotFoundException if the file is not found
   */
  @Override
  public ArrayList<Integer> readBinFileContentFromCtFile(String filepath) throws FileNotFoundException {
    File file = new File(filepath);
    StringBuilder content = new StringBuilder();
    ctData = new ArrayList<>();
    boolean loop = true;
    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      String line;
      while (loop) {
        line = reader.readLine();
        if(line.trim().equalsIgnoreCase("DATA")) {
          loop = false;
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    if (!loop) {
      try {
        Scanner scanner = new Scanner(new File(filepath));
        for (int i = 0; i < 3; i++) {
          scanner.nextLine();
        }
        int i = 0;
        while (scanner.hasNextInt()) {
          ctData.add(i, scanner.nextInt());
          i++;
        }

      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
    }
    return ctData;
  }

  /**
   * Reads image parameters from the text file
   *
   * @param filepath the path to the text file
   * @return an Array with the image parameters
   */
  public int[] getImageParameters(String filepath) {
    try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
      imageParameters = new int[3];
      String line;
      boolean loop = true;
      while (loop) {
        line = reader.readLine();
        System.out.println(line);
        if(line.trim().equalsIgnoreCase("DATA")){
          String line2;
          for (int i = 0; i < 3; i++) {
            line2 = reader.readLine();
            System.out.println(line2);
            int parameter = Integer.parseInt(line2);
            System.out.println(parameter);
            imageParameters[i]= parameter;
          }
          loop = false;
        }


      }
    } catch (IOException e) {
      e.printStackTrace();
    }


    return imageParameters;
  }
}
