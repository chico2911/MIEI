package com.example.myiatahome;

public class Torneira {
    private final String nome = "Torneira";
    private int state;
    private double mediaAgua;

    public Torneira(int state){
        this.state = state;
        mediaAgua = 0.15;
    }

    public String getNome() {
        return nome;
    }

    public double getConsumo(){
        return state*mediaAgua;
    }


}
