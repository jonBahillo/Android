package com.example.ik_2dm3.maps2;

/**
 * Created by Jon Bahillo, Mikel Gamboa, Borja Bueno, Gorka Gomez on 23/10/2018.
 */
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import org.osmdroid.api.IMapController;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.Polyline;
import org.osmdroid.views.overlay.infowindow.BasicInfoWindow;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.Dialog;
import android.widget.Toast;

import static android.os.Build.VERSION_CODES.N;
import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity {
    ArrayList<OverlayItem> puntos = new ArrayList<>();
    ArrayList<OverlayItem> puntos2 = new ArrayList<>();

    private MapView myOpenMapView;
    private MapController myMapController;
    private GeoPoint posicionActual;
    boolean repetido = false;
    Dialog dialog;
    IMapController mapController;
    public boolean dialodobo=false;
    public int indexfinal;
    public String ordenfinal;

    Polyline line=new Polyline();
    Polyline linepasado =new Polyline();
    List<GeoPoint> pts = new ArrayList<>();
    List<GeoPoint> ptspasado = new ArrayList<>();

    private static final int REQ_JUEGO = 1;

    @Override
   protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        if (Build.VERSION.SDK_INT > 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }

        setContentView(R.layout.activity_main);

        if (tengoPermisoEscritura()) {
            cargarMapas();
        }
        dialog=new Dialog(this);
    }

    private void cargarMapas() {

        myOpenMapView = (MapView) findViewById(R.id.openmapview);

        myMapController = (MapController) myOpenMapView.getController();

        myOpenMapView.setMultiTouchControls(true);
       /* myMapController.setZoom(30);
        myOpenMapView.setMinZoomLevel(18);*/
        Basededatos MDB = new Basededatos(getApplicationContext());
        ArrayList<Posiciones> elegir =  MDB.recuperarposicionesuno(0);

        if (elegir.get(0).getPasado() != 0 ) {
            myMapController.setZoom(18);
            myOpenMapView.setMinZoomLevel(17);
        }else{
            myMapController.setZoom(17);
            myOpenMapView.setMinZoomLevel(16);
        }
        myOpenMapView.setUseDataConnection(false);
        myOpenMapView.setTilesScaledToDpi(true);


       /* if (elegir.get(0).getPasado() != 0 ) {
            ///////////////////////////////////
            //Centrar en la posición actual
           final MyLocationNewOverlay myLocationoverlay = new MyLocationNewOverlay(new GpsMyLocationProvider(getApplicationContext()), myOpenMapView);
            myOpenMapView.getOverlays().add(myLocationoverlay); //No añadir si no quieres una marca
            myLocationoverlay.enableMyLocation();
            myLocationoverlay.runOnFirstFix(new Runnable() {
                public void run() {
                    myMapController.animateTo(myLocationoverlay.getMyLocation());
                }
            });
        }*/

        /////////////////////////////////////////
        // Añadir un punto en el mapa
       // Basededatos MDB = new Basededatos(getApplicationContext());
        Integer pasado2 =  MDB.recuperarpasado();
        ArrayList<Posiciones> posicion =  MDB.recuperarposiciones();

        for(int i = 0; i < posicion.size(); i++){

            Double Latitud = Double.valueOf(posicion.get(i).getLati());
            Double Longitud = Double.valueOf(posicion.get(i).getLong());

            GeoPoint durango2 = new GeoPoint(Latitud, Longitud);

            ArrayList<Posiciones> elegir2 =  MDB.recuperarposicionesuno(0);

            if (elegir2.get(0).getPasado() != 0 ) {
                if (posicion.get(i).getOrden().equals(Integer.toString(pasado2))){
                    myMapController.setCenter(durango2);
                }
            }else{
                GeoPoint dura = new GeoPoint(43.16710, -2.63120);
                myMapController.setCenter(dura);
                /*Context context = getApplicationContext();
                Toast.makeText(context, "Hello world, I am a toast.", Toast.LENGTH_SHORT).show();*/

            }
            SharedPreferences myPreferences
                    = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
            /*Bundle extras = getIntent().getExtras();
            boolean geolocalizacion = extras.getBoolean("geolocalizacion");*/
            boolean geolocalizacion = myPreferences.getBoolean("geolocalizacion", true);

            //Log.d("mytag","geolocalizacion "+ geolocalizacion);
            if (geolocalizacion == true) {
                if (elegir.get(0).getPasado() != 0) {
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ///////////////////////////////////////////
                            //Centrar en la posición actual
                            final MyLocationNewOverlay myLocationoverlay = new MyLocationNewOverlay(new GpsMyLocationProvider(getApplicationContext()), myOpenMapView);
                            myOpenMapView.getOverlays().add(myLocationoverlay); //No añadir si no quieres una marca
                            myLocationoverlay.enableMyLocation();
                            myLocationoverlay.runOnFirstFix(new Runnable() {
                                public void run() {
                                    myMapController.animateTo(myLocationoverlay.getMyLocation());
                                }
                            });


                        }
                    }, 3700);
                }
            }
            OverlayItem durango = new OverlayItem(posicion.get(i).getNombre(), posicion.get(i).getOrden(), durango2);

            byte[] decodedString;
            if (posicion.get(i).getImagen() != null) {

                Integer pasado =  MDB.recuperarpasado();

           if (posicion.get(i).getNombre().equals("HASIERA") || posicion.get(i).getNombre().equals("Amaiera") || posicion.get(i).getOrden().equals(Integer.toString(pasado))){
               if(pasado == 13){
                   decodedString = Base64.decode(posicion.get(13).getImagen(), Base64.DEFAULT);
                   for (int e= 0; e < puntos.size(); e++){
                       if (puntos.get(e).getTitle().equals("HASIERA")){
                               puntos.remove(e);
                       }
                   }
               }else{
                   decodedString = Base64.decode(posicion.get(i).getImagen(), Base64.DEFAULT);
               }
              }else{
               decodedString = Base64.decode(posicion.get(i).getImagenpasado(), Base64.DEFAULT);
           }

            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString,
                    0, decodedString.length);

                Drawable d = new BitmapDrawable(getResources(), decodedByte);

                if (posicion.get(i).getPasado() == 1.0 && (!posicion.get(i).getOrden().equals("0"))){

                    durango.setMarker(getDrawable(R.drawable.icongreen));

                }else{
                    durango.setMarker(d);
                }
            }
            puntos.add(durango);
            refrescaPuntos();
        }

        /////////////////////////////////////////
        // Detectar cambios de ubicación mediante un listener (OSMUpdateLocation)
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        OSMUpdateLocation detectaPosicion = new OSMUpdateLocation(this);
        if (tengoPermisoUbicacion()) {
            Location ultimaPosicionConocida = null;
            for (String provider : locationManager.getProviders(true)) {
                if (Build.VERSION.SDK_INT >= 23 && checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
                    ultimaPosicionConocida = locationManager.getLastKnownLocation(provider);
                if (ultimaPosicionConocida != null) {

                    ArrayList<Posiciones> posicionuno =  MDB.recuperarposicionesuno(0);
                    if(posicionuno.get(0).getPasado() != 0.0){
                       // Log.d("mytag","getPasado "+posicionuno.get(0).getPasado());
                        actualizaPosicionActual(ultimaPosicionConocida);
                    }
                }
                //Pedir nuevas ubicaciones
                locationManager.requestLocationUpdates(provider, 0, 0, detectaPosicion);
                break;
            }
        } else {
            // No tengo permiso de ubicación :(
        }

        ArrayList<Rutas> rutas =  MDB.recuperarruta();
        Integer pasado =  MDB.recuperarpasado();

        for(int e = 0; e < rutas.size(); e++){

            ArrayList<Posiciones> posiciones =  MDB.recuperarposicionesuno(Integer.valueOf(rutas.get(e).getPosicion()));

            Double Latitud = Double.valueOf(rutas.get(e).getLatiRu());
            Double Longitud = Double.valueOf(rutas.get(e).getLongRu());
            int pasadonumero = 0;
            if (pasado != 13){
                 pasadonumero = pasado - 1;
            }else{
                pasadonumero =  pasado;
            }
                if(posiciones.get(0).getPasado() == 1 || posiciones.get(0).getOrden().equals(Integer.toString(pasadonumero))){
                    if (posiciones.get(0).getOrden().equals(Integer.toString(pasadonumero))){
                        ptspasado.add(new GeoPoint(Latitud, Longitud));
                        linepasado.setColor(Color.RED);
                        linepasado.setTitle("Bigarren gunea");
                        linepasado.setWidth(17f);
                        linepasado.setPoints(ptspasado);
                        linepasado.setGeodesic(true);
                        linepasado.setInfoWindow(new BasicInfoWindow(R.layout.bonuspack_bubble, myOpenMapView));

                        myOpenMapView.getOverlayManager().add(linepasado);

                    }else{
                        pts.add(new GeoPoint(Latitud, Longitud));
                        line.setColor(Color.GREEN);
                        line.setTitle("Lehen gunea");
                        line.setWidth(17f);
                        line.setPoints(pts);
                        line.setGeodesic(true);
                        line.setInfoWindow(new BasicInfoWindow(R.layout.bonuspack_bubble, myOpenMapView));

                        myOpenMapView.getOverlayManager().add(line);
                    }
                }
            }
        }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent();
            intent.setClass(this, this.getClass());
            startActivity(intent);
            finish();
        } else {
            // El usuario no ha dado permiso :(
        }
    }

    public boolean tengoPermisoEscritura() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        } else {
            return true;
        }
    }

    public boolean tengoPermisoUbicacion() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 2);
                return false;
            }
        } else {
            return true;
        }
    }

    public void actualizaPosicionActual(Location location) {
        dialog=new Dialog(this);
        Basededatos MDB = new Basededatos(getApplicationContext());
        posicionActual = new GeoPoint(location.getLatitude(), location.getLongitude());
        OverlayItem marcador = new OverlayItem("Estás aquí", "Posicion actual", posicionActual);
        marcador.setMarker(ResourcesCompat.getDrawable(getResources(), R.drawable.sapua, null));
        puntos.add(marcador);

        ArrayList<Posiciones> posicion =  MDB.recuperarposiciones();

        for(int A = 0; A < posicion.size(); A++) {

            Double Latitud = Double.valueOf(posicion.get(A).getLati());
            Double Longitud = Double.valueOf(posicion.get(A).getLong());

            GeoPoint distant = new GeoPoint(Latitud, Longitud);
            float distanceInMeters = posicionActual.distanceTo(distant);
            int index = A;
            String orden=puntos.get(index).getSnippet();
            if (distanceInMeters <=15 && posicion.get(A).getPasado() == 0.0){
                //Log.d("mytag","aaa "+distanceInMeters);
                if (orden != "Posicion actual") {
                    if (dialodobo == false) {
                        dialodobo = true;
                        indexfinal = index; ordenfinal = orden;
                        //Log.d("mytag", "primero ");
                        enseñarPopUp(null, index, orden);
                    }
                }
            }else if(dialodobo == true && indexfinal == A && ordenfinal.equals(orden)){
                //Log.d("mytag","a "+distanceInMeters);
                dialodobo = false;
            }
        }
        refrescaPuntos();
    }

    private void refrescaPuntos() {
        myOpenMapView.getOverlays().clear();
        ItemizedIconOverlay.OnItemGestureListener<OverlayItem> tap = new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
            @Override
            public boolean onItemLongPress(int arg0, OverlayItem arg1) {
                return false;
            }

            @Override
            public boolean onItemSingleTapUp(int index, OverlayItem item) {
                Basededatos MDB = new Basededatos(getApplicationContext());
                String orden=puntos.get(index).getSnippet();
                SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                boolean geolocalizacion = myPreferences.getBoolean("geolocalizacion", false);


                ArrayList<Posiciones> elegir =  MDB.recuperarposicionesuno(0);

                if (elegir.get(0).getPasado() == 0.0 ) {
                    //Log.d("mytag", "getPasado2 " + elegir.get(0).getPasado());
                    //Log.d("mytag", "segundo ");
                    enseñarPopUp(null, index, orden);
                }else {
                    if (orden != "Posicion actual") {

                        ArrayList<Posiciones> posicion = MDB.recuperarposicionesuno(index);

                        Double Latitud = Double.valueOf(posicion.get(0).getLati());
                        Double Longitud = Double.valueOf(posicion.get(0).getLong());

                        GeoPoint distant = new GeoPoint(Latitud, Longitud);
                        float distanceInMeters = posicionActual.distanceTo(distant);
                        //  Log.d("mytag", "distanceInMeters " + distanceInMeters +"geolocalizacion "+geolocalizacion+"getPasado "+posicion.get(0).getPasado() +"index "+index);
                        //|| (posicion.get(0).getPasado() == 0.0 && index != 0)
                        Log.d("mytag", "distanceInMeters " + distanceInMeters);
                        if (distanceInMeters <= 15 || geolocalizacion == false) {
                           // Log.d("mytag", "tercero ");
                            enseñarPopUp(null, index, orden);
                        }
                    }
                }
                return true;
            }
        };

        for (int e= 0; e < puntos.size(); e++){
            if (puntos.get(e).getSnippet().equals("Posicion actual")){
                if (repetido == true) {
                    puntos.remove(e);
                }else{
                    repetido = true;
                }
            }
        }

        myOpenMapView.getOverlayManager().add(line);
        myOpenMapView.getOverlayManager().add(linepasado);

        puntos2.clear();
        for (int m = puntos.size()-1; m >= 0; m--){
            puntos2.add(puntos.get(m));
           // Log.d("mytag", "punt "+ puntos.get(m).getSnippet());
        }

       /* for(int l=0;l < puntos2.size(); l++){
            Log.d("mytag", "puntos2 "+ puntos2.get(l).getSnippet());
        }
        Log.d("mytag","----------------------------------------------------");*/

        ItemizedOverlayWithFocus<OverlayItem> capa = new ItemizedOverlayWithFocus<>(this, puntos2, tap);
        myOpenMapView.getOverlays().add(capa);

    }

    public void enseñarPopUp(View v, final int i, String orden){
        Basededatos MDB = new Basededatos(getApplicationContext());
        Integer pasado =  MDB.recuperarpasado();

        ArrayList<Posiciones> elegir =  MDB.recuperarposicionesuno(0);

        if (elegir.get(0).getPasado() == 0 &&  i != 0 ){
            if (i != 13){
                TextView tvCerrar, txtmostrar;
                Button btnbai, btnez;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setContentView(R.layout.popup_ini);

                tvCerrar=(TextView)dialog.findViewById(R.id.txtCerrar);
                txtmostrar = (TextView)dialog.findViewById(R.id.txtmostrar);
                btnbai=(Button)dialog.findViewById(R.id.btnbai);
                btnez=(Button)dialog.findViewById(R.id.btnez);

                txtmostrar.setText("Hasi nahi duzu puntu honetatik?");

                tvCerrar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                btnbai.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Basededatos MDB = new Basededatos(getApplicationContext());
                    for (int x = 0; x < i ; x++){
                        MDB.campiarposicion(x);
                    }
                        Intent intent = new Intent(getBaseContext(), MainActivity.class);
                        startActivity(intent);
                        dialog.dismiss();
                    }
                });

                btnez.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        }

        if ( i != 0 && i <= pasado){
        TextView tvCerrar,tvNombre;
        Button btnJugar;
        TextView  textView ;

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.popup);

        tvCerrar=(TextView)dialog.findViewById(R.id.txtCerrar);
        tvNombre=(TextView)dialog.findViewById(R.id.nombresitio);
        btnJugar=(Button)dialog.findViewById(R.id.btnJuego);
        textView =(TextView )dialog.findViewById(R.id.nombresitio);

        final int index = i;
        ArrayList<Posiciones> posicion =  MDB.recuperarposicionesuno(i);

        tvNombre.setText(posicion.get(0).getNombre());

        if (posicion.get(0).getImgenpopup() != null ) {
            byte[] decodedString = Base64.decode(posicion.get(0).getImgenpopup(), Base64.DEFAULT);

            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString,
                    0, decodedString.length);

            BitmapDrawable drawableTop = new BitmapDrawable(getResources(), decodedByte);

            textView.setCompoundDrawablesWithIntrinsicBounds(null, drawableTop, null, null);

        }

        tvCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Basededatos MDB = new Basededatos(getApplicationContext());
                ArrayList<Posiciones> posicion =  MDB.recuperarposicionesuno(i);

               // String activity = "com.example.ik_2dm3.maps2."+posicion.get(0).getJuegonombre();
                String activity = "com.example.ik_2dm3.maps2."+posicion.get(0).getTexto();
               // String  activity = "com.example.ik_2dm3.maps2.TextoAudio0";
                Intent intent= null;

                try {

                intent = new Intent(getBaseContext(), Class.forName(activity));

                intent.putExtra("index", index);
                startActivityForResult(intent, REQ_JUEGO);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        if (!puntos.get(i).getSnippet().equals("Posicion actual")){
            dialog.show();
        }
        }else if(i != 0 ){
           // Log.d("mytag","pasado: "+pasado);
        }
    }

    public void onActivityResult (int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_JUEGO){
            dialog.dismiss();
            String  returnValue = data.getStringExtra("index");

            Basededatos MDB = new Basededatos(getApplicationContext());

            MDB.campiarposicion(parseInt(returnValue));

            Intent intent= new Intent(getBaseContext(),pantallacarga.class);
           // intent.putExtra("index", parseInt(returnValue));
            startActivity(intent);
            finish();

        }
    }

    public void settings(View v) {
        TextView tvCerrar;
        Button btnberriarazi, btnsalir;

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.setting);

        tvCerrar    =   (TextView)dialog.findViewById(R.id.txtCerrar);
        btnberriarazi =  (Button)dialog.findViewById(R.id.btnberriarazi);
        btnsalir =  (Button)dialog.findViewById(R.id.btnsalir);

        btnberriarazi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                Basededatos MDB = new Basededatos(getApplicationContext());

                    MDB.borrarbasedatos(getApplicationContext());
                    dialog.dismiss();
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        tvCerrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnsalir.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
               // dialog.dismiss();
                /*finish();
                System.exit(0);*/
                Intent intent = new Intent(getBaseContext(), MainActivity_ini.class);
                startActivity(intent);

            }
        });

        dialog.show();
}
}