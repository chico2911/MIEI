import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.Timestamp;
import java.util.Date;

public class Process {
    private int index;
    private int cpu;
    private int ram;
    private Timestamp timestamp;

    public Process(int index) {
        Date date = new Date();
        this.index = index;
        timestamp = new Timestamp(date.getTime());
    }

    public Process(Process p) {
        this.index = p.getIndex();
        this.ram = p.getRam();
        this.cpu = p.getCpu();
        this.timestamp = p.getTimestamp();
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getCpu() {
        return cpu;
    }

    public int getRam() {
        return ram;
    }

    public void setCpu(int cpu) {
        this.cpu = cpu;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public Process clone(){
        return new Process(this);
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public JSONObject toObject(){
        JSONObject jsonObject = new JSONObject();
        Date date = new Date();
        JSONArray cpu = new JSONArray();
        JSONArray ram = new JSONArray();
        cpu.add(this.cpu);
        ram.add(this.ram);
        jsonObject.put("id",this.index);
        jsonObject.put("TIMESTAMP", timestamp.toString());
        jsonObject.put("CPU", cpu);
        jsonObject.put("RAM", ram);
        return jsonObject;
    }

    @Override
    public String toString() {
        Date date = new Date();
        return index + "#" + cpu + "#" + ram + "#" + timestamp.toString();
    }
}
