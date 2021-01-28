import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import static java.lang.System.out;

public class ThreadServerOpen extends Thread {
    public ThreadServerOpen() {
    }

    public void run(){
        try {
            out.println("Servidor Iniciado");
            String clientSentence;;
            ServerSocket newClientAcess = new ServerSocket(10);
            while (true) {
                Socket newClient = newClientAcess.accept();
                out.println("Novo cliente conectou-se.");
                BufferedReader inFromClient =
                        new BufferedReader(new InputStreamReader(newClient.getInputStream()));
                //
                clientSentence = inFromClient.readLine();
                out.println("Comando Recebido:" + clientSentence);
                OutputStream output = new DataOutputStream(newClient.getOutputStream());
                PrintWriter writer = new PrintWriter(output, true);
                writer.println(new Date().toString());
                //outToClient.writeBytes("ola bro");
            }
        }
        catch(Exception e){
            out.println("Algo correu mal.");
        }
    }
}
