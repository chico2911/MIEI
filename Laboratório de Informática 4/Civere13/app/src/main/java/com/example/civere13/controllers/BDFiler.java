package com.example.civere13.controllers;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.preference.PreferenceManager;

import com.example.civere13.models.Excursao;
import com.example.civere13.models.Hotel;
import com.example.civere13.models.PontoInteresse;
import com.example.civere13.models.Restaurante;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.nio.channels.Pipe;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class BDFiler implements Runnable{
    private DatabaseHelper db;
    private String token;
    private Context context;
    private String cidade;

    public  BDFiler(DatabaseHelper db, String token, Context context){
        this.db = db;
        this.context = context;
        this.token = token;
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        this.cidade = preferences.getString("cidade","Braga");
    }

    //Metodo que procede à atualização/introdução de info.
    public void run(){
        try {
            //vai buscar a versão ao SQLite
            Cursor versionSQLite = db.getAllData(4);
            DatabaseUtils.dumpCursorToString(versionSQLite);
            //Se não for a primeira vez que entro na aplicação
            getExcursaoFromServer();
            if(versionSQLite.getCount()!=0){
                versionSQLite.moveToFirst();
                String dataHotel = versionSQLite.getString(3);
                String dataPi = versionSQLite.getString(2);
                String dataRes = versionSQLite.getString(1);

                String rest = getDataFromServer(0,cidade,dataRes);
                String hotel = getDataFromServer(2,cidade,dataHotel);
                String pontoInt = getDataFromServer(1,cidade,dataPi);

                db.dropTable(4);
                db.insertVersion(rest, hotel, pontoInt);
                //insert version exc
            }
            //Else para a primeira vez. É preciso passa-lhe as versões
            else {
                String rest = getDataFromServer(0,cidade,"6/15/2020 7:33:26 PM");
                String hotel = getDataFromServer(2,cidade,"6/15/2020 7:33:26 PM");
                String pontoInt = getDataFromServer(1,cidade,"6/15/2020 7:33:26 PM");
                db.insertVersion(rest, hotel, pontoInt);
            }
        } catch (TokenInvalido tokenInvalido){
            System.out.println(tokenInvalido.getMessage());
        }catch (Exception e){e.printStackTrace();}

    }

    public void getExcursaoFromServer() {
        try {
            HTTPRequester requestInfo = new HTTPRequester("https://server20200609112751.azurewebsites.net/api/GetExcursões", context);
            db.dropTable(3);
            String request = "{\"Cidade\"  : \"" + cidade + "\",\"token\" : \"" + token + "\"}";
            JSONObject objRest = null;
            objRest = requestInfo.getJsonObject(request);
            JSONArray arrayRest = (JSONArray) objRest.get("cenas");
            assert arrayRest != null;
            for (int i = 0; i < arrayRest.size(); i++) {
                JSONObject rest = (JSONObject) arrayRest.get(i);
                int id = Integer.parseInt(Objects.requireNonNull(rest.get("IdExc")).toString());
                int PI = Integer.parseInt(Objects.requireNonNull(rest.get("Id")).toString());
                String nome = Objects.requireNonNull(rest.get("horario")).toString();
                int guia = Integer.parseInt(Objects.requireNonNull(rest.get("Guia")).toString());
                String descricao = Objects.requireNonNull(rest.get("descricao")).toString();
                float preco = Float.parseFloat(Objects.requireNonNull(rest.get("preço")).toString());
                db.insertExcursao(id, nome, PI, guia, preco, descricao);
            }
        }catch (Exception ignore){}
    }

    private String getDataFromServer(int tipo, String cidade, String data) throws Exception {
        String ret = data;
        String type = "";
        HTTPRequester requestInfo = new HTTPRequester("https://server20200609112751.azurewebsites.net/api/Images",context);
        switch (tipo){
            case 0:  type="r"; break;
            case 1:  type="p"; break;
            case 2:  type="h"; break;
        }
        String request = "{\"tipo\" : \"" + type + "\",\"cidade\"  : \"" + cidade + "\",\"token\" : \"" + token + "\",\"date\":\""+data+"\"}";
        JSONObject objRest = requestInfo.getJsonObject(request);
        JSONArray helper = (JSONArray) objRest.get("cenas");
        assert helper != null;
        if(helper.size()>0){
        JSONObject confirmData = (JSONObject) helper.get(0);
        if(!confirmData.get("date").equals( "TRUE"))   {
            db.dropTable(tipo);
            for (int i = 0; i < helper.size(); i++) {
                JSONObject rest = (JSONObject) helper.get(i);
                int id          = Integer.parseInt(Objects.requireNonNull(rest.get("id")).toString());
                String nome     = Objects.requireNonNull(rest.get("sitio")).toString();
                String morada   = rest.get("morada").toString();
                Map<String,String> contactos = new HashMap<>();
                contactos.put("Telemovel",rest.get("telemovel").toString()); //rest.get("telemovel").toString()
                contactos.put("Email",rest.get("mail").toString()); //rest.get("email").toString()
                contactos.put("Telefone",rest.get("telefone").toString()); //rest.get("telefone").toString()
                String descricao     = Objects.requireNonNull(rest.get("descrição")).toString();
                String classificacao = Objects.requireNonNull(rest.get("classificacao")).toString();
                String image         = Objects.requireNonNull(rest.get("url")).toString();
                db.insertPOI(tipo,id,nome,morada,contactos,descricao,classificacao,image);
            }
            ret = confirmData.get("date").toString(); //nova timestamp
        }}
        return ret;
    }

    //Metodo que vai buscar à BD info sobre Restaurantes.
    public ArrayList<PontoInteresse> getDataFromBD(int tipo){
        Cursor data = db.getAllData(tipo);
        ArrayList<PontoInteresse> mArrayList = new ArrayList<PontoInteresse>();
        try {
            data.moveToFirst();
            for (int i = 0; i < data.getCount(); i++) {
                int id = data.getInt(0);
                String nome = data.getString(1);
                String morada = data.getString(2);
                String descricao = data.getString(6);
                Map<String, String> contactos = new HashMap<>();
                if (tipo != 1) {
                    contactos.put("Telemovel", data.getString(3));
                    contactos.put("Telefone", data.getString(4));
                    contactos.put("Email", data.getString(5));
                }
                else {
                    contactos.put("Telemovel", "");
                    contactos.put("Telefone", "");
                    contactos.put("Email", "");
                }
                double classification = Double.parseDouble(data.getString(7));
                String rImgs = data.getString(8);
                switch (tipo) {
                    case 0:
                        Restaurante Res2Add = new Restaurante(id, 0, nome, descricao, classification, contactos, morada, rImgs);
                        mArrayList.add(Res2Add);
                        break;
                    case 1:
                        PontoInteresse PI2Add = new PontoInteresse(id, 1, nome, descricao, classification, contactos, morada, rImgs);
                        mArrayList.add(PI2Add);
                        break;
                    case 2:
                        Hotel Hotel2Add = new Hotel(id, 2, nome, descricao, classification, contactos, morada, rImgs);
                        mArrayList.add(Hotel2Add);
                        break;
                }
                data.moveToNext();
            }
        }catch (Exception e){e.printStackTrace();}
        return mArrayList;
    }

    public ArrayList<Excursao> getExcursao(int type, ArrayList<PontoInteresse> PI){
        ArrayList res = new ArrayList<PontoInteresse>();
        Cursor data = db.getAllData(type);
        data.moveToFirst();
        for(int i = 0; i<data.getCount() ; i++) {
            int id = data.getInt(0);
            String date = data.getString(1);
            int idPtoInicial = data.getInt(2);
            int idGuia = data.getInt(3);
            float preco = Float.parseFloat(data.getString(4));
            String descricao = data.getString(5);
            String url = "";
            String morada = "";
            for(PontoInteresse e : PI){
                if(e.getId()==idPtoInicial){
                    url = e.getUrlIMG();
                    morada = e.getMorada();}
            }

            Excursao toAdd = new Excursao(id,idGuia,date,idPtoInicial,morada,url,descricao,preco);

            res.add(toAdd);
            data.moveToNext();
        }
        return res;
    }
}
