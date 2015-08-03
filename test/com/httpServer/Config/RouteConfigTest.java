package com.httpServer.Config;

import com.httpServer.Handlers.*;
import com.httpServer.RequestAdapter.Request;
import com.httpServer.Router.RouteRegistrar;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RouteConfigTest {

    @Before
    public void clearRoutes() {
        RouteRegistrar.clearRoutes();
        ServerConfig.configureServer(new String[0]);
        RouteConfig.setupRoutes();
    }

    @Test
    public void routeConfigExists() {
        assertNotNull(new RouteConfig());
    }

    @Test
    public void addsRootRoute() {
        Request rootRequest = new Request();
        rootRequest.setMethod("GET");
        rootRequest.setUri("/");
        assertEquals(RootHandler.class, RouteRegistrar.getRoute(rootRequest).getClass());
    }

    @Test
    public void addsMethodOptionsRoute() {
        Request methodOptionsRequest = new Request();
        methodOptionsRequest.setMethod("OPTIONS");
        methodOptionsRequest.setUri("/method_options");
        assertEquals(MethodOptionsHandler.class, RouteRegistrar.getRoute(methodOptionsRequest).getClass());
    }

    @Test
    public void addsRedirectRoute() {
        Request redirectRequest = new Request();
        redirectRequest.setMethod("GET");
        redirectRequest.setUri("/redirect");
        assertEquals(RedirectHandler.class, RouteRegistrar.getRoute(redirectRequest).getClass());
    }

    @Test
    public void addsLogsRoute() {
        Request logsRequest = new Request();
        logsRequest.setMethod("GET");
        logsRequest.setUri("/logs");
        assertEquals(LogsHandler.class, RouteRegistrar.getRoute(logsRequest).getClass());
    }

    @Test
    public void addsParametersRoute() {
        Request parametersRequest = new Request();
        parametersRequest.setMethod("GET");
        parametersRequest.setUri("/parameters");
        assertEquals(ParametersHandler.class, RouteRegistrar.getRoute(parametersRequest).getClass());
    }

    @Test
    public void addsGetFormRoute() {
        Request getFormRequest = new Request();
        getFormRequest.setMethod("GET");
        getFormRequest.setUri("/form");
        assertEquals(ReadFileHandler.class, RouteRegistrar.getRoute(getFormRequest).getClass());
    }

    @Test
    public void addsPutFormRoute() {
        Request putFormRequest = new Request();
        putFormRequest.setMethod("PUT");
        putFormRequest.setUri("/form");
        assertEquals(EditFileHandler.class, RouteRegistrar.getRoute(putFormRequest).getClass());
    }

    @Test
    public void addsPostFormRoute() {
        Request postFormRequest = new Request();
        postFormRequest.setMethod("POST");
        postFormRequest.setUri("/form");
        assertEquals(EditFileHandler.class, RouteRegistrar.getRoute(postFormRequest).getClass());
    }

    @Test
    public void addsDeleteFormRoute() {
        Request deleteFormRequest = new Request();
        deleteFormRequest.setMethod("DELETE");
        deleteFormRequest.setUri("/form");
        assertEquals(EditFileHandler.class, RouteRegistrar.getRoute(deleteFormRequest).getClass());
    }

    @Test
    public void addsGetFile1Route() {
        Request getFile1Request = new Request();
        getFile1Request.setMethod("GET");
        getFile1Request.setUri("/file1");
        assertEquals(ReadFileHandler.class, RouteRegistrar.getRoute(getFile1Request).getClass());
    }

    @Test
    public void addsGetFile2Route() {
        Request getFile2Request = new Request();
        getFile2Request.setMethod("GET");
        getFile2Request.setUri("/file2");
        assertEquals(ReadFileHandler.class, RouteRegistrar.getRoute(getFile2Request).getClass());
    }

    @Test
    public void addsGetImageGifRoute() {
        Request getImageGifRequest = new Request();
        getImageGifRequest.setMethod("GET");
        getImageGifRequest.setUri("/image.gif");
        assertEquals(ReadFileHandler.class, RouteRegistrar.getRoute(getImageGifRequest).getClass());
    }

    @Test
    public void addsGetImageJpegRoute() {
        Request getImageJpegRequest = new Request();
        getImageJpegRequest.setMethod("GET");
        getImageJpegRequest.setUri("/image.jpeg");
        assertEquals(ReadFileHandler.class, RouteRegistrar.getRoute(getImageJpegRequest).getClass());
    }

    @Test
    public void addsGetImagePngRoute() {
        Request getImagePngRequest = new Request();
        getImagePngRequest.setMethod("GET");
        getImagePngRequest.setUri("/image.png");
        assertEquals(ReadFileHandler.class, RouteRegistrar.getRoute(getImagePngRequest).getClass());
    }

    @Test
    public void addsGetPartialContentRoute() {
        Request getPartialContentRequest = new Request();
        getPartialContentRequest.setMethod("GET");
        getPartialContentRequest.setUri("/partial_content.txt");
        assertEquals(PartialContentHandler.class, RouteRegistrar.getRoute(getPartialContentRequest).getClass());
    }

    @Test
    public void addsGetPatchContentRoute() {
        Request getPatchContentRequest = new Request();
        getPatchContentRequest.setMethod("GET");
        getPatchContentRequest.setUri("/patch-content.txt");
        assertEquals(ReadFileHandler.class, RouteRegistrar.getRoute(getPatchContentRequest).getClass());
    }

    @Test
    public void addsPatchPatchContentRoute() {
        Request patchPatchContentRequest = new Request();
        patchPatchContentRequest.setMethod("PATCH");
        patchPatchContentRequest.setUri("/patch-content.txt");
        assertEquals(PatchContentHandler.class, RouteRegistrar.getRoute(patchPatchContentRequest).getClass());
    }

    @Test
    public void addsGetTextFileRoute() {
        Request getTextFileRequest = new Request();
        getTextFileRequest.setMethod("GET");
        getTextFileRequest.setUri("/text-file.txt");
        assertEquals(ReadFileHandler.class, RouteRegistrar.getRoute(getTextFileRequest).getClass());
    }
}