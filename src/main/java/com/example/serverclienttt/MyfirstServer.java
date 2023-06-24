package com.example.serverclienttt;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MyfirstServer {

    public static void main(String[] args) throws Exception {

        ServerSocket ss = new ServerSocket(1234);
        System.out.println("waiting for the connection!!");
        Socket s = ss.accept();
        InputStream is = s.getInputStream();
        OutputStream os = s.getOutputStream();
        System.out.println("WAITING FOR A NUMBER ");
        int number = is.read();
        System.out.println("WE got the number " + number);
        int calc = number * 5;
        System.out.println("IM GOING TO SEND THE RESPONSE :"+ calc);
        os.write(calc);
        s.close();





    }
}
