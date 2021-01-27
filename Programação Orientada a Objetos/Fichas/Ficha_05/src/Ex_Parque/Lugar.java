package Ex_Parque;

public class Lugar {
    /** Matricula do veiculo estacionado */
    private String matricula ;
    /** Nome do proprietario */
    private String nome ;
    /** Tempo atribuido ao lugar , em minutos */
    private int minutos ;
    /** Indica se lugar é permanente , ou de aluguer*/
    private boolean permanente ;


    public Lugar(String matricula, String nome, int minutos, boolean permanente) {
        this.matricula  = matricula;
        this.nome       = nome;
        this.minutos    = minutos;
        this.permanente = permanente;
    }

    public Lugar(){
        matricula  ="n/a";
        nome       ="n/a";
        minutos    = 0;
        permanente = false;
    }

    public Lugar(Lugar lgr){
        matricula  =getMatricula();
        nome       =getNome();
        minutos    =getMinutos();
        permanente =isPermanente();
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getMinutos() {
        return minutos;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    public boolean isPermanente() {
        return permanente;
    }

    public void setPermanente(boolean permanente) {
        this.permanente = permanente;
    }

    public Lugar clone() {
        return new Lugar(this);
    }

    @Override
    public String toString() {
        return "Ex_Parque.Lugar{" +
                "matricula='" + matricula + '\'' +
                ", nome='" + nome + '\'' +
                ", minutos=" + minutos +
                ", permanente=" + permanente +
                '}';
    }
}
