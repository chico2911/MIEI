package Ex_Turma;

import java.util.ArrayList;
import java.util.List;

public class Aluno implements Comparable<Aluno>
{
    private String numero;
    private String nome;
    private List<Integer> notas;

    public Aluno()
    { this.numero = new String();
        this.nome = new String();
        notas=new ArrayList<>();
    }

    public Aluno (String num, String nom,List<Integer> notas)
    { this.numero = num;
        this.nome = nom;
        setNotas(notas);
    }

    public Aluno (Aluno al)
    { this.numero = al.getNumero();
        this.nome = al.getNome();
    }

    public String getNome()
    { return this.nome; }

    public String getNumero()
    { return this.numero; }

    public void setNotas(List<Integer> notas) {
        this.notas = new ArrayList<>();
        notas.forEach(e->this.notas.add(e));
    }

    public List<Integer> getNotas() {
        List<Integer> newNotas = new ArrayList<>();
        notas.forEach(e->newNotas.add(e));
        return newNotas;
    }

    public void addNota(Integer i){
        notas.add(i);
    }

    public String toString()
    { StringBuilder sb = new StringBuilder();
        sb.append("Ex_Turma.Aluno: ").append(this.numero).append(" , ")
                .append(this.nome).append("\n");
        return sb.toString();
    }

    public boolean equals (Object o)
    { if (o == this) return true;
        if (o == null || o.getClass() != this.getClass()) return false;
        Aluno a = (Aluno) o;
        return this.numero.equals(a.getNumero()) &&
                this.nome.equals(a.getNome());
    }


    public Aluno clone()
    { return new Aluno(this); }

    public int compareTo(Aluno a)
    { return this.nome.compareTo(a.getNome());
    }

}