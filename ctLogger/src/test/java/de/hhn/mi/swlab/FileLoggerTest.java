package de.hhn.mi.swlab;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import org.junit.jupiter.api.Test;

class FileLoggerTest {
  private Logger logger;
  private String report;
  private String time;
  private LoggerCalls log;

  private String expectedLog;

  @Test
  void loggerCall() {
  }

  @Test
  void getDateTime() {
  }

  @Test
  void getCallLocation() throws IOException {
    logger.loggerCall(report, log);
    expectedLog = time + " ERROR: loggerAufgabe.TestLogger.testConsoleLog: Test";

    assertEquals(expectedLog,logger.getFinalLog());
  }

  @Test
  void getFinalLog() {
  }

  @Test
  void isFileLogger() {
  }

  @Test
  void isConsoleLogger() {
  }

  @Test
  void writeLog() {
  }
}