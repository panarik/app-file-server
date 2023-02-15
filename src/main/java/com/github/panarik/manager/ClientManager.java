package com.github.panarik.manager;

import com.github.panarik.connection.SocketService;
import com.github.panarik.connection.model.Response;
import com.github.panarik.data.FileDataProvider;

/**
 * Manage client requests and responses.
 */
public class ClientManager implements Runnable {

    private final SocketService socketService;

    public ClientManager(SocketService socketService) {
        this.socketService = socketService;
    }

    @Override
    public void run() {
        if (socketService.getRequest().isValidRequest()) {
            socketService.getResponse().sendResponse(new Response(200)
                    .setBody(new FileDataProvider().getData(socketService.getRequest().getPath())));
        } else {
            socketService.getResponse().sendResponse(new Response(404)
                    .setBody(new FileDataProvider().getData("/404.html")));
        }
        System.out.println("Client disconnected!");
    }

}
