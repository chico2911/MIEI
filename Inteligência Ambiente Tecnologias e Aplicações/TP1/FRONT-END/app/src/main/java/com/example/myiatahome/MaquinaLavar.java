package com.example.myiatahome;

public class MaquinaLavar {

    private final String nome = "Samsung 8KG XPTO";
    private int state;

    public MaquinaLavar(int state){
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