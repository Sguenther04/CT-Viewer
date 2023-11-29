package de.hhn.mi.swlab.model;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class SimpleDataWriter implements DataWriter {

  @Override
  public void writeTxtFile(String filePath, String patientData) throws IOException {
    try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
      writer.print(patientData);
    } catch (IOException e) {
      throw new IOException("file could not be written");
    }

  }

  @Override
  public void writeBinFile(String filePath, ArrayList<Integer> imageData) throws IOException {
    try (DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream
        (filePath + ".bin"))) {
      for (int numbers : imageData) {
        dataOutputStream.writeShort(numbers);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  @Override
  public void writeCtFile(String filepath, String patientData, short[] imageData,int[] imageParameters)
      throws IOException {
    int[]parameters = imageParameters;
    int numbersPerLine = parameters[0];
    int numbersPerColumn = parameters[1];
    int counter = 0;
    try (PrintWriter writer = new PrintWriter(new FileWriter(filepath))) {
      writer.print(filepath);
      for (int i = 0; i < imageData.length; i++) {
        if(counter % numbersPerColumn == 0 && counter != 0){
          writer.println();
          counter = 0;
        }
        writer.print(imageData[i]);
        if (i % numbersPerLine == numbersPerLine - 1 && i != 0 || i == imageData.length - 1) {
          writer.print(" ");
          writer.println();
          counter++;
        }  else {
          writer.print(" ");
        }

      }
      writer.println();
    }

  }
}
