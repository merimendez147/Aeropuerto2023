/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aeropuerto;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maria Elisa Mendez Cares 
 * Legajo: 61921 
 * Carrera: Profesorado de Informatica 
 * Email: maria.mendez@est.fi.uncoma.edu.ar
 */

public class GestorCheckin {

    int cantPuestos;
    int capacidadColaCheckin = 2;
    BlockingQueue<Thread>[] colaCheckin;
    Semaphore semAtencionCheckin[];

    public GestorCheckin(int cantPuestosCheckin) {
        this.cantPuestos = cantPuestosCheckin;
        semAtencionCheckin = new Semaphore[cantPuestos];
        colaCheckin = new BlockingQueue[cantPuestos];
        for (int i = 0; i < this.cantPuestos; i++) {
            colaCheckin[i] = new LinkedBlockingQueue<>(capacidadColaCheckin);
            semAtencionCheckin[i] = new Semaphore(0);
        }
    }

    public void hacerChecking(int puestoCheckin) {
        Thread pasajero = Thread.currentThread();
        try {
            colaCheckin[puestoCheckin].put(pasajero);
        } catch (InterruptedException ex) {
            Logger.getLogger(GestorCheckin.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("El " + pasajero.getName() + " está haciendo cola en el puesto de Checkin " + puestoCheckin);
        try {
            semAtencionCheckin[puestoCheckin].acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(GestorCheckin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void atenderPuestoCheckin(int puestoCheckin) {
        Thread pasajero = null;
        Thread puesto = Thread.currentThread();
        synchronized (puesto) {
            try {
                pasajero = colaCheckin[puestoCheckin].take(); // El siguiente pasajero en la cola de espera
            } catch (InterruptedException ex) {
                Logger.getLogger(GestorCheckin.class.getName()).log(Level.SEVERE, null, ex);
            }
            semAtencionCheckin[puestoCheckin].release();
        }
    }
}
