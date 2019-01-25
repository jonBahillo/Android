package com.example.ik_2dm3.maps2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class TextoAudio extends AppCompatActivity {

    String texto1;
    String texto2;
    String texto3;
    String pantalla = "";
    String[] pruebatexto1;
    String[] pruebatexto2;
    String[] pruebatexto3;
    MediaPlayer audio;
    TextView mostrar;
    TextView siguiente;
    ImageView cambiartexto;
    ImageView cambiartexto2;
    boolean primero = false;
    boolean segundo = true;
    boolean finalizar = false;
    boolean textomayor = false;
    final int REQ_JUEGO0 = 1;
    final int REQ_JUEGO1 = 2;
    final int REQ_JUEGO2 = 3;
    final int REQ_JUEGO2_2 = 4;
    final int REQ_JUEGO3 = 5;
    final int REQ_JUEGO3_2 = 6;
    final int REQ_JUEGO4 = 7;
    final int REQ_JUEGO5 = 8;
    final int REQ_JUEGO6 = 9;
    final int REQ_JUEGO7 = 10;
    final int REQ_JUEGO8 = 11;
    int dialogos;
    int actividad;
    int vartexto = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        if (Build.VERSION.SDK_INT > 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        setContentView(R.layout.activity_texto_audio);
        LinearLayout linearlayout = findViewById(R.id.Fondoimagen);



        Basededatos MDB = new Basededatos(getApplicationContext());
        final Bundle extras = getIntent().getExtras();
        Integer index = extras.getInt("index");
        ArrayList<Posiciones> posicion =  MDB.recuperarposicionesuno(index);

        // String  activity = "com.example.ik_2dm3.maps2.TextoAudio";
        byte[] decodedString = Base64.decode(posicion.get(0).getImgenpopup(), Base64.DEFAULT);

        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString,
                0, decodedString.length);

        BitmapDrawable drawable = new BitmapDrawable(getResources(), decodedByte);


        linearlayout.setBackground(drawable);
        siguiente=findViewById(R.id.txtSig);
        cambiartexto = findViewById(R.id.imgCambiartexto);
        cambiartexto2 = findViewById(R.id.imgCambiartexto2);
        siguiente.setVisibility(View.INVISIBLE);
        cambiartexto.setVisibility(View.INVISIBLE);
        cambiartexto2.setVisibility(View.INVISIBLE);
        pantalla = extras.getString("numero");
        actividad = Integer.parseInt(pantalla);

        //// Quitar Ion
        //siguiente.setVisibility(View.VISIBLE);
        //Log.d("mytag", "Toma error:" + actividad);
        switch (actividad) {
            case 0:
                /////////////TIENE BOTON DE SIGUIENTE TEXTO
                //Reproducimos el audio
                audio = MediaPlayer.create(TextoAudio.this, R.raw.sarrera);
                audio.start();
                //Sacamos el texto palabra a palabra
                texto1 = getResources().getString(R.string.gunea0_1);
                texto2 = getResources().getString(R.string.gunea0_2);
                //Separamos las palabras
                pruebatexto1=texto1.split(" ");
                pruebatexto2=texto2.split(" ");
                mostrar = findViewById(R.id.txtHistoria);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        audio.pause();
                        siguiente.setVisibility(View.VISIBLE);
                        cambiartexto.setVisibility(View.VISIBLE);
                    }
                }, 71500);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ejecutar_hilo(mostrar, pruebatexto1, pruebatexto2,  pruebatexto3, 850, 2);
                    }
                }, 2900);
                cambiartexto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (primero){
                            mostrar.setText(texto2);
                            cambiartexto.setImageResource(R.drawable.flecha_iz);
                            primero=false;
                        }else{
                            mostrar.setText(texto1);
                            cambiartexto.setImageResource(R.drawable.flecha_der);
                            primero=true;
                        }
                    }
                });
                siguiente.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        /*Basededatos MDB = new Basededatos(getApplicationContext());

                        Bundle extras = getIntent().getExtras();

                        Integer index = extras.getInt("index");

                        ArrayList<Posiciones> posicion =  MDB.recuperarposicionesuno(index);

                        String activity = "com.example.ik_2dm3.maps2."+posicion.get(0).getJuegonombre();
                        String  activity = "com.example.ik_2dm3.maps2.TextoAudio";
                        Intent intent= null;
                        try {
                            intent = new Intent(getBaseContext(), Class.forName(activity));

                            intent.putExtra("index", index);
                            startActivityForResult(intent, REQ_JUEGO0);
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }*/

               // Intent juego = new Intent(TextoAudio.this, pantallacarga.class);
               /*         Intent juego = new Intent(TextoAudio.this, MainActivity.class);
                startActivityForResult(juego, REQ_JUEGO0);*/
               finish();


                    }
                });
                break;
            case 1:
                /////////NO TIENE BOTON DE SIGUIENTE TEXTO
                //Reproducimos el audio
                audio = MediaPlayer.create(TextoAudio.this, R.raw.santaana1);
                //audio1.setVolume(200,200);
                audio.start();
                //Sacamos el texto palabra a palabra
                texto1 = getResources().getString(R.string.gunea1);
                texto2 = getResources().getString(R.string.gunea1_2);
                //Separamos las palabras
                pruebatexto1 = texto1.split(" ");
                pruebatexto2 = texto2.split(" ");
                mostrar = findViewById(R.id.txtHistoria);
                cambiartexto.setVisibility(View.INVISIBLE);
                final Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ejecutar_hilo(mostrar, pruebatexto1, pruebatexto2,  pruebatexto3, 850, 2);
                    }
                }, 4500);
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        siguiente.setVisibility(View.VISIBLE);
                        cambiartexto.setVisibility(View.VISIBLE);
                    }
                }, 67100);//67100);
                cambiartexto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (primero){
                            mostrar.setText(texto2);
                            cambiartexto.setImageResource(R.drawable.flecha_iz);
                            primero=false;
                        }else{
                            mostrar.setText(texto1);
                            cambiartexto.setImageResource(R.drawable.flecha_der);
                            primero=true;
                        }
                    }
                });
                siguiente.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //if (primero){
                            primero=false;
                            if (audio.isPlaying()){
                                audio.stop();
                            }
                            Basededatos MDB = new Basededatos(getApplicationContext());
                            Bundle extras = getIntent().getExtras();
                            Integer index = extras.getInt("index");
                            ArrayList<Posiciones> posicion =  MDB.recuperarposicionesuno(index);
                            String activity = "com.example.ik_2dm3.maps2."+posicion.get(0).getJuegonombre();
                            // String  activity = "com.example.ik_2dm3.maps2.TextoAudio";
                            Intent intent= null;
                            try {
                                intent = new Intent(getBaseContext(), Class.forName(activity));
                                intent.putExtra("index", index);
                                startActivityForResult(intent, REQ_JUEGO1);
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }

                            /*
                            Bundle extras = getIntent().getExtras();
                            Integer index = extras.getInt("index");
                            Intent juego = new Intent(TextoAudio.this, MainActivity_juegoMemoria.class);
                            juego.putExtra("index", index);
                            startActivityForResult(juego, REQ_JUEGO1);*/


                       /* }else{
                            primero=false;
                            Bundle extras = getIntent().getExtras();
                            Integer index = extras.getInt("index");
                            Intent juego = new Intent(TextoAudio.this, MainActivity_juegoMemoria.class);
                            juego.putExtra("index", index);
                            startActivityForResult(juego, REQ_JUEGO1);
                        }*/
                    }
                });
                break;
            case 2:
                /////////NO TIENE BOTON DE SIGUIENTE TEXTO
                //Reproducimos el audio
                audio = MediaPlayer.create(TextoAudio.this, R.raw.kurutziagados);
                audio.start();
                if (audio.getCurrentPosition()==28000){
                    audio.pause();
                }
                //cambiartexto.setVisibility(View.INVISIBLE);
                //Sacamos el texto palabra a palabra
                texto1 = getResources().getString(R.string.gunea2_1);
                //Separamos las palabras
                pruebatexto1=texto1.split(" ");
                mostrar = findViewById(R.id.txtHistoria);
                final Handler handler2 = new Handler();
                handler2.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ejecutar_hilo(mostrar, pruebatexto1, pruebatexto2,  pruebatexto3, 800, 1);
                    }
                }, 2500);
                handler2.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        siguiente.setVisibility(View.VISIBLE);
                    }
                }, 30000);//30000);
                ////////MIRAR QUE HACE
                /*handler2.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        audio.stop();
                    }
                }, 30300);*/
                siguiente.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (segundo){
                            siguiente.setVisibility(View.INVISIBLE);
                            audio.pause();

                            Basededatos MDB = new Basededatos(getApplicationContext());
                            Bundle extras = getIntent().getExtras();
                            Integer index = extras.getInt("index");
                            ArrayList<Posiciones> posicion =  MDB.recuperarposicionesuno(index);
                            String activity = "com.example.ik_2dm3.maps2."+posicion.get(0).getJuegonombre();
                            // String  activity = "com.example.ik_2dm3.maps2.TextoAudio";
                            Intent intent= null;
                            try {
                                intent = new Intent(getBaseContext(), Class.forName(activity));
                                intent.putExtra("index", index);
                                startActivityForResult(intent, REQ_JUEGO2);
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }

                            /*Integer index = extras.getInt("index");
                            Intent juego = new Intent(TextoAudio.this, MainActivity_juegoMemoria.class);
                            juego.putExtra("index", index);
                            startActivityForResult(juego, REQ_JUEGO2);*/
                            segundo = false;
                       }else{
                       /*     Basededatos MDB = new Basededatos(getApplicationContext());
                            Bundle extras = getIntent().getExtras();

                            Integer index = extras.getInt("index");

                            ArrayList<Posiciones> posicion =  MDB.recuperarposicionesuno(index);

                            String activity = "com.example.ik_2dm3.maps2."+posicion.get(0).getJuegonombre();
                            // String  activity = "com.example.ik_2dm3.maps2.TextoAudio";
                            Intent intent= null;
                            try {
                                intent = new Intent(TextoAudio.this, Class.forName(activity));

                                intent.putExtra("index", index);
                                startActivityForResult(intent, REQ_JUEGO2_2);
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }*/
                            ////////////////ESTO CREO QUE SOBRA*/
                            siguiente.setVisibility(View.INVISIBLE);
                            Integer index = extras.getInt("index");
                           // Intent juego = new Intent(TextoAudio.this, MainActivity_camara.class);
                            Intent juego = new Intent(TextoAudio.this, MainActivity_camara.class);
                            juego.putExtra("index", index);
                            startActivityForResult(juego, REQ_JUEGO2_2);
                        }
                        if(finalizar){
                            Intent juego = new Intent(TextoAudio.this, pantallacarga.class);
                            startActivityForResult(juego, REQ_JUEGO2_2);
                        }
                    }
                });
                break;
            case 3:
                /////////NO TIENE BOTON DE SIGUIENTE TEXTO
                //Reproducimos el audio
                if (siguiente.isCursorVisible()){
                    siguiente.setVisibility(View.INVISIBLE);
                }
                audio = MediaPlayer.create(TextoAudio.this, R.raw.aldezaharra);
                audio.start();
                //Sacamos el texto palabra a palabra
                texto1 = getResources().getString(R.string.gunea3_1);
                //Separamos las palabras
                pruebatexto1=texto1.split(" ");
                mostrar = findViewById(R.id.txtHistoria);
                final Handler handler3 = new Handler();
                handler3.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ejecutar_hilo(mostrar, pruebatexto1, pruebatexto2,  pruebatexto3, 935, 1);
                    }
                }, 4000);
                //Log.d("mytag", "Disfruta del error:" + REQ_JUEGO3);
                siguiente.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (segundo){
                            segundo = false;
                            Basededatos MDB = new Basededatos(getApplicationContext());
                            Bundle extras = getIntent().getExtras();
                            Integer index = extras.getInt("index");
                            ArrayList<Posiciones> posicion =  MDB.recuperarposicionesuno(index);
                            String activity = "com.example.ik_2dm3.maps2."+posicion.get(0).getJuegonombre();
                            // String  activity = "com.example.ik_2dm3.maps2.TextoAudio";
                            Intent intent= null;
                            try {

                                intent = new Intent(getBaseContext(), Class.forName(activity));
                                intent.putExtra("index", index);
                                startActivityForResult(intent, REQ_JUEGO3);

                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }



                            /*Integer index = extras.getInt("index");
                            Intent juego = new Intent(TextoAudio.this, juegoHuecos.class);
                            juego.putExtra("index", index);
                            startActivityForResult(juego, REQ_JUEGO3);*/


                        }else{
                            Integer index = extras.getInt("index");
                            Intent juego = new Intent(TextoAudio.this, MainActivity.class);
                            juego.putExtra("index", index);
                            startActivityForResult(juego, REQ_JUEGO3_2);
                        }

                    }
                });
                handler3.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        audio.stop();
                    }
                }, 21000);
                handler3.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        siguiente.setVisibility(View.VISIBLE);
                    }
                }, 22000);
                break;
            case 4:
                /////////TIENE BOTON DE SIGUIENTE TEXTO
                //Reproducimos el audio
                audio = MediaPlayer.create(TextoAudio.this, R.raw.andramari);
                audio.start();
                if (audio.getCurrentPosition()==28000){
                    audio.stop(); //pausa
                }
                //Sacamos el texto palabra a palabra
                texto1 = getResources().getString(R.string.gunea4_1_1);
                texto2 = getResources().getString(R.string.gunea4_1_2);
                //Separamos las palabras
                pruebatexto1=texto1.split(" ");
                pruebatexto2=texto2.split(" ");
                mostrar = findViewById(R.id.txtHistoria);
                ejecutar_hilo(mostrar, pruebatexto1, pruebatexto2,  pruebatexto3, 565, 2);
                final Handler handler4 = new Handler();
                handler4.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        audio.pause();
                        siguiente.setVisibility(View.VISIBLE);
                        cambiartexto.setVisibility(View.VISIBLE);
                    }
                }, 51000);
                siguiente.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        /*Basededatos MDB = new Basededatos(getApplicationContext());
                        Bundle extras = getIntent().getExtras();
                        Integer index = extras.getInt("index");
                        ArrayList<Posiciones> posicion =  MDB.recuperarposicionesuno(index);
                        String activity = "com.example.ik_2dm3.maps2."+posicion.get(0).getJuegonombre();
                        // String  activity = "com.example.ik_2dm3.maps2.TextoAudio";
                        Intent intent= null;
                        try {
                            intent = new Intent(getBaseContext(), Class.forName(activity));
                            intent.putExtra("index", index);
                            startActivityForResult(intent, REQ_JUEGO4);
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }*/

                        Integer index = extras.getInt("index");
                        Intent juego = new Intent(TextoAudio.this, MainActivity_camara.class);
                        juego.putExtra("index", index);
                        startActivityForResult(juego, REQ_JUEGO4);
                        segundo = false;
                    }
                });
                cambiartexto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (primero){
                            mostrar.setText(texto2);
                            cambiartexto.setImageResource(R.drawable.flecha_iz);
                            primero=false;
                        }else{
                            mostrar.setText(texto1);
                            cambiartexto.setImageResource(R.drawable.flecha_der);
                            primero=true;
                        }
                    }
                });
                break;
            case 5:
                /////////NO TIENE BOTON DE SIGUIENTE TEXTO
                cambiartexto.setVisibility(View.INVISIBLE);
                final Handler handler5_1 = new Handler();
                //Reproducimos el audio
                audio = MediaPlayer.create(TextoAudio.this, R.raw.ezkurdi);
                audio.start();
                //Sacamos el texto palabra a palabra
                texto1 = getResources().getString(R.string.gunea5);
                texto2 = getResources().getString(R.string.gunea5_2);
                //Separamos las palabras
                pruebatexto1 = texto1.split(" ");
                pruebatexto2 = texto2.split(" ");
                mostrar = findViewById(R.id.txtHistoria);
                ejecutar_hilo(mostrar, pruebatexto1, pruebatexto2,  pruebatexto3, 605, 2);
                handler5_1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        siguiente.setVisibility(View.VISIBLE);
                        cambiartexto.setVisibility(View.VISIBLE);
                    }
                }, 38000);
                siguiente.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Basededatos MDB = new Basededatos(getApplicationContext());
                        Bundle extras = getIntent().getExtras();
                        Integer index = extras.getInt("index");
                        ArrayList<Posiciones> posicion =  MDB.recuperarposicionesuno(index);
                        String activity = "com.example.ik_2dm3.maps2."+posicion.get(0).getJuegonombre();
                        //String  activity = "com.example.ik_2dm3.maps2.TextoAudio";
                        Intent intent= null;
                        try {
                            intent = new Intent(TextoAudio.this, Class.forName(activity));
                            if (audio.isPlaying()){
                                audio.stop();
                            }
                            intent.putExtra("index", index);
                            startActivityForResult(intent, REQ_JUEGO5);
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }

                        /*Integer index = extras.getInt("index");
                        Intent juego = new Intent(TextoAudio.this, MainActivity_juegoMemoria.class);
                        juego.putExtra("index", index);*/
                        if (audio.isPlaying()){
                            audio.stop();
                        }
                        //startActivityForResult(juego, REQ_JUEGO5);
                        startActivityForResult(intent, REQ_JUEGO5);
                        segundo = false;
                    }
                });
                cambiartexto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (primero){
                            mostrar.setText(texto2);
                            cambiartexto.setImageResource(R.drawable.flecha_iz);
                            primero=false;
                        }else{
                            mostrar.setText(texto1);
                            cambiartexto.setImageResource(R.drawable.flecha_der);
                            primero=true;
                        }
                    }
                });
                break;
            case 6:
                /////////TIENE BOTON DE SIGUIENTE TEXTO
                //Reproducimos el audio
                audio = MediaPlayer.create(TextoAudio.this, R.raw.sanagustin1);
                audio.start();
                final Handler handler6 = new Handler();
                //Sacamos el texto palabra a palabra
                texto1 = getResources().getString(R.string.gunea6_1);
                //Separamos las palabras
                pruebatexto1=texto1.split(" ");
                mostrar = findViewById(R.id.txtHistoria);
                handler6.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ejecutar_hilo(mostrar, pruebatexto1, pruebatexto2,  pruebatexto3, 590, 1);
                    }
                }, 2);
                handler6.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        audio.pause();
                        siguiente.setVisibility(View.VISIBLE);
                        //cambiartexto.setVisibility(View.VISIBLE);
                    }
                }, 34800);
                siguiente.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Basededatos MDB = new Basededatos(getApplicationContext());

                        Bundle extras = getIntent().getExtras();

                        Integer index = extras.getInt("index");

                        ArrayList<Posiciones> posicion =  MDB.recuperarposicionesuno(index);

                        String activity = "com.example.ik_2dm3.maps2."+posicion.get(0).getJuegonombre();
                        // String  activity = "com.example.ik_2dm3.maps2.TextoAudio";
                        Intent intent= null;
                        try {
                            intent = new Intent(getBaseContext(), Class.forName(activity));

                            intent.putExtra("index", index);
                            startActivityForResult(intent, REQ_JUEGO6);
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                       /* Integer index = extras.getInt("index");
                        Intent juego = new Intent(TextoAudio.this, MainActivity_juegoimagenesdiferentes.class);
                        juego.putExtra("index", index);*/

                        if (audio.isPlaying()){
                        audio.stop();
                        }
                       // startActivityForResult(juego, REQ_JUEGO6);
                        startActivityForResult(intent, REQ_JUEGO6);
                    }
                });
                cambiartexto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (primero){
                            mostrar.setText(texto2);
                            cambiartexto.setImageResource(R.drawable.flecha_iz);
                            primero=false;
                        }else{
                            mostrar.setText(texto1);
                            cambiartexto.setImageResource(R.drawable.flecha_der);
                            primero=true;
                        }
                    }
                });
                break;
            case 7:
                final Handler handler7 = new Handler();
                //Sacamos el texto palabra a palabra
                texto1 = getResources().getString(R.string.gunea6_2);
                //Separamos las palabras
                pruebatexto1=texto1.split(" ");
                texto2 = getResources().getString(R.string.gunea6_3);
                //Separamos las palabras
                pruebatexto2=texto2.split(" ");
                mostrar = findViewById(R.id.txtHistoria);
                handler7.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        audio = MediaPlayer.create(TextoAudio.this, R.raw.sanagustin2);
                        audio.start();
                        ejecutar_hilo(mostrar, pruebatexto1, pruebatexto1,  pruebatexto3, 590, 1);
                    }
                }, 1);
                handler7.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        audio.stop();
                        audio = MediaPlayer.create(TextoAudio.this, R.raw.sanagustin3);
                        audio.start();
                        ejecutar_hilo(mostrar, pruebatexto2, pruebatexto1,  pruebatexto3, 590, 1);
                    }
                }, 31800);
                handler7.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        audio.stop();
                        audio.release();
                        siguiente.setVisibility(View.VISIBLE);
                        cambiartexto.setVisibility(View.VISIBLE);
                    }
                }, 52000);
                cambiartexto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (primero){
                            mostrar.setText(texto2);
                            cambiartexto.setImageResource(R.drawable.flecha_iz);
                            primero=false;
                        }else{
                            mostrar.setText(texto1);
                            cambiartexto.setImageResource(R.drawable.flecha_der);
                            primero=true;
                        }
                    }
                });
                siguiente.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        /*Basededatos MDB = new Basededatos(getApplicationContext());

                        Bundle extras = getIntent().getExtras();

                        Integer index = extras.getInt("index");

                        ArrayList<Posiciones> posicion =  MDB.recuperarposicionesuno(index);

                        String activity = "com.example.ik_2dm3.maps2."+posicion.get(0).getJuegonombre();
                        // String  activity = "com.example.ik_2dm3.maps2.TextoAudio0";
                        Intent intent= null;
                        try {
                            intent = new Intent(getBaseContext(), Class.forName(activity));

                            intent.putExtra("index", index);
                            startActivityForResult(intent, REQ_JUEGO6);
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }*/
                        Integer index = extras.getInt("index");
                        Intent juego = new Intent(TextoAudio.this, MainActivity_juegoimagenesdiferentes.class);
                        juego.putExtra("index", index);
                        /*if (audio.isPlaying()){
                            audio.stop();
                        }*/
                        startActivityForResult(juego, REQ_JUEGO7);
                    }
                });
                break;
            case 8:

                break;
        }
        /////////////////////////////////////////////////////////////////////////////////

    }

    public void onActivityResult (int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case REQ_JUEGO0:
                //dialog.dismiss();
                String  returnValue = data.getStringExtra("index");

                Basededatos MDB = new Basededatos(getApplicationContext());

                MDB.campiarposicion(parseInt(returnValue));

                Intent intent= new Intent(getBaseContext(),pantallacarga.class);
                // intent.putExtra("index", parseInt(returnValue));
                startActivity(intent);
                finish();
                break;
            case REQ_JUEGO1:
                if (resultCode==resultCode){
                    /*mostrar1.setText("");
                    //Reproducimos el audio
                    audio1 = MediaPlayer.create(TextoAudio1.this, R.raw.santaana2);
                    //audio1.setVolume(200,200);
                    audio1.start();
                    //Sacamos el texto palabra a palabra
                    texto1 = getResources().getString(R.string.gunea1_2);
                    pruebatexto1=texto1.split(" ");
                    mostrar1 = findViewById(R.id.txtHistoria1);
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ejecutar_hilo(mostrar1, pruebatexto1, boton_mostrar1, 850, dialogos1);
                        }
                    }, 1500);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            siguiente1.setVisibility(View.VISIBLE);
                        }
                    }, 7800);*/
                    returnValue = data.getStringExtra("index");
                    MDB = new Basededatos(getApplicationContext());
                    MDB.campiarposicion(parseInt(returnValue));
                    /*Intent intent= new Intent(getBaseContext(),pantallacarga.class);*/
                    Intent intent1= new Intent(getBaseContext(), MainActivity.class);
                    // intent.putExtra("index", parseInt(returnValue));
                    startActivity(intent1);
                    finish();
                }
                break;
            case REQ_JUEGO2:
                if (resultCode==RESULT_OK){
                    mostrar.setText("");
                    //Reproducimos el audio
                    audio = MediaPlayer.create(TextoAudio.this, R.raw.kurutziagados);
                    //audio2.setVolume(200,200);
                    audio.seekTo(49800);
                    //La siguiente parte del audio es en el segundo 49 +-
                    //Log.d("mytag", "Hola: " + audio2.getCurrentPosition());
                    audio.start();
                    //Sacamos el texto palabra a palabra
                    texto1 = getResources().getString(R.string.gunea2_2);
                    pruebatexto1=texto1.split(" ");
                    mostrar = findViewById(R.id.txtHistoria);
                    final Handler handler = new Handler();
                    ejecutar_hilo(mostrar, pruebatexto1, pruebatexto2,  pruebatexto3, 850, 1);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            audio.pause();
                        }
                    }, 10500);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            siguiente.setVisibility(View.VISIBLE);
                        }
                    }, 10800);
                    /*siguiente.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Bundle extras = getIntent().getExtras();
                            Integer index = extras.getInt("index");
                            Intent juego= new Intent(getBaseContext(),MainActivity_juegoMemoria.class);
                            juego.putExtra("index", index);
                            if (audio.isPlaying()){
                                audio.stop();
                            }
                            startActivity(juego);
                            finish();
                        }
                    });*/
                   /* returnValue = data.getStringExtra("index");
                    MDB = new Basededatos(getApplicationContext());
                    MDB.campiarposicion(parseInt(returnValue));
                    Intent intent= new Intent(getBaseContext(),pantallacarga.class)
                    Intent intent1= new Intent(getBaseContext(), MainActivity.class);
                    // intent.putExtra("index", parseInt(returnValue));
                    startActivity(intent1);
                    finish();*/
                }
                break;
            case REQ_JUEGO2_2:
                if (resultCode==RESULT_OK){
                    /////////////MIRAR PRUEBATEXTO1
                    siguiente.setVisibility(View.INVISIBLE);
                    mostrar.setText("");
                    //Reproducimos el audio

                    //audio3.setVolume(200,200);
                    audio.start();
                    //Sacamos el texto palabra a palabra
                    texto1 = getResources().getString(R.string.gunea2_3);
                    pruebatexto1=texto1.split(" ");
                    mostrar = findViewById(R.id.txtHistoria);

                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ejecutar_hilo(mostrar, pruebatexto1, pruebatexto2,  pruebatexto3, 720, 1);
                        }
                    }, 7300);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            siguiente.setVisibility(View.VISIBLE);
                        }
                    }, 22000);
                    siguiente.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Bundle extras = getIntent().getExtras();
                            Integer index = extras.getInt("index");


                            Basededatos MDB = new Basededatos(getApplicationContext());
                            MDB.campiarposicion(index);

                            Intent juego= new Intent(getBaseContext(),MainActivity.class);
                            juego.putExtra("index", index);
                            if (audio.isPlaying()){
                                audio.stop();
                            }
                            startActivity(juego);
                            finish();
                        }
                    });
                    finalizar = true;
                }
                break;
            case REQ_JUEGO3:
                if (resultCode==RESULT_OK){
                    ////////////MIRAR PRUEBATEXTO2
                    siguiente.setVisibility(View.INVISIBLE);
                    mostrar.setText("");
                    //Reproducimos el audio
                    audio = MediaPlayer.create(TextoAudio.this, R.raw.aldezaharra);
                    //audio3.setVolume(200,200);
                    audio.seekTo(29700);
                    //La siguiente parte del audio es en el segundo 59 +-
                    audio.start();
                    //Sacamos el texto palabra a palabra
                    texto1 = getResources().getString(R.string.gunea3_2);
                    pruebatexto1=texto1.split(" ");
                    mostrar = findViewById(R.id.txtHistoria);
                    final Handler handler = new Handler();
                    ejecutar_hilo(mostrar, pruebatexto1, pruebatexto2,  pruebatexto3, 880, 1);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            siguiente.setVisibility(View.VISIBLE);
                        }
                    }, 19850);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            audio.stop();
                        }
                    }, 19000);
                    siguiente.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Basededatos MDB = new Basededatos(getApplicationContext());

                            Bundle extras = getIntent().getExtras();

                            Integer index = extras.getInt("index");

                            ArrayList<Posiciones> posicion =  MDB.recuperarposicionesuno(index);

                            String activity = "com.example.ik_2dm3.maps2."+posicion.get(0).getJuegonombre();
                            // String  activity = "com.example.ik_2dm3.maps2.TextoAudio";
                            Intent intent= null;
                            try {
                                intent = new Intent(getBaseContext(), Class.forName(activity));

                                intent.putExtra("index", index);
                                startActivityForResult(intent, REQ_JUEGO3_2);
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }


                           /* Bundle extras = getIntent().getExtras();
                            Integer index = extras.getInt("index");
                            Intent juego = new Intent(TextoAudio.this, juegoAdivina.class);
                            juego.putExtra("index", index);*/


                            if (audio.isPlaying()){
                                audio.stop();
                            }
                           // startActivityForResult(juego, REQ_JUEGO3_2);

                        }
                    });

                }
                break;
            case REQ_JUEGO3_2:
                if (resultCode==RESULT_OK){
                    ImageView imgsapo;
                    imgsapo = findViewById(R.id.sapo);
                    imgsapo.setImageResource(R.drawable.goienkale);
                    siguiente.setVisibility(View.INVISIBLE);
                    mostrar.setText("");
                    final Handler handler3_2 = new Handler();
                    handler3_2.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //Reproducimos el audio
                            audio = MediaPlayer.create(TextoAudio.this, R.raw.aldezaharra2);
                            //audio3.setVolume(200,200);
                            audio.start();
                        }
                    }, 2);
                    //Sacamos el texto palabra a palabra
                    texto1 = getResources().getString(R.string.gunea3_3);
                    pruebatexto1=texto1.split(" ");
                    texto2 = getResources().getString(R.string.gunea3_4);
                    pruebatexto2=texto2.split(" ");
                    mostrar = findViewById(R.id.txtHistoria);
                    ejecutar_hilo(mostrar, pruebatexto1, pruebatexto2,  pruebatexto3, 585, 2);
                    handler3_2.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            siguiente.setVisibility(View.VISIBLE);
                            cambiartexto.setVisibility(View.VISIBLE);
                        }
                    }, 45700);
                    siguiente.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Bundle extras = getIntent().getExtras();
                            Integer index = extras.getInt("index");

                            Basededatos MDB = new Basededatos(getApplicationContext());
                            MDB.campiarposicion(index);

                            Intent juego = new Intent(TextoAudio.this, MainActivity.class);
                            juego.putExtra("index", index);
                            if (audio.isPlaying()){
                                audio.stop();
                            }
                            startActivityForResult(juego, REQ_JUEGO3_2);
                            segundo = false;
                        }
                    });
                    cambiartexto.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (primero){
                                mostrar.setText(texto2);
                                cambiartexto.setImageResource(R.drawable.flecha_iz);
                                primero=false;
                            }else{
                                mostrar.setText(texto1);
                                cambiartexto.setImageResource(R.drawable.flecha_der);
                                primero=true;
                            }
                        }
                    });
                }
                break;
            case REQ_JUEGO4:
                if (resultCode==RESULT_OK){
                    siguiente.setVisibility(View.INVISIBLE);
                    cambiartexto.setVisibility(View.INVISIBLE);
                    final Handler handler = new Handler();
                    mostrar.setText("");
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //Reproducimos el audio
                            ///audio = MediaPlayer.create(TextoAudio.this, R.raw.aldezaharra);
                            //audio3.setVolume(200,200);
                            ///audio.seekTo(52000);
                            //La siguiente parte del audio es en el segundo 59 +-
                            audio.start();
                        }
                    }, 2);
                    //Sacamos el texto palabra a palabra
                    texto1 = getResources().getString(R.string.gunea4_2);
                    pruebatexto1=texto1.split(" ");
                    mostrar = findViewById(R.id.txtHistoria);
                    ejecutar_hilo(mostrar, pruebatexto1, pruebatexto2,  pruebatexto3,570, 1);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            siguiente.setVisibility(View.VISIBLE);
                        }
                    }, 20000);
                    siguiente.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Bundle extras = getIntent().getExtras();
                            Integer index = extras.getInt("index");

                            Basededatos MDB = new Basededatos(getApplicationContext());
                            MDB.campiarposicion(index);

                            Intent juego = new Intent(TextoAudio.this, MainActivity.class);
                            juego.putExtra("index", index);
                            if (audio.isPlaying()){
                                audio.stop();
                            }
                            startActivity(juego);
                            segundo = false;
                        }
                    });
                }
                break;
            case REQ_JUEGO5:
                returnValue = data.getStringExtra("index");
                MDB = new Basededatos(getApplicationContext());
                MDB.campiarposicion(parseInt(returnValue));
                Intent intent5= new Intent(getBaseContext(),MainActivity.class);
                // intent.putExtra("index", parseInt(returnValue));
                startActivity(intent5);
                finish();
                break;
            case REQ_JUEGO6:
                //dialog.dismiss();
                returnValue = data.getStringExtra("index");
                MDB = new Basededatos(getApplicationContext());
                MDB.campiarposicion(parseInt(returnValue));
                Intent intent6 = new Intent(getBaseContext(), MainActivity.class);
                // intent.putExtra("index", parseInt(returnValue));
                startActivity(intent6);
                finish();
                break;
            case REQ_JUEGO7:
                returnValue = data.getStringExtra("index");
                MDB = new Basededatos(getApplicationContext());
                MDB.campiarposicion(parseInt(returnValue));
                Intent intent7 = new Intent(getBaseContext(), MainActivity.class);
                // intent.putExtra("index", parseInt(returnValue));
                startActivity(intent7);
                finish();
                break;
            case REQ_JUEGO8:
                returnValue = data.getStringExtra("index");
                MDB = new Basededatos(getApplicationContext());
                MDB.campiarposicion(parseInt(returnValue));
                Intent intent8 = new Intent(getBaseContext(), MainActivity.class);
                // intent.putExtra("index", parseInt(returnValue));
                startActivity(intent8);
                finish();
                break;
        }
    }
    public void ejecutar_hilo(TextView texto_pantalla, String[] palabras1, String[] palabras2, String[] palabras3, int tiempo, int numdialogos) {
        hilos2 hilo = new hilos2();
        hilo.txtview2 = texto_pantalla;
        hilo.palabras2_1 = palabras1;
        hilo.palabras2_2 = palabras2;
        hilo.palabras2_3 = palabras3;
        hilo.milisegundos2 = tiempo;
        hilo.dialogos = numdialogos;
        hilo.start();
    }
}
