package com.proyecto.proyectobatalla1;

import com.murcia.utils.Input;

public class Dado {

    public int lanzar() {
        return Input.genInt(1, 6);
    }
}