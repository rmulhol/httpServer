package com.httpServer.RequestAdapter;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.junit.Assert.*;

public class RequestReaderTest {

    @Test
    public void requestReaderExists() {
        assertNotNull(new RequestReader());
    }

    @Test
    public void readsRequestFromStream() {
        InputStream mockInput = new ByteArrayInputStream("GET / HTTP/1.1\r\n\r\nHi".getBytes());
        BufferedReader mockReader = new BufferedReader(new InputStreamReader(mockInput));

        assertEquals("GET / HTTP/1.1\r\n\r\nHi", RequestReader.read(mockReader));
    }

}