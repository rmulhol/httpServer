package com.httpServer.Handlers;

import com.httpServer.RequestAdapter.Request;
import com.httpServer.ResponseAdapter.Response;

public class MockHandler implements Handler {
    @Override
    public Response respondToRequest(Request request) {
        return null;
    }
}
