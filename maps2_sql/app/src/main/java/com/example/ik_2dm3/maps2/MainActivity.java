package com.example.ik_2dm3.maps2;

/**
 * Created by Jon Bahillo, Mikel Gamboa, Borja Bueno, Gorka Gomez on 23/10/2018.
 */
import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayList<OverlayItem> puntos = new ArrayList<>();
    private MapView myOpenMapView;
    private MapController myMapController;
    private GeoPoint posicionActual;
    boolean repetido = false;
    Dialog dialog;

    @Override
   protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (tengoPermisoEscritura()) {
            cargarMapas();
        }
        dialog=new Dialog(this);
    }

    private void cargarMapas() {

        /*Basededatos MDB = new Basededatos(getApplicationContext());

        ArrayList<Posiciones> posicion =  MDB.recuperarposiciones();

        for(int i = 0; i < posicion.size(); i++){

            Log.d("myTag","array1 "+  posicion.get(i).getId());
        }*/

        //GeoPoint durango = new GeoPoint(43.25860, -2.90272);

        myOpenMapView = (MapView) findViewById(R.id.openmapview);
       // myOpenMapView.setBuiltInZoomControls(true);
        myMapController = (MapController) myOpenMapView.getController();
        //myMapController.setCenter(durango);
        myMapController.setZoom(30);
        myOpenMapView.setBuiltInZoomControls(false);

        myOpenMapView.setMultiTouchControls(true);
       /* myOpenMapView = (MapView) findViewById(R.id.openmapview);
        myOpenMapView.setTileSource(new XYTileSource("Mapnik", 0, 19, 256, ".png", new String[] {}));
        //....
        myOpenMapView.setUseDataConnection(false); //optional, but a good way to prevent loading from the network and test your zip loading.
        IMapController mapController = myOpenMapView.getController();
        mapController.setZoom(15);
        mapController.setCenter(durango);*/



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

        /////////////////////////////////////////
        // Añadir un punto en el mapa
        Basededatos MDB = new Basededatos(getApplicationContext());

        ArrayList<Posiciones> posicion =  MDB.recuperarposiciones();

        for(int i = 0; i < posicion.size(); i++){

            Double Latitud = Double.valueOf(posicion.get(i).getLati());
            Double Longitud = Double.valueOf(posicion.get(i).getLong());

            GeoPoint durango2 = new GeoPoint(Latitud, Longitud);

           /* puntos.add(new OverlayItem(posicion.get(i).getNombre(), posicion.get(i).getNombre(), durango));*/

            OverlayItem durango = new OverlayItem(posicion.get(i).getNombre(), posicion.get(i).getNombre(), durango2);


            byte[] decodedString = Base64.decode(posicion.get(i).getImagen(), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString,
                    0, decodedString.length);

            Log.d("myTag", "Bitmap "+ decodedByte);

            Drawable d = new BitmapDrawable(getResources(), decodedByte);


          //  durango.setMarker(ResourcesCompat.getDrawable(getResources(), R.drawable.sapuabuenote, null));
            durango.setMarker(d);

            puntos.add(durango);
            refrescaPuntos();
            Log.d("myTag","id "+ posicion.get(i).getNombre()+' '+Latitud+' '+Longitud);
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
        posicionActual = new GeoPoint(location.getLatitude(), location.getLongitude());
       // myMapController.setCenter(posicionActual);
       /* if (puntos.size() > 1)
            puntos.remove(1);*/
        OverlayItem marcador = new OverlayItem("Estás aquí", "Posicion actual", posicionActual);
        marcador.setMarker(ResourcesCompat.getDrawable(getResources(), R.drawable.sapuabuenote, null));
        puntos.add(marcador);
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
                enseñarPopUp(null, index);
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
        ItemizedOverlayWithFocus<OverlayItem> capa = new ItemizedOverlayWithFocus<>(this, puntos, tap);
        capa.setFocusItemsOnTap(true);
        myOpenMapView.getOverlays().add(capa);
    }

public void enseñarPopUp(View v, int i){
    TextView tvCerrar;
    Button btnJugar;
    dialog.setContentView(R.layout.popup);
    tvCerrar=(TextView)dialog.findViewById(R.id.txtCerrar);
    btnJugar=(Button)dialog.findViewById(R.id.btnJuego);

    tvCerrar.setOnClickListener(new View.OnClickListener() {



        @Override
        public void onClick(View v) {
            dialog.dismiss();
        }
    });


        if (!puntos.get(i).getSnippet().equals("Posicion actual")){
                dialog.show();

    }

}

}