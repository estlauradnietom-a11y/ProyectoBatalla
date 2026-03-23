package com.proyecto.proyectobatalla1;

public abstract class Accion {

    protected int costo;
    protected String descripcion;

    public Accion(int costo, String descripcion) {
        this.costo = costo;
        this.descripcion = descripcion;
    }

    public abstract void ejecutar(Juego juego, Jugador origen, Jugador objetivo);
}