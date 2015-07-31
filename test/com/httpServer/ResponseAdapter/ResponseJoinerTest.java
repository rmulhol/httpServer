package com.httpServer.ResponseAdapter;

import org.junit.Test;

import static org.junit.Assert.*;

public class ResponseJoinerTest {

    @Test
    public void responseJoinerExists() {
        assertNotNull(new ResponseJoiner());
    }

    @Test
    public void joinsStatusHeaderAndBody() {
        Response response = new Response();
        response.setStatus("200 OK\r\n".getBytes());
        response.setHeader("\r\n".getBytes());
        response.setBody("Hi".getBytes());

        assertArrayEquals("HTTP/1.1 200 OK\r\n\r\nHi".getBytes(), ResponseJoiner.join(response));
    }
}