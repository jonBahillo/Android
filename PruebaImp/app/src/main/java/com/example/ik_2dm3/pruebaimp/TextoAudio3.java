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

public class TextoAudio3 extends AppCompatActivity {

    String texto3;
    TextView mostrar3;
    String[] pruebatexto3;
    String fuera3="";
    MediaPlayer audio3;
    TextView siguiente3;
    boolean boton_mostrar3;
    int REQ_JUEGO3 = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        if (Build.VERSION.SDK_INT > 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        setContentView(R.layout.activity_texto_audio3);
        siguiente3=findViewById(R.id.txtSig3);
        //siguiente5.setVisibility(View.INVISIBLE);
        //Reproducimos el audio
        audio3 = MediaPlayer.create(TextoAudio3.this, R.raw.aldezaharra);
        audio3.start();
        if (audio3.getCurrentPosition()==28000){
            audio3.stop();
        }
        //Sacamos el texto palabra a palabra
        texto3 = getResources().getString(R.string.gunea3_1);
        //Separamos las palabras
        pruebatexto3=texto3.split(" ");
        mostrar3 = findViewById(R.id.txtHistoria3);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ejecutar_hilo(mostrar3, fuera3, pruebatexto3, boton_mostrar3, 950);
            }
        }, 4000);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                audio3.stop();
            }
        }, 21300);
        siguiente3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent juego = new Intent(TextoAudio3.this, TextoAudio1.class);
                if (audio3.isPlaying()){
                    audio3.stop();
                }
                startActivityForResult(juego, REQ_JUEGO3);
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
