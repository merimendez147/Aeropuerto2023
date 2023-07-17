package com.mycompany.aeropuerto;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maria Mendez
 * Legajo 61921
 * Profesorado en Informatica
 */
public class PuestoCheckin {

    int capacidadPuestoCheckin = 4;
    private final Semaphore semColaCheckin;
    private final Semaphore semGuardia;
    private Semaphore semAtencionCheckin;
    private final Semaphore semPuestoCheckin;
    private final Semaphore semCheckin;

    public PuestoCheckin() {
        semColaCheckin = new Semaphore(capacidadPuestoCheckin, true);
        semPuestoCheckin = new Semaphore(1, true);//el Puesto esta disponible al principio
        semGuardia = new Semaphore(0, true);//el Guardia esta esperando a que llegue un pasajero
        semCheckin = new Semaphore(0, true);//exclusion mutua del puesto de atencion
        semAtencionCheckin = new Semaphore(0, true);//el Puesto de Atencion esta esperando a que llegue un pasajero
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
    
    public void darPasoPasajero(){//guardia da paso al pasajero al puesto de checkin y habilita un lugar en la cola de espera
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
    
    public char hacerCheckin(Reserva reserva){//Pasajero hace checkin
        try {
            semCheckin.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(PuestoCheckin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  reserva.terminal();
    }
    
    public void hacerCheckin(){//atencionCheckin  hace el checkin
        semCheckin.release();
    }
    
    public void liberarPuestoCheckin(){//el pasajero libera el puesto de Checkin
        semPuestoCheckin.release();
    }
}