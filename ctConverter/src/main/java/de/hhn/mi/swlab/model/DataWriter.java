package de.hhn.mi.swlab.model;

import java.io.IOException;
import java.util.ArrayList;

public interface DataWriter {
  /**
   * Writes the data that was read and saved before into a text file
   *
   * @param filePath    where the file should be saved
   * @param patientData that is written in the txt file
   */
  void writeTxtFile(String filePath, String patientData);

  /**
   * Writes data that was read and saved before into a binary file
   *
   * @param filePath  where the file should be saved
   * @param imageData that is written in the binary file
   */
  void writeBinFile(String filePath, ArrayList<Integer> imageData);

  /**
   * Writes data that was read and saved before into a ct file
   *
   * @param filepath    where the file should be stored
   * @param patientData that is written in the ct file
   * @param imageData   that is written in the ct file
   */
  void writeCtFile(String filepath, String patientData, short[] imageData, int[] imageParameters);

}
