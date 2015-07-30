package com.httpServer.Handler.Route;

import com.httpServer.RequestAdapter.Request;
import org.junit.Test;

import static org.junit.Assert.*;

public class RouteResponseStatusParserTest {

    @Test
    public void setsOkStatus() {
        Request okRequest = new Request();
        okRequest.setMethod("GET");
        okRequest.setUri("/");
        Route okRoute = new Route(okRequest);

        assertTrue(okRoute.isOkStatus());
    }

    @Test
    public void setsNotAllowedStatus() {
        Request notAllowedRequest = new Request();
        notAllowedRequest.setMethod("PUT");
        notAllowedRequest.setUri("/file1");
        Route notAllowedRoute = new Route(notAllowedRequest);

        assertTrue(notAllowedRoute.isNotAllowedStatus());
    }

    @Test
    public void setsPartialContentStatus() {
        Request partialContentRequest = new Request();
        partialContentRequest.setMethod("GET");
        partialContentRequest.setUri("/partial_content.txt");
        partialContentRequest.setRange("0-4");
        Route partialContentRoute = new Route(partialContentRequest);

        assertTrue(partialContentRoute.isPartialContentStatus());
    }

    @Test
    public void setsNoContentStatus() {
        Request patchContentRequest = new Request();
        patchContentRequest.setMethod("PATCH");
        patchContentRequest.setUri("/patch-content.txt");
        Route patchContentRoute = new Route(patchContentRequest);

        assertTrue(patchContentRoute.isNoContentStatus());
    }

    @Test
    public void setsUnauthorizedStatus() {
        Request unauthorizedRequest = new Request();
        unauthorizedRequest.setMethod("GET");
        unauthorizedRequest.setUri("/logs");
        Route unauthorizedRoute = new Route(unauthorizedRequest);

        assertTrue(unauthorizedRoute.isUnauthorizedStatus());
    }

    @Test
    public void setsRedirectStatus() {
        Request redirectRequest = new Request();
        redirectRequest.setMethod("GET");
        redirectRequest.setUri("/redirect");
        Route redirectRoute = new Route(redirectRequest);

        assertTrue(redirectRoute.isRedirectStatus());
    }
}