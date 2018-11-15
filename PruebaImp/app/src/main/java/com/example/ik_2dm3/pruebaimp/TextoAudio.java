package com.example.ik_2dm3.pruebaimp;

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

    String texto;
    TextView mostrar;
    String[] pruebatexto1;
    String fuera="";
    MediaPlayer audio;
    TextView siguiente;
    boolean boton_mostrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        if (Build.VERSION.SDK_INT > 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        setContentView(R.layout.activity_texto_audio);
        siguiente=findViewById(R.id.txtSig);
        siguiente.setVisibility(View.INVISIBLE);
        //Reproducimos el audio
        audio = MediaPlayer.create(TextoAudio.this, R.raw.ezkurdi);
        audio.start();
        //Sacamos el texto palabra a palabra
        texto = getResources().getString(R.string.gunea5);
        pruebatexto1=texto.split(" ");
        mostrar = findViewById(R.id.txtHistoria);
        ejecutar_hilo(mostrar, fuera, pruebatexto1, boton_mostrar);
        if (boton_mostrar == false){
            siguiente.setVisibility(View.VISIBLE);
        }
    }

    public void ejecutar_hilo(TextView texto_pantalla, String sacar, String[] palabras, boolean terminar) {
        hilos hilo = new hilos();
        hilo.palabras = palabras;
        hilo.sacar = sacar;
        hilo.txtview = texto_pantalla;
        hilo.finalizado = terminar;
        hilo.start();
    }
}
