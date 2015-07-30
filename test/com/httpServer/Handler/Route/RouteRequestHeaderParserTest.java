package com.httpServer.Handler.Route;

import com.httpServer.RequestAdapter.Request;
import org.junit.Test;

import static org.junit.Assert.*;

public class RouteRequestHeaderParserTest {

    @Test
    public void setsAuthorizationHeader() {
        Request authorizedRequest = new Request();
        authorizedRequest.setMethod("GET");
        authorizedRequest.setUri("/logs");
        authorizedRequest.setAuthorization();
        Route authorizedRoute = new Route(authorizedRequest);

        assertTrue(authorizedRoute.isAuthorized());
    }

    @Test
    public void setsRangeHeader() {
        Request partialContentRequest = new Request();
        partialContentRequest.setMethod("GET");
        partialContentRequest.setUri("/partial_content.txt");
        partialContentRequest.setRange("0-4");
        Route partialContentRoute = new Route(partialContentRequest);

        assertTrue(partialContentRoute.hasRange());
    }
}