package com.github.panarik.responceService;

import com.github.panarik.connection.SocketService;
import com.github.panarik.data.FileDataProvider;
import com.github.panarik.requestService.RequestReader;
import com.github.panarik.requestService.model.Request;
import com.github.panarik.responceService.model.Response;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Manage client requests and responses.
 */
public class ResponseManager implements Runnable {

    private final SocketService socketService;
    private Request request;
    private Response response;

    public ResponseManager(SocketService socketService) {
        this.socketService = socketService;
    }

    public void run() {
        readRequest();
        printRequest();
        ResponseBuilder responseBuilder = new ResponseBuilder();

        if (pathCheck()) {
            responseBuilder.setStatus(200).setBody(new FileDataProvider().getData(request.getPath()));
        } else {
            responseBuilder.setStatus(404).setBody(new FileDataProvider().getData("/404.html"));
        }

        PrintWriter output;
        try {
            output = new PrintWriter(this.socketService.getSocket().getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        output.println(responseBuilder.build());
        System.out.println("Response is sent.");
        output.flush();

        socketService.close();
        System.out.println("Client disconnected!");
    }

    private boolean pathCheck() {
        return this.request.isValid();
    }

    private void readRequest() {
        this.request = new RequestReader(socketService.getSocket()).readRequest();
    }

    private void printRequest() {
        for (String line : request.getLines()) System.out.println(line);
    }

}
