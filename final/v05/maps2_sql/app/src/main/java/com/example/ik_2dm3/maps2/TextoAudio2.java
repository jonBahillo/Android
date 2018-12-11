package com.example.ik_2dm3.maps2;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class TextoAudio2 extends AppCompatActivity {

    String texto2;
    TextView mostrar2;
    String[] pruebatexto2;
    MediaPlayer audio2;
    TextView siguiente2;
    boolean boton_mostrar2;
    boolean segundo = true;
    boolean finalizar = false;
    final int REQ_JUEGO2 = 1;
    final int REQ_JUEGO2_2 = 2;
    int dialogos2 = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        if (Build.VERSION.SDK_INT > 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        setContentView(R.layout.activity_texto_audio2);
        siguiente2=findViewById(R.id.txtSig2);
        siguiente2.setVisibility(View.INVISIBLE);
        //Reproducimos el audio
        audio2 = MediaPlayer.create(TextoAudio2.this, R.raw.kurutziagados);
        audio2.start();
        if (audio2.getCurrentPosition()==28000){
            audio2.pause();
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
                ejecutar_hilo(mostrar2, pruebatexto2, boton_mostrar2, 800, dialogos2);
            }
        }, 2500);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                siguiente2.setVisibility(View.VISIBLE);
            }
        }, 6800);//30000);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                audio2.stop();
            }
        }, 30300);
        siguiente2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (segundo){

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
                        startActivityForResult(intent, REQ_JUEGO2);

                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                    //audio2.pause();
                    /*Intent juego = new Intent(TextoAudio2.this, juegoAdivina.class);
                    startActivityForResult(juego, REQ_JUEGO2);*/
                    segundo = false;
                }else{
                    Basededatos MDB = new Basededatos(getApplicationContext());

                    Bundle extras = getIntent().getExtras();

                    Integer index = extras.getInt("index");

                    ArrayList<Posiciones> posicion =  MDB.recuperarposicionesuno(index);

                    String activity = "com.example.ik_2dm3.maps2."+posicion.get(0).getJuegonombre();
                    // String  activity = "com.example.ik_2dm3.maps2.TextoAudio0";
                    Intent intent= null;
                    try {
                        intent = new Intent(TextoAudio2.this, Class.forName(activity));

                        intent.putExtra("index", index);
                        startActivityForResult(intent, REQ_JUEGO2_2);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    /*Intent juego = new Intent(TextoAudio2.this, pantallacarga2.class);
                    startActivityForResult(juego, REQ_JUEGO2_2);*/
                }
                if(finalizar){
                    Intent juego = new Intent(TextoAudio2.this, pantallacarga.class);
                    startActivityForResult(juego, REQ_JUEGO2_2);
                }
            }
        });
    }

    public void ejecutar_hilo(TextView texto_pantalla, String[] palabras, boolean terminar, int tiempo, int numdialogos) {
        hilos2 hilo = new hilos2();
        hilo.txtview2 = texto_pantalla;
        hilo.palabras2_1 = palabras;
        hilo.finalizado2 = terminar;
        hilo.milisegundos2 = tiempo;
        hilo.dialogos = numdialogos;
        hilo.start();
    }

    public void onActivityResult (int requestCode, int resultCode, Intent Data){
        //audio2 = MediaPlayer.create(TextoAudio2.this, R.raw.kurutziaga);
        siguiente2.setVisibility(View.INVISIBLE);
        switch (requestCode){
            case REQ_JUEGO2:
                if (resultCode==resultCode){
                    /*mostrar2.setText("");
                    //Reproducimos el audio



                    //audio2 = MediaPlayer.create(TextoAudio2.this, R.raw.kurutziaga);

                    //audio2.setVolume(200,200);

                    //audio2.seekTo(49600);

                    //La siguiente parte del audio es en el segundo 49 +-
                    //Log.d("mytag", "Hola: " + audio2.getCurrentPosition());
                    if (audio2!= null) {
                        audio2.stop();
                    }
                    audio2.start();

                    //Sacamos el texto palabra a palabra
                    texto2 = getResources().getString(R.string.gunea2_2);
                    pruebatexto2=texto2.split(" ");
                    mostrar2 = findViewById(R.id.txtHistoria2);
                    final Handler handler = new Handler();
                    ejecutar_hilo(mostrar2, pruebatexto2, boton_mostrar2, 850, dialogos2);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            audio2.pause();
                        }
                    }, 10500);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            siguiente2.setVisibility(View.VISIBLE);
                        }
                    }, 10800);*/
                    String  returnValue = Data.getStringExtra("index");

                    Basededatos MDB = new Basededatos(getApplicationContext());

                    MDB.campiarposicion(parseInt(returnValue));

                    Intent intent= new Intent(getBaseContext(),pantallacarga.class);
                    // intent.putExtra("index", parseInt(returnValue));
                    startActivity(intent);
                    finish();
                }
                break;
            case REQ_JUEGO2_2:
                if (resultCode==resultCode){
                    siguiente2.setVisibility(View.INVISIBLE);
                    mostrar2.setText("");
                    //Reproducimos el audio
                    //audio3.setVolume(200,200);
                    audio2.start();
                    //Sacamos el texto palabra a palabra
                    texto2 = getResources().getString(R.string.gunea2_3);
                    pruebatexto2=texto2.split(" ");
                    mostrar2 = findViewById(R.id.txtHistoria2);
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ejecutar_hilo(mostrar2, pruebatexto2, boton_mostrar2, 720, dialogos2);
                        }
                    }, 7300);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            siguiente2.setVisibility(View.VISIBLE);
                        }
                    }, 22000);
                    finalizar = true;
                }
                break;
        }
    }

}
