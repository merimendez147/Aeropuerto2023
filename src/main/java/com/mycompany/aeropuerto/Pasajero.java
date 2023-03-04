/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aeropuerto;

/**
 *
 * @author Academica
 */
public class Pasajero implements Runnable {

    GestorInformes informes;
    GestorColaEspera colaEspera;
    GestorTransporte gestorTransporte;
    char terminal;

    int puestoChecking;
    boolean avanza = false;

    public Pasajero(GestorInformes informes, GestorColaEspera colaEspera, GestorTransporte gestorT) {
        this.informes = informes;
        this.colaEspera = colaEspera;
        this.gestorTransporte = gestorT;
    }

    public String nombre() {
        return Thread.currentThread().getName();
    }

    public boolean avanzaPasajero() {
        return this.avanza;
    }

    public void avanzarPasajero() {
        this.avanza = true;
    }

    @Override
    public void run() {
        System.out.println("El pasajero " + Thread.currentThread().getName() + " llega al Aeropuerto");
        informes.solicitarAtencionInformes();
        puestoChecking = informes.consultarPuestoChecking();
        System.out.println("El pasajero " + Thread.currentThread().getName() + " tiene que ir al puesto de Checking " + puestoChecking);
        colaEspera.hacerColaChecking(this, puestoChecking); //inserta al pasajero en la cola del puesto de Checking
        terminal = colaEspera.hacerChecking(puestoChecking);
        System.out.println("El pasajero " + Thread.currentThread().getName() + " hizo el Checking en el puesto " + puestoChecking + " tiene que ir a la Terminal " + terminal);
        gestorTransporte.subirTren(terminal);
        gestorTransporte.bajarTren(terminal);
    }
}
