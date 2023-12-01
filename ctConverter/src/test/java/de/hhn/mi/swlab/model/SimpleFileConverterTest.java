package de.hhn.mi.swlab.model;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SimpleFileConverterTest {
  private SimpleFileConverter converter;

  private SimpleDataReader reader;
  private SimpleDataWriter writer;
  private String ctPath;
  private String txtPath;
  private String binPath;
  private String ctPathTest;
  private String txtPathTest;
  private String binPathTest;

  @BeforeEach
  void setUp() {
    converter = new SimpleFileConverter();
    reader = converter.getReader();
    writer = converter.getWriter();




    ctPath = "C:\\Users\\samue\\group06_alt\\ctConverter\\src\\main\\resources\\dataViewer1.ct";
    txtPath = "C:\\Users\\samue\\group06_alt\\ctConverter\\src\\main\\resources\\dataViewer2.txt";
    binPath = "C:\\Users\\samue\\group06_alt\\ctConverter\\src\\main\\resources\\dataViewer2.bin";
    ctPathTest = "C:\\Users\\samue\\group06_alt\\ctConverter\\src\\main\\resources\\CtFileTest";
    txtPathTest = "C:\\Users\\samue\\group06_alt\\ctConverter\\src\\main\\resources\\TxtFileTest";
    binPathTest =
        "C:\\Users\\samue\\group06_alt\\ctConverter\\src\\main\\resources\\BinFileTest.bin";
  }

  @Test
  void convertCtToTxtAndBinTest() throws IOException {
    converter.convertCtToTxtAndBin(ctPath,txtPathTest,binPathTest);
    short[]newShort = reader.readBinFile(binPathTest);
    short[]originalShort = reader.readBinFile(binPath);
    String newTxtContent = reader.readTxtFile(txtPathTest);
    String originalTxtContent = reader.readTxtFile(txtPath);
    assertEquals(newTxtContent,originalTxtContent);
    assertArrayEquals(newShort,originalShort);

  }

  @Test
  void convertTxtAndBinToCtTest() throws IOException {
    BufferedReader readerOriginal = new BufferedReader(new FileReader(ctPath));
    BufferedReader readerCopy = new BufferedReader(new FileReader(ctPathTest));
    String lineOriginal;
    String lineCopied;

    while((lineOriginal = readerOriginal.readLine()) != null ) {
      lineCopied = readerCopy.readLine();
      assertEquals(lineOriginal,lineCopied);
    }

  }

}