package com.httpServer.RequestAdapter;

import org.junit.Test;

import static org.junit.Assert.*;

public class RequestTest {

    @Test
    public void defaultsMethodToNull() {
        Request noMethodRequest = new Request();

        assertNull(noMethodRequest.getMethod());
    }

    @Test
    public void setsMethod() {
        Request getRequest = new Request();
        getRequest.setMethod("GET");

        assertEquals("GET", getRequest.getMethod());
    }

    @Test
    public void defaultsUriToNull() {
        Request noUriRequest = new Request();

        assertNull(noUriRequest.getUri());
    }

    @Test
    public void setsUri() {
        Request rootRequest = new Request();
        rootRequest.setUri("/");

        assertEquals("/", rootRequest.getUri());
    }

    @Test
    public void defaultsBodyToNull() {
        Request noBodyRequest = new Request();

        assertNull(noBodyRequest.getBody());
    }

    @Test
    public void setsBody() {
        Request postRequest = new Request();
        postRequest.setBody("default-content");

        assertEquals("default-content", postRequest.getBody());
    }

    @Test
    public void defaultsRangeToNull() {
        Request noRangeRequest = new Request();

        assertNull(noRangeRequest.getRange());
    }

    @Test
    public void setsRange() {
        Request rangeRequest = new Request();
        rangeRequest.setRange("4-");

        assertEquals("4-", rangeRequest.getRange());
    }

    @Test
    public void defaultsAuthorizationToFalse() {
        Request unauthorizedRequest = new Request();

        assertTrue(!unauthorizedRequest.getAuthorization());
    }

    @Test
    public void setsAuthorization() {
        Request authorizedRequest = new Request();
        authorizedRequest.setAuthorization();

        assertTrue(authorizedRequest.getAuthorization());
    }
}