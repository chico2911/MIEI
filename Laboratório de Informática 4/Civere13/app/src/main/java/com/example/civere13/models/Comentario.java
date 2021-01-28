package com.example.civere13.models;

public class Comentario {
    public String texto;
    public String utilizador;
    public int classificacao;

    public Comentario(String texto, String utilizador, int classificacao) {
        this.texto = texto;
        this.utilizador = utilizador;
        this.classificacao = classificacao;
    }

    public String getTexto() {
        return texto;
    }

    public String getUtilizador() {
        return utilizador;
    }

    public int getClassificacao() {
        return classificacao;
    }
}
