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
public class ColaEspera {
    int max = 20;
    ArrayBlockingQueue<Pasajero> colaEspera;
    
    public ColaEspera(){
         colaEspera = new ArrayBlockingQueue<>(max);
    }
    
    public void hacerCola(Pasajero pasajero){
        try {
            colaEspera.put(pasajero);
            System.out.println("El "+ pasajero.nombre()+" esta haciendo cola");
        } catch (InterruptedException ex) {
            Logger.getLogger(ColaEspera.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
