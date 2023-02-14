package com.github.panarik.connection;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ResponseConnection {

    private final PrintWriter output;

    public ResponseConnection(Socket socket) {
        try {
            output = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public PrintWriter getOutput() {
        return output;
    }
}
