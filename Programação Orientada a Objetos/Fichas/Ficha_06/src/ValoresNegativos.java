public class ValoresNegativos extends Exception{

    public ValoresNegativos(int nr){
        super(String.valueOf(nr));
    }
}