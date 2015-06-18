package com.httpServer;

import org.junit.Test;

import static org.junit.Assert.*;

public class ResponseStatusTest {

    @Test
    public void okReturns200() {
        assertArrayEquals("HTTP/1.1 200 OK\r\n".getBytes(), ResponseStatus.ok());
    }

    @Test
    public void partialContentReturns206() {
        assertArrayEquals("HTTP/1.1 206 Partial Content\r\n".getBytes(), ResponseStatus.partialContent());
    }

    @Test
    public void redirectReturns302() {
        assertArrayEquals("HTTP/1.1 302 Found\r\n".getBytes(), ResponseStatus.redirect());
    }

    @Test
    public void unauthorizedReturns401() {
        assertArrayEquals("HTTP/1.1 401 Unauthorized\r\n".getBytes(), ResponseStatus.unauthorized());
    }

    @Test
    public void fourOhFourReturns404() {
        assertArrayEquals("HTTP/1.1 404 Not Found\r\n".getBytes(), ResponseStatus.fourOhFour());
    }

    @Test
    public void notAllowedReturns405() {
        assertArrayEquals("HTTP/1.1 405 Method Not Allowed\r\n".getBytes(), ResponseStatus.notAllowed());
    }
}