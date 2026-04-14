package com.proyecto.proyectobatalla1;

import com.murcia.utils.Nodo;
import com.murcia.utils.ListaEnlazada;

public class ListaCircular extends ListaEnlazada<Object> {

    private Nodo ultimo;

    public ListaCircular() {
        super(); // opcional pero correcto
        ultimo = null;
    }

    // ===============================
    // INSERTAR AL FINAL
    public void insertar(Object dato) {

        Nodo nuevo = new Nodo();
        nuevo.setData(dato); 

        if (ultimo == null) {
            ultimo = nuevo;
            ultimo.setNext(nuevo);
        } else {
            nuevo.setNext(ultimo.getNext()); 
            ultimo.setNext(nuevo);
            ultimo = nuevo; 
        }
    }

    // ===============================
    public Nodo getPrimero() {
        if (ultimo == null) return null;
        return ultimo.getNext();
    }

    public boolean estaVacia() {
        return ultimo == null;
    }

    public void eliminarSiguiente(Nodo actual) {

        if (actual == null || actual.getNext() == null) return;

        Nodo eliminado = actual.getNext();

        if (eliminado == actual) {
            ultimo = null;
            return;
        }

        actual.setNext(eliminado.getNext());

        if (eliminado == ultimo) {
            ultimo = actual;
        }
    }

    public int size() {

        if (estaVacia()) return 0;

        int count = 0;
        Nodo actual = getPrimero();

        do {
            count++;
            actual = actual.getNext();
        } while (actual != getPrimero());

        return count;
    }

    public void mostrar() {

        if (estaVacia()) {
            System.out.println("Lista vacía");
            return;
        }

        Nodo actual = getPrimero();

        do {
            System.out.println(actual.getData());
            actual = actual.getNext();
        } while (actual != getPrimero());
    }
}