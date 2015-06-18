package com.httpServer.Handler.ResponseContents;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class ResponseHeaderTest {

    @Test
    public void locationDeliversRootPath() {
        assertArrayEquals("Location: http://localhost:5000/\r\n".getBytes(), ResponseHeader.location());
    }

    @Test
    public void methodOptionsDeliversMethodOptions() {
        assertArrayEquals("Allow: GET,HEAD,POST,OPTIONS,PUT\r\n".getBytes(), ResponseHeader.methodOptions());
    }

    @Test
    public void noHeaderDeliversLineBreak() {
        assertArrayEquals("\r\n".getBytes(), ResponseHeader.noHeader());
    }
}