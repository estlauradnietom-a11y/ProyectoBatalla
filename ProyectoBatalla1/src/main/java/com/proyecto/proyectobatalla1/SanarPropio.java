package com.proyecto.proyectobatalla1;

public class SanarPropio extends Accion {

    public SanarPropio() {
        super(30, "Sanar vida propia");
    }

    @Override
    public void ejecutar(Juego juego, Jugador origen, Jugador objetivo) {
        juego.sumarVida(origen, 20);
    }
}