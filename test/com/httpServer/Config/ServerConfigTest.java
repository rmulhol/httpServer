package com.httpServer.Config;

import org.junit.Test;

import static org.junit.Assert.*;

public class ServerConfigTest {

    @Test
    public void parsesPort() {
        String[] args = new String[2];
        args[0] = "-p";
        args[1] = "8000";
        assertEquals(8000, new ServerConfig(args).getPort());
    }

    @Test
    public void defaultsPortTo5000() {
        String[] args = new String[0];
        assertEquals(5000, new ServerConfig(args).getPort());
    }

    @Test
    public void parsesDirectory() {
        String[] args = new String[2];
        args[0] = "-d";
        args[1] = "/directory";
        assertEquals("/directory", new ServerConfig(args).getDirectory());
    }

    @Test
    public void defaultsDirectoryToPublic() {
        String[] args = new String[0];
        assertEquals("/public", new ServerConfig(args).getDirectory());
    }
}