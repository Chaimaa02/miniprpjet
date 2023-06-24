package com.example.serverclienttt;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class MyFirstClient {
    public static void main(String[] args) throws Exception {
        System.out.println("im goinna connect to the server");
        Socket s = new Socket("localhost",1234);
        InputStream is = s.getInputStream();
        OutputStream os = s.getOutputStream();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a numbbb!!");
        int nbre = sc.nextInt();
        System.out.println("Im gonna send the numbb "+ nbre);
        os.write(nbre);
        System.out.println("waiting for the srever");
        int resp = is.read();
        System.out.println("the response is "+resp);


    }
}
