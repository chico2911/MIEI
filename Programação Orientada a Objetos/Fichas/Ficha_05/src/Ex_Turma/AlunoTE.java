package Ex_Turma;

import java.util.ArrayList;
import java.util.List;

public class AlunoTE extends Aluno {
    private String empresa;

    public AlunoTE(){
        super();
        this.empresa = "";
    }

    public AlunoTE(String num,String nome,List<Integer> notas,String empresa){
        super(num,nome,notas);
        this.empresa=empresa;
    }

    public String getEmpresa() {
        return empresa;
    }
    public void changeEmpresa(String i){
        this.empresa=empresa;
    }
    
}
