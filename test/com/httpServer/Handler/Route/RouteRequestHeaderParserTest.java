package com.httpServer.Handler.Route;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class RouteRequestHeaderParserTest {

    @Test
    public void setsAuthorizationHeader() {
        HashMap<String, String> authorizedRequest = new HashMap<String, String>();
        authorizedRequest.put("method", "GET");
        authorizedRequest.put("uri", "/logs");
        authorizedRequest.put("authorization", "true");
        Route authorizedRoute = new Route(authorizedRequest);

        assertTrue(authorizedRoute.isAuthorized());
    }

    @Test
    public void setsRangeHeader() {
        HashMap<String, String> partialContentRequest = new HashMap<String, String>();
        partialContentRequest.put("method", "GET");
        partialContentRequest.put("uri", "/partial_content.txt");
        partialContentRequest.put("range", "0-4");
        Route partialContentRoute = new Route(partialContentRequest);

        assertTrue(partialContentRoute.hasRange());
    }
}