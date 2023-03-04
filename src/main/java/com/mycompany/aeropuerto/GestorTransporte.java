/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aeropuerto;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Academica
 */
public class GestorTransporte {

    int capacidadTren;
    CyclicBarrier inicioTrayecto;
    CyclicBarrier finTrayecto;
    int terminalA = 0;
    int terminalB = 0;
    int terminalC = 0;
    Semaphore mutex;

    public GestorTransporte(int capacidadT) {
        this.capacidadTren = capacidadT;
        inicioTrayecto = new CyclicBarrier(capacidadTren);
        finTrayecto = new CyclicBarrier(capacidadTren);
        mutex = new Semaphore(1);
    }

    public int capacidadTren() {
        return this.capacidadTren;
    }

    public void subirTren(char terminal) {
        try {

            mutex.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(GestorTransporte.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            switch (terminal) {
                case 'A' -> {
                    terminalA++;
                    System.out.println("El " + Thread.currentThread().getName() + " subio al tren y tiene que ir a la terminal " + terminal);
                }
                case 'B' -> {
                    terminalB++;
                    System.out.println("El " + Thread.currentThread().getName() + " subio al tren y tiene que ir a la terminal " + terminal);
                }
                default -> {
                    terminalC++;
                    System.out.println("El " + Thread.currentThread().getName() + " subio al tren y tiene que ir a la terminal " + terminal);
                }
            }
            mutex.release();
            int mensaje = inicioTrayecto.await();
            if (mensaje == 0) {
                System.out.println("------------------------El tren sale completo---------------------------");
            }
        } catch (InterruptedException | BrokenBarrierException ex) {
            Logger.getLogger(GestorTransporte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void bajarTren(char terminal) {
        try {
            switch (terminal) {
                case 'A' -> {

                    System.out.println("El " + Thread.currentThread().getName() + " bajo en la terminal A");
                }
                case 'B' -> {

                    System.out.println("El " + Thread.currentThread().getName() + " bajo en la terminal B");
                }
                default -> {

                    System.out.println("El " + Thread.currentThread().getName() + " bajo en la terminal C");
                }
            }
            int mensaje = finTrayecto.await();
            if (mensaje == 0) {
                System.out.println("------------------El tren vuelve vacio-------------------");
            }
        } catch (InterruptedException | BrokenBarrierException ex) {
            Logger.getLogger(GestorTransporte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
