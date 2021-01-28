package com.example.civere13.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.civere13.R;
import com.example.civere13.models.PontoInteresse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Objects;

class PontoInteresseAdapter extends ArrayAdapter<PontoInteresse> {
    int type;
    Context context;

    public PontoInteresseAdapter(Context context , int resource, ArrayList<PontoInteresse> objects, int type) {
        super(context, resource,objects);
        this.context = context;
        this.type = type;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater)context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(R.layout.row, parent, false);
        ImageView images = row.findViewById(R.id.image);
        TextView myTitle = row.findViewById(R.id.user);
        TextView myDescription = row.findViewById(R.id.comment);


        // now set our resources on views
       // images.setImageResource(getItem(position).getrImgs()[0]);

        if(!Objects.requireNonNull(getItem(position)).getUrlIMG().isEmpty()){
        Picasso.get().load(getItem(position).getUrlIMG()).into(images);}
        myTitle.setText(getItem(position).getNome());
        myDescription.setText(getItem(position).getDescricao());

        return row;
    }
}
