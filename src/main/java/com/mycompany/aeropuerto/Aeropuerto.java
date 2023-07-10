/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aeropuerto;

/**
 *
 * @author Academica
 */
public class Aeropuerto {

    public static void main(String[] args) {

        int NUM_PASAJEROS = 10;
        int CAPACIDAD_PEOPLEMOVER = 5;
        Reserva reserva= new Reserva();
        GestorInformes informes = new GestorInformes(NUM_PUESTOSCHECKING, NUM_PASAJEROS);
        GestorCheckin colaEspera = new GestorCheckin(NUM_PUESTOSCHECKING);
        GestorTransporte gestorTransporte = new GestorTransporte(CAPACIDAD_PEOPLEMOVER);
        GestorSalasEmbarque gestorSalaEmbarque = new GestorSalasEmbarque(NUM_TERMINALES);
        Thread atencionInformes = new Thread(new AtencionInformes(informes));
        atencionInformes.setName("Atencion Informes");
        atencionInformes.start();
    
        Thread[] pasajeros = new Thread[NUM_PASAJEROS];
        for (int j = 0; j < NUM_PASAJEROS; j++) {
            reserva.hacerReserva();
            pasajeros[j] = new Thread(new Pasajero(informes, colaEspera, gestorTransporte, gestorSalaEmbarque));
            pasajeros[j].setName("Pasajero" + (j + 1));
            pasajeros[j].start();
        }
    }
}
