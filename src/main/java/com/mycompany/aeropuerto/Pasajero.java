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
    GestorChecking checking;
    int puestoChecking;

    public Pasajero(GestorInformes informes, GestorColaEspera colaEspera, GestorChecking checking) {
        this.informes = informes;
        this.colaEspera = colaEspera;
        this.checking = checking;
    }

    public String nombre() {
        return Thread.currentThread().getName();
    }

    @Override
    public void run() {
        System.out.println("El pasajero " + Thread.currentThread().getName() + " llega al Aeropuerto");
        informes.solicitarAtencionInformes();
        puestoChecking = informes.consultarPuestoChecking();
        System.out.println("El pasajero " + Thread.currentThread().getName() + " tiene que ir al puesto de Checking " + puestoChecking);
        colaEspera.hacerColaChecking(this, puestoChecking);
    }
}
