package com.example.ik_2dm3.maps2;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class TextoAudio0 extends AppCompatActivity {

    String texto1;
    String texto2;
    String[] pruebatexto1;
    String[] pruebatexto2;
    MediaPlayer audio;
    TextView mostrar;
    TextView siguiente;
    ImageView cambiartexto;
    boolean primero = false;
    boolean segundo = true;
    boolean finalizar = false;
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
    int dialogos;
    int actividad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        if (Build.VERSION.SDK_INT > 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        setContentView(R.layout.activity_texto_audio0);
        siguiente=findViewById(R.id.txtSig);
        cambiartexto = findViewById(R.id.imgCambiartexto);
        siguiente.setVisibility(View.INVISIBLE);
        cambiartexto.setVisibility(View.INVISIBLE);
        switch (actividad) {
            case 0:
                //Reproducimos el audio
                audio = MediaPlayer.create(TextoAudio0.this, R.raw.sarrera);
                audio.start();
                //Sacamos el texto palabra a palabra
                texto1 = getResources().getString(R.string.gunea0_1);
                texto2 = getResources().getString(R.string.gunea0_2);
                //Separamos las palabras
                pruebatexto1=texto1.split(" ");
                pruebatexto2=texto2.split(" ");
                mostrar = findViewById(R.id.txtHistoria);
                dialogos = 2;
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
                        ejecutar_hilo(mostrar, pruebatexto1, pruebatexto2, 850, dialogos);
                    }
                }, 2900);
                cambiartexto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (primero){
                            mostrar.setText(texto1);
                            cambiartexto.setImageResource(R.drawable.flecha_iz);
                            primero=false;
                        }else{
                            mostrar.setText(texto2);
                            cambiartexto.setImageResource(R.drawable.flecha_der);
                            primero=true;
                        }
                    }
                });
                siguiente.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Basededatos MDB = new Basededatos(getApplicationContext());

                        Bundle extras = getIntent().getExtras();

                        Integer index = extras.getInt("index");

                        ArrayList<Posiciones> posicion =  MDB.recuperarposicionesuno(index);

                        String activity = "com.example.ik_2dm3.maps2."+posicion.get(0).getJuegonombre();
                        // String  activity = "com.example.ik_2dm3.maps2.TextoAudio0";
                        Intent intent= null;
                        try {
                            intent = new Intent(getBaseContext(), Class.forName(activity));

                            intent.putExtra("index", index);
                            startActivityForResult(intent, REQ_JUEGO0);
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }

                /*Intent juego = new Intent(TextoAudio0.this, pantallacarga.class);
                startActivityForResult(juego, REQ_JUEGO0);*/
                    }
                });
                break;
            case 1:
                /////////NO TIENE BOTON DE SIGUIENTE TEXTO
                //Reproducimos el audio
                audio = MediaPlayer.create(TextoAudio0.this, R.raw.santaana1);
                //audio1.setVolume(200,200);
                audio.start();
                //Sacamos el texto palabra a palabra
                texto1 = getResources().getString(R.string.gunea1);
                texto2 = "";
                //Separamos las palabras
                pruebatexto1 = texto1.split(" ");
                pruebatexto2 = texto2.split(" ");
                mostrar = findViewById(R.id.txtHistoria);
                dialogos = 1;
                final Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ejecutar_hilo(mostrar, pruebatexto1, pruebatexto2, 850, dialogos);
                    }
                }, 4500);
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        siguiente.setVisibility(View.VISIBLE);
                    }
                }, 67100);//67100);
                siguiente.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (primero){
                            primero=false;
                            if (audio.isPlaying()){
                                audio.stop();
                            }
                            Basededatos MDB = new Basededatos(getApplicationContext());
                            Bundle extras = getIntent().getExtras();
                            Integer index = extras.getInt("index");
                            ArrayList<Posiciones> posicion =  MDB.recuperarposicionesuno(index);
                            String activity = "com.example.ik_2dm3.maps2."+posicion.get(0).getJuegonombre();
                            // String  activity = "com.example.ik_2dm3.maps2.TextoAudio0";
                            Intent intent= null;
                            try {
                                intent = new Intent(getBaseContext(), Class.forName(activity));
                                intent.putExtra("index", index);
                                startActivityForResult(intent, REQ_JUEGO1);
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                    /*Intent juego = new Intent(TextoAudio1.this, pantallacarga2.class);
                    startActivityForResult(juego, REQ_JUEGO1);*/
                        }else{
                            primero=false;
                            Intent juego = new Intent(TextoAudio0.this, pantallacarga.class);
                            startActivityForResult(juego, REQ_JUEGO1);
                        }
                    }
                });
                break;
            case 2:
                /////////NO TIENE BOTON DE SIGUIENTE TEXTO
                //Reproducimos el audio
                audio = MediaPlayer.create(TextoAudio0.this, R.raw.kurutziagados);
                audio.start();
                if (audio.getCurrentPosition()==28000){
                    audio.pause();
                }
                //Sacamos el texto palabra a palabra
                texto1 = getResources().getString(R.string.gunea2_1);
                //Separamos las palabras
                pruebatexto1=texto1.split(" ");
                mostrar = findViewById(R.id.txtHistoria);
                dialogos = 1;
                final Handler handler2 = new Handler();
                handler2.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ejecutar_hilo(mostrar, pruebatexto1, pruebatexto2, 800, dialogos);
                    }
                }, 2500);
                handler2.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        siguiente.setVisibility(View.VISIBLE);
                    }
                }, 6800);//30000);
                handler2.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        audio.stop();
                    }
                }, 30300);
                siguiente.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (segundo){
                            Basededatos MDB = new Basededatos(getApplicationContext());
                            Bundle extras = getIntent().getExtras();
                            Integer index = extras.getInt("index");
                            ArrayList<Posiciones> posicion =  MDB.recuperarposicionesuno(index);
                            String activity = "com.example.ik_2dm3.maps2."+posicion.get(0).getJuegonombre();
                            // String  activity = "com.example.ik_2dm3.maps2.TextoAudio0";
                            Intent intent= null;
                            try {
                                intent = new Intent(getBaseContext(), Class.forName(activity));
                                intent.putExtra("index", index);
                                startActivityForResult(intent, REQ_JUEGO2);
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                            audio.pause();
                    /*Intent juego = new Intent(TextoAudio2.this, juegoAdivina.class);
                    startActivityForResult(juego, REQ_JUEGO2);*/
                            segundo = false;
                        }else{
                            Basededatos MDB = new Basededatos(getApplicationContext());

                            Bundle extras = getIntent().getExtras();

                            Integer index = extras.getInt("index");

                            ArrayList<Posiciones> posicion =  MDB.recuperarposicionesuno(index);

                            String activity = "com.example.ik_2dm3.maps2."+posicion.get(0).getJuegonombre();
                            // String  activity = "com.example.ik_2dm3.maps2.TextoAudio0";
                            Intent intent= null;
                            try {
                                intent = new Intent(TextoAudio0.this, Class.forName(activity));

                                intent.putExtra("index", index);
                                startActivityForResult(intent, REQ_JUEGO2_2);
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                    /*Intent juego = new Intent(TextoAudio2.this, pantallacarga2.class);
                    startActivityForResult(juego, REQ_JUEGO2_2);*/
                        }
                        if(finalizar){
                            Intent juego = new Intent(TextoAudio0.this, pantallacarga.class);
                            startActivityForResult(juego, REQ_JUEGO2_2);
                        }
                    }
                });
                break;
            case 3:
                /////////NO TIENE BOTON DE SIGUIENTE TEXTO
                //Reproducimos el audio
                audio = MediaPlayer.create(TextoAudio0.this, R.raw.aldezaharra);
                audio.start();
                //Sacamos el texto palabra a palabra
                texto1 = getResources().getString(R.string.gunea3_1);
                //Separamos las palabras
                pruebatexto1=texto1.split(" ");
                mostrar = findViewById(R.id.txtHistoria);
                dialogos = 1;
                final Handler handler3 = new Handler();
                handler3.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ejecutar_hilo(mostrar, pruebatexto1, pruebatexto2, 935, dialogos);
                    }
                }, 4000);

                siguiente.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (segundo){
                            Intent juego = new Intent(TextoAudio0.this, pantallacarga.class);
                            startActivityForResult(juego, REQ_JUEGO3);
                            segundo = false;
                        }else{
                            Intent juego = new Intent(TextoAudio0.this, pantallacarga.class);
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
                /////////MIRAR LOS PRUEBATEXTO
                /////////MIRAR HILO
                /////////TIENE BOTON DE SIGUIENTE TEXTO
                //Reproducimos el audio
                audio = MediaPlayer.create(TextoAudio0.this, R.raw.andramari);
                audio.start();
                if (audio.getCurrentPosition()==28000){
                    audio.stop(); //pausa
                }
                //Sacamos el texto palabra a palabra
                texto1 = getResources().getString(R.string.gunea4_1_1);
                //Separamos las palabras
                pruebatexto1=texto1.split(" ");
                texto2 = getResources().getString(R.string.gunea4_1_2);
                //Separamos las palabras
                pruebatexto2=texto2.split(" ");
                mostrar = findViewById(R.id.txtHistoria);
                dialogos = 2;
                ejecutar_hilo(mostrar, pruebatexto1, pruebatexto2, 570, dialogos);
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
                        Intent juego = new Intent(TextoAudio0.this, pantallacarga.class);
                        startActivityForResult(juego, REQ_JUEGO4);
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

                /////////MIRAR HILO
                /////////NO TIENE BOTON DE SIGUIENTE TEXTO
                //Reproducimos el audio
                audio = MediaPlayer.create(TextoAudio0.this, R.raw.ezkurdi);
                audio.start();
                //Sacamos el texto palabra a palabra
                texto1 = getResources().getString(R.string.gunea5);
                texto2 = "";
                //Separamos las palabras
                pruebatexto1 = texto1.split(" ");
                pruebatexto2 = texto2.split(" ");
                mostrar = findViewById(R.id.txtHistoria);
                dialogos = 1;
                ejecutar_hilo(mostrar, pruebatexto1, pruebatexto2, 600, dialogos);
                siguiente.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Basededatos MDB = new Basededatos(getApplicationContext());
                        Bundle extras = getIntent().getExtras();
                        Integer index = extras.getInt("index");
                        ArrayList<Posiciones> posicion =  MDB.recuperarposicionesuno(index);
                        String activity = "com.example.ik_2dm3.maps2."+posicion.get(0).getJuegonombre();
                        // String  activity = "com.example.ik_2dm3.maps2.TextoAudio0";
                        Intent intent= null;
                        try {
                            intent = new Intent(TextoAudio0.this, Class.forName(activity));
                            if (audio.isPlaying()){
                                audio.stop();
                            }
                            intent.putExtra("index", index);
                            startActivityForResult(intent, REQ_JUEGO5);
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                /*Intent juego = new Intent(TextoAudio5.this, juegoAdivina.class);
                if (audio5.isPlaying()){
                    audio5.stop();
                }
                startActivityForResult(juego, REQ_JUEGO);*/
                    }
                });
                break;
            case 6:
                /////////TIENE BOTON DE SIGUIENTE TEXTO
                //Reproducimos el audio
                audio = MediaPlayer.create(TextoAudio0.this, R.raw.sanagustin1);
                audio.start();
                //Sacamos el texto palabra a palabra
                texto1 = getResources().getString(R.string.gunea6_1);
                //Separamos las palabras
                pruebatexto1=texto1.split(" ");
                texto2 = getResources().getString(R.string.gunea4_1_2);
                //Separamos las palabras
                pruebatexto2=texto2.split(" ");
                mostrar = findViewById(R.id.txtHistoria);
                ejecutar_hilo(mostrar, pruebatexto1, pruebatexto2, 565, dialogos);
                dialogos = 2;
                final Handler handler6 = new Handler();
                handler6.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        audio.pause();
                        siguiente.setVisibility(View.VISIBLE);
                        cambiartexto.setVisibility(View.VISIBLE);
                    }
                }, 63200);
                siguiente.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Basededatos MDB = new Basededatos(getApplicationContext());

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
                        }
               /*Intent juego = new Intent(TextoAudio6.this, TextoAudio1.class);
                if (audio6.isPlaying()){
                    audio6.stop();
                }
                startActivityForResult(juego, REQ_JUEGO6);*/
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
                    Intent intent1= new Intent(getBaseContext(), MainActivity_camara.class);
                    // intent.putExtra("index", parseInt(returnValue));
                    startActivity(intent1);
                    finish();
                }
                break;
            case REQ_JUEGO2:
                if (resultCode==resultCode){
                    /*mostrar2.setText("");
                    //Reproducimos el audio
                    //audio2 = MediaPlayer.create(TextoAudio2.this, R.raw.kurutziaga);
                    //audio2.setVolume(200,200);
                    //audio2.seekTo(49600);
                    //La siguiente parte del audio es en el segundo 49 +-
                    //Log.d("mytag", "Hola: " + audio2.getCurrentPosition());
                    if (audio2!= null) {
                        audio2.stop();
                    }
                    audio2.start();

                    //Sacamos el texto palabra a palabra
                    texto2 = getResources().getString(R.string.gunea2_2);
                    pruebatexto2=texto2.split(" ");
                    mostrar2 = findViewById(R.id.txtHistoria2);
                    final Handler handler = new Handler();
                    ejecutar_hilo(mostrar2, pruebatexto2, boton_mostrar2, 850, dialogos2);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            audio2.pause();
                        }
                    }, 10500);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            siguiente2.setVisibility(View.VISIBLE);
                        }
                    }, 10800);*/
                    returnValue = data.getStringExtra("index");

                    MDB = new Basededatos(getApplicationContext());

                    MDB.campiarposicion(parseInt(returnValue));

                    Intent intent2= new Intent(getBaseContext(),pantallacarga.class);
                    // intent.putExtra("index", parseInt(returnValue));
                    startActivity(intent2);
                    finish();
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
                    texto2 = getResources().getString(R.string.gunea2_3);
                    pruebatexto2=texto2.split(" ");
                    mostrar = findViewById(R.id.txtHistoria);
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ejecutar_hilo(mostrar, pruebatexto1, pruebatexto2, 720, dialogos);
                        }
                    }, 7300);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            siguiente.setVisibility(View.VISIBLE);
                        }
                    }, 22000);
                    finalizar = true;
                }
                break;
            case REQ_JUEGO3:
                if (resultCode==RESULT_OK){
                    ////////////MIRAR PRUEBATEXTO2
                    siguiente.setVisibility(View.INVISIBLE);
                    mostrar.setText("");
                    //Reproducimos el audio
                    //audio3 = MediaPlayer.create(TextoAudio0.this, R.raw.aldezaharra);
                    //audio3.setVolume(200,200);
                    //audio3.seekTo(29700);
                    //La siguiente parte del audio es en el segundo 59 +-
                    audio.start();
                    //Sacamos el texto palabra a palabra
                    texto1 = getResources().getString(R.string.gunea3_2);
                    pruebatexto1=texto1.split(" ");
                    mostrar = findViewById(R.id.txtHistoria);
                    final Handler handler = new Handler();
                    ejecutar_hilo(mostrar, pruebatexto1, pruebatexto2, 880, dialogos);
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

                }
                break;
            case REQ_JUEGO3_2:
                if (resultCode==RESULT_OK){
                    ////////////MIRAR PRUEBATEXTO2
                    ImageView imgsapo;
                    imgsapo = findViewById(R.id.imageView3);
                    imgsapo.setImageResource(R.drawable.goienkale);
                    siguiente.setVisibility(View.INVISIBLE);
                    mostrar.setText("");
                    //Reproducimos el audio
                    audio = MediaPlayer.create(TextoAudio0.this, R.raw.aldezaharra2);
                    //audio3.setVolume(200,200);
                    audio.start();
                    //Sacamos el texto palabra a palabra
                    texto1 = getResources().getString(R.string.gunea3_3);
                    pruebatexto1=texto1.split(" ");
                    mostrar = findViewById(R.id.txtHistoria);
                    final Handler handler = new Handler();
                    ejecutar_hilo(mostrar, pruebatexto1, pruebatexto2, 575, dialogos);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            siguiente.setVisibility(View.VISIBLE);
                        }
                    }, 47200);
                }
                break;
            case REQ_JUEGO4:
                if (resultCode==RESULT_OK){
                    dialogos=1;
                    siguiente.setVisibility(View.INVISIBLE);
                    cambiartexto.setVisibility(View.INVISIBLE);
                    mostrar.setText("");
                    //Reproducimos el audio
                    //audio4 = MediaPlayer.create(TextoAudio4.this, R.raw.aldezaharra);
                    //audio3.setVolume(200,200);
                    //audio4.seekTo(52000);
                    //La siguiente parte del audio es en el segundo 59 +-
                    audio.start();
                    //Sacamos el texto palabra a palabra
                    texto1 = getResources().getString(R.string.gunea4_2);
                    pruebatexto1=texto1.split(" ");
                    mostrar = findViewById(R.id.txtHistoria);
                    final Handler handler = new Handler();
                    ejecutar_hilo(mostrar, pruebatexto1, pruebatexto2, 570, dialogos);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            siguiente.setVisibility(View.VISIBLE);
                        }
                    }, 20000);
                }
                break;
            case REQ_JUEGO5:
                //dialog.dismiss();
                returnValue = data.getStringExtra("index");

                MDB = new Basededatos(getApplicationContext());

                MDB.campiarposicion(parseInt(returnValue));

                Intent intent5 = new Intent(getBaseContext(), pantallacarga.class);
                // intent.putExtra("index", parseInt(returnValue));
                startActivity(intent5);
                finish();
                break;
            case REQ_JUEGO6:
                //dialog.dismiss();
                returnValue = data.getStringExtra("index");

                MDB = new Basededatos(getApplicationContext());

                MDB.campiarposicion(parseInt(returnValue));

                Intent intent6 = new Intent(getBaseContext(), pantallacarga.class);
                // intent.putExtra("index", parseInt(returnValue));
                startActivity(intent6);
                finish();
                break;
            case REQ_JUEGO7:

                break;
        }
    }
    public void ejecutar_hilo(TextView texto_pantalla, String[] palabras1, String[] palabras2, int tiempo, int numdialogos) {
        hilos2 hilo = new hilos2();
        hilo.txtview2 = texto_pantalla;
        hilo.palabras2_1 = palabras1;
        hilo.palabras2_2 = palabras2;
        hilo.milisegundos2 = tiempo;
        hilo.dialogos = numdialogos;
        hilo.start();
    }
}
