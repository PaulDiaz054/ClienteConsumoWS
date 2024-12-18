package com.example.clienteconsumows;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class RomboActivity extends AppCompatActivity {

    Button btnComunicar, btnRegresar;
    EditText edLado, edDiagonalMayor, edDiagonalMenor;
    TextView tvRespuesta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rombo);

        btnComunicar = findViewById(R.id.btnComunicar);
        btnRegresar = findViewById(R.id.btnRegresar);
        edLado = findViewById(R.id.edLadoR);
        edDiagonalMayor = findViewById(R.id.edDiagonalMayor);
        edDiagonalMenor = findViewById(R.id.edDiagonalMenor);
        tvRespuesta = findViewById(R.id.tvRespuesta);

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
        String url = "http://"+IP+":3000/rombo/"+ edDiagonalMenor.getText() + "&" + edDiagonalMayor.getText() + "&" + edLado.getText();
        OkHttpClient cliente = new OkHttpClient();
        Request get = new Request.Builder()
                .url(url)
                .build();

        cliente.newCall(get).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                RomboActivity.this.runOnUiThread(() ->
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

                        RomboActivity.this.runOnUiThread(() -> {
                            tvRespuesta.setText(respuesta);
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    RomboActivity.this.runOnUiThread(() ->
                            tvRespuesta.setText("Error al procesar la respuesta del servidor")
                    );
                }
            }
        });
    }
}