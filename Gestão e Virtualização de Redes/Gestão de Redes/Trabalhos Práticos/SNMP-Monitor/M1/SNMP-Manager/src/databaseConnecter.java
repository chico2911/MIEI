import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;

public class databaseConnecter implements Runnable{
    Map<Integer,Process> processMap;
    String adress;
    JSONParser jsonParser = new JSONParser();

    public databaseConnecter(Map<Integer,Process> processMap,String adr){
        this.processMap = new HashMap<>();
        this.adress = adr;
        processMap.forEach((k,v)->this.processMap.put(k,v.clone()));
    }

    public void run() {
        for(Process p : processMap.values()){
            JSONObject process = getProcess(p.getIndex());
            if(!process.isEmpty()){
                JSONArray cpu = (JSONArray) process.get("CPU");
                JSONArray ram = (JSONArray) process.get("RAM");
                cpu.add(p.getCpu());
                ram.add(p.getRam());
                process.replace("CPU",cpu);
                process.replace("RAM",ram);
                process.replace("TIMESTAMP",p.getTimestamp().toString());
                putProcess(process);
            }
            else {
                process = p.toObject();
                postProcess(process);
            }
        }
    }

    public JSONObject getProcess(int id){
        try {
            URL url = new URL("https://json-server-runtime.azurewebsites.net/"+ adress +"?id=" + id);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                JSONArray jsonArray = (JSONArray) jsonParser.parse(response.toString());
                JSONObject jsonObject = (JSONObject) jsonArray.get(0);
                return jsonObject;
            } catch (Exception ignore) {
            }
        }catch (Exception ignore){System.out.println("Erro: "+ignore.getMessage());}
        return new JSONObject();
    }

    public void putProcess(JSONObject process){
        try {
            URL url = new URL("https://json-server-runtime.azurewebsites.net/" + adress + "/" +process.get("id"));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);
            byte[] input = process.toString().getBytes("utf-8");
            try(OutputStream os = conn.getOutputStream()) {
                os.write(input, 0, input.length);
            } catch (Exception ignore) {

            }
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
            } catch (Exception ignore) {
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void postProcess(JSONObject process){
        try {
            URL url = new URL("https://json-server-runtime.azurewebsites.net/"+ adress);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);
            byte[] input = process.toString().getBytes("utf-8");
            try(OutputStream os = conn.getOutputStream()) {
                os.write(input, 0, input.length);
            } catch (Exception ignore) {

            }
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "utf-8"));
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
        }catch (Exception ignore){System.out.println("Erro: "+ignore.getMessage());}

    }
}
