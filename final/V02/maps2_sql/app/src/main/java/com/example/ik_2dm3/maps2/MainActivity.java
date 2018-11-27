package com.example.ik_2dm3.maps2;

/**
 * Created by Jon Bahillo, Mikel Gamboa, Borja Bueno, Gorka Gomez on 23/10/2018.
 */
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
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
import org.osmdroid.tileprovider.MapTileProviderBasic;
import org.osmdroid.tileprovider.modules.OfflineTileProvider;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.tileprovider.tilesource.XYTileSource;
import org.osmdroid.tileprovider.util.SimpleRegisterReceiver;
import org.osmdroid.util.BoundingBox;
import org.osmdroid.util.BoundingBoxE6;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.Overlay;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.PathOverlay;
import org.osmdroid.views.overlay.Polyline;
import org.osmdroid.views.overlay.TilesOverlay;
import org.osmdroid.views.overlay.compass.CompassOverlay;
import org.osmdroid.views.overlay.compass.InternalCompassOrientationProvider;
import org.osmdroid.views.overlay.infowindow.BasicInfoWindow;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Dialog;
import static java.lang.Integer.parseInt;
import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {
    ArrayList<OverlayItem> puntos = new ArrayList<>();
    private MapView myOpenMapView;
    private MapController myMapController;
    private GeoPoint posicionActual;
    boolean repetido = false;
    Dialog dialog;
    IMapController mapController;
    List<GeoPoint> pts = new ArrayList<>();
    protected Paint mPaint = new Paint();


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
            myOpenMapView = (MapView) findViewById(R.id.openmapview);



            myMapController = (MapController) myOpenMapView.getController();


            myOpenMapView.setMultiTouchControls(true);
        /*myMapController.setZoom(30);
        myOpenMapView.setMinZoomLevel(18);*/

            myMapController.setZoom(30);
            myOpenMapView.setMinZoomLevel(10);
            //myOpenMapView.setUseDataConnection(false);
            myOpenMapView.setTilesScaledToDpi(true);

        }


        dialog=new Dialog(this);




        if (tengoPermisoEscritura()) {

            cargaPuntos();
        }

    }



    private void cargarMapas() {









       /* CompassOverlay comOverlay = new CompassOverlay(this, new InternalCompassOrientationProvider(getApplicationContext()), myOpenMapView);
        comOverlay.enableCompass();
        myOpenMapView.getOverlays().add(comOverlay);*/


        // pts.add(new GeoPoint(43.16814, -2.63781));
        //pts.add(new GeoPoint(43.17073, -2.63596));


        ///////////////////////////////////
        //Centrar en la posición actual
        /*final MyLocationNewOverlay myLocationoverlay = new MyLocationNewOverlay(new GpsMyLocationProvider(getApplicationContext()), myOpenMapView);
        myOpenMapView.getOverlays().add(myLocationoverlay); //No añadir si no quieres una marca
        myLocationoverlay.enableMyLocation();
        myLocationoverlay.runOnFirstFix(new Runnable() {
            public void run() {
                myMapController.animateTo(myLocationoverlay.getMyLocation());
            }
        });*/

        /////////////////////////////////////////
        // Añadir un punto en el mapa
        Basededatos MDB = new Basededatos(getApplicationContext());
        Integer pasado2 =  MDB.recuperarpasado();
        ArrayList<Posiciones> posicion =  MDB.recuperarposiciones();

        for(int i = 0; i < posicion.size(); i++){

            Double Latitud = Double.valueOf(posicion.get(i).getLati());
            Double Longitud = Double.valueOf(posicion.get(i).getLong());

            GeoPoint durango2 = new GeoPoint(Latitud, Longitud);

            if (posicion.get(i).getOrden().equals(Integer.toString(pasado2))){
               // myMapController.setCenter(durango2);
            }

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
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
                }
            },2000);

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

                if (posicion.get(i).getPasado() == 1 && (!posicion.get(i).getOrden().equals("0"))){

                        durango.setMarker(getDrawable(R.drawable.icongreen));

                }else{
                    durango.setMarker(d);
                }
            }
            puntos.add(durango);
            refrescaPuntos();
        }




        ////////////////////////////////////
        /*Polyline line = new Polyline();
        line.setWidth(20f);
        line.setPoints(puntos2);
        line.setGeodesic(true);
        myOpenMapView.getOverlayManager().add(line);*/
        ////////////////////////////////////


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
                    actualizaPosicionActual(ultimaPosicionConocida);
                }
                //Pedir nuevas ubicaciones
                locationManager.requestLocationUpdates(provider, 0, 0, detectaPosicion);
                break;
            }
        } else {
            // No tengo permiso de ubicación
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
            // El usuario no ha dado permiso
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
       /* posicionActual = new GeoPoint(location.getLatitude(), location.getLongitude());
        OverlayItem marcador = new OverlayItem("Estás aquí", "Posicion actual", posicionActual);
        marcador.setMarker(ResourcesCompat.getDrawable(getResources(), R.drawable.sapua, null));

        puntos.add(marcador);*/
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
                String orden=puntos.get(index).getSnippet();
                enseñarPopUp(null, index, orden);
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
        ruta();
        ItemizedOverlayWithFocus<OverlayItem> capa = new ItemizedOverlayWithFocus<>(this, puntos, tap);
        myOpenMapView.getOverlays().add(capa);


    }
    public void cargaPuntos(){


        cargarMapas();
        refrescaRuta();
    }

    private void refrescaRuta() {

        //ruta 1
        pts.add(new GeoPoint(43.168050, -2.637930));
        pts.add(new GeoPoint(43.167420, -2.636408));
        pts.add(new GeoPoint(43.167100, -2.635620));
        pts.add(new GeoPoint(43.16697, -2.63523));
        pts.add(new GeoPoint(43.16686, -2.63486));
        pts.add(new GeoPoint(43.16656, -2.63314));
        pts.add(new GeoPoint(43.16651,-2.63301));
        pts.add(new GeoPoint(43.16651,-2.63301));
        pts.add(new GeoPoint(43.16562,-2.63289));
        pts.add(new GeoPoint(43.16478, -2.63259));
        pts.add(new GeoPoint(43.1648, -2.6325));
        pts.add(new GeoPoint(43.16539, -2.63238));
        pts.add(new GeoPoint(43.16569, -2.63226));
        pts.add(new GeoPoint(43.16575, -2.63212));


        //ruta 2
        pts.add(new GeoPoint(43.16575, -2.63212));
        pts.add(new GeoPoint(43.16571, -2.63172));
        pts.add(new GeoPoint(43.16563, -2.63165));
        pts.add(new GeoPoint(43.16556, -2.63164));
        pts.add(new GeoPoint(43.16541,-2.63140));
        pts.add(new GeoPoint(43.16552,-2.63122));
        pts.add(new GeoPoint(43.16556,-2.63077));
        pts.add(new GeoPoint(43.16582,-2.63061));

        //ruta 3
        pts.add(new GeoPoint(43.16582,-2.63061));
        pts.add(new GeoPoint(43.16645,-2.63025));
        pts.add(new GeoPoint(43.16659,-2.63021));
        pts.add(new GeoPoint(43.16789,-2.62963));
        pts.add(new GeoPoint(43.16794,-2.62963));
        pts.add(new GeoPoint(43.16799,-2.62940));

        //ruta 4
        pts.add(new GeoPoint(43.16799,-2.62940));
        pts.add(new GeoPoint(43.16794, -2.62964));
        pts.add(new GeoPoint(43.16793, -2.62975));
        pts.add(new GeoPoint(43.16782,-2.63035));
        pts.add(new GeoPoint(43.16775,-2.63034));

        //ruta 5
        pts.add(new GeoPoint(43.16775,-2.63034));
        pts.add(new GeoPoint(43.16782,-2.63035));
        pts.add(new GeoPoint(43.16779,-2.63065));
        pts.add(new GeoPoint(43.16775,-2.63079));
        pts.add(new GeoPoint(43.16769,-2.63087));
        pts.add(new GeoPoint(43.16762,-2.63090));


        //ruta 6
        pts.add(new GeoPoint(43.16762,-2.63090));
        pts.add(new GeoPoint(43.16764,-2.63090));
        pts.add(new GeoPoint(43.16763, -2.63097));
        pts.add(new GeoPoint(43.16770,-2.63137));
        pts.add(new GeoPoint(43.16758,-2.63142));

        //ruta 7
        pts.add(new GeoPoint(43.16758,-2.63142));
        pts.add(new GeoPoint(43.16770,-2.63137));
        pts.add(new GeoPoint(43.16780,-2.63183));
        pts.add(new GeoPoint(43.16768,-2.63189));

        //ruta 8
        pts.add(new GeoPoint(43.16768,-2.63189));
        pts.add(new GeoPoint(43.16780,-2.63183));
        pts.add(new GeoPoint(43.16774,-2.63153));
        pts.add(new GeoPoint(43.16786,-2.63162));
        pts.add(new GeoPoint(43.16809,-2.63171));

        //ruta 9
        pts.add(new GeoPoint(43.16809,-2.63171));
        pts.add(new GeoPoint(43.16816,-2.63186));
        pts.add(new GeoPoint(43.16827,-2.63236));
        pts.add(new GeoPoint(43.16900,-2.63237));

        //ruta 10
        pts.add(new GeoPoint(43.16900,-2.63237));
        pts.add(new GeoPoint(43.16827,-2.63236));
        pts.add(new GeoPoint(43.16820,-2.63238));
        pts.add(new GeoPoint(43.16750,-2.63266));

        //ruta 11
        pts.add(new GeoPoint(43.16750,-2.63266));
        pts.add(new GeoPoint(43.16722,-2.63278));

        //ruta 12
        pts.add(new GeoPoint(43.16722,-2.63278));
        pts.add(new GeoPoint(43.16683,-2.63291));

        //ruta 13
        pts.add(new GeoPoint(43.16683,-2.63291));
        pts.add(new GeoPoint(43.16683,-2.63291));

        //ruta 14
        pts.add(new GeoPoint(43.16651,-2.63301));
        pts.add(new GeoPoint(43.16656, -2.63314));
        pts.add(new GeoPoint(43.16686, -2.63486));
        pts.add(new GeoPoint(43.16697, -2.63523));
        pts.add(new GeoPoint(43.167100, -2.635620));
        pts.add(new GeoPoint(43.167420, -2.636408));
        pts.add(new GeoPoint(43.168050, -2.637930));










        GeoPoint durango2 = new GeoPoint(43.16656, -2.63314);


        myMapController.setCenter(durango2);



    }


    public void enseñarPopUp(View v, final int i, String orden){
        Basededatos MDB = new Basededatos(getApplicationContext());
        Integer pasado =  MDB.recuperarpasado();

      //  if (i != 0 && i == pasado){
        if ( i != 0 && i <= pasado){
        TextView tvCerrar,tvNombre;
        Button btnJugar;
        TextView  textView ;

        dialog.setContentView(R.layout.popup);
        tvCerrar=(TextView)dialog.findViewById(R.id.txtCerrar);
        tvNombre=(TextView)dialog.findViewById(R.id.nombresitio);
        btnJugar=(Button)dialog.findViewById(R.id.btnJuego);
        textView =(TextView )dialog.findViewById(R.id.nombresitio);

        //Log.d("mytag","index: "+i);

        final int index = i;
        ArrayList<Posiciones> posicion =  MDB.recuperarposicionesuno(i);

        tvNombre.setText(posicion.get(0).getNombre());

       // if (posicion.get(0).getImagen() != null && posicion.get(0).getPasado() == 0) {
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

                String activity = "com.example.ik_2dm3.maps2."+posicion.get(0).getJuegonombre();

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
            Log.d("mytag","pasado: "+pasado);
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
            startActivity(intent);
        }
    }
    public void ruta(){
        Polyline line=new Polyline();

        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));


        line.setColor(color);
        line.setTitle("Lehenengo gunea: Santa anako arkua");
        line.setWidth(17f);
        line.setPoints(pts);
        line.setGeodesic(true);
        line.setInfoWindow(new BasicInfoWindow(R.layout.bonuspack_bubble, myOpenMapView));
        //Note, the info window will not show if you set the onclick listener
        //line can also attach click listeners to the line
        /*
        line.setOnClickListener(new Polyline.OnClickListener() {
            @Override
            public boolean onClick(Polyline polyline, MapView mapView, GeoPoint eventPos) {
                Toast.makeText(context, "Hello world!", Toast.LENGTH_LONG).show();
                return false;
            }
        });*/


        myOpenMapView.getOverlayManager().add(line);
    }
}