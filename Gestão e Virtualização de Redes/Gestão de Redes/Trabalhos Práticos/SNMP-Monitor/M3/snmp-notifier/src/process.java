
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;

public class process {
    long id;
    long ram;
    long cpu;
    String timestamp;

    public process(JSONObject jsonObject) {
        id = (long) jsonObject.get("id");
        JSONArray ramArray = (JSONArray) jsonObject.get("RAM");
        JSONArray cpuArray = (JSONArray) jsonObject.get("CPU");
        ram = (long) ramArray.get(ramArray.size()-1);
        cpu = (long) cpuArray.get(ramArray.size()-1);
        timestamp = (String) jsonObject.get("TIMESTAMP");
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRam() {
        return ram;
    }

    public void setRam(long ram) {
        this.ram = ram;
    }

    public long getCpu() {
        return cpu;
    }

    public void setCpu(long cpu) {
        this.cpu = cpu;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }


}
