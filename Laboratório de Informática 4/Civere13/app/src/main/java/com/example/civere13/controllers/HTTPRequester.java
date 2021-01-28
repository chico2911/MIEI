package com.example.civere13.controllers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.JsonToken;
import android.util.Log;

import com.example.civere13.view.MainActivity;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Objects;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;



public class HTTPRequester {
    private HttpURLConnection con;
    public int check;
    private final String charset = "utf-8";
    private JSONParser json = new JSONParser();
    SharedPreferences        preferences;
    SharedPreferences.Editor editor;
    Context context;

    public HTTPRequester(String url, Context context) {
        try {
            this.context = context;
            preferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
            editor      = preferences.edit();
            URL obj = new URL(url);
            con = (HttpURLConnection) obj.openConnection();
            establishConnection();
        } catch (Exception e) {
            Log.i("Erro", "HTTPRequester: " + e.getMessage());
        }
    }

    private void establishConnection() throws KeyManagementException, ProtocolException, NoSuchAlgorithmException {
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }

            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }
        }
        };
        // Install the all-trusting trust manager
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        // Create all-trusting host name verifier
        HostnameVerifier allHostsValid = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
        // Install the all-trusting host verifier
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);
    }

    public String makeRequest(String request){
        String answer = "";
        check = 0;
        try (OutputStream os = con.getOutputStream()) {
            byte[] input = request.getBytes("utf-8");
            os.write(input, 0, input.length);
        } catch (Exception e) {
            check=1;
        }
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            answer = response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            check = 1;
        }
        return answer;
    }

    public int getInt(String request){
        check = 0;
        try{
          String answer = makeRequest(request);
          check = Integer.parseInt(answer);
          if(check==0){openMainActivity();
              throw new TokenInvalido("O Token fornecido esta invalido.");}
        }catch (Exception e){Log.e("Erro: ", Objects.requireNonNull(e.getMessage())); check=400;}
        return check;
    }

    public int getValido(String request){
        check = 0;
        try{
            String answer = makeRequest(request);
            JSONObject confirm = (JSONObject) json.parse(answer);
            check = Integer.parseInt(Objects.requireNonNull(confirm.get("valido")).toString());
        }catch (Exception e){ check=1;}
        return check;
    }

    public String getString(String request) throws Exception {return makeRequest(request);}

    public JSONObject getJsonObject(String request) throws TokenInvalido,Exception{
        check = 0;
        String answer = makeRequest(request);
        JSONObject object = (JSONObject) json.parse(answer);
        check = Integer.parseInt(Objects.requireNonNull(object.get("valido")).toString());
        if(check!=1){
            editor.remove("token");
            editor.remove("user");
            editor.apply();
            openMainActivity();
            throw new TokenInvalido("O Token fornecido esta invalido.");}
        else {
            editor.putString("token", Objects.requireNonNull(object.get("token")).toString());
            editor.apply();
            Log.i("Novo Token:",
                    Objects.requireNonNull(object.get("token")).toString());
        }
        return object;
    }

    public JSONObject login(String request) throws TokenInvalido,Exception{
        check = 0;
        String answer = makeRequest(request);
        JSONObject object = (JSONObject) json.parse(answer);
        check = Integer.parseInt(Objects.requireNonNull(object.get("valido")).toString());
        return object;
    }


    private void openMainActivity(){
        Intent intent = new Intent(context.getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}
