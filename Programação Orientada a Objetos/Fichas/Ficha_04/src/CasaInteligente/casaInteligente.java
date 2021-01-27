package CasaInteligente;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
public class casaInteligente
{
    private String nome;
    private List<Lampada> lampadas;


    public casaInteligente() {
        this.nome=new String();
        this.lampadas=new ArrayList<>();
    }
    public casaInteligente(casaInteligente ci) {
        this.nome=ci.getNome();
        setLampadas(ci.getLampadas());
    }
    public casaInteligente(String nome, List<Lampada> lampadas) {
        this.nome = nome;
        setLampadas(lampadas);
    }

    public void setLampadas(List<Lampada> lps){
        this.lampadas=new ArrayList<>();
        for(Lampada l :lps){
            this.lampadas.add(l.clone());
        }
    }
    public List<Lampada> getLampadas() {
        List<Lampada> aux = new ArrayList<>();
        for(Lampada l : this.lampadas){
            aux.add(l.clone());
        }
        return aux;
    }
    public String getNome() {
        return nome;
    }

    public void addLampada(Lampada l){
        this.lampadas.add(l.clone());
    }

    public void ligaLampadaNormal(int index){
        this.lampadas.get(index).lampON();
    }
    public void ligaLampadaEco(int index){
        this.lampadas.get(index).lampECO();
    }
    public int qtEmEco(){
        return (int) this.lampadas.stream()
                .filter(l->l.getModo() == 2)
                .count();
    }

    public int qtEmEcoIt(){
        int qt = 0;
        Iterator<Lampada> it = this.lampadas.iterator();
        while (it.hasNext()){
            Lampada l = it.next();
            if(l.getModo()==2) qt++;
        }
        return qt;
    }
    public void removeLampada(int index){
        this.lampadas.remove(index);
    }
    public void ligaTodasEco(){
        this.lampadas.stream().forEach(Lampada::lampECO);
    }

    public void ligaTodasMax(){
        this.lampadas.stream().forEach(Lampada::lampON);
    }

    public double consumoTotal(){
        return this.lampadas.stream().mapToLong(Lampada::getTotal).sum();
    }
}
