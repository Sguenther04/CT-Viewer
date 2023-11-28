package de.hhn.mi.swlab.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class DataConverter {
  private static StringBuilder content;
  private static String txtFileContent;
  private static short[] data;
  private static int[] imageParameters;
  private static ArrayList<Integer> ctNumbers;

  public static void readTxtFile(String filePath) {
    File file = new File(filePath);
    content = new StringBuilder();
    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      String line;
      while ((line = reader.readLine()) != null) {
        content.append(line).append("\n");
      }
      txtFileContent = content.toString();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  public static void main(String[] args) throws IOException {
    getImageParameters("C:\\Users\\samue\\group06_alt\\ctConverter\\src\\main\\resources\\dataViewer1.ct");
     }

  public static void readBinFile(String filePath) throws FileNotFoundException {
    try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream(filePath))) {
      int numberOfShorts = dataInputStream.available() / 2;

      data = new short[numberOfShorts];

      for (int i = 0; i < numberOfShorts; i++) {
        data[i] = dataInputStream.readShort();
      }

    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }


  public static void readCtFile(String filePath) {
    File file = new File(filePath);
    content = new StringBuilder();
    ctNumbers = new ArrayList<>();
    int counter = 0;
    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      String line;
      while (counter < 10) {
        line = reader.readLine();
        content.append(line).append("\n");
        counter++;
      }
      txtFileContent = content.toString();
    } catch (IOException e) {
      e.printStackTrace();
    }
    if (counter == 10) {
      try {
        Scanner scanner = new Scanner(new File(filePath));
        for (int i = 0; i < 10; i++) {
          scanner.nextLine();
        }
        int i = 0;
        while (scanner.hasNextInt()) {
          ctNumbers.add(i, scanner.nextInt());
          i++;
        }

      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
    }



  }

  public static void writeTxtFile(String filePath) throws IOException {
    try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
      writer.print(txtFileContent);
    } catch (IOException e) {
      throw new IOException("file could not be written");
    }
  }

  public static void writeBinFile(String fileNameToCreate) {

    try (DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream
        (fileNameToCreate + ".bin"))) {
      for (int numbers : ctNumbers) {
        dataOutputStream.writeShort(numbers);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  public static void writeCtFile(String fileNameToCreate, String filePathTxt, String filePathBin)
      throws IOException {
    readTxtFile(filePathTxt);
    readBinFile(filePathBin);
    int numbersPerLine = 256;
    try (PrintWriter writer = new PrintWriter(new FileWriter(fileNameToCreate))) {
      writer.print(txtFileContent);
      for (int i = 0; i < data.length; i++) {
        writer.print(data[i]);
        if (i % numbersPerLine == numbersPerLine - 1 || i == data.length - 1) {
          writer.println();
        } else {
          writer.print(" ");
        }
      }


    }
  }

  public static int[] getImageParameters(String filepath) {
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
