package com.proyecto.proyectobatalla1;

import com.murcia.utils.Input;
import com.murcia.utils.Nodo;

import java.lang.reflect.Method;

public class ProyectoBatalla1 {

    static ListaCircular jugadores = new ListaCircular();

    public static void main(String[] args) {

        boolean activo = true;

        while (activo) {

            String opcionStr = Input.nextLine(
                "\n=== MENU PRINCIPAL ===\n" +
                "1. Iniciar partida\n" +
                "0. Mostrar ultimo ganador\n" +
                "Seleccione una opcion: "
            );

            int opcion;

            try {
                opcion = Integer.parseInt(opcionStr);
            } catch (Exception e) {
                opcion = -1;
            }

            switch (opcion) {

                case 1:
                    iniciarPartida();
                    break;

                case 0:
                    Input.nextLine("Ultimo ganador: (aun no definido)");
                    break;

                default:
                    Input.nextLine("Opcion invalida");
            }
        }
    }

    // ===============================
    // INICIAR PARTIDA
    // ===============================
    public static void iniciarPartida() {

        String cantidadStr = Input.nextLine("¿Cuantos jugadores van a jugar?");
        int cantidad;

        try {
            cantidad = Integer.parseInt(cantidadStr);
        } catch (Exception e) {
            Input.nextLine("Numero invalido");
            return;
        }

        jugadores = new ListaCircular();

        for (int i = 0; i < cantidad; i++) {

            String nombre = Input.nextLine("Ingrese el nombre del jugador #" + (i + 1));
            Jugador j = new Jugador(nombre);
            jugadores.insertar(j);
        }

        mostrarJugadores();
        menuInicioJuego();
    }

    // ===============================
    // MOSTRAR JUGADORES (SIN ERROR)
    // ===============================
    public static void mostrarJugadores() {

        if (jugadores.estaVacia()) {
            Input.nextLine("No hay jugadores registrados");
            return;
        }

        Nodo actual = jugadores.getPrimero();
        String lista = "\nJugadores registrados:\n";

        do {
            Jugador j = (Jugador) obtenerDatoNodo(actual);
            lista += "- " + j.getNombre() + "\n";
            actual = actual.getNext();
        } while (actual != jugadores.getPrimero());

        Input.nextLine(lista);
    }

    // ===============================
    // METODO UNIVERSAL PARA LEER NODO
    // ===============================
    public static Object obtenerDatoNodo(Nodo nodo) {

        try {
            Method metodo = nodo.getClass().getMethod("getDato");
            return metodo.invoke(nodo);
        } catch (Exception e1) {

            try {
                Method metodo = nodo.getClass().getMethod("getDatos");
                return metodo.invoke(nodo);
            } catch (Exception e2) {

                try {
                    Method metodo = nodo.getClass().getMethod("getInfo");
                    return metodo.invoke(nodo);
                } catch (Exception e3) {

                    try {
                        Method metodo = nodo.getClass().getMethod("getData");
                        return metodo.invoke(nodo);
                    } catch (Exception e4) {

                        return null;
                    }
                }
            }
        }
    }

    // ===============================
    // MENU DE CONFIRMACION
    // ===============================
    public static void menuInicioJuego() {

        String opcionStr = Input.nextLine(
            "\n¿Desean iniciar el juego?\n" +
            "1. Si\n" +
            "2. No (volver al menu principal)\n" +
            "Seleccione: "
        );

        int opcion;

        try {
            opcion = Integer.parseInt(opcionStr);
        } catch (Exception e) {
            opcion = -1;
        }

        switch (opcion) {

            case 1:
                Input.nextLine("Iniciando juego... (aun no implementado)");
                break;

            case 2:
                Input.nextLine("Regresando al menu principal...");
                break;

            default:
                Input.nextLine("Opcion invalida");
        }
    }
}