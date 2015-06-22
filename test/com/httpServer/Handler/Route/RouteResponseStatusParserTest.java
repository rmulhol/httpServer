package com.httpServer.Handler.Route;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class RouteResponseStatusParserTest {

    @Test
    public void setsOkStatus() {
        HashMap<String, String> okRequest = new HashMap<String, String>();
        okRequest.put("method", "GET");
        okRequest.put("uri", "/");
        Route okRoute = new Route(okRequest);

        assertTrue(okRoute.isOkStatus());
    }

    @Test
    public void setsNotAllowedStatus() {
        HashMap<String, String> notAllowedRequest = new HashMap<String, String>();
        notAllowedRequest.put("method", "PUT");
        notAllowedRequest.put("uri", "/file1");
        Route notAllowedRoute = new Route(notAllowedRequest);

        assertTrue(notAllowedRoute.isNotAllowedStatus());
    }

    @Test
    public void setsPartialContentStatus() {
        HashMap<String, String> partialContentRequest = new HashMap<String, String>();
        partialContentRequest.put("method", "GET");
        partialContentRequest.put("uri", "/partial_content.txt");
        partialContentRequest.put("range", "0-4");
        Route partialContentRoute = new Route(partialContentRequest);

        assertTrue(partialContentRoute.isPartialContentStatus());
    }

    @Test
    public void setsNoContentStatus() {
        HashMap<String, String> patchContentRequest = new HashMap<String, String>();
        patchContentRequest.put("method", "PATCH");
        patchContentRequest.put("uri", "/patch-content.txt");
        Route patchContentRoute = new Route(patchContentRequest);

        assertTrue(patchContentRoute.isNoContentStatus());
    }

    @Test
    public void setsUnauthorizedStatus() {
        HashMap<String, String> unauthorizedRequest = new HashMap<String, String>();
        unauthorizedRequest.put("method", "GET");
        unauthorizedRequest.put("uri", "/logs");
        Route unauthorizedRoute = new Route(unauthorizedRequest);

        assertTrue(unauthorizedRoute.isUnauthorizedStatus());
    }

    @Test
    public void setsRedirectStatus() {
        HashMap<String, String> redirectRequest = new HashMap<String, String>();
        redirectRequest.put("method", "GET");
        redirectRequest.put("uri", "/redirect");
        Route redirectRoute = new Route(redirectRequest);

        assertTrue(redirectRoute.isRedirectStatus());
    }
}