package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static List<Socket> socketList;
    private static ServerSocket serverSocket;
    public static void main(String[] args) {
        socketList = new ArrayList<Socket>();
        try{
            Runtime.getRuntime().addShutdownHook(new Thread(){
                @Override
                public void run() {
                    System.out.println("SIGINT Shutting down");
                    try {
                        System.out.println();
                        if (!socketList.isEmpty())
                            for (Socket socket : socketList)
                                socket.close();
                        serverSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
             serverSocket = new ServerSocket(1488);
            System.out.println("Server start..");
            acceptClients(serverSocket);
        }catch(IOException e){
            e.printStackTrace();
        }
    }


    private static void acceptClients(ServerSocket serverSocket) throws IOException{
        while (true){
            Socket socket = serverSocket.accept();
            socketList.add(socket);
            ConnectionThread connectionThread = new ConnectionThread(socket);
            connectionThread.start();
        }
    }
}
