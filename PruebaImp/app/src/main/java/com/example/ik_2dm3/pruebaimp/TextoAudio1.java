package com.example.ik_2dm3.pruebaimp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class TextoAudio1 extends AppCompatActivity {

    String texto1;
    TextView mostrar1;
    String[] pruebatexto1;
    String fuera1="";
    MediaPlayer audio1;
    TextView siguiente1;
    boolean boton_mostrar1;
    int REQ_JUEGO1 = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        if (Build.VERSION.SDK_INT > 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        setContentView(R.layout.activity_texto_audio1);
        siguiente1=findViewById(R.id.txtSig1);
        siguiente1.setVisibility(View.VISIBLE);
        //Reproducimos el audio
        audio1 = MediaPlayer.create(TextoAudio1.this, R.raw.santaana1);
        //audio1.setVolume(200,200);
        audio1.start();
        //Sacamos el texto palabra a palabra
        texto1 = getResources().getString(R.string.gunea1);
        //Separamos las palabras
        pruebatexto1=texto1.split(" ");
        mostrar1 = findViewById(R.id.txtHistoria1);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ejecutar_hilo(mostrar1, fuera1, pruebatexto1, boton_mostrar1, 850);
            }
        }, 4500);
        siguiente1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent juego = new Intent(TextoAudio1.this, MainActivity.class);
                startActivityForResult(juego, REQ_JUEGO1);
            }
        });
    }

    public void ejecutar_hilo(TextView texto_pantalla, String sacar, String[] palabras, boolean terminar, int tiempo) {
        hilos hilo = new hilos();
        hilo.palabras = palabras;
        hilo.sacar = sacar;
        hilo.txtview = texto_pantalla;
        hilo.finalizado = terminar;
        hilo.milisegundos = tiempo;
        hilo.start();
    }
}
