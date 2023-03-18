/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aeropuerto;

/**
 *
 * @author Academica
 */
public class GestorCheckin {

    int cantPuestosChecking;
    PuestoCheckin[] puestoChecking;
    //GuardiaPuestoCheckin[] guardiaPuestoCheckin;

    public GestorCheckin(int cantPuestos) {
        this.cantPuestosChecking = cantPuestos;
        puestoChecking = new PuestoCheckin[cantPuestosChecking];
        Thread[] guardiaPuestoCheckin = new Thread[cantPuestosChecking];
        Thread[] atencionCheckin = new Thread[cantPuestosChecking];
//        guardiaPuestoCheckin = new GuardiaPuestoCheckin[cantPuestosChecking];
        for (int i = 0; i < cantPuestosChecking; i++) {
            puestoChecking[i] = new PuestoCheckin();
            guardiaPuestoCheckin[i] = new Thread(new GuardiaPuestoCheckin(puestoChecking[i] ));
            guardiaPuestoCheckin[i].setName("GuardiaPuestoCheckin"+i);
            guardiaPuestoCheckin[i].start();
            atencionCheckin[i] = new Thread(new AtencionCheckin(puestoChecking[i] ));
            atencionCheckin[i].setName("AtencionPuestoCheckin"+i);
            atencionCheckin[i].start();
        }
    }
    
    
public void hacerColaChecking(int puestoChecking) {
        this.puestoChecking[puestoChecking].hacerColaCheckin();
        System.out.println("El " + Thread.currentThread().getName() + " esta haciendo cola en el puesto de Checking " + puestoChecking);
    }

    public void pasarPuestoCheckin(int puestoChecking) {
        this.puestoChecking[puestoChecking].pasarPuestoCheckin();
        System.out.println("El " + Thread.currentThread().getName() + " avisa que quiere pasar a hacer Checking en el puesto " + puestoChecking);
    }

    public char hacerChecking(int puestoChecking) {
        char terminal = this.puestoChecking[puestoChecking].hacerCheckin();
        System.out.println("El " + Thread.currentThread().getName() + " hizo el Checking en el puesto " + puestoChecking);
        return terminal;
    }

    public void liberarPuestoCheckin(int puestoChecking) {
        this.puestoChecking[puestoChecking].liberarPuestoCheckin();
        System.out.println( Thread.currentThread().getName() +" libero el puesto de checkin " + puestoChecking);
        
    }
}
