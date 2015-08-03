package com.httpServer;

import com.httpServer.Config.LoggerConfig;
import com.httpServer.Config.RouteConfig;
import com.httpServer.Config.ServerConfig;
import com.httpServer.Runner.ServerRunner;

import java.io.IOException;
import java.util.logging.Logger;

public class Main {

    public static final Logger serverLogger =
            Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws IOException {
        LoggerConfig.configureLogs();
        ServerConfig.configureServer(args);

        RouteConfig.setupRoutes();

        new ServerRunner().run();
    }
}
