package de.hhn.mi.swlab;

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
  /**
   * Creates a logger call
   * @param report   is the message which the logger prints out
   * @param callType type of the log
   */
  @Override
  public void loggerCall(String report, LoggerCalls callType) throws IOException {
    String callLocation = getCallLocation();
    String time = getDateTime();
    finalLog = time + " " + callType + ": " +callLocation + ": " + report;
    writeLog(finalLog);
  }
  /**
   * Returns current date and time for the log
   * @return String with current date and time
   */
  @Override
  public String getDateTime() {
    formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    currentDateTime = LocalDateTime.now();
    return currentDateTime.format(formatter);
  }
  /**
   * Returns the call location which shows in which class and method the log was called
   * @return String with a call location
   */
  @Override
  public String getCallLocation() {
    StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
    StackTraceElement caller = stackTraceElements[3];

    return caller.getClassName()+ "." + caller.getMethodName();
  }
  /**
   * Returns the complete Log
   * @return String which is the complete logger call
   */
  public String getFinalLog() {
    return finalLog;
  }
  /**
   * Returns true if the logger is a file logger
   * @return boolean which shows whether the logger is a file logger or not
   */
  public boolean isFileLogger() {
    return fileLogger;
  }
  /**
   * Returns true if the logger is a console logger
   * @return boolean which shows whether the logger is a console logger or not
   */
  public boolean isConsoleLogger() {
    return consoleLogger;
  }

  /**
   * Writes the log in a file
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