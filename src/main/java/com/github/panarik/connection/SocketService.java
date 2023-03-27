package com.github.panarik.connection;

import com.github.panarik.requestService.RequestReader;
import com.github.panarik.responceService.ResponseConnection;

import java.io.Closeable;
import java.io.IOException;
import java.net.Socket;

/**
 * Hold socket and creates request and response connections.
 */
public class SocketService implements Closeable {

    private final Socket socket;
    private final RequestReader request;
    private final ResponseConnection response;

    public SocketService(Socket socket) {
        this.socket = socket;
        this.request = new RequestReader(socket);
        this.response = new ResponseConnection(socket);
    }

    public RequestReader getRequest() {
        return request;
    }

    public ResponseConnection getResponse() {
        return response;
    }

    public void close() {
        try {
            if (!socket.isClosed()) socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
