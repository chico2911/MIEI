package com.example.myiatahome;

import android.content.Context;


import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

import okhttp3.OkHttpClient;

public class AskMe implements Serializable {
    private Context context;

    public AskMe(Context context) {
        this.context = context;
    }


    public String ask(String url) {
        AtomicReference<String> hello = new AtomicReference<>();
        Thread thread = new Thread(()->{
            OkHttpClient client = new OkHttpClient();
            okhttp3.Request request = new okhttp3.Request.Builder()
                    .url(url)
                    .build();

            try (okhttp3.Response response = client.newCall(request).execute()) {
                hello.set(Objects.requireNonNull(response.body()).string());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        try{
        thread.join();}catch (Exception ignore){}
        return hello.get();
    }

    /*
    public String askMe(String url){
        String resposta;
            RequestQueue queue = Volley.newRequestQueue(context);
            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println(error.getMessage());
                }
            });

        return null ;
    }*/

}
