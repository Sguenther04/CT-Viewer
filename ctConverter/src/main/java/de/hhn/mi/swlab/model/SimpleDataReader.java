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
