package com.example.starwarsapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class MenuEspecies extends AppCompatActivity {
    EditText editTextSearchEspecies;
    Button buttonVerifyEspecies;
    Button buttonReturnEspecies;
    TextView retornoEspecies;
    HashMap<String,String> hashMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_especies);

        hashMap = new HashMap<String, String>();
        hashMap.put("humano", "1");
        hashMap.put("droid", "2");
        hashMap.put("wookiee", "3");
        hashMap.put("rodian", "4");
        hashMap.put("hutt", "5");
        hashMap.put("yodas", "6");
        hashMap.put("trandoshan", "7");
        hashMap.put("mon calamari", "8");
        hashMap.put("ewok", "9");
        hashMap.put("sullustan", "10");
        hashMap.put("neimodian", "11");
        hashMap.put("gungan", "12");


        editTextSearchEspecies = findViewById(R.id.editTextSearch);
        buttonVerifyEspecies = findViewById(R.id.buttonVerify);
        buttonReturnEspecies = findViewById(R.id.buttonReturn);
        retornoEspecies = findViewById(R.id.textViewRetornoApi);
    }
    public void onClickSearchEspecies(View view) {
        buttonVerifyEspecies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextSearchEspecies.getText().toString().length() > 0 && !editTextSearchEspecies.getText().toString().equals("")) {
                    HTTPServiceEspecies service = new HTTPServiceEspecies(editTextSearchEspecies.getText().toString());

                    try {
                        EspeciesClass retorno = service.execute(hashMap.get(editTextSearchEspecies.getText().toString())).get();
                        retornoEspecies.setText(retorno.toString());
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    retornoEspecies.setText("Não foi encontrado");
                }
            }
        });
    }
    public void onClickReturnEspecies(View view) {
        buttonReturnEspecies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MenuEspecies.this, HomePage.class);
                startActivity(it);
            }
        });
    }
    public class HTTPServiceEspecies extends AsyncTask<String, Void,EspeciesClass> {
        private String nome;

        public HTTPServiceEspecies(String nome) {
            this.nome = nome;
        }

        @Override
        protected EspeciesClass doInBackground(String... strings) {
            nome = strings[0];
            StringBuilder resposta = new StringBuilder();
            try {
                URL url = new URL("https://swapi.co/api/species/"+ this.nome);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Accept", "application/json");
                connection.setConnectTimeout(5000);
                connection.connect();

                Scanner scanner = new Scanner(url.openStream());
                while (scanner.hasNext()){
                    resposta.append(scanner.next());
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new Gson().fromJson(resposta.toString(), EspeciesClass.class);
        }
    }

}
