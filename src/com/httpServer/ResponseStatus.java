package com.httpServer;

public class ResponseStatus {

    public static byte[] ok() {
        return "HTTP/1.1 200 OK\r\n".getBytes();
    }

    public static byte[] redirect() {
        return "HTTP/1.1 302 Found\r\n".getBytes();
    }

    public static byte[] unauthorized() {
        return "HTTP/1.1 401 Unauthorized\r\n".getBytes();
    }

    public static byte[] fourOhFour() {
        return "HTTP/1.1 404 Not Found\r\n".getBytes();
    }

    public static byte[] notAllowed() {
        return "HTTP/1.1 405 Method Not Allowed\r\n".getBytes();
    }
}
