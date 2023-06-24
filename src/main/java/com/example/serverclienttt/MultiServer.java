package com.example.serverclienttt;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiServer extends Thread{
    private int CLientNbre;
    public static void main(String[] args){
        new MultiServer().start();
    }

    @Override
    public void run() {
        try{
            ServerSocket ss = new ServerSocket(1234);
            System.out.println("Le Serveur essaie de demarrer");
            while (true){
                ++ CLientNbre;
                Socket s = ss.accept();
                new Communication(s,CLientNbre).start();
            }
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    class Communication extends Thread{
        private Socket s;
        private int ClienNbre;
        Communication(Socket s,int ClienNbre){
            this.s = s;
            this.ClienNbre = ClienNbre;
        }

        @Override
        public void run() {
            try {
                InputStream is = s.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);

                OutputStream os = s.getOutputStream();
                System.out.println("le Client numero "+ ClienNbre+" vient de se connecter");
                PrintWriter pw = new PrintWriter(os,true);
                pw.println("Vous etes le client "+ClienNbre);
                while (true){
                    String UserRequest = br.readLine();
                    pw.println("La taille de votre chaine de caracteres est : "+ UserRequest.length());
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
