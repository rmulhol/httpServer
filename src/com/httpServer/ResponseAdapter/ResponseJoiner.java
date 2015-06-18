package com.httpServer.ResponseAdapter;

import java.util.HashMap;

public class ResponseJoiner {

    public static byte[] join(HashMap<String, byte[]> responseMap) {
        byte[] status = responseMap.get("status");
        byte[] header = responseMap.get("header");
        byte[] body = responseMap.get("body");

        int statusLength = status.length;
        int headerLength = header.length;
        int bodyLength = body.length;

        int responseLength = statusLength + headerLength + bodyLength;
        byte[] response = new byte[responseLength];

        for (int i = 0; i < responseLength; i++) {
            if (i < statusLength) {
                response[i] = status[i];
            } else if (i < statusLength + headerLength) {
                response[i] = header[i - statusLength];
            } else {
                response[i] = body[i - statusLength - headerLength];
            }
        }

        return response;
    }


}
