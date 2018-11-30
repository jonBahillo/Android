package com.example.ik_2dm3.pruebaimp;

import android.app.Dialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class juegoHuecos extends AppCompatActivity {

    private TextView tv;
    private Button aurrera;
    private EditText erantzuna;
    private Dialog dialogo;
    private MediaPlayer audioJuego;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        if (Build.VERSION.SDK_INT > 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        setContentView(R.layout.activity_juego_huecos);
        dialogo = new Dialog(this);
        audioJuego = MediaPlayer.create(juegoHuecos.this, R.raw.aldezaharra);
        //audio3.setVolume(200,200);
        audioJuego.seekTo(19000);
        audioJuego.start();
        tv= findViewById(R.id.galdera);
        aurrera= findViewById(R.id.aurrerabtn);
        erantzuna= findViewById(R.id.erantzuna);
        aurrera.setVisibility(View.INVISIBLE);
        erantzuna.setVisibility(View.INVISIBLE);

        final Handler handler = new Handler();
        aurrera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (erantzuna.getText().toString().equals("kalebarria")){
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
        }, 10200);

    }
    public void popup (View v){
        Button Errepikatu;
        Button Aurrera;
        dialogo.setContentView(R.layout.popup);
        Errepikatu = dialogo.findViewById(R.id.btnErrepikatu);
        Aurrera = dialogo.findViewById(R.id.btnAurrera);
        Aurrera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Errepikatu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogo.dismiss();
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });
        dialogo.show();
    }
}
