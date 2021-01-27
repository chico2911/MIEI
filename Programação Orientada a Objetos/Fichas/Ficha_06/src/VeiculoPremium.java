public class VeiculoPremium extends Veiculo implements BonificaKms {
    double taxa;
    int pontosKM;

    public VeiculoPremium(){
        super();
        this.taxa=0;
        this.pontosKM=0;
    }
    public VeiculoPremium(String matricula, String marca, String modelo, int anoConstrucao, double avgSpeed,
                          double avgPrice, double classificacao, int numKms, double taxa,int pontosKM) {
        super(matricula,marca,modelo,anoConstrucao,avgSpeed,avgPrice,classificacao,numKms);
        this.taxa = taxa;
        this.pontosKM=pontosKM;
    }
    public VeiculoPremium(VeiculoPremium ev){
        super(ev.getMatricula(),ev.getMarca(),ev.getModelo(),ev.getAnoConstrucao(),ev.getAvgSpeed(),ev.getAvgPrice(),ev.getClassificacao(),ev.getNumKms());
        taxa=ev.getTaxa();
        pontosKM=ev.getPtsPorKm();
    }

    public double getTaxa() {
        return taxa;
    }
    public void setTaxa(double taxa) {
        this.taxa = taxa;
    }

    public Double getCustoKm() {
        return (1+taxa) * super.getAvgPrice();
    }
    public double custoRealKm(){
        return getNumKms() * getCustoKm();
    }

    public VeiculoPremium clone(){return new VeiculoPremium(this);}

    @Override
    public String toString() {
        return  super.toString() + ";N/A;N/A;" + pontosKM +';' +taxa;
    }

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
}
