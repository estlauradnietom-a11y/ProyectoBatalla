package com.proyecto.proyectobatalla1;

import com.murcia.utils.Input;
import com.murcia.utils.Nodo;

public class ProyectoBatalla1 {

    static ListaCircular jugadores = new ListaCircular();
    static String ultimoGanador = "Aun no definido";

    public static void main(String[] args) {

        boolean activo = true;

        while (activo) {

            String opcionStr = Input.nextLine(
                "\n=== MENU PRINCIPAL ===\n" +
                "1. Iniciar partida\n" +
                "0. Mostrar ultimo ganador\n" +
                "2. Salir\n" +
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
                    iniciarPartida();
                    break;

                case 0:
                    Input.nextLine("Ultimo ganador: " + ultimoGanador);
                    break;

                case 2:
                    activo = false;
                    break;

                default:
                    Input.nextLine("Opcion invalida");
            }
        }
    }

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
            String nombre = Input.nextLine("Jugador #" + (i + 1));
            jugadores.insertar(new Jugador(nombre)); // ✅ CORRECTO
        }

        mostrarJugadores();
        menuInicioJuego();
    }

    // ===============================
    public static void mostrarJugadores() {

        if (jugadores.estaVacia()) {
            Input.nextLine("No hay jugadores");
            return;
        }

        Nodo actual = jugadores.getPrimero();
        String lista = "\nJugadores registrados:\n";

        do {
            Jugador j = (Jugador) actual.getData();
            lista += "- " + j.getNombre() + " | Vida: " + j.getVida() + "\n";
            actual = actual.getNext();
        } while (actual != jugadores.getPrimero());

        Input.nextLine(lista);
    }

    // ===============================
    public static void menuInicioJuego() {

        String opcionStr = Input.nextLine(
            "\n¿Desean iniciar el juego?\n" +
            "1. Si\n" +
            "2. No\n" +
            "Seleccione: "
        );

        if (opcionStr.equals("1")) {
            ejecutarJuego();
        }
    }

    // ===============================
    public static void ejecutarJuego() {

        Nodo actual = jugadores.getPrimero();

        int totalJugadores = jugadoresActivos();
        int contadorTurnos = 0;

        while (jugadoresActivos() > 1) {

            Jugador jugador = (Jugador) actual.getData();

            Input.nextLine("\nTurno de: " + jugador.getNombre());

            int dado = Input.genInt(1, 6);
            jugador.sumarPuntos(dado);

            Input.nextLine("Sacaste: " + dado);
            Input.nextLine("Puntos: " + jugador.getPuntos());

            String opcion = Input.nextLine("¿Desea ejecutar accion? (si/no)");

            if (opcion.equalsIgnoreCase("si")) {
                ejecutarAccion(jugador, actual);
            }

            // 🔥 VALIDAR SI MURIÓ
            if (jugador.getVida() <= 0) {
                Input.nextLine("Jugador eliminado: " + jugador.getNombre());
                Nodo siguiente = actual.getNext(); // guardar siguiente
                eliminarJugador(actual);
                actual = siguiente;
                continue;
            }

            actual = actual.getNext();
            contadorTurnos++;

            // 🔥 EVENTO POR RONDA
            if (contadorTurnos >= totalJugadores) {
                ejecutarEventoAleatorio();
                contadorTurnos = 0;
                totalJugadores = jugadoresActivos();
            }
        }

        Jugador ganador = (Jugador) jugadores.getPrimero().getData();
        ultimoGanador = ganador.getNombre();
        Input.nextLine("🏆 GANADOR: " + ultimoGanador);
    }

    // ===============================
    public static void ejecutarAccion(Jugador jugador, Nodo actual) {

        int puntos = jugador.getPuntos();

        String menu = "\nAcciones:\n";

        if (puntos >= 3) menu += "1. Ataque rápido\n";
        if (puntos >= 5) menu += "2. Curación\n";
        if (puntos >= 7) menu += "3. Ataque fuerte\n";
        if (puntos >= 10) menu += "4. Ataque especial\n";

        menu += "0. Nada\n";

        int opcion;

        try {
            opcion = Integer.parseInt(Input.nextLine(menu));
        } catch (Exception e) {
            return;
        }

        Nodo objetivoNodo = actual.getNext();
        Jugador objetivo = (Jugador) objetivoNodo.getData();

        switch (opcion) {
            case 1:
                objetivo.restarVida(2);
                jugador.sumarPuntos(-3);
                break;

            case 2:
                jugador.sumarVida(2);
                jugador.sumarPuntos(-5);
                break;

            case 3:
                objetivo.restarVida(4);
                jugador.sumarPuntos(-7);
                break;

            case 4:
                jugadores.eliminarSiguiente(actual);
                jugador.sumarPuntos(-10);
                break;
        }
    }

    // ===============================
   public static void ejecutarEventoAleatorio() {

    int evento = (int)(Math.random() * 3) + 1; // ✅ CORREGIDO

    Input.nextLine("\n🎲 EVENTO ALEATORIO ACTIVADO");

    switch (evento) {

        case 1:
            Input.nextLine("🔄 Orden de jugadores invertido");
            invertirOrden();
            break;

        case 2:
            Input.nextLine("⚔️ Duelo de los más fuertes");
            dueloMejores();
            break;

        case 3:
            Nodo primero = jugadores.getPrimero();
            Jugador inmune = (Jugador) primero.getData();
            Input.nextLine("🛡 " + inmune.getNombre() + " es inmune por una ronda");
            break;
    }
}

    // ===============================
    public static void dueloMejores() {

        Nodo actual = jugadores.getPrimero();
        Jugador max1 = null, max2 = null;

        do {
            Jugador j = (Jugador) actual.getData();

            if (max1 == null || j.getVida() > max1.getVida()) {
                max2 = max1;
                max1 = j;
            } else if (max2 == null || j.getVida() > max2.getVida()) {
                max2 = j;
            }

            actual = actual.getNext();
        } while (actual != jugadores.getPrimero());

        if (max1 != null && max2 != null) {
            max1.restarVida(2);
            max2.restarVida(2);
            Input.nextLine(max1.getNombre() + " vs " + max2.getNombre());
        }
    }

    // ===============================
    public static void invertirOrden() {

        if (jugadores.estaVacia()) return;

        Nodo prev = null;
        Nodo actual = jugadores.getPrimero();
        Nodo inicio = actual;

        do {
            Nodo next = actual.getNext();
            actual.setNext(prev);
            prev = actual;
            actual = next;
        } while (actual != inicio);

        inicio.setNext(prev);
    }

    // ===============================
    public static void eliminarJugador(Nodo actual) {

        Nodo anterior = jugadores.getPrimero();

        while (anterior.getNext() != actual) {
            anterior = anterior.getNext();
        }

        jugadores.eliminarSiguiente(anterior);
    }

    // ===============================
    public static int jugadoresActivos() {

        if (jugadores.estaVacia()) return 0;

        int count = 0;
        Nodo actual = jugadores.getPrimero();

        do {
            count++;
            actual = actual.getNext();
        } while (actual != jugadores.getPrimero());

        return count;
    }
}