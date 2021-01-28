package com.example.civere13.view;

import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.civere13.R;
import com.example.civere13.controllers.HTTPRequester;
import com.example.civere13.models.Comentario;
import com.example.civere13.models.PontoInteresse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Interface_Info extends AppCompatActivity {
    private TextView nome;
    private TextView itemcontact;
    private ImageView image;
    private TextView itemaddress;
    private PontoInteresse item;
    private TextView itemclassification;
    private Button comments;
    private TextView info;
    private List<Comentario> comentarios;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    private int c=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        setIntances();
        Type type = new TypeToken<PontoInteresse>(){}.getType();
        Gson gson = new Gson();
        item= (PontoInteresse) gson.fromJson(getIntent().getStringExtra("item"), type);
        Picasso.get().load(item.getUrlIMG()).into(image);
        nome.setText(item.getNome());
        System.out.println(item.toString());
        if(!item.getContactos().get("Telemovel").isEmpty()){
            itemcontact.setText(item.getContactos().get("Telemovel")); c=2;
        }
        else{
            if(!item.getContactos().get("Telefone").isEmpty()){
                itemcontact.setText(item.getContactos().get("Telefone"));
                c=0;
            }
        else {itemcontact.setText(item.getContactos().get("Email"));c=1;}}
        String stringdouble= Double.toString(item.getClassification());
        itemclassification.setText(stringdouble);
        String street = item.getMorada();
        itemaddress.setText(street);
        info.setText(item.getDescricao());
        itemcontact.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return openContact();
            }
        });
        setButtons();
    }

    public void setIntances() {
        nome = findViewById(R.id.name);
        image = findViewById(R.id.image);
        itemclassification=findViewById(R.id.itemclassification);
        itemcontact=findViewById(R.id.itemcontact);
        itemaddress=findViewById(R.id.itemaddress);
        info=findViewById(R.id.info);
        comments = findViewById(R.id.button4);
        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor      = preferences.edit();
    }

    public void setButtons(){
        itemcontact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(c){
                    case 0:    c=1;
                              if(!item.getContactos().get("Email").isEmpty()){itemcontact.setText(item.getContactos().get("Email"));}
                              else {onClick(v);}

                            break;
                    case 1:c=2;
                        if(!item.getContactos().get("Telemovel").isEmpty()){itemcontact.setText(item.getContactos().get("Telemovel")); }
                            else {onClick(v);}

                        break;
                    case 2:c=0;  if(!item.getContactos().get("Telefone").isEmpty()){itemcontact.setText(item.getContactos().get("Telefone"));}
                            else {onClick(v);}

                        break;
            }
        }});
        Button back=findViewById(R.id.back2);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thisBack();
            }
        });
        itemaddress.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                String street = item.getMorada();
                Uri gmmIntentUri = Uri.parse("geo:0,0?q="+street);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                }
                return false;
            }
        });
        setCommentsButton();
    }

    public void setCommentsButton(){
        if(isOnline()){
        try {
            String request = "{\"tipo\" : \""+item.getTipo()+"\",\"token\" : \""+preferences.getString("token",null)+"\",\"id\":\""+item.getId()+"\"}";
            HTTPRequester requester = new HTTPRequester("https://server20200609112751.azurewebsites.net/api/Comments",this);
            Thread retriveComments = new Thread(() -> {
                try {
                    JSONObject fromServer = requester.getJsonObject(request);
                    JSONArray toParse = (JSONArray) fromServer.get("cenas");
                    parseAndSave(toParse);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            retriveComments.start();
            retriveComments.join();
            comments.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openComments();
                }
            });
        }catch (Exception e){
            e.printStackTrace();};}
    }

    private void parseAndSave(JSONArray toParse) {
        comentarios = new ArrayList<>();
        // parsing the jsonobject
        for(int i=0;i<toParse.size();i++){
            JSONObject obj = (JSONObject) toParse.get(i);
            String texto = obj.get("Texto").toString();
            String user = obj.get("Utilizador").toString();
            int classification =Integer.parseInt(obj.get("Votacao").toString());
            comentarios.add(new Comentario(texto,user,classification));
        }
        Gson gson = new Gson();
        String comments = gson.toJson(comentarios);
        editor.putString("comments",comments);
        editor.apply();
    }

    public void openComments(){
        Intent comments = new Intent(this, Comentarios.class);
        comments.putExtra("id",item.getId());
        comments.putExtra("tipo",item.getTipo());
        startActivity(comments);
    }

    public boolean openContact(){
        if(c==1){
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:"+item.getContactos().get("Email")));
            startActivity(intent);
        }
        else {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:"+itemcontact.getText()));
            startActivity(intent);
        }
        return false;
    }

    public boolean isOnline(){
        ConnectivityManager cm =
                (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }

    public void thisBack(){
    editor.remove("comments");
    editor.apply();
    this.finish();
        }
}
