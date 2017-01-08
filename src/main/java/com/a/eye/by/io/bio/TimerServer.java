package com.a.eye.by.io.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TimerServer {

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(9888);
            Socket socket = null;
            while (true) {
                socket = serverSocket.accept();
                new TimerServerHandler(socket).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != serverSocket) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
