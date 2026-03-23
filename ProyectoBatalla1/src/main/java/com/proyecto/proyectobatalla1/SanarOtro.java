package com.proyecto.proyectobatalla1;

public class SanarOtro extends Accion {

    public SanarOtro() {
        super(35, "Sanar otro jugador");
    }

    @Override
    public void ejecutar(Juego juego, Jugador origen, Jugador objetivo) {
        juego.sumarVida(objetivo, 20);
    }
}