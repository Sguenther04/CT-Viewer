package loggerAufgabe;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * a logger that prints out its calls on the console
 */
public class ConsoleLogger implements Logger {
  private LoggerCalls log;
  private LocalDateTime currentDateTime;
  private DateTimeFormatter formatter;
  private final boolean consoleLogger = true;
  private final boolean fileLogger = false;

  private String finalLog;

  /**
   * Prints out a logger call on the console
   * @param report   is the message which the logger prints out
   * @param callType type of the log
   */
  public void loggerCall(String report, LoggerCalls callType) {

    String callLocation = getCallLocation();
    String time = getDateTime();
    finalLog = time + " " + callType + ": " + callLocation + ": " + report;

    switch (callType) {
      case INFO:
        System.out.println(finalLog);
        break;
      case ERROR:
        System.out.println(finalLog);
        break;
      case WARNING:
        System.out.println(finalLog);
        break;
    }


  }

  /**
   * returns current date and time for the log
   * @return String with current date and time
   */
  public String getDateTime() {
    formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    currentDateTime = LocalDateTime.now();
    return currentDateTime.format(formatter);
  }

  /**
   * returns the call location which shows in which class and method the log was called
   * @return String with a call location
   */
  public String getCallLocation() {
    StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
    StackTraceElement caller = stackTraceElements[3];

    return caller.getClassName() + "." + caller.getMethodName();
  }

  /**
   * returns the complete Log
   * @return String which is the complete logger call
   */
  public String getFinalLog() {
    return finalLog;
  }

  /**
   * returns true if the logger is a console logger
   * @return boolean which shows whether the logger is a console logger or not
   */
  public boolean isConsoleLogger() {
    return consoleLogger;
  }

  /**
   * returns true if the logger is a file logger
   * @return boolean which shows whether the logger is a file logger or not
   */
  public boolean isFileLogger() {
    return fileLogger;
  }
}

