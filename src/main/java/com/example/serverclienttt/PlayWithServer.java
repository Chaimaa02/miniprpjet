package com.example.serverclienttt;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class PlayWithServer extends Thread {
    private int CLientNbre;
    private int SecretNbre;
    private boolean fin;


    public static void main(String[] args) {
        new PlayWithServer().start();
    }

    @Override
    public void run() {
        try {
            ServerSocket ss = new ServerSocket(1234);
            SecretNbre = new Random().nextInt(100);
            System.out.println("Le Serveur essaie de demarrer");
            while (true) {
                ++CLientNbre;
                Socket s = ss.accept();
                new Communication(s, CLientNbre).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    class Communication extends Thread {
        private Socket s;
        private int ClienNbre;

        Communication(Socket s, int ClienNbre) {
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
                System.out.println("le Client numero " + ClienNbre + " vient de se connecter");
                PrintWriter pw = new PrintWriter(os, true);
                pw.println("Vous etes le client " + ClienNbre);
                pw.println("Deviner le nombre secret");
                while (true) {
                    String UserRequest = br.readLine();
                    boolean RequestFormat = false;
                    int UserNbre = 0;
                    try {
                        UserNbre = Integer.parseInt(UserRequest);
                        RequestFormat = true;
                    } catch (NumberFormatException e) {
                        pw.println("Donne un numero");
                    }
                    if (RequestFormat) {
                        if (!fin) {
                            if (UserNbre > SecretNbre)
                                pw.println("Votre nombre est superieur au nombre secret");
                            else if (UserNbre < SecretNbre)
                                pw.println("Votre nombre est inferieur au nombre secret");
                            else {
                                pw.println("Vous avez trouvez le nombre !!");
                                System.out.println("le Client numero " + ClienNbre + " vient de trouvez le nombre secret");
                                fin = true;
                            }
                        } else {
                            pw.println("Le jeux est fini");
                        }


                        pw.println("La taille de votre chaine de caracteres est : " + UserRequest.length());
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
}

