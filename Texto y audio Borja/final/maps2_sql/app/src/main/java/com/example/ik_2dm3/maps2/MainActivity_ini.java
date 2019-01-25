package com.example.ik_2dm3.maps2;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static android.os.Environment.getExternalStorageDirectory;

public class MainActivity_ini extends AppCompatActivity {

    private Button btnRutaPredef, btnRutaLibre;
    private int contador=0;
    private boolean geolocalizacion=true;
    private ImageView geo, fondo, logoDE, mapaDurango, tLogo, txLogo;
    private MediaPlayer mp;
    Dialog dialog;
    Dialog dialog2;
    String nombreGrupo="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        final SharedPreferences.Editor myEditor = myPreferences.edit();
        myEditor.putBoolean("geolocalizacion", geolocalizacion);
        myEditor.putBoolean("punto0", true);
        myEditor.commit();

        if (Build.VERSION.SDK_INT > 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        dialog=new Dialog(this);
        dialog2=new Dialog(this);
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
                SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor myEditor = myPreferences.edit();
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

                myEditor.putBoolean("geolocalizacion", geolocalizacion);
                myEditor.commit();
            }
        });
        btnRutaLibre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                Basededatos MDB = new Basededatos(getApplicationContext());

                MDB.borrarbasedatos(getApplicationContext());
                MDB.campiarposicion();

                if (mp.isPlaying()) {
                    mp.stop();
                }
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                //intent.putExtra("geolocalizacion", geolocalizacion);
                startActivity(intent);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
         });

        btnRutaPredef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Basededatos MDB = new Basededatos(getApplicationContext());
                Integer pasado = MDB.recuperarpasado();

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
                            myEditor.putBoolean("punto0", false);
                            myEditor.commit();
                            //intent.putExtra("geolocalizacion", geolocalizacion);
                            startActivity(intent);
                            dialog.dismiss();
                        }
                    });

                    btnez.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Button Aurrera;
                            final EditText input;
                            dialog2.setContentView(R.layout.popup_imagen);
                            input = dialog2.findViewById(R.id.editText);
                            //dialog2.setCancelable(false);
                            //dialog2.setCanceledOnTouchOutside(false);
                            Aurrera = dialog2.findViewById(R.id.btnAurrera);
                            Aurrera.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    nombreGrupo=input.getText().toString();
                                    Log.d("mytag","input: "+nombreGrupo);
                                    File dir= new File(getExternalStorageDirectory()+"/Pictures",nombreGrupo);
                                    Log.d("mytag","el directorio: "+dir.toString());

                                    if (nombreGrupo.equals("")){
                                        Toast.makeText(getBaseContext(),"Mesedez idatzi zerbait!", Toast.LENGTH_SHORT).show();
                                    }else if (dir.exists()){
                                        Toast.makeText(getBaseContext(),"Izen hori ahutatuta dago, aukeratu beste bat!", Toast.LENGTH_SHORT).show();
                                    }else{
                                        dir.mkdir();
                                        dialog2.dismiss();

                                        try {
                                            Basededatos MDB = new Basededatos(getApplicationContext());
                                            MDB.borrarbasedatos(getApplicationContext());

                                            if (mp.isPlaying()) {
                                                mp.stop();
                                            }
                                            Intent intent = new Intent(getBaseContext(), MainActivity.class);
                                            //intent.putExtra("geolocalizacion", geolocalizacion);
                                            startActivity(intent);
                                            dialog.dismiss();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                }
                            });
                            dialog2.show();

                           /* try {
                                Basededatos MDB = new Basededatos(getApplicationContext());
                                MDB.borrarbasedatos(getApplicationContext());


                                if (mp.isPlaying()) {
                                    mp.stop();
                                }
                                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                                //intent.putExtra("geolocalizacion", geolocalizacion);
                                startActivity(intent);
                                dialog.dismiss();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }*/

                        }
                    });


                    dialog.show();




                } else {
                   // dialog2=new Dialog(context);
                    Button Aurrera;
                    final EditText input;
                    dialog2.setContentView(R.layout.popup_imagen);
                    input = dialog2.findViewById(R.id.editText);
                    //dialog2.setCancelable(false);
                    //dialog2.setCanceledOnTouchOutside(false);
                    Aurrera = dialog2.findViewById(R.id.btnAurrera);
                    Aurrera.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            nombreGrupo=input.getText().toString();
                            Log.d("mytag","input: "+nombreGrupo);
                            File dir= new File(getExternalStorageDirectory()+"/Pictures",nombreGrupo);
                            Log.d("mytag","el directorio: "+dir.toString());

                            if (nombreGrupo.equals("")){
                                Toast.makeText(getBaseContext(),"Mesedez idatzi zerbait!", Toast.LENGTH_SHORT).show();
                            }else if (dir.exists()){
                                Toast.makeText(getBaseContext(),"Izen hori ahutatuta dago, aukeratu beste bat!", Toast.LENGTH_SHORT).show();
                            }else{
                                dir.mkdir();
                                dialog2.dismiss();
                                try {
                                    if (mp.isPlaying()) {
                                        mp.stop();
                                    }

                                    MDB.borrarbasedatos(getApplicationContext());

                                    Intent intent = new Intent(getBaseContext(), MainActivity.class);
                                    //intent.putExtra("geolocalizacion", geolocalizacion);
                                    startActivity(intent);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                        }
                    });
                    dialog2.show();

                }
            }
        });


    }
}
