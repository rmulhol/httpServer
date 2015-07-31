package com.httpServer.RequestAdapter;

import org.junit.Test;

import static org.junit.Assert.*;

public class RequestBuilderTest {

    @Test
    public void requestBuilderExists() {
        assertNotNull(new RequestBuilder());
    }

    @Test
    public void buildsGetRequest() {
        String getRequest = "GET / HTTP/1.1\r\n";

        Request request = RequestBuilder.buildRequest(getRequest);

        assertEquals("GET", request.getMethod());
        assertEquals("/", request.getUri());
    }

    @Test
    public void buildsPutRequest() {
        String putRequest = "PUT /form HTTP/1.1\r\nform contents";

        Request request = RequestBuilder.buildRequest(putRequest);

        assertEquals("PUT", request.getMethod());
        assertEquals("/form", request.getUri());
        assertEquals("form contents", request.getBody());
    }

    @Test
    public void buildsPartialContentRequest() {
        String partialContentRequest = "GET /partial_content.txt\r\nRange: byte=0-4\r\n";

        Request request = RequestBuilder.buildRequest(partialContentRequest);

        assertEquals("GET", request.getMethod());
        assertEquals("/partial_content.txt", request.getUri());
        assertEquals("0-4", request.getRange());
    }

    @Test
    public void buildsAuthorizedLogsRequest() {
        String authorizedLogsRequest = "GET /logs\r\nAuthorization basic: 123";

        Request request = RequestBuilder.buildRequest(authorizedLogsRequest);

        assertEquals("GET", request.getMethod());
        assertEquals("/logs", request.getUri());
        assertTrue(request.getAuthorization());
    }

    @Test
    public void buildsParametersRequest() {
        String parametersRequest = "GET /parameters?query=true\r\n";

        Request request = RequestBuilder.buildRequest(parametersRequest);

        assertEquals("GET", request.getMethod());
        assertEquals("/parameters", request.getUri());
        assertEquals("query=true", request.getParameters());
    }
}