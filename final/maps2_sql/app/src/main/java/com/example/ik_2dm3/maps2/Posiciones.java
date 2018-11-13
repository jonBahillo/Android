package com.example.ik_2dm3.maps2;


public class Posiciones {
    private String ID;
    private String Nombre;
    private String Long;
    private String Lati;
    private String Orden;
    private String Imagen;
    private String Juego;
    private double Pasado;
    private String Imagenpasado;
    private String Imgenpopup;


    public Posiciones(String ID, String Nombre,
                      String Long, String Lati,
                      String Orden, String Imagen,
                      String Juego, double Pasado,String Imagenpasado,String Imgenpopup) {
        this.ID = ID;
        this.Nombre = Nombre;
        this.Long = Long;
        this.Lati = Lati;
        this.Orden = Orden;
        this.Imagen = Imagen;
        this.Juego = Juego;
        this.Pasado = Pasado;
        this.Imagenpasado = Imagenpasado;
        this.Imgenpopup = Imgenpopup;
    }

    public String getId() {
        return ID;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getLong() {
        return Long;
    }

    public String getLati() {
        return Lati;
    }

    public String getImagen() {
        return Imagen;
    }

    public String getOrden() {
        return Orden;
    }


    public String getJuego() {
        return Juego;
    }

    public double getPasado() {
        return Pasado;
    }

    public String getImagenpasado() {
        return Imagenpasado;
    }

    public String getImgenpopup() {
        return Imgenpopup;
    }

}

