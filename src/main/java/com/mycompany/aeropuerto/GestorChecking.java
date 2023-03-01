/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aeropuerto;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Academica
 */
public class GestorChecking {

    private Boolean puestoCheckingLibre = true;
    private final int nroPuestoChecking;

    public GestorChecking(int nroPuesto) {
        this.nroPuestoChecking = nroPuesto;
    }

    public synchronized void solicitarAtencion(String pasajero) {
        try {
            while (!puestoCheckingLibre) {
                wait();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(GestorChecking.class.getName()).log(Level.SEVERE, null, ex);
        }
        //System.out.println("El " + pasajero + " esta haciendo el checking en el puesto " + nroPuestoChecking);
        puestoCheckingLibre = false;
    }

    public synchronized void liberarPuestoChecking() {
        puestoCheckingLibre = false;
        //System.out.println("El  puesto de checking " + nroPuestoChecking + " se libero ");
    }

}
