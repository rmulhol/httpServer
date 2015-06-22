package com.httpServer.Handler.Route;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class RouteFileWritingParserTest {

    @Test
    public void setsFormEditRequired() {
        HashMap<String, String> formEditRequest = new HashMap<String, String>();
        formEditRequest.put("method", "PUT");
        formEditRequest.put("uri", "/form");
        Route formEditRoute = new Route(formEditRequest);

        assertTrue(formEditRoute.requiresFormEdit());
    }

    @Test
    public void setsFormDeleteRequired() {
        HashMap<String, String> formDeleteRequest = new HashMap<String, String>();
        formDeleteRequest.put("method", "DELETE");
        formDeleteRequest.put("uri", "/form");
        Route formDeleteRoute = new Route(formDeleteRequest);

        assertTrue(formDeleteRoute.requiresFormDelete());
    }

    @Test
    public void setsPatchContentRequired() {
        HashMap<String, String> patchContentRequest = new HashMap<String, String>();
        patchContentRequest.put("method", "PATCH");
        patchContentRequest.put("uri", "/patch-content.txt");
        Route patchContentRoute = new Route(patchContentRequest);

        assertTrue(patchContentRoute.requiresPatchContentPatch());
    }

}