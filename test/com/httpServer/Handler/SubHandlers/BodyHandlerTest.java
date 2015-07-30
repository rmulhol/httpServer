package com.httpServer.Handler.SubHandlers;

import com.httpServer.Handler.ResponseContents.ResponseBody;
import com.httpServer.Handler.Route.Route;
import com.httpServer.RequestAdapter.Request;
import com.httpServer.ResponseAdapter.Response;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class BodyHandlerTest {

    @Test
    public void setsBodyForRootWithDirectoryContents() {
        Request rootRequest = new Request();
        rootRequest.setMethod("GET");
        rootRequest.setUri("/");
        Route rootRoute = new Route(rootRequest);

        Response rootResponse = new Response();

        new BodyHandler(rootRoute, rootResponse).setBody();

        assertArrayEquals(ResponseBody.publicDirectoryContents(), rootResponse.getBody());
    }

    @Test
    public void setsBodyForFileWithFileContents() {
        Request file1Request = new Request();
        file1Request.setMethod("GET");
        file1Request.setUri("/file1");
        Route file1Route = new Route(file1Request);

        Response file1Response = new Response();

        new BodyHandler(file1Route, file1Response).setBody();

        assertArrayEquals(ResponseBody.fileContents("/file1"), file1Response.getBody());
    }

    @Test
    public void setsBodyForUnauthorizedLogsRequest() {
        Request unauthorizedLogsRequest = new Request();
        unauthorizedLogsRequest.setMethod("GET");
        unauthorizedLogsRequest.setUri("/logs");
        Route unauthorizedLogsRoute = new Route(unauthorizedLogsRequest);

        Response unauthorizedResponse = new Response();

        new BodyHandler(unauthorizedLogsRoute, unauthorizedResponse).setBody();

        assertArrayEquals(ResponseBody.authenticationRequired(), unauthorizedResponse.getBody());
    }

    @Test
    public void setsBodyForParametersRequest() {
        Request parametersRequest = new Request();
        parametersRequest.setMethod("GET");
        parametersRequest.setUri("/parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C" +
                "%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F" +
                "&variable_2=stuff");
        Route parametersRoute = new Route(parametersRequest);

        Response parametersResponse = new Response();

        new BodyHandler(parametersRoute, parametersResponse).setBody();

        assertArrayEquals(ResponseBody.parameters(parametersRequest.getUri()), parametersResponse.getBody());
    }

    @Test
    public void setsBodyForPartialContentRequest() {
        Request partialContentRequest = new Request();
        partialContentRequest.setMethod("GET");
        partialContentRequest.setUri("/partial_content.txt");
        partialContentRequest.setRange("0-4");
        Route partialContentRoute = new Route(partialContentRequest);

        Response partialContentResponse = new Response();

        new BodyHandler(partialContentRoute, partialContentResponse).setBody();

        assertArrayEquals(ResponseBody.partialContent("0-4"), partialContentResponse.getBody());
    }

    @Test
    public void setsBodyForRequestWithoutBody() {
        Request noBodyRequiredRequest = new Request();
        noBodyRequiredRequest.setMethod("GET");
        noBodyRequiredRequest.setUri("/redirect");
        Route noBodyRequiredRoute = new Route(noBodyRequiredRequest);

        Response noBodyResponse = new Response();

        new BodyHandler(noBodyRequiredRoute, noBodyResponse).setBody();

        assertArrayEquals(ResponseBody.noBody(), noBodyResponse.getBody());
    }
}