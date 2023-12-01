package de.hhn.mi.swlab.model;


import de.hhn.mi.swlab.ConsoleLogger;
import de.hhn.mi.swlab.LoggerCalls;
import de.hhn.mi.swlab.LoggerFactory;
import java.util.ArrayList;

public class SimpleFileConverter implements FileConverter {
  private SimpleDataReader reader;
  private SimpleDataWriter writer;
  private ConsoleLogger logger = (ConsoleLogger) LoggerFactory.createLogger("console");

  /**
   * constructor for SimpleFileConverter
   * Initializes SimpleDataReader and SimpleDataWriter
   */
  public SimpleFileConverter() {
    reader = new SimpleDataReader();
    writer = new SimpleDataWriter();
    logger.loggerCall("SimpleFileConverter was created", LoggerCalls.INFO);
  }

  /**
   * Converts the ct file to separate text and binary files
   *
   * @param filePathCt         path to the ct file
   * @param filePathNewTxtFile path to the new text file
   * @param filePathNewBinFile path to the new binary file
   */
  @Override
  public void convertCtToTxtAndBin(String filePathCt, String filePathNewTxtFile,
                                   String filePathNewBinFile) {
    String textFileContent = reader.readTxtFileContentFromCtFile(filePathCt);
    ArrayList<Integer> binFileContent = reader.readBinFileContentFromCtFile(filePathCt);
    writer.writeTxtFile(filePathNewTxtFile, textFileContent);
    writer.writeBinFile(filePathNewBinFile, binFileContent);
    logger.loggerCall("Ct file was converted to a text and binary file", LoggerCalls.INFO);
  }

  /**
   * Converts separate text and binary files to a ct file
   *
   * @param filePathTxt       path to the text file
   * @param filePathBin       path to the binary file
   * @param filePathNewCtFile path to the new ct file
   */
  @Override
  public void convertTxtAndBinToCt(String filePathTxt, String filePathBin,
                                   String filePathNewCtFile) {
    String patientData = reader.readTxtFile(filePathTxt);
    short[] imageData = reader.readBinFile(filePathBin);
    int[] parameters = reader.getImageParameters(filePathTxt);
    writer.writeCtFile(filePathNewCtFile, patientData, imageData, parameters);
    logger.loggerCall("Text and binary file were converted to a ct file", LoggerCalls.INFO);
  }

  /**
   * Returns the SimpleDataReader instance
   *
   * @return SimpleDataReader instance
   */
  @Override
  public SimpleDataReader getReader() {
    return reader;
  }

  /**
   * Returns SimpleDataWriter instance
   *
   * @return SimpleDataWriter instance
   */
  @Override
  public SimpleDataWriter getWriter() {
    return writer;
  }
}
