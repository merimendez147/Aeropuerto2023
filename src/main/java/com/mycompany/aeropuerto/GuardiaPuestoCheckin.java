/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aeropuerto;

/**
 *
 * @author Maria Mendez
 * Legajo 61921
 * Profesorado en Informatica
 */
public class GuardiaPuestoCheckin implements Runnable {

    PuestoCheckin puestoChecking;

    public GuardiaPuestoCheckin(PuestoCheckin puestoChecking) {
        this.puestoChecking= puestoChecking;
    }

    @Override
    public void run(){
        while(true){
            System.out.println(Thread.currentThread().getName() + " espera a un pasajero");
            puestoChecking.esperarPasajero();
            puestoChecking.darPasoPasajero();
            System.out.println(Thread.currentThread().getName() + " dejo pasar a un pasajero");
        }
    }
}
