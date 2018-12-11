package com.example.ik_2dm3.maps2;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity_ini extends AppCompatActivity {

    private Button btnRutaPredef, btnRutaLibre;
    private int contador=0;
    private boolean geolocalizacion=true;
    private ImageView geo, fondo, logoDE, mapaDurango, tLogo, txLogo;
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        if (Build.VERSION.SDK_INT > 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        setContentView(R.layout.activity_main_ini);

        btnRutaPredef=(Button) findViewById(R.id.btnRutaPredef);
        btnRutaLibre=(Button) findViewById(R.id.btnRutaLibre);
        geo=(ImageView) findViewById(R.id.imgOcultar);
        fondo=(ImageView) findViewById(R.id.imgLogoDE);
        logoDE=(ImageView) findViewById(R.id.imgOcultar);
        mapaDurango=(ImageView) findViewById(R.id.imgMapaDurango);
        tLogo=(ImageView) findViewById(R.id.imgTLogo);
        txLogo=(ImageView) findViewById(R.id.imgTX);

        mp = MediaPlayer.create(this, R.raw.menu);

        mp.start();

        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
               mp.start();
            }
        });

        geo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               contador++;
               if (contador == 5 && geolocalizacion){
                   Toast.makeText(getBaseContext(), "has desactivado la geolocalizacion",Toast.LENGTH_SHORT).show();
                   geolocalizacion=false;
                   contador=0;
               }else if (contador == 5 && !geolocalizacion){
                   Toast.makeText(getBaseContext(), "has activado la geolocalizacion",Toast.LENGTH_SHORT).show();
                   geolocalizacion=true;
                   contador=0;
               }
            }
        });
        btnRutaPredef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mp.isPlaying()) {
                    mp.stop();
                }
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });


    }
}
