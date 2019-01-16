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

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class TextoAudio0 extends AppCompatActivity {

    String texto0_1;
    String texto0_2;
    TextView mostrar0;
    String[] pruebatexto0_1;
    String[] pruebatexto0_2;
    MediaPlayer audio0;
    TextView siguiente0;
    final int REQ_JUEGO0 = 1;
    ImageView cambiartexto0;
    boolean primero0 = false;
    int dialogos0 = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        if (Build.VERSION.SDK_INT > 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        setContentView(R.layout.activity_texto_audio0);
        siguiente0=findViewById(R.id.txtSig0);
        cambiartexto0 = findViewById(R.id.imgCambiartexto0);
        siguiente0.setVisibility(View.INVISIBLE);
        cambiartexto0.setVisibility(View.INVISIBLE);
        //Reproducimos el audio
        audio0 = MediaPlayer.create(TextoAudio0.this, R.raw.sarrera);
        audio0.start();
        //Sacamos el texto palabra a palabra
        texto0_1 = getResources().getString(R.string.gunea0_1);
        texto0_2 = getResources().getString(R.string.gunea0_2);
        //Separamos las palabras
        pruebatexto0_1=texto0_1.split(" ");
        pruebatexto0_2=texto0_2.split(" ");
        mostrar0 = findViewById(R.id.txtHistoria0);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                audio0.pause();
                siguiente0.setVisibility(View.VISIBLE);
                cambiartexto0.setVisibility(View.VISIBLE);
            }
        }, 71500);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ejecutar_hilo(mostrar0, pruebatexto0_1, pruebatexto0_2, 850, dialogos0);
            }
        }, 2900);
        cambiartexto0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (primero0){
                    mostrar0.setText(texto0_1);
                    cambiartexto0.setImageResource(R.drawable.flecha_iz);
                    primero0=false;
                }else{
                    mostrar0.setText(texto0_2);
                    cambiartexto0.setImageResource(R.drawable.flecha_der);
                    primero0=true;
                }
            }
        });
        siguiente0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                    startActivityForResult(intent, REQ_JUEGO0);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                /*Intent juego = new Intent(TextoAudio0.this, pantallacarga.class);
                startActivityForResult(juego, REQ_JUEGO0);*/
            }
        });
    }

    public void onActivityResult (int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_JUEGO0){
            //dialog.dismiss();
            String  returnValue = data.getStringExtra("index");

            Basededatos MDB = new Basededatos(getApplicationContext());

            MDB.campiarposicion(parseInt(returnValue));

            Intent intent= new Intent(getBaseContext(),pantallacarga.class);
            // intent.putExtra("index", parseInt(returnValue));
            startActivity(intent);
            finish();

        }
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
