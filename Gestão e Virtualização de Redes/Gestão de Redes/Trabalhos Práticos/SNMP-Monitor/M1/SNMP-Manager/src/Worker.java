import org.ietf.jgss.Oid;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.snmp4j.smi.OID;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Thread.sleep;

public class Worker implements Runnable{
    private SNMPClient snmp;
    private Thread thread = new Thread( );
    Host host;
    int time;
    boolean online = true;
    Map<Integer,Process> processMap;

    public Worker(Host host,int time,String flag) {
        this.host = host;
        this.time = time * 1000;
        snmp      = new SNMPClient(host.getAdress(),host.getPort(),host.getCommunityTarget());
        if(flag.equals("-off")) online=false;
    }

    public Worker(String flag) {
        host = null;
        time = 30000;
        snmp      = new SNMPClient();
        if(flag.equals("-off")){ online=false;}
        else{
            //new Thread(this::registerHost).start();
        }
    }



    @Override
    public void run() {
        while (true) {
            System.out.println("Refreshing: " + host.getAdress() );
            try {
                processMap = new HashMap<>();
                snmp.start();
                writeTimeStamp();
                giveMapEntrys(snmp.doWalk(".1.3.6.1.2.1.25.4.2.1.1"));
                giveMapRamEntrys(snmp.doWalk( ".1.3.6.1.2.1.25.5.1.1.2"));
                giveMapCPUEntrys(snmp.doWalk( ".1.3.6.1.2.1.25.5.1.1.1"));
                writeLog();
                if(online){

                thread = new Thread(new databaseConnecter(processMap,host.getAdress()));thread.start();
                    thread.join();}
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

        }
    }

    private void giveMapEntrys(Map<OID,String> result){
        processMap = new HashMap<>();
        result.forEach((k,v)->processMap.put(Integer.parseInt(v),new Process(Integer.parseInt(v))));
    }

    private void giveMapRamEntrys(Map<OID,String> result){
        result.forEach((k,v)-> {
            processMap.get(k.last()).setRam(Integer.parseInt(v));
        });
    }



    private void writeTimeStamp(){
        try {
            FileOutputStream outputStream = new FileOutputStream("/home/chico/Documents/manager/"+host.getAdress()+".txt",true);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-16");
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
            Date date = new Date(System.currentTimeMillis());
            bufferedWriter.write(formatter.format(date)+"\n");
            bufferedWriter.close();
        } catch (Exception ignore){}
    }

    private void giveMapCPUEntrys(Map<OID,String> result){
        result.forEach((k,v)-> {
            processMap.get(k.last()).setCpu(Integer.parseInt(v));
        });
    }

    private void writeLog(){
        try {
            FileOutputStream outputStream = new FileOutputStream("/home/chico/Documents/manager/"+host.getAdress()+".txt",true);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-16");
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            processMap.forEach((k,v)-> {
                try {
                    bufferedWriter.write(v.toString() + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            bufferedWriter.close();
        } catch (Exception ignore){}
    }

    public void registerHost(){
        try {
            URL url = new URL("https://json-server-runtime.azurewebsites.net/recursos");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                JSONArray jsonArray = (JSONArray) new JSONParser().parse(response.toString());
                if(jsonArray.size()>0){
                    boolean flag = false;
                    for (Object o : jsonArray){
                        JSONObject jsonObject = (JSONObject) o;
                        if(jsonObject.get("id").equals(host)){flag=true;}
                    }
                    if(!flag){
                        postHost();
                    }
                }
                else {postHost();}
            } catch (Exception ignore) {
            }
        }catch (Exception ignore){}
    }

    public void postHost(){
        try {
            URL url = new URL("https://json-server-runtime.azurewebsites.net/recursos");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id",host);
            byte[] input = jsonObject.toString().getBytes("utf-8");
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
        }catch (Exception ignore){}
    }




}
