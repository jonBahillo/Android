package com.example.ik_2dm3.pruebaimp;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import java.util.Collections;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ImageView foto1_1;
    ImageView foto1_2;
    ImageView foto2_1;
    ImageView foto2_2;
    ImageView foto3_1;
    ImageView foto3_2;
    ImageView foto4_1;
    ImageView foto4_2;
    ImageView foto5_1;
    ImageView foto5_2;
    ImageView foto6_1;
    ImageView foto6_2;
    ImageView foto7_1;
    ImageView foto7_2;
    ImageView foto8_1;
    ImageView foto8_2;


    int intentos=0;
    int ja;
    int aciertos=0;
    //Imagenes
    int Imagenes[];
    //Las guardamos duplicadas
    ImageView [] botonera = new ImageView[16];
    //Imagen de fondo
    int fondo;
    int fondo2;
    //el ArrayList que recoge el resultado de barajar
    ArrayList<Integer> arrayBarajado;
    //COMPARACIÓN
    //los botones que se han pulsado y se comparan
    ImageView primero;
    //posiciones de las imágenes a comparar en el vector de imágenes
    int numeroPrimero, numeroSegundo;
    //durante un segundo se bloquea el juego y no se puede pulsar ningún botón
    boolean bloqueo = false;
    //para controlar las pausas del juego
    final Handler handler = new Handler();
    Drawable imagenvuelta1;
    Drawable imagenvuelta2;
    Dialog dialogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        //Esconder barra de estado
        if (Build.VERSION.SDK_INT > 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            //getWindow().addFlags( WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        dialogo = new Dialog(this);
        cargarImagenes();
        iniciar();
    }
    public void cargarImagenes(){
        Imagenes = new int[]{
                R.drawable.fa,
                R.drawable.fb,
                R.drawable.fc,
                R.drawable.fd,
                R.drawable.fe,
                R.drawable.ff,
                R.drawable.fg,
                R.drawable.fh,
        };

        fondo = R.drawable.sapo;
        fondo2 = R.drawable.sapa;
    }
    public ArrayList<Integer> barajar(int longitud) {
        ArrayList resultadoA = new ArrayList<Integer>();
        for(int i=0; i<longitud; i++)
            resultadoA.add(i % longitud/2);
        Collections.shuffle(resultadoA);
        //Log.d("mytag", "Resultado: " + resultadoA);
        return  resultadoA;
    }
    public void cargarBotones(){
        foto1_1 = findViewById(R.id.ivFoto1_1);
        botonera[0] = foto1_1;
        foto1_2 = findViewById(R.id.ivFoto1_2);
        botonera[1] = foto1_2;
        foto2_1 = findViewById(R.id.ivFoto2_1);
        botonera[2] = foto2_1;
        foto2_2 = findViewById(R.id.ivFoto2_2);
        botonera[3] = foto2_2;
        foto3_1 = findViewById(R.id.ivFoto3_1);
        botonera[4] = foto3_1;
        foto3_2 = findViewById(R.id.ivFoto3_2);
        botonera[5] = foto3_2;
        foto4_1 = findViewById(R.id.ivFoto4_1);
        botonera[6] = foto4_1;
        foto4_2 = findViewById(R.id.ivFoto4_2);
        botonera[7] = foto4_2;
        foto5_1 = findViewById(R.id.ivFoto5_1);
        botonera[8] = foto5_1;
        foto5_2 = findViewById(R.id.ivFoto5_2);
        botonera[9] = foto5_2;
        foto6_1 = findViewById(R.id.ivFoto6_1);
        botonera[10] = foto6_1;
        foto6_2 = findViewById(R.id.ivFoto6_2);
        botonera[11] = foto6_2;
        foto7_1 = findViewById(R.id.ivFoto7_1);
        botonera[12] = foto7_1;
        foto7_2 = findViewById(R.id.ivFoto7_2);
        botonera[13] = foto7_2;
        foto8_1 = findViewById(R.id.ivFoto8_1);
        botonera[14] = foto8_1;
        foto8_2 = findViewById(R.id.ivFoto8_2);
        botonera[15] = foto8_2;
    }
    public void popup (View v){
        Button Errepikatu;
        Button Aurrera;
        dialogo.setContentView(R.layout.popup);
        Errepikatu = dialogo.findViewById(R.id.btnErrepikatu);
        Aurrera = dialogo.findViewById(R.id.btnAurrera);
        Errepikatu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogo.dismiss();
                cargarImagenes();
                iniciar();
            }
        });
        Aurrera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        dialogo.show();
    }
    public void comprobar(int i, final ImageView imgb){

        if(primero==null){//ningún botón ha sido pulsado
            //el botón primero será el que acabamos de pulsar
            primero = imgb;
            //Log.d("mytag", "Imagen:");
            imagenvuelta1 = primero.getDrawable();
        /*le asignamos la imagen del vector imágenes situada
        en la posición arrayBarajado.get(i), con un valor entre 0 y 7*/
            primero.setScaleType(ImageView.ScaleType.CENTER_CROP);
            primero.setImageResource(Imagenes[arrayBarajado.get(i)]);
            //bloqueamos el botón
            primero.setEnabled(false);
            //almacenamos el valor de arrayBarajado[i]
            numeroPrimero=arrayBarajado.get(i);
        }else{//ya hay un botón descubierto
            //bloqueamos todos los demás
            bloqueo=true;
            //el botón segundo será el que acabamos de pulsar
        /*le asignamos la imagen del vector imágenes situada
        en la posición arrayBarajado.get(i), con un valor entre 0 y 7*/
            imagenvuelta2 = imgb.getDrawable();
            imgb.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imgb.setImageResource(Imagenes[arrayBarajado.get(i)]);
            //bloqueamos el botón
            imgb.setEnabled(false);
            //almacenamos el valor de arrayBarajado.get(i)
            numeroSegundo=arrayBarajado.get(i);
            if(numeroPrimero==numeroSegundo){//si coincide el valor los dejamos   destapados
                //reiniciamos
                primero=null;
                bloqueo=false;
                //aumentamos los aciertos y la puntuación
                aciertos++;
                intentos++;
                //al llegar a 8 aciertos se ha ganado el juego
                if(aciertos==8){
                    //dialogo.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    popup(null);
                }
            }else{//si NO coincide el valor los volvemos a tapar al cabo de un segundo
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //les ponemos la imagen de fondo
                        primero.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        primero.setImageDrawable(imagenvuelta1);
                        imgb.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        imgb.setImageDrawable(imagenvuelta2);
                        //los volvemos a habilitar
                        primero.setEnabled(true);
                        imgb.setEnabled(true);
                        //reiniciamos la variables auxiliares
                        primero = null;
                        bloqueo = false;
                        intentos++;
                    }
                }, 500);//al cabo de un segundo
            }
        }
    }
    public void iniciar(){
        int inicio=0;
        final Intent cargaI = new Intent(MainActivity.this, pantallacarga.class);
        if (inicio==0) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(cargaI);
                }
            }, 1);
            inicio++;
        }
        arrayBarajado = barajar(Imagenes.length*2);
        cargarBotones();

        //MOSTRAMOS LA IMAGEN
        for(int i=0; i<botonera.length; i++) {
            botonera[i].setScaleType(ImageView.ScaleType.CENTER_CROP);
            botonera[i].setImageResource(Imagenes[arrayBarajado.get(i)]);
        }

        //Y EN UN SEGUNDO LA OCULTAMOS
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < botonera.length; i++) {
                    botonera[i].setScaleType(ImageView.ScaleType.CENTER_CROP);
                    if (i%2 == 0){
                        botonera[i].setImageResource(fondo2);
                    }else{
                        botonera[i].setImageResource(fondo);
                    }
                }
            }
        }, 2500);

        //AÑADIMOS LOS EVENTOS A LOS BOTONES DEL JUEGO
        for(int i=0; i <arrayBarajado.size(); i++){
            final int j=i;
            botonera[i].setEnabled(true);
            botonera[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!bloqueo)
                        comprobar(j, botonera[j]);
                }
            });
            ja=j;
        }
        aciertos=0;
    }
    /*public void onClick(View v) {
        if(!bloqueo)
            comprobar(ja, botonera[ja]);
    }*/

}
