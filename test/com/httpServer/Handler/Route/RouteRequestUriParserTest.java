package com.httpServer.Handler.Route;

import com.httpServer.RequestAdapter.Request;
import org.junit.Test;

import static org.junit.Assert.*;

public class RouteRequestUriParserTest {

    @Test
    public void setsAvailableResourceUri() {
        Request availableResourceRequest = new Request();
        availableResourceRequest.setMethod("GET");
        availableResourceRequest.setUri("/file1");
        Route availableResourceRoute = new Route(availableResourceRequest);

        assertTrue(availableResourceRoute.isAvailableResourceRequest());
    }

    @Test
    public void setsFormUri() {
        Request formRequest = new Request();
        formRequest.setMethod("GET");
        formRequest.setUri("/form");
        Route formRoute = new Route(formRequest);

        assertTrue(formRoute.isFormRequest());
    }

    @Test
    public void setsLogsUri() {
        Request logsRequest = new Request();
        logsRequest.setMethod("GET");
        logsRequest.setUri("/logs");
        Route logsRoute = new Route(logsRequest);

        assertTrue(logsRoute.isLogsRequest());
    }

    @Test
    public void setsMethodOptionsUri() {
        Request methodOptionsRequest = new Request();
        methodOptionsRequest.setMethod("OPTIONS");
        methodOptionsRequest.setUri("/method_options");
        Route methodOptionsRoute = new Route(methodOptionsRequest);

        assertTrue(methodOptionsRoute.isMethodOptionsRequest());
    }

    @Test
    public void setsParametersUri() {
        Request parametersRequest = new Request();
        parametersRequest.setMethod("GET");
        parametersRequest.setUri("/parameters?query=true");
        Route parametersRoute = new Route(parametersRequest);

        assertTrue(parametersRoute.isParametersRequest());
    }

    @Test
    public void setsPartialContentUri() {
        Request partialContentRequest = new Request();
        partialContentRequest.setMethod("GET");
        partialContentRequest.setUri("/partial_content.txt");
        Route partialContentRoute = new Route(partialContentRequest);

        assertTrue(partialContentRoute.isPartialContentRequest());
    }

    @Test
    public void setsPatchContentUri() {
        Request patchRequest = new Request();
        patchRequest.setMethod("GET");
        patchRequest.setUri("/patch-content.txt");
        Route patchRoute = new Route(patchRequest);

        assertTrue(patchRoute.isPatchContentRequest());
    }

    @Test
    public void setsRedirectUri() {
        Request redirectRequest = new Request();
        redirectRequest.setMethod("GET");
        redirectRequest.setUri("/redirect");
        Route redirectRoute = new Route(redirectRequest);

        assertTrue(redirectRoute.isRedirectRequest());
    }

    @Test
    public void setsRootUri() {
        Request rootRequest = new Request();
        rootRequest.setMethod("GET");
        rootRequest.setUri("/");
        Route rootRoute = new Route(rootRequest);

        assertTrue(rootRoute.isRootRequest());
    }
}