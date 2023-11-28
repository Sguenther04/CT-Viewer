package de.hhn.mi.swlab.model;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public interface DataReader {
  /**
   * Reads the contents of a given text file and formats them in a certain format
   *
   * @param filepath to the file that should be read
   * @return txtFileContent contents read from the file
   * @throws FileNotFoundException exception
   */
  String readTxtFile(String filepath) throws FileNotFoundException;

  /**
   * Reads the contents of a given binary file
   * @param filepath to the file that should be read
   * @return data a short array with shorts read from the binary file
   * @throws FileNotFoundException exception
   */
  short[] readBinFile(String filepath) throws  FileNotFoundException;

  /**
   * Reads the text file contents of a given ct file
   * @param filepath to the file that should be read
   * @return txtFileContentCt that is read from the ct file
   * @throws FileNotFoundException exception
   */
  String readTxtFileContentFromCtFile(String filepath) throws FileNotFoundException;

  /**
   * Reads the imageData of a given ct file and stores them as shorts to write them in a binary file
   *
   * @param filepath to the file that should be read
   * @return imageData that is read from the ct file and stored in a short array
   * @throws FileNotFoundException exception
   */
ArrayList<Integer> readBinFileContentFromCtFile(String filepath) throws FileNotFoundException;
}
