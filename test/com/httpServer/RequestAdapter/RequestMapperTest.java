package com.httpServer.RequestAdapter;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class RequestMapperTest {

    @Test
    public void mapsGetRequestToHashMap() {
        String getRequest = "GET / HTTP/1.1\r\n";

        HashMap<String, String> expectedRequestMap = new HashMap<String, String>();
        expectedRequestMap.put("method", "GET");
        expectedRequestMap.put("uri", "/");

        assertEquals(expectedRequestMap.get("method"), RequestMapper.map(getRequest).get("method"));
        assertEquals(expectedRequestMap.get("uri"), RequestMapper.map(getRequest).get("uri"));
    }

    @Test
    public void mapsPutRequestToHashMap() {
        String putRequest = "PUT /form HTTP/1.1\r\nform contents";

        HashMap<String, String> expectedRequestMap = new HashMap<String, String>();
        expectedRequestMap.put("method", "PUT");
        expectedRequestMap.put("uri", "/form");
        expectedRequestMap.put("body", "form contents");

        assertEquals(expectedRequestMap.get("method"), RequestMapper.map(putRequest).get("method"));
        assertEquals(expectedRequestMap.get("uri"), RequestMapper.map(putRequest).get("uri"));
        assertEquals(expectedRequestMap.get("body"), RequestMapper.map(putRequest).get("body"));
    }

    @Test
    public void mapsPartialContentRequestToHashMap() {
        String partialContentRequest = "GET /partial_content.txt\r\nRange: byte=0-4\r\n";

        HashMap<String, String> expectedRequestMap = new HashMap<String, String>();
        expectedRequestMap.put("method", "GET");
        expectedRequestMap.put("uri", "/partial_content.txt");
        expectedRequestMap.put("range", "0-4");

        assertEquals(expectedRequestMap.get("method"), RequestMapper.map(partialContentRequest).get("method"));
        assertEquals(expectedRequestMap.get("uri"), RequestMapper.map(partialContentRequest).get("uri"));
        assertEquals(expectedRequestMap.get("range"), RequestMapper.map(partialContentRequest).get("range"));
    }

}