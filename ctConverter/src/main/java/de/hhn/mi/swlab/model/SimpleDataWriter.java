package de.hhn.mi.swlab.model;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class SimpleDataWriter implements DataWriter {

  /**
   * Write patient dara to a text file
   *
   * @param filePath the path to the text file
   * @param patientData the data to be written into the text file
   * @throws IOException if the file could not be written
   */
  @Override
  public void writeTxtFile(String filePath, String patientData) throws IOException {
    try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
      writer.print(patientData);
    } catch (IOException e) {
      throw new IOException("file could not be written");
    }

  }

  /**
   * Writes image data to a binary file
   *
   * @param filePath the path to the binary file
   * @param imageData the data be written binary file
   * @throws IOException if the file could not be written
   */
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

  /**
   * Writes patient and image data to a text file
   *
   * @param filepath the path to the ct file
   * @param patientData the patient data to be written into the ct file
   * @param imageData the image data to be written to the text file
   * @param imageParameters an Array with image parameters
   * @throws IOException if the file could not be written
   */
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
