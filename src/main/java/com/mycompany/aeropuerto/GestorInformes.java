/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aeropuerto;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Academica
 */
public class GestorInformes {

    Semaphore semPasajero = new Semaphore(0);
    Semaphore semAtencionInformes = new Semaphore(0);
    // Semaphore mutexAtencion = new Semaphore(1);
    int cantPuestoChecking;
    int cantPasajeros;

    public GestorInformes(int cantPuestos, int cantPasajeros) {
        this.cantPasajeros = cantPasajeros;
        this.cantPuestoChecking = cantPasajeros;

    }

    public int cantidadPasajeros() {
        return this.cantPasajeros;
    }

    public void solicitarAtencionInformes() {
        semAtencionInformes.release();
    }

    public int consultarPuestoChecking() {
        int puestoAtencionAerolinea = 0;
        try {
            semPasajero.acquire();
            puestoAtencionAerolinea = (int) (Math.random() * cantPuestoChecking);
        } catch (InterruptedException ex) {
            Logger.getLogger(GestorInformes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return puestoAtencionAerolinea;
    }

    public void esperarPasajero() {
        try {
            semAtencionInformes.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(GestorInformes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void atenderPasajero() {
        semPasajero.release();
    }
}
