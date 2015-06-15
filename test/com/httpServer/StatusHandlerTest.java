package com.httpServer;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class StatusHandlerTest {

    @Test
    public void setsStatusForMethodOptionsRequest() {
        HashMap<String, String> methodOptionsRequest = new HashMap<String, String>();
        methodOptionsRequest.put("method", "GET");
        methodOptionsRequest.put("uri", "/method_options");

        HashMap<String, byte[]> methodOptionsResponse = new HashMap<String, byte[]>();

        new StatusHandler(methodOptionsRequest, methodOptionsResponse).setStatus();

        assertArrayEquals(ResponseStatus.ok(), methodOptionsResponse.get("status"));
    }

    @Test
    public void setsStatusForRedirectRequest() {
        HashMap<String, String> redirectRequest = new HashMap<String, String>();
        redirectRequest.put("method", "GET");
        redirectRequest.put("uri", "/redirect");

        HashMap<String, byte[]> redirectResponse = new HashMap<String, byte[]>();

        new StatusHandler(redirectRequest, redirectResponse).setStatus();

        assertArrayEquals(ResponseStatus.redirect(), redirectResponse.get("status"));
    }


    @Test
    public void setsHeaderForUnauthorizedLogsRequest() {
        HashMap<String, String> unauthorizedLogsRequest = new HashMap<String, String>();
        unauthorizedLogsRequest.put("method", "GET");
        unauthorizedLogsRequest.put("uri", "/logs");

        HashMap<String, byte[]> unauthorizedResponse = new HashMap<String, byte[]>();

        new StatusHandler(unauthorizedLogsRequest, unauthorizedResponse).setStatus();

        assertArrayEquals(ResponseStatus.unauthorized(), unauthorizedResponse.get("status"));
    }


    @Test
    public void setsStatusForNotAllowedRequest() {
        HashMap<String, String> notAllowedRequest = new HashMap<String, String>();
        notAllowedRequest.put("method", "PUT");
        notAllowedRequest.put("uri", "file1");

        HashMap<String, byte[]> notAllowedResponse = new HashMap<String, byte[]>();

        new StatusHandler(notAllowedRequest, notAllowedResponse).setStatus();

        assertArrayEquals(ResponseStatus.notAllowed(), notAllowedResponse.get("status"));
    }

    @Test
    public void setsStatusForUnrecognizedRequest() {
        HashMap<String, String> unrecognizedRequest = new HashMap<String, String>();
        unrecognizedRequest.put("method", "GET");
        unrecognizedRequest.put("uri", "/foobar");

        HashMap<String, byte[]> fourOfFourResponse = new HashMap<String, byte[]>();

        new StatusHandler(unrecognizedRequest, fourOfFourResponse).setStatus();

        assertArrayEquals(ResponseStatus.fourOhFour(), fourOfFourResponse.get("status"));
    }
}