package com.httpServer.ResponseAdapter;

public class Response {

    private byte[] status;
    private byte[] header;
    private byte[] body;

    public byte[] getStatus() {
        return this.status;
    }

    public void setStatus(byte[] status) {
        this.status = status;
    }

    public byte[] getHeader() {
        return this.header;
    }

    public void setHeader(byte[] header) {
        this.header = header;
    }

    public byte[] getBody() {
        return this.body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }
}
