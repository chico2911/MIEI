import java.util.Scanner;

import static java.lang.System.in;

public class Main {
    public static void main(String[] args) {
        ThreadClient newThread = new ThreadClient();
        newThread.start();
        Scanner terminal = new Scanner(in);
        String str = new String();
        while (!str.equals(":exit:")) {
            str = terminal.nextLine();
        }
        System.exit(0);
    }

}
