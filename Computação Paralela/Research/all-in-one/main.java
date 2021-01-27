import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class main {
    List<String> list = new ArrayList<>();
    public main(){
        List<String> valores = lerFicheiro("resultados.txt");
        StringBuilder valies = new StringBuilder();
        for(String valo : valores){

            if (valo.startsWith("Sequencial") || valo.startsWith("paraleloSimples") || valo.startsWith("Paralelo") || valo.startsWith("paraleloSingle") || valo.startsWith("Quicksort") ){
                valies.append("\n");
            }
            String newString = valo.replace('.', ',');
            valies.append(newString).append(";");

        }
        logWriter(valies.toString());
    }



    public static void  main(String[] args){
        new main();
    }


    public void logWriter(String input) {
        try {
            PrintWriter fwriter = new PrintWriter(new BufferedWriter(new FileWriter("resultados.csv", false)));
            fwriter.print(input);
            fwriter.close();
        } catch (Exception ignore) {
        }
    }


        public List<String> lerFicheiro(String nomeFich) {
        List<String> lines = new ArrayList<>();
        try { lines = Files.readAllLines(Paths.get(nomeFich)); }
        catch(IOException exc) { exc.printStackTrace(); }
        return lines;
    }
}
