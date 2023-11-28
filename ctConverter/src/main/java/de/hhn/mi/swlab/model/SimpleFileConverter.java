package de.hhn.mi.swlab.model;

import java.io.IOException;
import java.util.ArrayList;

public class SimpleFileConverter implements FileConverter {
  private SimpleDataReader reader;
  private SimpleDataWriter writer;


  public SimpleFileConverter() {
    reader = new SimpleDataReader();
    writer = new SimpleDataWriter();
  }


  @Override
  public void convertCtToTxtAndBin(String filePathCt, String filePathNewTxtFile,
                                   String filePathNewBinFile)
      throws IOException {
    String textFileContent = reader.readTxtFileContentFromCtFile(filePathCt);
    ArrayList<Integer> binFileContent = reader.readBinFileContentFromCtFile(filePathCt);
    writer.writeTxtFile(filePathNewTxtFile, textFileContent);
    writer.writeBinFile(filePathNewBinFile, binFileContent);

  }

  @Override
  public void convertTxtAndBinToCt(String filePathTxt, String filePathBin,
                                   String filePathNewCtFile) throws IOException {
    String patientData = reader.readTxtFile(filePathTxt);
    short[] imageData = reader.readBinFile(filePathBin);
    writer.writeCtFile(filePathNewCtFile, patientData, imageData);

  }

  public SimpleDataReader getReader() {
    return reader;
  }

  public SimpleDataWriter getWriter() {
    return writer;
  }
}
