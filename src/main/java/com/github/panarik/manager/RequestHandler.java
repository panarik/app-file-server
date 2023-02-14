package com.github.panarik.manager;

import com.github.panarik.connection.RequestConnection;
import com.github.panarik.connection.ResponseConnection;
import com.github.panarik.connection.model.Response;

import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;

public class RequestHandler implements Runnable {

    private final RequestConnection requestConnection;
    private final ResponseConnection responseConnection;

    public RequestHandler(Socket socket) {
        this.requestConnection = new RequestConnection(socket);
        this.responseConnection = new ResponseConnection(socket);
    }

    @Override
    public void run() {
        if (fileIsExist()) {
            sendResponse(new Response(200).setBody(getFilePath()));
        } else {
            responseConnection.getOutput().println("HTTP/1.1 404 NOT_FOUND");
            responseConnection.getOutput().println("Content-Type: text/html; charset=utf-8" + "\n");
            responseConnection.getOutput().println("<h1>File not found!</h1>");
            responseConnection.getOutput().flush();
        }
        System.out.println("Client disconnected!");
    }

    private Path getFilePath() {
        return requestConnection.getPath();
    }

    /**
     * Verify requested file is exist or not.
     *
     * @return {@link true} file exist, {@link false} file not exist.
     */
    private boolean fileIsExist() {
        return Files.exists(getFilePath());
    }

    private void sendResponse(Response response) {
        responseConnection.getOutput().println(response.print());
        responseConnection.getOutput().flush();
    }

}
