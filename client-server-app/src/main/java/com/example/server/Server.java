package com.example.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int PORT = 8080;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started on port " + PORT);

            System.out.println("Waiting for client connection...");
            Socket clientSocket = serverSocket.accept();

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));

            System.out.println("New connection accepted from port " + clientSocket.getPort());

            String clientMessage = in.readLine();
            System.out.println("Received from client: " + clientMessage);
            System.out.println("Client port: " + clientSocket.getPort());

            String response = String.format("Hi, your message '%s' was received from port %d",
                    clientMessage, clientSocket.getPort());
            out.println(response);

            clientSocket.close();
        } catch (IOException e) {
            System.err.println("Server exception: " + e.getMessage());
        }
    }
}