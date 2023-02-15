package com.github.panarik.manager;

import com.github.panarik.connection.SocketService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerManager {

    private static ServerSocket server;

    public static void main(String[] args) {
        startServer();
        handleRequests();
    }

    private static void startServer() {
        try {
            server = new ServerSocket(8088);
            System.out.println("Server started!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void handleRequests() {
        while (true) {
            new Thread(new ClientManager(new SocketService(connect()))).start();
        }
    }

    /**
     * Wait until client. Create connection socket.
     *
     * @return socket.
     */
    private static Socket connect() {
        try {
            Socket socket = server.accept();
            System.out.println("New client connected!");
            return socket;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}