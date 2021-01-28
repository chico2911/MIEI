package com.example.civere13.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.civere13.R;
import com.example.civere13.controllers.BDFiler;
import com.example.civere13.controllers.DatabaseHelper;
import com.example.civere13.controllers.HTTPRequester;
import com.example.civere13.controllers.LocaleHelper;
import com.example.civere13.models.Excursao;
import com.example.civere13.models.PontoInteresse;
import com.example.civere13.models.Utilizador;
import com.google.gson.Gson;

import org.json.simple.JSONObject;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {
    private Button login; private EditText email; private EditText pass;
    private TextView regist; private TextView error;
    ApplicationInfo app;
    Bundle bundle;
    private String mail;
    SharedPreferences        preferences;
    SharedPreferences.Editor editor;

    public Utilizador user=null;

    //Variaveis de Instancia para preenchimento e recolha de info, bem como criação de tabelas.
    public DatabaseHelper myDb;
    BDFiler filler;

    @SuppressLint("CommitPrefEdits")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myDb        = new DatabaseHelper(this);
        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor      = preferences.edit();
        try {
             app = this.getPackageManager().getApplicationInfo(this.getPackageName(),PackageManager.GET_META_DATA);
             bundle = app.metaData;
        }catch (Exception e){runAppNotValid();}
        if(!preferences.contains("cidade")){editor.putString("cidade","Braga");}
        if(preferences.contains("token")){
            runApp();
        }
        else {runLogin();}
    }

    public void runApp() {
        filler = new BDFiler(myDb, preferences.getString("token", null),this);
        if(isOnline()){
        try{
            String jsonInputString = "{\"tipo\" : \"v\",\"token\" : \""+preferences.getString("token",null)+"\",\"date\":\""+bundle.getString("version")+"\"}";
            HTTPRequester versionCheck = new HTTPRequester("https://server20200609112751.azurewebsites.net/api/Images",this);
            Thread check = new Thread(()->versionCheck.getValido(jsonInputString));
            check.start();
            check.join();
            Log.i("Check", String.valueOf(versionCheck.check));
            switch (versionCheck.check){
                case 1:
                    Thread Fill = new Thread(filler);
                    Fill.start();
                    Fill.join();
                    fillInformation();
                    openMenu(); break;
                case 0: runLogin(); break;
                case -1: runAppNotValid(); ;break;
            }
        }
        catch (Exception e){runLogin();}}else{
            Log.i("Conexão","Sem internet");
            fillInformation();
            openMenu();}
    }

    public void runLogin() {
        linguagemSetting();
        setContentView(R.layout.activity_main);
        loginB();
        registB();
        error = findViewById(R.id.errorM);
    }

    public void runAppNotValid(){
        setContentView(R.layout.appnotvalid);
        Button site = findViewById(R.id.button);
        Button exit = findViewById(R.id.button3);
        site.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://chico2911.github.io/li4Site/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
    }

    public void linguagemSetting() {
        if (preferences.contains("lang")) {
            LocaleHelper.setLocale(this, preferences.getString("lang", null));
        }
    }

    //Este método vai buscar a informação à base de dados e guarda em cache a informação
    public void fillInformation()   {
        Gson gson = new Gson();
        ArrayList<PontoInteresse> restauntes        = filler.getDataFromBD(0);
        ArrayList<PontoInteresse> pontosDeInteresse = filler.getDataFromBD(1);
        ArrayList<PontoInteresse> hoteis            = filler.getDataFromBD(2);
        ArrayList<Excursao> excursoes               = filler.getExcursao(3,pontosDeInteresse);
        String jsonStringR  = gson.toJson(restauntes);
        String jsonStringH  = gson.toJson(hoteis);
        String jsonStringPI = gson.toJson(pontosDeInteresse);
        String jsonStringEX = gson.toJson(excursoes);
        editor.putString("restauntes"       , jsonStringR);
        editor.putString("hoteis"           , jsonStringH);
        editor.putString("pontosDeInteresse", jsonStringPI);
        editor.putString("excursoes"        , jsonStringEX);
        editor.apply();
    }

    //Este método inicia a atividade Registo, para que seja possivel criar uma conta nova.
    private void registB()          {
        regist=findViewById(R.id.textView);
        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regista = new Intent(getApplicationContext(),Registar.class);
                startActivity(regista);
            }
        });

    }

    //Método para Efetuar login, desde set clickListener, a confirmar login e guardar utilizador.
    public void loginB()            {
        login = findViewById(R.id.login);
        login.setOnClickListener(v -> {
            email=findViewById(R.id.editText);
            pass=findViewById(R.id.editText2);
            String KEY = email.getText().toString();
            mail = KEY;
            String KEYPASS = pass.getText().toString();
            AtomicInteger id = new AtomicInteger();
            try{
            Thread linda = new Thread(()->{
                id.set(confirmLogin(KEY, KEYPASS));
                });
                linda.start();
                linda.join();
            }catch (Exception ignore){}
            int larilas = id.intValue();
            switch (larilas){
                case 1:
                    runApp();
                    break;
                case -1:error.setVisibility(View.VISIBLE);
                    error.setText("Conta não existe");
                    login.setClickable(true);
                    break;
                case 0:error.setVisibility(View.VISIBLE);
                         error.setText("Email Ou Palavra-Passe Errada");
                       login.setClickable(true);break;}
        });

    }

    //Atravês da classe HTTPRequester pede que o backend confirme as credênciais -> Retorna o Não se errado, {token,nome,email,etc}
    public int confirmLogin(String email,String pass) {
            int erro=0;
            try {
                HTTPRequester httpRequester = new HTTPRequester("https://server20200609112751.azurewebsites.net/api/Login",this);
                String request = "{ \"Email\": \"" + email + "\", \"Pass\":\"" + pass + "\"}";
                JSONObject login = httpRequester.login(request);
                erro=httpRequester.check;
                if(erro==1){
                    getUtilizador((JSONObject) Objects.requireNonNull(login.get("Userinfo")),login.get("token").toString());
                }
            }catch (Exception e){System.out.println(e.getMessage()); e.printStackTrace();}
            return erro;
    }

    //Atravês da classe HTTPRequester pede as informações para criar um Utilizador.
    public void getUtilizador(JSONObject userJson,String token) {
        String nome = userJson.get("Segundo_nome").toString();
        int guia = Integer.parseInt(userJson.get("Guia").toString());
        int id = Integer.parseInt(userJson.get("id").toString());
        Utilizador user = new Utilizador(nome,mail,0,guia,id);
        Log.i("User:",user.toString());
        Gson gson = new Gson();
        String jsonString = gson.toJson(user);
        editor.putString("token",token);
        editor.putString("user", jsonString);
        editor.apply();
    }

    //Metodo para criar nova Atividade Menu.
    public void openMenu() {
        Intent menu = new Intent(this,Menu.class);
        startActivity(menu); fin();
    }


    public void fin() {
        this.finish();
    }


    public boolean isOnline(){
        ConnectivityManager cm =
                (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }
}
