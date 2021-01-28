package com.example.myiatahome;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
public class MainActivity2 extends AppCompatActivity {
    private AskMe askMe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        askMe = new AskMe(this);
        last24Hours();
        last7days();

    }


    public void last24Hours(){

        String arr = askMe.ask("http://10.0.2.2:8080/getTemperatura24");
        String[] items = arr.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\s", "").split(",");

        int[] results = new int[items.length];
        ArrayList<Entry> entries = new ArrayList<>();
        for (int i = 0; i < items.length; i++) {
            try {
                results[i] = Integer.parseInt(items[i]);
                entries.add(new Entry(i,results[i]));
            } catch (NumberFormatException nfe) {
                //NOTE: write something here if you need to recover from formatting errors
            };
        }

        LineDataSet lineDataSet = new LineDataSet(entries,"Temperatura");
        LineData data = new LineData(lineDataSet);
        LineChart lineChart = findViewById(R.id.chart);
        lineChart.setData(data);
        lineChart.setScaleX(1);
        lineChart.setScaleY((float) 1);

    }

    public void last7days(){
        String arr = askMe.ask("http://10.0.2.2:8080/getTemperatura7days");
        String[] items = arr.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\s", "").split(",");

        int[] results = new int[items.length];
        ArrayList<Entry> entries = new ArrayList<>();
        for (int i = 0; i < items.length; i++) {
            try {
                results[i] = Integer.parseInt(items[i]);
                entries.add(new Entry(i,results[i]));
            } catch (NumberFormatException nfe) {
                //NOTE: write something here if you need to recover from formatting errors
            };
        }

        LineDataSet lineDataSet = new LineDataSet(entries,"Temperatura 7 Dias");
        LineData data = new LineData(lineDataSet);
        LineChart lineChart = findViewById(R.id.chart7);
        lineChart.setData(data);
        lineChart.setScaleX(1);
        lineChart.setScaleY((float) 1);
    }

}