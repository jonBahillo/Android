package com.example.ik_2dm3.pruebaimp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class TextoAudio2 extends AppCompatActivity {

    String texto2;
    TextView mostrar2;
    String[] pruebatexto2;
    String fuera2="";
    MediaPlayer audio2;
    TextView siguiente2;
    boolean boton_mostrar2;
    int REQ_JUEGO2 = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        if (Build.VERSION.SDK_INT > 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        setContentView(R.layout.activity_texto_audio2);
        siguiente2=findViewById(R.id.txtSig2);
        //siguiente5.setVisibility(View.INVISIBLE);
        //Reproducimos el audio
        audio2 = MediaPlayer.create(TextoAudio2.this, R.raw.kurutziaga);
        audio2.start();
        if (audio2.getCurrentPosition()==28000){
            audio2.stop();
        }
        //Sacamos el texto palabra a palabra
        texto2 = getResources().getString(R.string.gunea2_1);
        //Separamos las palabras
        pruebatexto2=texto2.split(" ");
        mostrar2 = findViewById(R.id.txtHistoria2);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ejecutar_hilo(mostrar2, fuera2, pruebatexto2, boton_mostrar2, 800);
            }
        }, 2500);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                audio2.stop();
            }
        }, 30300);
        siguiente2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent juego = new Intent(TextoAudio2.this, TextoAudio1.class);
                if (audio2.isPlaying()){
                    audio2.stop();
                }
                startActivityForResult(juego, REQ_JUEGO2);
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
