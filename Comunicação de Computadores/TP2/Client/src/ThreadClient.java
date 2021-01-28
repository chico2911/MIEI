import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ThreadClient extends Thread {
    static Socket newConnection;
    public ThreadClient(){try{newConnection = new Socket("localhost", 2911);}catch (Exception e){}}

    public void run() {
        sendToServer();
    }
    public String sendToServer(){
        try {
            String sentence="CC Client Server with ANONGW";
            String modifiedSentence;
            DataOutputStream outToServer = new DataOutputStream(newConnection.getOutputStream());
            outToServer.writeBytes(sentence + "\n");
            InputStream input = newConnection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String time = reader.readLine();
            System.out.println(time);
            newConnection.close();
            return  time;
        }
        catch(Exception e){
            return "Erro a enviar para o Server.";
        }
    }
}
