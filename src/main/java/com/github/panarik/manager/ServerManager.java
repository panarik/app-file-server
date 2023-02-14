package com.github.panarik.manager;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerManager {

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8088)) {
            System.out.println("Server started!");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected!");
                new Thread(new RequestHandler(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}