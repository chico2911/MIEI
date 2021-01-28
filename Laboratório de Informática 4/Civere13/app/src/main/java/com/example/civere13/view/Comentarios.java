package com.example.civere13.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.civere13.R;
import com.example.civere13.models.Comentario;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Comentarios extends AppCompatActivity {
    private ArrayList<Comentario> comments; ListView listView;
    private int id; private int type;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentarios);
        listView = findViewById(R.id.lista);
        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        getComments();
        inflateView();
        Button back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thisBack();
            }
        });

        Button add = findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addComment();
            }
        });

    }

    private void addComment() {
        Intent addComment = new Intent(this, AddComment.class);
        addComment.putExtra("id",id);
        addComment.putExtra("tipo",type);
        startActivity(addComment);
    }

    public void getComments(){
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Comentario>>(){}.getType();
        comments =  gson.fromJson(preferences.getString("comments", null), type);
        this.id = getIntent().getIntExtra("id",0);
        this.type = getIntent().getIntExtra("tipo",0);
    }
    public void inflateView(){
        ComentarioAdapter inflate = new ComentarioAdapter(this,R.layout.comentario,comments);
        listView.setAdapter(inflate);
    }

    public void thisBack(){this.finish();}
}


class ComentarioAdapter extends ArrayAdapter<Comentario> {
    Context context;
    public ComentarioAdapter(Context context, int resource, ArrayList<Comentario> objects) {
        super(context, resource,objects);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater)context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View comment = layoutInflater.inflate(R.layout.comentario, parent, false);
        Comentario comentario = getItem(position);
        TextView utilizador = comment.findViewById(R.id.user);
        TextView classi = comment.findViewById(R.id.classication);
        TextView texto = comment.findViewById(R.id.comment);

        utilizador.setText("User: " + comentario.getUtilizador());
        classi.setText("Class: " + comentario.getClassificacao());
        texto.setText( comentario.getTexto());


        return comment;
    }
}
