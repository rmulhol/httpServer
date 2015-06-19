package com.httpServer.Handler.SubHandlers;

import com.httpServer.Handler.ResponseContents.ResponseBody;
import com.httpServer.Handler.Route.Route;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertArrayEquals;

public class BodyHandlerTest {

    @Test
    public void setsBodyForRootWithDirectoryContents() {
        HashMap<String, String> rootRequest = new HashMap<String, String>();
        rootRequest.put("method", "GET");
        rootRequest.put("uri", "/");
        Route rootRoute = new Route(rootRequest);

        HashMap<String, byte[]> rootResponse = new HashMap<String, byte[]>();

        new BodyHandler(rootRoute, rootResponse).setBody();

        assertArrayEquals(ResponseBody.publicDirectoryContents(), rootResponse.get("body"));
    }

    @Test
    public void setsBodyForFileWithFileContents() {
        HashMap<String, String> file1Request = new HashMap<String, String>();
        file1Request.put("method", "GET");
        file1Request.put("uri", "/file1");
        Route file1Route = new Route(file1Request);

        HashMap<String, byte[]> file1Response = new HashMap<String, byte[]>();

        new BodyHandler(file1Route, file1Response).setBody();

        assertArrayEquals(ResponseBody.fileContents("/file1"), file1Response.get("body"));
    }

    @Test
    public void setsBodyForUnauthorizedLogsRequest() {
        HashMap<String, String> unauthorizedLogsRequest = new HashMap<String, String>();
        unauthorizedLogsRequest.put("method", "GET");
        unauthorizedLogsRequest.put("uri", "/logs");
        Route unauthorizedLogsRoute = new Route(unauthorizedLogsRequest);

        HashMap<String, byte[]> unauthorizedResponse = new HashMap<String, byte[]>();

        new BodyHandler(unauthorizedLogsRoute, unauthorizedResponse).setBody();

        assertArrayEquals(ResponseBody.authenticationRequired(), unauthorizedResponse.get("body"));
    }

    @Test
    public void setsBodyForParametersRequest() {
        HashMap<String, String> parametersRequest = new HashMap<String, String>();
        parametersRequest.put("method", "GET");
        parametersRequest.put("uri", "/parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C" +
                "%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F" +
                "&variable_2=stuff");
        Route parametersRoute = new Route(parametersRequest);

        HashMap<String, byte[]> parametersResponse = new HashMap<String, byte[]>();

        new BodyHandler(parametersRoute, parametersResponse).setBody();

        assertArrayEquals(ResponseBody.parameters(parametersRequest.get("uri")), parametersResponse.get("body"));
    }

    @Test
    public void setsBodyForPartialContentRequest() {
        HashMap<String, String> partialContentRequest  = new HashMap<String, String>();
        partialContentRequest.put("method", "GET");
        partialContentRequest.put("uri", "/partial_content.txt");
        partialContentRequest.put("range", "0-4");
        Route partialContentRoute = new Route(partialContentRequest);

        HashMap<String, byte[]> partialContentResponse = new HashMap<String, byte[]>();

        new BodyHandler(partialContentRoute, partialContentResponse).setBody();

        assertArrayEquals(ResponseBody.partialContent("0-4"), partialContentResponse.get("body"));
    }

    @Test
    public void setsBodyForRequestWithoutBody() {
        HashMap<String, String> noBodyRequiredRequest = new HashMap<String, String>();
        noBodyRequiredRequest.put("method", "GET");
        noBodyRequiredRequest.put("uri", "/redirect");
        Route noBodyRequiredRoute = new Route(noBodyRequiredRequest);

        HashMap<String, byte[]> noBodyResponse = new HashMap<String, byte[]>();

        new BodyHandler(noBodyRequiredRoute, noBodyResponse).setBody();

        assertArrayEquals(ResponseBody.noBody(), noBodyResponse.get("body"));
    }
}