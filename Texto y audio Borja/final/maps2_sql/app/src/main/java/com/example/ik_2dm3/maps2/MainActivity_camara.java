package com.example.ik_2dm3.maps2;

import android.Manifest;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.os.Environment.getExternalStorageDirectory;

public class MainActivity_camara extends AppCompatActivity {

    ImageView imagen, imagenCompara;
    Button btnSacarFoto, btnleerFoto, btnvolver;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    Uri image_uri;
    int numFotos=0;
    Dialog dialogo;
    String nombreGrupo="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_camara);



        dialogo = new Dialog(this);
        nombreGrupo = "lagun";
        //popup(null);

        tengoPermisoEscritura();
        //imagen = (ImageView) findViewById(R.id.imagenSacada);
      //  imagenCompara = (ImageView) findViewById(R.id.imagenAComparar);
        btnSacarFoto = (Button) findViewById(R.id.btnFoto);
        btnvolver = (Button) findViewById(R.id.btnvolver);
        //btnleerFoto= (Button) findViewById(R.id.leerFoto);

        btnSacarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrircamara();


            }
        });

        btnvolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle extras = getIntent().getExtras();
                Integer index = extras.getInt("index");
                Intent salir = new Intent(getBaseContext(), TextoAudio.class);
                salir.putExtra("index", index.toString());
                setResult(RESULT_OK, salir);
                dialogo.dismiss();
                finish();
            }
        });

       /* btnleerFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });*/
    }

    private void llamarIntent(){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager())!=null){
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);



        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){



    }

    private void abrircamara(){

        File f = new File(getExternalStorageDirectory()+"/Pictures");
        if (!f.exists()){
            f.mkdir();
        }



        String tiempo= new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss").format(new Date());



        int numFotos=0;
        ContentValues values= new ContentValues();
        values.put(MediaStore.Images.Media.TITLE,"foto1");
        values.put(MediaStore.Images.Media.DESCRIPTION,"foto1");
        values.put(MediaStore.Images.Media.DATA,getExternalStorageDirectory()+"/Pictures/"+nombreGrupo+"/"+tiempo+".png");
        Log.d("mytag",""+getExternalStorageDirectory());



        image_uri=getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        Intent cameraintent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraintent.putExtra(MediaStore.EXTRA_OUTPUT,image_uri);
        startActivityForResult(cameraintent, REQUEST_IMAGE_CAPTURE);


    }


    public boolean tengoPermisoEscritura() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        } else {
            return true;
        }
    }

    /*public void popup (View v){
        Button Aurrera;
        final EditText input;
        dialogo.setContentView(R.layout.popup_imagen);
        input = dialogo.findViewById(R.id.editText);
        dialogo.setCancelable(false);
        dialogo.setCanceledOnTouchOutside(false);
        Aurrera = dialogo.findViewById(R.id.btnAurrera);
        Aurrera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombreGrupo=input.getText().toString();
                Log.d("mytag","input: "+nombreGrupo);
                File dir= new File(getExternalStorageDirectory()+"/Pictures",nombreGrupo);
                Log.d("mytag","el directorio: "+dir.toString());

                if (dir.exists()){
                    Toast.makeText(getBaseContext(),"Izen hori ahutatuta dago, aukeratu beste bat!", Toast.LENGTH_SHORT).show();
                }else{
                    dir.mkdir();
                    dialogo.dismiss();
                }

            }
        });
        dialogo.show();
    }*/

    private void scanMedia(String path) {
        File file = new File(path);
        Uri uri = Uri.fromFile(file);
        Intent scanFileIntent = new Intent(
                Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri);
        sendBroadcast(scanFileIntent);
    }
}
