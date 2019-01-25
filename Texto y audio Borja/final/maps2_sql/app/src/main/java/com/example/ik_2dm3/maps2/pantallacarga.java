package com.example.ik_2dm3.maps2;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

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
                    Bundle extras = getIntent().getExtras();
                    Integer index = extras.getInt("index");
                    Intent salir = new Intent(pantallacarga.this, MainActivity.class);
                    salir.putExtra("index", index.toString());
                    setResult(RESULT_OK, salir);
                    finish();
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
