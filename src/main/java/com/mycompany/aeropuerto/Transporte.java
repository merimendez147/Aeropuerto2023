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
public class Transporte implements Runnable {

    GestorTransporte gestorTransporte;
    int capacidadTransporte;
    int cantidadPasajeros;

    public Transporte(GestorTransporte gestorT, int capacidadT, int cantidadP) {
        this.gestorTransporte = gestorT;
        this.capacidadTransporte = capacidadT;
        this.cantidadPasajeros = cantidadP;
    }

    private void avanzar() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Transporte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void esperar() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(Transporte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void run() {
        int cantidadRecorridos = cantidadPasajeros / capacidadTransporte;
        System.out.println("Cantidad de recorridos " + cantidadRecorridos);
        int recorridos = 1;
        while (recorridos <= cantidadRecorridos) {
            System.out.println("Recorrido Nº: " + recorridos);
            while (!gestorTransporte.trenLleno()) {
                gestorTransporte.dejarSubirPasajeros();
            }
            avanzar();
            while (!gestorTransporte.hayPasajero('A')) {
                gestorTransporte.asignarTurno('A');
               
            }
            esperar();
            while (!gestorTransporte.hayPasajero('B')) {
                gestorTransporte.asignarTurno('B');
                
            }
            esperar();
            while (!gestorTransporte.hayPasajero('C')) {
                gestorTransporte.asignarTurno('C');
                
            }
            recorridos++;
        }
       // System.out.println("Termina ejecucion del Transporte");
    }

}
