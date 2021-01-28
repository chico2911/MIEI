import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import static java.lang.Thread.sleep;

public class worker implements Runnable{
    private String adress;
    long ram; long cpu; String email;
    sendEmail sendEmail;

    public worker(String adr,long cpu,long ram,String email){
        this.adress = adr;
       this.ram = ram;
       this.cpu = cpu;
       this.email = email;
       sendEmail = new sendEmail();
    }




    @Override
    public void run() {
        try {
            /*while (true) {
                JSONArray jsonArray = getProcess();
               jsonArray.forEach(e -> {
                    JSONObject jsonObject = (JSONObject) e;
                    process process = new process(jsonObject);
                    new Thread(new classifier(process,cpu,ram)).start();
                });
                sleep(60000);
            }*/
            JSONArray jsonArray = getProcess();
            JSONObject jsonObject = (JSONObject) jsonArray.get(0);
            process process = new process(jsonObject);
            new Thread(new classifier(process,cpu,ram)).start();
            }catch (Exception e){System.out.println("Não Existem Processos");}


    }


    public JSONArray getProcess(){
        try {
            URL url = new URL("https://json-server-runtime.azurewebsites.net/"+ adress );
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
                return jsonArray;
            } catch (Exception ignore) {
            }
        }catch (Exception ignore){}
        return new JSONArray();
    }


    private class classifier implements Runnable{
        private process process; long max_value_cpu; long max_value_ram;

        public classifier(process process,long cpu, long ram) {
            max_value_cpu = cpu;
            max_value_ram = ram;
            this.process = process;
        }

        @Override
        public void run() {
            if(process.getCpu()>max_value_cpu || process.getRam()>max_value_ram){
                sendEmail.run(email,"Processo "+process.getId() +" no host "+ adress +" com comportamento estrando\n CPU: " + process.getCpu()
                +" RAM: " + process.getRam());
            }
        }
    }
}
