package com.httpServer.Handler.Route;

import com.httpServer.RequestAdapter.Request;
import org.junit.Test;

import static org.junit.Assert.*;

public class RouteFileWritingParserTest {

    @Test
    public void setsFormEditRequired() {
        Request formEditRequest = new Request();
        formEditRequest.setMethod("PUT");
        formEditRequest.setUri("/form");
        Route formEditRoute = new Route(formEditRequest);

        assertTrue(formEditRoute.requiresFormEdit());
    }

    @Test
    public void setsFormDeleteRequired() {
        Request formDeleteRequest = new Request();
        formDeleteRequest.setMethod("DELETE");
        formDeleteRequest.setUri("/form");
        Route formDeleteRoute = new Route(formDeleteRequest);

        assertTrue(formDeleteRoute.requiresFormDelete());
    }

    @Test
    public void setsPatchContentRequired() {
        Request patchContentRequest = new Request();
        patchContentRequest.setMethod("PATCH");
        patchContentRequest.setUri("/patch-content.txt");
        Route patchContentRoute = new Route(patchContentRequest);

        assertTrue(patchContentRoute.requiresPatchContentPatch());
    }

}