package com.httpServer.Handler.Route;

import com.httpServer.RequestAdapter.Request;
import org.junit.Test;

import static org.junit.Assert.*;

public class RouteResponseHeaderParserTest {

    @Test
    public void setsLocationHeaderRequired() {
        Request redirectRequest = new Request();
        redirectRequest.setMethod("GET");
        redirectRequest.setUri("/redirect");
        Route redirectRoute = new Route(redirectRequest);

        assertTrue(redirectRoute.requiresLocationHeader());
    }

    @Test
    public void setsMethodOptionsRequired() {
        Request methodOptionsRequest = new Request();
        methodOptionsRequest.setMethod("OPTIONS");
        methodOptionsRequest.setUri("/method_options");
        Route methodOptionsRoute = new Route(methodOptionsRequest);

        assertTrue(methodOptionsRoute.requiresMethodOptionsHeader());
    }
}