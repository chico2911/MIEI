import java.net.Socket;
import java.rmi.server.ExportException;
import java.util.*;

import static java.lang.System.out;
import static java.lang.System.in;

public class Main {
    private static Map<String, Socket> socketMap ;
    private static Map<String,String> ipsUDPMA;
    private static List<String> availableIPS;

   public static void main(String[] args) {
        socketMap = new HashMap<>();
        ipsUDPMA = new HashMap<>();
        availableIPS = new ArrayList<>();
        availableIPS.add("10.4.4.3");
        availableIPS.add("10.2.2.1");
        availableIPS.add("10.2.2.2");
        tcpListener tcp = new tcpListener(availableIPS,socketMap,ipsUDPMA);
        updListener udp = new updListener(availableIPS,socketMap,ipsUDPMA);
        tcp.start(); udp.start();
   }
}
