/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Asus
 */
public class Client {

    private DataOutputStream dataOutputStream;

    public void connectToServer() throws IOException {

        String serverAddress = "127.0.0.1";
        int port = 6000;
        Socket socket = new Socket(serverAddress, port);
        dataOutputStream = new DataOutputStream(socket.getOutputStream());
        dataOutputStream.writeBytes("JOIN#");
        dataOutputStream.flush();
        socket.close();
    }
    
    

}
