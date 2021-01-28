package com.example.civere13.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

import com.example.civere13.R;
import com.example.civere13.controllers.DatabaseHelper;

public class CitySelector extends AppCompatActivity {
    private Button porto;
    private Button braga;
    private Button vila;
    private Button funchal;
    private Button coimbra;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_selector);
        porto = findViewById(R.id.lang2);
        braga = findViewById(R.id.lang);
        vila = findViewById(R.id.new_pass);
        funchal = findViewById(R.id.new_number);
        coimbra = findViewById(R.id.sugest);
        porto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changecity(porto.getText().toString());
            }
        });
        braga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changecity(braga.getText().toString());
            }
        });
        vila.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changecity(vila.getText().toString());
            }
        });
        funchal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changecity(funchal.getText().toString());
            }
        });
        coimbra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changecity(coimbra.getText().toString());
            }
        });
        Button back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });
    }

    public void changecity(String city){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor  editor = preferences.edit();
        editor.putString("cidade",city);
        editor.apply();
        new DatabaseHelper(this).dropTable(8);
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    public void goBack(){this.finish();}

}
