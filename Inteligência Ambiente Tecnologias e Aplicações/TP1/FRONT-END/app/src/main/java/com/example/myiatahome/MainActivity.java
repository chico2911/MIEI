package com.example.myiatahome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private AskMe askMe;
    private Autoclismo atc;
    private Interruptor itp;
    private MaquinaLavar ml;
    private Temperatura tem;
    private Torneira torn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        askMe = new AskMe(this);
        setVariaveis();
        TextView temp = findViewById(R.id.temperature);
        View tempButton = findViewById(R.id.view);
        temp.setText(tem.getState() + " ºC");
        tempButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGraphic();
            }
        });
        setUI();
    }


    public void openGraphic(){
        Intent graphicsMain = new Intent(this,MainActivity2.class);
        startActivity(graphicsMain);
    }

    public void setVariaveis(){
        String valores = askMe.ask("http://10.0.2.2:8080/getSensores");
        String[] splitted =  valores.split("-");
        int temperatura = Integer.parseInt(splitted[0]);
        int interruptor = Integer.parseInt(splitted[1]);
        int mal = Integer.parseInt(splitted[2]);
        int auto = Integer.parseInt(splitted[3]);
        int tor = Integer.parseInt(splitted[4]);
        atc  = new Autoclismo(auto);
        itp  = new Interruptor(interruptor);
        ml   = new MaquinaLavar(mal);
        tem  = new Temperatura(temperatura,askMe);
        torn = new Torneira(tor);
    }

    public void setUI(){
        TextView intName = findViewById(R.id.interruptorName); intName.setText(itp.getNome());
        TextView maqName = findViewById(R.id.maquinaName); maqName.setText(ml.getNome());
        TextView torneira = findViewById(R.id.torneiraNome); torneira.setText(torn.getNome());
        TextView autoclismo = findViewById(R.id.autoclismoNome); autoclismo.setText(atc.getNome());
        TextView maquinaTotal = findViewById(R.id.maqtotal); maquinaTotal.setText(ml.getState()+"");
        TextView torneiraTotal = findViewById(R.id.torneiraTotal); torneiraTotal.setText(torn.getConsumo()+" L");
        TextView autoTotal = findViewById(R.id.autoclismoTotal); autoTotal.setText(atc.getConsumo()+" L");
        Switch intSwitch = findViewById(R.id.switch1); intSwitch.setChecked(itp.getState() == 1);
        intSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    itp.setState(0);
                }
                else{
                    itp.setState(1);
                }
                askMe.ask("http://10.0.2.2:8080/changeStateInterruptor");
            }
        });
    }

}