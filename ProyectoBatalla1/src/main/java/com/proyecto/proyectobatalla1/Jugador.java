package com.proyecto.proyectobatalla1;

public class Jugador {

    private String nombre;
    private int vida;
    private int puntos;

    // 🔹 Constructor principal (OBLIGATORIO)
    public Jugador(String nombre) {
        this.nombre = nombre;
        this.vida = 10;
        this.puntos = 0;
    }

    // 🔹 Constructor opcional (por si lo necesitas sin nombre)
    public Jugador() {
        this.nombre = "Sin nombre";
        this.vida = 10;
        this.puntos = 0;
    }

    // ===============================
    // GETTERS

    public String getNombre() {
        return nombre;
    }

    public int getVida() {
        return vida;
    }

    public int getPuntos() {
        return puntos;
    }

    // ===============================
    // SETTER OPCIONAL

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // ===============================
    // MÉTODOS DE LÓGICA

    // 🔹 SUMAR PUNTOS
    public void sumarPuntos(int cantidad) {
        this.puntos += cantidad;

        // Evita puntos negativos
        if (this.puntos < 0) {
            this.puntos = 0;
        }
    }

    // 🔹 RESTAR VIDA
    public void restarVida(int cantidad) {
        this.vida -= cantidad;

        // Evita vida negativa
        if (this.vida < 0) {
            this.vida = 0;
        }
    }

    // 🔹 SUMAR VIDA
    public void sumarVida(int cantidad) {
        this.vida += cantidad;

        // 🔥 Límite máximo de vida (recomendado)
        if (this.vida > 10) {
            this.vida = 10;
        }
    }

    // 🔹 VALIDAR SI ESTÁ VIVO
    public boolean estaVivo() {
        return this.vida > 0;
    }

    // 🔹 MOSTRAR INFO (útil para debug o impresión)
    @Override
    public String toString() {
        return nombre + " | Vida: " + vida + " | Puntos: " + puntos;
    }
}