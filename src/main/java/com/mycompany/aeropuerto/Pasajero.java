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

    public Pasajero(GestorInformes informes, GestorColaEspera colaEspera, GestorChecking checking) {
        this.informes = informes;
        this.colaEspera = colaEspera;
        this.checking = checking;
    }

    public void run() {
        informes.consultarPuestoChecking();
    }
}
