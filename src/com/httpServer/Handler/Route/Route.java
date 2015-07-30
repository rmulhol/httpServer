package com.httpServer.Handler.Route;

import com.httpServer.RequestAdapter.Request;

public class Route {

    public final Request request;

    // request method
    private boolean isDeleteRequest;
    private boolean isGetRequest;
    private boolean isOptionsRequest;
    private boolean isPatchRequest;
    private boolean isPutRequest;
    private boolean isPostRequest;

    // request uri
    private boolean isAvailableResourceRequest;
    private boolean isFormRequest;
    private boolean isLogsRequest;
    private boolean isMethodOptionsRequest;
    private boolean isParametersRequest;
    private boolean isPartialContentRequest;
    private boolean isPatchContentRequest;
    private boolean isRedirectRequest;
    private boolean isRootRequest;

    // request header
    private boolean isAuthorized;
    private boolean hasRange;

    // response status
    private boolean isOkStatus;
    private boolean isNoContentStatus;
    private boolean isNotAllowedStatus;
    private boolean isPartialContentStatus;
    private boolean isRedirectStatus;
    private boolean isUnauthorizedStatus;

    // response header
    private boolean requiresLocationHeader;
    private boolean requiresMethodOptionsHeader;

    // response body
    private boolean requiresDirectoryContentsBody;
    private boolean requiresFileContentsBody;
    private boolean requiresAuthenticationRequiredBody;
    private boolean requiresLogsBody;
    private boolean requiresParametersBody;
    private boolean requiresPartialContentBody;

    // response file writing operations
    private boolean requiresFormEdit;
    private boolean requiresFormDelete;
    private boolean requiresPatchContentPatch;

    public Route(Request request) {
        this.request = request;

        setAuthorized(isAuthorized());

        new RouteRequestMethodParser(this).parseRouteMethod();
        new RouteRequestUriParser(this).parseRouteUri();
        new RouteRequestHeaderParser(this).parseRequestHeader();
        new RouteResponseStatusParser(this).parseStatus();
        new RouteResponseHeaderParser(this).parseResponseHeader();
        new RouteResponseBodyParser(this).parseBody();
        new RouteFileWritingParser(this).parseFileWritingOperation();
    }

    // request method
    public boolean isDeleteRequest() {
        return isDeleteRequest;
    }

    public void setDeleteRequest(boolean isDeleteRequest) {
        this.isDeleteRequest = isDeleteRequest;
    }

    public boolean isGetRequest() {
        return isGetRequest;
    }

    public void setGetRequest(boolean isGetRequest) {
        this.isGetRequest = isGetRequest;
    }

    public boolean isOptionsRequest() {
        return isOptionsRequest;
    }

    public void setOptionsRequest(boolean isOptionsRequest) {
        this.isOptionsRequest = isOptionsRequest;
    }

    public boolean isPatchRequest() {
        return isPatchRequest;
    }

    public void setPatchRequest(boolean isPatchRequest) {
        this.isPatchRequest = isPatchRequest;
    }

    public boolean isPostRequest() {
        return isPostRequest;
    }

    public void setPostRequest(boolean isPostRequest) {
        this.isPostRequest = isPostRequest;
    }

    public boolean isPutRequest() {
        return isPutRequest;
    }

    public void setPutRequest(boolean isPutRequest) {
        this.isPutRequest = isPutRequest;
    }

    // request uri
    public boolean isAvailableResourceRequest() {
        return isAvailableResourceRequest;
    }

    public void setAvailableResourceRequest(boolean isAvailableResourceRequest) {
        this.isAvailableResourceRequest = isAvailableResourceRequest;
    }

    public boolean isFormRequest() {
        return isFormRequest;
    }

    public void setFormRequest(boolean isFormRequest) {
        this.isFormRequest = isFormRequest;
    }

    public boolean isLogsRequest() {
        return isLogsRequest;
    }

    public void setLogsRequest(boolean isLogsRequest) {
        this.isLogsRequest = isLogsRequest;
    }

    public boolean isMethodOptionsRequest() {
        return isMethodOptionsRequest;
    }

    public void setMethodOptionsRequest(boolean isMethodOptionsRequest) {
        this.isMethodOptionsRequest = isMethodOptionsRequest;
    }

    public boolean isParametersRequest() {
        return isParametersRequest;
    }

    public void setParametersRequest(boolean isParametersRequest) {
        this.isParametersRequest = isParametersRequest;
    }

    public boolean isPartialContentRequest() {
        return isPartialContentRequest;
    }

    public void setPartialContentRequest(boolean isPartialContentRequest) {
        this.isPartialContentRequest = isPartialContentRequest;
    }

    public boolean isPatchContentRequest() {
        return isPatchContentRequest;
    }

    public void setPatchContentRequest(boolean isPatchContentRequest) {
        this.isPatchContentRequest = isPatchContentRequest;
    }

