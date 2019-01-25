package com.example.ik_2dm3.maps2;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class juegoAdivina extends AppCompatActivity {

    public TextView tv;
    public Button aurrera;
    public EditText erantzuna;
    public Dialog dialogo;
    private MediaPlayer audioJuego;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        if (Build.VERSION.SDK_INT > 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        setContentView(R.layout.activity_juego_adivina);
        dialogo = new Dialog(this);
        audioJuego = MediaPlayer.create(juegoAdivina.this, R.raw.aldezaharra);
        //audio3.setVolume(200,200);
        audioJuego.seekTo(48500);
        audioJuego.start();
        tv= findViewById(R.id.adivinanza);
        aurrera= findViewById(R.id.aurrerabtn2);
        erantzuna= findViewById(R.id.erantzuna2);
        aurrera.setVisibility(View.INVISIBLE);
        erantzuna.setVisibility(View.INVISIBLE);

        final Handler handler = new Handler();
        aurrera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (erantzuna.getText().toString().equals("goienkale")){
                    popup(null);

                }else{
                    Toast.makeText(getBaseContext(),"Erantzun okerra",Toast.LENGTH_SHORT).show();
                    erantzuna.setText("");
                }
            }
        });
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                aurrera.setVisibility(View.VISIBLE);
                erantzuna.setVisibility(View.VISIBLE);
                audioJuego.stop();
            }
        }, 16500);
    }

    public void popup (View v){
        Button Errepikatu;
        Button Aurrera;
        //dialogo.setContentView(R.layout.popup_juegomemoria);
        dialogo.setContentView(R.layout.popup_juego);
        Errepikatu = dialogo.findViewById(R.id.btnErrepikatu);
        Aurrera = dialogo.findViewById(R.id.btnAurrera);
        dialogo.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Errepikatu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                erantzuna.setText("");
                dialogo.dismiss();
                audioJuego.seekTo(48500);
                audioJuego.start();
            }
        });
        Aurrera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle extras = getIntent().getExtras();
                Integer index = extras.getInt("index");
                Intent salir = new Intent(juegoAdivina.this, TextoAudio.class);
                salir.putExtra("index", index.toString());
                setResult(RESULT_OK, salir);
                dialogo.dismiss();
                finish();
            }
        });
        dialogo.show();

    }

    @Override
    public void onDestroy() {

        super.onDestroy();
       /* dialogo.dismiss();


        audioJuego.stop();
        audioJuego.release();*/


    }

}
