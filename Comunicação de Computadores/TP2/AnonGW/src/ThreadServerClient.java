import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import static java.lang.System.out;
import static java.lang.System.in;

public class ThreadServerClient extends Thread {
    static Socket newClient;
    public ThreadServerClient(
    ) { try{newClient = new Socket("localhost", 10);}catch (Exception e){}}

    public void run() {
        try {
            fromClient();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fromClient() throws Exception{
        try {
            out.println("AnonGW Iniciado.");
            String clientSentence;
            ServerSocket newClientAcess = new ServerSocket(2911);

            while (true) {
                Socket newClient = newClientAcess.accept();
                System.out.println("New Client!");
                BufferedReader inFromClient =
                        new BufferedReader(new InputStreamReader(newClient.getInputStream()));
                clientSentence = inFromClient.readLine();
                out.println("Comando Recebido: " + clientSentence);
                String fromserver = sendToServer(clientSentence);
                OutputStream output = new DataOutputStream(newClient.getOutputStream());
                PrintWriter writer = new PrintWriter(output, true);
                writer.println(fromserver);
            }
        }
        catch(Exception e){
            out.println("Erro a receber do Cliente.");
        }
    }

    public static String sendToServer(String sentence) {
        try {
            String modifiedSentence;

            DataOutputStream outToServer = new DataOutputStream(newClient.getOutputStream());
            outToServer.writeBytes(sentence + "\n");
            InputStream input = newClient.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String time = reader.readLine();
            newClient.close();
            return  time;
        }
        catch(Exception e){
            return "Erro a enviar para o server.";
        }
    }
}
