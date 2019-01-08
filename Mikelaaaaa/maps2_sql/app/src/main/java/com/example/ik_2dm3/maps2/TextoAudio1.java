package com.example.ik_2dm3.maps2;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class TextoAudio1 extends AppCompatActivity {

    String texto1;
    TextView mostrar1;
    String[] pruebatexto1;
    MediaPlayer audio1;
    TextView siguiente1;
    boolean boton_mostrar1;
    final int REQ_JUEGO1 = 1;
    int dialogos1 = 1;
    boolean primero = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        if (Build.VERSION.SDK_INT > 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        setContentView(R.layout.activity_texto_audio1);
        siguiente1=findViewById(R.id.txtSig1);
        siguiente1.setVisibility(View.INVISIBLE);
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
                ejecutar_hilo(mostrar1, pruebatexto1, boton_mostrar1, 850, dialogos1);
            }
        }, 4500);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                siguiente1.setVisibility(View.VISIBLE);
            }
        }, 16800);//67100);
        siguiente1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (primero){
                    primero=false;
                    if (audio1.isPlaying()){
                        audio1.stop();
                    }
                    Basededatos MDB = new Basededatos(getApplicationContext());

                    Bundle extras = getIntent().getExtras();

                    Integer index = extras.getInt("index");

                    ArrayList<Posiciones> posicion =  MDB.recuperarposicionesuno(index);

                    String activity = "com.example.ik_2dm3.maps2."+posicion.get(0).getJuegonombre();
                    // String  activity = "com.example.ik_2dm3.maps2.TextoAudio0";
                    Intent intent= null;
                    try {
                        intent = new Intent(getBaseContext(), Class.forName(activity));
                        intent.putExtra("index", index);
                        startActivityForResult(intent, REQ_JUEGO1);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    /*Intent juego = new Intent(TextoAudio1.this, pantallacarga2.class);
                    startActivityForResult(juego, REQ_JUEGO1);*/
                }else{
                    primero=false;
                    Intent juego = new Intent(TextoAudio1.this, pantallacarga.class);
                    startActivityForResult(juego, REQ_JUEGO1);
                }
            }
        });
    }

    public void ejecutar_hilo(TextView texto_pantalla, String[] palabras1, boolean terminar, int tiempo, int numdialogos) {
        hilos2 hilo = new hilos2();
        hilo.txtview2 = texto_pantalla;
        hilo.palabras2_1 = palabras1;
        hilo.finalizado2 = terminar;
        hilo.milisegundos2 = tiempo;
        hilo.dialogos = numdialogos;
        hilo.start();
    }

    public void onActivityResult (int requestCode, int resultCode, Intent Data){
        siguiente1.setVisibility(View.INVISIBLE);
        switch (requestCode){
            case REQ_JUEGO1:
                if (resultCode==resultCode){
                    /*mostrar1.setText("");
                    //Reproducimos el audio
                    audio1 = MediaPlayer.create(TextoAudio1.this, R.raw.santaana2);
                    //audio1.setVolume(200,200);
                    audio1.start();
                    //Sacamos el texto palabra a palabra
                    texto1 = getResources().getString(R.string.gunea1_2);
                    pruebatexto1=texto1.split(" ");
                    mostrar1 = findViewById(R.id.txtHistoria1);
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ejecutar_hilo(mostrar1, pruebatexto1, boton_mostrar1, 850, dialogos1);
                        }
                    }, 1500);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            siguiente1.setVisibility(View.VISIBLE);
                        }
                    }, 7800);*/
                    String  returnValue = Data.getStringExtra("index");

                    Basededatos MDB = new Basededatos(getApplicationContext());

                    MDB.campiarposicion(parseInt(returnValue));

                    /*Intent intent= new Intent(getBaseContext(),pantallacarga.class);*/
                    Intent intent= new Intent(getBaseContext(), MainActivity_camara.class);
                    // intent.putExtra("index", parseInt(returnValue));
                    startActivity(intent);
                    finish();

                }
                break;
        }
    }

}
