package com.httpServer.Handler.Route;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class RouteTest {

    @Test
    public void determinesRouteForGetRootRequest() {
        HashMap<String, String> rootRequest = new HashMap<String, String>();
        rootRequest.put("method", "GET");
        rootRequest.put("uri", "/");
        Route rootRoute = new Route(rootRequest);

        assertTrue(rootRoute.isGetRequest());
        assertTrue(rootRoute.isRootRequest());
        assertTrue(rootRoute.isOkStatus());
        assertTrue(rootRoute.requiresDirectoryContentsBody());
    }

    @Test
    public void determinesRouteForGetFileRequest() {
        HashMap<String, String> getFileRequest = new HashMap<String, String>();
        getFileRequest.put("method", "GET");
        getFileRequest.put("uri", "/file1");
        Route getFileRoute = new Route(getFileRequest);

        assertTrue(getFileRoute.isGetRequest());
        assertTrue(getFileRoute.isAvailableResourceRequest());
        assertTrue(getFileRoute.isOkStatus());
        assertTrue(getFileRoute.requiresFileContentsBody());
    }

    @Test
    public void determinesRouteForNotAllowedFileRequest() {
        HashMap<String, String> notAllowedFileRequest = new HashMap<String, String>();
        notAllowedFileRequest.put("method", "PUT");
        notAllowedFileRequest.put("uri", "/file1");
        Route notAllowedFileRoute = new Route(notAllowedFileRequest);

        assertTrue(notAllowedFileRoute.isPutRequest());
        assertTrue(notAllowedFileRoute.isAvailableResourceRequest());
        assertTrue(notAllowedFileRoute.isNotAllowedStatus());
    }

    @Test
    public void determinesRouteForGetFormRequest() {
        HashMap<String, String> getFormRequest = new HashMap<String, String>();
        getFormRequest.put("method", "GET");
        getFormRequest.put("uri", "/form");
        Route getFormRoute = new Route(getFormRequest);

        assertTrue(getFormRoute.isGetRequest());
        assertTrue(getFormRoute.isFormRequest());
        assertTrue(getFormRoute.isOkStatus());
        assertTrue(getFormRoute.requiresFileContentsBody());
    }

    @Test
    public void determinesRouteForPutFormRequest() {
        HashMap<String, String> putFormRequest = new HashMap<String, String>();
        putFormRequest.put("method", "PUT");
        putFormRequest.put("uri", "/form");
        Route putFormRoute = new Route(putFormRequest);

        assertTrue(putFormRoute.isPutRequest());
        assertTrue(putFormRoute.isFormRequest());
        assertTrue(putFormRoute.isOkStatus());
        assertTrue(putFormRoute.requiresFormEdit());
    }

    @Test
    public void determinesRouteForDeleteFormRequest() {
        HashMap<String, String> deleteFormRequest = new HashMap<String, String>();
        deleteFormRequest.put("method", "DELETE");
        deleteFormRequest.put("uri", "/form");
        Route deleteFormRoute = new Route(deleteFormRequest);

        assertTrue(deleteFormRoute.isDeleteRequest());
        assertTrue(deleteFormRoute.isFormRequest());
        assertTrue(deleteFormRoute.isOkStatus());
        assertTrue(deleteFormRoute.requiresFormDelete());
    }

    @Test
    public void determinesRouteForGetParametersRequest() {
        HashMap<String, String> getParametersRequest = new HashMap<String, String>();
        getParametersRequest.put("method", "GET");
        getParametersRequest.put("uri", "/parameters?query=true");
        Route getParametersRoute = new Route(getParametersRequest);

        assertTrue(getParametersRoute.isGetRequest());
        assertTrue(getParametersRoute.isParametersRequest());
        assertTrue(getParametersRoute.isOkStatus());
        assertTrue(getParametersRoute.requiresParametersBody());
    }

    @Test
    public void determinesRouteForGetPartialContentRequest() {
        HashMap<String, String> getPartialContentRequest = new HashMap<String, String>();
        getPartialContentRequest.put("method", "GET");
        getPartialContentRequest.put("uri", "/partial_content.txt");
        getPartialContentRequest.put("range", "0-4");
        Route getPartialContentRoute = new Route(getPartialContentRequest);

        assertTrue(getPartialContentRoute.isGetRequest());
        assertTrue(getPartialContentRoute.isPartialContentRequest());
        assertTrue(getPartialContentRoute.isPartialContentStatus());
        assertTrue(getPartialContentRoute.requiresPartialContentBody());
    }

    @Test
    public void determinesRouteForGetPatchContentRequest() {
        HashMap<String, String> getPatchContentRequest = new HashMap<String, String>();
        getPatchContentRequest.put("method", "GET");
        getPatchContentRequest.put("uri", "/patch-content.txt");
        Route getPatchContentRoute = new Route(getPatchContentRequest);

        assertTrue(getPatchContentRoute.isGetRequest());
        assertTrue(getPatchContentRoute.isPatchContentRequest());
        assertTrue(getPatchContentRoute.isOkStatus());
        assertTrue(getPatchContentRoute.requiresFileContentsBody());
    }

    @Test
    public void determinesRouteForPatchPatchContentRequest() {
        HashMap<String, String> patchPatchContentRequest = new HashMap<String, String>();
        patchPatchContentRequest.put("method", "PATCH");
        patchPatchContentRequest.put("uri", "/patch-content.txt");
        Route patchPatchContentRoute = new Route(patchPatchContentRequest);

        assertTrue(patchPatchContentRoute.isPatchRequest());
        assertTrue(patchPatchContentRoute.isPatchContentRequest());
        assertTrue(patchPatchContentRoute.isNoContentStatus());
        assertTrue(patchPatchContentRoute.requiresPatchContentPatch());
    }

    @Test
    public void determinesRouteForGetRedirectRequest() {
        HashMap<String, String> redirectRequest = new HashMap<String, String>();
        redirectRequest.put("method", "GET");
        redirectRequest.put("uri", "/redirect");
        Route redirectRoute = new Route(redirectRequest);

        assertTrue(redirectRoute.isGetRequest());
        assertTrue(redirectRoute.isRedirectRequest());
        assertTrue(redirectRoute.isRedirectStatus());
        assertTrue(redirectRoute.requiresLocationHeader());
    }

    @Test
    public void determinesRouteForOptionsMethodOptionsRequest() {
        HashMap<String, String> optionsMethodOptionsRequest = new HashMap<String, String>();
        optionsMethodOptionsRequest.put("method", "OPTIONS");
        optionsMethodOptionsRequest.put("uri", "/method_options");
        Route optionsMethodOptionsRoute = new Route(optionsMethodOptionsRequest);

        assertTrue(optionsMethodOptionsRoute.isOptionsRequest());
        assertTrue(optionsMethodOptionsRoute.isMethodOptionsRequest());
        assertTrue(optionsMethodOptionsRoute.isOkStatus());
        assertTrue(optionsMethodOptionsRoute.requiresMethodOptionsHeader());
    }

    @Test
    public void determinesRouteForUnauthorizedLogsRequest() {
        HashMap<String, String> unauthorizedLogsRequest = new HashMap<String, String>();
        unauthorizedLogsRequest.put("method", "GET");
        unauthorizedLogsRequest.put("uri", "/logs");
        Route unauthorizedLogsRoute = new Route(unauthorizedLogsRequest);

        assertTrue(unauthorizedLogsRoute.isGetRequest());
        assertTrue(unauthorizedLogsRoute.isLogsRequest());
        assertTrue(unauthorizedLogsRoute.isUnauthorizedStatus());
        assertTrue(unauthorizedLogsRoute.requiresAuthenticationRequiredBody());
    }

    @Test
    public void determinesRouteForAuthorizedLogsRequest() {
        HashMap<String, String> authorizedLogsRequest = new HashMap<String, String>();
        authorizedLogsRequest.put("method", "GET");
        authorizedLogsRequest.put("uri", "/logs");
        authorizedLogsRequest.put("authorization", "true");
        Route authorizedLogsRoute = new Route(authorizedLogsRequest);

        assertTrue(authorizedLogsRoute.isGetRequest());
        assertTrue(authorizedLogsRoute.isLogsRequest());
        assertTrue(authorizedLogsRoute.isAuthorized());
        assertTrue(authorizedLogsRoute.isOkStatus());
        assertTrue(authorizedLogsRoute.requiresLogsBody());
    }
}