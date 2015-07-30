package com.httpServer.Handler.Route;

import com.httpServer.RequestAdapter.Request;
import org.junit.Test;

import static org.junit.Assert.*;

public class RouteTest {

    @Test
    public void determinesRouteForGetRootRequest() {
        Request rootRequest = new Request();
        rootRequest.setMethod("GET");
        rootRequest.setUri("/");
        Route rootRoute = new Route(rootRequest);

        assertTrue(rootRoute.isGetRequest());
        assertTrue(rootRoute.isRootRequest());
        assertTrue(rootRoute.isOkStatus());
        assertTrue(rootRoute.requiresDirectoryContentsBody());
    }

    @Test
    public void determinesRouteForGetFileRequest() {
        Request getFileRequest = new Request();
        getFileRequest.setMethod("GET");
        getFileRequest.setUri("/file1");
        Route getFileRoute = new Route(getFileRequest);

        assertTrue(getFileRoute.isGetRequest());
        assertTrue(getFileRoute.isAvailableResourceRequest());
        assertTrue(getFileRoute.isOkStatus());
        assertTrue(getFileRoute.requiresFileContentsBody());
    }

    @Test
    public void determinesRouteForNotAllowedFileRequest() {
        Request notAllowedFileRequest = new Request();
        notAllowedFileRequest.setMethod("PUT");
        notAllowedFileRequest.setUri("/file1");
        Route notAllowedFileRoute = new Route(notAllowedFileRequest);

        assertTrue(notAllowedFileRoute.isPutRequest());
        assertTrue(notAllowedFileRoute.isAvailableResourceRequest());
        assertTrue(notAllowedFileRoute.isNotAllowedStatus());
    }

    @Test
    public void determinesRouteForGetFormRequest() {
        Request getFormRequest = new Request();
        getFormRequest.setMethod("GET");
        getFormRequest.setUri("/form");
        Route getFormRoute = new Route(getFormRequest);

        assertTrue(getFormRoute.isGetRequest());
        assertTrue(getFormRoute.isFormRequest());
        assertTrue(getFormRoute.isOkStatus());
        assertTrue(getFormRoute.requiresFileContentsBody());
    }

    @Test
    public void determinesRouteForPutFormRequest() {
        Request putFormRequest = new Request();
        putFormRequest.setMethod("PUT");
        putFormRequest.setUri("/form");
        Route putFormRoute = new Route(putFormRequest);

        assertTrue(putFormRoute.isPutRequest());
        assertTrue(putFormRoute.isFormRequest());
        assertTrue(putFormRoute.isOkStatus());
        assertTrue(putFormRoute.requiresFormEdit());
    }

    @Test
    public void determinesRouteForDeleteFormRequest() {
        Request deleteFormRequest = new Request();
        deleteFormRequest.setMethod("DELETE");
        deleteFormRequest.setUri("/form");
        Route deleteFormRoute = new Route(deleteFormRequest);

        assertTrue(deleteFormRoute.isDeleteRequest());
        assertTrue(deleteFormRoute.isFormRequest());
        assertTrue(deleteFormRoute.isOkStatus());
        assertTrue(deleteFormRoute.requiresFormDelete());
    }

    @Test
    public void determinesRouteForGetParametersRequest() {
        Request getParametersRequest = new Request();
        getParametersRequest.setMethod("GET");
        getParametersRequest.setUri("/parameters?query=true");
        Route getParametersRoute = new Route(getParametersRequest);

        assertTrue(getParametersRoute.isGetRequest());
        assertTrue(getParametersRoute.isParametersRequest());
        assertTrue(getParametersRoute.isOkStatus());
        assertTrue(getParametersRoute.requiresParametersBody());
    }

    @Test
    public void determinesRouteForGetPartialContentRequest() {
        Request getPartialContentRequest = new Request();
        getPartialContentRequest.setMethod("GET");
        getPartialContentRequest.setUri("/partial_content.txt");
        getPartialContentRequest.setRange("0-4");
        Route getPartialContentRoute = new Route(getPartialContentRequest);

        assertTrue(getPartialContentRoute.isGetRequest());
        assertTrue(getPartialContentRoute.isPartialContentRequest());
        assertTrue(getPartialContentRoute.isPartialContentStatus());
        assertTrue(getPartialContentRoute.requiresPartialContentBody());
    }

