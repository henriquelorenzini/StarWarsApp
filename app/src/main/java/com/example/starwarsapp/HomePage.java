package com.example.starwarsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class HomePage extends AppCompatActivity {
    ImageButton imageButtonPersonagem;
    ImageButton imageButtonPlaneta;
    ImageButton imageButtonFilmes;
    ImageButton imageButtonVeiculos;
    ImageButton imageButtonEspecie;
    ImageButton imageButtonNaves;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        imageButtonPersonagem = findViewById(R.id.imageButtonPersonagem);
        imageButtonPlaneta = findViewById(R.id.imageButtonPlaneta);
        imageButtonFilmes = findViewById(R.id.imageButtonFilmes);
        imageButtonVeiculos = findViewById(R.id.imageButtonVeiculos);
        imageButtonEspecie = findViewById(R.id.imageButtonEspecie);
        imageButtonNaves = findViewById(R.id.imageButtonNaves);
    }

    public void onClickPersonagemPage(View view) {
        imageButtonPersonagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(HomePage.this, MenuPersonagem.class);
                startActivity(it);
            }
        });
    }

    public void onClickPlaneta(View view) {
        imageButtonPlaneta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(HomePage.this, MenuPlaneta.class);
                startActivity(it);
            }
        });
    }

    public void onClickFilmes(View view) {
        imageButtonFilmes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(HomePage.this, MenuFilmes.class);
                startActivity(it);
            }
        });
    }

    public void onClickVeiculos(View view) {
        imageButtonVeiculos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(HomePage.this, MenuVeiculos.class);
                startActivity(it);
            }
        });
    }

    public void onClickEspecie(View view) {
        imageButtonEspecie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(HomePage.this, MenuEspecies.class);
                startActivity(it);
            }
        });
    }

    public void onClickNaves(View view) {
        imageButtonNaves.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(HomePage.this, MenuNaves.class);
                startActivity(it);
            }
        });
    }
}