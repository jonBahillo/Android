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
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class TextoAudio4 extends AppCompatActivity {

    String texto4_1;
    String texto4_2;
    TextView mostrar4;
    String[] pruebatexto4_1;
    String[] pruebatexto4_2;
    MediaPlayer audio4;
    TextView siguiente4;
    boolean boton_mostrar4;
    int REQ_JUEGO4 = 1;
    ImageView cambiartexto;
    boolean primero = false;
    int dialogos = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        if (Build.VERSION.SDK_INT > 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        setContentView(R.layout.activity_texto_audio4);
        siguiente4=findViewById(R.id.txtSig4);
        cambiartexto = findViewById(R.id.imgCambiartexto);
        siguiente4.setVisibility(View.INVISIBLE);
        cambiartexto.setVisibility(View.INVISIBLE);
        //Reproducimos el audio
        audio4 = MediaPlayer.create(TextoAudio4.this, R.raw.andramari);
        audio4.start();
        if (audio4.getCurrentPosition()==28000){
            audio4.stop(); //pausa
        }
        //Sacamos el texto palabra a palabra
        texto4_1 = getResources().getString(R.string.gunea4_1_1);
        //Separamos las palabras
        pruebatexto4_1=texto4_1.split(" ");
        texto4_2 = getResources().getString(R.string.gunea4_1_2);
        //Separamos las palabras
        pruebatexto4_2=texto4_2.split(" ");
        mostrar4 = findViewById(R.id.txtHistoria4);
        ejecutar_hilo(mostrar4, pruebatexto4_1, pruebatexto4_2, 565, dialogos);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                audio4.pause();
                siguiente4.setVisibility(View.VISIBLE);
                cambiartexto.setVisibility(View.VISIBLE);
                //Log.d("mytag", "Hola: " + hilop.texto1);
            }
        }, 50100);
        siguiente4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent juego = new Intent(TextoAudio4.this, TextoAudio1.class);
                if (audio4.isPlaying()){
                    audio4.stop();
                }

                startActivityForResult(juego, REQ_JUEGO4);
            }
        });
        cambiartexto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (primero){
                    mostrar4.setText(texto4_2);
                    cambiartexto.setImageResource(R.drawable.flecha_iz);
                    primero=false;
                }else{
                    mostrar4.setText(texto4_1);
                    cambiartexto.setImageResource(R.drawable.flecha_der);
                    primero=true;
                }
            }
        });
    }

    public void ejecutar_hilo(TextView texto_pantalla, String[] palabras1, String[] palabras2, int tiempo, int numdialogos) {
        hilos2 hilo = new hilos2();
        hilo.txtview2 = texto_pantalla;
        hilo.palabras2_1 = palabras1;
        hilo.palabras2_2 = palabras2;
        hilo.milisegundos2 = tiempo;
        hilo.dialogos = numdialogos;
        hilo.start();
    }
}
