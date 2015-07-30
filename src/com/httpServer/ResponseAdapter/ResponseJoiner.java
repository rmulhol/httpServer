package com.httpServer.ResponseAdapter;

public class ResponseJoiner {

    public static byte[] join(Response response) {
        byte[] httpProtocol = "HTTP/1.1 ".getBytes();

        byte[] status = response.getStatus();
        byte[] header = response.getHeader();
        byte[] body = response.getBody();

        int protocolLength = httpProtocol.length;
        int statusLength = status.length;
        int headerLength = header.length;
        int bodyLength = body.length;

        int responseLength = protocolLength + statusLength + headerLength + bodyLength;
        byte[] output = new byte[responseLength];

        for (int i = 0; i < responseLength; i++) {
            if (i < protocolLength) {
                output[i] = httpProtocol[i];
            } else if (i < protocolLength + statusLength) {
                output[i] = status[i - protocolLength];
            } else if (i < protocolLength + statusLength + headerLength) {
                output[i] = header[i - statusLength - protocolLength];
            } else {
                output[i] = body[i - headerLength - statusLength - protocolLength];
            }
        }

        return output;
    }


}
