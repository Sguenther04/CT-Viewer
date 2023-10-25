package loggerAufgabe;

/**
 * a class that functions as a factory for creating different types of loggers
 */
public class LoggerFactory{
  private static ConsoleLogger logger;
  private static FileLogger logger2;
  public static Logger createLogger(String loggertype) {
    if(loggertype.equalsIgnoreCase("console")) {
      logger = new ConsoleLogger();
      return logger;
    }
    if(loggertype.equalsIgnoreCase("file")){
      logger2 = new FileLogger();
      return logger2;
    }
    return null;
  }
}
