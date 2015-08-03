package com.httpServer.Config;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;

public class LoggerConfig {

    public static void configureLogs() {
        try {
            FileHandler requestLogHandler = new FileHandler(System.getProperty("user.dir") + "/log/logs.txt");
            com.httpServer.Main.serverLogger.addHandler(requestLogHandler);
        } catch (IOException e) {
            com.httpServer.Main.serverLogger.log(Level.SEVERE, "Couldn't add handler to logger", e);
        }
    }
}
