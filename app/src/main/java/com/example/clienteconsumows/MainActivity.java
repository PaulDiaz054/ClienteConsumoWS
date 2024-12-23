package com.example.clienteconsumows;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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

public class MainActivity extends AppCompatActivity {


    Button btnComunicar, btnRegresar;
    TextView tvRespuesta;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnComunicar = findViewById(R.id.btnComunicar);
        tvRespuesta = findViewById(R.id.tvRespuesta);
        btnRegresar = findViewById(R.id.btnRegresar);

        VariableGlobal app = (VariableGlobal) getApplicationContext();
        btnComunicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConsumirWS(app.getIP());
            }
        });
        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void ConsumirWS(String IP) {
        String url = "http://"+IP+":3000/nombre";
        OkHttpClient cliente = new OkHttpClient();
        Request get = new Request.Builder()
                .url(url)
                .build();

        cliente.newCall(get).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                MainActivity.this.runOnUiThread(() ->
                        tvRespuesta.setText("Error al conectar con el servidor: " + e.toString())
                );
            }

            @Override
            public void onResponse(Call call, Response response) {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful()) {
                        throw new IOException("Unexpected code " + response);
                    } else {
                        String respuestaJson = responseBody.string();
                        JSONArray lista = new JSONArray(respuestaJson);

                        MainActivity.this.runOnUiThread(() -> {
                            tvRespuesta.setText(lista.toString());
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    MainActivity.this.runOnUiThread(() ->
                            tvRespuesta.setText("Error al procesar la respuesta del servidor")
                    );
                }
            }
        });
    }
}