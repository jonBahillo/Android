package com.example.ik_2dm3.maps2;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class TextoAudio3 extends AppCompatActivity {

    String texto3;
    TextView mostrar3;
    String[] pruebatexto3;
    MediaPlayer audio3;
    TextView siguiente3;
    boolean segundo = true;
    final int REQ_JUEGO3 = 1;
    final int REQ_JUEGO3_2 = 2;
    int dialogos3 = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        if (Build.VERSION.SDK_INT > 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        setContentView(R.layout.activity_texto_audio3);
        siguiente3=findViewById(R.id.txtSig3);
        siguiente3.setVisibility(View.INVISIBLE);
        //Reproducimos el audio
        audio3 = MediaPlayer.create(TextoAudio3.this, R.raw.aldezaharra);
        audio3.start();
        //Sacamos el texto palabra a palabra
        texto3 = getResources().getString(R.string.gunea3_1);
        //Separamos las palabras
        pruebatexto3=texto3.split(" ");
        mostrar3 = findViewById(R.id.txtHistoria3);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ejecutar_hilo(mostrar3, pruebatexto3, 935, dialogos3);
            }
        }, 4000);

        siguiente3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (segundo){
                    Intent juego = new Intent(TextoAudio3.this, pantallacarga.class);
                    startActivityForResult(juego, REQ_JUEGO3);
                    segundo = false;
                }else{
                    Intent juego = new Intent(TextoAudio3.this, pantallacarga.class);
                    startActivityForResult(juego, REQ_JUEGO3_2);
                }

            }
        });
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                audio3.stop();
            }
        }, 21000);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                siguiente3.setVisibility(View.VISIBLE);
            }
        }, 22000);
    }

    public void ejecutar_hilo(TextView texto_pantalla, String[] palabras, int tiempo, int numdialogos) {
        hilos2 hilo = new hilos2();
        hilo.txtview2 = texto_pantalla;
        hilo.palabras2_1 = palabras;
        hilo.milisegundos2 = tiempo;
        hilo.dialogos = numdialogos;
        hilo.start();
    }

    public void onActivityResult (int requestCode, int resultCode, Intent Data){
        switch (requestCode){
            case REQ_JUEGO3:
                if (resultCode==RESULT_OK){
                    siguiente3.setVisibility(View.INVISIBLE);
                    mostrar3.setText("");
                    //Reproducimos el audio
                    //audio3 = MediaPlayer.create(TextoAudio3.this, R.raw.aldezaharra);
                    //audio3.setVolume(200,200);
                    //audio3.seekTo(29700);
                    //La siguiente parte del audio es en el segundo 59 +-
                    audio3.start();
                    //Sacamos el texto palabra a palabra
                    texto3 = getResources().getString(R.string.gunea3_2);
                    pruebatexto3=texto3.split(" ");
                    mostrar3 = findViewById(R.id.txtHistoria3);
                    final Handler handler = new Handler();
                    ejecutar_hilo(mostrar3, pruebatexto3, 880, dialogos3);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            siguiente3.setVisibility(View.VISIBLE);
                        }
                    }, 19850);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            audio3.stop();
                        }
                    }, 19000);

                }
                break;
            case REQ_JUEGO3_2:
                if (resultCode==RESULT_OK){
                    ImageView imgsapo;
                    imgsapo = findViewById(R.id.imageView3);
                    imgsapo.setImageResource(R.drawable.goienkale);
                    siguiente3.setVisibility(View.INVISIBLE);
                    mostrar3.setText("");
                    //Reproducimos el audio
                    audio3 = MediaPlayer.create(TextoAudio3.this, R.raw.aldezaharra2);
                    //audio3.setVolume(200,200);
                    audio3.start();
                    //Sacamos el texto palabra a palabra
                    texto3 = getResources().getString(R.string.gunea3_3);
                    pruebatexto3=texto3.split(" ");
                    mostrar3 = findViewById(R.id.txtHistoria3);
                    final Handler handler = new Handler();
                    ejecutar_hilo(mostrar3, pruebatexto3, 575, dialogos3);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            siguiente3.setVisibility(View.VISIBLE);
                        }
                    }, 47200);
                }
                break;
        }
    }
}
