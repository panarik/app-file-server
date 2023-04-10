package com.github.panarik.responceService.typeDecorator;

import com.github.panarik.requestService.model.Request;

/**
 * Decorator for any response.
 * Provides custom response for every type of request: GET, POST, PUT, etc.
 */
public abstract class ResponseDecorator implements Response {

    /**
     * Hold request for make response.
     */
    protected Request request;

    public ResponseDecorator(Request request) {
        this.request = request;
    }

    /**
     * Provides finish response lines. Different lines for different types of responses.
     */
    public abstract String answer();
}
