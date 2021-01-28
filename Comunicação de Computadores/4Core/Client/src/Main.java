import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class Main {
    public static void main(String[] args) {
        out.println("Insira o número de saltos, o comando e o IP do server separado por \" \"");
        Scanner terminal = new Scanner(in);
        String str = new String();
        while (!str.equals(":exit:")) {
            String[] campos = str.split(" ");
            if(campos.length==3){
            ThreadClient newThread = new ThreadClient(Integer.parseInt(campos[0]),campos[1],campos[2]);
            newThread.sendToServer();
            }
            str = terminal.nextLine();
        }
        System.exit(0);
    }
}
