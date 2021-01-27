public class AutocarroInteligente extends Veiculo implements BonificaKms{
    double ocupacao;
    int pontosKM;

    public AutocarroInteligente(){
        super();
        this.ocupacao=0;
        this.pontosKM=0;
    }
    public AutocarroInteligente(String matricula, String marca, String modelo, int anoConstrucao, double avgSpeed,
                          double avgPrice, double classificacao, int numKms, double ocupacao,int pontosKM) {
        super(matricula,marca,modelo,anoConstrucao,avgSpeed,avgPrice,classificacao,numKms);
        this.ocupacao = ocupacao;
        this.pontosKM=pontosKM;
    }
    public AutocarroInteligente(AutocarroInteligente ev){
        super(ev.getMatricula(),ev.getMarca(),ev.getModelo(),ev.getAnoConstrucao(),ev.getAvgSpeed(),ev.getAvgPrice(),ev.getClassificacao(),ev.getNumKms());
        ocupacao=ev.getOcupacao();
        pontosKM=ev.getPtsPorKm();
    }

    public double getOcupacao() {
        return ocupacao;
    }
    public void setOcupacao(double ocupacao) {
        this.ocupacao = ocupacao;
    }
    public double custoRealKm() {
        double value = getOcupacao();
        double tax = 0;
        if(value<=0.6) tax=0.5;
        else tax=0.25;
    return super.getAvgPrice()*super.getNumKms()*tax;
    }
    public AutocarroInteligente clone(){return new AutocarroInteligente(this);}


    @Override
    public void definirPtsPorKm(int pts) {
        this.pontosKM=pts;
    }

    @Override
    public int getPtsPorKm() {
        return pontosKM;
    }

    @Override
    public double getPtsVeiculo(Veiculo v) {
        return pontosKM * v.getNumKms();
    }

    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append(super.toString());
        s.append(';').append("N/A").append(';').append(ocupacao).append(';').append(pontosKM).append(";N/A");
        return s.toString();
    }

}
