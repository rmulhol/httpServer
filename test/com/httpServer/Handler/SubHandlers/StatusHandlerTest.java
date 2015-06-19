package com.httpServer.Handler.SubHandlers;

import com.httpServer.Handler.ResponseContents.ResponseStatus;
import com.httpServer.Handler.Route.Route;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertArrayEquals;

public class StatusHandlerTest {

    @Test
    public void setsStatusForRootRequest() {
        HashMap<String, String> rootRequest = new HashMap<String, String>();
        rootRequest.put("method", "GET");
        rootRequest.put("uri", "/");
        Route rootRoute = new Route(rootRequest);

        HashMap<String, byte[]> rootResponse = new HashMap<String, byte[]>();

        new StatusHandler(rootRoute, rootResponse).setStatus();

        assertArrayEquals(ResponseStatus.ok(), rootResponse.get("status"));
    }

    @Test
    public void setsStatusForMethodOptionsRequest() {
        HashMap<String, String> methodOptionsRequest = new HashMap<String, String>();
        methodOptionsRequest.put("method", "OPTIONS");
        methodOptionsRequest.put("uri", "/method_options");
        Route methodOptionsRoute = new Route(methodOptionsRequest);

        HashMap<String, byte[]> methodOptionsResponse = new HashMap<String, byte[]>();

        new StatusHandler(methodOptionsRoute, methodOptionsResponse).setStatus();

        assertArrayEquals(ResponseStatus.ok(), methodOptionsResponse.get("status"));
    }

    @Test
    public void setsStatusForParametersRequest() {
        HashMap<String, String> parametersRequest = new HashMap<String, String>();
        parametersRequest.put("method", "GET");
        parametersRequest.put("uri", "/parameters?query=this");
        Route parametersRoute = new Route(parametersRequest);

        HashMap<String, byte[]> parametersResponse = new HashMap<String, byte[]>();

        new StatusHandler(parametersRoute, parametersResponse).setStatus();

        assertArrayEquals(ResponseStatus.ok(), parametersResponse.get("status"));
    }

    @Test
    public void setsStatusForPutFormRequest() {
        HashMap<String, String> putFormRequest = new HashMap<String, String>();
        putFormRequest.put("method", "PUT");
        putFormRequest.put("uri", "/form");
        Route putFormRoute = new Route(putFormRequest);

        HashMap<String, byte[]> putFormResponse = new HashMap<String, byte[]>();

        new StatusHandler(putFormRoute, putFormResponse).setStatus();

        assertArrayEquals(ResponseStatus.ok(), putFormResponse.get("status"));
    }

    @Test
    public void setsStatusForPostFormRequest() {
        HashMap<String, String> postFormRequest = new HashMap<String, String>();
        postFormRequest.put("method", "POST");
        postFormRequest.put("uri", "/form");
        Route postFormRoute = new Route(postFormRequest);

        HashMap<String, byte[]> postFormResponse = new HashMap<String, byte[]>();

        new StatusHandler(postFormRoute, postFormResponse).setStatus();

        assertArrayEquals(ResponseStatus.ok(), postFormResponse.get("status"));
    }

    @Test
    public void setsStatusForDeleteFormRequest() {
        HashMap<String, String> deleteFormRequest = new HashMap<String, String>();
        deleteFormRequest.put("method", "DELETE");
        deleteFormRequest.put("uri", "/form");
        Route deleteFormRoute = new Route(deleteFormRequest);

        HashMap<String, byte[]> deleteFormResponse = new HashMap<String, byte[]>();

        new StatusHandler(deleteFormRoute, deleteFormResponse).setStatus();

        assertArrayEquals(ResponseStatus.ok(), deleteFormResponse.get("status"));
    }

