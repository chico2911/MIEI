package com.example.civere13.models;

import java.io.Serializable;
import java.util.Map;

public  class PontoInteresse implements Serializable {
    private int id;
    private int tipo;
    private String nome;
    private String descricao;
    private double classification;
    private Map<String,String> contactos;
    private String morada;
    private String urlIMG;

    public PontoInteresse(int id,int tipo, String nome, String descricao, double classification, Map<String, String> contactos, String morada, String urlIMG) {
        this.id             = id;
        this.tipo           = tipo;
        this.nome           = nome;
        this.descricao      = descricao;
        this.classification = classification;
        this.contactos      = contactos;
        this.morada         = morada;
        this.urlIMG         = urlIMG;
    }

    public int getTipo() {
        return tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getClassification() {
        return classification;
    }

    public void setClassification(double classification) {
        this.classification = classification;
    }

    public Map<String, String> getContactos() {
        return contactos;
    }

    public void setContactos(Map<String, String> contactos) {
        this.contactos = contactos;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public String getUrlIMG() {
        return urlIMG;
    }

    public void setUrlIMG(String urlIMG) {
        this.urlIMG = urlIMG;
    }

    @Override
    public String toString() {
        return "PontoInteresse{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", classification=" + classification +
                ", contactos=" + contactos +
                ", morada='" + morada + '\'' +
                ", urlIMG='" + urlIMG + '\'' +
                '}';
    }
}
