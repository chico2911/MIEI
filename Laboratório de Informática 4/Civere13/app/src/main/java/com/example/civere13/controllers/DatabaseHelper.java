package com.example.civere13.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.Map;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final  String DATABASE_NAME = "Civere.db";
    private static final String USER = "user_table";
    private static final String HOTEL = "hotel_table";
    private static final String RESTAURANTE = "restaurante_table";
    private static final String PONTOINT = "pontoInt_table";
    private static final String EXCURSAO = "excursoe_table";
    private static final String TOP_Res = "top_res_table";
    private static final String TOP_Hot = "top_hot_table";
    private static final String TOP_PI = "top_pi_table";
    private static final String VERSION = "version_table";

    public DatabaseHelper(@Nullable Context context) {
        //cria a base de dados
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String restaurante_table = ("create table " + RESTAURANTE + " (ID INTEGER PRIMARY KEY, nome TEXT, morada TEXT, telemovel TEXT,telefone TEXT,email TEXT, categoria TEXT,classificacao TEXT, image_path TEXT)");
        String pontoInt_table    = ("create table " + PONTOINT    + " (ID INTEGER PRIMARY KEY, nome TEXT, morada TEXT, telemovel TEXT,telefone TEXT,email TEXT, descricao TEXT,classificacao TEXT, image_path TEXT)");
        String hotel_table       = ("create table " + HOTEL       + " (ID INTEGER PRIMARY KEY, nome TEXT, morada TEXT, telemovel TEXT,telefone TEXT,email TEXT, descricao TEXT,classificacao TEXT,image_path TEXT)");
        String excursoes         = ("create table " + EXCURSAO    + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, data_hora TEXT, ponto_interesse_id INTEGER, guia_id INTEGER, preco TEXT, descricao TEXT)");
        String version_table     = ("create table " + VERSION     + " (VERSION TEXT PRIMARY KEY, RESTAURANTE TEXT, PONTOINT TEXT, HOTEL TEXT)");
        db.execSQL(pontoInt_table); db.execSQL(hotel_table);
        db.execSQL(excursoes);
        db.execSQL(version_table);  db.execSQL(restaurante_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + RESTAURANTE);
        db.execSQL("DROP TABLE IF EXISTS " + PONTOINT);
        db.execSQL("DROP TABLE IF EXISTS " + HOTEL);
        db.execSQL("DROP TABLE IF EXISTS " + EXCURSAO);
        db.execSQL("DROP TABLE IF EXISTS " + VERSION);
        onCreate(db);
    }


    //type = 0 -> res   type = 1 -> POI  type  = 2 -> Hotel
    public void insertPOI(int type, int id, String nome, String morada, Map<String,String> contactos, String descricao, String classificacao, String image){
        SQLiteDatabase db = this.getWritableDatabase();
        long result=0;
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID",id);
        contentValues.put("nome", nome);
        contentValues.put("morada", morada);
        contentValues.put("telemovel", contactos.get("Telemovel"));
        contentValues.put("email", contactos.get("Email"));
        contentValues.put("telefone", contactos.get("Telefone"));
        if(type == 0) contentValues.put("categoria", descricao);
        else {
            contentValues.put("descricao", descricao);
        }
        contentValues.put("classificacao", classificacao);
        contentValues.put("image_path"   , image);
        switch(type){
            case 0: result = db.insert(RESTAURANTE, null, contentValues); break;
            case 1: result = db.insert(PONTOINT, null, contentValues);    break;
            case 2: result = db.insert(HOTEL, null, contentValues);       break;
        }
    }

    public void insertExcursao(int id, String data_hora, int ponto_interesse, int guia, float preco, String descricao){
        SQLiteDatabase db = this.getWritableDatabase();
        long result;
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID", id);
        contentValues.put("data_hora", data_hora);
        contentValues.put("ponto_interesse_id", ponto_interesse);
        contentValues.put("guia_id", guia);
        contentValues.put("descricao", descricao);
        contentValues.put("preco", String.valueOf(preco));
        result = db.insert(EXCURSAO, null, contentValues);
    }

    public void insertVersion(String dateRest, String dataHotel, String dataPI){
        SQLiteDatabase db = this.getWritableDatabase();
        long result;
        String column = null;
        ContentValues contentValues = new ContentValues();
        contentValues.put("RESTAURANTE", dateRest);
        contentValues.put("HOTEL", dataHotel);
        contentValues.put("PONTOINT", dataPI);
        contentValues.put("VERSION", "6/25/2020 12:05:00 PM");
        result = db.insert(VERSION, null, contentValues);
    }

    public Cursor getAllData(int tabela) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res;
        switch (tabela){
            case 0:
                res = db.rawQuery("select * from " + RESTAURANTE, null);
                break;
            case 1:
                res = db.rawQuery("select * from " + PONTOINT, null);
                break;
            case 2:
                res = db.rawQuery("select * from " + HOTEL, null);
                break;
            case 3:
                res = db.rawQuery("select * from " + EXCURSAO, null);
                break;
            case 4:
                res = db.rawQuery("select * from " + VERSION, null);
                break;
            default:
                res = null;
        }
        return res;
    }

    public void dropTable(int tabela) {
        SQLiteDatabase db = this.getWritableDatabase();
        switch (tabela){
            case 0:
                db.execSQL("DELETE FROM " + RESTAURANTE);
                break;
            case 1:
                db.execSQL("DELETE FROM " + PONTOINT);
                break;
            case 2:
                db.execSQL("DELETE FROM " + HOTEL);
                break;
            case 3:
                db.execSQL("DELETE FROM " + EXCURSAO);
                break;
            case 4:
                db.execSQL("DELETE FROM " + VERSION);
                break;
            default:
                db.execSQL("DELETE FROM " + VERSION);
                db.execSQL("DELETE FROM " + EXCURSAO);
                db.execSQL("DELETE FROM " + HOTEL);
                db.execSQL("DELETE FROM " + PONTOINT);
                db.execSQL("DELETE FROM " + RESTAURANTE);
                break;
        }
    }
}
