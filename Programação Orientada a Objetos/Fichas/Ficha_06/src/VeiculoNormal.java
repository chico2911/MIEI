public class VeiculoNormal extends Veiculo {

        public VeiculoNormal(){
            super();
        }
        public VeiculoNormal(String matricula, String marca, String modelo, int anoConstrucao, double avgSpeed,
                                    double avgPrice, double classificacao, int numKms) {
            super(matricula,marca,modelo,anoConstrucao,avgSpeed,avgPrice,classificacao,numKms);
        }
        public VeiculoNormal(VeiculoNormal ev) {
            super(ev.getMatricula(), ev.getMarca(), ev.getModelo(), ev.getAnoConstrucao(), ev.getAvgSpeed(), ev.getAvgPrice(), ev.getClassificacao(), ev.getNumKms());
        }

        public double custoRealKm(){
            return getAvgPrice()*getNumKms();
        }

        public VeiculoNormal clone(){return new VeiculoNormal(this);}

    public String toString() {
        return  super.toString() +
                ";N/A;N/A;N/A;N/A";
    }
}
