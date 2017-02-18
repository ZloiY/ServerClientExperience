package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        try{
            ServerSocket serverSocket = new ServerSocket(1488);
            System.out.println("Server start..");
            acceptClients(serverSocket);
        }catch(IOException e){
            e.printStackTrace();
        }
    }


    private static void acceptClients(ServerSocket serverSocket) throws IOException{
        while (true){
            Socket socket = serverSocket.accept();
            ConnectionThread connectionThread = new ConnectionThread(socket);
            connectionThread.start();
        }
    }
}
