package com.company;

import java.io.*;

public class Main {

        public static void main(String args[]) throws IOException {
            System.out.println("*#");
            BufferedReader in = null;


            try {
                in = new BufferedReader(new FileReader("filename"));


                String leitura = in.readLine();

                while(leitura != null){
                    String[] tokens = leitura.split("\\*\\#");
                    leitura = in.readLine();

                }

            }finally {
                if (in != null) {
                    in.close();
                }
        }
    }
}
