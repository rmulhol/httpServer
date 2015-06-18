package com.httpServer.Handler.ResponseContents;

public class ResponseStatus {

    public static byte[] ok() {
        return "200 OK\r\n".getBytes();
    }

    public static byte[] patchContent() {
        return "204 No Content\r\n".getBytes();
    }

    public static byte[] partialContent() {
        return "206 Partial Content\r\n".getBytes();
    }

    public static byte[] redirect() {
        return "302 Found\r\n".getBytes();
    }

    public static byte[] unauthorized() {
        return "401 Unauthorized\r\n".getBytes();
    }

    public static byte[] fourOhFour() {
        return "404 Not Found\r\n".getBytes();
    }

    public static byte[] notAllowed() {
        return "405 Method Not Allowed\r\n".getBytes();
    }
}
