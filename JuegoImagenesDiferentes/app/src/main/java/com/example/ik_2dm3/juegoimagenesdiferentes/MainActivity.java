package com.example.ik_2dm3.juegoimagenesdiferentes;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    ImageView[] imageviewAntes = new ImageView[6];
    ImageView[] imageviewAhora = new ImageView[6];
    public ImageView iv0, iv1, iv2, iv3, iv4, iv5, iv6, iv7, iv8, iv9, iv10, iv11;
    ImageView primero;
    int numeroPrimero, numeroSegundo;
    boolean bloqueo = false, antes = false;
    ArrayList<Integer> arrayBarajado1;
    ArrayList<Integer> arrayBarajado2;
    public ArrayList<Integer> acertadosPrimero = new ArrayList<Integer>();
    public ArrayList<Integer> acertadosSegundo = new ArrayList<Integer>();
    final Handler handler = new Handler();
    int imagenes1[];
    int imagenes2[];
    int imagenes1elek[];
    int imagenes2elek[];
    Drawable imagenvuelta1;
    Drawable imagenvuelta2;
    int aciertos = 0,cont=0;
    Dialog dialogo;
    int iprimero,isegundo;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        if (Build.VERSION.SDK_INT > 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            //getWindow().addFlags( WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        dialogo = new Dialog(this);
        arrayBarajado1 = barajar1();
        arrayBarajado2 = barajar2();
        cargarImagenes();
        cargarImageViews();
        iniciar();
        while (cont < 6) {
            Log.d("entra", "si" + cont);
            imageviewAhora[cont].setEnabled(true);
            imageviewAntes[cont].setEnabled(true);
            cont++;
        }

    }

    public ArrayList<Integer> barajar1() {
        ArrayList resultadoA = new ArrayList<Integer>();
        for (int i = 0; i <= 5; i++)
            resultadoA.add(i % 6);
        Collections.shuffle(resultadoA);
        Log.d("imagenes", "resultadoA" + resultadoA);
        return resultadoA;

    }

    public ArrayList<Integer> barajar2() {
        ArrayList resultadoB = new ArrayList<Integer>();
        for (int i = 0; i <= 5; i++)
            resultadoB.add(i % 6);
        Collections.shuffle(resultadoB);
        return resultadoB;
    }

    public void cargarImagenes() {
        imagenes1 = new int[]{
                R.drawable.ga,
                R.drawable.gb,
                R.drawable.gc,
                R.drawable.gd,
                R.drawable.ge,
                R.drawable.gf,
        };
        imagenes2 = new int[]{
                R.drawable.gaa,
                R.drawable.gbb,
                R.drawable.gcc,
                R.drawable.gdd,
                R.drawable.gee,
                R.drawable.gff,
        };

        imagenes1elek = new int[]{
                R.drawable.gaelek,
                /*R.drawable.gbelek,
                R.drawable.gcelek,
                R.drawable.gdelek,
                R.drawable.geelek,
                R.drawable.gfelek,*/
        };
    }

    public void cargarImageViews() {
        iv0 = (ImageView) findViewById(R.id.imageView);
        imageviewAntes[0] = iv0;
        iv1 = (ImageView) findViewById(R.id.imageView2);
        imageviewAntes[1] = iv1;
        iv2 = (ImageView) findViewById(R.id.imageView3);
        imageviewAntes[2] = iv2;
        iv3 = (ImageView) findViewById(R.id.imageView4);
        imageviewAntes[3] = iv3;
        iv4 = (ImageView) findViewById(R.id.imageView5);
        imageviewAntes[4] = iv4;
        iv5 = (ImageView) findViewById(R.id.imageView6);
        imageviewAntes[5] = iv5;


        iv6 = (ImageView) findViewById(R.id.imageView7);
        imageviewAhora[0] = iv6;
        iv7 = (ImageView) findViewById(R.id.imageView8);
        imageviewAhora[1] = iv7;
        iv8 = (ImageView) findViewById(R.id.imageView9);
        imageviewAhora[2] = iv8;
        iv9 = (ImageView) findViewById(R.id.imageView10);
        imageviewAhora[3] = iv9;
        iv10 = (ImageView) findViewById(R.id.imageView11);
        imageviewAhora[4] = iv10;
        iv11 = (ImageView) findViewById(R.id.imageView12);
        imageviewAhora[5] = iv11;

    }

    public void comprobar(int i, final ImageView imgb, boolean antes) {


        if (primero == null) {//ningún botón ha sido pulsado
            try {
                Log.d("imagen", "array1" + arrayBarajado1);
                Log.d("imagen", "array2" + arrayBarajado2);
                //el botón primero será el que acabamos de pulsar
                primero = imgb;
                Log.d("imagen", "" + primero.getDrawable());

                isegundo = i;
                Log.d("mytagerror", "array1 " + arrayBarajado1 + " array2: " + arrayBarajado2);
                imagenvuelta1 = imgb.getDrawable();


                if (antes) {
                    numeroPrimero = arrayBarajado1.get(i);
                } else {
                    numeroPrimero = arrayBarajado2.get(i);
                }

                Log.d("mytagerror", "numero1 " + arrayBarajado1.get(i));


        /*le asignamos la imagen del vector imágenes situada
        en la posición arrayBarajado.get(i), con un valor entre 0 y 7*/
                if (antes) {
                    switch (numeroPrimero) {

                        //plaza
                        case 0:
                            primero.setImageResource(R.drawable.gaelek);
                            break;


                        //otra plaza
                        case 1:
                            primero.setImageResource(R.drawable.gbelek);
                            break;


                        case 2:
                            primero.setImageResource(R.drawable.gcelek);
                            break;

                        case 3:
                            primero.setImageResource(R.drawable.gdelek);
                            break;

                        case 4:
                            primero.setImageResource(R.drawable.geelek);
                            break;

                        case 5:
                            primero.setImageResource(R.drawable.gfelek);
                            break;
                    }
                } else {
                    Log.d("mytag",numeroPrimero+" es el numero");
                    switch (numeroPrimero) {

                        case 0:
                            imgb.setImageResource(R.drawable.gaaelek);
                            break;


                        //otra plaza
                        case 1:
                            imgb.setImageResource(R.drawable.gbbelek);
                            break;


                        case 2:
                            //bien
                            imgb.setImageResource(R.drawable.gccelek);
                            break;

                        case 3:
                            imgb.setImageResource(R.drawable.gddelek);
                            break;

                        case 4:
                            imgb.setImageResource(R.drawable.geeelek);
                            break;

                        case 5:
                            imgb.setImageResource(R.drawable.gffelek);
                            break;
                    }
                }


                if (!antes) {
                    for (int b = 0; b <= arrayBarajado1.size(); b++) {
                        imageviewAhora[b].setEnabled(false);
                    }
                } else {
                    for (int d = 0; d <= arrayBarajado2.size(); d++) {
                        imageviewAntes[d].setEnabled(false);
                    }
                }

            } catch (Exception e) {
                imgb.setEnabled(true);
                primero.setEnabled(true);
            }
            //almacenamos el valor de arrayBarajado[i]
            //numeroPrimero = primero.getId();


        } else {//ya hay un botón descubierto
            if (!primero.equals(imgb)) {

                iprimero = i;
                //bloqueamos todos los demás
                bloqueo = true;
                //imgb.setImageResource();
                //bloqueamos el botón
                imagenvuelta2 = imgb.getDrawable();
                //almacenamos el valor de arrayBarajado.get(i)
                numeroSegundo = arrayBarajado2.get(i);

                if (antes) {
                    numeroSegundo = arrayBarajado1.get(i);
                } else {
                    numeroSegundo = arrayBarajado2.get(i);
                }

                Log.d("mytagerror", "numero2 " + arrayBarajado2.get(i));

                if (numeroPrimero == numeroSegundo) {//si coincide el valor los dejamos   destapados
                    if (!antes) {
                        acertadosPrimero.add(iprimero);
                        acertadosSegundo.add(isegundo);
                    } else {
                        acertadosPrimero.add(isegundo);
                        acertadosSegundo.add(iprimero);
                    }

                    aciertos++;

                    //primero.setVisibility(View.INVISIBLE);
                    //imgb.setVisibility(View.INVISIBLE);


                    primero.setImageResource(R.drawable.bien);
                    imgb.setImageResource(R.drawable.bien);

                    primero = null;
                    if (aciertos == 6) {
                        popup(null);
                    }

                    //ganadores
                    //acertados[acertados.length]=numeroPrimero;
                    int k = 0;
                    while (k < 6) {
                        Log.d("entra", "si" + k);
                        imageviewAhora[k].setEnabled(true);
                        imageviewAntes[k].setEnabled(true);
                        k++;

                    }
                    for (int a = 0; a <= acertadosPrimero.size() - 1; a++) {
                        imageviewAhora[acertadosPrimero.get(a)].setEnabled(false);
                        imageviewAntes[acertadosSegundo.get(a)].setEnabled(false);
                    }
                    bloqueo = false;


                } else {//si NO coincide el valor los volvemos a tapar al cabo de un segundo
                    primero.setImageResource(R.drawable.mal);
                    //imgb.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    imgb.setImageResource(R.drawable.mal);
                    try {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                //les ponemos la imagen de fondo
                                //primero.setScaleType(ImageView.ScaleType.CENTER_CROP);
                                primero.setImageDrawable(imagenvuelta1);
                                //imgb.setScaleType(ImageView.ScaleType.CENTER_CROP);
                                imgb.setImageDrawable(imagenvuelta2);
                                //los volvemos a habilitar

                                for (int b = 0; b < 6; b++) {
                                    imageviewAhora[b].setEnabled(true);
                                    imageviewAntes[b].setEnabled(true);
                                }
                                for (int a = 0; a <= acertadosPrimero.size() - 1; a++) {
                                    imageviewAhora[acertadosPrimero.get(a)].setEnabled(false);
                                    imageviewAntes[acertadosSegundo.get(a)].setEnabled(false);
                                }


                                //reiniciamos la variables auxiliares
                                primero = null;
                                bloqueo = false;
                                //restamos uno a la puntuación
                        /*if (puntuacion > 0) {
                            puntuacion--;
                            textoPuntuacion.setText("Puntuación: " + puntuacion);
                        }*/
                            }
                        }, 1000);//al cabo de un segundo
                    } catch (Exception e) {
                        Log.d("mytagerror", "excepcion " + e);
                    }
                }

            } else {


                primero.setImageDrawable(imagenvuelta1);

                for (int b = 0; b < 6; b++) {
                    imageviewAhora[b].setEnabled(true);
                    imageviewAntes[b].setEnabled(true);
                }
                for (int a = 0; a <= acertadosPrimero.size() - 1; a++) {
                    imageviewAhora[acertadosPrimero.get(a)].setEnabled(false);
                    imageviewAntes[acertadosSegundo.get(a)].setEnabled(false);
                }
                primero=null;
            }
            antes = false;
        }
    }

    public void iniciar() {
        for (int i = 0; i <= 5; i++) {
            imageviewAntes[i].setImageResource(imagenes1[arrayBarajado1.get(i)]);
            Log.d("imagenes", "" + imagenes2[i]);
            imageviewAhora[i].setImageResource(imagenes2[arrayBarajado2.get(i)]);


            //AÑADIMOS LOS EVENTOS A LOS BOTONES DEL JUEGO
            for (int a = 0; a < 6; a++) {
                final int j = a;
                imageviewAhora[a].setEnabled(true);
                imageviewAhora[a].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!bloqueo)
                            comprobar(j, imageviewAhora[j], false);
                    }
                });
            }
            for (int a = 0; a < 6; a++) {
                final int j = a;
                imageviewAntes[a].setEnabled(true);
                imageviewAntes[a].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!bloqueo)
                            comprobar(j, imageviewAntes[j], true);
                    }
                });
            }


        }


    }

    public void popup(View v) {
        Button Errepikatu;
        Button Aurrera;
        dialogo.setContentView(R.layout.popup_juego);
        Errepikatu = dialogo.findViewById(R.id.btnErrepikatu);
        Aurrera = dialogo.findViewById(R.id.btnAurrera);
        dialogo.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Errepikatu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogo.dismiss();
                cargarImagenes();
                aciertos=0;
                acertadosPrimero.clear();
                acertadosSegundo.clear();
                arrayBarajado1 = barajar1();
                arrayBarajado2 = barajar2();
                cargarImageViews();
                visibilizar();
                iniciar();
            }
        });
        Aurrera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent salir = new Intent();
                setResult(RESULT_OK, salir);
                finish();
            }
        });
        dialogo.show();
    }
    public void visibilizar(){
        for (int a = 0; a < 6; a++) {

            imageviewAhora[a].setVisibility(View.VISIBLE);
            imageviewAntes[a].setVisibility(View.VISIBLE);
        }
    }
}