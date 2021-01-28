package com.example.myiatahome;

public class Interruptor {
    private final String nome = "Interruptor";
    private int state;

    public Interruptor(int state){
        this.state = state;
    }

    public String getNome() {
        return nome;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
