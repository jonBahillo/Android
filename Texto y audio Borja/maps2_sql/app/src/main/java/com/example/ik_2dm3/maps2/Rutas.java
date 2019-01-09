package com.example.ik_2dm3.maps2;


public class Rutas {
    private String Idruta;
    private String Posicion;
    private String LatiRu;
    private String LongRu;



    public Rutas(String Idruta, String Posicion,
                 String LatiRu, String LongRu) {
        this.Idruta = Idruta;
        this.Posicion = Posicion;
        this.LatiRu = LatiRu;
        this.LongRu = LongRu;

    }

    public String getIdruta() {
        return Idruta;
    }

    public String getPosicion() {
        return Posicion;
    }

    public String getLatiRu() {
        return LatiRu;
    }

    public String getLongRu() {
        return LongRu;
    }


}

