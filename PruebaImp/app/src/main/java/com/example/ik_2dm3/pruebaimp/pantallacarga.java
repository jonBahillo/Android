package com.example.ik_2dm3.pruebaimp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class pantallacarga extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantallacarga);
        getSupportActionBar().hide();

        final Intent cartasI = new Intent(pantallacarga.this, TextoAudio3.class);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                setResult(RESULT_OK, cartasI);
                finish();
            }
        }, 3000);
    }
}
