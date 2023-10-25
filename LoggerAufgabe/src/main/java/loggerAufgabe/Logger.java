package loggerAufgabe;
import java.io.IOException;

/**
 * interface for a logger
 */
public interface Logger {
  /**
   * prints out a log with different types
   *
   * @param report   is the message which the logger prints out
   * @param callType type of the log
   * @throws IOException exception
   */
  void loggerCall(String report, LoggerCalls callType) throws IOException;

  /**
   *returns the time of the moment the method was called
   * @return date and time of the moment the method was called
   */
  String getDateTime();

  /**
   * returns the location of the method call
   * @return location from which the method was called
   */

  String getCallLocation();

  /**
   * returns the complete String of the logger call
   * @return complete String which the logger builds
   */

  String getFinalLog();

  /**
   * returns a boolean that indicates the type of the logger
   * @return boolean true if the logger is a file logger
   */

  boolean isFileLogger();
  /**
   * returns a boolean that indicates the type of the logger
   * @return boolean true if the logger is a console logger
   */

  boolean isConsoleLogger();

}