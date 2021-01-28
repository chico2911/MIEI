import java.util.Scanner;
import static java.lang.System.out;
import static java.lang.System.in;

public class Main {

    public static void main(String argv[]) throws Exception {
        ThreadServerOpen newServer = new ThreadServerOpen();
        newServer.start();
        Scanner terminal = new Scanner(in);
        String str = new String();
        while (!str.equals(":exit:")){
            str = terminal.nextLine();
        }
        System.exit(0);
    }
}
