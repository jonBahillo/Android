package com.example.ik_2dm3.maps2;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

/**
 * Created by grupo3 on 23/10/2018.
 */

public class OSMUpdateLocation implements LocationListener {
    private MainActivity actividad;

    public OSMUpdateLocation(MainActivity actividad) {
        this.actividad = actividad;
    }

    @Override
    public void onLocationChanged(Location location) {
        actividad.actualizaPosicionActual(location);
    }

    @Override
    public void onProviderDisabled(String provider) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }
}