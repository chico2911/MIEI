package com.example.civere13.models;

import java.io.Serializable;

public class Contactos implements Serializable {
    private String email;
    private String telemovel;
    private String telefone;

    public Contactos(String email, String telemovel, String telefone) {
        this.email     = email;
        this.telemovel = telemovel;
        this.telefone  = telefone;
    }

    public Contactos() {
        this.email     = "";
        this.telemovel = "";
        this.telefone  = "";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelemovel() {
        return telemovel;
    }

    public void setTelemovel(String telemovel) {
        this.telemovel = telemovel;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
