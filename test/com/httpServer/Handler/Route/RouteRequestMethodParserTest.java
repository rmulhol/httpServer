package com.httpServer.Handler.Route;

import com.httpServer.RequestAdapter.Request;
import org.junit.Test;

import static org.junit.Assert.*;

public class RouteRequestMethodParserTest {

    @Test
    public void setsDeleteMethod() {
        Request deleteRequest = new Request();
        deleteRequest.setMethod("DELETE");
        deleteRequest.setUri("/");
        Route deleteRoute = new Route(deleteRequest);

        assertTrue(deleteRoute.isDeleteRequest());
    }

    @Test
    public void setsGetMethod() {
        Request getRequest = new Request();
        getRequest.setMethod("GET");
        getRequest.setUri("/");
        Route getRoute = new Route(getRequest);

        assertTrue(getRoute.isGetRequest());
    }

    @Test
    public void setsOptionsMethod() {
        Request optionsRequest = new Request();
        optionsRequest.setMethod("OPTIONS");
        optionsRequest.setUri("/");
        Route optionsRoute = new Route(optionsRequest);

        assertTrue(optionsRoute.isOptionsRequest());
    }

    @Test
    public void setsPatchMethod() {
        Request patchRequest = new Request();
        patchRequest.setMethod("PATCH");
        patchRequest.setUri("/");
        Route patchRoute = new Route(patchRequest);

        assertTrue(patchRoute.isPatchRequest());
    }

    @Test
    public void setsPostMethod() {
        Request postRequest = new Request();
        postRequest.setMethod("POST");
        postRequest.setUri("/");
        Route postRoute = new Route(postRequest);

        assertTrue(postRoute.isPostRequest());
    }

    @Test
    public void setsPutMethod() {
        Request putRequest = new Request();
        putRequest.setMethod("PUT");
        putRequest.setUri("/");
        Route putRoute = new Route(putRequest);

        assertTrue(putRoute.isPutRequest());
    }
}