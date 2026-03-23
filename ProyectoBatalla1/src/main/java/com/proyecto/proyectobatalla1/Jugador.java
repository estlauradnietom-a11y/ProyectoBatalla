package com.proyecto.proyectobatalla1;

import com.murcia.utils.Input;

public class Jugador {

    private String nombre;

    public Jugador() {
        nombre = Input.nextLine("Ingrese el nombre del jugador: ");
    }

    public String getNombre() {
        return nombre;
    }
}