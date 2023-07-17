/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aeropuerto;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maria Mendez
 * Legajo 61921
 * Profesorado en Informatica
 */
public class GestorInformes {

    private final int cantidadPuestosInforme;
    private int atendiendo;

    public GestorInformes(int numPuestosInforme) {
        this.cantidadPuestosInforme = numPuestosInforme;
        this.atendiendo = 0;
    }

    public synchronized void solicitarAtencion() {
        while (atendiendo >= cantidadPuestosInforme) {
            System.out.println(Thread.currentThread().getName() + " debe esperar a ser atendido en el puesto de informes del aeropuerto");
            try {
                this.wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(GestorInformes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println(Thread.currentThread().getName() + " es atendido en el puesto de informes del aeropuerto");
        atendiendo++;
    }

    public synchronized int consultarPuestoChecking(Reserva reserva) {
        int puestoAtencionAerolinea = reserva.puestoChecking();
        System.out.println(Thread.currentThread().getName() + " tiene que ir al puesto de checkin " + puestoAtencionAerolinea);
        atendiendo--;
        this.notifyAll();
        return puestoAtencionAerolinea;
    }
}
