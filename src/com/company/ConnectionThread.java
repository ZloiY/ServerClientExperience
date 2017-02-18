package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by ZloiY on 18-Feb-17.
 */
public class ConnectionThread extends Thread {
    private Socket clientSocket;
    public ConnectionThread(Socket clientSocket){
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        super.run();
        try {
            InputStream inputStream = clientSocket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String str = bufferedReader.readLine();
            System.out.println("Client message accepted: " + str);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
