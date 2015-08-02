package com.httpServer.Config;

public class ServerConfig {

    private final int port;
    private final String directory;

    public ServerConfig(String[] args) {
        port = parsePort(args);
        directory = parseDirectory(args);
    }

    public int getPort() {
        return port;
    }

    public String getDirectory() {
        return directory;
    }

    private int parsePort(String[] args) {
        int port = 5000;
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-p")) {
                port = Integer.parseInt(args[++i]);
            }
        }
        return port;
    }

    private String parseDirectory(String[] args) {
        String directory = "/public";
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-d")) {
                directory = args[++i];
            }
        }
        return directory;
    }
}
