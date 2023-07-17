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
public class GestorCheckin {

    int cantPuestosCheckin;
    PuestoCheckin[] puestoCheckin;
    Thread[] guardiaPuestoCheckin ;
    Thread[] atencionCheckin;


    public GestorCheckin(int cantPuestos) {
        this.cantPuestosCheckin = cantPuestos;
        puestoCheckin = new PuestoCheckin[cantPuestosCheckin];
        guardiaPuestoCheckin = new Thread[cantPuestosCheckin];
        atencionCheckin = new Thread[cantPuestosCheckin];
        for (int i = 0; i < cantPuestosCheckin; i++) {
            puestoCheckin[i] = new PuestoCheckin();
        }
    }
    
    public void iniciarAtencion(){
         for (int i = 0; i < cantPuestosCheckin; i++) {
            guardiaPuestoCheckin[i] = new Thread(new GuardiaPuestoCheckin(puestoCheckin[i] ));
            guardiaPuestoCheckin[i].setName("GuardiaPuestoCheckin"+i);
            guardiaPuestoCheckin[i].start();
            atencionCheckin[i] = new Thread(new AtencionCheckin(puestoCheckin[i] ));
            atencionCheckin[i].setName("AtencionPuestoCheckin"+i);
            atencionCheckin[i].start();
        }
    }
    
    
public void hacerColaChecking(int puestoCheckin) {
        this.puestoCheckin[puestoCheckin].hacerColaCheckin();
        System.out.println("El " + Thread.currentThread().getName() + " esta haciendo cola en el puesto de Checkin " + puestoCheckin);
    }

    public void pasarPuestoCheckin(int puestoCheckin) {
        this.puestoCheckin[puestoCheckin].pasarPuestoCheckin();
        System.out.println("El " + Thread.currentThread().getName() + " pasa a hacer Checkin en el puesto " + puestoCheckin);
    }

    public char hacerChecking(int puestoCheckin, Reserva reserva) {
        char terminal = this.puestoCheckin[puestoCheckin].hacerCheckin(reserva);
        System.out.println("El " + Thread.currentThread().getName() + " hizo el Checkin en el puesto " + puestoCheckin);
        return terminal;
    }

    public void liberarPuestoCheckin(int puestoCheckin) {
        this.puestoCheckin[puestoCheckin].liberarPuestoCheckin();
        System.out.println( Thread.currentThread().getName() +" libero el puesto de checkin " + puestoCheckin);
        
    }
}