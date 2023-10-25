package loggerAufgabe;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * a logger that writes its calls in a file
 */
public class FileLogger implements Logger{
  private LoggerCalls log;
  private LocalDateTime currentDateTime;
  private DateTimeFormatter formatter;
  private final boolean consoleLogger = false;
  private final boolean fileLogger = true;

  private String finalLog;
  @Override
  public void loggerCall(String report, LoggerCalls callType) throws IOException {
    String callLocation = getCallLocation();
    String time = getDateTime();
    finalLog = time + " " + callType + ": " +callLocation + ": " + report;
    writeLog(finalLog);
  }

  @Override
  public String getDateTime() {
    formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    currentDateTime = LocalDateTime.now();
    return currentDateTime.format(formatter);
  }

  @Override
  public String getCallLocation() {
    StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
    StackTraceElement caller = stackTraceElements[3];

    return caller.getClassName() + caller.getMethodName();
  }
  public String getFinalLog() {
    return finalLog;
  }

  public boolean isFileLogger() {
    return fileLogger;
  }
  public boolean isConsoleLogger() {
    return consoleLogger;
  }

  /**
   * writes the log in a file
   * @param log the string that has to be written in the file
   * @throws IOException if a print writer cannot be used
   */
  public void writeLog(String log) throws IOException {

    try (PrintWriter writer = new PrintWriter(new FileWriter("testorder.bin"))) {
      writer.println(log);
    } catch (IOException e) {
      throw new IOException("file could not be written");
    }
  }
}