    public boolean isRedirectRequest() {
        return isRedirectRequest;
    }

    public void setRedirectRequest(boolean isRedirectRequest) {
        this.isRedirectRequest = isRedirectRequest;
    }

    public boolean isRootRequest() {
        return isRootRequest;
    }

    public void setRootRequest(boolean isRootRequest) {
        this.isRootRequest = isRootRequest;
    }

    // request header
    public boolean isAuthorized() {
        return isAuthorized;
    }

    void setAuthorized(boolean isAuthorized) {
        this.isAuthorized = isAuthorized;
    }

    public boolean hasRange() {
        return hasRange;
    }

    public void setHasRange(boolean hasRange) {
        this.hasRange = hasRange;
    }

    // response status
    public boolean isOkStatus() {
        return this.isOkStatus;
    }

    public void setOkStatus(boolean isOkStatus) {
        this.isOkStatus = isOkStatus;
    }

    public boolean isNotAllowedStatus() {
        return this.isNotAllowedStatus;
    }

    public void setNotAllowedStatus(boolean isNotAllowedStatus) {
        this.isNotAllowedStatus = isNotAllowedStatus;
    }

    public boolean isPartialContentStatus() {
        return isPartialContentStatus;
    }

    public void setPartialContentStatus(boolean isPartialContentStatus) {
        this.isPartialContentStatus = isPartialContentStatus;
    }

    public boolean isNoContentStatus() {
        return isNoContentStatus;
    }

    public void setNoContentStatus(boolean isNoContentStatus) {
        this.isNoContentStatus = isNoContentStatus;
    }

    public boolean isRedirectStatus() {
        return isRedirectStatus;
    }

    public void setRedirectStatus(boolean isRedirectStatus) {
        this.isRedirectStatus = isRedirectStatus;
    }

    public boolean isUnauthorizedStatus() {
        return isUnauthorizedStatus;
    }

    public void setUnauthorizedStatus(boolean isUnauthorizedStatus) {
        this.isUnauthorizedStatus = isUnauthorizedStatus;
    }

    // response header
    public boolean requiresLocationHeader() {
        return requiresLocationHeader;
    }

    public void setRequiresLocationHeader(boolean requiresLocationHeader) {
        this.requiresLocationHeader = requiresLocationHeader;
    }

    public boolean requiresMethodOptionsHeader() {
        return requiresMethodOptionsHeader;
    }

    public void setRequiresMethodOptionsHeader(boolean requiresMethodOptionsHeader) {
        this.requiresMethodOptionsHeader = requiresMethodOptionsHeader;
    }

    // response body
    public boolean requiresDirectoryContentsBody() {
        return requiresDirectoryContentsBody;
    }

    public void setRequiresDirectoryContentsBody(boolean requiresDirectoryContentsBody) {
        this.requiresDirectoryContentsBody = requiresDirectoryContentsBody;
    }

    public boolean requiresFileContentsBody() {
        return requiresFileContentsBody;
    }

    public void setRequiresFileContentsBody(boolean requiresFileContentsBody) {
        this.requiresFileContentsBody = requiresFileContentsBody;
    }

    public boolean requiresAuthenticationRequiredBody() {
        return requiresAuthenticationRequiredBody;
    }

    public void setRequiresAuthenticationRequiredBody(boolean requiresAuthenticationRequiredBody) {
        this.requiresAuthenticationRequiredBody = requiresAuthenticationRequiredBody;
    }

    public boolean requiresLogsBody() {
        return requiresLogsBody;
    }

    public void setRequiresLogsBody(boolean requiresLogsBody) {
        this.requiresLogsBody = requiresLogsBody;
    }

    public boolean requiresParametersBody() {
        return requiresParametersBody;
    }

    public void setRequiresParametersBody(boolean requiresParametersBody) {
        this.requiresParametersBody = requiresParametersBody;
    }

    public boolean requiresPartialContentBody() {
        return requiresPartialContentBody;
    }

    public void setRequiresPartialContentBody(boolean requiresPartialContentBody) {
        this.requiresPartialContentBody = requiresPartialContentBody;
    }

    // response file writing operations
    public boolean requiresFormEdit() {
        return requiresFormEdit;
    }

    public void setRequiresFormEdit(boolean requiresFormEdit) {
        this.requiresFormEdit = requiresFormEdit;
    }

    public boolean requiresFormDelete() {
        return requiresFormDelete;
    }

    public void setRequiresFormDelete(boolean requiresFormDelete) {
        this.requiresFormDelete = requiresFormDelete;
    }

    public boolean requiresPatchContentPatch() {
        return requiresPatchContentPatch;
    }

    public void setRequiresPatchContentPatch(boolean requiresPatchContentPatch) {
        this.requiresPatchContentPatch = requiresPatchContentPatch;
    }
}