import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import static java.lang.System.out;
import static java.lang.System.in;

public class Main {

    public static void main(String argv[]) throws Exception {
        Scanner terminal = new Scanner(in);
        String str = new String();
        try{
            ServerSocket server=new ServerSocket(10);
            int counter=0;
            System.out.println("Server Started ....");
            while (!str.equals(":exit:")) {
                counter++;
                Socket newClient=server.accept();  //server accept the client connection request
                System.out.println(" >> " + "Client No:" + counter + " started!");
                ThreadServerOpen sct = new ThreadServerOpen(newClient);; //send  the request to a separate thread
                sct.start();
                str = terminal.nextLine();}
        }catch(Exception e){
        System.out.println(e);}
        System.exit(0);
    }

    public static void tcpConnect(){
        try{
            ServerSocket server=new ServerSocket(10);
            int counter=0;
            System.out.println("Server Started ....");
            while(true){
                counter++;
                Socket newClient=server.accept();  //server accept the client connection request
                System.out.println(" >> " + "Client No:" + counter + " started!");
                ThreadServerOpen sct = new ThreadServerOpen(newClient);; //send  the request to a separate thread
                sct.start();
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
}

