package com.example.ik_2dm3.maps2;


import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import static android.opengl.Matrix.length;

public class Basededatos extends SQLiteOpenHelper {
    private final Object Context = this;
    private static final int DATABASE_VERSION = 1;
    private static String DB_PATH = "/data/data/com.example.ik_2dm3.maps2/databases/";
    private static final String NOMBRE_BASEDATOS = "database.db";


    public Basededatos(Context context) {
        super(context, NOMBRE_BASEDATOS, null, DATABASE_VERSION);
       try{
            copyDataBase(context);
        } catch(IOException e){
            Log.d("mytag", "ERROR COPYDATABASE");
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    private void copyDataBase(Context context) throws IOException {

        File file = new File(DB_PATH);
        if(file.exists()){
            String DB_PATH = context.getDatabasePath(NOMBRE_BASEDATOS).getPath();

            // Open your local db as the input stream
            InputStream myInput = context.getAssets().open(NOMBRE_BASEDATOS);

            // Path to the just created empty db
            String outFileName = DB_PATH;

            // Open the empty db as the output stream
            OutputStream myOutput = new FileOutputStream(outFileName);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }
            // Close the streams
            myOutput.flush();
            myOutput.close();
            myInput.close();

        }
       }



    public ArrayList<Posiciones> recuperarposiciones(){
        ArrayList<Posiciones> posiciones = new ArrayList<Posiciones>();

        String myPath = DB_PATH + NOMBRE_BASEDATOS;
        SQLiteDatabase db = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);

        Cursor c = db.rawQuery("SELECT * FROM Posiciones", null);

       if(c != null) {
            c.moveToFirst();
        }

        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            String ID = c.getString(c.getColumnIndex("ID"));
            String Nombre = c.getString(c.getColumnIndex("Descripcion"));
            String Long =  c.getString(c.getColumnIndex("Long"));
            String Lati =  c.getString(c.getColumnIndex("Lati"));
            String Orden =  c.getString(c.getColumnIndex("Orden"));
            String Imagen = c.getString(c.getColumnIndex("Imagen"));
            String Juego =  c.getString(c.getColumnIndex("Juego"));
            double Pasado = c.getDouble(c.getColumnIndex("Pasado"));
            String Imagenpasado =  c.getString(c.getColumnIndex("Imagenpasado"));
            String Imgenpopup =  c.getString(c.getColumnIndex("Imgenpopup"));

            Posiciones posicion = new Posiciones(ID,Nombre,Long,Lati,Orden,Imagen,Juego,Pasado,Imagenpasado,Imgenpopup,"");

            posiciones.add(posicion);
        }

        db.close();
        c.close();

        return posiciones;
    }

    public ArrayList<Posiciones> recuperarposicionesuno(int IDentrada){
        ArrayList<Posiciones> posiciones = new ArrayList<Posiciones>();

        String myPath = DB_PATH + NOMBRE_BASEDATOS;
        SQLiteDatabase db = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);

        Cursor c = db.rawQuery("SELECT * FROM Posiciones, Juegos where Orden ='"+IDentrada+"'", null);

        if(c != null) {
            c.moveToFirst();
        }

        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            String ID = c.getString(c.getColumnIndex("ID"));
            String Nombre = c.getString(c.getColumnIndex("Descripcion"));
            String Long =  c.getString(c.getColumnIndex("Long"));
            String Lati =  c.getString(c.getColumnIndex("Lati"));
            String Orden =  c.getString(c.getColumnIndex("Orden"));
            String Imagen = c.getString(c.getColumnIndex("Imagen"));
            String Juego =  c.getString(c.getColumnIndex("Juego"));
            double Pasado = c.getDouble(c.getColumnIndex("Pasado"));
            String Imagenpasado =  c.getString(c.getColumnIndex("Imagenpasado"));
            String Imgenpopup =  c.getString(c.getColumnIndex("Imgenpopup"));
            String Juegonombre =  c.getString(c.getColumnIndex("Clase"));

            Posiciones posicion = new Posiciones(ID,Nombre,Long,Lati,Orden,Imagen,Juego,Pasado,Imagenpasado,Imgenpopup,Juegonombre);

            posiciones.add(posicion);
        }

        db.close();
        c.close();

        return posiciones;
    }

    public int  recuperarpasado(){

        String myPath = DB_PATH + NOMBRE_BASEDATOS;
        SQLiteDatabase db = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);

        Cursor c = db.rawQuery("SELECT * FROM Posiciones where Pasado = 1", null);

        if(c != null) {
            c.moveToFirst();
        }

        int count = c.getCount();

        db.close();
        c.close();

        return count;
    }
    public void campiarposicion(int id){

        String myPath = DB_PATH + NOMBRE_BASEDATOS;
        SQLiteDatabase db = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);

        ContentValues cv = new ContentValues();
        cv.put("Pasado",1);

        db.update("Posiciones", cv, "ID="+id, null);

        db.close();

    }

}