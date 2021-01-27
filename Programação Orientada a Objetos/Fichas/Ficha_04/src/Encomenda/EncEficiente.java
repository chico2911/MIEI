package Encomenda;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

public class EncEficiente {
    //Variaveis de Instância
    private String nomeCli;
    private int nifCli;
    private String moradaCli;
    private int noEnc;
    private LocalDate data;
    private ArrayList<LinhaEncomenda> linhas;

    public void main(){
        LinhaEncomenda le = new LinhaEncomenda("6593431", "Telemóvel",
                13, 300, 0.23, 0.20);
        LinhaEncomenda le1 = new LinhaEncomenda("6593431", "Telemóvel",
                13, 300, 0.23, 0.20);
        LinhaEncomenda le2 = new LinhaEncomenda("6593431", "Telemóvel",
                13, 300, 0.23, 0.20);
        ArrayList<LinhaEncomenda> linha = new ArrayList<>();
        linha.add(le); linha.add(le2);linha.add(le1);
        EncEficiente Ola = new EncEficiente("Ze",2655685,"Rua da Uni",1,LocalDate.now(),linha);
        System.out.println("Valor total de Produtos"+Ola.calculaValorTotal());
        System.out.println("Desconto total de Produtos"+Ola.calculaValorDesconto());
        System.out.println("Numero total de Produtos"+Ola.numeroTotalProdutos());
        if(Ola.existeProdutoEncomenda("6593431")) System.out.println("Existe produto");
        LinhaEncomenda le3 = new LinhaEncomenda("12345", "Telemóvel",
                13, 300, 0.23, 0.20);
        Ola.adicionaLinha(le3);
        System.out.println(Ola.toString());


    }
    //construtores de classe
    public EncEficiente(String nomeCli, int nifCli, String moradaCli, int noEnc, LocalDate data, ArrayList<LinhaEncomenda> linhas) {
        this.nomeCli = nomeCli;
        this.nifCli = nifCli;
        this.moradaCli = moradaCli;
        this.noEnc = noEnc;
        this.data = data;
        setEncomendas(linhas);
        ;
    }

    public EncEficiente() {
        this.nomeCli = "n/a";
        this.nifCli = 0;
        this.moradaCli = "n/a";
        this.noEnc = 0;
        this.data = LocalDate.now();
        this.linhas = new ArrayList<LinhaEncomenda>();
    }

    public EncEficiente(EncEficiente enc){
        this.nomeCli = enc.getNomeCli();
        this.nifCli = enc.getNifCli();
        this.moradaCli = enc.getMoradaCli();
        this.noEnc = enc.getNoEnc();
        this.data = enc.getData();
        this.linhas = enc.getLinhas();
    }

    //getters and setters
    public String getNomeCli() {
        return nomeCli;
    }

    public void setNomeCli(String nomeCli) {
        this.nomeCli = nomeCli;
    }

    public int getNifCli() {
        return nifCli;
    }

    public void setNifCli(int nifCli) {
        this.nifCli = nifCli;
    }

    public String getMoradaCli() {
        return moradaCli;
    }

    public void setMoradaCli(String moradaCli) {
        this.moradaCli = moradaCli;
    }

    public int getNoEnc() {
        return noEnc;
    }

    public void setNoEnc(int noEnc) {
        this.noEnc = noEnc;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setEncomendas(ArrayList<LinhaEncomenda> linhasEnc) {
        this.linhas = new ArrayList<LinhaEncomenda>();
        for(LinhaEncomenda le : linhasEnc) {
            this.linhas.add(le.clone());
        }
    }

    public ArrayList<LinhaEncomenda> getLinhas() {
        return this.linhas;
    }

    //Converter em String
    public String toString() {
        return "EncEficiente{" +
                "nomeCli='" + nomeCli + '\'' +
                ", nifCli=" + nifCli +
                ", moradaCli='" + moradaCli + '\'' +
                ", noEnc=" + noEnc +
                ", data=" + data +
                ", linhas=" + linhas.toString() +
                '}';
    }

    //verificar se sao iguais
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EncEficiente that = (EncEficiente) o;
        return nifCli == that.nifCli &&
                noEnc == that.noEnc &&
                nomeCli.equals(that.nomeCli) &&
                moradaCli.equals(that.moradaCli) &&
                data.equals(that.data) &&
                linhas.equals(that.linhas);
    }

    //clone
    public EncEficiente clone() {
        return new EncEficiente(this);
    }
    //b método que determina o valor total da encomenda
    public double calculaValorTotal(){
        return this.linhas.stream().mapToDouble(LinhaEncomenda::calculaValorLinhaEnc).sum();
    }

    //c.método que determina o valor total dos descontos obtidos nos diversos produtos encomendados
    public double calculaValorDesconto(){
        return this.linhas.stream().mapToDouble(LinhaEncomenda::calculaValorDesconto).sum();
    }

    //d.método que determina o número total de produtos a receber
    public int numeroTotalProdutos(){
        return this.linhas.stream().mapToInt(LinhaEncomenda::getQuantidade).sum();
    }

    //e.método que determina se um produto vai ser encomendado
    public boolean existeProdutoEncomenda(String codProd) {
        boolean existe = false; int i=0;
        while(!existe && i<this.linhas.size()) {
            if(this.linhas.get(i).getReferencia().equals(codProd))
                existe = true;
            i++;
        }
        return existe;
    }
    //f.método que adiciona uma nova linha de encomenda
    public void adicionaLinha(LinhaEncomenda linha){
        this.linhas.add(linha.clone());
    }

    //g.método que remove uma linha de encomenda dado a referência do produto
    public void removeProduto(String codProd){
        for(Iterator<LinhaEncomenda> it = this.linhas.iterator(); it.hasNext();){
            LinhaEncomenda le = it.next();
            if(le.getReferencia().equals(codProd)){
                it.remove();
            }
        }
    }


}

