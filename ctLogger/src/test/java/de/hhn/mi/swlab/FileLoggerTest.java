package de.hhn.mi.swlab;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FileLoggerTest {
  private ConsoleLogger loggerConsole;
  private FileLogger loggerFile;
  private String report;
  private String time;
  private LoggerCalls log;

  private String expectedLog;


  @BeforeEach
  void setUp() {
    loggerConsole = (ConsoleLogger) LoggerFactory.createLogger("console");
    loggerFile = (FileLogger) LoggerFactory.createLogger("file");
  }

  @Test
  void testGetFinalLog() {
    String expectedLog = loggerConsole.getDateTime() + " ERROR: de.hhn.mi.swlab.FileLoggerTest.testGetFinalLog: test";
    loggerConsole.loggerCall("test","",LoggerCalls.ERROR);
    String actualLog =  loggerConsole.getFinalLog();
    assertEquals(expectedLog,actualLog);

  }

  @Test
  void isFileLogger() {
    assertTrue(loggerFile.isFileLogger());
  }

  @Test
  void isConsoleLogger() {
    assertTrue(loggerConsole.isConsoleLogger());
  }

  @Test
  void testWriteLog() throws IOException {
    String testLog = "";
    Path filePath = Paths.get("src", "test", "resources", "TestLog");
    Path absPath = filePath.toAbsolutePath();
    String logPath = absPath.toString();
    loggerFile.writeLog("test", logPath);
    try (BufferedReader reader = new BufferedReader(new FileReader(logPath))) {
      testLog = reader.readLine();
    } catch (IOException e) {
      e.printStackTrace();
    }
    assertEquals(testLog, "test");
  }
}