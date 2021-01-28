import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

public class SBR {
    View view = new View();
    Controller controller = new Controller();


    public SBR(String[] args) {
        try {
            if (args.length != 0) {
                switch (args[0]) {
                    case "-f":
                        controller.fromFile(args[1], args[2]);
                        break;
                    case "-h": {
                        view.help();
                        break;
                    }
                    case "-i": {
                        controller.openPort(args[1]);
                        break;
                    }
                    case "-w":
                        controller.changeConfig(args[1], args[2], args[3], args[4]);
                        break;
                }
            } else view.help();
        }catch(Exception e){view.help();}
    }



    public static void main(String[] args){
        new SBR(args);
    }
}
