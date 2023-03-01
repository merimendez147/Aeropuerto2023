package com.mycompany.aeropuerto;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Semaphore;
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
    ArrayBlockingQueue<Semaphore> turno;
    
    public ColaEspera(){
         colaEspera = new ArrayBlockingQueue<>(max);
         turno = new ArrayBlockingQueue<>(max);
    }
    
    public void hacerCola(Pasajero pasajero){
        Semaphore semTurno= new Semaphore(0);
        try {
            colaEspera.put(pasajero);
            turno.put(semTurno);
            
        } catch (InterruptedException ex) {
            Logger.getLogger(ColaEspera.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean colaVacia(){
        return colaEspera.isEmpty();
    }
    
    public String salirCola(){
        Pasajero pasajero = null;
        try {
            pasajero = colaEspera.take();
        } catch (InterruptedException ex) {
            Logger.getLogger(ColaEspera.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pasajero.nombre();
    }
    
}
