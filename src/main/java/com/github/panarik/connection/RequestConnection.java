package com.github.panarik.connection;

import com.github.panarik.data.FileDataProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * Request connection from client.
 */
public class RequestConnection {

    private final BufferedReader input; // read request data
    private String firstLine;
    private String path; // requested file path.
    private List<String> parts;

    public RequestConnection(Socket socket) {
        try {
            input = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        waitRequest();
        readRequest();
        printRequest();
    }

    /**
     * Get full server path of requested file.
     *
     * @return requested file path.
     */
    public Path getPath() {
        return Paths.get(new FileDataProvider().ROOT, path);
    }

    /**
     * Verify request is valid or not.
     *
     * @return {@link true} file exist, {@link false} file not exist.
     */
    public boolean isValidRequest() {
        return new FileDataProvider().fileIsExist(this.getPath());
    }

    /**
     * Wait until current connection has some data.
     */
    private boolean waitRequest() {
        try {
            while (!input.ready()) ;
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void readRequest() {
        try {
            firstLine = input.readLine();
            parts = Arrays.asList(firstLine.split(" "));
            path = parts.get(1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void printRequest() {
        try {
            while (input.ready()) {
                System.out.println(input.readLine());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
