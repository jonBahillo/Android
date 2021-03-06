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

public class TextoAudio7 extends AppCompatActivity {

    String texto7_1;
    String texto7_2;
    TextView mostrar7;
    String[] pruebatexto7_1;
    String[] pruebatexto7_2;
    String fuera7="";
    MediaPlayer audio7;
    TextView siguiente7;
    boolean boton_mostrar7;
    int REQ_JUEGO7 = 1;
    ImageView cambiartexto7;
    boolean primero7 = false;
    int dialogos7 = 2;
    ImageView imagensapo,imagensapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        if (Build.VERSION.SDK_INT > 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        setContentView(R.layout.activity_texto_audio7);
        imagensapo=findViewById(R.id.imageView7);
        imagensapa=findViewById(R.id.imageView8);
        imagensapa.setVisibility(View.INVISIBLE);
        siguiente7=findViewById(R.id.txtSig7);
        cambiartexto7 = findViewById(R.id.imgCambiartexto7);
        siguiente7.setVisibility(View.INVISIBLE);
        cambiartexto7.setVisibility(View.INVISIBLE);
        final Handler handler = new Handler();
        //Reproducimos el audio
        audio7 = MediaPlayer.create(TextoAudio7.this, R.raw.amaiera1);
        audio7.start();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                audio7.release();
                audio7 = MediaPlayer.create(TextoAudio7.this, R.raw.amaiera2);
                audio7.start();
                imagensapo.setVisibility(View.INVISIBLE);
                imagensapa.setVisibility(View.VISIBLE);

            }
<<<<<<< Updated upstream
        }, 25700);
=======
        }, 23100);
>>>>>>> Stashed changes
        //Sacamos el texto palabra a palabra
        texto7_1 = getResources().getString(R.string.gunea7_1);
        //Separamos las palabras
        pruebatexto7_1=texto7_1.split(" ");
        texto7_2 = getResources().getString(R.string.gunea7_2);
        //Separamos las palabras
        pruebatexto7_2=texto7_2.split(" ");
        mostrar7 = findViewById(R.id.txtHistoria7);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ejecutar_hilo(mostrar7, pruebatexto7_1, pruebatexto7_2, boton_mostrar7, 710, dialogos7);
                //Log.d("mytag", "Hola: " + pruebatexto7_1[pruebatexto7_1.length-1]);
            }
        }, 3500);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                audio7.stop();
                siguiente7.setVisibility(View.VISIBLE);
                cambiartexto7.setVisibility(View.VISIBLE);
                //Log.d("mytag", "Hola: " + hilop.texto1);
            }
        }, 48100);
        siguiente7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent juego = new Intent(TextoAudio7.this, TextoAudio1.class);
                if (audio7.isPlaying()){
                    audio7.stop();
                }
                startActivityForResult(juego, REQ_JUEGO7);
            }
        });
        cambiartexto7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (primero7){
                    mostrar7.setText(texto7_2);
                    cambiartexto7.setImageResource(R.drawable.flecha_iz);
                    imagensapo.setImageResource(R.drawable.goienkale);
                    imagensapa.setVisibility(View.INVISIBLE);
                    imagensapo.setVisibility(View.VISIBLE);

                    primero7=false;

                }else{
                    mostrar7.setText(texto7_1);
                    cambiartexto7.setImageResource(R.drawable.flecha_der);
                    imagensapo.setImageResource(R.drawable.kalebarria);
                    imagensapo.setVisibility(View.INVISIBLE);
                    imagensapa.setVisibility(View.VISIBLE);
                    primero7=true;
                }
            }
        });
    }

    public void ejecutar_hilo(TextView texto_pantalla, String[] palabras1, String[] palabras2, boolean terminar, int tiempo, int numdialogos) {
        hilos2 hilo = new hilos2();
        hilo.palabras2_1 = palabras1;
        hilo.palabras2_2 = palabras2;
        hilo.txtview2 = texto_pantalla;
        hilo.finalizado2 = terminar;
        hilo.milisegundos2 = tiempo;
        hilo.dialogos = numdialogos;
        hilo.start();
    }
}
