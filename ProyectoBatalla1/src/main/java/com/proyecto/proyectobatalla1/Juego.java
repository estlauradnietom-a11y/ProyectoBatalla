package com.proyecto.proyectobatalla1;

import com.murcia.utils.ColaEnlazada;

public class Juego {

    private ListaCircular jugadores;
    private ColaEnlazada turnos;
    private Dado dado;

    public Juego() {
        jugadores = new ListaCircular();
        turnos = new ColaEnlazada();
        dado = new Dado();
    }

    public void agregarJugador() {
        Jugador jugador = new Jugador();
        jugadores.insertar(jugador);
        turnos.encolar(jugador);
    }
}