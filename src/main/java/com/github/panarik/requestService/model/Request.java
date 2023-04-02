package com.github.panarik.requestService.model;

import com.github.panarik.data.FileDataProvider;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

/**
 * Request from client.
 */
public class Request {

    private List<String> lines; // all request fields.
    private String type;
    private String path;
    private String protocol;
    private String host;
    private LinkedList<String> headers;

    /**
     * Verify request is valid or not. Verify requested file is exist or not.
     *
     * @return {@link true} file exist, {@link false} file not exist.
     */
    public boolean isValid() {
        Path path = Paths.get(this.path);
        return new FileDataProvider().fileIsExist(path);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public LinkedList<String> getHeaders() {
        return headers;
    }

    public void setHeaders(LinkedList<String> headers) {
        this.headers = headers;
    }

    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }

}
