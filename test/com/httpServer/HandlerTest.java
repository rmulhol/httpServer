package com.httpServer;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class HandlerTest {

    @Test
    public void getResponseDelivers200ForRootRequest() {
        HashMap<String, String> rootRequest = new HashMap<String, String>();
        rootRequest.put("method", "GET");
        rootRequest.put("uri", "/");

        HashMap<String, byte[]> rootResponse = new HashMap<String, byte[]>();
        rootResponse.put("status", "HTTP/1.1 200 OK\r\n".getBytes());
        rootResponse.put("header", "\r\n".getBytes());

        String directoryContents = "<p><a href='/file1'>file1</a></p>\r\n" +
                "<p><a href='/file2'>file2</a></p>\r\n" +
                "<p><a href='/form'>form</a></p>\r\n" +
                "<p><a href='/image.gif'>image.gif</a></p>\r\n" +
                "<p><a href='/image.jpeg'>image.jpeg</a></p>\r\n" +
                "<p><a href='/image.png'>image.png</a></p>\r\n" +
                "<p><a href='/partial_content.txt'>partial_content.txt</a></p>\r\n" +
                "<p><a href='/patch-content.txt'>patch-content.txt</a></p>\r\n" +
                "<p><a href='/text-file.txt'>text-file.txt</a></p>\r\n";

        rootResponse.put("body", directoryContents.getBytes());

        assertArrayEquals(rootResponse.get("status"), Handler.getResponse(rootRequest).get("status"));
        assertArrayEquals(rootResponse.get("header"), Handler.getResponse(rootRequest).get("header"));
        assertArrayEquals(rootResponse.get("body"), Handler.getResponse(rootRequest).get("body"));
    }

    @Test
    public void getResponseDelivers200ForGetFormRequest() {
        HashMap<String, String> formRequest = new HashMap<String, String>();
        formRequest.put("method", "GET");
        formRequest.put("uri", "/form");

        HashMap<String, byte[]> formResponse = new HashMap<String, byte[]>();
        formResponse.put("status", "HTTP/1.1 200 OK\r\n".getBytes());
        formResponse.put("header", "\r\n".getBytes());
        formResponse.put("body", "\"My\"=\"Data\"\n\n".getBytes());

        assertArrayEquals(formResponse.get("status"), Handler.getResponse(formRequest).get("status"));
        assertArrayEquals(formResponse.get("header"), Handler.getResponse(formRequest).get("header"));
        assertArrayEquals(formResponse.get("body"), Handler.getResponse(formRequest).get("body"));
    }

    @Test
    public void getResponseDelivers200ForMethodOptionsRequest() {
        HashMap<String, String> methodOptionsRequest = new HashMap<String, String>();
        methodOptionsRequest.put("method", "GET");
        methodOptionsRequest.put("uri", "/method_options");

        HashMap<String, byte[]> methodOptionsResponse = new HashMap<String, byte[]>();
        methodOptionsResponse.put("status", "HTTP/1.1 200 OK\r\n".getBytes());
        methodOptionsResponse.put("header", "Allow: GET,HEAD,POST,OPTIONS,PUT\r\n".getBytes());
        methodOptionsResponse.put("body", "".getBytes());

        assertArrayEquals(methodOptionsResponse.get("status"), Handler.getResponse(methodOptionsRequest).get("status"));
        assertArrayEquals(methodOptionsResponse.get("header"), Handler.getResponse(methodOptionsRequest).get("header"));
        assertArrayEquals(methodOptionsResponse.get("body"), Handler.getResponse(methodOptionsRequest).get("body"));
    }

    @Test
    public void getResponseDelivers200ForParametersRequest() {
        HashMap<String, String> parametersRequest = new HashMap<String, String>();
        parametersRequest.put("method", "GET");
        parametersRequest.put("uri", "/parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C" +
                "%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F" +
                "&variable_2=stuff");

        HashMap<String, byte[]> parametersResponse = new HashMap<String, byte[]>();
        parametersResponse.put("status", "HTTP/1.1 200 OK\r\n".getBytes());
        parametersResponse.put("header", "\r\n".getBytes());
        parametersResponse.put("body", ("/parameters?variable_1 = Operators <, >, =, !=; +, -, *, &, @, #, $, [, ]: " +
                "\"is that all\"?&variable_2 = stuff").getBytes());

        assertArrayEquals(parametersResponse.get("status"), Handler.getResponse(parametersRequest).get("status"));
        assertArrayEquals(parametersResponse.get("header"), Handler.getResponse(parametersRequest).get("header"));
        assertArrayEquals(parametersResponse.get("body"), Handler.getResponse(parametersRequest).get("body"));
    }

    @Test
    public void getResponseDelivers302ForRedirectRequest() {
        HashMap<String, String> redirectRequest = new HashMap<String, String>();
        redirectRequest.put("method", "GET");
        redirectRequest.put("uri", "/redirect");

        HashMap<String, byte[]> redirectResponse = new HashMap<String, byte[]>();
        redirectResponse.put("status", "HTTP/1.1 302 Found\r\n".getBytes());
        redirectResponse.put("header", "Location: http://localhost:5000/\r\n".getBytes());
        redirectResponse.put("body", "".getBytes());

        assertArrayEquals(redirectResponse.get("status"), Handler.getResponse(redirectRequest).get("status"));
        assertArrayEquals(redirectResponse.get("header"), Handler.getResponse(redirectRequest).get("header"));
        assertArrayEquals(redirectResponse.get("body"), Handler.getResponse(redirectRequest).get("body"));
    }

    @Test
    public void getResponseDelivers401ForUnauthorizedLogsRequest() {
        HashMap<String, String> unauthorizedLogsRequest = new HashMap<String, String>();
        unauthorizedLogsRequest.put("method", "GET");
        unauthorizedLogsRequest.put("uri", "/logs");

        HashMap<String, byte[]> unauthorizedResponse= new HashMap<String, byte[]>();
        unauthorizedResponse.put("status", "HTTP/1.1 401 Unauthorized\r\n".getBytes());
        unauthorizedResponse.put("header", "\r\n".getBytes());
        unauthorizedResponse.put("body", "Authentication required".getBytes());

        assertArrayEquals(unauthorizedResponse.get("status"), Handler.getResponse(unauthorizedLogsRequest).get("status"));
        assertArrayEquals(unauthorizedResponse.get("header"), Handler.getResponse(unauthorizedLogsRequest).get("header"));
        assertArrayEquals(unauthorizedResponse.get("body"), Handler.getResponse(unauthorizedLogsRequest).get("body"));
    }

    @Test
    public void getResponseDelivers404ForUnrecognizedRequest() {
        HashMap<String, String> unrecognizedRequest = new HashMap<String, String>();
        unrecognizedRequest.put("method", "GET");
        unrecognizedRequest.put("uri", "/foobar");

        HashMap<String, byte[]> fourOhFourResponse = new HashMap<String, byte[]>();
        fourOhFourResponse.put("status", "HTTP/1.1 404 Not Found\r\n".getBytes());
        fourOhFourResponse.put("header", "\r\n".getBytes());
        fourOhFourResponse.put("body", "".getBytes());

        assertArrayEquals(fourOhFourResponse.get("status"), Handler.getResponse(unrecognizedRequest).get("status"));
        assertArrayEquals(fourOhFourResponse.get("header"), Handler.getResponse(unrecognizedRequest).get("header"));
        assertArrayEquals(fourOhFourResponse.get("body"), Handler.getResponse(unrecognizedRequest).get("body"));
    }

    @Test
    public void getResponseDelivers405ForNotAllowedRequest() {
        HashMap<String, String> notAllowedRequest = new HashMap<String, String>();
        notAllowedRequest.put("method", "PUT");
        notAllowedRequest.put("uri", "/file1");

        HashMap<String, byte[]> notAllowedResponse = new HashMap<String, byte[]>();
        notAllowedResponse.put("status", "HTTP/1.1 405 Method Not Allowed\r\n".getBytes());
        notAllowedResponse.put("header", "\r\n".getBytes());
        notAllowedResponse.put("body", "".getBytes());

        assertArrayEquals(notAllowedResponse.get("status"), Handler.getResponse(notAllowedRequest).get("status"));
        assertArrayEquals(notAllowedResponse.get("header"), Handler.getResponse(notAllowedRequest).get("header"));
        assertArrayEquals(notAllowedResponse.get("body"), Handler.getResponse(notAllowedRequest).get("body"));
    }
}