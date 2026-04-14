package com.proyecto.proyectobatalla1;

public class Jugador {

    private String nombre;
    private int vida;
    private int puntos;

    //  Constructor principal 
    public Jugador(String nombre) {
        this.nombre = nombre;
        this.vida = 10;
        this.puntos = 0;
    }

    //  Constructor opcional 
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
    // SETTER 

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // ===============================
    // MÉTODOS 

    public void sumarPuntos(int cantidad) {
        this.puntos += cantidad;

        // Evita puntos negativos
        if (this.puntos < 0) {
            this.puntos = 0;
        }
    }


    public void restarVida(int cantidad) {
        this.vida -= cantidad;

 
        if (this.vida < 0) {
            this.vida = 0;
        }
    }

 
    public void sumarVida(int cantidad) {
        this.vida += cantidad;

        
        if (this.vida > 10) {
            this.vida = 10;
        }
    }

 
    public boolean estaVivo() {
        return this.vida > 0;
    }

    
    @Override
    public String toString() {
        return nombre + " | Vida: " + vida + " | Puntos: " + puntos;
    }
}