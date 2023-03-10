/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aeropuerto;

/**
 *
 * @author Academica
 */
public class GestorChecking {

    int cantPuestosChecking;
    ColaChecking[] colaPuestos;
    PuestoChecking[] gestorChecking;

    public GestorChecking(int cantPuestos) {
        this.cantPuestosChecking = cantPuestos;
        colaPuestos = new ColaChecking[cantPuestosChecking];
        gestorChecking = new PuestoChecking[cantPuestosChecking];
        for (int i = 0; i < cantPuestosChecking; i++) {
            colaPuestos[i] = new ColaChecking();
            gestorChecking[i] = new PuestoChecking(i);
        }
    }

    public void hacerColaChecking(Pasajero pasajero, int puestoChecking) {
        colaPuestos[puestoChecking].hacerCola(pasajero);
        System.out.println("El " + pasajero.nombre() + " esta haciendo cola en el puesto de Checking " + puestoChecking);
    }

    public void solicitarChecking(int puestoChecking) {
        String nombre = colaPuestos[puestoChecking].sacarCola();
        gestorChecking[puestoChecking].solicitarAtencion(nombre);
        System.out.println("El " + nombre + " esta haciendo el Checking en el puesto " + puestoChecking);
    }

    public char hacerChecking(int puestoChecking) {
        char terminal;
        terminal = gestorChecking[puestoChecking].liberarPuestoChecking();
        System.out.println("El  puesto de checking " + puestoChecking + " se libero ");
        return terminal;
    }
}
