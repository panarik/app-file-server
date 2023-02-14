package com.github.panarik.connection;

import com.github.panarik.manager.FileManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RequestConnection {

    private final BufferedReader input;
    private String firstLine;
    private String path; // requested file path.
    private String[] parts;

    public RequestConnection(Socket socket) {
        try {
            input = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        readRequest();
        printRequest();
    }

    public Path getPath() {
        return Paths.get(FileManager.ROOT, path);
    }

    public void readRequest() {
        try {
            firstLine = input.readLine();
            parts = firstLine.split(" ");
            path = parts[1];
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
