package com.example.civere13.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.civere13.R;
import com.example.civere13.controllers.HTTPRequester;
import com.example.civere13.models.Utilizador;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;

public class ChangeNumber extends AppCompatActivity {
    private Button back; private String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_number);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        token = preferences.getString("token",null);
        back=findViewById(R.id.backL);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backFinish();
            }
        });
        Button confirm = findViewById(R.id.confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(makeRequest()){
                    case 1: backFinish(); break;
                    case -1:TextView textView = findViewById(R.id.textView6); textView.setVisibility(View.VISIBLE);break;
                }

            }
        });

    }


    public int makeRequest() {
        try {
            EditText password = findViewById(R.id.password);
            EditText new_number = findViewById(R.id.new_number);
            String Pass = password.getText().toString();
            String newNumber = new_number.getText().toString();
            Utilizador user = getUtilizador();
            if (!Pass.isEmpty() && !newNumber.isEmpty()) {
                    StringBuilder request = new StringBuilder("{");
                    request.append("\"Email\" : \"").append(user.getEmail()).append("\",");
                    request.append("\"Pass\" : \"").append(Pass).append("\",");
                    request.append("\"valido\" : \"").append("0").append("\",");
                    request.append("\"token\" : \"").append(token).append("\",");
                    request.append("\"Telemovel\" : \"").append(newNumber).append("\"}");
                    HTTPRequester requester = new HTTPRequester("https://server20200609112751.azurewebsites.net/api/EditAcount",this);
                    Thread omw = new Thread(() -> {
                        try {
                            requester.getInt(request.toString());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                    omw.start();
                    omw.join();
                    return requester.check;
                }
        }catch (Exception ignore){return -1;}
        return 0;
    }


    public Utilizador getUtilizador(){
        Gson gson = new Gson();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Type typeUser = new TypeToken<Utilizador>(){}.getType();
        return gson.fromJson(preferences.getString("user", null), typeUser);
    }

    public void backFinish(){this.finish();
        Intent menu;
        menu = new Intent(this,Settings.class);
        startActivity(menu);}
}

