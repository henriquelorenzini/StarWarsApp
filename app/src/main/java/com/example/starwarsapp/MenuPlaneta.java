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

public class MenuPlaneta extends AppCompatActivity {
    EditText editTextSearchPlaneta;
    Button buttonVerifyPlaneta;
    Button buttonReturnPlaneta;
    TextView retornoPlaneta;
    HashMap<String,String> hashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_planeta);

        hashMap = new HashMap<String, String>();
        hashMap.put("tatooine", "1");
        hashMap.put("alderaan", "2");
        hashMap.put("hoth", "4");
        hashMap.put("dagobah", "5");
        hashMap.put("bespin", "6");
        hashMap.put("endor", "7");
        hashMap.put("naboo", "8");
        hashMap.put("coruscant", "9");
        hashMap.put("kamino", "10");
        hashMap.put("geonosis", "11");
        hashMap.put("utapau", "12");

        editTextSearchPlaneta = findViewById(R.id.editTextSearch);
        buttonVerifyPlaneta = findViewById(R.id.buttonVerify);
        buttonReturnPlaneta = findViewById(R.id.buttonReturn);
        retornoPlaneta = findViewById(R.id.textViewRetornoApi);
    }
    public void onClickReturnPlaneta(View view) {
        buttonReturnPlaneta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MenuPlaneta.this, HomePage.class);
                startActivity(it);
            }
        });
    }
    public void onClickSearchPlaneta(View view) {
        buttonVerifyPlaneta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextSearchPlaneta.getText().toString().length() > 0 && !editTextSearchPlaneta.getText().toString().equals("")) {
                    HTTPServicePlaneta service = new HTTPServicePlaneta(editTextSearchPlaneta.getText().toString());

                    try {
                        PlanetaClass retorno = service.execute(hashMap.get(editTextSearchPlaneta.getText().toString())).get();
                        retornoPlaneta.setText(retorno.toString());
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else {
                    retornoPlaneta.setText("Não foi encontrado");
                }
            }
        });
    }
    public class HTTPServicePlaneta extends AsyncTask<String, Void, PlanetaClass> {
        private String nome;

        public HTTPServicePlaneta(String nome) {
            this.nome = nome;
        }

        @Override
        protected PlanetaClass doInBackground(String... strings) {
            nome = strings[0];
            StringBuilder resposta = new StringBuilder();
            try {
                URL url = new URL("https://swapi.co/api/planets/"+ this.nome);
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
            return new Gson().fromJson(resposta.toString(), PlanetaClass.class);
        }
    }


}
