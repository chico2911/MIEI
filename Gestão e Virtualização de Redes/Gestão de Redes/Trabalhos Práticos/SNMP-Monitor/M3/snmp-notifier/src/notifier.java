
public class notifier {

    public static void main(String[] args){
        try {
            long cpu = Long.parseLong(args[0]);
            long ram = Long.parseLong(args[1]);
            String email = args[2];
            new Parser().now().forEach(e->{
                System.out.println("Started on host: "+e);
                new Thread(new worker(e,cpu,ram,email)).start();
            });
        }catch (Exception e){System.out.println("Ocorreu um problema.");}

    }



}
