package com.proyecto.proyectobatalla1;

import com.murcia.utils.Nodo;
import com.murcia.utils.ListaEnlazada; // 🔥 IMPORTANTE
import java.lang.reflect.Method;

// 🔥 HERENCIA AQUÍ
public class ListaCircular extends ListaEnlazada<Object> {

    private Nodo ultimo;

    public ListaCircular() {
        super(); // 🔥 opcional pero correcto
        ultimo = null;
    }

    // 🔥 TU MÉTODO ORIGINAL (NO SE TOCA)
    public void insertar(Object dato) {

        Nodo nuevo = new Nodo();

        // 🔥 SET DINÁMICO (FUNCIONA SIEMPRE)
        try {
            Method m = nuevo.getClass().getMethod("setDatos", Object.class);
            m.invoke(nuevo, dato);
        } catch (Exception e1) {
            try {
                Method m = nuevo.getClass().getMethod("setDato", Object.class);
                m.invoke(nuevo, dato);
            } catch (Exception e2) {
                try {
                    Method m = nuevo.getClass().getMethod("setInfo", Object.class);
                    m.invoke(nuevo, dato);
                } catch (Exception e3) {
                    System.out.println("ERROR: No se pudo guardar dato en Nodo");
                }
            }
        }

        if (ultimo == null) {
            ultimo = nuevo;
            ultimo.setNext(nuevo);
        } else {
            nuevo.setNext(ultimo.getNext());
            ultimo.setNext(nuevo);
            ultimo = nuevo;
        }
    }

    public Nodo getPrimero() {
        if (ultimo == null) return null;
        return ultimo.getNext();
    }

    public boolean estaVacia() {
        return ultimo == null;
    }
}