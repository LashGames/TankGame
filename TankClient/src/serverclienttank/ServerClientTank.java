/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverclienttank;

import client.Client;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Asus
 */
public class ServerClientTank {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        System.out.println("The server is running.");
        ServerSocket listener = new ServerSocket(7000);
        Client client = new Client();
        client.connectToServer();
        try {
            while (true) {
                new Handler(listener.accept()).start();
            }
        } finally {
            listener.close();
        }
    }

    private static class Handler extends Thread {

        private Socket socket;
        private DataOutputStream dataOutputStream;
        private BufferedReader bufferedReader;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {

                while (true) {
                    bufferedReader = new BufferedReader(new InputStreamReader(
                            socket.getInputStream()));
                    dataOutputStream = new DataOutputStream(socket.getOutputStream());
                    String input = bufferedReader.readLine();
                    dataOutputStream.writeBytes("LEFT#");
                    //dataOutputStream.flush();
                }
            } catch (IOException e) {
                System.out.println(e);
            } finally {

                try {
                    socket.close();
                } catch (IOException e) {
                }
            }
        }
    }
}
