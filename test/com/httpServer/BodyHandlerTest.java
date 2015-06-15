package com.httpServer;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class BodyHandlerTest {

    @Test
    public void setsBodyForUnauthorizedLogsRequest() {
        HashMap<String, String> unauthorizedLogsRequest = new HashMap<String, String>();
        unauthorizedLogsRequest.put("method", "GET");
        unauthorizedLogsRequest.put("uri", "/logs");

        HashMap<String, byte[]> unauthorizedResponse = new HashMap<String, byte[]>();

        new BodyHandler(unauthorizedLogsRequest, unauthorizedResponse).setBody();

        assertArrayEquals("Authentication required".getBytes(), unauthorizedResponse.get("body"));
    }

    @Test
    public void setsBodyForParametersRequest() {
        HashMap<String, String> parametersRequest = new HashMap<String, String>();
        parametersRequest.put("method", "GET");
        parametersRequest.put("uri", "/parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C" +
                "%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F" +
                "&variable_2=stuff");

        HashMap<String, byte[]> parametersResponse = new HashMap<String, byte[]>();

        new BodyHandler(parametersRequest, parametersResponse).setBody();
        byte[] expectedOutput = ("/parameters?variable_1 = Operators <, >, =, !=; +, -, *, &, @, #, $, [, ]: " +
                "\"is that all\"?&variable_2 = stuff").getBytes();

        assertArrayEquals(expectedOutput, parametersResponse.get("body"));


    }

    @Test
    public void setsBodyForRequestWithoutBody() {
        HashMap<String, String> noBodyRequiredRequest = new HashMap<String, String>();
        noBodyRequiredRequest.put("method", "GET");
        noBodyRequiredRequest.put("uri", "/redirect");

        HashMap<String, byte[]> noBodyResponse = new HashMap<String, byte[]>();

        new BodyHandler(noBodyRequiredRequest, noBodyResponse).setBody();

        assertArrayEquals("".getBytes(), noBodyResponse.get("body"));
    }
}