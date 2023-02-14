package com.github.panarik.input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class RequestReader {

    Socket socket;
    String firstLine;
    String[] parts;
    String requestDir;

    public RequestReader(Socket socket) {
        this.socket = socket;
        read();
        printRequest();
    }

    public String getRequestDir() {
        return this.requestDir;
    }

    private void read() {
        try (BufferedReader requestReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8))) {
            firstLine = requestReader.readLine();
            parts = firstLine.split(" ");
            requestDir = parts[1];
            System.out.println(firstLine);
            while (requestReader.ready()) {
                System.out.println(requestReader.readLine());
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private void printRequest() {
    }

}
