/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverclienttank;

import client.Client;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import map.Map;

/**
 *
 * @author Asus
 */
public class ServerClientTank {
    private DataOutputStream dataOutputStream;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        Socket socket = null;
        System.out.println("The server is running.");
        
        Client client = new Client();
        client.connectToServer();
        Handler handler = new Handler(socket, client);
        handler.start();
        
    }

    private static class Handler extends Thread {

        private Socket socket;
        private Client client;
        ServerSocket listener;
        Map map = new Map();
        public Handler(Socket socket, Client client) {
            this.socket = socket;
            this.client = client;
        }

        public void run() {
            
            try {
                listener = new ServerSocket(7000);
            while (true) {
                try {
                    socket = listener.accept();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String message = reader.readLine();
                    System.out.println(message);
                    if(message.charAt(0)=='I' && message.charAt(1)==':'){
                        map.createMap(message);
                    }
//                    client.send("RIGHT#");
//                    client.send("SHOOT#");
                    String command = JOptionPane.showInputDialog(
            "Enter a command:");
                    client.sendCommand(command);
                    

                } catch (IOException ex) {
                }
            }
        }   catch (IOException ex) {
                Logger.getLogger(ServerClientTank.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    listener.close();
                } catch (IOException ex) {
                    Logger.getLogger(ServerClientTank.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        }
    }
}
