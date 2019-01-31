package com.example.ik_2dm3.maps2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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

    String texto1;              //Variable 1 que guarda toda la frase
    String texto2;              //Variable 2 que guarda toda la frase
    String texto3;              //Variable 3 que guarda toda la frase
    String pantalla = "";
    String[] pruebatexto1;      //Variable 1 que gurada toda la frase palabra a palabra
    String[] pruebatexto2;      //Variable 2 que gurada toda la frase palabra a palabra
    String[] pruebatexto3;      //Variable 3 que gurada toda la frase palabra a palabra
    MediaPlayer audio;          //Reproductor de audio
    TextView mostrar;           //TextView donde saldran las frases
    TextView siguiente;         //TextView que funciona como boton para pasar a otro activity
    ImageView cambiartexto;     //ImageView que se utiliza cuando hay varios textos para moverse entre ellos
    ImageView cambiartexto2;    //ImageView que se utiliza cuando hay varios textos para moverse entre ellos
    boolean primero = false;
    boolean segundo = true;
    boolean finalizar = false;
    boolean textomayor = false;  //Se utiliza para saber si se ha elegido letra grande o normal
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
    int actividad;              //Se utiliza para indicar que actividad se tiene que ejecutar
    int vartexto = 3;
    BroadcastReceiver br = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)){
                Log.d("mytag", "A");
            } else if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)){
                audio.stop();
            }
        }
    };

    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        if (Build.VERSION.SDK_INT > 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        setContentView(R.layout.activity_texto_audio);
        LinearLayout linearlayout = findViewById(R.id.Fondoimagen);

        registerReceiver(br, new IntentFilter(Intent.ACTION_SCREEN_ON));
        registerReceiver(br, new IntentFilter(Intent.ACTION_SCREEN_OFF));

        Basededatos MDB = new Basededatos(getApplicationContext());
        final Bundle extras = getIntent().getExtras();
        Integer index = extras.getInt("index");
        ArrayList<Posiciones> posicion =  MDB.recuperarposicionesuno(index);

        // String  activity = "com.example.ik_2dm3.maps2.TextoAudio";
        byte[] decodedString = Base64.decode(posicion.get(0).getImgenpopup(), Base64.DEFAULT);

        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString,
                0, decodedString.length);

        BitmapDrawable drawable = new BitmapDrawable(getResources(), decodedByte);

        //Aumentar el tamaño de la letra
        //Cualquier numero que se ponga lo multiplica por 3
        /*if (textomayor){

        }*/

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
                final Handler handler = new Handler();
                if (!textomayor){
                    //Sacamos el texto palabra a palabra
                    texto1 = getResources().getString(R.string.gunea0_1);
                    texto2 = getResources().getString(R.string.gunea0_2);
                    //Separamos las palabras
                    pruebatexto1=texto1.split(" ");
                    pruebatexto2=texto2.split(" ");
                    mostrar = findViewById(R.id.txtHistoria);
                    mostrar.setTextSize(13);
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
                } else{
                    texto1 = getResources().getString(R.string.gunea0_1_1);
                    texto2 = getResources().getString(R.string.gunea0_1_2);
                    texto3 = getResources().getString(R.string.gunea0_2);
                    pruebatexto1=texto1.split(" ");
                    pruebatexto2=texto2.split(" ");
                    pruebatexto3=texto3.split(" ");
                    mostrar = findViewById(R.id.txtHistoria);
                    mostrar.setTextSize(18);
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
                            ejecutar_hilo(mostrar, pruebatexto1, pruebatexto2,  pruebatexto3, 850, 3);
                        }
                    }, 2900);
                    cambiartexto.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            switch (vartexto){
                                case 1:

                                    break;
                                case 2:
                                    mostrar.setText(texto1);
                                    cambiartexto.setVisibility(View.INVISIBLE);
                                    //cambiartexto.setImageResource(R.drawable.flecha_iz);
                                    cambiartexto2.setImageResource(R.drawable.flecha_der);
                                    vartexto = 1;
                                    break;
                                case 3:
                                    mostrar.setText(texto2);
                                    cambiartexto2.setVisibility(View.VISIBLE);
                                    cambiartexto2.setImageResource(R.drawable.flecha_der);
                                    vartexto = 2;
                                    break;
                            }
                        }
                    });
                    cambiartexto2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            switch (vartexto){
                                case 1:
                                    mostrar.setText(texto2);
                                    cambiartexto.setVisibility(View.VISIBLE);
                                    cambiartexto2.setImageResource(R.drawable.flecha_iz);
                                    cambiartexto2.setImageResource(R.drawable.flecha_der);
                                    vartexto = 2;
                                    break;
                                case 2:
                                    mostrar.setText(texto3);
                                    cambiartexto.setVisibility(View.VISIBLE);
                                    cambiartexto.setImageResource(R.drawable.flecha_iz);
                                    cambiartexto2.setVisibility(View.INVISIBLE);
                                    vartexto = 3;
                                    break;
                                case 3:

                                    break;
                            }
                        }
                    });
                }
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
                final Handler handler1 = new Handler();
                if (!textomayor){
                    //Sacamos el texto palabra a palabra
                    texto1 = getResources().getString(R.string.gunea1);
                    //texto2 = getResources().getString(R.string.gunea1_2);
                    //Separamos las palabras
                    pruebatexto1 = texto1.split(" ");
                    //pruebatexto2 = texto2.split(" ");
                    mostrar = findViewById(R.id.txtHistoria);
                    mostrar.setTextSize(13);
                    cambiartexto.setVisibility(View.INVISIBLE);
                    handler1.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ejecutar_hilo(mostrar, pruebatexto1, pruebatexto2,  pruebatexto3, 850, 1);
                        }
                    }, 4500);
                    handler1.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            siguiente.setVisibility(View.VISIBLE);
                            cambiartexto.setVisibility(View.VISIBLE);
                        }
                    }, 67100);//67100);
                    /*cambiartexto.setOnClickListener(new View.OnClickListener() {
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
                    });*/
                } else{
                    texto1 = getResources().getString(R.string.gunea1_1_1);
                    texto2 = getResources().getString(R.string.gunea1_1_2);
                    pruebatexto1=texto1.split(" ");
                    pruebatexto2=texto2.split(" ");
                    mostrar = findViewById(R.id.txtHistoria);
                    mostrar.setTextSize(18);
                    handler1.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            audio.pause();
                            siguiente.setVisibility(View.VISIBLE);
                            cambiartexto.setVisibility(View.VISIBLE);
                        }
                    }, 71500);
                    handler1.postDelayed(new Runnable() {
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
                }
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
                if (textomayor){
                    mostrar.setTextSize(18);
                } else{
                    mostrar.setTextSize(13);
                }
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
                        audio.pause();
                    }
                }, 29100);//30000);
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
                            juego.putExtra("popupcamara", false);
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
                if (textomayor){
                    mostrar.setTextSize(18);
                } else{
                    mostrar.setTextSize(13);
                }
                final Handler handler3 = new Handler();
                handler3.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ejecutar_hilo(mostrar, pruebatexto1, pruebatexto2,  pruebatexto3, 935, 1);
                    }
                }, 4100);
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
                final Handler handler4 = new Handler();
                mostrar = findViewById(R.id.txtHistoria);
                if (!textomayor){
                    mostrar.setTextSize(13);
                    //Sacamos el texto palabra a palabra
                    texto1 = getResources().getString(R.string.gunea4_1);
                    texto2 = getResources().getString(R.string.gunea4_2);
                    //Separamos las palabras
                    pruebatexto1=texto1.split(" ");
                    pruebatexto2=texto2.split(" ");
                    ejecutar_hilo(mostrar, pruebatexto1, pruebatexto2,  pruebatexto3, 570, 2);
                    handler4.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            audio.pause();
                            siguiente.setVisibility(View.VISIBLE);
                            cambiartexto.setVisibility(View.VISIBLE);
                        }
                    }, 51000);
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
                } else{
                    mostrar.setTextSize(18);
                    //Sacamos el texto palabra a palabra
                    texto1 = getResources().getString(R.string.gunea4_1);
                    texto2 = getResources().getString(R.string.gunea4_2_1);
                    texto3 = getResources().getString(R.string.gunea4_2_2);
                    //Separamos las palabras
                    pruebatexto1=texto1.split(" ");
                    pruebatexto2=texto2.split(" ");
                    pruebatexto3=texto3.split(" ");
                    ejecutar_hilo(mostrar, pruebatexto1, pruebatexto2,  pruebatexto3, 570, 3);
                    handler4.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            audio.pause();
                            siguiente.setVisibility(View.VISIBLE);
                            cambiartexto.setVisibility(View.VISIBLE);
                        }
                    }, 51000);
                    cambiartexto.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            switch (vartexto){
                                case 1:

                                    break;
                                case 2:
                                    mostrar.setText(texto1);
                                    cambiartexto.setVisibility(View.INVISIBLE);
                                    //cambiartexto.setImageResource(R.drawable.flecha_iz);
                                    cambiartexto2.setImageResource(R.drawable.flecha_der);
                                    vartexto = 1;
                                    break;
                                case 3:
                                    mostrar.setText(texto2);
                                    cambiartexto2.setVisibility(View.VISIBLE);
                                    cambiartexto2.setImageResource(R.drawable.flecha_der);
                                    vartexto = 2;
                                    break;
                            }
                        }
                    });
                    cambiartexto2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            switch (vartexto){
                                case 1:
                                    mostrar.setText(texto2);
                                    cambiartexto.setVisibility(View.VISIBLE);
                                    cambiartexto2.setImageResource(R.drawable.flecha_iz);
                                    cambiartexto2.setImageResource(R.drawable.flecha_der);
                                    vartexto = 2;
                                    break;
                                case 2:
                                    mostrar.setText(texto3);
                                    cambiartexto.setVisibility(View.VISIBLE);
                                    cambiartexto.setImageResource(R.drawable.flecha_iz);
                                    cambiartexto2.setVisibility(View.INVISIBLE);
                                    vartexto = 3;
                                    break;
                                case 3:

                                    break;
                            }
                        }
                    });
                }
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
                        juego.putExtra("popupcamara", false);
                        startActivityForResult(juego, REQ_JUEGO4);
                        segundo = false;
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
                if (textomayor){
                    mostrar.setTextSize(18);
                } else{
                    mostrar.setTextSize(13);
                }
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
                mostrar = findViewById(R.id.txtHistoria);
                if (!textomayor){
                    mostrar.setTextSize(13);
                    texto1 = getResources().getString(R.string.gunea6_1);
                    //Separamos las palabras
                    pruebatexto1=texto1.split(" ");
                    handler6.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ejecutar_hilo(mostrar, pruebatexto1, pruebatexto2,  pruebatexto3, 595, 1);
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
                }else{
                    mostrar.setTextSize(18);
                    //Sacamos el texto palabra a palabra
                    texto1 = getResources().getString(R.string.gunea6_1_1);
                    texto2 = getResources().getString(R.string.gunea6_1_2);
                    //Separamos las palabras
                    pruebatexto1 = texto1.split(" ");
                    pruebatexto2 = texto2.split(" ");
                    handler6.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ejecutar_hilo(mostrar, pruebatexto1, pruebatexto2,  pruebatexto3, 560, 2);
                        }
                    }, 2);
                    handler6.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            audio.stop();
                            siguiente.setVisibility(View.VISIBLE);
                            cambiartexto.setVisibility(View.VISIBLE);
                        }
                    }, 36000);
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
                break;
            case 7:
                final Handler handler7 = new Handler();
                //Sacamos el texto palabra a palabra

                mostrar = findViewById(R.id.txtHistoria);
                if (!textomayor){
                    mostrar.setTextSize(13);
                    texto1 = getResources().getString(R.string.gunea6_2);
                    //Separamos las palabras
                    pruebatexto1=texto1.split(" ");
                    texto2 = getResources().getString(R.string.gunea6_3);
                    //Separamos las palabras
                    pruebatexto2=texto2.split(" ");
                    handler7.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            audio = MediaPlayer.create(TextoAudio.this, R.raw.sanagustin2);
                            audio.start();
                            ejecutar_hilo(mostrar, pruebatexto1, pruebatexto2,  pruebatexto3, 590, 1);
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
                    }, 32000);
                    handler7.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            audio.stop();
                            audio.release();
                            siguiente.setVisibility(View.VISIBLE);
                            cambiartexto.setVisibility(View.VISIBLE);
                        }
                    }, 52500);
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
                } else{
                    mostrar.setTextSize(18);
                    texto1 = getResources().getString(R.string.gunea6_2_1);
                    pruebatexto1=texto1.split(" ");
                    texto2 = getResources().getString(R.string.gunea6_2_2);
                    pruebatexto2=texto2.split(" ");
                    texto3 = getResources().getString(R.string.gunea6_3);
                    pruebatexto3=texto3.split(" ");
                    handler7.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            audio = MediaPlayer.create(TextoAudio.this, R.raw.sanagustin2);
                            audio.start();
                        }
                    }, 2);
                    handler7.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ejecutar_hilo(mostrar, pruebatexto1, pruebatexto2,  pruebatexto3, 595, 2);
                        }
                    }, 1500);
                    handler7.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            audio.stop();
                            audio = MediaPlayer.create(TextoAudio.this, R.raw.sanagustin3);
                            audio.start();
                            ejecutar_hilo(mostrar, pruebatexto3, pruebatexto2,  pruebatexto1, 590, 1);
                        }
                    }, 36300);
                    handler7.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            audio.stop();
                            audio.release();
                            siguiente.setVisibility(View.VISIBLE);
                            cambiartexto.setVisibility(View.VISIBLE);
                        }
                    }, 52500);
                    cambiartexto.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            switch (vartexto){
                                case 1:

                                    break;
                                case 2:
                                    mostrar.setText(texto1);
                                    cambiartexto.setVisibility(View.INVISIBLE);
                                    //cambiartexto.setImageResource(R.drawable.flecha_iz);
                                    cambiartexto2.setImageResource(R.drawable.flecha_der);
                                    vartexto = 1;
                                    break;
                                case 3:
                                    mostrar.setText(texto2);
                                    cambiartexto2.setVisibility(View.VISIBLE);
                                    cambiartexto2.setImageResource(R.drawable.flecha_der);
                                    vartexto = 2;
                                    break;
                            }
                        }
                    });
                    cambiartexto2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            switch (vartexto){
                                case 1:
                                    mostrar.setText(texto2);
                                    cambiartexto.setVisibility(View.VISIBLE);
                                    cambiartexto2.setImageResource(R.drawable.flecha_iz);
                                    cambiartexto2.setImageResource(R.drawable.flecha_der);
                                    vartexto = 2;
                                    break;
                                case 2:
                                    mostrar.setText(texto3);
                                    cambiartexto.setVisibility(View.VISIBLE);
                                    cambiartexto.setImageResource(R.drawable.flecha_iz);
                                    cambiartexto2.setVisibility(View.INVISIBLE);
                                    vartexto = 3;
                                    break;
                                case 3:
                                    break;
                            }
                        }
                    });
                }
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
                Intent intentfin = new Intent(this, TextoAudiofinal.class);
                intentfin.putExtra("letra", textomayor);
                startActivityForResult(intentfin, REQ_JUEGO8);
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
                    if (textomayor){
                        mostrar.setTextSize(18);
                    } else{
                        mostrar.setTextSize(13);
                    }
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
                    if (textomayor){
                        mostrar.setTextSize(18);
                    } else{
                        mostrar.setTextSize(13);
                    }
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
                    if (textomayor){
                        mostrar.setTextSize(18);
                    } else{
                        mostrar.setTextSize(13);
                    }
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
                    mostrar = findViewById(R.id.txtHistoria);
                    if (!textomayor){
                        mostrar.setTextSize(13);
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
                        ejecutar_hilo(mostrar, pruebatexto1, pruebatexto2,  pruebatexto3, 585, 2);
                        handler3_2.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                siguiente.setVisibility(View.VISIBLE);
                                cambiartexto.setVisibility(View.VISIBLE);
                            }
                        }, 45700);
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
                    } else{
                        mostrar.setTextSize(18);
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
                        texto1 = getResources().getString(R.string.gunea3_3_1);
                        pruebatexto1=texto1.split(" ");
                        texto2 = getResources().getString(R.string.gunea3_3_2);
                        pruebatexto2=texto2.split(" ");
                        texto3 = getResources().getString(R.string.gunea3_4);
                        pruebatexto3=texto3.split(" ");
                        ejecutar_hilo(mostrar, pruebatexto1, pruebatexto2,  pruebatexto3, 590, 3);
                        handler3_2.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                siguiente.setVisibility(View.VISIBLE);
                                cambiartexto.setVisibility(View.VISIBLE);
                            }
                        }, 45700);
                        cambiartexto.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                switch (vartexto){
                                    case 1:

                                        break;
                                    case 2:
                                        mostrar.setText(texto1);
                                        cambiartexto.setVisibility(View.INVISIBLE);
                                        //cambiartexto.setImageResource(R.drawable.flecha_iz);
                                        cambiartexto2.setImageResource(R.drawable.flecha_der);
                                        vartexto = 1;
                                        break;
                                    case 3:
                                        mostrar.setText(texto2);
                                        cambiartexto2.setVisibility(View.VISIBLE);
                                        cambiartexto2.setImageResource(R.drawable.flecha_der);
                                        vartexto = 2;
                                        break;
                                }
                            }
                        });
                        cambiartexto2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                switch (vartexto){
                                    case 1:
                                        mostrar.setText(texto2);
                                        cambiartexto.setVisibility(View.VISIBLE);
                                        cambiartexto2.setImageResource(R.drawable.flecha_iz);
                                        cambiartexto2.setImageResource(R.drawable.flecha_der);
                                        vartexto = 2;
                                        break;
                                    case 2:
                                        mostrar.setText(texto3);
                                        cambiartexto.setVisibility(View.VISIBLE);
                                        cambiartexto.setImageResource(R.drawable.flecha_iz);
                                        cambiartexto2.setVisibility(View.INVISIBLE);
                                        vartexto = 3;
                                        break;
                                    case 3:
                                        break;
                                }
                            }
                        });
                    }
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
                    texto1 = getResources().getString(R.string.gunea4_3);
                    pruebatexto1=texto1.split(" ");
                    mostrar = findViewById(R.id.txtHistoria);
                    if (textomayor){
                        mostrar.setTextSize(18);
                    } else{
                        mostrar.setTextSize(13);
                    }
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
                //MDB.campiarposicion(parseInt(returnValue));
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
