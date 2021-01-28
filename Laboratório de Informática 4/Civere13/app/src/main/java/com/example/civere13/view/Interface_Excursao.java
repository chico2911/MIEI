package com.example.civere13.view;


import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.civere13.R;
import com.example.civere13.controllers.HTTPRequester;
import com.example.civere13.models.Excursao;
import com.example.civere13.models.Utilizador;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.stripe.android.Stripe;
import com.stripe.android.ApiResultCallback;
import com.stripe.android.model.PaymentMethod;
import com.stripe.android.model.PaymentMethodCreateParams;
import com.stripe.android.view.CardInputWidget;

import java.lang.reflect.Type;

public class Interface_Excursao extends AppCompatActivity {
    private Stripe stripe; private Button buy;
    private Excursao excursao; private Utilizador user; private String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        getExcursao();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        token = preferences.getString("token",null);
        setContentView(R.layout.activity_checkout);
        TextView nome = findViewById(R.id.name); nome.setText(excursao.getHorario());
        TextView price = findViewById(R.id.itemprice); price.setText(String.valueOf(excursao.getPreco()));
        TextView info = findViewById(R.id.info); info.setText(excursao.getDescricao());
        buy = findViewById(R.id.pay);
        Button back = findViewById(R.id.back2);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });
        if(user.getId()==excursao.getIdGuia()){
            buy.setVisibility(View.INVISIBLE);
            Button remove = findViewById(R.id.remove);
            remove.setVisibility(View.VISIBLE);
            if(isOnline()){
            remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    remove();
                }
            });}
        }
        else{
            if(isOnline()){
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payment();
            }
        });}}
    }


    // remover se o user for o guia dela.
    public void remove(){
        try {
            HTTPRequester requester = new HTTPRequester("https://server20200609112751.azurewebsites.net/api/RemoveExcursão", this);
            String jsonInputString = "{\"IdExc\":\""+excursao.getId()+"\",\"token\":\""+token+"\"}";
            System.out.println(jsonInputString);
            Thread thread = new Thread(() -> {
                requester.getInt(jsonInputString);
            });
            thread.start(); thread.join();
            if(requester.check==1){this.finish();}
        }catch (Exception ignore){}
    }



    // Comprar Excursão se o user nao for o guia dela.
    public void getExcursao(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor  editor      = preferences.edit();
        Type typeUser = new TypeToken<Utilizador>(){}.getType();
        Type type = new TypeToken<Excursao>(){}.getType();
        Gson gson = new Gson();
        user= (Utilizador) gson.fromJson(preferences.getString("user", null), typeUser);
        excursao= (Excursao) gson.fromJson(getIntent().getStringExtra("excursao"), type);
    }

    public void payment(){
        ConstraintLayout payment = findViewById(R.id.payment);
        payment.setVisibility(View.VISIBLE);
        Button pay = findViewById(R.id.confirmPay);
        Button cancel = findViewById(R.id.cancel_pay);
        TextView amount = findViewById(R.id.amount);
        amount.setText(String.valueOf(excursao.getPreco()));
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartao(user.getId(),excursao.getId());
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payment.setVisibility(View.INVISIBLE);
            }
        });

    }


    public void cartao(int idC,int idExc){
               CardInputWidget cardInputWidget = findViewById(R.id.cardInputWidget);
               PaymentMethodCreateParams params = cardInputWidget.getPaymentMethodCreateParams();

               if (params == null) {
                   return;
               }
               // Configure the SDK with your Stripe publishable key so that it can make requests to the Stripe API
               stripe = new Stripe(getApplicationContext(),"pk_test_pBY9Z6DkzvCxsOR1rnuEOfAH003PFJNnq5");
               stripe.createPaymentMethod(params, new ApiResultCallback<PaymentMethod>() {
                   @Override
                   public void onSuccess(@NonNull PaymentMethod result) {
                       // Create and confirm the PaymentIntent by calling the sample server's /pay endpoint
                       TextView amount = findViewById(R.id.amount);
                           switch (pay(result.id,idExc,idC,token)){
                               case 1: amount.setText("Foi inscrito!");
                                       Button cancel = findViewById(R.id.cancel_pay); cancel.setText("Exit"); break;
                               case -5: amount.setText("Já estava inscrito"); break;
                               case -3:
                               case -4:
                                   amount.setText("Tente novamente mais tarde.");break;
                           }
                   }

                   @Override
                   public void onError(@NonNull Exception e) {
                       TextView amount = findViewById(R.id.amount);
                       amount.setText("Tente novamente mais tarde.");
                   }
               });

       }

       public int pay(@Nullable String paymentMethodId,int idExc,int idC,String token){
           HTTPRequester requester = new HTTPRequester("https://server20200609112751.azurewebsites.net/api/Payment",this);
           String jsonInputString = "{\"key\" :" +" \""+paymentMethodId+"\",\"IDC\" : \""+idC+"\",\"IDEx\":\""+idExc+"\",\"token\":\""+token+"\"}";
           System.out.println(jsonInputString);
           try{
               Thread thread = new Thread(()->{
                   requester.getInt(jsonInputString);
               });
               thread.start(); thread.join();
           }catch (Exception e){e.printStackTrace();}
           System.out.println(requester.check);
           return requester.check;
       }

    public boolean isOnline(){
        ConnectivityManager cm =
                (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }


     public void goBack(){this.finish();}
}
