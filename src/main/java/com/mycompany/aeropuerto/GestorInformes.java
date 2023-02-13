/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aeropuerto;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Academica
 */
public class GestorInformes {
    
    Semaphore semPasajero= new Semaphore(0);
    Semaphore semAtencionInformes = new Semaphore (0);
    Semaphore mutexAtencion = new Semaphore (1);
    
    public GestorInformes(){
        
    }
    
    public void solicitarAtencionInformes(){
        semAtencionInformes.release();
    }
    
    public void consultarPuestoChecking(){
        try {
            semPasajero.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(GestorInformes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void esperarPasajero(){
        try {
            semAtencionInformes.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(GestorInformes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void atenderPasajero(){
        semPasajero.release();
    }
}
