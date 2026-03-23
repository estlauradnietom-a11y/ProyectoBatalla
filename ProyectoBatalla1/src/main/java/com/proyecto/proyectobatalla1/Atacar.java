package com.proyecto.proyectobatalla1;

public class Atacar extends Accion {

    public Atacar() {
        super(40, "Atacar");
    }

    @Override
    public void ejecutar(Juego juego, Jugador origen, Jugador objetivo) {
        juego.restarVida(objetivo, 20);
    }
}