package com.httpServer;

public class ResponseHeader {

    public static byte[] location() {
        return "Location: http://localhost:5000/\r\n".getBytes();
    }

    public static byte[] methodOptions() {
        return "Allow: GET,HEAD,POST,OPTIONS,PUT\r\n".getBytes();
    }

    public static byte[] noHeader() {
        return "\r\n".getBytes();
    }


}
