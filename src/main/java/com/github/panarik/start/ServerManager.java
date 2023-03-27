package com.github.panarik.start;

import com.github.panarik.config.ConfigFactory;
import com.github.panarik.config.ConfigHolder;
import com.github.panarik.config.TerminalConfig;
import com.github.panarik.connection.SocketService;
import com.github.panarik.responceService.ResponseManager;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Manage whole server jobs.
 */
public class ServerManager {

    private static ServerSocket server;

    public static void main(String[] args) {
        configServer(args);
        startServer();
        handleRequests();
    }

    private static void configServer(String[] args) {
        TerminalConfig.getInstance().setArgs(args);
        ConfigHolder.instance().setServerConfig(new ConfigFactory().getConfig());
    }

    private static void startServer() {
        try {
            server = new ServerSocket(ConfigHolder.instance().getConfig().getPort());
            System.out.println("Server started!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void handleRequests() {
        while (true) {
            new Thread(new ResponseManager(new SocketService(connect()))).start();
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