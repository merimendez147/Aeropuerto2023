/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aeropuerto;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maria Mendez
 * Legajo 61921
 * Profesorado en Informatica
 */
public class AtencionCheckin implements Runnable{

     PuestoCheckin puestoCheckin;

    public AtencionCheckin(PuestoCheckin puestoCheckin) {
        this.puestoCheckin = puestoCheckin;
    }


    @Override
    public void run() {
        while (true) {
            System.out.println(Thread.currentThread().getName() + " espera a un pasajero");
            puestoCheckin.esperarAtenderPasajero();
            puestoCheckin.hacerCheckin();
        }
    }
}