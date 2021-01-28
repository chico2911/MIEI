import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Controller {
    FromFile letsGo;
    Map<String,Integer> TemperaturaPorEstacao;

    public Controller(){
        Parser parser = new Parser("config.txt");
        TemperaturaPorEstacao = parser.config();
        letsGo = new FromFile(TemperaturaPorEstacao);
    }


    public void fromFile(String PathFrom, String PathTo) {
        letsGo.fromFile(PathFrom,PathTo);
    }

    public void openPort(String port){
        int porta = Integer.parseInt(port);
        try {
            ServerSocket newClientAcess = new ServerSocket(porta);
            System.out.println("À escuta em "+ newClientAcess.getLocalSocketAddress());
            while(true){
                Socket s = newClientAcess.accept();
                BufferedReader in = new BufferedReader( new InputStreamReader( s.getInputStream() ) );
                DataOutputStream out = new DataOutputStream( s.getOutputStream() );
                out.writeBytes("dt_iso,city_name,lat,lon,temp,humidity\n");
                while (true) {
                    String input = in.readLine();
                    Registo registo = createRegisto(input);
                    String str = letsGo.manageTemp(registo);
                    out.writeBytes(str+"\n");
                    out.flush();
                }
            }
        }
        catch(Exception e){
            System.out.println("Algo correu mal.");
        }
    }

    private Registo createRegisto(String message){
        String[] campos = message.split(",");
        return new Registo(campos[0],campos[1],campos[2],campos[3],campos[4],campos[5]);
    }

    public void changeConfig(String inv, String out, String prim, String ver){
        StringBuilder strToWrite = new StringBuilder();
        strToWrite.append("Inverno ").append(inv).append("\n");
        strToWrite.append("Outono ").append(out).append("\n");
        strToWrite.append("Primavera ").append(prim).append("\n");
        strToWrite.append("Verao ").append(ver);
        letsGo.logWriter("config.txt",strToWrite.toString());
    }


}
