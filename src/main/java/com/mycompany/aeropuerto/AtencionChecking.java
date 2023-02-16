/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aeropuerto;

/**
 *
 * @author Academica
 */
public class AtencionChecking implements Runnable {

    GestorColaEspera colaEspera;

    public AtencionChecking(GestorColaEspera colaE) {
        this.colaEspera = colaE;
    }

    @Override
    public void run() {

    }

}
