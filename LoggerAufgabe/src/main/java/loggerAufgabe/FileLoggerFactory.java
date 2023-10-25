package loggerAufgabe;
/**
 * a class that functions as a factory for creating file loggers
 */
public class FileLoggerFactory implements Factory{
  @Override
  public Logger getLogger() {
    return LoggerFactory.createLogger("file");
  }
}
