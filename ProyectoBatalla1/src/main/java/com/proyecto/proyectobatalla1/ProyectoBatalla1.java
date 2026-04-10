package com.proyecto.proyectobatalla1;

import com.murcia.utils.Input;
import com.murcia.utils.Nodo;

public class ProyectoBatalla1 {

    static ListaCircular jugadores = new ListaCircular();

    public static void main(String[] args) {

        boolean activo = true;
        

        while (activo) {

            String opcionStr = Input.nextLine(
                "\n=== MENU PRINCIPAL ===\n" +
                "1. Iniciar partida\n" +
                "0. Mostrar ultimo ganador\n" +
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
                    Input.nextLine("Ultimo ganador: (aun no definido)");
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
            Jugador j = new Jugador(nombre);

            jugadores.insertar(j);
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

        int opcion;

        try {
            opcion = Integer.parseInt(opcionStr);
        } catch (Exception e) {
            opcion = -1;
        }

        if (opcion == 1) {
            ejecutarJuego();
        }
    }

    // ===============================
    public static void ejecutarJuego() {

        Nodo actual = jugadores.getPrimero();

        while (jugadoresActivos() > 1) {

            Jugador jugador = (Jugador) actual.getData();

            Input.nextLine("\nTurno de: " + jugador.getNombre());

            int dado = Input.genInt(1, 6);
            jugador.sumarPuntos(dado);

            Input.nextLine("Sacaste: " + dado);
            Input.nextLine("Puntos acumulados: " + jugador.getPuntos());

            String opcion = Input.nextLine("¿Desea ejecutar accion? (si/no)");

            if (opcion.equalsIgnoreCase("si")) {
                ejecutarAccion(jugador, actual);
            }

            if (jugador.getVida() <= 0) {
                Input.nextLine("Jugador " + jugador.getNombre() + " eliminado");
                eliminarJugador(actual);
            }

            actual = actual.getNext();
        }

        //  GANADOR
        Jugador ganador = (Jugador) jugadores.getPrimero().getData();
        Input.nextLine("🏆 GANADOR: " + ganador.getNombre());
    }

    // ===============================
   public static void ejecutarAccion(Jugador jugador, Nodo actual) {

    int puntos = jugador.getPuntos();

    String menu = "\nAcciones disponibles:\n";

    if (puntos >= 3) menu += "1. Ataque rápido (3 puntos)\n";
    if (puntos >= 5) menu += "2. Curación (5 puntos)\n";
    if (puntos >= 7) menu += "3. Ataque fuerte (7 puntos)\n";
    if (puntos >= 10) menu += "4. Ataque especial (10 puntos)\n";

    menu += "0. No hacer nada";

    String opcionStr = Input.nextLine(menu + "\nSeleccione: ");

    int opcion;
    try {
        opcion = Integer.parseInt(opcionStr);
    } catch (Exception e) {
        opcion = -1;
    }

    Nodo objetivoNodo = actual.getNext();
    Jugador objetivo = (Jugador) objetivoNodo.getData();

    switch (opcion) {

        case 1:
            if (puntos >= 3) {
                objetivo.restarVida(2);
                jugador.sumarPuntos(-3);
                Input.nextLine("Ataque rápido a " + objetivo.getNombre());
            } else {
                Input.nextLine("No tienes suficientes puntos");
            }
            break;

        case 2:
            if (puntos >= 5) {
                jugador.sumarVida(2);
                jugador.sumarPuntos(-5);
                Input.nextLine("Te curaste +2 de vida");
            } else {
                Input.nextLine("No tienes suficientes puntos");
            }
            break;

        case 3:
            if (puntos >= 7) {
                objetivo.restarVida(4);
                jugador.sumarPuntos(-7);
                Input.nextLine("Ataque fuerte a " + objetivo.getNombre());
            } else {
                Input.nextLine("No tienes suficientes puntos");
            }
            break;

        case 4:
            if (puntos >= 10) {
                jugadores.eliminarSiguiente(actual);
                jugador.sumarPuntos(-10);
                Input.nextLine("Ataque especial ejecutado");
            } else {
                Input.nextLine("No tienes suficientes puntos");
            }
            break;

        default:
            Input.nextLine("No hiciste ninguna acción");
    }
}
   
   
//   ------------
  public static void eliminarJugador(Nodo actual) {

    if (jugadores.estaVacia()) return;

    Nodo anterior = jugadores.getPrimero();

    //  buscar el nodo anterior al actual
    while (anterior.getNext() != actual) {
        anterior = anterior.getNext();
    }

    // 🗑 eliminar el nodo actual
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

