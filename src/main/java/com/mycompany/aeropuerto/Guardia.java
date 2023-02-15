/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aeropuerto;

/**
 *
 * @author Academica
 */
public class Guardia implements Runnable {

    GestorColaEspera colaEspera;

    public Guardia(GestorColaEspera colaEspera) {
        this.colaEspera = colaEspera;
    }

    @Override
    public void run() {

    }
}
