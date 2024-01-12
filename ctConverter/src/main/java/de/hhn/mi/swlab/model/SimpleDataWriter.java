package de.hhn.mi.swlab.model;

import de.hhn.mi.swlab.ConsoleLogger;
import de.hhn.mi.swlab.LoggerCalls;
import de.hhn.mi.swlab.LoggerFactory;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class SimpleDataWriter implements DataWriter {
  private ConsoleLogger logger = (ConsoleLogger) LoggerFactory.createLogger("console");

  /**
   * Write patient dara to a text file
   *
   * @param filePath    the path to the text file
   * @param patientData the data to be written into the text file
   */
  @Override
  public void writeTxtFile(String filePath, String patientData) {
    try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
      writer.print(patientData);
    } catch (IOException e) {
      e.printStackTrace();
    }
    logger.loggerCall("Text file was written", LoggerCalls.INFO);

  }

  /**
   * Writes image data to a binary file
   *
   * @param filePath  the path to the binary file
   * @param imageData the data be written binary file
   */
  @Override
  public void writeBinFile(String filePath, ArrayList<Short> imageData) {
    try (DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream
        (filePath))) {
      for (short numbers : imageData) {
        dataOutputStream.writeShort(numbers);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    logger.loggerCall("Binary file was written", LoggerCalls.INFO);

  }

  /**
   * Writes patient and image data to a text file
   *
   * @param filepath        the path to the ct file
   * @param patientData     the patient data to be written into the ct file
   * @param imageData       the image data to be written to the text file
   * @param imageParameters an Array with image parameters
   */
  @Override
  public void writeCtFile(String filepath, String patientData, short[] imageData,
                          int[] imageParameters) {
    int[] parameters = imageParameters;
    int numbersPerLine = parameters[0];
    int numbersPerColumn = parameters[1];
    int counter = 0;
    try (PrintWriter writer = new PrintWriter(new FileWriter(filepath))) {
      writer.print(filepath);
      for (int i = 0; i < imageData.length; i++) {
        if (counter % numbersPerColumn == 0 && counter != 0) {
          writer.println();
          counter = 0;
        }
        writer.print(imageData[i]);
        if (i % numbersPerLine == numbersPerLine - 1 && i != 0 || i == imageData.length - 1) {
          writer.print(" ");
          writer.println();
          counter++;
        } else {
          writer.print(" ");
        }

      }
      writer.println();
    } catch (IOException e) {
      e.printStackTrace();
    }
    logger.loggerCall("Ct file was written", LoggerCalls.INFO);
  }

}
