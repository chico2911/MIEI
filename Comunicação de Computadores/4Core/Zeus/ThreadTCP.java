import java.io.*;
import java.net.*;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadTCP extends Thread {
    private Map<String, Socket> socketMap;
    private Map<String,String> ipsUDPMAP;
    private List<String> availableIPS;
    private Socket serverSocket;
    private static ReentrantLock reentrantLock = new ReentrantLock();
    private String clientSentence;
    public ThreadTCP(List<String> availableIPS,Map<String, Socket> socketMap, Map<String,String> ipsUDPMAP,Socket socket,String sentence){
        try {this.serverSocket= socket;
            this.socketMap=socketMap;
            this.ipsUDPMAP=ipsUDPMAP;
            this.availableIPS=availableIPS;
        this.clientSentence=sentence;}
        catch (Exception exception){System.out.println("Erro ao criar DatagramSocket");}
    }

    public void run(){
        String campos[] = clientSentence.split(" ");
        int jump = Integer.parseInt(campos[1]);
        System.out.println(campos[1])
        if(jump==0){
            sendToServer(campos);
            System.out.println("entrei");
        }else {;sendToUDP(campos);}
    }


    public void sendToUDP(String[] clientSentence){
        try{
        System.out.println(clientSentence[1]);
        Random randomlyChooseIp = new Random();
        int chosenIP = randomlyChooseIp.nextInt(2);
        String ip = availableIPS.get(chosenIP);
        InetAddress address = null;address = InetAddress.getByName(ip.substring(1));
        DatagramSocket dataSocket = new DatagramSocket(8611, address);
        int jump= Integer.parseInt(clientSentence[1]);
        String campos = clientSentence[0] +" " + jump-- +" " + clientSentence[2] +" " + clientSentence[3];
        byte[] buffer = campos.toString().getBytes();
        DatagramPacket data = new DatagramPacket(buffer, buffer.length);
        dataSocket.send(data);
        reentrantLock.unlock();}
        catch (Exception e){}
    }


    public void sendToServer(String[] sentence) {
        try {
            System.out.println(sentence[3]);
            Socket server = new Socket(sentence[3], 10);
            DataOutputStream outToServer = new DataOutputStream(server.getOutputStream());
            String toServer = sentence[0] + " " + sentence[2];
            outToServer.writeBytes(toServer + "\n");
            InputStream input = server.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String reply = reader.readLine();
            server.close();
            replyToClient(reply);
        }
        catch(Exception e){
        }
    }
    public void replyToClient(String reply) throws IOException {
        String[] campos = reply.split(" ");
        reentrantLock.lock();
        Socket client = socketMap.get(campos[1]);
        if (client != null) {
            OutputStream output = new DataOutputStream(client.getOutputStream());
            PrintWriter writer = new PrintWriter(output, true);
            writer.println(campos[2]);
            client.close();
            socketMap.remove(campos[1]);
            reentrantLock.unlock();
        }
    }

}
