import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadUDP extends Thread {
    private Map<String,String> ipsUDPMAP;
    private String[] campos;
    private List<String> availableIPS;
    private static ReentrantLock reentrantLock = new ReentrantLock();

    public ThreadUDP(Map<String,String> ipsUDPMAP,List<String> availableIPS,String[] campos){
        this.ipsUDPMAP=ipsUDPMAP;
        this.campos=campos;
        this.availableIPS=availableIPS;
    }

    public void run() {
        if (campos[0] == "Reply") {
            sendToAnonGWReply();
        } else {
            sendToAnonGW();
        }
    }

    public void sendToAnonGWReply () {
            try {
                reentrantLock.lock();
                InetAddress address = null;
                String ip = ipsUDPMAP.get(campos[1]);
                address = InetAddress.getByName(ip.substring(1));
                DatagramSocket dataSocket = new DatagramSocket(8611, address);
                byte[] buffer = campos.toString().getBytes();
                DatagramPacket data = new DatagramPacket(buffer, buffer.length);
                dataSocket.send(data);
                reentrantLock.unlock();
            } catch (Exception e) {
            }
    }

    public void sendToAnonGW () {
            try {
                reentrantLock.lock();
                int jump = Integer.parseInt(campos[1]);
                if(jump==0){sendToTCP();}
                else{
                    Random randomlyChooseIp = new Random();
                    int chosenIP = randomlyChooseIp.nextInt(2);
                    String ip = availableIPS.get(chosenIP);
                    InetAddress address = null;
                    address = InetAddress.getByName(ip.substring(1));
                    String sentence = campos[0] + jump-- + campos[2] + campos[3];
                    DatagramSocket dataSocket = new DatagramSocket(8611, address);
                    byte[] buffer = sentence.getBytes();
                    DatagramPacket data = new DatagramPacket(buffer, buffer.length);
                    dataSocket.send(data);
                }
                reentrantLock.unlock();
            } catch (Exception e) { }
    }

    public void sendToTCP(){
      try {
          reentrantLock.lock();
          Socket serverSocket = new Socket(campos[3], 10);
          String modifiedSentence = campos[0] + " " + campos[2];
          DataOutputStream outToServer = new DataOutputStream(serverSocket.getOutputStream());
          outToServer.writeBytes(modifiedSentence + "\n");
          InputStream input = serverSocket.getInputStream();
          BufferedReader reader = new BufferedReader(new InputStreamReader(input));
          String reply = reader.readLine();
          campos = reply.split(" ");
          sendToAnonGWReply();
          serverSocket.close();
          reentrantLock.unlock();
      }
      catch (Exception e){}
    }
}