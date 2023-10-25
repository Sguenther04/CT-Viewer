package loggerAufgabe;
/**
 * a class that functions as a factory for creating console loggers
 */
public class ConsoleLoggerFactory implements Factory{
  @Override
  public Logger getLogger() {
    return  LoggerFactory.createLogger("console");
  }
}
