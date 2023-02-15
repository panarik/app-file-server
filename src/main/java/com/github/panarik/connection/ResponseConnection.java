package com.github.panarik.connection;

import com.github.panarik.connection.model.Response;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Response connection to client.
 */
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

    public void sendResponse(Response response) {
        this.getOutput().println(response.print());
        this.getOutput().flush();
    }
}
