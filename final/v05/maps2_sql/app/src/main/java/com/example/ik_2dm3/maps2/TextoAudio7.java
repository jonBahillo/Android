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

public class TextoAudio7 extends AppCompatActivity {

    String texto7_1;
    String texto7_2;
    TextView mostrar7;
    String[] pruebatexto7_1;
    String[] pruebatexto7_2;
    MediaPlayer audio7;
    TextView siguiente7;
    int REQ_JUEGO7 = 1;
    ImageView cambiartexto7;
    boolean primero7 = false;
    int dialogos7 = 2;
    ImageView imagensapo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        if (Build.VERSION.SDK_INT > 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        setContentView(R.layout.activity_texto_audio7);
        imagensapo=findViewById(R.id.imageView7);
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
                imagensapo.setImageResource(R.drawable.goienkale);
            }
        }, 22100);
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
                ejecutar_hilo(mostrar7, pruebatexto7_1, pruebatexto7_2, 680, dialogos7);
            }
        }, 3100);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                audio7.pause();
                siguiente7.setVisibility(View.VISIBLE);
                cambiartexto7.setVisibility(View.VISIBLE);
                //Log.d("mytag", "Hola: " + hilop.texto1);
            }
        }, 48100);
        siguiente7.setOnClickListener(new View.OnClickListener() {
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
                    intent = new Intent(TextoAudio7.this, Class.forName(activity));
                    if (audio7.isPlaying()){
                        audio7.stop();
                    }
                    intent.putExtra("index", index);
                    startActivityForResult(intent, REQ_JUEGO7);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
               /* Intent juego = new Intent(TextoAudio7.this, TextoAudio1.class);
                if (audio7.isPlaying()){
                    audio7.stop();
                }
                startActivityForResult(juego, REQ_JUEGO7);*/
            }
        });
        cambiartexto7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (primero7){
                    mostrar7.setText(texto7_2);
                    cambiartexto7.setImageResource(R.drawable.flecha_iz);
                    imagensapo.setImageResource(R.drawable.goienkale);
                    primero7=false;
                }else{
                    mostrar7.setText(texto7_1);
                    cambiartexto7.setImageResource(R.drawable.flecha_der);
                    imagensapo.setImageResource(R.drawable.kalebarria);
                    primero7=true;
                }
            }
        });
    }
    public void onActivityResult (int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_JUEGO7) {
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
    public void ejecutar_hilo(TextView texto_pantalla, String[] palabras1, String[] palabras2, int tiempo, int numdialogos) {
        hilos2 hilo = new hilos2();
        hilo.palabras2_1 = palabras1;
        hilo.palabras2_2 = palabras2;
        hilo.txtview2 = texto_pantalla;
        hilo.milisegundos2 = tiempo;
        hilo.dialogos = numdialogos;
        hilo.start();
    }
}
