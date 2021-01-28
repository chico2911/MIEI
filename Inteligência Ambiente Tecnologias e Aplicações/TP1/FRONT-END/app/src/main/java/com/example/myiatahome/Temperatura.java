package com.example.myiatahome;

import java.io.Serializable;
import java.util.List;

public class Temperatura implements Serializable {
    private final String nome = "Temperatura";
    private int state;
    private final int humidade = 50;
    private AskMe askMe;


    public Temperatura(int state, AskMe askMe) {
        this.state = state;
        this.askMe = askMe;
    }

    public String getNome() {
        return nome;
    }

    public int getState() {
        return state;
    }

    public int getHumidade() {
        return humidade;
    }

    public String last24hours(){
        return askMe.ask("http://10.0.2.2:8080/getTemperatura24");
    }

    public String last7days(){
        return askMe.ask("http://10.0.2.2:8080/getTemperatura7days");
    }

    public String last365days(){
        return askMe.ask("http://10.0.2.2:8080/getTemperatura365");
    }

}