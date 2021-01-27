package Ex_Turma;

import java.util.*;

public class Turma {
    private String nome;
    private String codigo;
    Map<String,Aluno> alunos;

    public Turma(){
        nome   = new String();
        codigo = new String();
        alunos = new HashMap<>();
    }
    public Turma(String nome,String codigo,Map<String,Aluno> alunos){
        this.nome   = nome;
        this.codigo = codigo;
        setAlunos(alunos);
    }
    public Turma(Turma t){
        this.nome   = t.getNome();
        this.codigo = t.getCodigo();
        setAlunos(t.getAlunos());
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getNome(){return nome;}

    public void setCodigo(String codigo){this.codigo=codigo;}
    public String getCodigo(){return codigo;}

    public void setAlunos(Map<String, Aluno> als) {
        this.alunos = new HashMap<>();
        als.entrySet().forEach(e-> this.alunos.put(e.getKey(),e.getValue().clone()));
        //this.alunos = als.entrySet().stream().collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone()));
    }
    public Map<String,Aluno> getAlunos(){
        Map<String,Aluno> als = new HashMap<>();
        this.alunos.forEach((key, value) -> als.put(key, value.clone()));
        return als;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Ex_Turma.Turma:\n").append(this.nome).append("\n").append(this.codigo).append("\n").append(this.alunos);
        return sb.toString();
    }

    public Turma clone(){
        return new Turma(this);
    }

    public boolean equals (Object o){
        if(o == this) return true;
        if(o == null || o.getClass() != this.getClass()) return false;
        Turma t = (Turma) o;
        return this.nome.equals(t.getNome()) && this.codigo.equals(t.getCodigo()) && this.alunos.equals(t.getAlunos());
    }

    public int compareTo(Turma t){
        return this.nome.compareTo(t.getNome());
    }




    //ii
    public void insereAluno(Aluno a){
        this.alunos.put(a.getNumero(),a.clone());
    }
    //iii
    public Aluno getAluno(String codigo){
        return this.alunos.get(codigo);
    }
    //iv
    public void removeAluno(String codigo){
        this.alunos.remove(codigo);
    }
    //v.
    public Set<String> todosOsCodigos(){
        return new HashSet<>(this.alunos.keySet());
    }
    //vi.
    public int qtsAlunos(){
        return this.alunos.size();
    }
    //vii.
    public Set<Aluno> alunosOrdemAlfabetica(){
        Set<Aluno> alsorted = new TreeSet<>((a1, a2) -> {
            if(a1.getNome().compareTo(a2.getNome()) < 0) return -1;
            if(a1.getNome().compareTo(a2.getNome()) > 0) return 1;
            return a1.getNome().compareTo(a2.getNome());
        });
        alunos.values().forEach(e->alsorted.add(e.clone()));
        return alsorted;
    }
    //viii.
    public Set<Aluno> alunosOrdemDescrescenteNumero(){
        Set<Aluno> Alsorted = new TreeSet<>((a1, a2) -> {
            if(a1.getNumero().compareTo(a2.getNumero()) < 0) return 1;
            if(a1.getNumero().compareTo(a2.getNumero()) > 0) return -1;
            return a1.getNome().compareTo(a2.getNome());
        });
        for(Aluno als : this.alunos.values()){
            Alsorted.add(als);
        }
        return Alsorted;
    }



}
