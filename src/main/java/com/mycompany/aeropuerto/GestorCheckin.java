/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aeropuerto;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maria Mendez Legajo 61921 Profesorado en Informatica
 */
public class GestorCheckin {
        int cantPuestos;
        int capacidadColaCheckin = 2;
        BlockingQueue<Thread>[] colaCheckin;

        public GestorCheckin(int cantPuestosCheckin) {
            this.cantPuestos = cantPuestosCheckin;
            colaCheckin = new BlockingQueue[cantPuestos];
            for (int i = 0; i < this.cantPuestos; i++) {
                colaCheckin[i] = new LinkedBlockingQueue<>(capacidadColaCheckin);
            }
        }

        public void hacerChecking(int puestoCheckin) {
            Thread pasajero = Thread.currentThread();
            try {
                colaCheckin[puestoCheckin].put(pasajero);
            } catch (InterruptedException ex) {
                Logger.getLogger(GestorCheckin.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("El " + pasajero.getName() + " está haciendo cola en el puesto de Checkin " + puestoCheckin);
            //synchronized (pasajero) {
                pasajero.suspend(); // suspender  al pasajero para que continúe en la cola de check-in              
          //  }
        }

        public void atenderPuestoCheckin(int puestoCheckin) {
            Thread pasajero = null;
            Thread puesto = Thread.currentThread();
            synchronized (puesto) {
                try {
                    pasajero = colaCheckin[puestoCheckin].take(); // El siguiente pasajero en la cola de espera
                } catch (InterruptedException ex) {
                    Logger.getLogger(GestorCheckin.class.getName()).log(Level.SEVERE, null, ex);
                }
                pasajero.resume(); // reanudar el hilo pasajero
                //System.out.println("El " + pasajero.getName() + " hizo el Checkin en el puesto " + puestoCheckin);
            }
        }
}
