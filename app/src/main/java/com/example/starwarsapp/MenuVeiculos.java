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

public class MenuVeiculos extends AppCompatActivity {
    EditText editTextSearchVeiculos;
    Button buttonVerifyVeiculos;
    Button buttonReturnVeiculos;
    TextView retornoVeiculos;
    HashMap<String,String> hashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_veiculos);

        hashMap = new HashMap<String, String>();
        hashMap.put("sand crawler", "4");
        hashMap.put("t 16", "6");
        hashMap.put("x 34", "7");
        hashMap.put("star fighter", "8");
        hashMap.put("snowspeeder", "14");
        hashMap.put("bomber", "16");
        hashMap.put("at at", "18");
        hashMap.put("at st", "19");
        hashMap.put("storm 4", "20");

        editTextSearchVeiculos = findViewById(R.id.editTextSearch);
        buttonVerifyVeiculos = findViewById(R.id.buttonVerify);
        buttonReturnVeiculos = findViewById(R.id.buttonReturn);
        retornoVeiculos = findViewById(R.id.textViewRetornoApi);
    }
        public void onClickSearchVeiculos(View view) {
            buttonVerifyVeiculos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (editTextSearchVeiculos.getText().toString().length() > 0 && !editTextSearchVeiculos.getText().toString().equals("")) {
                        HTTPServiceVeiculos service = new HTTPServiceVeiculos(editTextSearchVeiculos.getText().toString());

                        try {
                            VeiculosClass retorno = service.execute(hashMap.get(editTextSearchVeiculos.getText().toString())).get();
                            retornoVeiculos.setText(retorno.toString());
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }else{
                        retornoVeiculos.setText("Não foi encontrado");
                    }
                }
            });
    }
    public void onClickReturnVeiculos(View view) {
        buttonReturnVeiculos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MenuVeiculos.this, HomePage.class);
                startActivity(it);
            }
        });
    }
    public class HTTPServiceVeiculos extends AsyncTask<String, Void,VeiculosClass> {
        private String nome;

        public HTTPServiceVeiculos(String nome) {
            this.nome = nome;
        }

        @Override
        protected VeiculosClass doInBackground(String... strings) {
            nome = strings[0];
            StringBuilder resposta = new StringBuilder();
            try {
                URL url = new URL("https://swapi.co/api/vehicles/"+ this.nome);
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
            return new Gson().fromJson(resposta.toString(), VeiculosClass.class);
        }
    }

}


