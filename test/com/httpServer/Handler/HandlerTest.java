package com.httpServer.Handler;

import com.httpServer.Handler.FileIO.MyFileReader;
import com.httpServer.Handler.FileIO.MyFileWriter;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class HandlerTest {

    @Test
    public void getResponseDelivers200ForRootRequest() {
        HashMap<String, String> rootRequest = new HashMap<String, String>();
        rootRequest.put("method", "GET");
        rootRequest.put("uri", "/");

        HashMap<String, byte[]> rootResponse = new HashMap<String, byte[]>();
        rootResponse.put("status", "HTTP/1.1 200 OK\r\n".getBytes());
        rootResponse.put("header", "\r\n".getBytes());

        String directoryContents = "<p><a href='/file1'>file1</a></p>\r\n" +
                "<p><a href='/file2'>file2</a></p>\r\n" +
                "<p><a href='/form'>form</a></p>\r\n" +
                "<p><a href='/image.gif'>image.gif</a></p>\r\n" +
                "<p><a href='/image.jpeg'>image.jpeg</a></p>\r\n" +
                "<p><a href='/image.png'>image.png</a></p>\r\n" +
                "<p><a href='/partial_content.txt'>partial_content.txt</a></p>\r\n" +
                "<p><a href='/patch-content.txt'>patch-content.txt</a></p>\r\n" +
                "<p><a href='/text-file.txt'>text-file.txt</a></p>\r\n";

        rootResponse.put("body", directoryContents.getBytes());

        assertArrayEquals(rootResponse.get("status"), Handler.getResponse(rootRequest).get("status"));
        assertArrayEquals(rootResponse.get("header"), Handler.getResponse(rootRequest).get("header"));
        assertArrayEquals(rootResponse.get("body"), Handler.getResponse(rootRequest).get("body"));
    }

    @Test
    public void getResponseDelivers200ForMethodOptionsRequest() {
        HashMap<String, String> methodOptionsRequest = new HashMap<String, String>();
        methodOptionsRequest.put("method", "OPTIONS");
        methodOptionsRequest.put("uri", "/method_options");

        HashMap<String, byte[]> methodOptionsResponse = new HashMap<String, byte[]>();
        methodOptionsResponse.put("status", "HTTP/1.1 200 OK\r\n".getBytes());
        methodOptionsResponse.put("header", "Allow: GET,HEAD,POST,OPTIONS,PUT\r\n".getBytes());
        methodOptionsResponse.put("body", "".getBytes());

        assertArrayEquals(methodOptionsResponse.get("status"), Handler.getResponse(methodOptionsRequest).get("status"));
        assertArrayEquals(methodOptionsResponse.get("header"), Handler.getResponse(methodOptionsRequest).get("header"));
        assertArrayEquals(methodOptionsResponse.get("body"), Handler.getResponse(methodOptionsRequest).get("body"));
    }

    @Test
    public void getResponseDelivers200ForParametersRequest() {
        HashMap<String, String> parametersRequest = new HashMap<String, String>();
        parametersRequest.put("method", "GET");
        parametersRequest.put("uri", "/parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C" +
                "%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F" +
                "&variable_2=stuff");

        HashMap<String, byte[]> parametersResponse = new HashMap<String, byte[]>();
        parametersResponse.put("status", "HTTP/1.1 200 OK\r\n".getBytes());
        parametersResponse.put("header", "\r\n".getBytes());
        parametersResponse.put("body", ("/parameters?variable_1 = Operators <, >, =, !=; +, -, *, &, @, #, $, [, ]: " +
                "\"is that all\"?&variable_2 = stuff").getBytes());

        assertArrayEquals(parametersResponse.get("status"), Handler.getResponse(parametersRequest).get("status"));
        assertArrayEquals(parametersResponse.get("header"), Handler.getResponse(parametersRequest).get("header"));
        assertArrayEquals(parametersResponse.get("body"), Handler.getResponse(parametersRequest).get("body"));
    }

    @Test
    public void getResponseDelivers200ForGetFormRequest() {
        HashMap<String, String> getFormRequest = new HashMap<String, String>();
        getFormRequest.put("method", "GET");
        getFormRequest.put("uri", "/form");

        HashMap<String, byte[]> getFormResponse = new HashMap<String, byte[]>();
        getFormResponse.put("status", "HTTP/1.1 200 OK\r\n".getBytes());
        getFormResponse.put("header", "\r\n".getBytes());
        getFormResponse.put("body", "\"My\"=\"Data\"\n\n".getBytes());

        assertArrayEquals(getFormResponse.get("status"), Handler.getResponse(getFormRequest).get("status"));
        assertArrayEquals(getFormResponse.get("header"), Handler.getResponse(getFormRequest).get("header"));
        assertArrayEquals(getFormResponse.get("body"), Handler.getResponse(getFormRequest).get("body"));
    }

    @Test
    public void getResponseDelivers200ForPutFormRequest() {
        HashMap<String, String> putFormRequest = new HashMap<String, String>();
        putFormRequest.put("method", "PUT");
        putFormRequest.put("uri", "/form");
        putFormRequest.put("body", "\"My\"=\"Data\"\n");

        HashMap<String, byte[]> putFormResponse = new HashMap<String, byte[]>();
        putFormResponse.put("status", "HTTP/1.1 200 OK\r\n".getBytes());
        putFormResponse.put("header", "\r\n".getBytes());
        putFormResponse.put("body", "".getBytes());

        assertArrayEquals(putFormResponse.get("status"), Handler.getResponse(putFormRequest).get("status"));
        assertArrayEquals(putFormResponse.get("header"), Handler.getResponse(putFormRequest).get("header"));
        assertArrayEquals(putFormResponse.get("body"), Handler.getResponse(putFormRequest).get("body"));
    }

    @Test
    public void getResponseDelivers200ForPostFormRequest() {
        HashMap<String, String> postFormRequest = new HashMap<String, String>();
        postFormRequest.put("method", "POST");
        postFormRequest.put("uri", "/form");
        postFormRequest.put("body", "\"My\"=\"Data\"\n");

        HashMap<String, byte[]> postFormResponse = new HashMap<String, byte[]>();
        postFormResponse.put("status", "HTTP/1.1 200 OK\r\n".getBytes());
        postFormResponse.put("header", "\r\n".getBytes());
        postFormResponse.put("body", "".getBytes());

        assertArrayEquals(postFormResponse.get("status"), Handler.getResponse(postFormRequest).get("status"));
        assertArrayEquals(postFormResponse.get("header"), Handler.getResponse(postFormRequest).get("header"));
        assertArrayEquals(postFormResponse.get("body"), Handler.getResponse(postFormRequest).get("body"));
    }

    @Test
    public void getResponseDelivers200ForDeleteFormRequest() {
        HashMap<String, String> deleteFormRequest = new HashMap<String, String>();
        deleteFormRequest.put("method", "DELETE");
        deleteFormRequest.put("uri", "/form");

        HashMap<String, byte[]> deleteFormResponse = new HashMap<String, byte[]>();
        deleteFormResponse.put("status", "HTTP/1.1 200 OK\r\n".getBytes());
        deleteFormResponse.put("header", "\r\n".getBytes());
        deleteFormResponse.put("body", "".getBytes());

        assertArrayEquals(deleteFormResponse.get("status"), Handler.getResponse(deleteFormRequest).get("status"));
        assertArrayEquals(deleteFormResponse.get("header"), Handler.getResponse(deleteFormRequest).get("header"));
        assertArrayEquals(deleteFormResponse.get("body"), Handler.getResponse(deleteFormRequest).get("body"));

        MyFileWriter.editFile("/public/form", "\"My\"=\"Data\"\n");
        assertArrayEquals("\"My\"=\"Data\"\n\n".getBytes(), MyFileReader.readFileContents("/form"));
    }

    @Test
    public void getResponseModifiesFormForPutFormRequest() {
        HashMap<String, String> putFormRequest = new HashMap<String, String>();
        putFormRequest.put("method", "PUT");
        putFormRequest.put("uri", "/form");
        putFormRequest.put("body", "test data");

        assertArrayEquals("\"My\"=\"Data\"\n\n".getBytes(), MyFileReader.readFileContents("/form"));
        Handler.getResponse(putFormRequest);
        assertArrayEquals("test data\n".getBytes(), MyFileReader.readFileContents("/form"));
        MyFileWriter.editFile("/public/form", "\"My\"=\"Data\"\n");
        assertArrayEquals("\"My\"=\"Data\"\n\n".getBytes(), MyFileReader.readFileContents("/form"));
    }

    @Test
    public void getResponseModifiesFormForPostFormRequest() {
        HashMap<String, String> postFormRequest = new HashMap<String, String>();
        postFormRequest.put("method", "POST");
        postFormRequest.put("uri", "/form");
        postFormRequest.put("body", "test data");

        assertArrayEquals("\"My\"=\"Data\"\n\n".getBytes(), MyFileReader.readFileContents("/form"));
        Handler.getResponse(postFormRequest);
        assertArrayEquals("test data\n".getBytes(), MyFileReader.readFileContents("/form"));
        MyFileWriter.editFile("/public/form", "\"My\"=\"Data\"\n");
        assertArrayEquals("\"My\"=\"Data\"\n\n".getBytes(), MyFileReader.readFileContents("/form"));
    }

    @Test
    public void getResponseDeletesFormsContentsForDeleteFormRequest() {
        HashMap<String, String> deleteFormRequest = new HashMap<String, String>();
        deleteFormRequest.put("method", "DELETE");
        deleteFormRequest.put("uri", "/form");

        assertArrayEquals("\"My\"=\"Data\"\n\n".getBytes(), MyFileReader.readFileContents("/form"));
        Handler.getResponse(deleteFormRequest);
        assertArrayEquals("\n".getBytes(), MyFileReader.readFileContents("/form"));
        MyFileWriter.editFile("/public/form", "\"My\"=\"Data\"\n");
        assertArrayEquals("\"My\"=\"Data\"\n\n".getBytes(), MyFileReader.readFileContents("/form"));
    }

    @Test
    public void getResponseDelivers204ForPatchRequest() {
        HashMap<String, String> patchRequest = new HashMap<String, String>();
        patchRequest.put("method", "PATCH");
        patchRequest.put("uri", "/patch-content.txt");
        patchRequest.put("body", "default content\n");

        HashMap<String, byte[]> patchResponse = new HashMap<String, byte[]>();
        patchResponse.put("status", "HTTP/1.1 204 No Content\r\n".getBytes());
        patchResponse.put("header", "\r\n".getBytes());
        patchResponse.put("body", "".getBytes());

        assertArrayEquals(patchResponse.get("status"), Handler.getResponse(patchRequest).get("status"));
        assertArrayEquals(patchResponse.get("header"), Handler.getResponse(patchRequest).get("header"));
        assertArrayEquals(patchResponse.get("body"), Handler.getResponse(patchRequest).get("body"));
    }

    @Test
    public void getResponseModifiesPatchContentForPatchRequest() {
        HashMap<String, String> patchRequest = new HashMap<String, String>();
        patchRequest.put("method", "PATCH");
        patchRequest.put("uri", "/patch-content.txt");
        patchRequest.put("body", "test content");

        assertArrayEquals("default content\n\n".getBytes(), MyFileReader.readFileContents("/patch-content.txt"));
        Handler.getResponse(patchRequest);
        assertArrayEquals("test content\n".getBytes(), MyFileReader.readFileContents("/patch-content.txt"));
        MyFileWriter.editFile("/public/patch-content.txt", "default content\n");
        assertArrayEquals("default content\n\n".getBytes(), MyFileReader.readFileContents("/patch-content.txt"));
    }

    @Test
    public void getResponseDelivers206ForPartialContentRequest() {
        HashMap<String, String> partialContentRequest = new HashMap<String, String>();
        partialContentRequest.put("method", "GET");
        partialContentRequest.put("uri", "/partial_content.txt");
        partialContentRequest.put("range", "0-4");

        HashMap<String, byte[]> partialContentResponse = new HashMap<String, byte[]>();
        partialContentResponse.put("status", "HTTP/1.1 206 Partial Content\r\n".getBytes());
        partialContentResponse.put("header", "\r\n".getBytes());
        partialContentResponse.put("body", "This ".getBytes());

        assertArrayEquals(partialContentResponse.get("status"), Handler.getResponse(partialContentRequest).get("status"));
        assertArrayEquals(partialContentResponse.get("header"), Handler.getResponse(partialContentRequest).get("header"));
        assertArrayEquals(partialContentResponse.get("body"), Handler.getResponse(partialContentRequest).get("body"));
    }

    @Test
    public void getResponseDelivers302ForRedirectRequest() {
        HashMap<String, String> redirectRequest = new HashMap<String, String>();
        redirectRequest.put("method", "GET");
        redirectRequest.put("uri", "/redirect");

        HashMap<String, byte[]> redirectResponse = new HashMap<String, byte[]>();
        redirectResponse.put("status", "HTTP/1.1 302 Found\r\n".getBytes());
        redirectResponse.put("header", "Location: http://localhost:5000/\r\n".getBytes());
        redirectResponse.put("body", "".getBytes());

        assertArrayEquals(redirectResponse.get("status"), Handler.getResponse(redirectRequest).get("status"));
        assertArrayEquals(redirectResponse.get("header"), Handler.getResponse(redirectRequest).get("header"));
        assertArrayEquals(redirectResponse.get("body"), Handler.getResponse(redirectRequest).get("body"));
    }

    @Test
    public void getResponseDelivers401ForUnauthorizedLogsRequest() {
        HashMap<String, String> unauthorizedLogsRequest = new HashMap<String, String>();
        unauthorizedLogsRequest.put("method", "GET");
        unauthorizedLogsRequest.put("uri", "/logs");

        HashMap<String, byte[]> unauthorizedResponse= new HashMap<String, byte[]>();
        unauthorizedResponse.put("status", "HTTP/1.1 401 Unauthorized\r\n".getBytes());
        unauthorizedResponse.put("header", "\r\n".getBytes());
        unauthorizedResponse.put("body", "Authentication required".getBytes());

        assertArrayEquals(unauthorizedResponse.get("status"), Handler.getResponse(unauthorizedLogsRequest).get("status"));
        assertArrayEquals(unauthorizedResponse.get("header"), Handler.getResponse(unauthorizedLogsRequest).get("header"));
        assertArrayEquals(unauthorizedResponse.get("body"), Handler.getResponse(unauthorizedLogsRequest).get("body"));
    }

    @Test
    public void getResponseDelivers404ForUnrecognizedRequest() {
        HashMap<String, String> unrecognizedRequest = new HashMap<String, String>();
        unrecognizedRequest.put("method", "GET");
        unrecognizedRequest.put("uri", "/foobar");

        HashMap<String, byte[]> fourOhFourResponse = new HashMap<String, byte[]>();
        fourOhFourResponse.put("status", "HTTP/1.1 404 Not Found\r\n".getBytes());
        fourOhFourResponse.put("header", "\r\n".getBytes());
        fourOhFourResponse.put("body", "".getBytes());

        assertArrayEquals(fourOhFourResponse.get("status"), Handler.getResponse(unrecognizedRequest).get("status"));
        assertArrayEquals(fourOhFourResponse.get("header"), Handler.getResponse(unrecognizedRequest).get("header"));
        assertArrayEquals(fourOhFourResponse.get("body"), Handler.getResponse(unrecognizedRequest).get("body"));
    }

    @Test
    public void getResponseDelivers405ForNotAllowedRequest() {
        HashMap<String, String> notAllowedRequest = new HashMap<String, String>();
        notAllowedRequest.put("method", "PUT");
        notAllowedRequest.put("uri", "/file1");

        HashMap<String, byte[]> notAllowedResponse = new HashMap<String, byte[]>();
        notAllowedResponse.put("status", "HTTP/1.1 405 Method Not Allowed\r\n".getBytes());
        notAllowedResponse.put("header", "\r\n".getBytes());
        notAllowedResponse.put("body", "".getBytes());

        assertArrayEquals(notAllowedResponse.get("status"), Handler.getResponse(notAllowedRequest).get("status"));
        assertArrayEquals(notAllowedResponse.get("header"), Handler.getResponse(notAllowedRequest).get("header"));
        assertArrayEquals(notAllowedResponse.get("body"), Handler.getResponse(notAllowedRequest).get("body"));
    }
}