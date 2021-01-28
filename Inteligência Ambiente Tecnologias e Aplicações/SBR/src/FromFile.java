import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class FromFile {
    List<Registo> registers;
    Map<String,Integer> TemperaturaPorEstacao;

    public FromFile(Map<String,Integer> fromController){TemperaturaPorEstacao = fromController;}

    public void fromFile(String PathFrom, String PathTo){
        Parser parser = new Parser(PathFrom);
        System.out.println("Parsing File...");
        registers = parser.file();
        System.out.println("Parsing Complete...");
        System.out.println("Writing Log...");
        StringBuilder regis = new StringBuilder();
        for(Registo r : registers){
            regis.append(manageTemp(r)+"\n");
        }
        regis.append(getGreatestTemp()).append("\n");
        regis.append(getLowestTemp());
        logWriter(PathTo,regis.toString());
        System.out.println("Done.");
    }

    public void logWriter(String path,String input){
        try {
            PrintWriter fwriter = new PrintWriter(new BufferedWriter(new FileWriter(path, false)));
            fwriter.print(input);
            fwriter.close();
        }catch (Exception ignore){}


    }

    public String manageTemp(Registo r){
        int temperatura;
        String season = "";
        String str = "";
        switch(r.getSeason()){
            case 1:
                season = "Primavera";
                break;
            case 2:
                season = "Verao";
                break;
            case 3:
                season = "Outono";
                break;
            default:
                season = "Inverno";
                break;
        }
        temperatura = getBestTemp(r.getHumidity(),season);
        double temp;
        temp = temperatura - r.getTemperature();
        if(temp>0){
            str= season+ " airconditioning+" + temp ;
        }
        else{
            str=season+" airconditioning-" + temp*-1;
        }
        return str;
    }

    private int getBestTemp(double humidity,String season){
        int temperatura=0;
        switch(season){
            case "Verao":
                temperatura = TemperaturaPorEstacao.get("Verao");
                break;
            case "Inverno":
                temperatura = TemperaturaPorEstacao.get("Inverno");
                break;
            case "Outono":
                temperatura = TemperaturaPorEstacao.get("Outono");
                break;
            case "Primavera":
                temperatura = TemperaturaPorEstacao.get("Primavera");
                break;
        }

        return temperatura;
    }

    private String getGreatestTemp(){
        Registo registo = null; double temp=Double.MIN_VALUE;
        for(Registo r : registers){
            if(r.getTemperature()>temp){
                registo = r;
                temp = r.getTemperature();
            }
        }
        if(registo == null) return "Não há registos";
        return "Maior temperatura registada foi no registo: " + registo.toString();
    }

    private String getLowestTemp(){
        Registo registo = null; double temp=Double.MAX_VALUE;
        for(Registo r : registers){
            if(r.getTemperature()<temp){
                registo = r;
                temp = r.getTemperature();
            }
        }
        if(registo == null) return "Não há registos";
        return "Menor temperatura registada foi no registo: " + registo.toString();
    }

}
