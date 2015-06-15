package com.httpServer;

public class ResponseBody {

    public static byte[] noBody() {
        return "".getBytes();
    }

    public static byte[] parameters(String uri) {
        return ParameterDecoder.decode(uri).getBytes();
    }

    public static byte[] authorizationRequired() {
        return "Authentication required".getBytes();
    }
}
