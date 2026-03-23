package com.proyecto.proyectobatalla1;

import com.murcia.utils.ListaEnlazada;
import com.murcia.utils.Nodo;

public class ListaCircular extends ListaEnlazada {

    private Nodo ultimo;

    public ListaCircular() {
        ultimo = null;
    }

    public void insertar(Object dato) {

        Nodo nuevo = new Nodo();
        nuevo.setDatos(dato);   // ← este era el error

        if (ultimo == null) {
            ultimo = nuevo;
            ultimo.setNext(nuevo);
        } else {
            nuevo.setNext(ultimo.getNext());
            ultimo.setNext(nuevo);
            ultimo = nuevo;
        }
    }
}