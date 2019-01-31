package com.example.ik_2dm3.maps2;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity_ini extends AppCompatActivity {

    private Button btnRutaPredef, btnRutaLibre;
    private int contador=0;
    private boolean geolocalizacion=true;
    private ImageView geo, fondo, logoDE, mapaDurango, tLogo, txLogo;
    private MediaPlayer mp;
    Dialog dialog;
    BroadcastReceiver br = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)){
                Log.d("mytag", "A");
            } else if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)){
                mp.stop();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        if (Build.VERSION.SDK_INT > 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        dialog=new Dialog(this);
        setContentView(R.layout.activity_main_ini);
        registerReceiver(br, new IntentFilter(Intent.ACTION_SCREEN_ON));
        registerReceiver(br, new IntentFilter(Intent.ACTION_SCREEN_OFF));
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


                Basededatos MDB = new Basededatos(getApplicationContext());
                Integer pasado = MDB.recuperarpasado();

                Log.d("mytag", "aaa " + pasado);

                if (pasado > 1) {
                    TextView tvCerrar;
                    Button btnbai, btnez;

                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.setContentView(R.layout.popup_ini);

                    tvCerrar=(TextView)dialog.findViewById(R.id.txtCerrar);
                    btnbai=(Button)dialog.findViewById(R.id.btnbai);
                    btnez=(Button)dialog.findViewById(R.id.btnez);

                    tvCerrar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });

                    btnbai.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {


                            if (mp.isPlaying()) {
                                mp.stop();
                            }
                            Intent intent = new Intent(getBaseContext(), MainActivity.class);
                            startActivity(intent);
                            dialog.dismiss();
                        }
                    });

                    btnez.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            try {
                                Basededatos MDB = new Basededatos(getApplicationContext());
                                MDB.borrarbasedatos(getApplicationContext());


                                if (mp.isPlaying()) {
                                    mp.stop();
                                }
                                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                                startActivity(intent);
                                dialog.dismiss();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                    });


                    dialog.show();

                } else {

                    if (mp.isPlaying()) {
                        mp.stop();
                    }
                    Intent intent = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });


    }
}
