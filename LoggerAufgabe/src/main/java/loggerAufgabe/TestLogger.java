package loggerAufgabe;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
  private ConsoleLoggerFactory consoleFactory;
  private FileLoggerFactory fileFactory;
  private String expectedLog;

  @BeforeTest
  public void setup() {
    logger = new ConsoleLogger();
    fileFactory = new FileLoggerFactory();
    consoleFactory = new ConsoleLoggerFactory();
    time = logger.getDateTime();
    log = LoggerCalls.ERROR;
    report = "Test";

  }

  @Test
  public void testConsoleLog() throws IOException {
    logger.loggerCall(report, log);
    expectedLog = time + " ERROR: loggerAufgabe.TestLoggertestConsoleLog: Test";

    Assert.assertEquals(expectedLog,logger.getFinalLog());
  }

  @Test
  public void testFileLoggerFactory() {
    logger = fileFactory.getLogger();
    Assert.assertTrue(logger.isFileLogger());
  }

  @Test
  public void testConsoleLoggerFactory() {
    logger = consoleFactory.getLogger();
    Assert.assertTrue(logger.isConsoleLogger());
  }
  @Test
  public void testFileLog() throws IOException {
    logger = new FileLogger();
    logger.loggerCall(report, log);
    expectedLog = time + " ERROR: loggerAufgabe.TestLoggertestFileLog: Test";

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


