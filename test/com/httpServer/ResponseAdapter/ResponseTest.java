package com.httpServer.ResponseAdapter;

import org.junit.Test;

import static org.junit.Assert.*;

public class ResponseTest {

    @Test
    public void defaultsStatusToNull() {
        Response responseWithoutStatus = new Response();

        assertNull(responseWithoutStatus.getStatus());
    }

    @Test
    public void setsStatus() {
        Response responseWithStatus = new Response();
        byte[] responseStatus = "123".getBytes();
        responseWithStatus.setStatus(responseStatus);

        assertArrayEquals("123".getBytes(), responseWithStatus.getStatus());
    }

    @Test
    public void defaultsHeaderToNull() {
        Response responseWithoutHeader = new Response();

        assertNull(responseWithoutHeader.getHeader());
    }

    @Test
    public void setsHeader() {
        Response responseWithHeader = new Response();
        byte[] responseHeader = "123".getBytes();
        responseWithHeader.setHeader(responseHeader);

        assertArrayEquals("123".getBytes(), responseWithHeader.getHeader());
    }

    @Test
    public void defaultsBodyToNull() {
        Response responseWithoutBody = new Response();

        assertNull(responseWithoutBody.getBody());
    }

    @Test
    public void setsBody() {
        Response responseWithBody = new Response();
        byte[] responseBody = "123".getBytes();
        responseWithBody.setBody(responseBody);

        assertArrayEquals("123".getBytes(), responseWithBody.getBody());
    }
}