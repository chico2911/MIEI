package com.example.civere13.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.civere13.R;
import com.example.civere13.controllers.HTTPRequester;
import com.example.civere13.models.Utilizador;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Objects;

public class AddComment extends AppCompatActivity {
    private int id;
    private int tipo;
    private String token;
    private Utilizador user;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_comment);
        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor      = preferences.edit();
        getVariaveis();
        Button back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goback();
            }
        });

        Button confirm = findViewById(R.id.button5);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendComment();
            }
        });
    }

    @Override
    public String toString() {
        return "addComment{" +
                "id=" + id +
                ", tipo=" + tipo +
                ", token='" + token + '\'' +
                ", user=" + user +
                ", preferences=" + preferences +
                ", editor=" + editor +
                '}';
    }

    public void getVariaveis(){
        Gson gson = new Gson();
        Type typeUser = new TypeToken<Utilizador>(){}.getType();
        user= (Utilizador) gson.fromJson(preferences.getString("user", null), typeUser);
        id = getIntent().getIntExtra("id",0);
        tipo = getIntent().getIntExtra("tipo",0);
        token = preferences.getString("token",null);
    }

    public void sendComment(){
        EditText classificação = findViewById(R.id.editText3);
        TextInputEditText text = findViewById(R.id.textinput);
        String vot =classificação.getText().toString();
        String comment = Objects.requireNonNull(text.getText()).toString();
        if(0<=Integer.parseInt(vot) && Integer.parseInt(vot)<=10){
            int send;
            HTTPRequester addComment = new HTTPRequester("https://server20200609112751.azurewebsites.net/api/newComment",this);
            String request = "{\"tipo\" : \""+tipo+"\",\"Token\" : \""+token+"\",\"id\":\""+id+"\",\"Utilizador\":\""+user.getId()+"\",\"Votacao\":\""+vot+"\",\"Texto\":\""+comment+"\"}";
            System.out.println(request);
            try {
                Thread thread = new Thread(() -> {
                    try {
                        addComment.getJsonObject(request);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                thread.start(); thread.join();
                send = addComment.check;
            }catch (Exception e){send = -1;}
            switch (send){
                case 1: this.finish(); break;
                case -1: System.out.println("Ocorreu um erro"); break;
            }
        }
    }

    public void goback(){
        this.finish();
    }
}
