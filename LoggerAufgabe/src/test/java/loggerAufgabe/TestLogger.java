package loggerAufgabe;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


/**
 * a class fpr testing the function of the loggers and their factories
 */
public class TestLogger {

  private Logger logger;
  private String report;
  private String time;
  private LoggerCalls log;

  private String expectedLog;

  @BeforeTest
  public void setup() {
    logger = new ConsoleLogger();
    time = logger.getDateTime();
    log = LoggerCalls.ERROR;
    report = "Test";

  }

  @Test
  public void testConsoleLog() throws IOException {
    logger.loggerCall(report, log);
    expectedLog = time + " ERROR: loggerAufgabe.TestLogger.testConsoleLog: Test";

    Assert.assertEquals(expectedLog,logger.getFinalLog());
  }

  @Test
  public void testLoggerCreation(){
    logger = LoggerFactory.createLogger("console");
    Assert.assertTrue(logger.isConsoleLogger());
    logger = LoggerFactory.createLogger("file");
    Assert.assertTrue(logger.isFileLogger());
  }


  @Test
  public void testFileLog() throws IOException {
    logger = new FileLogger();
    logger.loggerCall(report, log);
    expectedLog = time + " ERROR: loggerAufgabe.TestLogger.testFileLog: Test";

    StringBuilder content = new StringBuilder();
    try (BufferedReader reader = new BufferedReader(new FileReader("testorder.bin"))) {
      String line;
      while ((line = reader.readLine()) != null) {
        content.append(line).append("\n");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    Assert.assertEquals(expectedLog,content.toString().trim());
  }
}


