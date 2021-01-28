package com.example.civere13.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.civere13.R;
import com.example.civere13.controllers.HTTPRequester;
import com.example.civere13.models.Excursao;
import com.example.civere13.models.PontoInteresse;
import com.example.civere13.models.Utilizador;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.simple.JSONObject;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;

public class Interface_Guia extends AppCompatActivity {
    private ConstraintLayout walletBackground; private Button back;
    private ConstraintLayout addView;
    private ConstraintLayout excursionsView;
    private EditText iban;
    private EditText amount;
    private Button add;
    private TextView topText;
    private ImageView walletBack;
    private float y;
    TextView dateTime;
    private Button cashOut; ListView listView;
    private Button wallet;
    private Button excursions;
    double balance = 0;
    private ArrayList<Excursao> walletExcursionsList;
    private ArrayList<String>  pointsName = new ArrayList<>();
    private Spinner pointsList;
    ArrayList<PontoInteresse> listPI;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Calendar myCalendar = Calendar.getInstance();
    Utilizador user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interface__guia);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setInstances();
        retrieveBalance();
        setSpinner();
        setExcursion();
        wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addView.setVisibility(View.INVISIBLE);
                excursionsView.setVisibility(View.INVISIBLE);
                walletBackground.setVisibility(View.VISIBLE);
                payOut();
                animateWallet(0);
                topText.setText("Wallet");
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                excursionsView.setVisibility(View.INVISIBLE);
                addView.setVisibility(View.VISIBLE);
                walletBackground.setVisibility(View.INVISIBLE);
                dateTime = findViewById(R.id.timestamp);
                setSpinner();
                date();
                time();
                confirmExc();
            }
        });
        excursions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        activateInfoFun(position);
                    }
                });
                animateWallet(1);
                excursionsView.setVisibility(View.VISIBLE);
                addView.setVisibility(View.INVISIBLE);
                walletBackground.setVisibility(View.INVISIBLE);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               goBack();
            }
        });

    }

    public void setInstances(){
        walletBack           = findViewById(R.id.moneyBack);
        cashOut              = findViewById(R.id.button2);
        wallet               = findViewById(R.id.wallet);
         listView = findViewById(R.id.excursionsList);
        walletBackground     = findViewById(R.id.walletView);
        topText              = findViewById(R.id.braga);
        pointsList           = findViewById(R.id.spinner);
        add                  = findViewById(R.id.addExcursion);
        addView              = findViewById(R.id.addView);
        excursions           = findViewById(R.id.excursions);
        excursionsView       = findViewById(R.id.excursionsView);
        iban = findViewById(R.id.editText4);
        amount = findViewById(R.id.amount);
        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor      = preferences.edit();
        Type type = new TypeToken<Utilizador>(){}.getType();
        Gson gson = new Gson();
        user = gson.fromJson(preferences.getString("user", null), type);
        Type tpe = new TypeToken<ArrayList<Excursao>>(){}.getType();
        Type tpe2 = new TypeToken<ArrayList<PontoInteresse>>(){}.getType();
        listPI = gson.fromJson(preferences.getString("pontosDeInteresse", null), tpe2);
        walletExcursionsList = gson.fromJson(preferences.getString("excursoes", null), tpe);
        back = findViewById(R.id.back3);
    }



    public void setExcursion(){

        ListView listView1 = findViewById(R.id.walletExcursionsList);
        ExcursaoAdapter adapter = new ExcursaoAdapter(this, R.layout.row, walletExcursionsList,5);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                activateInfoFun(position);
            }
        });
        listView1.setAdapter(adapter);
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                activateInfoFun(position);
            }
        });
    }

    public void activateInfoFun(int position){
        Gson gson = new Gson();
        Intent info ;
        info = new Intent(this, Interface_Excursao.class);
        String jsonStringR  = gson.toJson(walletExcursionsList.get(position));
        info.putExtra("excursao",jsonStringR);info.putExtra("item",jsonStringR);
        startActivity(info);
    }


    public void goBack() {this.finish();}

    // Everything Related with wallet
    public void animateWallet(int i){
        if(i==0){
            Animation animation = new TranslateAnimation(0, 0,0, 750);
            animation.setDuration(500);
            animation.setFillAfter(true);
            walletBack.startAnimation(animation);
            cashOut.animate()
                    .alpha(1f)
                    .setDuration(600)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            cashOut.setVisibility(View.VISIBLE);
                        }
                    });
            iban.animate()
                    .alpha(1f)
                    .setDuration(600)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            iban.setVisibility(View.VISIBLE);
                        }
                    });
            amount.animate()
                    .alpha(1f)
                    .setDuration(600)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            amount.setVisibility(View.VISIBLE);
                        }
                    });
            listView.setOnItemClickListener(null);
        }else{
            Animation animation = new TranslateAnimation(0, 0,750, 0);
            TextView error = findViewById(R.id.errorM); error.setVisibility(View.INVISIBLE);
            animation.setDuration(500);
            animation.setFillAfter(true);
            walletBack.setVisibility(View.INVISIBLE);
            cashOut.animate()
                    .alpha(1f)
                    .setDuration(500)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            cashOut.setVisibility(View.INVISIBLE);
                            iban.setVisibility(View.INVISIBLE);
                            amount.setVisibility(View.INVISIBLE);
                        }
                    });
            walletBack.startAnimation(animation);
        }
    }

    public void retrieveBalance(){
        String token = preferences.getString("token",null);
        String request = "{\"token\" : \"" + token + "\",\"Guia\":\""+user.getId()+"\"}";
        HTTPRequester requester = new HTTPRequester("https://server20200609112751.azurewebsites.net/api/Saldo",this);
        try{
            Thread thread = new Thread(()->{
                try{
                    JSONObject object = requester.getJsonObject(request);
                    TextView balance = findViewById(R.id.textView4);
                    balance.setText(Objects.requireNonNull(object.get("saldo")).toString()+"€");
                    this.balance = Double.parseDouble(Objects.requireNonNull(object.get("saldo")).toString());
                }catch (Exception ignore){}
            });
            thread.start(); thread.join();
        }catch (Exception ignore){}
    }



    // Everything related to add Excursion
    public void setSpinner(){
        pointsName = new ArrayList<>();
        for(PontoInteresse e : listPI){
            System.out.println(e.getNome());
            pointsName.add(e.getNome());
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                R.layout.spiner_item, pointsName);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        pointsList.setAdapter(arrayAdapter); pointsList.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
    }

    public void confirmExc(){
        Button confirm = findViewById(R.id.confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView time = findViewById(R.id.time);
                TextView data = findViewById(R.id.timestamp);
                EditText amount = findViewById(R.id.price);
                String ponto = pointsList.getSelectedItem().toString();
                TextInputEditText inputEditText = findViewById(R.id.textinput);
                String DataTime = data.getText().toString() + " " +time.getText().toString()+":00";
                if(!Objects.requireNonNull(inputEditText.getText()).toString().isEmpty() && !time.getText().toString().isEmpty()
                    && !data.getText().toString().isEmpty() && !amount.getText().toString().isEmpty() && !ponto.isEmpty()){
                int i = makeRequest(DataTime,amount.getText().toString(),inputEditText.getText().toString(),ponto);
                    if(i==1){
                        goBack(); }
                }

            }
        });
    }

    public int makeRequest(String DataTime, String price, String descricao,String ponto){
        int idPI=0;
        for(PontoInteresse e : listPI){
            if(ponto.equals(e.getNome())){
                idPI=e.getId();
            }
        }
        String token = preferences.getString("token",null);

        assert user != null;
        String request = "{\"horario\" : \"" + DataTime + "\",\"PontosInteresse\"  : \"" + idPI + "\",\"token\" : \"" + token + "\",\"descricao\":\""+descricao+
                         "\",\"Guia\":\""+user.getId()+ "\",\"preço\":\""+price+"\"}";
        HTTPRequester httpRequester = new HTTPRequester("https://server20200609112751.azurewebsites.net/api/Excursões",this);
        try {
            Thread thread = new Thread(() -> {
                try {
                    httpRequester.getInt(request);
                } catch (Exception ignore) {
                }
            });
            thread.start();thread.join();
        }catch (Exception ignore){}
        return httpRequester.check;
    }

    public void date(){

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "yyyy-MM-dd"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);
                dateTime.setText(sdf.format(myCalendar.getTime()));
            }

        };


        dateTime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

                DatePickerDialog dialog = new DatePickerDialog(Interface_Guia.this, date,
                        calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                dialog.show();
            }
        });
    }

    public void time(){
        TextView time = findViewById(R.id.time);
        TimePickerDialog.OnTimeSetListener date = new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                myCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                myCalendar.set(Calendar.MINUTE, minute);
                String myFormat = "hh:mm"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);
                time.setText(sdf.format(myCalendar.getTime()));
            }


        };


        time.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

                TimePickerDialog dialog = new TimePickerDialog(Interface_Guia.this, date,
                        calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),true);
                dialog.show();
            }
        });

    }


    public void payOut(){
        HTTPRequester httpRequester = new HTTPRequester("https://server20200609112751.azurewebsites.net/api/Excursões",this);
        TextView error = findViewById(R.id.errorM);
        cashOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String token = preferences.getString("token",null);
                if(!amount.getText().toString().isEmpty() &&  !iban.getText().toString().isEmpty()) {
                    double quantia = Double.parseDouble(amount.getText().toString());
                    if (quantia <= balance) {
                        String request = "{\"token\" : \"" + token + "\",\"descricao\":\"" + iban.getText().toString() +
                                "\",\"Guia\":\"" + user.getId() + "\",\"preço\":\"" + amount.getText().toString() + "\"}";
                        try {
                            Thread thread = new Thread(() -> {
                                try {
                                    httpRequester.getJsonObject(request);
                                } catch (Exception ignore) {
                                }
                            });
                            ;
                            thread.join();
                            error.setText("Dados guardos com sucesso.");
                        } catch (Exception ignore) {
                            error.setText("Erro, tente mais tarde");
                        }
                    }
                    else { error.setText("Quantia superior ao seu balance.");}
                }
                error.setVisibility(View.VISIBLE);
            }
        });


    }

}
