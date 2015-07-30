package com.httpServer.Handler.SubHandlers;

import com.httpServer.Handler.ResponseContents.ResponseStatus;
import com.httpServer.Handler.Route.Route;
import com.httpServer.RequestAdapter.Request;
import com.httpServer.ResponseAdapter.Response;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class StatusHandlerTest {

    @Test
    public void setsStatusForRootRequest() {
        Request rootRequest = new Request();
        rootRequest.setMethod("GET");
        rootRequest.setUri("/");
        Route rootRoute = new Route(rootRequest);

        Response rootResponse = new Response();

        new StatusHandler(rootRoute, rootResponse).setStatus();

        assertArrayEquals(ResponseStatus.ok(), rootResponse.getStatus());
    }

    @Test
    public void setsStatusForMethodOptionsRequest() {
        Request methodOptionsRequest = new Request();
        methodOptionsRequest.setMethod("OPTIONS");
        methodOptionsRequest.setUri("/method_options");
        Route methodOptionsRoute = new Route(methodOptionsRequest);

        Response methodOptionsResponse = new Response();

        new StatusHandler(methodOptionsRoute, methodOptionsResponse).setStatus();

        assertArrayEquals(ResponseStatus.ok(), methodOptionsResponse.getStatus());
    }

    @Test
    public void setsStatusForParametersRequest() {
        Request parametersRequest = new Request();
        parametersRequest.setMethod("GET");
        parametersRequest.setUri("/parameters?query=this");
        Route parametersRoute = new Route(parametersRequest);

        Response parametersResponse = new Response();

        new StatusHandler(parametersRoute, parametersResponse).setStatus();

        assertArrayEquals(ResponseStatus.ok(), parametersResponse.getStatus());
    }

    @Test
    public void setsStatusForPutFormRequest() {
        Request putFormRequest = new Request();
        putFormRequest.setMethod("PUT");
        putFormRequest.setUri("/form");
        Route putFormRoute = new Route(putFormRequest);

        Response putFormResponse = new Response();

        new StatusHandler(putFormRoute, putFormResponse).setStatus();

        assertArrayEquals(ResponseStatus.ok(), putFormResponse.getStatus());
    }

    @Test
    public void setsStatusForPostFormRequest() {
        Request postFormRequest = new Request();
        postFormRequest.setMethod("POST");
        postFormRequest.setUri("/form");
        Route postFormRoute = new Route(postFormRequest);

        Response postFormResponse = new Response();

        new StatusHandler(postFormRoute, postFormResponse).setStatus();

        assertArrayEquals(ResponseStatus.ok(), postFormResponse.getStatus());
    }

    @Test
    public void setsStatusForDeleteFormRequest() {
        Request deleteFormRequest = new Request();
        deleteFormRequest.setMethod("DELETE");
        deleteFormRequest.setUri("/form");
        Route deleteFormRoute = new Route(deleteFormRequest);

        Response deleteFormResponse = new Response();

        new StatusHandler(deleteFormRoute, deleteFormResponse).setStatus();

        assertArrayEquals(ResponseStatus.ok(), deleteFormResponse.getStatus());
    }

    @Test
    public void setsStatusForAuthorizedLogsRequest() {
        Request authorizedLogsRequest = new Request();
        authorizedLogsRequest.setMethod("GET");
        authorizedLogsRequest.setUri("/logs");
        authorizedLogsRequest.setAuthorization();
        Route authorizedLogsRoute = new Route(authorizedLogsRequest);

        Response authorizedLogsResponse = new Response();

        new StatusHandler(authorizedLogsRoute, authorizedLogsResponse).setStatus();

        assertArrayEquals(ResponseStatus.ok(), authorizedLogsResponse.getStatus());
    }

    @Test
    public void setsStatusForPatchContentRequest() {
        Request patchRequest = new Request();
        patchRequest.setMethod("PATCH");
        patchRequest.setUri("/patch-content.txt");
        Route patchRequestRoute = new Route(patchRequest);

        Response patchResponse = new Response();

        new StatusHandler(patchRequestRoute, patchResponse).setStatus();

        assertArrayEquals(ResponseStatus.noContent(), patchResponse.getStatus());
    }

    @Test
    public void setsStatusForPartialContentRequest() {
        Request partialContentRequest = new Request();
        partialContentRequest.setMethod("GET");
        partialContentRequest.setUri("/partial_content.txt");
        partialContentRequest.setRange("0-4");
        Route partialContentRoute = new Route(partialContentRequest);

        Response partialContentResponse = new Response();

        new StatusHandler(partialContentRoute, partialContentResponse).setStatus();

        assertArrayEquals(ResponseStatus.partialContent(), partialContentResponse.getStatus());
    }

    @Test
    public void setsStatusForRedirectRequest() {
        Request redirectRequest = new Request();
        redirectRequest.setMethod("GET");
        redirectRequest.setUri("/redirect");
        Route redirectRoute = new Route(redirectRequest);

        Response redirectResponse = new Response();

        new StatusHandler(redirectRoute, redirectResponse).setStatus();

        assertArrayEquals(ResponseStatus.redirect(), redirectResponse.getStatus());
    }


    @Test
    public void setsHeaderForUnauthorizedLogsRequest() {
        Request unauthorizedLogsRequest = new Request();
        unauthorizedLogsRequest.setMethod("GET");
        unauthorizedLogsRequest.setUri("/logs");
        Route unauthorizedLogsRoute = new Route(unauthorizedLogsRequest);

        Response unauthorizedResponse = new Response();

        new StatusHandler(unauthorizedLogsRoute, unauthorizedResponse).setStatus();

        assertArrayEquals(ResponseStatus.unauthorized(), unauthorizedResponse.getStatus());
    }


    @Test
    public void setsStatusForNotAllowedRequest() {
        Request notAllowedRequest = new Request();
        notAllowedRequest.setMethod("PUT");
        notAllowedRequest.setUri("/file1");
        Route notAllowedRoute = new Route(notAllowedRequest);

        Response notAllowedResponse = new Response();

        new StatusHandler(notAllowedRoute, notAllowedResponse).setStatus();

        assertArrayEquals(ResponseStatus.notAllowed(), notAllowedResponse.getStatus());
    }

    @Test
    public void setsStatusForUnrecognizedRequest() {
        Request unrecognizedRequest = new Request();
        unrecognizedRequest.setMethod("GET");
        unrecognizedRequest.setUri("/foobar");
        Route unrecognizedRoute = new Route(unrecognizedRequest);

        Response fourOfFourResponse = new Response();

        new StatusHandler(unrecognizedRoute, fourOfFourResponse).setStatus();

        assertArrayEquals(ResponseStatus.fourOhFour(), fourOfFourResponse.getStatus());
    }
}