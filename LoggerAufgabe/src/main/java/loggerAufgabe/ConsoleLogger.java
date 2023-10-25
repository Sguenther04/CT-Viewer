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

  public String getDateTime() {
    formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    currentDateTime = LocalDateTime.now();
    return currentDateTime.format(formatter);
  }

  public String getCallLocation() {
    StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
    StackTraceElement caller = stackTraceElements[3];

    return caller.getClassName() + caller.getMethodName();
  }


  public String getFinalLog() {
    return finalLog;
  }

  public boolean isConsoleLogger() {
    return consoleLogger;
  }

  public boolean isFileLogger() {
    return fileLogger;
  }
}

