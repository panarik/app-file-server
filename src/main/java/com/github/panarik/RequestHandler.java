package com.github.panarik;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RequestHandler implements Runnable {

    private final Socket socket;
    private final String dir; // directory with requested file

    public RequestHandler(Socket socket, String dir) {
        this.socket = socket;
        this.dir = dir;
    }

    @Override
    public void run() {
        try (BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
             PrintWriter output = new PrintWriter(socket.getOutputStream())) {

            String firstLine = input.readLine();
            String[] parts = firstLine.split(" ");
            System.out.println(firstLine);
            while (input.ready()) {
                System.out.println(input.readLine());
            }

            Path filePath = Paths.get(this.dir, parts[1]);
            if (!Files.exists(filePath)) {
                output.println("HTTP/1.1 404 NOT_FOUND");
                output.println("Content-Type: text/html; charset=utf-8" + "\n");
                output.println("<h1>Файл не найден!</h1>");
            } else {
                output.println("HTTP/1.1 200 OK");
                output.println("Content-Type: text/html; charset=utf-8" + "\n");
                try (BufferedReader indexReader = Files.newBufferedReader(filePath)) {
                    indexReader.transferTo(output);
                } catch (IOException e) {
                    throw new RuntimeException("add filename to request query. For example: 'http://localhost:8088/index.html'.");
                }
            }
            output.flush();
            System.out.println("Client disconnected!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
