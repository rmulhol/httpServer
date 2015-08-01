package com.httpServer.Router;

import com.httpServer.Handlers.EditFileHandler;
import com.httpServer.Handlers.FourOhFourHandler;
import com.httpServer.Handlers.MockHandler;
import com.httpServer.Handlers.NotAllowedHandler;
import com.httpServer.RequestAdapter.Request;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RouteRegistrarTest {

    @Before
    public void clearRoutes() {
        RouteRegistrar.clearRoutes();
    }

    @Test
    public void routesRegistrarExists() {
        assertNotNull(new RouteRegistrar());
    }

    @Test
    public void addsARoute() {
        assertTrue(RouteRegistrar.getRoutes().isEmpty());
        RouteRegistrar.addRoute("/test", "GET", new MockHandler());
        assertTrue(RouteRegistrar.getRoutes().get("/test").containsKey("GET"));
    }

    @Test
    public void addsAdditionalRoutesForSameUriWithDifferentMethod() {
        RouteRegistrar.addRoute("/test", "GET", new MockHandler());
        RouteRegistrar.addRoute("/test", "POST", new MockHandler());

        assertTrue(RouteRegistrar.getRoutes().get("/test").containsKey("GET"));
        assertTrue(RouteRegistrar.getRoutes().get("/test").containsKey("POST"));
    }

    @Test
    public void returnsHandlerAssociatedWithKnownRoute() {
        RouteRegistrar.addRoute("/test", "GET", new MockHandler());

        Request testRequest = new Request();
        testRequest.setMethod("GET");
        testRequest.setUri("/test");

        assertEquals(MockHandler.class, RouteRegistrar.getRoute(testRequest).getClass());
    }

    @Test
    public void returnsProperHandlerIf2MethodsForKnownUri() {
        RouteRegistrar.addRoute("/test", "GET", new MockHandler());
        RouteRegistrar.addRoute("/test", "POST", new EditFileHandler());

        Request postTestRequest = new Request();
        postTestRequest.setMethod("POST");
        postTestRequest.setUri("/test");

        assertEquals(EditFileHandler.class, RouteRegistrar.getRoute(postTestRequest).getClass());
    }

    @Test
    public void returns404ForUnrecognizedRoute() {
        Request unrecognizedRequest = new Request();
        unrecognizedRequest.setMethod("GET");
        unrecognizedRequest.setUri("/foobar");

        assertEquals(FourOhFourHandler.class, RouteRegistrar.getRoute(unrecognizedRequest).getClass());
    }

    @Test
    public void returnsNotAllowedForUnrecognizedMethodOnExistingPath() {
        RouteRegistrar.addRoute("/test-file", "GET", new MockHandler());

        Request notAllowedRequest = new Request();
        notAllowedRequest.setMethod("POST");
        notAllowedRequest.setUri("/test-file");

        assertEquals(NotAllowedHandler.class, RouteRegistrar.getRoute(notAllowedRequest).getClass());
    }

}