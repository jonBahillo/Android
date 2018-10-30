package com.example.ik_2dm3.sql;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Basededatos extends SQLiteOpenHelper {

    private final Object Context = this;
    private static final int DATABASE_VERSION = 1;
    private static String DB_PATH = "/data/data/com.example.ik_2dm3.sql/databases/";
    private static final String NOMBRE_BASEDATOS = "usuarios.db";
    // Nombre de nuestro archivo de base de datos
   // private static final String NOMBRE_BASEDATOS = "usuarios.db";


    // CONSTRUCTOR de la clase
    public Basededatos(Context context) {
      //  dbHelper = new miHelperBD(context, NOMBRE_BASEDATOS, null, DATABASE_VERSION);
        super(context, NOMBRE_BASEDATOS, null, DATABASE_VERSION);
        try{
            copyDataBase(context);
        } catch(IOException e){
            Log.d("mytag", "ERROR COPYDATABASE");
        }
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
       Log.d("myTag","adasdasdasda");
   /*
        // Open your local db as the input stream
        InputStream myInput = null;
        try {
            myInput = getContext().getAssets().open(NOMBRE_BASEDATOS);



        // Path to the just created empty db
        String outFileName = DB_PATH;

        // Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        // transfer bytes from the input file to the output file
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        // Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    private void copyDataBase(Context context) throws IOException {

        //String DB_NAME = "AnArbitraryName.db";
        String DB_PATH = context.getDatabasePath(NOMBRE_BASEDATOS).getPath();
        Log.d("mytag", DB_PATH);
        // Open your local db as the input stream
        InputStream myInput = context.getAssets().open(NOMBRE_BASEDATOS);


        // Path to the just created empty db
        String outFileName = DB_PATH;

        // Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        // transfer bytes from the input file to the output file
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        // Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();
        /*Static.getSharedPreference(context).edit()
                .putInt("DB_VERSION", DB_VERSION).commit();*/
    }


    public String recuperarusuario(int id){

        String myPath = DB_PATH + NOMBRE_BASEDATOS;
        SQLiteDatabase db = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);

        //Log.d("myTag", "db "+db);

        //SQLiteStatement stmt = db.compileStatement("SELECT name, sql FROM sqlite_master WHERE type='table' ORDER BY name");

        //Cursor c = db.rawQuery("SELECT * FROM usuario ", null);

        Cursor c = db.rawQuery("SELECT * FROM usuario", null);
        //Log.d("myTag", "c "+c);

        //SQLiteDatabase db = getReadableDatabase();



        /*String[] valores_recuperar = {"*"};
        Cursor c = db.query("usuario", valores_recuperar, "",
                null, null, null, null, null);*/



        //Cursor c = db.rawQuery("SELECT * FROM usuario", null);


       // Log.d("myTag", "c "+c);

       if(c != null) {
            c.moveToFirst();
        }
        String a = c.getString(2);
      //  Log.d("myTag", "string "+c.getString(3));
      //  Log.d("myTag", "count "+c.getCount());


        db.close();
        c.close();

        return a;
    }
}