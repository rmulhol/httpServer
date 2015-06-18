package com.httpServer.RequestAdapter;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.junit.Assert.*;

public class RequestReaderTest {

    @Test
    public void readsRequestFromStream() {
        InputStream mockInput = new ByteArrayInputStream("GET / HTTP/1.1\r\n\r\n".getBytes());
        BufferedReader mockReader = new BufferedReader(new InputStreamReader(mockInput));

        assertEquals(RequestReader.read(mockReader), "GET / HTTP/1.1\r\n\r\n");
    }

}