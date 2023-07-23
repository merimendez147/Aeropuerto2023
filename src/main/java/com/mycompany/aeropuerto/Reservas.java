/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aeropuerto;
/**
 *
 * @author Maria Elisa Mendez Cares
 * Legajo: 61921
 * Carrera: Profesorado de Informatica
 * Email: maria.mendez@est.fi.uncoma.edu.ar
 */
public class Reservas {

    Reserva reservas[];
    int tope;

    public Reservas(int cantidadReservas) {
        reservas = new Reserva[cantidadReservas];
        tope = -1;
    }

    public synchronized void push(Reserva reserva) {
        tope++;
        reservas[tope] = reserva;
    }

    public synchronized Reserva pop() {
        Reserva reserva = reservas[tope];
        tope--;
        return reserva;
    }

    public boolean reservasVacia() {
        return tope == -1;
    }

}
