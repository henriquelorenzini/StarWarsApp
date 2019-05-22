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

public class MenuPersonagem extends AppCompatActivity {
    EditText editTextSearchPersonagem;
    Button buttonVerifyPersonagem;
    Button buttonReturnPersonagem;
    TextView retornoPersonagem;
    HashMap<String,String> hashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_personagem);

        hashMap = new HashMap<String, String>();
        hashMap.put("Luke", "1");
        hashMap.put("C3PO", "2");
        hashMap.put("R2D2", "3");
        hashMap.put("Darth Vader", "4");
        hashMap.put("Leia", "5");
        hashMap.put("Owen", "6");
        hashMap.put("Beru", "7");
        hashMap.put("R5D4", "8");
        hashMap.put("Biggs", "9");
        hashMap.put("Obi Wan", "10");
        hashMap.put("Anakin", "11");
        hashMap.put("Wilhuff", "12");



        editTextSearchPersonagem = findViewById(R.id.editTextSearch);
        buttonVerifyPersonagem = findViewById(R.id.buttonVerify);
        buttonReturnPersonagem = findViewById(R.id.buttonReturn);
        retornoPersonagem = findViewById(R.id.textViewRetornoApi);
    }

    public void onClickSearchPersonagem(View view) {
        buttonVerifyPersonagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextSearchPersonagem.getText().toString().length() > 0 && !editTextSearchPersonagem.getText().toString().equals("")) {
                    HTTPServicePersonagem service = new HTTPServicePersonagem(editTextSearchPersonagem.getText().toString());

                    try {
                        PersonagemClass retorno = service.execute(hashMap.get(editTextSearchPersonagem.getText().toString())).get();
                        retornoPersonagem.setText(retorno.toString());
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void onClickReturnPersonagem(View view) {
        buttonReturnPersonagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MenuPersonagem.this, HomePage.class);
                startActivity(it);
            }
        });
    }

    public class HTTPServicePersonagem extends AsyncTask<String, Void, PersonagemClass> {

        private String nome;

        public HTTPServicePersonagem(String nome) {
            this.nome = nome;
        }

        @Override
        protected void onPostExecute(PersonagemClass personagemClass) {
            super.onPostExecute(personagemClass);

        }

        @Override
        protected PersonagemClass doInBackground(String... strings) {
            nome = strings[0];
            StringBuilder resposta = new StringBuilder();
            try {
                URL url = new URL("https://swapi.co/api/people/"+ this.nome);
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
            return new Gson().fromJson(resposta.toString(), PersonagemClass.class);
        }
        }


    }

