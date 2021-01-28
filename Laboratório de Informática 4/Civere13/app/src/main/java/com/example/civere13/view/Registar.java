package com.example.civere13.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.civere13.R;
import com.example.civere13.controllers.HTTPRequester;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class Registar extends AppCompatActivity {
    private Button back; private Button confirm;
    private EditText email; private EditText pass;
    private EditText passconf;  private EditText nome;
    private EditText p1;private EditText p2; private EditText guia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registar);
        back=findViewById(R.id.backL);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backFinish();
            }
        });
        confirm=findViewById(R.id.confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nome=findViewById(R.id.name);
                email=findViewById(R.id.editText);
                pass=findViewById(R.id.new_password);
                passconf=findViewById(R.id.confirm_password);

                    p1 = findViewById(R.id.name);
                    p2 = findViewById(R.id.name);
                    email = findViewById(R.id.editText);
                    //guia = findViewById(R.id.guide);
                    //guia = "0";
                    String nome_ = nome.getText().toString();
                    String email_ = email.getText().toString();
                    String pass_ = pass.getText().toString();
                    String pass_conf = passconf.getText().toString();
                TextView error = findViewById(R.id.errorM);
                RadioButton radioButton1 = findViewById(R.id.guide);
                System.out.println(radioButton1.isChecked());
                if(!nome_.isEmpty() && !email_.isEmpty() && !pass_.isEmpty() && !pass_conf.isEmpty()){
                    if(pass_.equals(pass_conf)) {
                        String p1_ = p1.getText().toString();
                        String p2_ = p2.getText().toString();
                        RadioButton radioButton = findViewById(R.id.guide);
                        String guiaStr = "0";
                        if(radioButton.isChecked()){guiaStr="1";}else{ guiaStr = "0";}
                       int check = sendRequest(nome_, nome_, email_, pass_, guiaStr, "0"); //qual é o segundo nome?
                        switch (check){
                            case 1: backFinish(); break;
                            case -1: error.setText("Não foi possivel Registar.");
                                     error.setVisibility(View.VISIBLE); break;
                            case -2: error.setText("Email já existente ou inválido.");
                                error.setVisibility(View.VISIBLE); break;
                            case -3: error.setText("Nick Name já existente ou inválido.");
                                error.setVisibility(View.VISIBLE); break;
                        }
                    }else {
                        error.setText("Palavras passes não correspondem");
                        error.setVisibility(View.VISIBLE);
                    }
            }
            }
        });

    }

    public int sendRequest(String nome, String segundoN , String email, String passe, String guia, String telemovel){
         String jsonInputString = "{ \"Nome\": \"" + nome + "\", \"Email\":\""+email +"\", \"Pass\":\"" + passe + "\", \"Primeiro_nome\":\"" + nome + "\", \"Segundo_nome\":\""+segundoN + "\", \"Guia\":\""+guia+"\"," + "\"telemovel\":\"" + telemovel +"\"" + "}";
        HTTPRequester requester = new HTTPRequester("https://server20200609112751.azurewebsites.net/api/Register",this);

        try {
            Thread pedido = new Thread(()-> {
                requester.getInt(jsonInputString);
            });
            pedido.start();
            pedido.join();
        } catch (Exception i){i.printStackTrace();};

        return requester.check;
    }


    public void backFinish(){
        this.finish();
    }
}
