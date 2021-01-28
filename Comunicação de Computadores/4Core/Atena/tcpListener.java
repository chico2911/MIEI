import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.SecureRandom;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class tcpListener extends Thread {
    private Map<String, Socket> socketMap;
    private Map<String,String> ipsUDPMAP;
    private List<String> availableIPS;
    private ServerSocket serverSocket;
    protected static SecureRandom random = new SecureRandom();
    private static ReentrantLock reentrantLock = new ReentrantLock();
    public tcpListener(List<String> availableIPS,Map<String, Socket> socketMap, Map<String,String> ipsUDPMAP){
        try {this.serverSocket= new ServerSocket(75);
            this.socketMap=socketMap;
            this.ipsUDPMAP=ipsUDPMAP;
            this.availableIPS=availableIPS;}
        catch (Exception exception){exception.printStackTrace();}
    }

    public void run() {
       try {
           while (true){
               reentrantLock.lock();
               Socket newClient = serverSocket.accept();
               System.out.println("novo cliente!!");
               long longToken = Math.abs( random.nextLong() );
               String random = Long.toString( longToken, 16 );
               socketMap.put(random,newClient);
               BufferedReader inFromClient =
                       new BufferedReader(new InputStreamReader(newClient.getInputStream()));
               String clientSentence = random + " " + inFromClient.readLine();
                ThreadTCP threadTCP = new ThreadTCP(availableIPS,socketMap,ipsUDPMAP,newClient,clientSentence);
                threadTCP.start();
               reentrantLock.unlock();
           }
       }catch (Exception e){}
    }
}
