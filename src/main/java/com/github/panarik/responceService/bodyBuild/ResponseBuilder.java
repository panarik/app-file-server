package com.github.panarik.responceService.bodyBuild;

import java.io.InputStreamReader;

/**
 * Build new response bodies.
 */
public class ResponseBuilder {

    private final ResponseBody response = new ResponseBody();

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
        checkResponse();
        StringBuilder builder = new StringBuilder();
        builder.append(this.response.getFirstLine()).append('\n');
        if (this.response.getHeaders() != null) {
            for (String line : this.response.getHeaders()) builder.append(line).append('\n');
        }
        builder.append('\n');
        builder.append(this.response.getBody());
        return builder.toString();
    }

    private void checkResponse() {
        if (this.response.getStatus() == 0) throw new IllegalStateException("ResponseBody must have status code.");
    }
}
