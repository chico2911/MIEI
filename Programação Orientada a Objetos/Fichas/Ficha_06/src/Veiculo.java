import java.io.Serializable;
import java.util.Objects;

public abstract class Veiculo implements Comparable<Veiculo>, Serializable {
    private String matricula;
    private String marca;
    private String modelo;
    private int anoConstrucao;
    private Double avgSpeed;
    private Double avgPrice;
    private Double classificacao;
    private int numKms;
    public Veiculo(){
        this.matricula = "";
        this.marca = "";
        this.modelo = "";
        this.anoConstrucao = 2020;
        this.avgSpeed = 0.0;
        this.avgPrice = 0.0;
        this.classificacao = 0.0;
        this.numKms=0;
    }
    public Veiculo(String matricula, String marca, String modelo,
                   int anoConstrucao, Double avgSpeed, Double avgPrice, Double classificacao,int numKms) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.anoConstrucao = anoConstrucao;
        this.avgSpeed = avgSpeed;
        this.avgPrice = avgPrice;
        this.classificacao = classificacao;
        this.numKms=numKms;
    }
    public Veiculo(Veiculo velo){
        this.matricula = velo.getMatricula();
        this.marca = velo.getMarca();
        this.modelo = velo.getModelo();
        this.anoConstrucao = velo.getAnoConstrucao();
        this.avgSpeed = velo.getAvgSpeed();
        this.avgPrice = velo.getAvgPrice();
        this.classificacao = velo.getClassificacao();
        this.numKms=velo.getNumKms();
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getAnoConstrucao() {
        return anoConstrucao;
    }

    public void setAnoConstrucao(Integer anoConstrucao) {
        this.anoConstrucao = anoConstrucao;
    }

    public Double getAvgSpeed() {
        return avgSpeed;
    }

    public void setAvgSpeed(Double avgSpeed) {
        this.avgSpeed = avgSpeed;
    }

    public Double getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(Double avgPrice) {
        this.avgPrice = avgPrice;
    }

    public Double getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(Double classificacao) {
        this.classificacao = classificacao;
    }

    public int getNumKms() {
        return numKms;
    }

    public void setNumKms(int numKms) {
        this.numKms = numKms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Veiculo veiculo = (Veiculo) o;
        return  Objects.equals(matricula, veiculo.matricula);

    }


    public String toString() {
        return matricula +
                ';' + marca +
                ';' + modelo +
                ';' + anoConstrucao +
                ';' + avgSpeed +
                ';' + avgPrice +
                ';' + classificacao +
                ";" + numKms;
    }

    public abstract Veiculo clone();
    public abstract double custoRealKm();

    public int compareTo(Veiculo v){
        if(this.marca.equals(v.getMarca()))
            return this.modelo.compareTo(v.getModelo());
        return this.marca.compareTo(v.getMarca());
    }
}
