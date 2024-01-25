package de.hhn.mi.swlab.model;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public interface DataReader {
  /**
   * Reads the contents of a given text file and formats them in a certain format
   *
   * @param filepath to the file that should be read
   * @return txtFileContent contents read from the file
   */
  String readTxtFile(String filepath);

  /**
   * Reads the contents of a given binary file
   *
   * @param filepath to the file that should be read
   * @return data a short array with shorts read from the binary file
   */
  short[] readBinFile(String filepath);

  /**
   * Reads the text file contents of a given ct file
   *
   * @param filepath to the file that should be read
   * @return txtFileContentCt that is read from the ct file
   */
  String readTxtFileContentFromCtFile(String filepath);

  /**
   * Reads the imageData of a given ct file and stores them as shorts to write them in a binary file
   *
   * @param filepath to the file that should be read
   * @return imageData that is read from the ct file and stored in a short array
   */
  ArrayList<Short> readBinFileContentFromCtFile(String filepath);

  /**
   * Extracts the image Parameters to use to write a ct File
   *
   * @param filepath to the file to extract the parameters from
   * @return imageParameters that indicate the x,y and z dimensions of a ct file
   */
  int[] getImageParameters(String filepath);

  String[] getPatientInfoFromCt(String filepath);

  short[]getImageData();

}
