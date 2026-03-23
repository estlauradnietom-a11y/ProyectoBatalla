package com.proyecto.proyectobatalla1;

import com.murcia.utils.ColaEnlazada;

public class ControlTurnos {

    private ColaEnlazada turnos;

    public ControlTurnos(ColaEnlazada turnos) {
        this.turnos = turnos;
    }

    public Jugador siguienteTurno() {
        Jugador actual = (Jugador) turnos.desencolar();
        turnos.encolar(actual);
        return actual;
    }
}