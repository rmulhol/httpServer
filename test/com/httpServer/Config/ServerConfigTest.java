package com.httpServer.Config;

import org.junit.Test;

import static org.junit.Assert.*;

public class ServerConfigTest {

    @Test
    public void parsesPort() {
        String[] args = new String[2];
        args[0] = "-p";
        args[1] = "8000";
        ServerConfig.configureServer(args);
        assertEquals(8000, ServerConfig.getPort());
    }

    @Test
    public void defaultsPortTo5000() {
        String[] args = new String[0];
        ServerConfig.configureServer(args);
        assertEquals(5000, ServerConfig.getPort());
    }

    @Test
    public void parsesDirectory() {
        String[] args = new String[2];
        args[0] = "-d";
        args[1] = "/directory";
        ServerConfig.configureServer(args);
        assertEquals("/directory", ServerConfig.getDirectory());
    }

    @Test
    public void defaultsDirectoryToPublic() {
        String[] args = new String[0];
        ServerConfig.configureServer(args);
        assertEquals("/public", ServerConfig.getDirectory());
    }
}