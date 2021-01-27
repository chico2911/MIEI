package Ex_Turma;

import java.util.ArrayList;
import java.util.List;

public class TesteTurma {

    public static void main(String[] opts){
        List<Integer> notas = new ArrayList<>();
        notas.add(1);
        Aluno a1 = new Aluno("1","Zé",notas);
        Aluno a2 = new Aluno("2","Ana",notas);
        Aluno a3 = new Aluno("3","Tó",notas);
        AlunoTE a4 = new AlunoTE("4","Simão",notas,"DIUM");
        Turma t = new Turma();
        t.setNome("POO");
        t.insereAluno(a1);
        t.insereAluno(a2);
        t.insereAluno(a3);
        t.insereAluno(a4);

        System.out.println("Ordem coleçao");

        System.out.println(t.toString());


        System.out.println("Ordem coleçao Alfabetica");

        System.out.println(t.alunosOrdemAlfabetica());


        System.out.println("Ordem coleçao Decrescente");

        System.out.println(t.alunosOrdemDescrescenteNumero());

        System.out.println("Todas os códigos: "+t.todosOsCodigos());

        System.out.println("Numero de alunos: "+t.qtsAlunos());


    }
}
