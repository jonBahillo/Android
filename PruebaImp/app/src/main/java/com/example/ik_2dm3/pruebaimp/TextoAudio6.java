package com.example.ik_2dm3.pruebaimp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class TextoAudio6 extends AppCompatActivity {

    String texto6_1;
    String texto6_2;
    TextView mostrar6;
    String[] pruebatexto6_1;
    String[] pruebatexto6_2;
    String fuera6="";
    MediaPlayer audio6;
    TextView siguiente6;
    boolean boton_mostrar6;
    int REQ_JUEGO6 = 1;
    ImageView cambiartexto;
    boolean primero = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        if (Build.VERSION.SDK_INT > 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        setContentView(R.layout.activity_texto_audio6);
        siguiente6=findViewById(R.id.txtSig4);
        cambiartexto = findViewById(R.id.imgCambiartexto);
        siguiente6.setVisibility(View.INVISIBLE);
        cambiartexto.setVisibility(View.INVISIBLE);
        //Reproducimos el audio
        audio6 = MediaPlayer.create(TextoAudio6.this, R.raw.andramari);
        audio6.start();
        if (audio6.getCurrentPosition()==28000){
            audio6.stop(); //pausa
        }
        //Sacamos el texto palabra a palabra
        texto6_1 = getResources().getString(R.string.gunea6_1);
        //Separamos las palabras
        pruebatexto6_1=texto6_1.split(" ");
        texto6_2 = getResources().getString(R.string.gunea4_1_2);
        //Separamos las palabras
        pruebatexto6_2=texto6_2.split(" ");
        mostrar6 = findViewById(R.id.txtHistoria6);
        ejecutar_hilo(mostrar6, pruebatexto6_1, pruebatexto6_2, boton_mostrar6, 565);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                audio6.pause();
                siguiente6.setVisibility(View.VISIBLE);
                //cambiartexto.setVisibility(View.VISIBLE);
                //Log.d("mytag", "Hola: " + hilop.texto1);
            }
        }, 50100);
        siguiente6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent juego = new Intent(TextoAudio6.this, TextoAudio1.class);
                if (audio6.isPlaying()){
                    audio6.stop();
                }

                startActivityForResult(juego, REQ_JUEGO6);
            }
        });
        /*cambiartexto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (primero){
                    mostrar6.setText(texto6_2);
                    cambiartexto.setImageResource(R.drawable.flecha_iz);
                    primero=false;
                }else{
                    mostrar6.setText(texto6_1);
                    cambiartexto.setImageResource(R.drawable.flecha_der);
                    primero=true;
                }
            }
        });*/
    }

    public void ejecutar_hilo(TextView texto_pantalla, String[] palabras1, String[] palabras2, boolean terminar, int tiempo) {
        hilos2 hilo = new hilos2();
        hilo.palabras2_1 = palabras1;
        hilo.palabras2_2 = palabras2;
        hilo.txtview2 = texto_pantalla;
        hilo.finalizado2 = terminar;
        hilo.milisegundos2 = tiempo;
        hilo.start();
    }
}
