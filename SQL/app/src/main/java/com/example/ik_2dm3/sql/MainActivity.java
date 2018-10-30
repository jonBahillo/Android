package com.example.ik_2dm3.sql;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView =(ImageView)findViewById(R.id.imageView2);

        Basededatos MDB = new Basededatos(getApplicationContext());

        String nombre = MDB.recuperarusuario(0);


        Log.d("myTag", "nombre "+ nombre);

        byte[] decodedString = Base64.decode(nombre, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString,
                0, decodedString.length);

        Log.d("myTag", "Bitmap "+ decodedByte);

        imageView.setImageBitmap(decodedByte);

        AlertDialog alertDialog = new AlertDialog.Builder(
                MainActivity.this).create();

        // Setting Dialog Title
        alertDialog.setTitle("Alert Dialog");

        // Setting Dialog Message
        alertDialog.setMessage("");

        // Setting Icon to Dialog
        alertDialog.setIcon(R.drawable.a);

        // Setting OK Button
        alertDialog.setButton(Dialog.BUTTON_POSITIVE,"OK",new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();

            }
        });

        alertDialog.setButton(Dialog.BUTTON_NEGATIVE,"cancel",new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {



            }
        });


        // Showing Alert Message
        alertDialog.show();





    }
}
