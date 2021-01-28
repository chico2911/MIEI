package com.example.civere13.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.civere13.R;
import com.example.civere13.controllers.Language;


public class Settings extends AppCompatActivity {
    private Button back; private Button lang;
    private Switch darkswitch; private Button pass; private Button log_out;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setInstances();
        darkCheker(darkswitch);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backFinish();
            }
        });
        lang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLanguage();
            }
        });
        darkswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }
                else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });
        if(isOnline()) {
            pass.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openPass();
                }
            });
            log_out.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor Editor = preferences.edit();
                    Editor.remove("token");
                    Editor.remove("user");
                    Editor.apply();
                    openMainActivity();
                    backFinish();
                }
            });
            Button number = findViewById(R.id.new_number);
            number.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openNumber();
                }
            });
        }
        Button propor = findViewById(R.id.sugest);
        propor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMail();
            }
        });

    }

    public void openLanguage(){
        this.finish();
        Intent menu;
        menu = new Intent(this, Language.class);
        startActivity(menu);}
    private void setInstances(){
        darkswitch=findViewById(R.id.dark);
        darkCheker(darkswitch);
        back = findViewById(R.id.back);
        lang=findViewById(R.id.lang);
        pass=findViewById(R.id.new_pass);
        log_out=findViewById(R.id.logout);
    }
    public void darkCheker(Switch dark){
        int nightModeFlags = getResources().getConfiguration().uiMode;
        if(AppCompatDelegate.getDefaultNightMode()!=AppCompatDelegate.MODE_NIGHT_NO && nightModeFlags==33){
            dark.setChecked(true);
        }
    }
    public void openPass(){
        this.finish();
        Intent menu;
        menu = new Intent(this,Change_Pass.class);
        startActivity(menu);}


    public void openMail(){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:"+"sugestoes@civere-suporte.com"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "[Sugestao] Sugestão proposta por Cliente.");
        startActivity(intent);
    }


    public void openNumber(){
        this.finish();
        Intent menu;
        menu = new Intent(this, ChangeNumber.class);
        startActivity(menu);}

    public void openMainActivity(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);}

    public void backFinish(){this.finish();
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
