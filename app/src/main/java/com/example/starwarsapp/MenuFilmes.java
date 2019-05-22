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

public class MenuFilmes extends AppCompatActivity {
    EditText editTextSearchFilmes;
    Button buttonVerifyFilmes;
    Button buttonReturnFilmes;
    TextView retornoFilmes;
    HashMap<String,String> hashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_filmes);

        hashMap = new HashMap<String, String>();
        hashMap.put("episodio 4", "1");
        hashMap.put("episodio 5", "2");
        hashMap.put("episodio 6", "3");
        hashMap.put("episodio 1", "4");
        hashMap.put("episodio 2", "5");
        hashMap.put("episodio 3", "6");
        hashMap.put("episodio 7", "7");

        editTextSearchFilmes = findViewById(R.id.editTextSearch);
        buttonVerifyFilmes = findViewById(R.id.buttonVerify);
        buttonReturnFilmes = findViewById(R.id.buttonReturn);
        retornoFilmes = findViewById(R.id.textViewRetornoApi);
    }
    public void onClickSearchFilme(View view) {
        buttonVerifyFilmes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextSearchFilmes.getText().toString().length() > 0 && !editTextSearchFilmes.getText().toString().equals("")) {
                    HTTPServiceFilmes service = new HTTPServiceFilmes(editTextSearchFilmes.getText().toString());

                    try {
                        FilmesClass retorno = service.execute(hashMap.get(editTextSearchFilmes.getText().toString())).get();
                        retornoFilmes.setText(retorno.toString());
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    retornoFilmes.setText("Não foi encontrado");
                }
            }
        });
    }
    public void onClickReturnFilmes(View view) {
        buttonReturnFilmes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MenuFilmes.this, HomePage.class);
                startActivity(it);
            }
        });
    }
    public class HTTPServiceFilmes extends AsyncTask<String, Void,FilmesClass> {
        private String nome;

        public HTTPServiceFilmes(String nome) {
            this.nome = nome;
        }

        @Override
        protected FilmesClass doInBackground(String... strings) {
            nome = strings[0];
            StringBuilder resposta = new StringBuilder();
            try {
                URL url = new URL("https://swapi.co/api/films/"+ this.nome);
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
            return new Gson().fromJson(resposta.toString(), FilmesClass.class);
        }
    }



}
