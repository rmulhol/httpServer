package com.httpServer.RequestAdapter;

public class Request {

    private String method;
    private String uri;
    private String parameters;
    private String body;
    private String range;
    private boolean authorization;

    public Request() {
        this.authorization = false;
    }

    public String getMethod() {
        return this.method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUri() {
        return this.uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getParameters() {
        return this.parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getRange() {
        return this.range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public boolean getAuthorization() {
        return this.authorization;
    }

    public void setAuthorization() {
        this.authorization = true;
    }
}
