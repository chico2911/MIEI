public class VeiculoOcasiao extends Veiculo{

    private boolean promocao;

    public VeiculoOcasiao(){
        super();
        this.promocao = false;
    }

    public VeiculoOcasiao(String matricula, String marca, String modelo, int anoConstrucao, double avgSpeed,
                          double avgPrice, double classificacao, int numKms, boolean promocao){

        super(matricula,marca,modelo,anoConstrucao,avgSpeed,avgPrice,classificacao,numKms);
        this.promocao = promocao;
    }

    public VeiculoOcasiao(VeiculoOcasiao ao){
        super(ao);
        this.promocao = ao.getPromocao();
    }

    public boolean getPromocao(){
        return this.promocao;
    }

    public void setPromocao(boolean p){
        this.promocao = p;
    }

    public double getPrecoKm(){
        return this.promocao ? 0.75 * super.getAvgPrice(): super.getAvgPrice();
    }

    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append(super.toString());
        s.append(";");
        if(this.promocao) s.append("Em Promocao:").append(getPrecoKm());
        else s.append("Sem Promocao");
        s.append(";N/A;N/A;N/A");
        return s.toString();
    }


    public VeiculoOcasiao clone(){
        return new VeiculoOcasiao(this);
    }

    public double custoRealKm(){
        return getNumKms() * getPrecoKm();
    }


}
