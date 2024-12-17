package com.example.clienteconsumows;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {

    Button btnJson, btnBio, btnSuma, btnSumaPar, btnRectangulo, btnPentagono, btnRombo;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnBio = findViewById(R.id.btnBio);
        btnJson = findViewById(R.id.btnJson);
        btnSuma = findViewById(R.id.btnSuma);
        btnSumaPar = findViewById(R.id.btnSumaPar);
        btnRectangulo = findViewById(R.id.btnRectangulo);
        btnPentagono = findViewById(R.id.btnPentagono);
        btnRombo = findViewById(R.id.btnRombo);

        btnBio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CambiarPantallas(BioActivity.class);
            }
        });

        btnJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CambiarPantallas(MainActivity.class);
            }
        });

        btnSuma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CambiarPantallas(SumaActivity.class);
            }
        });

        btnSumaPar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CambiarPantallas(SumaParActivity.class);
            }
        });

        btnRectangulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CambiarPantallas(RectanguloActivity.class);
            }
        });

        btnPentagono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CambiarPantallas(PentagonoActivity.class);
            }
        });
    }
    public void CambiarPantallas( Class<?> targetActivity) {
        Intent intent = new Intent(Menu.this, targetActivity);
        startActivity(intent);
    }

}