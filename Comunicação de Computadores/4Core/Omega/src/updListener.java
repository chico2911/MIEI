import java.io.DataOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class updListener extends Thread{
    private Map<String, Socket> socketMap;
    private Map<String,String> ipsUDPMAP;
    private List<String> availableIPS;
    private static ReentrantLock reentrantLock = new ReentrantLock();

    private DatagramSocket serverSocket;
    public updListener(List<String> availableIPS,Map<String, Socket> socketMap, Map<String,String> ipsUDPMAP){
        try {serverSocket = new DatagramSocket(8611);
            this.socketMap=socketMap;
            this.ipsUDPMAP=ipsUDPMAP;
        this.availableIPS=availableIPS;}
        catch (Exception exception){System.out.println("Erro ao criar DatagramSocket");}
    }

    public void run(){

    }

    public void listeningUDPrequests(){
        try {
            while (true) {
                byte[] buffer = new byte[1024];
                DatagramPacket requestUDP = new DatagramPacket(buffer,buffer.length);
                serverSocket.receive(requestUDP);
                String sentence = new String(buffer); String campos[] = sentence.split(" ");
                if(campos[0]=="Reply"){
                    reentrantLock.lock();
                    Socket client = socketMap.get(campos[1]);
                    if(client!=null) {
                        OutputStream output = new DataOutputStream(client.getOutputStream());
                        PrintWriter writer = new PrintWriter(output, true);
                        writer.println(campos[2]);
                        client.close(); socketMap.remove(campos[1]);
                    } else{ThreadUDP udpT = new ThreadUDP(ipsUDPMAP,availableIPS,campos); udpT.start();}
                    reentrantLock.unlock();} else {
                    reentrantLock.lock();
                    ipsUDPMAP.put(campos[0],requestUDP.getAddress().toString());
                    reentrantLock.unlock();
                    ThreadUDP udpT = new ThreadUDP(ipsUDPMAP,availableIPS,campos); udpT.start();}
                }
        }catch (Exception exception){System.out.println("Erro ao aceitar pedido udp");}
    }

}
