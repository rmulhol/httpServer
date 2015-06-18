package com.httpServer.Handler.SubHandlers;

import com.httpServer.Handler.ResponseContents.ResponseHeader;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertArrayEquals;

public class HeaderHandlerTest {

    @Test
    public void setsHeaderForMethodOptionsResponse() {
        HashMap<String, String> methodOptionsRequest = new HashMap<String, String>();
        methodOptionsRequest.put("method", "GET");
        methodOptionsRequest.put("uri", "/method_options");

        HashMap<String, byte[]> methodOptionsResponse = new HashMap<String, byte[]>();

        new HeaderHandler(methodOptionsRequest, methodOptionsResponse).setHeader();

        assertArrayEquals(ResponseHeader.methodOptions(), methodOptionsResponse.get("header"));
    }

    @Test
    public void setsHeaderForRedirectResponse() {
        HashMap<String, String> redirectRequest = new HashMap<String, String>();
        redirectRequest.put("method", "GET");
        redirectRequest.put("uri", "/redirect");

        HashMap<String, byte[]> redirectResponse = new HashMap<String, byte[]>();

        new HeaderHandler(redirectRequest, redirectResponse).setHeader();

        assertArrayEquals(ResponseHeader.location(), redirectResponse.get("header"));
    }

    @Test
    public void setsHeaderForResponseWithoutHeader() {
        HashMap<String, String> rootRequest = new HashMap<String, String>();
        rootRequest.put("method", "GET");
        rootRequest.put("uri", "/");

        HashMap<String, byte[]> rootResponse = new HashMap<String, byte[]>();

        new HeaderHandler(rootRequest, rootResponse).setHeader();

        assertArrayEquals(ResponseHeader.noHeader(), rootResponse.get("header"));
    }
}