import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.spi.LocaleServiceProvider;

import static java.lang.System.clearProperty;
import static java.lang.System.out;

public class ThreadServerOpen extends Thread {
    private Socket newClientAcess;
    public ThreadServerOpen(Socket newClientAcess) {this.newClientAcess=newClientAcess;
    }

    public void run(){
        try {
            String clientSentence;
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(newClientAcess.getInputStream()));
            clientSentence = inFromClient.readLine();
            OutputStream output = new DataOutputStream(newClientAcess.getOutputStream());
            PrintWriter writer = new PrintWriter(output, true);
            writer.println(replyMessage(clientSentence));
            newClientAcess.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public String replyMessage(String receivedComand){
        String[] campos = receivedComand.split(" ");
        String reply = new String();
        StringBuilder builder = new StringBuilder();
        builder.append("Answer" + " " + campos[0]+" ");
        switch (campos[1]){
            case "Data": builder.append(LocalDate.now().toString());
                        break;
            case "data": builder.append(LocalDate.now().toString());
                        break;
            case "Horas": builder.append(LocalDateTime.now().getHour());break;
            case "horas": builder.append(LocalDateTime.now().getHour());break;
            default: builder.append("h");
        }
        reply=builder.toString();
        System.out.println(reply);
        return reply;
    }
}
