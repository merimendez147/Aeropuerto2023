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
public class AtencionCheckin implements Runnable{

     PuestoCheckin puestoCheckin;

    public AtencionCheckin(PuestoCheckin puestoCheckin) {
        this.puestoCheckin = puestoCheckin;
    }

    public void hacerCheckin() {
        int tiempoCheckin = (int) (Math.random() * 3) + 7;// entre 7 y 9 segundos
        System.out.println(Thread.currentThread().getName() + " esta atendiendo a un pasajero");
        try {
            Thread.sleep(tiempoCheckin * 1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(AtencionCheckin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        while (true) {
            System.out.println(Thread.currentThread().getName() + " espera a un pasajero");
            puestoCheckin.esperarAtenderPasajero();
            this.hacerCheckin();
            puestoCheckin.terminarCheckin();
        }
    }
}
