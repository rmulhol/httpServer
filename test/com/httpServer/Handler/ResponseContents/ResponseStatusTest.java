package com.httpServer.Handler.ResponseContents;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class ResponseStatusTest {

    @Test
    public void okReturns200() {
        assertArrayEquals("200 OK\r\n".getBytes(), ResponseStatus.ok());
    }

    @Test
    public void noContentReturns204() {
        assertArrayEquals("204 No Content\r\n".getBytes(), ResponseStatus.noContent());
    }

    @Test
    public void partialContentReturns206() {
        assertArrayEquals("206 Partial Content\r\n".getBytes(), ResponseStatus.partialContent());
    }

    @Test
    public void redirectReturns302() {
        assertArrayEquals("302 Found\r\n".getBytes(), ResponseStatus.redirect());
    }

    @Test
    public void unauthorizedReturns401() {
        assertArrayEquals("401 Unauthorized\r\n".getBytes(), ResponseStatus.unauthorized());
    }

    @Test
    public void fourOhFourReturns404() {
        assertArrayEquals("404 Not Found\r\n".getBytes(), ResponseStatus.fourOhFour());
    }

    @Test
    public void notAllowedReturns405() {
        assertArrayEquals("405 Method Not Allowed\r\n".getBytes(), ResponseStatus.notAllowed());
    }
}