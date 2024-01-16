package de.hhn.mi.swlab;


import static de.hhn.mi.swlab.LoggerExample.logger;

import java.io.IOException;

/**
 * interface for a class that shows the function of the logger
 */
public interface Example {
  /**
   * prints out an example call
   * @throws IOException if a print writer cannot be used
   */
  static void exampleCall() throws IOException
  {
    logger.loggerCall("Das ist ein Error",LoggerCalls.ERROR);
  }
}
