package loggerAufgabe;

/**
 * interface for a factory that creates loggers
 */
public interface Factory {
  /**
   * returns a logger that can be used to give out  different types of logger calls
   * @return a logger
   */
  Logger getLogger();
}
