package com.example.myiatahome;

public class Autoclismo {

    private final String nome = "Autoclismo";
    private int state;
    private int total;

    public Autoclismo(int state){
        this.state = state;
    }

    public String getNome() {
        return nome;
    }

    public int getConsumo(){
        return state;
    }

    public int getTotal(){return total;}

}