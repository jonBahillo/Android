package com.example.ik_2dm3.pruebaimp;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class TextoAudio extends AppCompatActivity {

    String texto;
    TextView mostrar;
    ArrayList<String> textoprueba = new ArrayList<>();
    String[] pruebatexto1;
    String fuera="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        if (Build.VERSION.SDK_INT > 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            //getWindow().addFlags( WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        setContentView(R.layout.activity_texto_audio);
        texto = getResources().getString(R.string.gunea5);
        pruebatexto1=texto.split(" ");
        mostrar = findViewById(R.id.txtHistoria);
        for (int j=0; j<pruebatexto1.length; j++) {
            fuera = fuera + " " + pruebatexto1[j];
            Log.d("mytag", "Prueba: " + fuera);
            mostrar.setText(fuera);
        }
    }

}
