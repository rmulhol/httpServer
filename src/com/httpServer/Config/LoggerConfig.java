package com.httpServer.Config;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerConfig {

    public static void configureLogs(Logger logger) {
        try {
            FileHandler requestLogHandler = new FileHandler(System.getProperty("user.dir") + "/log/logs.txt");
            logger.addHandler(requestLogHandler);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Couldn't add handler to logger", e);
        }
    }
}
