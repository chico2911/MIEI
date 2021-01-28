import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Parser {
    String path;

    public Parser(String path){
        this.path = path;
    }

    public List<Host> now(){
        List<Host> toDelivery = new ArrayList<>();
        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader(path));

            JSONObject jsonObject =  (JSONObject) obj;
            JSONArray hosts = (JSONArray) jsonObject.get("hosts");
            for(Object host : hosts){
                JSONObject realHost = (JSONObject) host;
                String adress = realHost.get("adress").toString();
                String port = realHost.get("port").toString();
                String communityTarget = realHost.get("communityTarget").toString();
                Host toList = new Host(adress,port,communityTarget);
                toDelivery.add(toList);
            }

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return toDelivery;
    }

}

