package com.httpServer.ResponseAdapter;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class ResponseJoinerTest {

    @Test
    public void joinsStatusHeaderAndBody() {
        HashMap<String, byte[]> response = new HashMap<String, byte[]>();
        response.put("status", "200 OK\r\n".getBytes());
        response.put("header", "\r\n".getBytes());
        response.put("body", "".getBytes());

        assertArrayEquals("HTTP/1.1 200 OK\r\n\r\n".getBytes(), ResponseJoiner.join(response));
    }

}