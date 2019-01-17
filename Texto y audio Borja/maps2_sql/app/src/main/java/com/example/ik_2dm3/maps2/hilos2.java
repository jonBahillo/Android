package com.example.ik_2dm3.maps2;

import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

public class hilos2 extends Thread{
    TextView txtview2;
    String algo = "";
    String[] palabras2_1;
    String[] palabras2_2;
    int milisegundos2;
    int dialogos=0;

    @Override
    public void run(){
            txtview2.setText("");
            String sacar2;
            ArrayList guardartexto = new ArrayList<String>();
            if (dialogos == 1) {
                for (int j = 0; j < palabras2_1.length; j++) {
                    sacar2 = palabras2_1[j];
                    synchronized (this) {
                        try {
                            wait(milisegundos2);
                            //txtview2.setText(txtview2.getText() + " " + sacar2);
                            algo = txtview2.getText() + " " + sacar2;
                        } catch (InterruptedException e) {
                            Log.d("mytag", "" + e);
                        }
                    }
                }
            } else if (dialogos == 2) {
                for (int j = 0; j < palabras2_1.length; j++) {
                    sacar2 = palabras2_1[j];
                    synchronized (this) {
                        try {
                            wait(milisegundos2);
                            txtview2.setText(txtview2.getText() + " " + sacar2);
                            if (j == palabras2_1.length - 1) {
                                guardartexto.add(txtview2.getText());
                                algo = "";
                                for (int i = 0; i < palabras2_2.length; i++) {
                                    sacar2 = palabras2_2[i];
                                    wait(milisegundos2);
                                    algo = txtview2.getText() + " " + sacar2;
                                }
                            }
                        } catch (Exception e) {
                            Log.d("mytag", "" + e);
                        }
                    }
                }
            }
    }
}
