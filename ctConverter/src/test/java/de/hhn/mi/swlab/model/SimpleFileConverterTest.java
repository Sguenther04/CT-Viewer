package de.hhn.mi.swlab.model;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
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
  private String testPath;

  @BeforeEach
  void setUp() {
    converter = new SimpleFileConverter();
    reader = converter.getReader();
    writer = converter.getWriter();
    Path filePath = Paths.get("src", "main", "resources", "dataViewer1.ct");
    Path absPath = filePath.toAbsolutePath();
    ctPath = absPath.toString();
    filePath = Paths.get("src", "main", "resources", "dataViewer2.txt");
    absPath = filePath.toAbsolutePath();
    txtPath = absPath.toString();
    filePath = Paths.get("src", "main", "resources", "dataViewer2.bin");
    absPath = filePath.toAbsolutePath();
    binPath = absPath.toString();
    filePath = Paths.get("src", "test", "resources", "CtFileTest");
    absPath = filePath.toAbsolutePath();
    ctPathTest = absPath.toString();
    filePath = Paths.get("src", "test", "resources", "TxtFileTest");
    absPath = filePath.toAbsolutePath();
    txtPathTest = absPath.toString();
    filePath = Paths.get("src", "test", "resources", "BinFileTest.bin");
    absPath = filePath.toAbsolutePath();
    binPathTest = absPath.toString();
    filePath = Paths.get("src", "test", "resources", "test3D");
    absPath = filePath.toAbsolutePath();
    testPath = absPath.toString();
  }

  @Test
  void convertCtToTxtAndBinTest() throws IOException {
    converter.convertCtToTxtAndBin(ctPath, txtPathTest, binPathTest);
    short[] newShort = reader.readBinFile(binPathTest);
    short[] originalShort = reader.readBinFile(binPath);
    String newTxtContent = reader.readTxtFile(txtPathTest);
    String originalTxtContent = reader.readTxtFile(txtPath);
    assertEquals(newTxtContent, originalTxtContent);
    assertArrayEquals(newShort, originalShort);

  }

  @Test
  void convertTxtAndBinToCtTest() throws IOException {
    BufferedReader readerOriginal = new BufferedReader(new FileReader(ctPath));
    BufferedReader readerCopy = new BufferedReader(new FileReader(ctPathTest));
    String lineOriginal;
    String lineCopied;

    while ((lineOriginal = readerOriginal.readLine()) != null) {
      lineCopied = readerCopy.readLine();
      assertEquals(lineOriginal, lineCopied);
    }
  }
    @Test
        void test(){
      String line = reader.readTxtFileContentFromCtFile(ctPath);
      System.out.println(line);
      String lines[] = line.split("\n");
      System.out.println(Arrays.toString(lines));






    }

  }

