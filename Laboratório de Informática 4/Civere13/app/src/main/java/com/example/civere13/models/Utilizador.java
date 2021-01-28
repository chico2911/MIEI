package com.example.civere13.models;

import android.os.Parcelable;
import android.util.ArraySet;
import android.view.ViewDebug;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.Serializable;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.util.Map;


public class Utilizador implements Serializable {
    private String nome;
    private String email;
    private int telemovel;
    private int guia;
    private int id;

    public Utilizador() {

    }

    public Utilizador(String nome, String email, int telemovel, int guia,int id) {
        this.nome = nome;
        this.email = email;
        this.telemovel = telemovel;
        this.guia = guia;
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelemovel() {
        return telemovel;
    }

    public void setTelemovel(int telemovel) {
        this.telemovel = telemovel;
    }

    public int getGuia() {
        return guia;
    }

    public int getId() {
        return id;
    }

    public void setGuia(int guia) {
        this.guia = guia;
    }

    @Override
    public String toString() {
        return "Utilizador{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", telemovel=" + telemovel +
                ", guia=" + guia +
                '}';
    }
}
