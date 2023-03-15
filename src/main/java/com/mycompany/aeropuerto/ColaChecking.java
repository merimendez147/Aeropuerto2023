package com.mycompany.aeropuerto;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Academica
 */
public class ColaChecking {
    int max = 20;
    ArrayBlockingQueue<Pasajero> colaEspera;
    
    public ColaChecking(){
         colaEspera = new ArrayBlockingQueue<>(max);
    }
    
    public void hacerCola(Pasajero pasajero){
        try {
            colaEspera.put(pasajero);
            System.out.println("El " + pasajero.nombre() + " esta en cola ");
        } catch (InterruptedException ex) {
            Logger.getLogger(ColaChecking.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
    
    public boolean colaVacia(){
        return colaEspera.isEmpty();
    }
    
    public String sacarCola(){
        Pasajero pasajero = null;
        try {
            pasajero = colaEspera.take();
             System.out.println("El " + pasajero.nombre() + " salio de la cola ");
        } catch (InterruptedException ex) {
            Logger.getLogger(ColaChecking.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pasajero.nombre();
    }
    
}
