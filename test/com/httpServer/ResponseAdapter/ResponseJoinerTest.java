package com.httpServer.ResponseAdapter;

import org.junit.Test;

import static org.junit.Assert.*;

public class ResponseJoinerTest {

    @Test
    public void joinsStatusHeaderAndBody() {
        Response response = new Response();
        response.setStatus("200 OK\r\n".getBytes());
        response.setHeader("\r\n".getBytes());
        response.setBody("".getBytes());

        assertArrayEquals("HTTP/1.1 200 OK\r\n\r\n".getBytes(), ResponseJoiner.join(response));
    }
}