package com.httpServer.Handlers;

import com.httpServer.RequestAdapter.Request;
import com.httpServer.ResponseAdapter.Response;

public interface Handler {

    Response respondToRequest(Request request);

}
