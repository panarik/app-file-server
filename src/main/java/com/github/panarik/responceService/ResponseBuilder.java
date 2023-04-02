package com.github.panarik.responceService;

import com.github.panarik.responceService.model.Response;

import java.io.InputStreamReader;

/**
 * Build new response.
 */
public class ResponseBuilder {

    private final Response response = new Response();

    public ResponseBuilder() {
    }

    public ResponseBuilder setStatus(int status) {
        this.response.setStatus(status);
        this.response.setFirstLine();
        return this;
    }

    public ResponseBuilder setBody(InputStreamReader reader) {
        this.response.setBody(reader);
        return this;
    }

    public String build() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.response.getFirstLine()).append('\n');
        if (this.response.getHeaders() != null) {
            for (String line : this.response.getHeaders()) builder.append(line).append('\n');
        }
        builder.append('\n');
        builder.append(this.response.getBody());
        return builder.toString();
    }
}
