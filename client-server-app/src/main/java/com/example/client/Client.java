package com.example.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 8080;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(
                     new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Connected to server at " + SERVER_HOST + ":" + SERVER_PORT);

            System.out.print("Enter your message: ");
            String message = scanner.nextLine();

            out.println(message);
            System.out.println("Message sent to server: " + message);

            String response = in.readLine();
            System.out.println("Server response: " + response);

        } catch (IOException e) {
            System.err.println("Client exception: " + e.getMessage());
        }
    }
}