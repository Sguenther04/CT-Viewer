package de.hhn.mi.swlab.model;

import java.io.IOException;

public interface FileConverter {
  /**
   * Converts a ct file to a text and binary file
   *
   * @param filePathCt         filePath to where the file should be stored
   * @param filePathNewTxtFile filePath to where the file should be stored
   * @param filePathNewBinFile filePath to where the file should be stored
   */
  void convertCtToTxtAndBin(String filePathCt, String filePathNewTxtFile,
                            String filePathNewBinFile);

  /**
   * Converts a text and binary file to a ct file
   *
   * @param filePathTxt       filePath to where the file should be stored
   * @param filePathBin       filePath to where the file should be stored
   * @param filePathNewCtFile filePath to where the file should be stored
   */
  void convertTxtAndBinToCt(String filePathTxt, String filePathBin, String filePathNewCtFile);

  /**
   * Returns the writer
   *
   * @return writer that writes the converted version of the files
   */
  SimpleDataWriter getWriter();

  /**
   * Returns the reader
   *
   * @return reader that reads the different contents of the files
   */
  SimpleDataReader getReader();
}
