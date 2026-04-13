package com.proyecto.proyectobatalla1;

import com.murcia.utils.Nodo;

public class ListaCircular {

    private Nodo ultimo;

    public ListaCircular() {
        ultimo = null;
    }

    // ===============================
    // INSERTAR AL FINAL
    public void insertar(Object dato) {

        Nodo nuevo = new Nodo();
        nuevo.setData(dato); // ✅ SIMPLE (sin reflection)

        if (ultimo == null) {
            ultimo = nuevo;
            ultimo.setNext(nuevo);
        } else {
            nuevo.setNext(ultimo.getNext()); // apunta al primero
            ultimo.setNext(nuevo);
            ultimo = nuevo; // se convierte en el último
        }
    }

    // ===============================
    // OBTENER PRIMERO
    public Nodo getPrimero() {
        if (ultimo == null) return null;
        return ultimo.getNext();
    }

    // ===============================
    // VALIDAR SI ESTÁ VACÍA
    public boolean estaVacia() {
        return ultimo == null;
    }

    // ===============================
    // ELIMINAR SIGUIENTE (CLAVE PARA EL JUEGO)
    public void eliminarSiguiente(Nodo actual) {

        if (actual == null || actual.getNext() == null) return;

        Nodo eliminado = actual.getNext();

        // 🔥 CASO: solo un nodo
        if (eliminado == actual) {
            ultimo = null;
            return;
        }

        actual.setNext(eliminado.getNext());

        // 🔥 SI ELIMINA EL ÚLTIMO
        if (eliminado == ultimo) {
            ultimo = actual;
        }
    }

    // ===============================
    // CONTAR ELEMENTOS
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

    // ===============================
    // MOSTRAR LISTA (DEBUG)
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