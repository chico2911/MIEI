package Ex_Parque;

import java.util.*;
import java.util.stream.Collectors;

public class Parque{
    private Map<String,Lugar> estacionamento;
    private String nome;

    public Parque(){
        estacionamento = new HashMap<>();
    }
    public Parque(String nome, Map<String,Lugar> estacionamento){
        this.nome = nome;
        setEstacionamento(estacionamento);
    }
    public Parque(Parque parque){
        this.nome=parque.getNome();
        setEstacionamento(parque.getEstacionamento());
    }

    public void setEstacionamento(Map<String,Lugar> est){
        est.entrySet().forEach(e->estacionamento.put(e.getKey(),e.getValue().clone()));
    }
    public Map<String, Lugar> getEstacionamento() {
        return this.estacionamento.values().stream().collect(Collectors.toMap(Lugar::getMatricula, Lugar::clone));
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome){this.nome=nome;}

    //Método que devolve todas as matriculas dos lugares ocupados;
    public Set<String> lugarOcupado(){
        return estacionamento.values().stream().filter(l->l.getMinutos()>0 || l.isPermanente())
                             .map(Lugar::getMatricula).collect(Collectors.toSet());
    }

    //Método que regista uma nova ocupação de lugar;
    public void addLugar(Lugar lgr){
        estacionamento.put(lgr.getMatricula(),lgr.clone());
    }

    //Método que remove o lugar de dada matricula
    public void removeLugar(String matricula){
        estacionamento.remove(matricula);
    }

    //Método que altera o tempo disponível de um lugar, para uma dada matricula;
    public void refactorTime(String matricula,int time){
        estacionamento.get(matricula).setMinutos(time);
    }

    //Método que devolve a quantidade total de minutos atribuídos.
    public int qtTime(){
        return estacionamento.values().stream().mapToInt(Lugar::getMinutos).sum();
    }

    //Método que verifica existe lugar atribuído a uma dada matrícula
    public boolean existeLugar(String matricula){
        return estacionamento.containsKey(matricula);
    }

    //Método que cria uma lista com as matriculas com tempo atribuído > x, em que o lugar seja permanente.
    public List<String> matriculasPermanenteComTempoInt(){
        return estacionamento.values().stream()
                             .filter(e->e.getMinutos()>0 && e.isPermanente())
                             .map(Lugar::getMatricula).collect(Collectors.toList());
    }

    public List<String> matriculasPermanenteComTempoExt(){
        List<String> matriculas = new ArrayList<>();
        for(Lugar lugar : estacionamento.values()){
            if(lugar.getMinutos()>0 && lugar.isPermanente()) matriculas.add(lugar.getMatricula());
        }
        return matriculas;
    }

    //Método que devolve uma cópia dos lugares.
    public Set<Lugar> setCloneLugar(){
        return estacionamento.values().stream().map(Lugar::clone).collect(Collectors.toSet());
    }

    //Método que devolve a informação de um lugar para uma dada matricula.
    public void toStringByMatricula(String matricula){
        StringBuilder sb = new StringBuilder();
        sb.append("Lugares pela Matricula:\n").append(matricula).append("\n").append(this.estacionamento.values());
    }


    public String convertWithStream() {
        String mapAsString = this.estacionamento.keySet().stream()
                .map(key -> key + "=" + this.estacionamento.get(key))
                .collect(Collectors.joining(", ", "{", "}"));
        return mapAsString;
    }
}
