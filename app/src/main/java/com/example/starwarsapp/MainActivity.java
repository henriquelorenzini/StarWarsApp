package com.example.starwarsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button buttonStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_page);
    public void onClick (Button);
         buttonStart =  findViewById(R.id.buttonStart);

         buttonStart.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent it = new Intent(MainActivity.this, homePage.this);
                 startActivity(it);
             }
         });
    }
}
