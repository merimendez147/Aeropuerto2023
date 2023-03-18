package com.mycompany.aeropuerto;

import java.util.Random;
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
public class PuestoCheckin {

    int capacidadPuestoCheckin = 20;
    private final Semaphore semColaCheckin;
    private final Semaphore semSalida;// lo usaria el cocinero para avisar al mozo que esta la comida 
    private final Semaphore semGuardia;
    private Semaphore semAtencionCheckin;
    private final Semaphore semPuestoCheckin;
    private final Semaphore semCheckin;

    public PuestoCheckin() {
        semColaCheckin = new Semaphore(capacidadPuestoCheckin, true);
        semSalida = new Semaphore(0, true);
        semPuestoCheckin = new Semaphore(1, true);//el Puesto esta disponible al principio
        semGuardia = new Semaphore(0, true);
        semCheckin = new Semaphore(0, true);
        semAtencionCheckin = new Semaphore(0, true);
    }

    public void hacerColaCheckin(){
        try {
            semColaCheckin.acquire();//pasajero hace cola para el checkin
        } catch (InterruptedException ex) {
            Logger.getLogger(PuestoCheckin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void pasarPuestoCheckin(){
        semGuardia.release(); //pasajero avisa al guardia
    }
    
    public void esperarPasajero(){//guardia espera pasajero
        try {
            semGuardia.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(PuestoCheckin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void darPasoPasajero(){//guardia da paso al pasajero al puesto de checkin
        try {
            semPuestoCheckin.acquire();//si el puesto de checkin esta libre
        } catch (InterruptedException ex) {
            Logger.getLogger(PuestoCheckin.class.getName()).log(Level.SEVERE, null, ex);
        }
        semAtencionCheckin.release();//el guardia avisa a AtencionCheckin que hay un pasjero
        semColaCheckin.release();//deja lugar en la cola del puesto de checkin
    }
    
    public void esperarAtenderPasajero(){//el puesto de atencion de checkin espera a un pasajero
        try {
            semAtencionCheckin.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(PuestoCheckin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public char hacerCheckin(){//Pasajero hace checkin
        try {
            semCheckin.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(PuestoCheckin.class.getName()).log(Level.SEVERE, null, ex);
        }
        Random random = new Random();
        char terminal = (char) (random.nextInt(3) + 'A');
        return terminal;
    }
    
    public void terminarCheckin(){//atencionCheckin termina de hacer checkin
        semCheckin.release();
    }
    
    public void liberarPuestoCheckin(){//el pasajero se va
        semPuestoCheckin.release();
    }
}
