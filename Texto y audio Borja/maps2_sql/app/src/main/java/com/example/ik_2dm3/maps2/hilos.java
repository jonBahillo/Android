package com.example.ik_2dm3.maps2;

import android.util.Log;
import android.widget.TextView;

public class hilos extends Thread {
    TextView txtview;
    String sacar;
    String[] palabras;
    boolean finalizado;
    int milisegundos;

    @Override
    public void run(){
        for (int j=0; j<palabras.length; j++) {
            sacar = palabras[j];
            synchronized (this) {
                try {
                    wait(milisegundos);
                    txtview.setText(txtview.getText() + " " + sacar);
                } catch (InterruptedException e) {
                    Log.d("mytag", "" + e);
                }
            }
        }
    }
}
