package com.example.civere13.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.civere13.R;
import com.example.civere13.controllers.BDFiler;
import com.example.civere13.controllers.DatabaseHelper;
import com.example.civere13.models.Excursao;
import com.example.civere13.models.PontoInteresse;
import com.example.civere13.models.Utilizador;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Menu extends AppCompatActivity {
    private Button hotel;
    private Button restaurante;
    private Button ponto;
    private Button guia;
    private Button settings;
    private Button menuGuide;
    private ArrayList<PontoInteresse> listHotel;
    private ArrayList<PontoInteresse> listRes;
    private ArrayList<PontoInteresse> listPI;
    private ArrayList<Excursao> exc;
    private Utilizador user;
    private  int type;
    ListView listView;SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setInstances();
        activateGuideFun();
        TextView city = findViewById(R.id.braga);
        city.setText(preferences.getString("cidade","Braga"));
        hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateButton(1);
                updateHotelview();
            }
        });
        restaurante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateButton(2);
                updaterestview();
            }
        });
        ponto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateButton(3);
                updatePontoview();
            }
        });
        guia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateButton(4);
                openguide();
            }
        });
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSettings();
            }
        });

        listView = findViewById(R.id.listView);

        type=2;
        PontoInteresseAdapter adapter = new PontoInteresseAdapter(this, R.layout.row, listHotel,type);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                activateInfoFun(position);
            }
        });
        Button world = findViewById(R.id.earth);
        world.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSelector();
            }
        });

    }

    public void setInstances() {
        Gson gson     = new Gson();
        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Type typeUser = new TypeToken<Utilizador>(){}.getType();
        user          =  gson.fromJson(preferences.getString("user", null), typeUser);
        hotel         = findViewById(R.id.hotel);
        restaurante   = findViewById(R.id.addExcursion);
        ponto         = findViewById(R.id.wallet);
        guia          = findViewById(R.id.mapa);
        settings      = findViewById(R.id.settings);
        menuGuide     = findViewById(R.id.menuguide);
        Type type     = new TypeToken<ArrayList<PontoInteresse>>(){}.getType();
        Type excursao = new TypeToken<ArrayList<Excursao>>(){}.getType();
        listHotel     = gson.fromJson(preferences.getString("hoteis", null), type);
        listRes       = gson.fromJson(preferences.getString("restauntes", null), type);
        listPI        = gson.fromJson(preferences.getString("pontosDeInteresse", null), type);
        exc           = gson.fromJson(preferences.getString("excursoes", null), excursao);
    }

    public void openSettings() {
        Intent set = new Intent(this, Settings.class);
        startActivity(set);

    }

    public void updateButton(int id) {
        switch (id) {
            case 1:
                hotel.getBackground().setTint(getResources().getColor(R.color.buttonPressed));
                restaurante.getBackground().setTint(getResources().getColor(R.color.buttonUnPressed));
                ponto.getBackground().setTint(getResources().getColor(R.color.buttonUnPressed));
                activateGuideFun();
                menuGuide.setVisibility(View.INVISIBLE);
                break;
            case 2:
                hotel.getBackground().setTint(getResources().getColor(R.color.buttonUnPressed));
                restaurante.getBackground().setTint(getResources().getColor(R.color.buttonPressed));
                ponto.getBackground().setTint(getResources().getColor(R.color.buttonUnPressed));
                activateGuideFun();
                menuGuide.setVisibility(View.INVISIBLE);
                break;
            case 3:
                hotel.getBackground().setTint(getResources().getColor(R.color.buttonUnPressed));
                restaurante.getBackground().setTint(getResources().getColor(R.color.buttonUnPressed));
                ponto.getBackground().setTint(getResources().getColor(R.color.buttonPressed));
                activateGuideFun();
                menuGuide.setVisibility(View.INVISIBLE);
                break;
            case 4:
                if(user.getGuia()!=1){
                hotel.getBackground().setTint(getResources().getColor(R.color.buttonUnPressed));
                restaurante.getBackground().setTint(getResources().getColor(R.color.buttonUnPressed));
                ponto.getBackground().setTint(getResources().getColor(R.color.buttonUnPressed));
                guia.getBackground().setTint(getResources().getColor(R.color.buttonPressed));}
                break;
        }
    }

    public void updateHotelview(){
        type=2;
        PontoInteresseAdapter adapter = new PontoInteresseAdapter(this, R.layout.row, listHotel,type);
        listView.setAdapter(adapter);
    }

    public void updaterestview(){
        type=0;
        PontoInteresseAdapter adapter = new PontoInteresseAdapter(this, R.layout.row, listRes,type);
        listView.setAdapter(adapter);
    }

    public void updatePontoview(){
        type=1;
        PontoInteresseAdapter adapter = new PontoInteresseAdapter(this, R.layout.row, listPI,type);
        listView.setAdapter(adapter);
    }

    //If user is a guide then change its color
    public void activateGuideFun(){
        if(user.getGuia()==1){
            guia.getBackground().setTint(getResources().getColor(R.color.botao_guia));
        }
        else{
            guia.getBackground().setTint(getResources().getColor(R.color.buttonUnPressed));
        }

    }

    public void openguide(){
        if(isOnline()){
        try {
            BDFiler filler = new BDFiler(new DatabaseHelper(this), preferences.getString("token", null), this);
            Thread thread = new Thread(filler::getExcursaoFromServer);
            thread.start(); thread.join();
            SharedPreferences.Editor editor      = preferences.edit();
            System.out.println("--------1---------");
            exc = filler.getExcursao(3, listPI);
            System.out.println("--------2---------");
            Gson gson = new Gson();
            String jsonStringEX = gson.toJson(exc);
            editor.putString("excursoes", jsonStringEX);
            editor.apply();
        }catch (Exception e){e.printStackTrace();}}
        if (user.getGuia()==1) {
            if(isOnline()){
            Intent guide = new Intent(this, Interface_Guia.class);
            startActivity(guide);}
        }else {
            type=5;
            ExcursaoAdapter adapter = new ExcursaoAdapter(this, R.layout.row, exc,type);
            listView.setAdapter(adapter);
        }
    }

    public void activateInfoFun(int position){
        Gson gson = new Gson();
        Intent info ;
        if(type==5){
            info = new Intent(this, Interface_Excursao.class);
            String jsonStringR  = gson.toJson(exc.get(position));
            info.putExtra("excursao",jsonStringR);}
        else{
            info = new Intent(this,Interface_Info.class);
            String jsonStringR="";
            switch (type){
                case 0: jsonStringR  = gson.toJson(listRes.get(position)); break;
                case 1: jsonStringR  = gson.toJson(listPI.get(position)); break;
                case 2: jsonStringR  = gson.toJson(listHotel.get(position)); break;
            }
            info.putExtra("item",jsonStringR);}
        startActivity(info);
    }

    public void openSelector(){
        Intent citySelector = new Intent(this, CitySelector.class);
        startActivity(citySelector);
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


