package com.example.clienteconsumows;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class BioActivity extends AppCompatActivity {

    Button btnComunicar, btnRegresar;
    TextView tvRespuesta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bio);

        btnComunicar = findViewById(R.id.btnComunicar);
        btnRegresar = findViewById(R.id.btnRegresar);
        tvRespuesta = findViewById(R.id.tvRespuesta);

        btnComunicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConsumirWS();
            }
        });

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void ConsumirWS() {
        String url = "http://192.168.1.11:3000/Paul";
        OkHttpClient cliente = new OkHttpClient();
        Request get = new Request.Builder()
                .url(url)
                .build();

        cliente.newCall(get).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                BioActivity.this.runOnUiThread(() ->
                        tvRespuesta.setText("Error al conectar con el servidor: " + e.toString())
                );
            }

            @Override
            public void onResponse(Call call, Response response) {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful()) {
                        throw new IOException("Unexpected code " + response);
                    } else {
                        String respuesta = responseBody.string();

                        BioActivity.this.runOnUiThread(() -> {
                            tvRespuesta.setText(respuesta);
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    BioActivity.this.runOnUiThread(() ->
                            tvRespuesta.setText("Error al procesar la respuesta del servidor")
                    );
                }
            }
        });
    }
}