package com.example.ik_2dm3.maps2;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class TextoAudio5 extends AppCompatActivity {

    String texto5;
    TextView mostrar5;
    String[] pruebatexto5;
    MediaPlayer audio5;
    TextView siguiente5;
    int REQ_JUEGO5 = 1;
    int dialogos = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        if (Build.VERSION.SDK_INT > 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        setContentView(R.layout.activity_texto_audio5);
        siguiente5=findViewById(R.id.txtSig);
        //siguiente5.setVisibility(View.INVISIBLE);
        //Reproducimos el audio
        audio5 = MediaPlayer.create(TextoAudio5.this, R.raw.ezkurdi);
        audio5.start();
        //Sacamos el texto palabra a palabra
        texto5 = getResources().getString(R.string.gunea5);
        //Separamos las palabras
        pruebatexto5=texto5.split(" ");
        mostrar5 = findViewById(R.id.txtHistoria);
        ejecutar_hilo(mostrar5, pruebatexto5, 600, dialogos);
        siguiente5.setOnClickListener(new View.OnClickListener() {
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
                    intent = new Intent(TextoAudio5.this, Class.forName(activity));

                    if (audio5.isPlaying()){
                        audio5.stop();
                    }

                    intent.putExtra("index", index);
                    startActivityForResult(intent, REQ_JUEGO5);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                /*Intent juego = new Intent(TextoAudio5.this, juegoAdivina.class);
                if (audio5.isPlaying()){
                    audio5.stop();
                }
                startActivityForResult(juego, REQ_JUEGO);*/
            }
        });
    }
    public void onActivityResult (int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_JUEGO5) {
            //dialog.dismiss();
            String returnValue = data.getStringExtra("index");

            Basededatos MDB = new Basededatos(getApplicationContext());

            MDB.campiarposicion(parseInt(returnValue));

            Intent intent = new Intent(getBaseContext(), pantallacarga.class);
            // intent.putExtra("index", parseInt(returnValue));
            startActivity(intent);
            finish();

        }
    }
            public void ejecutar_hilo(TextView texto_pantalla, String[] palabras, int tiempo, int numdialogos) {
        hilos2 hilo = new hilos2();
        hilo.palabras2_1 = palabras;
        hilo.txtview2 = texto_pantalla;
        hilo.milisegundos2 = tiempo;
        hilo.dialogos = numdialogos;
        hilo.start();
    }


}
