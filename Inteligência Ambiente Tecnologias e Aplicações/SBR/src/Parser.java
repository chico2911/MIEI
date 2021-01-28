import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Parser {

    String path;

    public Parser(String path) {
        this.path = path;
    }

    public List<Registo> file(){
        List<String> lines = lerFicheiro(path);
        lines.remove(0);
        List<Registo> registos = new ArrayList<>();
        String al = lines.remove(1);
        String ali = al.replace("\"", "");
        String[] camp = ali.split(",");
        Registo registo = new Registo(camp[0],camp[1],camp[2],camp[3],camp[4],camp[5]);
        registos.add(registo);
        for(String linha : lines){
            if(!linha.equals(al)){
            linha = linha.replace("\"", "");
            String[] campos = linha.split(",");
            registo = new Registo(campos[0],campos[1],campos[2],campos[3],campos[4],campos[5]);
            registos.add(registo);
            al=linha;
            }
        }
        return registos;
    }

    public Map<String,Integer> config(){
        Map<String,Integer> bySeason = new HashMap<>();
        List<String> file = lerFicheiro(path);
        for(String linha : file){
            String[] campos = linha.split(" ");
            bySeason.put(campos[0],Integer.parseInt(campos[1]));
        }
        return bySeason;
    }


    public List<String> lerFicheiro(String nomeFich) {
        List<String> lines = new ArrayList<>();
        try { lines = Files.readAllLines(Paths.get(nomeFich)); }
        catch(IOException exc) { exc.printStackTrace(); }
        return lines;
    }
}