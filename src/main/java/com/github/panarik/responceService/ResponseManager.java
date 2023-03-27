package com.github.panarik.responceService;

import com.github.panarik.connection.SocketService;
import com.github.panarik.data.FileDataProvider;
import com.github.panarik.responceService.model.Response;

/**
 * Manage client requests and responses.
 */
public class ResponseManager implements Runnable {

    private final SocketService socketService;

    public ResponseManager(SocketService socketService) {
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
        socketService.close();
        System.out.println("Client disconnected!");
    }

}
