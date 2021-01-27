import java.io.IOException;

public class testeDriveIT {
    public static void main(String[] opts) throws IOException, VeiculoExistente, ClassNotFoundException {

        Veiculo v1 = new VeiculoNormal("AA00AA","Fiat","Panda",1996,50.0,6.0,3,10);
        VeiculoPremium v2 = new VeiculoPremium("AA01AA","Rolls-Royce","Phantom",2014,30.0,20,5,10,0.350,1);
        VeiculoOcasiao v3 = new VeiculoOcasiao("AA02AA","Rolls-Royce","Phantom",2014,30.0,8,5,10,true);
        AutocarroInteligente v4 = new AutocarroInteligente("AA03AA","Man","ESSE",420,75,2.33,10,36,5,45);
        DriveIT d = new DriveIT();


        d.adiciona(v1);
        d.adiciona(v2);
        d.adiciona(v3);
        d.adiciona(v4);

        d.gravaObj("driveIt.obj");

        DriveIT it = new DriveIT();
        it = it.lerObj("driveIt.obj");

        System.out.println(it);

    }
}
