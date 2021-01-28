package com.example.civere13.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.civere13.R;
import com.example.civere13.view.Settings;

public class Language extends AppCompatActivity {
    private TextView fr; private Button back;
    private TextView pt;
    private TextView en;
    private TextView ger;
    private TextView es;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
        fr=findViewById(R.id.french);  pt=findViewById(R.id.tuga);
        en=findViewById(R.id.english); ger=findViewById(R.id.german);
        es=findViewById(R.id.espanol);
        fr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocaleHelper.setLocale(Language.this,"fr");
                backFinish();
            }
        });
        pt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocaleHelper.setLocale(Language.this,"pt");
                backFinish();
            }
        });
        en.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocaleHelper.setLocale(Language.this,"en");
                backFinish();
            }
        });
        ger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocaleHelper.setLocale(Language.this,"de");
                backFinish();
            }
        });
        es.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocaleHelper.setLocale(Language.this,"es");
                backFinish();
            }
        });
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backFinish();
            }
        });
    }

    public void backFinish(){this.finish();
        Intent menu;
        menu = new Intent(this, Settings.class);
        startActivity(menu);}
}

