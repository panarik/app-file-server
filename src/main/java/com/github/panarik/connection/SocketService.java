package com.github.panarik.connection;

import java.io.Closeable;
import java.io.IOException;
import java.net.Socket;

/**
 * Hold socket and creates request and response connections.
 */
public class SocketService implements Closeable {

    private final Socket socket;

    public SocketService(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    public void close() {
        try {
            if (!socket.isClosed()) socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
