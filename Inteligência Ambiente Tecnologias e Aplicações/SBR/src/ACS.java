import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class ACS {
    static Socket newConnection;

    public static void main(String[] args) {
        Scanner terminal = new Scanner(System.in);
        try {

            String str = new String();
            sendToServer();
        }
        catch(Exception e){e.printStackTrace();}
        System.exit(0);
    }

    public static void sendToServer(){
        try {
            Socket clientSocket = new Socket( "localhost", 8080 );
            DataOutputStream out = new DataOutputStream( clientSocket.getOutputStream() );
            BufferedReader in =
                    new BufferedReader( new InputStreamReader( clientSocket.getInputStream() ) );
            System.out.println( in.readLine() );

            while ( true ) {
                Scanner sc = new Scanner( System.in );
                String msg = sc.nextLine();
                out.writeBytes( msg + "\n" );
                System.out.println(in.readLine() );

            }
        }
        catch(Exception ignored){
        }
    }

}
