package com.httpServer.ResponseAdapter;

import java.util.HashMap;

public class ResponseJoiner {

    public static byte[] join(HashMap<String, byte[]> responseMap) {
        byte[] httpProtocol = "HTTP/1.1 ".getBytes();

        byte[] status = responseMap.get("status");
        byte[] header = responseMap.get("header");
        byte[] body = responseMap.get("body");

        int protocolLength = httpProtocol.length;
        int statusLength = status.length;
        int headerLength = header.length;
        int bodyLength = body.length;

        int responseLength = protocolLength + statusLength + headerLength + bodyLength;
        byte[] response = new byte[responseLength];

        for (int i = 0; i < responseLength; i++) {
            if (i < protocolLength) {
                response[i] = httpProtocol[i];
            } else if (i < protocolLength + statusLength) {
                response[i] = status[i - protocolLength];
            } else if (i < protocolLength + statusLength + headerLength) {
                response[i] = header[i - statusLength - protocolLength];
            } else {
                response[i] = body[i - headerLength - statusLength - protocolLength];
            }
        }

        return response;
    }


}
