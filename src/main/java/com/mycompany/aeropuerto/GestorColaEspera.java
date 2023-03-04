/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aeropuerto;

/**
 *
 * @author Academica
 */
public class GestorColaEspera {

    int cantPuestosChecking;
    ColaEspera[] colaPuestos;
    GestorChecking[] gestorChecking;

    public GestorColaEspera(int cantPuestos) {
        this.cantPuestosChecking = cantPuestos;
        colaPuestos = new ColaEspera[cantPuestosChecking];
        gestorChecking = new GestorChecking[cantPuestosChecking];
        for (int i = 0; i < cantPuestosChecking; i++) {
            colaPuestos[i] = new ColaEspera();
            gestorChecking[i] = new GestorChecking(i);
        }
    }

    public void hacerColaChecking(Pasajero pasajero, int puestoChecking) {
        colaPuestos[puestoChecking].hacerCola(pasajero);
        System.out.println("El " + pasajero.nombre() + " esta haciendo cola en el puesto de Checking " + puestoChecking);
    }

    public void solicitarChecking(int puestoChecking) {
        String nombre = colaPuestos[puestoChecking].salirCola();
        gestorChecking[puestoChecking].solicitarAtencion(nombre);
        System.out.println("El " + nombre + " esta haciendo cola en el puesto de Checking " + puestoChecking);
    }

    public char hacerChecking(int puestoChecking) {
        char terminal;
        terminal = gestorChecking[puestoChecking].liberarPuestoChecking();
        System.out.println("El  puesto de checking " + puestoChecking + " se libero ");
        return terminal;
    }
}
