package de.hhn.mi.swlab.model;

import java.io.IOException;
import java.util.ArrayList;

public class SimpleFileConverter implements FileConverter {
  private SimpleDataReader reader;
  private SimpleDataWriter writer;

  /**
   * constructor for SimpleFileConverter
   * Initializes SimpleDataReader and SimpleDataWriter
   */
  public SimpleFileConverter() {
    reader = new SimpleDataReader();
    writer = new SimpleDataWriter();
  }

  /**
   * Converts the ct file to separate text and binary files
   *
   * @param filePathCt  path to the ct file
   * @param filePathNewTxtFile path to the new text file
   * @param filePathNewBinFile path to the new binary file
   * * @throws IOException if and IO exception occurs during the conversion
   */
  @Override
  public void convertCtToTxtAndBin(String filePathCt, String filePathNewTxtFile,
                                   String filePathNewBinFile)
      throws IOException {
    String textFileContent = reader.readTxtFileContentFromCtFile(filePathCt);
    ArrayList<Integer> binFileContent = reader.readBinFileContentFromCtFile(filePathCt);
    writer.writeTxtFile(filePathNewTxtFile, textFileContent);
    writer.writeBinFile(filePathNewBinFile, binFileContent);

  }

  /**
   * Converts separate text and binary files to a ct file
   *
   * @param filePathTxt path to the text file
   * @param filePathBin path to the binary file
   * @param filePathNewCtFile  path to the new ct file
   * @throws IOException if an IO exception occurs during the conversion
   */
  @Override
  public void convertTxtAndBinToCt(String filePathTxt, String filePathBin,
                                   String filePathNewCtFile) throws IOException {
    String patientData = reader.readTxtFile(filePathTxt);
    short[] imageData = reader.readBinFile(filePathBin);
    int[]parameters = reader.getImageParameters(filePathTxt);
    writer.writeCtFile(filePathNewCtFile,patientData,imageData,parameters);

  }

  /**
   * Returns the SimpleDataReader instance
   *
   * @return SimpleDataReader instance
   */
  public SimpleDataReader getReader() {
    return reader;
  }

  /**
   * Returns SimpleDataWriter instance
   *
   * @return SimpleDataWriter instance
   */
  public SimpleDataWriter getWriter() {
    return writer;
  }
}
