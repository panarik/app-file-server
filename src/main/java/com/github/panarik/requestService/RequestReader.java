package com.github.panarik.requestService;

import com.github.panarik.requestService.model.Request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Reader client connections.
 */
public class RequestReader {

    private final BufferedReader input; // read request data
    private String firstLine;
    private List<String> parts;

    public RequestReader(Socket socket) {
        try {
            input = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Wait until current connection has some data.
     */
    private void waitRequest() {
        try {
            while (!input.ready()) ;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Request readRequest() {
        waitRequest();
        Request request = new Request();
        List<String> lines = new ArrayList<>();
        try {
            while (input.ready()) {
                lines.add(input.readLine());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        request.setLines(lines);

        firstLine = request.getLines().get(0);
        parts = Arrays.asList(firstLine.split(" "));
        request.setType(parts.get(0));
        request.setPath(parts.get(1));
        request.setProtocol(parts.get(2));
        request.setHost(request.getLines().get(1));

        return request;
    }

}
