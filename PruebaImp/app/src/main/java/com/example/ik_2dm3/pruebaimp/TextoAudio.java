package com.example.ik_2dm3.pruebaimp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class TextoAudio extends AppCompatActivity {

    String texto5;
    TextView mostrar5;
    String[] pruebatexto5;
    MediaPlayer audio5;
    TextView siguiente5;
    boolean boton_mostrar5;
    int REQ_JUEGO = 1;
    int dialogos = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        if (Build.VERSION.SDK_INT > 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        setContentView(R.layout.activity_texto_audio);
        siguiente5=findViewById(R.id.txtSig);
        //siguiente5.setVisibility(View.INVISIBLE);
        //Reproducimos el audio
        audio5 = MediaPlayer.create(TextoAudio.this, R.raw.ezkurdi);
        audio5.start();
        //Sacamos el texto palabra a palabra
        texto5 = getResources().getString(R.string.gunea5);
        //Separamos las palabras
        pruebatexto5=texto5.split(" ");
        mostrar5 = findViewById(R.id.txtHistoria);
        ejecutar_hilo(mostrar5, pruebatexto5, boton_mostrar5, 600, dialogos);
        siguiente5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent juego = new Intent(TextoAudio.this, juegoAdivina.class);
                if (audio5.isPlaying()){
                    audio5.stop();
                }
                startActivityForResult(juego, REQ_JUEGO);
            }
        });
    }

    public void ejecutar_hilo(TextView texto_pantalla, String[] palabras, boolean terminar, int tiempo, int numdialogos) {
        hilos2 hilo = new hilos2();
        hilo.palabras2_1 = palabras;
        hilo.txtview2 = texto_pantalla;
        hilo.finalizado2 = terminar;
        hilo.milisegundos2 = tiempo;
        hilo.dialogos = numdialogos;
        hilo.start();
    }


}
