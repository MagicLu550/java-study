package com.a.eye.by.io.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

public class TimerServerHandler extends Thread {

    private Socket socket;

    public TimerServerHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {

        BufferedReader in = null;

        PrintWriter out = null;

        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out = new PrintWriter(socket.getOutputStream(), true);

            while (true) {
                String body = in.readLine();
                if (null == body) {
                    break;
                }
                out.println("this time is : " + new Date().toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != in) {
                try {
                    in.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (null != out) {
                out.close();
            }
            if (null != this.socket) {
                try {
                    this.socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
