package com.github.panarik.responceService.bodyBuild;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * ResponseBody data to client.
 */
public class ResponseBody {

    private int status;
    private String firstLine;
    private String[] headers;
    private String body;

    public int getStatus() {
        return status;
    }

    public String getFirstLine() {
        return firstLine;
    }

    public String[] getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }

    public ResponseBody setBody(InputStreamReader stream) {
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

    public ResponseBody setStatus(int status) {
        this.status = status;
        return this;
    }

    public ResponseBody setFirstLine() {
        this.firstLine = "HTTP/1.1 " + status + " " + new Codes().get(status);
        return this;
    }

    public ResponseBody setHeaders(String[] headers) {
        this.headers = headers;
        return this;
    }

}
