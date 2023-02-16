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
    ColaEspera [] colaPuestos;

    public GestorColaEspera(int cantPuestos) {
        this.cantPuestosChecking = cantPuestos;
        colaPuestos = new ColaEspera[cantPuestosChecking];
        for (int i = 0; i < cantPuestosChecking; i++) {
            colaPuestos[i]=new ColaEspera();

        }
    }

    public void hacerColaChecking(Pasajero pasajero, int puestoChecking) {
        colaPuestos[puestoChecking].hacerCola(pasajero);
    }

}
