package com.httpServer.Handler;

import com.httpServer.Handler.FileIO.MyFileReader;
import com.httpServer.Handler.FileIO.MyFileWriter;
import com.httpServer.RequestAdapter.Request;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class HandlerTest {

    @Test
    public void getResponseDelivers200ForRootRequest() {
        Request rootRequest = new Request();
        rootRequest.setMethod("GET");
        rootRequest.setUri("/");

        String directoryContents = "<p><a href='/file1'>file1</a></p>\r\n" +
                "<p><a href='/file2'>file2</a></p>\r\n" +
                "<p><a href='/form'>form</a></p>\r\n" +
                "<p><a href='/image.gif'>image.gif</a></p>\r\n" +
                "<p><a href='/image.jpeg'>image.jpeg</a></p>\r\n" +
                "<p><a href='/image.png'>image.png</a></p>\r\n" +
                "<p><a href='/partial_content.txt'>partial_content.txt</a></p>\r\n" +
                "<p><a href='/patch-content.txt'>patch-content.txt</a></p>\r\n" +
                "<p><a href='/text-file.txt'>text-file.txt</a></p>\r\n";


        assertArrayEquals("200 OK\r\n".getBytes(), Handler.getResponse(rootRequest).getStatus());
        assertArrayEquals("\r\n".getBytes(), Handler.getResponse(rootRequest).getHeader());
        assertArrayEquals(directoryContents.getBytes(), Handler.getResponse(rootRequest).getBody());
    }

    @Test
    public void getResponseDelivers200ForMethodOptionsRequest() {
        Request methodOptionsRequest = new Request();
        methodOptionsRequest.setMethod("OPTIONS");
        methodOptionsRequest.setUri("/method_options");

        assertArrayEquals("200 OK\r\n".getBytes(), Handler.getResponse(methodOptionsRequest).getStatus());
        assertArrayEquals("Allow: GET,HEAD,POST,OPTIONS,PUT\r\n".getBytes(), Handler.getResponse(methodOptionsRequest).getHeader());
        assertArrayEquals("".getBytes(), Handler.getResponse(methodOptionsRequest).getBody());
    }

    @Test
    public void getResponseDelivers200ForParametersRequest() {
        Request parametersRequest = new Request();
        parametersRequest.setMethod("GET");
        parametersRequest.setUri( "/parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C" +
                "%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F" +
                "&variable_2=stuff");

        byte[] responseBody = ("/parameters?variable_1 = Operators <, >, =, !=; +, -, *, &, @, #, $, [, ]: " +
                "\"is that all\"?&variable_2 = stuff").getBytes();

        assertArrayEquals("200 OK\r\n".getBytes(), Handler.getResponse(parametersRequest).getStatus());
        assertArrayEquals("\r\n".getBytes(), Handler.getResponse(parametersRequest).getHeader());
        assertArrayEquals(responseBody, Handler.getResponse(parametersRequest).getBody());
    }

    @Test
    public void getResponseDelivers200ForGetFormRequest() {
        Request getFormRequest = new Request();
        getFormRequest.setMethod("GET");
        getFormRequest.setUri("/form");

        assertArrayEquals("200 OK\r\n".getBytes(), Handler.getResponse(getFormRequest).getStatus());
        assertArrayEquals("\r\n".getBytes(), Handler.getResponse(getFormRequest).getHeader());
        assertArrayEquals("\"My\"=\"Data\"\n".getBytes(), Handler.getResponse(getFormRequest).getBody());
    }

    @Test
    public void getResponseDelivers200ForPutFormRequest() {
        Request putFormRequest = new Request();
        putFormRequest.setMethod("PUT");
        putFormRequest.setUri("/form");
        putFormRequest.setBody("\"My\"=\"Data\"");

        assertArrayEquals("200 OK\r\n".getBytes(), Handler.getResponse(putFormRequest).getStatus());
        assertArrayEquals("\r\n".getBytes(), Handler.getResponse(putFormRequest).getHeader());
        assertArrayEquals("".getBytes(), Handler.getResponse(putFormRequest).getBody());
    }

    @Test
    public void getResponseDelivers200ForPostFormRequest() {
        Request postFormRequest = new Request();
        postFormRequest.setMethod("POST");
        postFormRequest.setUri("/form");
        postFormRequest.setBody("\"My\"=\"Data\"");

        assertArrayEquals("200 OK\r\n".getBytes(), Handler.getResponse(postFormRequest).getStatus());
        assertArrayEquals("\r\n".getBytes(), Handler.getResponse(postFormRequest).getHeader());
        assertArrayEquals("".getBytes(), Handler.getResponse(postFormRequest).getBody());
    }

    @Test
    public void getResponseDelivers200ForDeleteFormRequest() {
        Request deleteFormRequest = new Request();
        deleteFormRequest.setMethod("DELETE");
        deleteFormRequest.setUri("/form");

        assertArrayEquals("200 OK\r\n".getBytes(), Handler.getResponse(deleteFormRequest).getStatus());
        assertArrayEquals("\r\n".getBytes(), Handler.getResponse(deleteFormRequest).getHeader());
        assertArrayEquals("".getBytes(), Handler.getResponse(deleteFormRequest).getBody());

        MyFileWriter.editFile("/public/form", "\"My\"=\"Data\"");
        assertArrayEquals("\"My\"=\"Data\"\n".getBytes(), MyFileReader.readFileContents("/form"));
    }

    @Test
    public void getResponseModifiesFormForPutFormRequest() {
        Request putFormRequest = new Request();
        putFormRequest.setMethod("PUT");
        putFormRequest.setUri("/form");
        putFormRequest.setBody("test data");

        assertArrayEquals("\"My\"=\"Data\"\n".getBytes(), MyFileReader.readFileContents("/form"));
        Handler.getResponse(putFormRequest);
        assertArrayEquals("test data\n".getBytes(), MyFileReader.readFileContents("/form"));
        MyFileWriter.editFile("/public/form", "\"My\"=\"Data\"");
        assertArrayEquals("\"My\"=\"Data\"\n".getBytes(), MyFileReader.readFileContents("/form"));
    }

    @Test
    public void getResponseModifiesFormForPostFormRequest() {
        Request postFormRequest = new Request();
        postFormRequest.setMethod("POST");
        postFormRequest.setUri("/form");
        postFormRequest.setBody("test data");

        assertArrayEquals("\"My\"=\"Data\"\n".getBytes(), MyFileReader.readFileContents("/form"));
        Handler.getResponse(postFormRequest);
        assertArrayEquals("test data\n".getBytes(), MyFileReader.readFileContents("/form"));
        MyFileWriter.editFile("/public/form", "\"My\"=\"Data\"");
        assertArrayEquals("\"My\"=\"Data\"\n".getBytes(), MyFileReader.readFileContents("/form"));
    }

    @Test
    public void getResponseDeletesFormsContentsForDeleteFormRequest() {
        Request deleteFormRequest = new Request();
        deleteFormRequest.setMethod("DELETE");
        deleteFormRequest.setUri("/form");

        assertArrayEquals("\"My\"=\"Data\"\n".getBytes(), MyFileReader.readFileContents("/form"));
        Handler.getResponse(deleteFormRequest);
        assertArrayEquals("\n".getBytes(), MyFileReader.readFileContents("/form"));
        MyFileWriter.editFile("/public/form", "\"My\"=\"Data\"");
        assertArrayEquals("\"My\"=\"Data\"\n".getBytes(), MyFileReader.readFileContents("/form"));
    }

    @Test
    public void getResponseDelivers204ForPatchRequest() {
        Request patchRequest = new Request();
        patchRequest.setMethod("PATCH");
        patchRequest.setUri("/patch-content.txt");
        patchRequest.setBody("default content");

        assertArrayEquals("204 No Content\r\n".getBytes(), Handler.getResponse(patchRequest).getStatus());
        assertArrayEquals("\r\n".getBytes(), Handler.getResponse(patchRequest).getHeader());
        assertArrayEquals("".getBytes(), Handler.getResponse(patchRequest).getBody());
    }

    @Test
    public void getResponseModifiesPatchContentForPatchRequest() {
        Request patchRequest = new Request();
        patchRequest.setMethod("PATCH");
        patchRequest.setUri("/patch-content.txt");
        patchRequest.setBody("test content");

        assertArrayEquals("default content\n".getBytes(), MyFileReader.readFileContents("/patch-content.txt"));
        Handler.getResponse(patchRequest);
        assertArrayEquals("test content\n".getBytes(), MyFileReader.readFileContents("/patch-content.txt"));
        MyFileWriter.editFile("/public/patch-content.txt", "default content");
        assertArrayEquals("default content\n".getBytes(), MyFileReader.readFileContents("/patch-content.txt"));
    }

    @Test
    public void getResponseDelivers206ForPartialContentRequest() {
        Request partialContentRequest = new Request();
        partialContentRequest.setMethod("GET");
        partialContentRequest.setUri("/partial_content.txt");
        partialContentRequest.setRange("0-4");

        assertArrayEquals("206 Partial Content\r\n".getBytes(), Handler.getResponse(partialContentRequest).getStatus());
        assertArrayEquals("\r\n".getBytes(), Handler.getResponse(partialContentRequest).getHeader());
        assertArrayEquals("This ".getBytes(), Handler.getResponse(partialContentRequest).getBody());
    }

    @Test
    public void getResponseDelivers302ForRedirectRequest() {
        Request redirectRequest = new Request();
        redirectRequest.setMethod("GET");
        redirectRequest.setUri("/redirect");

        assertArrayEquals("302 Found\r\n".getBytes(), Handler.getResponse(redirectRequest).getStatus());
        assertArrayEquals("Location: http://localhost:5000/\r\n".getBytes(), Handler.getResponse(redirectRequest).getHeader());
        assertArrayEquals("".getBytes(), Handler.getResponse(redirectRequest).getBody());
    }

    @Test
    public void getResponseDelivers401ForUnauthorizedLogsRequest() {
        Request unauthorizedLogsRequest = new Request();
        unauthorizedLogsRequest.setMethod("GET");
        unauthorizedLogsRequest.setUri("/logs");

        assertArrayEquals("401 Unauthorized\r\n".getBytes(), Handler.getResponse(unauthorizedLogsRequest).getStatus());
        assertArrayEquals("\r\n".getBytes(), Handler.getResponse(unauthorizedLogsRequest).getHeader());
        assertArrayEquals("Authentication required".getBytes(), Handler.getResponse(unauthorizedLogsRequest).getBody());
    }

    @Test
    public void getResponseDelivers404ForUnrecognizedRequest() {
        Request unrecognizedRequest = new Request();
        unrecognizedRequest.setMethod("GET");
        unrecognizedRequest.setUri("/foobar");

        assertArrayEquals("404 Not Found\r\n".getBytes(), Handler.getResponse(unrecognizedRequest).getStatus());
        assertArrayEquals("\r\n".getBytes(), Handler.getResponse(unrecognizedRequest).getHeader());
        assertArrayEquals("".getBytes(), Handler.getResponse(unrecognizedRequest).getBody());
    }

    @Test
    public void getResponseDelivers405ForNotAllowedRequest() {
        Request notAllowedRequest = new Request();
        notAllowedRequest.setMethod("PUT");
        notAllowedRequest.setUri("/file1");

        assertArrayEquals("405 Method Not Allowed\r\n".getBytes(), Handler.getResponse(notAllowedRequest).getStatus());
        assertArrayEquals("\r\n".getBytes(), Handler.getResponse(notAllowedRequest).getHeader());
        assertArrayEquals("".getBytes(), Handler.getResponse(notAllowedRequest).getBody());
    }
}