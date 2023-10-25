package loggerAufgabe;

/**
 * enum for different types of logger calls
 */
public enum LoggerCalls {
  WARNING("warning"),ERROR("error"),INFO("info");
  private String name;
  LoggerCalls(String name) {
    this.name = name;
  }
  public String getName() {
    return name;
  }
}
