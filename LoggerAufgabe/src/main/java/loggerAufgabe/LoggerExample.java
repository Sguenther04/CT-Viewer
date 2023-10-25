package loggerAufgabe;
import java.io.File;
import java.io.IOException;

/**
 * main class which demonstrates the functionality of the logger
 */
public class LoggerExample {
  static Logger logger = LoggerFactory.createLogger("console");

  public static void main(String[] args) throws IOException {

    Example.exampleCall();
  }
}