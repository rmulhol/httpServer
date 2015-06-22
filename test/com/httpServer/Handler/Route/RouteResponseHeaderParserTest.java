package com.httpServer.Handler.Route;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class RouteResponseHeaderParserTest {

    @Test
    public void setsLocationHeaderRequired() {
        HashMap<String, String> redirectRequest = new HashMap<String, String>();
        redirectRequest.put("method", "GET");
        redirectRequest.put("uri", "/redirect");
        Route redirectRoute = new Route(redirectRequest);

        assertTrue(redirectRoute.requiresLocationHeader());
    }

    @Test
    public void setsMethodOptionsRequired() {
        HashMap<String, String> methodOptionsRequest = new HashMap<String, String>();
        methodOptionsRequest.put("method", "OPTIONS");
        methodOptionsRequest.put("uri", "/method_options");
        Route methodOptionsRoute = new Route(methodOptionsRequest);

        assertTrue(methodOptionsRoute.requiresMethodOptionsHeader());
    }
}