    @Test
    public void setsStatusForAuthorizedLogsRequest() {
        HashMap<String, String> authorizedLogsRequest = new HashMap<String, String>();
        authorizedLogsRequest.put("method", "GET");
        authorizedLogsRequest.put("uri", "/logs");
        authorizedLogsRequest.put("authorization", "true");
        Route authorizedLogsRoute = new Route(authorizedLogsRequest);

        HashMap<String, byte[]> authorizedLogsResponse = new HashMap<String, byte[]>();

        new StatusHandler(authorizedLogsRoute, authorizedLogsResponse).setStatus();

        assertArrayEquals(ResponseStatus.ok(), authorizedLogsResponse.get("status"));
    }

    @Test
    public void setsStatusForPatchContentRequest() {
        HashMap<String, String> patchRequest = new HashMap<String, String>();
        patchRequest.put("method", "PATCH");
        patchRequest.put("uri", "/patch-content.txt");
        Route patchRequestRoute = new Route(patchRequest);

        HashMap<String, byte[]> patchResponse = new HashMap<String, byte[]>();

        new StatusHandler(patchRequestRoute, patchResponse).setStatus();

        assertArrayEquals(ResponseStatus.noContent(), patchResponse.get("status"));
    }

    @Test
    public void setsStatusForPartialContentRequest() {
        HashMap<String, String> partialContentRequest = new HashMap<String, String>();
        partialContentRequest.put("method", "GET");
        partialContentRequest.put("uri", "/partial_content.txt");
        partialContentRequest.put("range", "0-4");
        Route partialContentRoute = new Route(partialContentRequest);

        HashMap<String, byte[]> partialContentResponse = new HashMap<String, byte[]>();

        new StatusHandler(partialContentRoute, partialContentResponse).setStatus();

        assertArrayEquals(ResponseStatus.partialContent(), partialContentResponse.get("status"));
    }

    @Test
    public void setsStatusForRedirectRequest() {
        HashMap<String, String> redirectRequest = new HashMap<String, String>();
        redirectRequest.put("method", "GET");
        redirectRequest.put("uri", "/redirect");
        Route redirectRoute = new Route(redirectRequest);

        HashMap<String, byte[]> redirectResponse = new HashMap<String, byte[]>();

        new StatusHandler(redirectRoute, redirectResponse).setStatus();

        assertArrayEquals(ResponseStatus.redirect(), redirectResponse.get("status"));
    }


    @Test
    public void setsHeaderForUnauthorizedLogsRequest() {
        HashMap<String, String> unauthorizedLogsRequest = new HashMap<String, String>();
        unauthorizedLogsRequest.put("method", "GET");
        unauthorizedLogsRequest.put("uri", "/logs");
        Route unauthorizedLogsRoute = new Route(unauthorizedLogsRequest);

        HashMap<String, byte[]> unauthorizedResponse = new HashMap<String, byte[]>();

        new StatusHandler(unauthorizedLogsRoute, unauthorizedResponse).setStatus();

        assertArrayEquals(ResponseStatus.unauthorized(), unauthorizedResponse.get("status"));
    }


    @Test
    public void setsStatusForNotAllowedRequest() {
        HashMap<String, String> notAllowedRequest = new HashMap<String, String>();
        notAllowedRequest.put("method", "PUT");
        notAllowedRequest.put("uri", "file1");
        Route notAllowedRoute = new Route(notAllowedRequest);

        HashMap<String, byte[]> notAllowedResponse = new HashMap<String, byte[]>();

        new StatusHandler(notAllowedRoute, notAllowedResponse).setStatus();

        assertArrayEquals(ResponseStatus.notAllowed(), notAllowedResponse.get("status"));
    }

    @Test
    public void setsStatusForUnrecognizedRequest() {
        HashMap<String, String> unrecognizedRequest = new HashMap<String, String>();
        unrecognizedRequest.put("method", "GET");
        unrecognizedRequest.put("uri", "/foobar");
        Route unrecognizedRoute = new Route(unrecognizedRequest);

        HashMap<String, byte[]> fourOfFourResponse = new HashMap<String, byte[]>();

        new StatusHandler(unrecognizedRoute, fourOfFourResponse).setStatus();

        assertArrayEquals(ResponseStatus.fourOhFour(), fourOfFourResponse.get("status"));
    }
}