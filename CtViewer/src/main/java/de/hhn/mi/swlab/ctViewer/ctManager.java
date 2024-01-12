package de.hhn.mi.swlab.ctViewer;

import de.hhn.mi.swlab.model.SimpleDataReader;
import de.hhn.mi.swlab.model.SimpleDataWriter;
import de.hhn.mi.swlab.model.SimpleFileConverter;

public class ctManager {
  private SimpleFileConverter converter;
  private SimpleDataReader reader;
  private SimpleDataWriter writer;
  private int[] ctParameters;
  private short[][][] ctData;
  private String patientData;
  private String[] patientInfo;


  public ctManager() {
    this.converter = new SimpleFileConverter();
    reader = converter.getReader();
    writer = converter.getWriter();
  }

  public short[][][] getCtDataFromFile(String filepath) {
    ctData = reader.generate3DimensionalCtData(filepath);
    return ctData;
  }
  public short[][][] getCtData() {
    return ctData;
  }

  public String getPatientDataFromFile(String filepath) {
    patientData =  reader.readTxtFileContentFromCtFile(filepath);
    return patientData;
  }
  public String getPatientData() {
    return patientData;
  }
  public int[] getCtParametersFromFile(String filepath) {
     ctParameters = reader.getImageParameters(filepath);
     return ctParameters;
  }

  public String[] getPatientInfoFromFile(String filepath) {
    patientInfo = reader.getPatientInfoFromCt(filepath);
    return patientInfo;
  }

  public String[] getPatientInfo() {
    return patientInfo;
  }

  public SimpleDataWriter getWriter() {
    return writer;
  }

  public int[] getCtParameters() {
    return ctParameters;
  }

}
