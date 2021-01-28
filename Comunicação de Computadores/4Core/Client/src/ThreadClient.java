import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

import static java.lang.System.out;

public class ThreadClient extends Thread {
    static Socket newConnection;
    private String comand; String ipServer;
    private int jumpsNO;
    static List<String> availableIPS = new ArrayList<>();
    public ThreadClient(int jumpsNO,String comando,String ipServer){
        this.jumpsNO=jumpsNO;
        availableIPS.add("10.4.4.3");
        availableIPS.add("10.4.4.2");
        availableIPS.add("10.4.4.1");
        availableIPS.add("10.2.2.3");
        availableIPS.add("10.2.2.2");
        availableIPS.add("10.2.2.1");
        comand = comando; this.ipServer=ipServer;
    }

    public void run() {
        sendToServer();
    }

    public void sendToServer(){
        try {
            Random randomlyChooseIp = new Random();
            int chosenIP = randomlyChooseIp.nextInt(5);
            String ip = availableIPS.get(chosenIP);
            newConnection = new Socket(ip, 75);
            DataOutputStream outToServer = new DataOutputStream(newConnection.getOutputStream());
            outToServer.writeBytes(jumpsNO + " " + comand + " "+ ipServer +'\n');
            InputStream input = newConnection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String messageFromServer = reader.readLine();
            newConnection.close();
            printResult(messageFromServer);
        }
        catch(Exception e){
            out.println( "Erro a enviar para o Server.");
        }
    }

    public static void printResult(String fromServer){
        switch (fromServer){
            case "h": out.println("Esse comando não existe.\nComandos Disponiveis: Data,Hora. \nTente novamente!");
                break;
            default: out.println(fromServer);
        }
    }
}
