package com.httpServer.Handler.Route;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class RouteRequestMethodParserTest {

    @Test
    public void setsDeleteMethod() {
        HashMap<String, String > deleteRequest = new HashMap<String, String>();
        deleteRequest.put("method", "DELETE");
        deleteRequest.put("uri", "/");
        Route deleteRoute = new Route(deleteRequest);

        assertTrue(deleteRoute.isDeleteRequest());
    }

    @Test
    public void setsGetMethod() {
        HashMap<String, String > getRequest = new HashMap<String, String>();
        getRequest.put("method", "GET");
        getRequest.put("uri", "/");
        Route getRoute = new Route(getRequest);

        assertTrue(getRoute.isGetRequest());
    }

    @Test
    public void setsOptionsMethod() {
        HashMap<String, String > optionsRequest = new HashMap<String, String>();
        optionsRequest.put("method", "OPTIONS");
        optionsRequest.put("uri", "/");
        Route optionsRoute = new Route(optionsRequest);

        assertTrue(optionsRoute.isOptionsRequest());
    }

    @Test
    public void setsPatchMethod() {
        HashMap<String, String > patchRequest = new HashMap<String, String>();
        patchRequest.put("method", "PATCH");
        patchRequest.put("uri", "/");
        Route patchRoute = new Route(patchRequest);

        assertTrue(patchRoute.isPatchRequest());
    }

    @Test
    public void setsPostMethod() {
        HashMap<String, String > postRequest = new HashMap<String, String>();
        postRequest.put("method", "POST");
        postRequest.put("uri", "/");
        Route postRoute = new Route(postRequest);

        assertTrue(postRoute.isPostRequest());
    }

    @Test
    public void setsPutMethod() {
        HashMap<String, String > putRequest = new HashMap<String, String>();
        putRequest.put("method", "PUT");
        putRequest.put("uri", "/");
        Route putRoute = new Route(putRequest);

        assertTrue(putRoute.isPutRequest());
    }
}