    @Test
    public void determinesRouteForGetPatchContentRequest() {
        Request getPatchContentRequest = new Request();
        getPatchContentRequest.setMethod("GET");
        getPatchContentRequest.setUri("/patch-content.txt");
        Route getPatchContentRoute = new Route(getPatchContentRequest);

        assertTrue(getPatchContentRoute.isGetRequest());
        assertTrue(getPatchContentRoute.isPatchContentRequest());
        assertTrue(getPatchContentRoute.isOkStatus());
        assertTrue(getPatchContentRoute.requiresFileContentsBody());
    }

    @Test
    public void determinesRouteForPatchPatchContentRequest() {
        Request patchPatchContentRequest = new Request();
        patchPatchContentRequest.setMethod("PATCH");
        patchPatchContentRequest.setUri("/patch-content.txt");
        Route patchPatchContentRoute = new Route(patchPatchContentRequest);

        assertTrue(patchPatchContentRoute.isPatchRequest());
        assertTrue(patchPatchContentRoute.isPatchContentRequest());
        assertTrue(patchPatchContentRoute.isNoContentStatus());
        assertTrue(patchPatchContentRoute.requiresPatchContentPatch());
    }

    @Test
    public void determinesRouteForGetRedirectRequest() {
        Request redirectRequest = new Request();
        redirectRequest.setMethod("GET");
        redirectRequest.setUri("/redirect");
        Route redirectRoute = new Route(redirectRequest);

        assertTrue(redirectRoute.isGetRequest());
        assertTrue(redirectRoute.isRedirectRequest());
        assertTrue(redirectRoute.isRedirectStatus());
        assertTrue(redirectRoute.requiresLocationHeader());
    }

    @Test
    public void determinesRouteForOptionsMethodOptionsRequest() {
        Request optionsMethodOptionsRequest = new Request();
        optionsMethodOptionsRequest.setMethod("OPTIONS");
        optionsMethodOptionsRequest.setUri("/method_options");
        Route optionsMethodOptionsRoute = new Route(optionsMethodOptionsRequest);

        assertTrue(optionsMethodOptionsRoute.isOptionsRequest());
        assertTrue(optionsMethodOptionsRoute.isMethodOptionsRequest());
        assertTrue(optionsMethodOptionsRoute.isOkStatus());
        assertTrue(optionsMethodOptionsRoute.requiresMethodOptionsHeader());
    }

    @Test
    public void determinesRouteForUnauthorizedLogsRequest() {
        Request unauthorizedLogsRequest = new Request();
        unauthorizedLogsRequest.setMethod("GET");
        unauthorizedLogsRequest.setUri("/logs");
        Route unauthorizedLogsRoute = new Route(unauthorizedLogsRequest);

        assertTrue(unauthorizedLogsRoute.isGetRequest());
        assertTrue(unauthorizedLogsRoute.isLogsRequest());
        assertTrue(unauthorizedLogsRoute.isUnauthorizedStatus());
        assertTrue(unauthorizedLogsRoute.requiresAuthenticationRequiredBody());
    }

    @Test
    public void determinesRouteForAuthorizedLogsRequest() {
        Request authorizedLogsRequest = new Request();
        authorizedLogsRequest.setMethod("GET");
        authorizedLogsRequest.setUri("/logs");
        authorizedLogsRequest.setAuthorization();
        Route authorizedLogsRoute = new Route(authorizedLogsRequest);

        assertTrue(authorizedLogsRoute.isGetRequest());
        assertTrue(authorizedLogsRoute.isLogsRequest());
        assertTrue(authorizedLogsRoute.isAuthorized());
        assertTrue(authorizedLogsRoute.isOkStatus());
        assertTrue(authorizedLogsRoute.requiresLogsBody());
    }
}