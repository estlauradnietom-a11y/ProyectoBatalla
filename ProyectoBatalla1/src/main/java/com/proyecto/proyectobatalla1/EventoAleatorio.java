package com.proyecto.proyectobatalla1;

public abstract class EventoAleatorio {

    protected String nombre;

    public EventoAleatorio(String nombre) {
        this.nombre = nombre;
    }

    public abstract void aplicar(Juego juego, Jugador jugador);
}