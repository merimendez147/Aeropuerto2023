/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aeropuerto;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Academica
 */
public class GestorFreeShop {

    int capacidad = 20;
    char nombreTerminal;
    ArrayBlockingQueue<Pasajero> freeShop;

    public GestorFreeShop(char nombreT) {
        this.nombreTerminal = nombreT;
        freeShop = new ArrayBlockingQueue<>(capacidad);
    }

    public void ingresarFreeShop(Pasajero pasajero) {
        try {
            freeShop.put(pasajero);
        } catch (InterruptedException ex) {
            Logger.getLogger(GestorFreeShop.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean colaVacia() {
        return freeShop.isEmpty();
    }

    public String salirFreeShop() {
        Pasajero pasajero = null;
        try {
            pasajero = freeShop.take();
        } catch (InterruptedException ex) {
            Logger.getLogger(ColaChecking.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pasajero.nombre();
    }
}
