package com.example.ik_2dm3.maps2;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;

import java.util.ArrayList;

public class pantallacarga extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantallacarga);
        getSupportActionBar().hide();

        //Esconder barra de estado
        if (Build.VERSION.SDK_INT > 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            //getWindow().addFlags( WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

       /* Bundle extras = getIntent().getExtras();
        Integer index = extras.getInt("index");

        Basededatos MDB = new Basededatos(getApplicationContext());
        ArrayList<Posiciones> posicion =  MDB.recuperarposicionesuno(index);

        String activity = "com.example.ik_2dm3.maps2."+posicion.get(0).getJuegonombre();

        Intent intent= null;
        try {
            intent = new Intent(pantallacarga.this, Class.forName(activity));*/
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                  // finish();
                    Intent intent= new Intent(getBaseContext(),MainActivity.class);
                    // intent.putExtra("index", parseInt(returnValue));
                    startActivity(intent);
                }
            }, 1600);

      /*  } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/

        /*
        Intent cartasI = new Intent(pantallacarga.this, MainActivity_juegoMemoria.class);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 1600);*/
    }
}
