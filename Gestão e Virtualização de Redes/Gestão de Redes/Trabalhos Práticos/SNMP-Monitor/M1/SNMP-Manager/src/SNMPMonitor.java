import java.util.ArrayList;
import java.util.List;

public class SNMPMonitor {
    Parser giveMeHosts; //Class to parse json file, to get info from hosts
    List<Worker> threadWorkers; //soIcanKill all threads before shutting down.
    List<Host> hostList;

    public SNMPMonitor(String[] arg){
        threadWorkers = new ArrayList<>();
        hostList = new ArrayList<>();
        giveMeHosts = new Parser("config.json");
        hostList = giveMeHosts.now();
        System.out.println("Starting Client...");
        if(arg.length>0){
            createWorkers(arg[0]);
            if(!arg[0].equals("-off")) System.out.println("Starting Offline...");
        }
        createWorkers("online");
    }




    public void createWorkers(String flag){
        if(hostList.size()==0){
            Worker worker = new Worker(flag);
            threadWorkers.add(worker);
            worker.run();
        }
        else {
            for(Host host : hostList){
                Worker worker = new Worker(host,30,flag);
                new Thread(worker).start();
                threadWorkers.add(worker);
            }
        }

    }


    public static void main(String[] arg){
        new SNMPMonitor(arg);
    }

}
