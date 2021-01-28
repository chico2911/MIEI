package com.example.civere13.models;

import android.util.Pair;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Excursao implements Serializable {
    private int id;
    private int idGuia;
    private String horario;
    private int pontoInicial;
    private String moradaDoPontoInicial;
    private String urlPI;
    private String descricao;
    private float preco;

    public Excursao(int id, int idGuia, String horario, int pontoInicial, String moradaDoPontoInicial, String urlPI,String descricao, float preco) {
        this.id = id;
        this.idGuia = idGuia;
        this.horario = horario;
        this.pontoInicial = pontoInicial;
        this.moradaDoPontoInicial = moradaDoPontoInicial;
        this.urlPI = urlPI;
        this.descricao = descricao;
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUrlPI() {
        return urlPI;
    }

    public void setUrlPI(String urlPI) {
        this.urlPI = urlPI;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdGuia() {
        return idGuia;
    }

    public void setIdGuia(int idGuia) {
        this.idGuia = idGuia;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public int getPontoInicial() {
        return pontoInicial;
    }

    public void setPontoInicial(int pontoInicial) {
        this.pontoInicial = pontoInicial;
    }

    public String getMoradaDoPontoInicial() {
        return moradaDoPontoInicial;
    }

    public void setMoradaDoPontoInicial(String moradaDoPontoInicial) {
        this.moradaDoPontoInicial = moradaDoPontoInicial;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }
}
