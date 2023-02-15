package com.github.panarik.connection.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Response data to client.
 */
public class Response {

    private final int status;
    private final String firstLine;
    private String[] headers;
    private String body;

    public Response(int status) {
        this.status = status;
        this.firstLine = "HTTP/1.1 " + status + " " + new Codes().get(status);
        this.headers = new String[]{"Content-Type: text/html; charset=utf-8"};
    }

    public Response setBody(InputStreamReader stream) {
        BufferedReader reader = new BufferedReader(stream);
        try {
            StringBuilder builder = new StringBuilder();
            while (reader.ready()) builder.append(reader.readLine());
            body = builder.toString();
            return this;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String print() {
        StringBuilder builder = new StringBuilder();
        builder.append(firstLine).append('\n');
        for (String line : headers) builder.append(line).append('\n');
        builder.append('\n');
        builder.append(body);
        return builder.toString();
    }

}
