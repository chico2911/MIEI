package com.example.civere13.models;

import android.util.Pair;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class Restaurante extends PontoInteresse implements Serializable {
    public Restaurante(int id, int tipo,String nome, String descricao, double classification, Map<String, String> contactos, String morada, String urlIMG) {
        super(id,tipo,nome,descricao,classification,contactos,morada,urlIMG);
    }

}
