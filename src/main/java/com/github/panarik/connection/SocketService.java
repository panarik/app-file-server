package com.github.panarik.connection;

import java.net.Socket;

/**
 * Hold socket and creates request and response connections.
 */
public class SocketService {

    private final Socket socket;
    private final RequestConnection request;
    private final ResponseConnection response;

    public SocketService(Socket socket) {
        this.socket = socket;
        this.request = new RequestConnection(socket);
        this.response = new ResponseConnection(socket);
    }

    public RequestConnection getRequest() {
        return request;
    }

    public ResponseConnection getResponse() {
        return response;
    }
    
}
