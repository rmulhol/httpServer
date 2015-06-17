package com.httpServer;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class StatusHandlerTest {

    @Test
    public void setsStatusForRootRequest() {
        HashMap<String, String> rootRequest = new HashMap<String, String>();
        rootRequest.put("method", "GET");
        rootRequest.put("uri", "/");

        HashMap<String, byte[]> rootResponse = new HashMap<String, byte[]>();

        new StatusHandler(rootRequest, rootResponse).setStatus();

        assertArrayEquals(ResponseStatus.ok(), rootResponse.get("status"));
    }

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
    public void setsStatusForParametersRequest() {
        HashMap<String, String> parametersRequest = new HashMap<String, String>();
        parametersRequest.put("method", "GET");
        parametersRequest.put("uri", "/parameters?query=this");

        HashMap<String, byte[]> parametersResponse = new HashMap<String, byte[]>();

        new StatusHandler(parametersRequest, parametersResponse).setStatus();

        assertArrayEquals(ResponseStatus.ok(), parametersResponse.get("status"));
    }

    @Test
    public void setsStatusForPutFormRequest() {
        HashMap<String, String> putFormRequest = new HashMap<String, String>();
        putFormRequest.put("method", "PUT");
        putFormRequest.put("uri", "/form");

        HashMap<String, byte[]> putFormResponse = new HashMap<String, byte[]>();

        new StatusHandler(putFormRequest, putFormResponse).setStatus();

        assertArrayEquals(ResponseStatus.ok(), putFormResponse.get("status"));
    }

    @Test
    public void setsStatusForPostFormRequest() {
        HashMap<String, String> postFormRequest = new HashMap<String, String>();
        postFormRequest.put("method", "POST");
        postFormRequest.put("uri", "/form");

        HashMap<String, byte[]> postFormResponse = new HashMap<String, byte[]>();

        new StatusHandler(postFormRequest, postFormResponse).setStatus();

        assertArrayEquals(ResponseStatus.ok(), postFormResponse.get("status"));
    }

    @Test
    public void setsStatusForDeleteFormRequest() {
        HashMap<String, String> deleteFormRequest = new HashMap<String, String>();
        deleteFormRequest.put("method", "DELETE");
        deleteFormRequest.put("uri", "/form");

        HashMap<String, byte[]> deleteFormResponse = new HashMap<String, byte[]>();

        new StatusHandler(deleteFormRequest, deleteFormResponse).setStatus();

        assertArrayEquals(ResponseStatus.ok(), deleteFormResponse.get("status"));
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