package com.httpServer.Handler.SubHandlers;

import com.httpServer.Handler.ResponseContents.ResponseHeader;
import com.httpServer.Handler.Route.Route;
import com.httpServer.RequestAdapter.Request;
import com.httpServer.ResponseAdapter.Response;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class HeaderHandlerTest {

    @Test
    public void setsHeaderForMethodOptionsResponse() {
        Request methodOptionsRequest = new Request();
        methodOptionsRequest.setMethod("OPTIONS");
        methodOptionsRequest.setUri("/method_options");
        Route methodOptionsRoute = new Route(methodOptionsRequest);

        Response methodOptionsResponse = new Response();

        new HeaderHandler(methodOptionsRoute, methodOptionsResponse).setHeader();

        assertArrayEquals(ResponseHeader.methodOptions(), methodOptionsResponse.getHeader());
    }

    @Test
    public void setsHeaderForRedirectResponse() {
        Request redirectRequest = new Request();
        redirectRequest.setMethod("GET");
        redirectRequest.setUri("/redirect");
        Route redirectRoute = new Route(redirectRequest);

        Response redirectResponse = new Response();

        new HeaderHandler(redirectRoute, redirectResponse).setHeader();

        assertArrayEquals(ResponseHeader.location(), redirectResponse.getHeader());
    }

    @Test
    public void setsHeaderForResponseWithoutHeader() {
        Request rootRequest = new Request();
        rootRequest.setMethod("GET");
        rootRequest.setUri("/");
        Route rootRoute = new Route(rootRequest);

        Response rootResponse = new Response();

        new HeaderHandler(rootRoute, rootResponse).setHeader();

        assertArrayEquals(ResponseHeader.noHeader(), rootResponse.getHeader());
    }
}