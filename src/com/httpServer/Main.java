package com.httpServer;

import com.httpServer.Config.RouteConfig;
import com.httpServer.Config.ServerConfig;

import java.io.IOException;
import java.util.logging.Logger;

class Main {

    static final Logger serverLogger =
            Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws IOException {
        ServerConfig config = new ServerConfig(args);
        RouteConfig.setupRoutes();
        new ServerRunner().run(serverLogger, config);
    }
}
