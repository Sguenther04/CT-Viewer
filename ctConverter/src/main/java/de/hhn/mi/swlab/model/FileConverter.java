package de.hhn.mi.swlab.model;

import java.io.IOException;

public interface FileConverter {
  /**
   * Converts a ct file to a text and binary file
   *
   * @param filePathCt  filePath to where the file should be stored
   * @param filePathNewTxtFile filePath to where the file should be stored
   * @param filePathNewBinFile filePath to where the file should be stored
   * @throws IOException exception
   */
  void convertCtToTxtAndBin(String filePathCt, String filePathNewTxtFile, String filePathNewBinFile)
      throws IOException;

  /**
   * Converts a text and binary file to a ct file
   *
   * @param filePathTxt filePath to where the file should be stored
   * @param filePathBin filePath to where the file should be stored
   * @param filePathNewCtFile  filePath to where the file should be stored
   * @throws IOException exception.
   */
  void convertTxtAndBinToCt(String filePathTxt, String filePathBin, String filePathNewCtFile)
      throws IOException;
}
